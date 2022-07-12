package com.company;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class is a panel foe student to choose class.
 */
public class ChooseClass extends Background {
    JButton save;
    JTextField textField;
    User currentUser;

    /**
     * Constructor of the class to build the panel
     * @param user Username of the current user
     * @param pass Password of the current user
     */
    public ChooseClass(String user, String pass){
        super(".\\Images\\3033.jpg",650,700);
        currentUser = new User(user,pass);

        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"Choose Class");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));

        //Table
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

        JLabel label = new JLabel("Code:");
        label.setFont(new Font("Nani" , Font.BOLD , 15));

        textField = new JTextField();
        textField.setFont(new Font("Nani" , Font.BOLD ,15));

        save = new JButton("Save", new ImageIcon(".\\Images\\Check.png"));
        save.setFont(new Font("Nani" , Font.BOLD, 15));
        Handler handler = new Handler();
        save.addActionListener(handler);

        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(groupLayout.createSequentialGroup()
                        .addGap(15)
                        .addComponent(scrollPane,550,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(textField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,200))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(save,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(scrollPane,500,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label)
                        .addComponent(textField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(save,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
        );
    }

    /**
     * Handler class to handle action on the panel
     */
    private class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(save)){
                if (!Student.addClass(textField.getText(),currentUser.getUsername())){
                    JOptionPane.showMessageDialog(null,"Could not add the class",
                            "Failed",JOptionPane.ERROR_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null,"Class added.",
                            "Successful",JOptionPane.INFORMATION_MESSAGE);
                }
                textField.setText("");
            }
        }
    }
}
