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
 * This class is a panel in which the admin adds food
 */
public class AddFood extends JPanel {
    JButton save;
    JTextField satTxt1;
    JTextField satPrice1;
    JTextField satTxt2;
    JTextField satPrice2;
    JTextField sunTxt1;
    JTextField sunPrice1;
    JTextField sunTxt2;
    JTextField sunPrice2;
    JTextField monTxt1;
    JTextField monPrice1;
    JTextField monTxt2;
    JTextField monPrice2;
    JTextField tueTxt1;
    JTextField tuePrice1;
    JTextField tueTxt2;
    JTextField tuePrice2;
    JTextField wedTxt1;
    JTextField wedPrice1;
    JTextField wedTxt2;
    JTextField wedPrice2;

    /**
     * Constructor of the class to build the panel
     */
    public AddFood(){

        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"Add Food");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));
//        setBackground(new Color(255 ,228, 181));

        JLabel saturday = new JLabel("Saturday:");
        saturday.setFont(new Font("Nani" , Font.BOLD, 15));
        JLabel sunday = new JLabel("Sunday:");
        sunday.setFont(new Font("Nani" , Font.BOLD, 15));
        JLabel monday = new JLabel("Monday");
        monday.setFont(new Font("Nani" , Font.BOLD, 15));
        JLabel tuesday = new JLabel("Tuesday:");
        tuesday.setFont(new Font("Nani" , Font.BOLD, 15));
        JLabel wednesday = new JLabel("Wednesday:");
        wednesday.setFont(new Font("Nani" , Font.BOLD, 15));

        //Saturday
        satTxt1 = new JTextField();
        satPrice1 = new JTextField();
        satTxt2 = new JTextField();
        satPrice2 = new JTextField();

        JLabel food = new JLabel("Food");
        food.setFont(new Font("Nani" , Font.BOLD , 15));
        JLabel price = new JLabel("Price");
        price.setFont(new Font("Nani" , Font.BOLD , 15));

        JPanel satPanel = new JPanel();
        GroupLayout gp = new GroupLayout(satPanel);
        satPanel.setLayout(gp);
        gp.setAutoCreateGaps(true);
        gp.setAutoCreateContainerGaps(true);

        gp.setHorizontalGroup(
                gp.createSequentialGroup()
                    .addGroup(gp.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(satTxt1)
                        .addComponent(satTxt2))
                    .addGroup(gp.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(satPrice1)
                        .addComponent(satPrice2))
        );
        gp.setVerticalGroup(
                gp.createSequentialGroup()
                    .addGroup(gp.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(satTxt1)
                        .addComponent(satPrice1))
                    .addGroup(gp.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(satTxt2)
                        .addComponent(satPrice2))
        );

        //Sunday
        sunTxt1 = new JTextField();
        sunPrice1 = new JTextField();
        sunTxt2 = new JTextField();
        sunPrice2 = new JTextField();

        JPanel sunPanel = new JPanel();
        GroupLayout gp1 = new GroupLayout(sunPanel);
        sunPanel.setLayout(gp1);
        gp1.setAutoCreateGaps(true);
        gp1.setAutoCreateContainerGaps(true);

        gp1.setHorizontalGroup(
                gp1.createSequentialGroup()
                        .addGroup(gp1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(sunTxt1)
                                .addComponent(sunTxt2))
                        .addGroup(gp1.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(sunPrice1)
                                .addComponent(sunPrice2))
        );
        gp1.setVerticalGroup(
                gp1.createSequentialGroup()
                        .addGroup(gp1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(sunTxt1)
                                .addComponent(sunPrice1))
                        .addGroup(gp1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(sunTxt2)
                                .addComponent(sunPrice2))
        );

        //Monday
        monTxt1 = new JTextField();
        monPrice1 = new JTextField();
        monTxt2 = new JTextField();
        monPrice2 = new JTextField();

        JPanel monPanel = new JPanel();
        GroupLayout gp2 = new GroupLayout(monPanel);
        monPanel.setLayout(gp2);
        gp2.setAutoCreateGaps(true);
        gp2.setAutoCreateContainerGaps(true);

        gp2.setHorizontalGroup(
                gp2.createSequentialGroup()
                        .addGroup(gp2.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(monTxt1)
                                .addComponent(monTxt2))
                        .addGroup(gp2.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(monPrice1)
                                .addComponent(monPrice2))
        );
        gp2.setVerticalGroup(
                gp2.createSequentialGroup()
                        .addGroup(gp2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(monTxt1)
                                .addComponent(monPrice1))
                        .addGroup(gp2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(monTxt2)
                                .addComponent(monPrice2))
        );

        //Tuesday
        tueTxt1 = new JTextField();
        tuePrice1 = new JTextField();
        tueTxt2 = new JTextField();
        tuePrice2 = new JTextField();

        JPanel tuePanel = new JPanel();
        GroupLayout gp3 = new GroupLayout(tuePanel);
        tuePanel.setLayout(gp3);
        gp3.setAutoCreateGaps(true);
        gp3.setAutoCreateContainerGaps(true);

        gp3.setHorizontalGroup(
                gp3.createSequentialGroup()
                        .addGroup(gp3.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(tueTxt1)
                                .addComponent(tueTxt2))
                        .addGroup(gp3.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(tuePrice1)
                                .addComponent(tuePrice2))
        );
        gp3.setVerticalGroup(
                gp3.createSequentialGroup()
                        .addGroup(gp3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(tueTxt1)
                                .addComponent(tuePrice1))
                        .addGroup(gp3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(tueTxt2)
                                .addComponent(tuePrice2))
        );

        //Wednesday
        wedTxt1 = new JTextField();
        wedPrice1 = new JTextField();
        wedTxt2 = new JTextField();
        wedPrice2 = new JTextField();

        JPanel wedPanel = new JPanel();
        GroupLayout gp4 = new GroupLayout(wedPanel);
        wedPanel.setLayout(gp4);
        gp4.setAutoCreateGaps(true);
        gp4.setAutoCreateContainerGaps(true);

        gp4.setHorizontalGroup(
                gp4.createSequentialGroup()
                        .addGroup(gp4.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(wedTxt1)
                                .addComponent(wedTxt2))
                        .addGroup(gp4.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(wedPrice1)
                                .addComponent(wedPrice2))
        );
        gp4.setVerticalGroup(
                gp4.createSequentialGroup()
                        .addGroup(gp4.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(wedTxt1)
                                .addComponent(wedPrice1))
                        .addGroup(gp4.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(wedTxt2)
                                .addComponent(wedPrice2))
        );

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
                        .addGap(200)
                        .addComponent(food)
                        .addGap(150)
                        .addComponent(price))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createSequentialGroup().addGap(30)
                            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(saturday)
                                .addComponent(sunday)
                                .addComponent(monday)
                                .addComponent(tuesday)
                                .addComponent(wednesday))
                            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(satPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,400)
                                .addComponent(sunPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,400)
                                .addComponent(monPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,400)
                                .addComponent(tuePanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,400)
                                .addComponent(wedPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,400))))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(420)
                                .addComponent(save,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,100))
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup().addGap(30)
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(food)
                        .addComponent(price))
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(saturday)
                        .addComponent(satPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGap(20)
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(sunday)
                        .addComponent(sunPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGap(20)
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(monday)
                        .addComponent(monPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGap(20)
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(tuesday)
                        .addComponent(tuePanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGap(20)
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(wednesday)
                        .addComponent(wedPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addComponent(save)
        );
    }

    /**
     * Handler class to handle the actions on the panel
     */
    private class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(save)){
                if (validate(satTxt1.getText(),satPrice1.getText()) && validate(satTxt2.getText(),satPrice2.getText())
                && validate(sunTxt1.getText(),sunPrice1.getText()) && validate(sunTxt2.getText(),sunPrice2.getText())
                && validate(monTxt1.getText(),monPrice1.getText()) && validate(monTxt2.getText(),monPrice2.getText())
                && validate(tueTxt1.getText(),tuePrice1.getText()) && validate(tueTxt2.getText(),tuePrice2.getText())
                && validate(wedTxt1.getText(),wedPrice1.getText()) && validate(wedTxt2.getText(),wedPrice2.getText())){
                    Food satFood1 = new Food(satTxt1.getText(), Double.parseDouble(satPrice1.getText()));
                    Food satFood2 = new Food(satTxt2.getText(), Double.parseDouble(satPrice2.getText()));
                    Admin.addFood(satFood1,satFood2,"Saturday");

                    Food sunFood1 = new Food(sunTxt1.getText(), Double.parseDouble(sunPrice1.getText()));
                    Food sunFood2 = new Food(sunTxt2.getText(), Double.parseDouble(sunPrice2.getText()));
                    Admin.addFood(sunFood1,sunFood2,"Sunday");

                    Food monFood1 = new Food(monTxt1.getText(), Double.parseDouble(monPrice1.getText()));
                    Food monFood2 = new Food(monTxt2.getText(), Double.parseDouble(monPrice2.getText()));
                    Admin.addFood(monFood1,monFood2,"Monday");

                    Food tueFood1 = new Food(tueTxt1.getText(), Double.parseDouble(tuePrice1.getText()));
                    Food tueFood2 = new Food(tueTxt2.getText(), Double.parseDouble(tuePrice2.getText()));
                    Admin.addFood(tueFood1,tueFood2,"Tuesday");

                    Food wedFood1 = new Food(wedTxt1.getText(), Double.parseDouble(wedPrice1.getText()));
                    Food wedFood2 = new Food(wedTxt2.getText(), Double.parseDouble(wedPrice2.getText()));
                    Admin.addFood(wedFood1,wedFood2,"Wednesday");

                    JOptionPane.showMessageDialog(null,"Saved successfully.",
                            "Saved", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid input or empty field.",
                            "Cannot save",JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        /**
         * Checks whether all the fields are filled or not.
         * @param text1 First text
         * @param text2 Second tex
         * @return true if valid, false if not.
         */
        private boolean validate(String text1, String text2){
            if (!User.justDigit(text2)){
                return false;
            }
            if (text1.length()==0 || text2.length()==0){
                return false;
            }
            return true;
        }
    }
}
