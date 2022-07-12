package com.company;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * It represents a food.
 */
public class Food implements Serializable {
    //Name of the food
    private String name;
    //Price of the food
    private double price;

    public Food(String name, double price){
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the name of the food
     * @return Name of the food
     */
    public String getName(){ return name; }

    /**
     * Gets the price of the food
     * @return Price of the food
     */
    public double getPrice(){ return price;}

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("");
        builder.append(name);
        if (name.equals("Not reserved") || name.equals("\u2014")){
            return builder.toString();
        }
        builder.append("  ");
        builder.append("( ");
        builder.append(price).append(" R");
        builder.append(" )");

        return builder.toString();
    }

    /**
     * Returns the list of the saved foods in the file
     * @return List of the foods
     */
    public static ArrayList<Food> foodList(){
        ObjectInputStream reader = null;
        ArrayList<Food> toPrint = new ArrayList<Food>();

        Path path = Paths.get(User.getFoodAddress());
        try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)){
            for (Path p : directory){
                File file = p.toFile();
                reader = new ObjectInputStream(new FileInputStream(file.getPath()));
                Food food1 = (Food) reader.readObject();
                toPrint.add(food1);
                Food food2 = (Food) reader.readObject();
                toPrint.add(food2);
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
        if (toPrint.size()==0){
            for (int i=0 ; i<10 ; i++){
                toPrint.add(new Food("\u2014",0));
            }
        }
        return toPrint;
    }
}
