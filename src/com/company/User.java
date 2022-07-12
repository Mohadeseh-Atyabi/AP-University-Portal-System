package com.company;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * It represents a user of the portal.
 */
public class User implements Serializable {

    private static final String studentAddress = ".\\Files\\Students";
    private static final String adminAddress = ".\\Files\\Admin";
    private static final String professorAddress = ".\\Files\\Professors";
    private static final String foodAddress = ".\\Files\\Food";
    private static final String classAddress = ".\\Files\\Classes";

    //Username of the user
    private String username;
    //Password of the user
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Sets the username of the user
     * @param username Username of the user
     */
    public void setUsername(String username){ this.username = username; }

    /**
     * Sets the password of the user
     * @param password Password of the user
     */
    public void setPassword(String password){ this.password = password; }

    /**
     * Gets the username of the user
     * @return Username of the user
     */
    public String getUsername(){ return username; }

    /**
     * Gets the password of the user
     * @return Password of the user
     */
    public String getPassword(){ return password; }

    /**
     * Gets address of the student files
     * @return Address
     */
    public static String getStudentAddress(){ return studentAddress; }

    /**
     * Gets address of the admin files
     * @return Address
     */
    public static String getAdminAddress(){ return adminAddress; }

    /**
     * Gets address of the professor files
     * @return Address
     */
    public static String getProfessorAddress(){ return professorAddress; }

    /**
     * Gets address of the food files
     * @return Address
     */
    public static String getFoodAddress(){ return foodAddress; }

    /**
     * Gets address of the class files
     * @return Address
     */
    public static String getClassAddress(){ return classAddress; }

    /**
     *Checks whether the password is valid or not.
     * @param pass Password
     * @return true if valid, false if not.
     */
    public static boolean validPassword(String pass){
        if (pass.length() < 8){
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (!(obj instanceof User)){
            return false;
        }
        User other = (User) obj;
        return other.getUsername().equals(this.username) &&
                other.getPassword().equals(this.password);
    }

    /**
     * Checks whether the new password is valid to be changed or not
     * @param username Username of the user
     * @param newPas1 New password of the user
     * @param newPass2 Repeat new password
     * @return True if valid, false if not
     */
    public static boolean validChangePass(String username, String newPas1, String newPass2){
        if (username.length() == 0 || newPas1.length() == 0 || newPass2.length()==0){
            return false;
        }
        return newPas1.equals(newPass2);
    }

    /**
     * Checks whether the new username is valid to be changed or not
     * @param oldUser Current username
     * @param newUser New username
     * @param address Address of the files
     * @return True if valid, false if not
     */
    public static boolean validChangeUser(String oldUser, String newUser, String address){
        if (oldUser.length()==0 || newUser.length()==0){
            return false;
        }

        Path path = Paths.get(address);
        try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)){
            for (Path p : directory){
                if (p.toFile().getName().equals(newUser)){
                    return false;
                }
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return true;
    }

    /**
     * Returns the list of the exist classes
     * @return List of the classes
     */
    public static ArrayList<String[]> viewClass(){
        ObjectInputStream reader = null;
        ArrayList<String[]> toPrint = new ArrayList<String[]>();
        Path path = Paths.get(getClassAddress());
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)){
            for (Path p : stream){
                File file = p.toFile();
                reader = new ObjectInputStream(new FileInputStream(file.getPath()));
                Class temp = (Class) reader.readObject();
                String[] parts = temp.toString().split("/");
                toPrint.add(parts);
            }

        } catch (NullPointerException nullPointerException){
            String[] strings = {"", "", ""};
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
     * Checks whether the input is just digit or not
     * @param toCheck String to check
     * @return True if yes, false if not
     */
    public static boolean justDigit(String toCheck){
        for (char c : toCheck.toCharArray()){
            if (!(c>='0' && c<='9')){
                return false;
            }
        }
        return true;
    }


}
