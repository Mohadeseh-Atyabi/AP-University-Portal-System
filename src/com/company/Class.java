package com.company;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a class in the university.
 */
public class Class implements Serializable {
    //Day of the class
    private Day day;
    //Time of the class
    private Time time;
    //Name of the class
    private String name;
    //Code of the class (which is unique)
    private String code;
    //Number of units
    private int unit;
    //Capacity of the class
    private int capacity;
    //List of students in this class
    private ArrayList<String> students;

    public Class(Day day, Time time,String name, String code, int unit, int capacity){
        this.day = day;
        this.time = time;
        this.code = code;
        this.name = name;
        this.unit = unit;
        this.capacity = capacity;
        students = new ArrayList<String>();
    }

    /**
     * Adds a student to the list.
     * @param toAdd Student to add.
     */
    public void addToStudents(String toAdd){
        students.add(toAdd);
    }

    /**
     * Gets the list of the students.
     * @return List of the students
     */
    public ArrayList<String> getStudents(){ return students; }

    /**
     * Sets the day of the class.
     * @param day Day of the class
     */
    public void setDay(Day day){ this.day = day; }

    /**
     * Sets the time of the class
     * @param time Time of the class
     */
    public void setTime(Time time){ this.time = time; }

    /**
     * Sets the name of the class
     * @param name Name of the class
     */
    public void setName(String name){ this.name = name;}

    /**
     * Sets the code of the lass
     * @param code Code of the class
     */
    public void setCode(String code){ this.code = code;}

    /**
     * Sets the unit of the class
     * @param unit Number of the units of the class
     */
    public void setUnit(int unit){ this.unit = unit;}

    /**
     * Gets the name of the class
     * @return Name of the class
     */
    public String getName(){ return name;}

    /**
     * Gets the code of the class
     * @return Code of the class
     */
    public String getCode(){ return code;}

    /**
     * Gets the day of the class
     * @return Day of the class
     */
    public Day getDay(){ return day;}

    /**
     * Gets the time of the class
     * @return Time of the class
     */
    public Time getTime(){ return time;}

    /**
     * Gets the unit of the class
     * @return Units of the class
     */
    public int getUnit(){ return unit; }

    /**
     * Gets the capacity of the class
     * @return Capacity of the class
     */
    public int getCapacity(){ return capacity; }

    /**
     * Reduces the capacity of the class
     */
    public void lessenCapacity(){ capacity--; }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("");
        builder.append(code).append("/");
        builder.append(getName()).append("/");
        switch (getDay()){
            case SATURDAY -> builder.append("Saturday");
            case SUNDAY -> builder.append("Sunday");
            case MONDAY -> builder.append("Monday");
            case WEDNESDAY -> builder.append("Wednesday");
            case TUESDAY -> builder.append("Tuesday");
        }
        builder.append("/");
        switch (getTime()){
            case EIGHT_TEN -> builder.append("8-10");
            case TEN_TWELVE -> builder.append("10-12");
            case FOURTEEN_SIXTEEN -> builder.append("14-16");
        }
        builder.append("/").append(unit).append("/");
        builder.append(capacity);
        return builder.toString();
    }

    /**
     * Checks whether the student can choose the class or not
     * @param day Day of the class
     * @param time Tie of the class
     * @param classes List of exist classes
     * @return True if valid, false if not
     */
    public static boolean studentValidClass(Day day, Time time, HashMap<String,Double> classes){
        ObjectInputStream reader = null;

        //The student has no classes
        if (classes.size()==0){
            return true;
        }

        for (Map.Entry<String,Double> temp : classes.entrySet()){

            //Reads the class
            Path path = Paths.get(User.getClassAddress());
            try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)){
                for (Path p : directory){
                    File file = p.toFile();
                    if (file.getName().equals(temp.getKey())){
                        reader = new ObjectInputStream(new FileInputStream(file.getPath()));
                        Class readClass = (Class) reader.readObject();
                        if (readClass.getDay().equals(day) && readClass.getTime().equals(time)){
                            return false;
                        }
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
        }
        return true;
    }

    /**
     * Checks whether the professor can choose the class or not
     * @param day Day of the class
     * @param time Tie of the class
     * @param classes List of exist classes
     * @return True if valid, false if not
     */
    public static boolean professorValidClass(Day day, Time time, ArrayList<String> classes){
        ObjectInputStream reader = null;
        if (classes.size()==0){
            return true;
        }

        for (String cls : classes){
            Class temp = null;
            try {
                reader = new ObjectInputStream(new FileInputStream(User.getClassAddress()+"\\"+cls));
                temp = (Class) reader.readObject();
                if (temp.getDay().equals(day) && temp.getTime().equals(time)){
                    return false;
                }

            } catch (IOException | ClassNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        return true;
    }
}
