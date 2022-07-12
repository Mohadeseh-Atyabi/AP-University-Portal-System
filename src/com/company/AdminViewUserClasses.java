package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is a frame in which admin views the classes of the professor or student
 */
public class AdminViewUserClasses extends JFrame {
    static AdminViewUserClasses frame;
    private Background contentPane;
    String userType;

    /**
     * Main method to open the frame after the row is clicked on the table.
     * @param user Username of the current user
     * @param userType Password of the current user
     */
    public static void start(String user, String userType){
        frame = new AdminViewUserClasses(user,userType);
        frame.setVisible(true);
    }

    /**
     * Constructor of the class to build the frame
     * @param user Username of the current user
     * @param userType Password of the current user
     */
    public AdminViewUserClasses(String user, String userType){
        this.userType = userType;

        setSize(800,700);
        setLocation(300,50);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Student classes");

        contentPane = new Background(".\\Images\\3033.jpg" , 800,700);
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(20,20,20,20));
        setContentPane(contentPane);

        switch (userType){
            case "Student" ->{
                String[] column = {"Code" , "Name" , "Day" , "Time" , "Unit" , "Grade"};
                JTable table = new JTable(){
                    @Override
                    public boolean isCellEditable(int row, int column){
                        return false;
                    }
                };
                table.setFont(new Font("Nani" , Font.ITALIC,13));
                table.setRowHeight(table.getRowHeight()+15);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setColumnIdentifiers(column);
                ArrayList<String[]> data1 = Admin.viewStudentClasses(user);
                for (String[] str : data1){
                    model.addRow(str);
                }
                for (int i=0 ; i<model.getRowCount() ; i++){
                    for (int j=0 ; j<model.getColumnCount() ; j++){
                        model.isCellEditable(i,j);
                    }
                }
                JScrollPane scrollPane = new JScrollPane(table);
                table.setFillsViewportHeight(true);

                contentPane.add(scrollPane,BorderLayout.CENTER);
            }

            case "Professor" -> {
                String[] column = {"Code" , "Name" , "Day" , "Time" , "Unit"};
                JTable table = new JTable(){
                    @Override
                    public boolean isCellEditable(int row, int column){
                        return false;
                    }
                };
                table.setFont(new Font("Nani" , Font.ITALIC,13));
                table.setRowHeight(table.getRowHeight()+15);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setColumnIdentifiers(column);
                ArrayList<String[]> data1 = Admin.viewProfessorClasses(user);
                for (String[] str : data1){
                    model.addRow(str);
                }
                for (int i=0 ; i<model.getRowCount() ; i++){
                    for (int j=0 ; j<model.getColumnCount() ; j++){
                        model.isCellEditable(i,j);
                    }
                }
                JScrollPane scrollPane = new JScrollPane(table);
                table.setFillsViewportHeight(true);

                contentPane.add(scrollPane,BorderLayout.CENTER);

            }
        }
    }

}
