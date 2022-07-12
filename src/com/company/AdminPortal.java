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
 * This class is a frame of the admin portal.
 */
public class AdminPortal extends JFrame {
    static AdminPortal frame;
    private JPanel contentPane;
    User currentUser;
    JButton addFood;
    JButton viewStd;
    JButton viewProf;
    JButton viewClass;
    JButton addStd;
    JButton addProf;
    JButton changeUsername;
    JButton changePassword;
    JButton logout;

    /**
     * Main method to open the frame after the previous one is closed.
     * @param user Username of the current user
     * @param pass Password of the current user
     */
    public static void start(String user, String pass){
        frame = new AdminPortal(user,pass);
        frame.setVisible(true);
    }

    /**
     * Constructor of the class to build the frame
     * @param user Username of the current user
     * @param pass Password of the current user
     */
    public AdminPortal(String user, String pass){
        currentUser = new Admin(user,pass);

        setSize(800,700);
        setLocation(300,50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Admin Portal");

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        Background empty = new Background(".\\Images\\3033.jpg",650,700);
        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"Admin Portal");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        empty.setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));
        contentPane.add(empty,BorderLayout.CENTER);

        Handler handler = new Handler();

        addFood = new JButton("Add Food");
        addFood.setForeground(Color.DARK_GRAY);
        addFood.setBackground(new Color(245, 245 ,245 ));
        addFood.setFont(new Font("Nani" , Font.BOLD, 15));
        addFood.addActionListener(handler);

        viewStd = new JButton("View Student");
        viewStd.setForeground(Color.DARK_GRAY);
        viewStd.setBackground(new Color(245, 245 ,245 ));
        viewStd.setFont(new Font("Nani" , Font.BOLD, 15));
        viewStd.addActionListener(handler);

        viewProf = new JButton("View Professor");
        viewProf.setForeground(Color.DARK_GRAY);
        viewProf.setBackground(new Color(245, 245 ,245 ));
        viewProf.setFont(new Font("Nani" , Font.BOLD, 15));
        viewProf.addActionListener(handler);

        viewClass = new JButton("View Class");
        viewClass.setForeground(Color.DARK_GRAY);
        viewClass.setBackground(new Color(245, 245 ,245 ));
        viewClass.setFont(new Font("Nani" , Font.BOLD, 15));
        viewClass.addActionListener(handler);

        addStd = new JButton("Add Student");
        addStd.setForeground(Color.DARK_GRAY);
        addStd.setBackground(new Color(245, 245 ,245 ));
        addStd.setFont(new Font("Nani" , Font.BOLD, 15));
        addStd.addActionListener(handler);

        addProf = new JButton("Add Professor");
        addProf.setForeground(Color.DARK_GRAY);
        addProf.setBackground(new Color(245, 245 ,245 ));
        addProf.setFont(new Font("Nani" , Font.BOLD, 15));
        addProf.addActionListener(handler);

        changeUsername = new JButton("Change Username");
        changeUsername.setForeground(Color.DARK_GRAY);
        changeUsername.setBackground(new Color(245, 245 ,245 ));
        changeUsername.setFont(new Font("Nani" , Font.BOLD, 15));
        changeUsername.addActionListener(handler);

        changePassword = new JButton("Change Password");
        changePassword.setForeground(Color.DARK_GRAY);
        changePassword.setBackground(new Color(245, 245 ,245 ));
        changePassword.setFont(new Font("Nani" , Font.BOLD, 15));
        changePassword.addActionListener(handler);

        logout = new JButton("Logout", new ImageIcon(".\\Images\\exit.png"));
        logout.setForeground(Color.DARK_GRAY);
        logout.setBackground(new Color(245, 245 ,245 ));
        logout.setFont(new Font("Nani" , Font.BOLD, 15));
        logout.addActionListener(handler);

        JPanel buttonList = new JPanel();
        buttonList.setBackground(Color.lightGray);
        buttonList.setLayout(new GridLayout(9,1));
        buttonList.add(addFood);
        buttonList.add(addStd);
        buttonList.add(addProf);
        buttonList.add(viewStd);
        buttonList.add(viewProf);
        buttonList.add(viewClass);
        buttonList.add(changePassword);
        buttonList.add(changeUsername);
        buttonList.add(logout);
        contentPane.add(buttonList,BorderLayout.WEST);

    }

    /**
     * Handler of the class to handle action of the frame
     */
    public class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(logout)){
                JOptionPane.showMessageDialog(null,"The portal will be closed." , "Logout", JOptionPane.INFORMATION_MESSAGE);
                Entrance.start();
                frame.dispose();
            }else {

                BorderLayout layout = (BorderLayout)contentPane.getLayout();
                contentPane.remove(layout.getLayoutComponent(BorderLayout.CENTER));

                if (e.getSource().equals(addFood)){
                    AddFood food = new AddFood();
                    contentPane.add(food,BorderLayout.CENTER);

                }else if (e.getSource().equals(viewStd)){
                    ViewStudent viewStudent = new ViewStudent(currentUser.getUsername(), currentUser.getPassword());
                    contentPane.add(viewStudent,BorderLayout.CENTER);

                }else if(e.getSource().equals(viewProf)){
                    ViewProfessor viewProfessor = new ViewProfessor();
                    contentPane.add(viewProfessor,BorderLayout.CENTER);

                }else if (e.getSource().equals(viewClass)){
                    ViewClass viewClass = new ViewClass();
                    contentPane.add(viewClass,BorderLayout.CENTER);

                }else if (e.getSource().equals(addStd)){
                    AddStudent addStudent = new AddStudent();
                    contentPane.add(addStudent,BorderLayout.CENTER);

                }else if (e.getSource().equals(addProf)){
                    AddProfessor addProfessor = new AddProfessor();
                    contentPane.add(addProfessor,BorderLayout.CENTER);

                }else if (e.getSource().equals(changeUsername)){
                    ChangeUsername changeUsername1 = new ChangeUsername("Admin",currentUser.getUsername());
                    contentPane.add(changeUsername1,BorderLayout.CENTER);

                }else if (e.getSource().equals(changePassword)){
                    ChangePassword changePassword1 = new ChangePassword("Admin", currentUser.getUsername());
                    contentPane.add(changePassword1,BorderLayout.CENTER);
                }

                validate();
            }
        }
    }

}
