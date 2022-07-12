package com.company;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * It represents a student in the university.
 */
public class Student extends User{
    private static ObjectOutputStream writer;
    private static ObjectInputStream reader;

    //Balance of the account
    private double balance;
    //Average of the grades
    private double average;
    //List of the classes and their marks
    private HashMap<String,Double> classes;
    //Number of units
    private int unit;
    //List of foods
    private String[] foods;
    //Number of units which are deleted
    private int deletedUnits;
    //Summation of deleted marks
    private double sumOfDeleted;

    public Student(String username, String password) {
        super(username, password);
        classes = new HashMap<String,Double>();
        foods = new String[]{"Not reserved", "Not reserved", "Not reserved", "Not reserved", "Not reserved"};
        average = (double) -1;
    }

    /**
     * Adds to units
     * @param toAdd Number of units to be added
     */
    public void addUnits(int toAdd){ unit += toAdd; }

    /**
     * Returns number of deleted units
     * @return Number of deleted units
     */
    public int getDeletedUnits(){ return deletedUnits; }

    /**
     * Returns the summation of the deleted units
     * @return Summation of the deleted units
     */
    public double getSumOfDeleted() { return sumOfDeleted; }

    /**
     * Adds to the number of deleted units
     * @param toAdd Number to add
     */
    public void addDeletedUnits(int toAdd){ deletedUnits += toAdd; }

    /**
     * Adds to the summation of the deleted units
     * @param toAdd Number to add
     */
    public void addSumOfDeleted(double toAdd){ sumOfDeleted += toAdd; }

    /**
     * Returns the number of units
     * @return Number of units
     */
    public int getUnit(){ return unit; }

    /**
     * Returns the average of the student
     * @return Average of the student
     */
    public double getAverage() { return average; }

    /**
     * Returns the list of the classes of the student
     * @return List of classes
     */
    public HashMap<String,Double> getClasses(){ return classes; }

    /**
     * Returns the balance of the student
     * @return Balance of the student
     */
    public double getBalance(){ return balance; }

    /**
     * Returns the list of foods of the student
     * @return List of food
     */
    public String[] getFoods(){ return foods; }

    /**
     * Adds to the balance oof the student
     * @param toAdd Number to add
     */
    public void addBalance(double toAdd){ balance += toAdd; }

    /**
     * Changes the average of the student
     * @param average Average
     */
    public void changeAverage(double average){ this.average = average; }

    /**
     * Reduces the number of the units of the student
     * @param toDelete Number to delete
     */
    public void deleteUnit(int toDelete){ unit -= toDelete; }

    /**
     * Withdraws from the balance.
     * @param toWithdraw Amount of needed
     */
    public void withdraw(double toWithdraw){
        balance -= toWithdraw;
    }

    @Override
    public String toString(){
        changeAverage(calculateAverage(getUsername()));

        String builder = "" + getUsername() + "/" +
                getPassword() + "/" +
                (getAverage()==(double) -1 ? "\u2014" : getAverage() ) + "/" +
                unit;
        return builder;
    }

    /**
     * Changes the password of the student
     * @param user Username of the current user
     * @param newPas1 New password
     * @param newPass2 Repeat new password
     * @return True if changed, false if not.
     */
    public static boolean changePassword(String user, String newPas1, String newPass2){
        if (!validChangePass(user,newPas1,newPass2)){
            return false;
        }

        Student student = null;
        Path path = Paths.get(getStudentAddress());
        try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)){
            for (Path p : directory){
                File file = p.toFile();
                if (file.getName().equals(user)){
                    reader = new ObjectInputStream(new FileInputStream(file.getPath()));
                    student = (Student) reader.readObject();
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

        if (student==null){
            return false;
        }
        try {
            writer = new ObjectOutputStream(new FileOutputStream(getStudentAddress()+"\\"+user));
            student.setPassword(newPas1);
            writer.writeObject(student);
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
     * Changes the username of the student
     * @param oldUser Current username of the student
     * @param newUser New username of the student
     * @return True if changed, false if not
     */
    public static boolean changeUsername(String  oldUser, String newUser){
        if (!validChangeUser(oldUser,newUser,getStudentAddress())){
            return false;
        }

        Student student = null;
        Path path = Paths.get(getStudentAddress());
        try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)){
            for (Path p : directory){
                File file = p.toFile();
                if (file.getName().equals(newUser)){
                    return false;
                }else if (file.getName().equals(oldUser)){
                    reader = new ObjectInputStream(new FileInputStream(file.getPath()));
                    student = (Student) reader.readObject();
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
            Path path1 = Paths.get(getStudentAddress()+"\\"+oldUser);
            try {
                Files.move(path1,path1.resolveSibling(newUser));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (student==null){
            return false;
        }
        try {
            writer = new ObjectOutputStream(new FileOutputStream(getStudentAddress()+"\\"+newUser));
            student.setUsername(newUser);
            writer.writeObject(student);

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
     * Checks the data to login
     * @param user Username
     * @param pass Password
     * @return True if login is valid, false if not.
     */
    public static boolean login(String user, String pass) {
        Path path = Paths.get(getStudentAddress());
        Student student = null;
        try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)){
            for (Path p : directory){
                File file = p.toFile();
                if (file.getName().equals(user)){
                    reader = new ObjectInputStream(new FileInputStream(file.getPath()));
                    student = (Student) reader.readObject();
                    return student.getPassword().equals(pass);
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
        return false;
    }

    /**
     * Adds a class to the list of the classes of the student
     * @param classCode Code of the class to be added
     * @param currentUser Username of the student
     * @return True if added, false if not
     */
    public static boolean addClass(String classCode, String currentUser){
        Class chosenClass = null;

        Path path = Paths.get(getClassAddress());
        DirectoryStream<Path> directory = null;
        try {
            directory = Files.newDirectoryStream(path);
            for (Path p : directory){
                if (p.toFile().getName().equals(classCode)){
                    reader = new ObjectInputStream(new FileInputStream(p.toFile().getPath()));
                    chosenClass = (Class) reader.readObject();
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
        if (chosenClass==null){
            return false;
        }else {
            if (chosenClass.getCapacity()==0){
                return false;
            }else {

                Student student = null;
                try {
                    reader = new ObjectInputStream(new FileInputStream(getStudentAddress()+"\\"+currentUser));
                    student = (Student) reader.readObject();

                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        reader.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

                if (student==null || !Class.studentValidClass(chosenClass.getDay(),chosenClass.getTime(),student.getClasses())){
                    return false;
                }
                student.changeAverage(calculateAverage(student.getUsername()));
                if (student.getAverage() >= 17){
                    if (student.getUnit()+ chosenClass.getUnit() > 24){
                        return false;
                    }
                }else {
                    if (student.getUnit()+ chosenClass.getUnit() > 20){
                        return false;
                    }
                }
                student.classes.put(classCode, (double)-1);
                student.addUnits(chosenClass.getUnit());
                try {
                    writer = new ObjectOutputStream(new FileOutputStream(getStudentAddress()+"\\"+currentUser));
                    writer.writeObject(student);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        writer.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

                chosenClass.lessenCapacity();
                chosenClass.addToStudents(currentUser);
                try {
                    writer = new ObjectOutputStream(new FileOutputStream(getClassAddress()+"\\"+classCode));
                    writer.writeObject(chosenClass);

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
        return true;
    }

    /**
     * Sets the foods for the student
     * @param food Food
     * @param day Day
     * @param list List of the foods of the student
     * @return True if set, false if not
     */
    public boolean setFood(Food food,Day day,String[] list ){
        int index = 0;
        switch (day){
            case SATURDAY -> { }
            case SUNDAY -> index = 1;
            case MONDAY -> index = 2;
            case TUESDAY -> index = 3;
            case WEDNESDAY -> index = 4;
        }

        if (!list[index].equals("Not reserved")){
            return false;
        }else {
            list[index] = food.toString();
        }
        return true;
    }

    /**
     * Adds food to the list
     * @param day Day
     * @param food Food
     * @param currentUser Username of the current student
     * @return True if added, false if not
     */
    public static boolean addFood(Day day, Food food, String currentUser){
        Student student = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(getStudentAddress()+"\\"+currentUser));
            student = (Student) reader.readObject();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (student!=null && student.getBalance() < food.getPrice()){
            return false;
        }else {
            if (student != null) {
                if (!student.setFood(food,day,student.getFoods())){
                    return false;
                }else {
                    student.withdraw(food.getPrice());
                    try {
                        writer = new ObjectOutputStream(new FileOutputStream(getStudentAddress()+"\\"+currentUser));
                        writer.writeObject(student);
                        return true;
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
        }
        return true;
    }

    /**
     * Charges the account of the student
     * @param amount Amount to add
     * @param card Card number
     * @param pass Password of the card
     * @param currentUser Username of the current user
     * @return True if charged, false if not
     */
    public static boolean chargeAccount(String amount, String card, String pass, String currentUser){
        if (!(User.justDigit(amount) && User.justDigit(card) && User.justDigit(pass))){
            return false;
        }
        if (card.length()!=16 || amount.length()==0 || pass.length()==0){
            return false;
        }

        Student student = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(getStudentAddress()+"\\"+currentUser));
            student = (Student) reader.readObject();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (student!=null)
            student.addBalance(Double.parseDouble(amount));

        try {
            writer = new ObjectOutputStream(new FileOutputStream(getStudentAddress()+"\\"+currentUser));
            writer.writeObject(student);

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
     * Returns the balance of the student
     * @param currentUser Username of the current student
     * @return Balance
     */
    public static double getBalanceOfStudent(String currentUser){
        Student student = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(getStudentAddress()+"\\"+currentUser));
            student = (Student) reader.readObject();

        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if (student!=null)
            return student.getBalance();
        else
            return 0.0;
    }

    /**
     * Returns the list of the classes of the student
     * @param currentUser Username of the current student
     * @return List of classes
     */
    public static ArrayList<String[]> studentClass(String currentUser){
        ArrayList<String[]> toPrint = new ArrayList<String[]>();
        Student student = null;
        //Read the student
        try {
            reader = new ObjectInputStream(new FileInputStream(getStudentAddress()+"\\"+currentUser));
            student = (Student) reader.readObject();

        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        //Read classes of the student
        if (student!=null){
            HashMap<String,Double> classes = student.getClasses();
            for (Map.Entry<String,Double> temp : classes.entrySet()){
                String tempClass = temp.getKey();
                try {
                    reader = new ObjectInputStream(new FileInputStream(getClassAddress()+"\\"+ tempClass));
                    Class cls = (Class) reader.readObject();
                    String[] parts = cls.toString().split("/");
                    String[] toAdd = new String[6];
                    System.arraycopy(parts, 0, toAdd, 0, 5);
                    if (temp.getValue() == (double)-1){
                        toAdd[5] = "\u2014";
                    }else {
                        toAdd[5] = temp.getValue().toString();
                    }
                    toPrint.add(toAdd);

                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                } finally {
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

    /**
     * Returns the list of the foods of the student
     * @param currentUser Username of the current student
     * @return List of the foods
     */
    public static String[] studentFood(String currentUser){
        Student student = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(getStudentAddress()+"\\"+currentUser));
            student = (Student) reader.readObject();

        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if (student==null){
            return null;
        }
        return student.getFoods();
    }

    /**
     * Calculates the average of the student
     * @param currentUser Username of the current student
     * @return Average of the student
     */
    public static double calculateAverage(String currentUser){
        Student student = null;
        double avg = (double) -1;
        double sum = 0;
        double numOfUnits = 0;

        //Read student
        try {
            reader = new ObjectInputStream(new FileInputStream(getStudentAddress()+"\\"+currentUser));
            student = (Student) reader.readObject();

        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        //Read units and grades
        if (student != null) {

            for (Map.Entry<String,Double> temp : student.getClasses().entrySet()){
                if (temp.getValue() != (double)-1){
                    try {
                        reader = new ObjectInputStream(new FileInputStream(getClassAddress()+"\\"+temp.getKey()));
                        Class cls= (Class) reader.readObject();
                        sum += (cls.getUnit()*temp.getValue());
                        numOfUnits += cls.getUnit();

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
            if (numOfUnits+student.getDeletedUnits()!=0) {
                avg = (sum + student.getSumOfDeleted()) / (numOfUnits + student.getDeletedUnits());
                student.changeAverage(avg);

                try {
                    writer = new ObjectOutputStream(new FileOutputStream(getStudentAddress()+"\\"+currentUser));
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
        return Math.round(avg*100.0)/100.0;
    }

}
