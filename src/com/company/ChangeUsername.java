package com.company;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is a panel for users to change username.
 */
public class ChangeUsername extends Background {

    JButton save;
    JTextField userTxt;
    JTextField newUserTxt;
    String userType;
    String currentUsername;

    /**
     * Constructor of the class to build the panel
     * @param userType Type of the user
     * @param currentUsername Username of the current user
     */
    public ChangeUsername(String userType, String currentUsername){
        super(".\\Images\\3033.jpg",650,700);
        this.userType = userType;
        this.currentUsername = currentUsername;

        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"Change Username");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));

        JLabel newUser = new JLabel("Old Username:");
        newUser.setFont(new Font("Nani" , Font.BOLD, 15));
        JLabel newPass = new JLabel("New Username:");
        newPass.setFont(new Font("Nani" , Font.BOLD, 15));

        userTxt = new JTextField();
        userTxt.setFont(new Font("Nani" , Font.BOLD ,15));
        newUserTxt = new JTextField();
        newUserTxt.setFont(new Font("Nani" , Font.BOLD ,15));

        save = new JButton("Save", new ImageIcon(".\\Images\\Check.png"));
        save.setFont(new Font("Nani" , Font.BOLD, 15));
        Handler handler = new Handler();
        save.addActionListener(handler);


        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createSequentialGroup().addGap(100)
                                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(newUser)
                                                .addComponent(newPass))
                                        .addGap(25)
                                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(userTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,250)
                                                .addComponent(newUserTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,250))))
                        .addComponent(save,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,100)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup().addGap(120)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(newUser)
                                .addComponent(userTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                        .addGap(50)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(newPass)
                                .addComponent(newUserTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                        .addGap(50)
                        .addComponent(save)
        );
    }

    /**
     * Handler class to handle the action on the panel
     */
    private class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean changed = true;
            if (userTxt.getText().length()==0 || newUserTxt.getText().length()==0){
                JOptionPane.showMessageDialog(null , "Empty parts" , "Failed" ,
                        JOptionPane.ERROR_MESSAGE);
            } else if (e.getSource().equals(save)){
                if (!currentUsername.equals(userTxt.getText())){
                    JOptionPane.showMessageDialog(null,"This is not your account.",
                            "Wrong username",JOptionPane.ERROR_MESSAGE);
                    userTxt.setText("");
                    newUserTxt.setText("");
                    return;
                }

                switch (userType){
                    case "Admin" -> changed = Admin.changeUsername(userTxt.getText(),newUserTxt.getText());
                    case "Student" -> changed = Student.changeUsername(userTxt.getText(),newUserTxt.getText());
                    case "Professor" -> changed = Professor.changeUsername(userTxt.getText(),newUserTxt.getText());
                }

                if (changed){
                    JOptionPane.showMessageDialog(null,"Username changed successfully.",
                            "Password changed",JOptionPane.INFORMATION_MESSAGE);
                    switch (userType){
                        case "Admin" -> {
                            AdminLogin.start();
                            AdminPortal.frame.dispose();
                        }
                        case "Student" -> {
                            StudentLogin.start();
                            StudentPortal.frame.dispose();
                        }
                        case "Professor" -> {
                            ProfessorLogin.start();
                            ProfessorPortal.frame.dispose();
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid input.",
                            "Failed",JOptionPane.ERROR_MESSAGE);
                }
                userTxt.setText("");
                newUserTxt.setText("");
            }
        }
    }
}
