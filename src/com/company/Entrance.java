package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The first frame to show when the program is run.
 * Here the user chooses whether to enter the student portal or admin or professor.
 */
public class Entrance extends JFrame {
    static Entrance frame;
    private Background contentPane;
    JButton adminLogin;
    JButton professorLogin;
    JButton studentLogin;
    JButton exit;

    /**
     * Main method to open the frame after the previous one is closed.
     */
    public static void start(){
        frame = new Entrance();
        frame.setVisible(true);
    }

    /**
     * Constructor of the class to build the frame
     */
    public Entrance(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login to portal");
        setLocation(500,150);
        setSize(500,500);
        setResizable(false);

        contentPane = new Background(".\\Images\\First.jpg",500,500);
        setContentPane(contentPane);

        JLabel label = new JLabel("           Login");
        label.setForeground(new Color(210, 105,  30));
        label.setFont(new Font("Nani", Font.BOLD, 20));

        Handler handler = new Handler();

        adminLogin = new JButton("Admin Login");
        adminLogin.setForeground(new Color(210, 105,  30));
        adminLogin.setBackground(new Color(253 ,245 ,230));
        adminLogin.setFont(new Font("Nani" , Font.BOLD , 15));
        adminLogin.addActionListener(handler);

        professorLogin = new JButton("    Professor Login     ");
        professorLogin.setForeground(new Color(210, 105,  30));
        professorLogin.setBackground(new Color(253 ,245 ,230));
        professorLogin.setFont(new Font("Nani" , Font.BOLD , 15));
        professorLogin.addActionListener(handler);

        studentLogin = new JButton("Student Login");
        studentLogin.setForeground(new Color(210, 105,  30));
        studentLogin.setBackground(new Color(253 ,245 ,230));
        studentLogin.setFont(new Font("Nani" , Font.BOLD , 15));
        studentLogin.addActionListener(handler);

        exit = new JButton("Exit");
        exit.setForeground(new Color(210, 105,  30));
        exit.setBackground(new Color(253 ,245 ,230));
        exit.setFont(new Font("Nani" , Font.BOLD , 15));
        exit.addActionListener(handler);

        GroupLayout groupLayout = new GroupLayout(contentPane);
        contentPane.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup().addGap(250)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(label)
                            .addComponent(adminLogin,GroupLayout.Alignment.TRAILING,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                            .addComponent(professorLogin,GroupLayout.Alignment.TRAILING,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                            .addComponent(studentLogin,GroupLayout.Alignment.TRAILING,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                            .addComponent(exit,GroupLayout.Alignment.TRAILING,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE))
                .addContainerGap(210,Short.MAX_VALUE)
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup().addGap(20)
                        .addComponent(label).addGap(15)
                             .addComponent(adminLogin,20,30,35).addGap(15)
                             .addComponent(professorLogin,20,30,35).addGap(15)
                             .addComponent(studentLogin,20,30,35).addGap(15)
                             .addComponent(exit,20,30,35)
        );
    }

    private class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(adminLogin)){
                AdminLogin.start();
                frame.dispose();
            }else if (e.getSource().equals(professorLogin)){
                ProfessorLogin.start();
                frame.dispose();
            }else if (e.getSource().equals(studentLogin)){
                StudentLogin.start();
                frame.dispose();
            }else if (e.getSource().equals(exit)){
                System.exit(0);
            }
        }
    }
}
