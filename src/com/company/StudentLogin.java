package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * This class is a frame for student login.
 */
public class StudentLogin extends JFrame {
    static StudentLogin frame;
    private Background contentPane;
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton login;

    /**
     * Main method to open the frame after the previous one is closed.
     */
    public static void start() {
        frame = new StudentLogin();
        frame.setVisible(true);
    }

    /**
     * Constructor of the class to build the frame
     */
    public StudentLogin(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(500,150);
        setSize(500,500);
        setResizable(false);

        contentPane = new Background(".\\Images\\First.jpg",500,500);
        setContentPane(contentPane);

        JLabel studentLogin = new JLabel("Student Login");
        studentLogin.setForeground(new Color(210, 105,  30));
        studentLogin.setFont(new Font("Nani", Font.BOLD, 20));

        JLabel username = new JLabel("Username:");
        username.setFont(new Font("Nani" , Font.BOLD , 15));
        username.setForeground(new Color(210, 105,  30));
        JLabel password = new JLabel("Password:");
        password.setFont(new Font("Nani" , Font.BOLD , 15));
        password.setForeground(new Color(210, 105,  30));

        userField = new JTextField();
        userField.setFont(new Font("Nani", Font.ITALIC , 15));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Nani", Font.ITALIC , 15));

        login = new JButton("Login");
        login.setForeground(new Color(210, 105,  30));
        login.setBackground(new Color(253 ,245 ,230));
        login.setFont(new Font("Nani" , Font.BOLD , 15));
        login.setMnemonic(KeyEvent.VK_ENTER);
        Handler handler = new Handler();
        login.addActionListener(handler);

        GroupLayout groupLayout = new GroupLayout(contentPane);
        contentPane.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup().addGap(75)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(170)
                                                .addComponent(studentLogin))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(100)
                                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(username)
                                                        .addComponent(password))
                                                .addGap(20)
                                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(passwordField)
                                                        .addComponent(userField, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(190)
                                                .addComponent(login, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup().addGap(45)
                                .addComponent(studentLogin)
                                .addGap(20)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(username)
                                        .addComponent(userField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(20)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(password))
                                .addGap(20)
                                .addComponent(login, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(groupLayout);
    }

    /**
     * Handler of the class to handle action of the frame
     */
    private class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(login)){
                String user = userField.getText();
                String pass = String.valueOf(passwordField.getPassword());
                if (user.trim().equals("") || pass.trim().equals("")){
                    JOptionPane.showMessageDialog(null,"Username or password field is empty.",
                            "Empty field",JOptionPane.ERROR_MESSAGE);
                }else if (!User.validPassword(pass)){
                    JOptionPane.showMessageDialog(null,"Password must be at least 8 character.",
                            "Invalid password",JOptionPane.ERROR_MESSAGE);
                }else{
                    if (Student.login(user,pass)){
                        JOptionPane.showMessageDialog(null, "Login successful.",
                                "Successful",JOptionPane.INFORMATION_MESSAGE);
                        StudentPortal.start(user, pass);
                        frame.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null,"Login failed.",
                                "Invalid username",JOptionPane.ERROR_MESSAGE);
                    }
                }
                userField.setText("");
                passwordField.setText("");
            }
        }
    }
}
