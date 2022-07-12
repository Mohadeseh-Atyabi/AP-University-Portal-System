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
 * This class is a panel to view classes.
 */
public class ViewClass extends Background {

    /**
     * Constructor of the class to build the frame
     */
    public ViewClass(){
        super(".\\Images\\3033.jpg",650,700);
        setLayout(new BorderLayout());
        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"View Classes");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));

        //Fill
        String[] column = {"Code" , "Name", "Day" , "Time" , "Unit" , "Capacity"};
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
        ArrayList<String[]> data1 = User.viewClass();
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

        add(scrollPane,BorderLayout.CENTER);

    }

}
