package com.company;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is a panel to see information of the student.
 */
public class StudentAccountInfo extends Background {
    User currentUser;

    /**
     * Main method to open the frame after the previous one is closed.
     * @param user Username of the current user
     * @param pass Password of the current user
     */
    public StudentAccountInfo(String user, String pass){
        super(".\\Images\\3033.jpg",650,700);
        currentUser = new User(user,pass);

        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"Account Information");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));

        //Classes of the student
        String[] column = {"Code" , "Name", "Day" , "Time" , "Unit" , "Grade"};
        JTable table = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.setRowHeight(getHeight()+25);
        table.setFont(new Font("Nani" , Font.ITALIC,12));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(column);
        ArrayList<String[]> data1 = Student.studentClass(currentUser.getUsername());
        for (String[] str : data1){
            model.addRow(str);
        }
        for (int i=0 ; i<model.getRowCount() ; i++){
            for (int j=0 ; j<model.getColumnCount() ; j++){
                model.isCellEditable(i,j);
            }
        }
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        //Average of the student
        double avg = Student.calculateAverage(currentUser.getUsername());
        String disp = (avg==(double)-1) ? "\u2014" : String.valueOf(avg);
        JLabel average = new JLabel("Your total average is  " + disp);
        average.setFont(new Font("Nani" , Font.BOLD , 15));

        //Foods of the student
        String[] studentFood = Student.studentFood(currentUser.getUsername());
        String[][] food = new String[0][];
        if (studentFood != null) {
            food = new String[][]{{"Saturday" , studentFood[0]} , {"Sunday" , studentFood[1]} , {"Monday" , studentFood[2]},
                    {"Tuesday" , studentFood[3]} , {"Wednesday" , studentFood[4]}};
        }
        String[] header = {"Day" , "Food"};
        JTable foodTable = new JTable(food,header){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.setFont(new Font("Nani" , Font.ITALIC,13));
        for (int i=0 ; i<model.getRowCount() ; i++){
            for (int j=0 ; j<5 ; j++){
                model.isCellEditable(i,j);
            }
        }
        foodTable.setRowHeight(getHeight()+25);
        foodTable.setFont(new Font("Nani" , Font.ITALIC,12));
        JScrollPane foodScroll = new JScrollPane(foodTable);
        table.setFillsViewportHeight(true);

        JLabel classLabel = new JLabel("Class");
        classLabel.setFont(new Font("Nani" , Font.BOLD , 15));
        JLabel foodlabel = new JLabel("Food");
        foodlabel.setFont(new Font("Nani" , Font.BOLD , 15));

        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(average)
                    .addComponent(classLabel)
                    .addComponent(scrollPane)
                    .addComponent(foodlabel)
                    .addComponent(foodScroll)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                    .addComponent(average)
                    .addComponent(classLabel)
                    .addComponent(scrollPane)
                    .addComponent(foodlabel)
                    .addComponent(foodScroll,154,175,200)
        );
    }
}
