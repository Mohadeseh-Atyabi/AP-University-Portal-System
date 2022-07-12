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
 * This class is a panel for professor to delete a class.
 */
public class DeleteClass extends Background {
    User currentUser;
    JTextField codeTxt;
    JButton save;

    /**
     * Constructor of the class to build the panel
     * @param user Username of the current user
     * @param pass Password of the current user
     */
    public DeleteClass(String user, String pass){
        super(".\\Images\\3033.jpg",650,700);
        currentUser = new User(user,pass);

        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"Delete Class");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));

        //Table
        String[] column = {"Code" , "Name", "Day" , "Time" , "Unit"};
        JTable table = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.setRowHeight(getHeight()+25);
        table.setFont(new Font("Nani" , Font.ITALIC,14));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(column);
        ArrayList<String[]> data1 = Professor.listOfClasses(currentUser.getUsername());
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

        //Code
        JLabel codeLabel = new JLabel("Code:");
        codeLabel.setFont(new Font("Nani" , Font.BOLD , 15));

        codeTxt = new JTextField();
        codeTxt.setFont(new Font("Nani" , Font.BOLD ,15));

        //Save button
        save = new JButton("Save", new ImageIcon(".\\Images\\Check.png"));
        save.setFont(new Font("Nani" , Font.BOLD, 15));
        Handler handler = new Handler();
        save.addActionListener(handler);

        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER).addGap(550)
                                .addGroup(groupLayout.createSequentialGroup()
                                    .addComponent(scrollPane,550,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(codeLabel)
                                        .addComponent(codeTxt,300,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(save,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)))
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(scrollPane,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGap(30)
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(codeLabel)
                        .addComponent(codeTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(save,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
        );
    }

    /**
     * Handler class to handle the action on the panel
     */
    private class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(save)){
                if (!Professor.deleteClass(currentUser.getUsername(),codeTxt.getText())){
                    JOptionPane.showMessageDialog(null,"Deleting class failed.",
                            "Failed", JOptionPane.ERROR_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null,"Class deleted successfully",
                            "Successful",JOptionPane.INFORMATION_MESSAGE);
                }
                codeTxt.setText("");
            }
        }
    }
}
