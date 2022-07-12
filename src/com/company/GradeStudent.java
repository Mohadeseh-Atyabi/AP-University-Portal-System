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
 * This is a panel for professor to grade the students.
 */
public class GradeStudent extends Background {
    User currentUser;
    JTextField classTxt;
    JTextField studentTx;
    JTextField gradeTxt;
    JButton save;
    JTable table;

    /**
     * Constructor of the class to build the frame
     * @param user Username of the current user
     * @param pass Password of the current user
     */
    public GradeStudent(String user, String pass){
        super(".\\Images\\3033.jpg",650,700);
        currentUser = new User(user,pass);

        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"View Student & Grade");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));

        //Table of the classes
        String[] column = {"Code" , "Name", "Day" , "Time" , "Unit" , "Student" , "Grade"};
        table = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.setRowHeight(getHeight()+25);
        table.setFont(new Font("Nani" , Font.ITALIC,14));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(column);
        ArrayList<String[]> data1 = Professor.listOfClassesWithStd(currentUser.getUsername());
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

        //Class
        JLabel classLabel = new JLabel("Class Code:");
        classLabel.setFont(new Font("Nani" , Font.BOLD , 15));
        classTxt = new JTextField();
        classTxt.setFont(new Font("Nani" , Font.BOLD ,15));

        //Student
        JLabel studentLabel = new JLabel("Student number");
        studentLabel.setFont(new Font("Nani" , Font.BOLD , 15));
        studentTx = new JTextField();
        studentTx.setFont(new Font("Nani" , Font.BOLD ,15));

        //Grade
        JLabel gradeLabel = new JLabel("Grade:");
        gradeLabel.setFont(new Font("Nani" , Font.BOLD , 15));
        gradeTxt = new JTextField();
        gradeTxt.setFont(new Font("Nani" , Font.BOLD ,15));

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
                    .addGap(10)
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(scrollPane,550,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(80)
                            .addComponent(classLabel)
                            .addGap(33)
                            .addComponent(classTxt,300,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(80)
                            .addComponent(studentLabel)
                            .addComponent(studentTx,300,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(80)
                            .addComponent(gradeLabel)
                            .addGap(74)
                            .addComponent(gradeTxt,300,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(250)
                            .addComponent(save,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)))
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(scrollPane,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(classLabel)
                        .addComponent(classTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(studentLabel)
                        .addComponent(studentTx,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(gradeLabel)
                        .addComponent(gradeTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(save,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
        );

    }

    /**
     * Handler of the class to handle action of the frame
     */
    private class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(save)){
                if (classTxt.getText().length()==0 || studentTx.getText().length()==0 || gradeTxt.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "Empty parts." ,
                            "Failed", JOptionPane.ERROR_MESSAGE);
                } else if (classTxt.getText().length()!=7 || Double.parseDouble(gradeTxt.getText())<0){
                    JOptionPane.showMessageDialog(null,"Invalid code or grade",
                            "Failed",JOptionPane.ERROR_MESSAGE);
                }else if (!Professor.gradeStudent(currentUser.getUsername(),classTxt.getText(),studentTx.getText(),Double.parseDouble(gradeTxt.getText()))){
                    JOptionPane.showMessageDialog(null,"Either code of class or student number is wrong or you graded previously.",
                            "Failed", JOptionPane.ERROR_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null,"Grade is saved successfully.",
                            "Successful",JOptionPane.INFORMATION_MESSAGE);
                }
                classTxt.setText("");
                studentTx.setText("");
                gradeTxt.setText("");
            }
        }
    }

}
