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
 * This class is a panel for users to change password.
 */
public class ChangePassword extends Background {
    JButton save;
    JTextField userTxt;
    JPasswordField newPassTxt;
    JPasswordField repeatTxt;
    String userType;
    String currentUsername;

    /**
     * Constructor of the class to build the panel
     * @param userType Type of the current user
     * @param currentUsername Username of the current user
     */
    public ChangePassword(String userType, String currentUsername){
        super(".\\Images\\3033.jpg",650,700);
        this.userType = userType;
        this.currentUsername = currentUsername;

        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"Change Password");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));
//        setBackground(new Color(255 ,228, 181));

        JLabel newUser = new JLabel("Username:");
        newUser.setFont(new Font("Nani" , Font.BOLD, 15));
        JLabel newPass = new JLabel("New Password:");
        newPass.setFont(new Font("Nani" , Font.BOLD, 15));
        JLabel repeat = new JLabel("Repeat Password:");
        repeat.setFont(new Font("Nani" , Font.BOLD, 15));

        userTxt = new JTextField();
        userTxt.setFont(new Font("Nani" , Font.BOLD ,15));
        newPassTxt = new JPasswordField();
        newPassTxt.setFont(new Font("Nani" , Font.BOLD ,15));
        repeatTxt = new JPasswordField();
        repeatTxt.setFont(new Font("Nani" , Font.BOLD ,15));

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
                                                .addComponent(newPass)
                                                .addComponent(repeat))
                                        .addGap(25)
                                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(userTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,250)
                                                .addComponent(newPassTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,250)
                                                .addComponent(repeatTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,250))))
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
                                .addComponent(newPassTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                        .addGap(50)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(repeat)
                                .addComponent(repeatTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
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
            if (userTxt.getText().length()==0 || newPassTxt.getPassword().length==0 || repeatTxt.getPassword().length==0){
                JOptionPane.showMessageDialog(null, "Empty parts." ,"Failed" ,
                        JOptionPane.ERROR_MESSAGE);
                userTxt.setText("");
                newPassTxt.setText("");
                repeatTxt.setText("");
                return;
            }else if (!currentUsername.equals(userTxt.getText())){
                JOptionPane.showMessageDialog(null,"This is not your account.",
                        "Wrong account",JOptionPane.ERROR_MESSAGE);
                userTxt.setText("");
                newPassTxt.setText("");
                repeatTxt.setText("");
                return;
            }

            boolean changed = true;
            if (e.getSource().equals(save)){
                switch (userType){
                    case "Admin" -> changed = Admin.changePassword(userTxt.getText(),String.valueOf(newPassTxt.getPassword()),String.valueOf(repeatTxt.getPassword()));
                    case "Student" -> changed = Student.changePassword(userTxt.getText(),String.valueOf(newPassTxt.getPassword()),String.valueOf(repeatTxt.getPassword()));
                    case "Professor" -> changed = Professor.changePassword(userTxt.getText(),String.valueOf(newPassTxt.getPassword()),String.valueOf(repeatTxt.getPassword()));
                }

                if (changed){
                    JOptionPane.showMessageDialog(null,"Password changed successfully.",
                            "Password changed",JOptionPane.INFORMATION_MESSAGE);
                }else if (!User.validPassword(String.valueOf(newPassTxt.getPassword()))){
                    JOptionPane.showMessageDialog(null,"Password must be at least 8 character.",
                            "Invalid password",JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"Passwords are not the same or user doesn't exist.",
                            "Failed",JOptionPane.ERROR_MESSAGE);
                }
                userTxt.setText("");
                newPassTxt.setText("");
                repeatTxt.setText("");
            }
        }
    }
}
