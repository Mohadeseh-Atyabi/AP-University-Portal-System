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
 * This class is a frame of portal of the professor.
 */
public class ProfessorPortal extends JFrame {
    static ProfessorPortal frame;
    private JPanel contentPane;
    User currentUser;
    JButton addClass;
    JButton deleteClass;
    JButton grade;
    JButton changeUser;
    JButton changePassword;
    JButton logout;

    /**
     * Main method to open the frame after the previous one is closed.
     * @param user Username of the current user
     * @param pass Password of the current user
     */
    public static void start(String user, String pass){
        frame = new ProfessorPortal(user, pass);
        frame.setVisible(true);
    }

    /**
     * Constructor of the class to build the frame
     * @param user Username of the current user
     * @param pass Password of the current user
     */
    public ProfessorPortal(String user, String pass){
        currentUser = new User(user,pass);

        setSize(800,700);
        setLocation(300,50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Professor Portal");

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        Handler handler = new Handler();

        Background empty = new Background(".\\Images\\3033.jpg",650,700);
        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"Student Portal");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        empty.setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));
        contentPane.add(empty,BorderLayout.CENTER);

        addClass = new JButton("Add Class");
        addClass.setForeground(Color.DARK_GRAY);
        addClass.setBackground(new Color(245, 245 ,245 ));
        addClass.setFont(new Font("Nani" , Font.BOLD, 15));
        addClass.addActionListener(handler);

        deleteClass = new JButton("Delete Class");
        deleteClass.setForeground(Color.DARK_GRAY);
        deleteClass.setBackground(new Color(245, 245 ,245 ));
        deleteClass.setFont(new Font("Nani" , Font.BOLD, 15));
        deleteClass.addActionListener(handler);

        grade = new JButton("View Student & Grade");
        grade.setForeground(Color.DARK_GRAY);
        grade.setBackground(new Color(245, 245 ,245 ));
        grade.setFont(new Font("Nani" , Font.BOLD, 15));
        grade.addActionListener(handler);

        changeUser = new JButton("Change Username");
        changeUser.setForeground(Color.DARK_GRAY);
        changeUser.setBackground(new Color(245, 245 ,245 ));
        changeUser.setFont(new Font("Nani" , Font.BOLD, 15));
        changeUser.addActionListener(handler);

        changePassword = new JButton("Change Password");
        changePassword.setForeground(Color.DARK_GRAY);
        changePassword.setBackground(new Color(245, 245 ,245 ));
        changePassword.setFont(new Font("Nani" , Font.BOLD, 15));
        changePassword.addActionListener(handler);

        logout = new JButton("Logout",new ImageIcon(".\\Images\\exit.png"));
        logout.setForeground(Color.DARK_GRAY);
        logout.setBackground(new Color(245, 245 ,245 ));
        logout.setFont(new Font("Nani" , Font.BOLD, 15));
        logout.setFont(new Font("Nani" , Font.BOLD, 15));
        logout.addActionListener(handler);

        JPanel buttonList = new JPanel();
        buttonList.setBackground(Color.lightGray);
        buttonList.setLayout(new GridLayout(6,1));
        buttonList.add(addClass);
        buttonList.add(deleteClass);
        buttonList.add(grade);
        buttonList.add(changeUser);
        buttonList.add(changePassword);
        buttonList.add(logout);
        contentPane.add(buttonList,BorderLayout.WEST);
    }

    /**
     * Handler of the class to handle action of the frame
     */
    private class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(logout)){
                JOptionPane.showMessageDialog(null,"The portal will be closed." , "Logout", JOptionPane.INFORMATION_MESSAGE);
                Entrance.start();
                frame.dispose();
            }else {

                BorderLayout layout = (BorderLayout)contentPane.getLayout();
                contentPane.remove(layout.getLayoutComponent(BorderLayout.CENTER));

                if (e.getSource().equals(addClass)){
                    AddClass addClass1 = new AddClass(currentUser.getUsername(),currentUser.getPassword());
                    contentPane.add(addClass1,BorderLayout.CENTER);

                }else if (e.getSource().equals(deleteClass)){
                    DeleteClass deleteClass1 = new DeleteClass(currentUser.getUsername(),currentUser.getPassword());
                    contentPane.add(deleteClass1,BorderLayout.CENTER);

                }else if (e.getSource().equals(grade)){
                    GradeStudent gradeStudent = new GradeStudent(currentUser.getUsername(),currentUser.getPassword());
                    contentPane.add(gradeStudent,BorderLayout.CENTER);

                }else if (e.getSource().equals(changeUser)){
                    ChangeUsername changeUsername = new ChangeUsername("Professor",currentUser.getUsername());
                    contentPane.add(changeUsername,BorderLayout.CENTER);

                }else if (e.getSource().equals(changePassword)){
                    ChangePassword changePassword1 = new ChangePassword("Professor",currentUser.getUsername());
                    contentPane.add(changePassword1,BorderLayout.CENTER);
                }

                validate();
            }
        }
    }

}
