package com.company;
import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        //Initialize Admin
//        FileOutputStream file = null;
//        ObjectOutputStream writer = null;
//        try{
//            file = new FileOutputStream(".\\Files\\Admin\\9726004");
//            Admin admin = new Admin("9726004","10101010");
//            writer = new ObjectOutputStream(file);
//            writer.writeObject(admin);
//
//        }catch (IOException e){
//            System.out.println("Exception");
//        }finally {
//            try {
//                writer.close();
//                file.close();
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        }

        Entrance.start();


    }
}
