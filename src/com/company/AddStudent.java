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
 * This class is a panel in which the admin adds student.
 */
public class AddStudent extends Background {
    JTextField userTxt;
    JPasswordField passTxt;
    JButton save;

    /**
     * Constructor of the class to build the panel
     */
    public AddStudent(){
        super(".\\Images\\3033.jpg",650,700);

        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"Add Student");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));
//        setBackground(new Color(255 ,228, 181));

        JLabel username = new JLabel("Username:");
        username.setFont(new Font("Nani" , Font.BOLD, 15));
        JLabel password = new JLabel("Password:");
        password.setFont(new Font("Nani" , Font.BOLD, 15));

        userTxt = new JTextField();
        userTxt.setFont(new Font("Nani" , Font.BOLD ,15));
        passTxt = new JPasswordField();
        passTxt.setFont(new Font("Nani" , Font.BOLD ,15));

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
                                .addGroup(groupLayout.createSequentialGroup().addGap(120)
                                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(username)
                                                .addComponent(password))
                                        .addGap(25)
                                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(userTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,250)
                                                .addComponent(passTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,250))))
                        .addComponent(save,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,100)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup().addGap(120)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(username)
                                .addComponent(userTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                        .addGap(50)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(password)
                                .addComponent(passTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                        .addGap(50)
                        .addComponent(save)
        );
    }

    /**
     * Handler class to handle the actions on the panel
     */
    private class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            if (e.getSource().equals(save)){

                String user = userTxt.getText();
                String pass = String.valueOf(passTxt.getPassword());

                if (user.trim().length()==0 || pass.trim().length()==0){
                    JOptionPane.showMessageDialog(null,"Username or password field is empty.",
                            "Empty field",JOptionPane.ERROR_MESSAGE);
                }else if (!User.validPassword(pass)){
                    JOptionPane.showMessageDialog(null,"Password must be at least 8 character.",
                            "Invalid password",JOptionPane.ERROR_MESSAGE);
                }else{
                    if (Admin.addStudent(user,pass)){
                        JOptionPane.showMessageDialog(null, "Student saved successfully.",
                                "Successful",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null,"This username exists.",
                                "Invalid username",JOptionPane.ERROR_MESSAGE);
                    }
                }
                userTxt.setText("");
                passTxt.setText("");
            }
        }
    }
}
