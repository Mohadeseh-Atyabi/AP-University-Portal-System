package com.company;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * This class is a panel to view students.
 */
public class ViewStudent extends Background {
    User currentUser;

    /**
     * Constructor of the class to build the frame
     * @param user Username of the current user
     * @param pass Password of the current user
     */
    public ViewStudent(String user, String pass){
        super(".\\Images\\3033.jpg",650,700);

        currentUser = new User(user,pass);

        setLayout(new BorderLayout());
        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"View Students");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));

        //Fill it according to list
        String[] column = {"Username" , "Password" , "Average" , "Unit"};
        JTable table = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.setRowHeight(getHeight()+25);
        table.setFont(new Font("Nani" , Font.ITALIC,13));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(column);
        ArrayList<String[]> data1 = Admin.viewStudent();
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

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    AdminViewUserClasses.start(model.getValueAt(target.getSelectedRow(),0).toString(),"Student");
                }
            }
        });

        add(scrollPane,BorderLayout.CENTER);
    }

}
