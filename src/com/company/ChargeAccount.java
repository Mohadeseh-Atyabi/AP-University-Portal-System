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
 * This class is a panel for student to charge account.
 */
public class ChargeAccount extends Background {
    User currentUser;
    JTextField textField;
    JButton save;
    JTextField cardTextField;
    JPasswordField passwordField;

    /**
     * Constructor of the class to build the panel
     * @param user Username of the current user
     * @param pass Password of the current user
     */
    public ChargeAccount(String user, String pass){
        super(".\\Images\\3033.jpg",650,700);
        currentUser = new User(user,pass);

        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"Charge Account");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));

        JLabel label1 = new JLabel();
        label1.setBackground(new Color(255 ,228, 181));
        label1.setFont(new Font("Nani" , Font.BOLD , 15));
        label1.setText("You have "+ Student.getBalanceOfStudent(currentUser.getUsername()) +
                " rial in your account now. Write how much to charge!");

        //Amount of charge
        JLabel label = new JLabel("Amount:");
        label.setFont(new Font("Nani", Font.BOLD , 15));
        textField = new JTextField();
        textField.setFont(new Font("Nani" , Font.BOLD , 15));

        //Card number
        JLabel cardLabel = new JLabel("Card number:");
        cardLabel.setFont(new Font("Nani", Font.BOLD , 15));
        cardTextField = new JTextField();
        cardTextField.setFont(new Font("Nani" , Font.BOLD ,15));

        //Card password
        JLabel passLabel = new JLabel("Card password:");
        passLabel.setFont(new Font("Nani", Font.BOLD , 15));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Nani" , Font.BOLD ,15));

        save = new JButton("Save", new ImageIcon(".\\Images\\Check.png"));
        save.setFont(new Font("Nani" , Font.BOLD, 15));
        Handler handler = new Handler();
        save.addActionListener(handler);

        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(label1,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(label,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addGap(65)
                        .addComponent(textField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,420))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(cardLabel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addGap(25)
                        .addComponent(cardTextField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,420))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(passLabel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addGap(11)
                        .addComponent(passwordField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,420))
                    .addGroup(groupLayout.createSequentialGroup()
                         .addGap(410)
                        .addComponent(save,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup().addGap(50)
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(label1,50,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cardLabel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addComponent(cardTextField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passLabel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(save,20,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
        );
    }

    /**
     * Handler class to handle the action on the panel
     */
    private class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(save)){
                if (!Student.chargeAccount(textField.getText(),cardTextField.getText(),String.valueOf(passwordField.getPassword()),
                        currentUser.getUsername())){
                    JOptionPane.showMessageDialog(null,"Invalid input.",
                            "Charge failed",JOptionPane.ERROR_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null,"Account charged.",
                            "Charged successfully", JOptionPane.INFORMATION_MESSAGE);
                }
                textField.setText("");
                cardTextField.setText("");
                passwordField.setText("");
            }
        }
    }
}
