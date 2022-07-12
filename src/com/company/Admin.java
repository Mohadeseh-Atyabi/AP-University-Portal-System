package com.company;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

/**
 * Admin of the university
 */
public class Admin extends User{
    private static ObjectOutputStream writer;
    private static ObjectInputStream reader;

    public Admin(String username, String password) {
        super(username, password);
    }

    /**
     * Adds student to portal.
     * @param user Username of the student
     * @param pass Password of the student
     * @return true if saved, false if not.
     */
    public static boolean addStudent(String user, String pass){
        Path p = Paths.get(getStudentAddress());
        try (DirectoryStream<Path> student = Files.newDirectoryStream(p)) {
            for (Path p1 : student){
                File temp = p1.toFile();
                if (temp.getName().equals(user)){
                    return false;
                }
            }
        } catch (IOException ioException) {
            System.out.println("Error in new directory");
            ioException.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream(getStudentAddress()+"\\"+user)) {
            writer = new ObjectOutputStream(fos);
            Student std = new Student(user,pass);
            writer.writeObject(std);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException ioException) {
                System.out.println("Error in closing file.");
                ioException.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Adds professor to the portal.
     * @param user Username of the professor
     * @param pass Password of the professor
     * @return true if save, false if not.
     */
    public static boolean addProfessor(String user, String pass){
        Path p = Paths.get(getProfessorAddress());
        try (DirectoryStream<Path> professor = Files.newDirectoryStream(p)) {
            for (Path p1 : professor){
                File temp = p1.toFile();
                if (temp.getName().equals(user)){
                    return false;
                }
            }
        } catch (IOException ioException) {
            System.out.println("Error in new directory");
            ioException.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream(getProfessorAddress()+"\\"+user)) {
            writer = new ObjectOutputStream(fos);
            Professor std = new Professor(user,pass);
            writer.writeObject(std);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException ioException) {
                System.out.println("Error in closing file.");
                ioException.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Checks the information to login.
     * @param user Username of the user
     * @param pass Password of the user
     * @return true if login is possible, false if not.
     */
    public static boolean login(String user, String pass){
        FileInputStream file = null;
        try {
            file = new FileInputStream(getAdminAddress()+"\\"+user);
            reader = new ObjectInputStream(file);
            Admin admin = (Admin) reader.readObject();
            Admin newAdmin = new Admin(user,pass);
            return admin.equals(newAdmin);

        } catch (FileNotFoundException fileNotFoundException){
            return false;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader!=null){
                try {
                    reader.close();
                    if (file != null) {
                        file.close();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * Adds food to the list of the foods.
     * @param food1 First food
     * @param food2 Second food
     * @param day Day to set foods
     */
    public static void addFood(Food food1, Food food2, String day){
        boolean found = false;
        Path path = Paths.get(getFoodAddress());
        try (DirectoryStream<Path> food = Files.newDirectoryStream(path)) {
            for (Path p : food){
                File temp = p.toFile();
                if (temp.getName().equals(day)){
                    found = true;
                    writer= new ObjectOutputStream(new FileOutputStream(temp.getPath()));
                    writer.writeObject(food1);
                    writer.writeObject(food2);
                    return;
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }finally {
            try {
                if (writer!=null)
                     writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (!found){
            try {
                writer = new ObjectOutputStream(new FileOutputStream(getFoodAddress()+'\\'+day));
                writer.writeObject(food1);
                writer.writeObject(food2);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

        }
    }

    /**
     * Returns the list of the students in the university.
     * @return List of students
     */
    public static ArrayList<String[]> viewStudent(){
        ArrayList<String[]> toPrint = new ArrayList<String[]>();

        //Read students from the file and add them to list to return
        Path path = Paths.get(getStudentAddress());
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)){
            for (Path p : stream){
                File file = p.toFile();
                reader = new ObjectInputStream(new FileInputStream(file.getPath()));
                Student temp = (Student) reader.readObject();
                String[] parts = temp.toString().split("/");
                toPrint.add(parts);
            }

        } catch (NullPointerException nullPointerException){
          String[] strings = {"", "", "", ""};
          toPrint.add(strings);

        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                if (reader!=null)
                    reader.close();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return toPrint;
    }

    /**
     * Returns the list of the professors in the university.
     * @return List of professors
     */
    public static ArrayList<String[]> viewProfessor(){
        ArrayList<String[]> toPrint = new ArrayList<String[]>();
        Path path = Paths.get(getProfessorAddress());
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)){
            for (Path p : stream){
                File file = p.toFile();
                reader = new ObjectInputStream(new FileInputStream(file.getPath()));
                Professor temp = (Professor) reader.readObject();
                String[] parts = temp.toString().split("/");
                toPrint.add(parts);
            }

        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        } catch (NullPointerException nullPointerException){
            String[] strings = {"", "", "", ""};
            toPrint.add(strings);

        }finally {
            try {
                if (reader!=null)
                    reader.close();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return toPrint;
    }

    /**
     * Changes the password of the admin
     * @param user Username of the admin
     * @param newPass1 New password
     * @param newPass2 Repeat new password
     * @return True if changed, false if not
     */
    public static boolean changePassword(String user, String newPass1, String newPass2){
        //Checks whether the inputs are valid or not
        if (!validChangePass(user,newPass1,newPass2)){
            return false;
        }

        //Reads the admin from the file
        Admin admin = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(getAdminAddress()+"\\"+user));
            admin = (Admin) reader.readObject();

        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        } finally {
            if (reader!=null) {
                try {
                    reader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        if (admin==null){
            return false;
        }
        //Writes admin with new password in rhe file
        try {
            writer = new ObjectOutputStream(new FileOutputStream(getAdminAddress()+"\\"+user));
            admin.setPassword(newPass1);
            writer.writeObject(admin);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
            writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Changes the username of the admin
     * @param oldUser Current username of the admin
     * @param newUser New username of the admin
     * @return True if changed, false if not
     */
    public static boolean changeUsername(String oldUser, String newUser){
        //Checks whether the inputs are valid or not
        if (!validChangeUser(oldUser,newUser,getAdminAddress())){
            return false;
        }

        //Reads admin from file
        Admin admin = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(getAdminAddress()+"\\"+oldUser));
            admin = (Admin) reader.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (admin!=null) {
                try {
                    reader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            //Rename the file
            Path path = Paths.get(getAdminAddress()+"\\"+oldUser);
            try {
                Files.move(path,path.resolveSibling(newUser));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (admin==null){
            return false;
        }
        //Writes the admin with new information in the file
        try {
            writer = new ObjectOutputStream(new FileOutputStream(getAdminAddress()+"\\"+newUser));
            admin.setUsername(newUser);
            writer.writeObject(admin);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Returns the list of the classes of the student.
     * @param studentUser Username of the student to see classes
     * @return List of classes
     */
    public static ArrayList<String[]> viewStudentClasses(String studentUser){
        Student student = null;
        ArrayList<String[]> toPrint = new ArrayList<>();

        //Reads the student from the file
        try {
            reader = new ObjectInputStream(new FileInputStream(getStudentAddress()+"\\"+ studentUser));
            student = (Student) reader.readObject();
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (student != null) {
            //Search classes of the student
            for (Map.Entry<String,Double> classes : student.getClasses().entrySet()){
                Class readClass = null;
                //Reads the class
                try {
                    reader = new ObjectInputStream(new FileInputStream(getClassAddress()+"\\"+classes.getKey()));
                    readClass = (Class) reader.readObject();

                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }finally {
                    try {
                        reader.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

                //Adds classes to list to return
                if (readClass!=null){
                    String[] parts = readClass.toString().split("/");
                    String[] toAdd = new String[6];
                    System.arraycopy(parts, 0, toAdd, 0 , 5);
                    if (classes.getValue()==(double) -1){
                        toAdd[5] = "\u2014";
                    }else
                        toAdd[5] = classes.getValue().toString();
                    toPrint.add(toAdd);
                }
            }
        }
        //Fill the list if it's empty
        if (toPrint.size()==0){
            String[] parts = new String[]{"","","","","",""};
            toPrint.add(parts);
        }
        return toPrint;
    }

    /**
     * Returns the list of the classes of the professors.
     * @param professorUser Username of the professor to see classes
     * @return List of classes
     */
    public static ArrayList<String[]> viewProfessorClasses(String professorUser){
        Professor professor = null;
        ArrayList<String[]> toPrint = new ArrayList<>();

        //Reads the admin from the file
        try {
            reader = new ObjectInputStream(new FileInputStream(getProfessorAddress()+"\\"+ professorUser));
            professor = (Professor) reader.readObject();
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (professor != null) {
            //Reads class
            for (String classes : professor.getClasses()){
                Class readClass = null;
                try {
                    reader = new ObjectInputStream(new FileInputStream(getClassAddress()+"\\"+classes));
                    readClass = (Class) reader.readObject();

                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }finally {
                    try {
                        reader.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

                if (readClass!=null){
                    String[] parts = readClass.toString().split("/");
                    String[] toAdd = new String[5];
                    System.arraycopy(parts, 0, toAdd, 0 , 5);
                    toPrint.add(toAdd);
                }
            }
        }
        //Fill the list if it's empty
        if (toPrint.size()==0){
            String[] parts = new String[]{"","","","",""};
            toPrint.add(parts);
        }
        return toPrint;
    }
}
