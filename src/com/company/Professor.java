package com.company;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * It represents a professor of the university.
 */
public class Professor extends User{
    private static ObjectOutputStream writer;
    private static ObjectInputStream reader;

    //List of classes
    private ArrayList<String> classes;
    //Number of units he has
    private int units;

    public Professor(String username, String password) {
        super(username, password);
        classes = new ArrayList<>();
    }

    /**
     * Returns number of units of the professor
     * @return Number of units
     */
    public int getUnits(){ return units; }

    /**
     * Adds a class to the list of the classes of the professor
     * @param classToAdd Class to add
     */
    public void addClass(String classToAdd){ classes.add(classToAdd); }

    /**
     * Adds to the units of the professor
     * @param toAdd Number of units to add
     */
    public void addUnit(int toAdd){ units += toAdd; }

    /**
     * Reduces the number of the units
     * @param toDelete Number of units to delete
     */
    public void deleteUnit(int toDelete){ units -= toDelete; }

    /**
     * Returns list of the classes of the professor
     * @return List of the classes
     */
    public ArrayList<String> getClasses(){ return classes; }

    @Override
    public String toString(){
        String builder = getUsername() + "/" +
                getPassword() + "/" +
                getUnits();
        return builder;
    }

    /**
     * Checks data to login
     * @param user Username of the professor
     * @param pass Password of the professor
     * @return True if login is valid, false if not
     */
    public static boolean login(String user, String pass) {
        Path path = Paths.get(getProfessorAddress());
        Professor professor;
        DirectoryStream<Path> directory = null;
        try {
            directory = Files.newDirectoryStream(path);
            for (Path p : directory){
                File file = p.toFile();
                if (file.getName().equals(user)){
                    reader = new ObjectInputStream(new FileInputStream(file.getPath()));
                    professor = (Professor) reader.readObject();
                    return professor.getPassword().equals(pass);
                }
            }

        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                if (directory != null) {
                    directory.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * Changes the username of the professor
     * @param oldUser Current username
     * @param newUser New username
     * @return True if changed, false if not
     */
    public static boolean changeUsername(String  oldUser, String newUser){
        if (!validChangeUser(oldUser,newUser,getProfessorAddress())){
            return false;
        }

        Professor professor = null;
        Path path = Paths.get(getProfessorAddress());
        try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)){
            for (Path p : directory){
                File file = p.toFile();
                if (file.getName().equals(newUser)){
                    return false;
                }else if (file.getName().equals(oldUser)){
                    reader = new ObjectInputStream(new FileInputStream(file.getPath()));
                    professor = (Professor) reader.readObject();
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        } finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            Path path1 = Paths.get(getProfessorAddress()+"\\"+oldUser);
            try {
                Files.move(path1,path1.resolveSibling(newUser));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (professor==null){
            return false;
        }
        try {
            writer = new ObjectOutputStream(new FileOutputStream(getProfessorAddress()+"\\"+newUser));
            professor.setUsername(newUser);
            writer.writeObject(professor);

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
     * Changes the password of the professor
     * @param user Username of the professor
     * @param newPas1 New password of the professor
     * @param newPass2 Repeat new password
     * @return True if changed, false if not
     */
    public static boolean changePassword(String user, String newPas1, String newPass2){
        if (!validChangePass(user,newPas1,newPass2)){
            return false;
        }

        Professor professor = null;
        Path path = Paths.get(getProfessorAddress());
        try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)){
            for (Path p : directory){
                File file = p.toFile();
                if (file.getName().equals(user)){
                    reader = new ObjectInputStream(new FileInputStream(file.getPath()));
                    professor = (Professor) reader.readObject();
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        } finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        if (professor==null){
            return false;
        }
        try {
            writer = new ObjectOutputStream(new FileOutputStream(getProfessorAddress()+"\\"+user));
            professor.setPassword(newPas1);
            writer.writeObject(professor);
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
     * Adds a class to the list of the classes of the professor
     * @param currentUser Username of the current professor
     * @param newClass Class to add
     * @return True if dded, false if not
     */
    public static boolean addClass(String currentUser, Class newClass){

        //Check whether the code exists or not.
        Path path = Paths.get(getClassAddress());
        DirectoryStream<Path> directory = null;
        try {
            directory = Files.newDirectoryStream(path);
            for (Path p : directory){
                if (p.toFile().getName().equals(newClass.getCode())){
                    return false;
                }
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }finally {
            try {
                if (directory != null) {
                    directory.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        //Reads the professor from file
        Professor professor = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(getProfessorAddress()+"\\"+currentUser));
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
            if (!Class.professorValidClass(newClass.getDay(),newClass.getTime(), professor.getClasses())){
                return false;
            }
        }

        //Adds new class to the list of classes
        try {
            writer = new ObjectOutputStream(new FileOutputStream(getClassAddress()+"\\"+newClass.getCode()));
            writer.writeObject(newClass);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        //Adds the class to the professor's list
        if (professor != null) {
            professor.addClass(newClass.getCode());
            professor.addUnit(newClass.getUnit());
        }
        try {
            writer = new ObjectOutputStream(new FileOutputStream(getProfessorAddress()+"\\"+currentUser));
            writer.writeObject(professor);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        return true;
    }

    /**
     * Grades the student
     * @param currentUser Username of the professor
     * @param classCode Code of th class
     * @param studentUser Username of the student to grade
     * @param grade Grade of the student
     * @return True if grade is saved, false if not
     */
    public static boolean gradeStudent(String currentUser, String classCode, String studentUser, double grade){
        //Check whether the class is in professor's list or not
        Professor professor = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(getProfessorAddress()+"\\"+currentUser));
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

        boolean exist = false;
        if (professor != null) {
            for (String str : professor.getClasses()){
                if (str.equals(classCode)){
                    exist = true;
                    break;
                }
            }
        }
        if (!exist){
            return false;
        }
        //Checks whether the class is in the student's list
        Student student = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(getStudentAddress()+"\\"+studentUser));
            student = (Student) reader.readObject();

        } catch (FileNotFoundException fileNotFoundException){
            return false;
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        boolean changed = false;
        if (student != null) {
            for (Map.Entry<String,Double> cls : student.getClasses().entrySet()){
                if (cls.getKey().equals(classCode)){
                    if (cls.getValue()!=(double)-1){
                        return false;
                    }
                    else {
                        changed = true;
                        cls.setValue(grade);
                        break;
                    }
                }
            }
        }
        if (!changed){
            return false;
        }

        //Write student with grade in list
        try {
            writer = new ObjectOutputStream(new FileOutputStream(getStudentAddress()+"\\"+studentUser));
            writer.writeObject(student);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Returns the list of the student with students
     * @param currentUser Username of the current professor
     * @return List of the classes
     */
    public static ArrayList<String[]> listOfClassesWithStd(String currentUser){
        ArrayList<String[]> toPrint = new ArrayList<>();
        ArrayList<String> professorClass = new ArrayList<>();

        //Read professor's classes
        try {
            reader = new ObjectInputStream(new FileInputStream(getProfessorAddress()+"\\"+currentUser));
            Professor professor = (Professor) reader.readObject();
            professorClass = professor.getClasses();

        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        //If professor does not have any class.
        if (professorClass.size()==0){
            String[] temp = {"","","","","",""};
            toPrint.add(temp);
            return toPrint;
        }

        //If professor has classes
        for (String classes : professorClass){
            Class readClass = null;
            //Read the class of the professor
            try {
                reader = new ObjectInputStream(new FileInputStream(getClassAddress()+"\\"+ classes));
                readClass = (Class) reader.readObject();

            } catch (FileNotFoundException fileNotFoundException){
                String[] temp = {"","","","","",""};
                toPrint.add(temp);
                return toPrint;
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }finally {
                try {
                    reader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            if (readClass != null) {
                //If no student is in the class
                if (readClass.getStudents().size()==0){
                    String[] parts = readClass.toString().split("/");
                    String[] toAdd = new String[7];
                    System.arraycopy(parts, 0, toAdd, 0, 5);
                    toAdd[5] = toAdd[6] = "\u2014";
                    toPrint.add(toAdd);
                }else {
                    //Add students of the class to the list of classes
                    for (String student : readClass.getStudents()){
                        String[] parts = readClass.toString().split("/");
                        String[] toAdd = new String[7];
                        System.arraycopy(parts, 0, toAdd, 0, 5);
                        toAdd[5] = student;

                        try {
                            reader = new ObjectInputStream(new FileInputStream(getStudentAddress()+"\\"+student));
                            Student std = (Student) reader.readObject();
                            for (Map.Entry<String,Double> stdClass : std.getClasses().entrySet()){
                                if (stdClass.getKey().equals(readClass.getCode())){
                                    if (stdClass.getValue()==(double)-1){
                                        toAdd[6] = "\u2014";
                                    }
                                    else
                                        toAdd[6] = String.valueOf(stdClass.getValue());
                                    toPrint.add(toAdd);
                                    break;
                                }
                            }

                        } catch (IOException | ClassNotFoundException ioException) {
                            ioException.printStackTrace();
                        }finally {
                            try {
                                reader.close();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return toPrint;
    }

    /**
     * Deletes the class from the file
     * @param currentUser Username of the professor
     * @param classCode Code of the class to delete
     * @return True if deleted, false if not
     */
    public static boolean deleteClass(String currentUser, String classCode){
        Class classToDelete = null;
        //Read the class from the file
        try {
            reader = new ObjectInputStream(new FileInputStream(getClassAddress()+"\\"+classCode));
            classToDelete = (Class) reader.readObject();

        } catch (FileNotFoundException fileNotFoundException){
            return false;
        }
        catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        //If class does not exist
        if (classToDelete==null){
            System.out.println("Class does not exist");
            return false;

        }

        //Delete the class from the file
        Path path = Paths.get(getClassAddress());
        DirectoryStream<Path> directory = null;
        try {
            directory = Files.newDirectoryStream(path);
            for (Path path1 : directory) {
                File file = path1.toFile();
                if (file.getName().equals(classCode)) {
                    if (!file.delete()) {
                        System.out.println("File didn't deleted.");
                        return false;
                    }
                    break;
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }finally {
            try {
                if (directory != null) {
                    directory.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        //Read students of the class
        for (String classStudents : classToDelete.getStudents()){
            Student student = null;

            try {
                reader = new ObjectInputStream(new FileInputStream(getStudentAddress()+"\\"+ classStudents));
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

            //Delete the class from student list & sets new units
            if (student != null) {
                double deleteMark = 0;
                for (Map.Entry<String,Double> temp : student.getClasses().entrySet()){
                    if (temp.getKey().equals(classCode)){
                        deleteMark = temp.getValue();
                        break;
                    }
                }
                student.addSumOfDeleted(deleteMark * classToDelete.getUnit());
                student.addDeletedUnits(classToDelete.getUnit());
                student.deleteUnit(classToDelete.getUnit());
                student.getClasses().remove(classCode);

                try {
                    writer = new ObjectOutputStream(new FileOutputStream(getStudentAddress()+"\\"+classStudents));
                    writer.writeObject(student);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }finally {
                    try {
                        writer.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }

        //Delete the class from professor list
        Professor professor = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(getProfessorAddress()+"\\"+currentUser));
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

        if (professor!=null){
            professor.getClasses().remove(classCode);
            professor.deleteUnit(classToDelete.getUnit());
            try {
                writer = new ObjectOutputStream(new FileOutputStream(getProfessorAddress()+"\\"+currentUser));
                writer.writeObject(professor);

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }finally {
                try {
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        return true;
    }

    /**
     * Returns the list of the classes of the professor
     * @param currentUser Username of the current professor
     * @return List of the classes
     */
    public static ArrayList<String[]> listOfClasses(String currentUser){
        ArrayList<String[]> toPrint = new ArrayList<>();
        Professor professor = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(getProfessorAddress()+"\\"+currentUser));
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
            for (String classes : professor.getClasses()){
                try {
                    reader = new ObjectInputStream(new FileInputStream(getClassAddress()+"\\"+classes));
                    Class cls = (Class) reader.readObject();
                    String[] parts = cls.toString().split("/");
                    String[] toAdd = new String[5];
                    System.arraycopy(parts, 0, toAdd, 0, 5);
                    toPrint.add(toAdd);

                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }finally {
                    try {
                        reader.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
        return toPrint;
    }
}