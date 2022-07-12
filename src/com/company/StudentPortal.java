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
 * This class is the frame of the student portal.
 */
public class StudentPortal extends JFrame {
    static StudentPortal frame;
    private JPanel contentPane;
    User currentUser;
    JButton chooseFood;
    JButton chargeAccount;
    JButton chooseClass;
    JButton changePassword;
    JButton changeUsername;
    JButton accountInfo;
    JButton logout;

    /**
     * Main method to open the frame after the previous one is closed.
     * @param user Username of the current user
     * @param pass Password of the current user
     */
    public static void start(String user, String pass){
        frame = new StudentPortal(user,pass);
        frame.setVisible(true);
    }

    /**
     * Constructor of the class to build the frame
     * @param user Username of the current user
     * @param pass Password of the current user
     */
    public StudentPortal(String user, String pass){
        currentUser = new User(user,pass);

        setSize(800,700);
        setLocation(300,50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Student Portal");

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        Background empty = new Background(".\\Images\\3033.jpg",650,700);
        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"Student Portal");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        empty.setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));
        contentPane.add(empty,BorderLayout.CENTER);

        Handler handler = new Handler();

        chooseFood = new JButton("Choose Food");
        chooseFood.setForeground(Color.DARK_GRAY);
        chooseFood.setBackground(new Color(245, 245 ,245 ));
        chooseFood.setFont(new Font("Nani" , Font.BOLD, 15));
        chooseFood.addActionListener(handler);

        chargeAccount = new JButton("Charge Account");
        chargeAccount.setForeground(Color.DARK_GRAY);
        chargeAccount.setBackground(new Color(245, 245 ,245 ));
        chargeAccount.setFont(new Font("Nani" , Font.BOLD, 15));
        chargeAccount.addActionListener(handler);

        chooseClass = new JButton("Choose Class");
        chooseClass.setForeground(Color.DARK_GRAY);
        chooseClass.setBackground(new Color(245, 245 ,245 ));
        chooseClass.setFont(new Font("Nani" , Font.BOLD, 15));
        chooseClass.addActionListener(handler);

        changePassword = new JButton("Change Password");
        changePassword.setForeground(Color.DARK_GRAY);
        changePassword.setBackground(new Color(245, 245 ,245 ));
        changePassword.setFont(new Font("Nani" , Font.BOLD, 15));
        changePassword.addActionListener(handler);

        changeUsername = new JButton("Change Username");
        changeUsername.setForeground(Color.DARK_GRAY);
        changeUsername.setBackground(new Color(245, 245 ,245 ));
        changeUsername.setFont(new Font("Nani" , Font.BOLD, 15));
        changeUsername.addActionListener(handler);


        accountInfo = new JButton("Account Information");
        accountInfo.setForeground(Color.DARK_GRAY);
        accountInfo.setBackground(new Color(245, 245 ,245 ));
        accountInfo.setFont(new Font("Nani" , Font.BOLD, 15));
        accountInfo.addActionListener(handler);

        logout = new JButton("Logout",new ImageIcon(".\\Images\\exit.png"));
        logout.setForeground(Color.DARK_GRAY);
        logout.setBackground(new Color(245, 245 ,245 ));
        logout.setFont(new Font("Nani" , Font.BOLD, 15));
        logout.addActionListener(handler);

        JPanel buttonList = new JPanel();
        buttonList.setBackground(Color.lightGray);
        buttonList.setLayout(new GridLayout(7,1));
        buttonList.add(chooseClass);
        buttonList.add(chooseFood);
        buttonList.add(chargeAccount);
        buttonList.add(accountInfo);
        buttonList.add(changeUsername);
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

                if (e.getSource().equals(chooseFood)){

                    ChooseFood chooseFood1 = new ChooseFood(currentUser.getUsername(),currentUser.getPassword());
                    contentPane.add(chooseFood1,BorderLayout.CENTER);

                }else if (e.getSource().equals(chooseClass)){
                    ChooseClass chooseClass1 = new ChooseClass(currentUser.getUsername(),currentUser.getPassword());
                    contentPane.add(chooseClass1,BorderLayout.CENTER);

                }else if (e.getSource().equals(chargeAccount)){
                    ChargeAccount chargeAccount1 = new ChargeAccount(currentUser.getUsername(),currentUser.getPassword());
                    contentPane.add(chargeAccount1,BorderLayout.CENTER);

                }else if (e.getSource().equals(accountInfo)){
                    StudentAccountInfo list = new StudentAccountInfo(currentUser.getUsername(),currentUser.getPassword());
                    contentPane.add(list,BorderLayout.CENTER);

                }else if (e.getSource().equals(changeUsername)){
                    ChangeUsername changeUsername1 = new ChangeUsername("Student", currentUser.getUsername());
                    contentPane.add(changeUsername1,BorderLayout.CENTER);

                }else if (e.getSource().equals(changePassword)){
                    ChangePassword changePassword1 = new ChangePassword("Student", currentUser.getUsername());
                    contentPane.add(changePassword1,BorderLayout.CENTER);
                }

                validate();
            }
        }
    }
}
