package com.company;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class is a panel for student to choose food.
 */
public class ChooseFood extends JPanel {
    ArrayList<Food> food = Food.foodList();
    JButton save;
    JRadioButton sat1;
    JRadioButton sat2;
    JRadioButton sun1;
    JRadioButton sun2;
    JRadioButton mon1;
    JRadioButton mon2;
    JRadioButton tue1;
    JRadioButton tue2;
    JRadioButton wed1;
    JRadioButton wed2;
    ButtonGroup satGroup;
    ButtonGroup sunGroup;
    ButtonGroup monGroup;
    ButtonGroup tueGroup;
    ButtonGroup wedGroup;
    User currentUser;

    /**
     * Constructor of the class to build the panel
     * @param user Username of the current user
     * @param pass Password of the current user
     */
    public ChooseFood(String user, String pass){
        currentUser = new User(user,pass);

        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"Add Food");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));
//        setBackground(new Color(255 ,228, 181));

        //Saturday
        sat1 = new JRadioButton(food.get(0).toString());
        sat1.setFont(new Font("Nani" , Font.BOLD , 15));
        sat2 = new JRadioButton(food.get(1).toString());
        sat2.setFont(new Font("Nani" , Font.BOLD , 15));
        satGroup = new ButtonGroup();
        satGroup.add(sat1); satGroup.add(sat2);

        JPanel satPanel = new JPanel();
        satPanel.setLayout(new GridLayout(2,1));
        TitledBorder titledBorder1 = new TitledBorder(new LineBorder(Color.BLACK) , "Saturday");
        titledBorder1.setTitleFont(new Font("Nani" , Font.BOLD , 15));
        titledBorder1.setTitlePosition(TitledBorder.CENTER);
        satPanel.setBorder(titledBorder1);
        satPanel.add(sat1); satPanel.add(sat2);

        //Sunday
        sun1 = new JRadioButton(food.get(2).toString());
        sun1.setFont(new Font("Nani" , Font.BOLD , 15));
        sun2 = new JRadioButton(food.get(3).toString());
        sun2.setFont(new Font("Nani" , Font.BOLD , 15));
        sunGroup = new ButtonGroup();
        sunGroup.add(sun1); sunGroup.add(sun2);

        JPanel sunPanel = new JPanel();
        sunPanel.setLayout(new GridLayout(2,1));
        TitledBorder titledBorder2 = new TitledBorder(new LineBorder(Color.BLACK) , "Sunday");
        titledBorder2.setTitleFont(new Font("Nani" , Font.BOLD , 15));
        titledBorder2.setTitlePosition(TitledBorder.CENTER);
        sunPanel.setBorder(titledBorder2);
        sunPanel.add(sun1); sunPanel.add(sun2);

        //Monday
        mon1 = new JRadioButton(food.get(4).toString());
        mon1.setFont(new Font("Nani" , Font.BOLD , 15));
        mon2 = new JRadioButton(food.get(5).toString());
        mon2.setFont(new Font("Nani" , Font.BOLD , 15));
        monGroup = new ButtonGroup();
        monGroup.add(mon1); monGroup.add(mon2);

        JPanel monPanel = new JPanel();
        monPanel.setLayout(new GridLayout(2,1));
        TitledBorder titledBorder3 = new TitledBorder(new LineBorder(Color.BLACK) , "Monday");
        titledBorder3.setTitleFont(new Font("Nani" , Font.BOLD , 15));
        titledBorder3.setTitlePosition(TitledBorder.CENTER);
        monPanel.setBorder(titledBorder3);
        monPanel.add(mon1); monPanel.add(mon2);

        //Tuesday
        tue1 = new JRadioButton(food.get(6).toString());
        tue1.setFont(new Font("Nani" , Font.BOLD , 15));
        tue2 = new JRadioButton(food.get(7).toString());
        tue2.setFont(new Font("Nani" , Font.BOLD , 15));
        tueGroup = new ButtonGroup();
        tueGroup.add(tue1); tueGroup.add(tue2);

        JPanel tuePanel = new JPanel();
        tuePanel.setLayout(new GridLayout(2,1));
        TitledBorder titledBorder4 = new TitledBorder(new LineBorder(Color.BLACK) , "Tuesday");
        titledBorder4.setTitleFont(new Font("Nani" , Font.BOLD , 15));
        titledBorder4.setTitlePosition(TitledBorder.CENTER);
        tuePanel.setBorder(titledBorder4);
        tuePanel.add(tue1); tuePanel.add(tue2);

        //Wednesday
        wed1 = new JRadioButton(food.get(8).toString());
        wed1.setFont(new Font("Nani" , Font.BOLD , 15));
        wed2 = new JRadioButton(food.get(9).toString());
        wed2.setFont(new Font("Nani" , Font.BOLD , 15));
        wedGroup = new ButtonGroup();
        wedGroup.add(wed1); wedGroup.add(wed2);

        JPanel wedPanel = new JPanel();
        wedPanel.setLayout(new GridLayout(2,1));
        TitledBorder titledBorder5 = new TitledBorder(new LineBorder(Color.BLACK) , "Tuesday");
        titledBorder5.setTitleFont(new Font("Nani" , Font.BOLD , 15));
        titledBorder5.setTitlePosition(TitledBorder.CENTER);
        wedPanel.setBorder(titledBorder5);
        wedPanel.add(wed1); wedPanel.add(wed2);

        save = new JButton("Save", new ImageIcon(".\\Images\\Check.png"));
        save.setFont(new Font("Nani" , Font.BOLD, 15));
        Handler handler = new Handler();
        save.addActionListener(handler);

        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGap(70)
                    .addGroup(groupLayout.createParallelGroup()
                        .addComponent(satPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                        .addComponent(sunPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                        .addComponent(monPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                        .addComponent(tuePanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                        .addComponent(wedPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE))
                    .addGroup(groupLayout.createParallelGroup().addComponent(save))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                    .addComponent(satPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE).addGap(20)
                    .addComponent(sunPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE).addGap(20)
                    .addComponent(monPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE).addGap(20)
                    .addComponent(tuePanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE).addGap(20)
                    .addComponent(wedPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE).addGap(20)
                    .addComponent(save)
        );
    }

    /**
     * Handler class to handle action on the panel
     */
    private class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(save)){
                Day day = null;
                Food food1 = null;
                if (satGroup.getSelection()!=null){
                    day = Day.SATURDAY;
                    food1 = sat1.isSelected() ? food.get(0) : food.get(1);
                }
                else if (sunGroup.getSelection()!=null){
                    day = Day.SATURDAY;
                    food1 = sun1.isSelected() ? food.get(2) : food.get(3);
                }
                else if (monGroup.getSelection()!=null){
                    day = Day.MONDAY;
                    food1 = mon1.isSelected() ? food.get(4) : food.get(5);
                }
                else if (tueGroup.getSelection()!=null){
                    day = Day.TUESDAY;
                    food1 = tue1.isSelected() ? food.get(6) : food.get(7);
                }
                else if (wedGroup.getSelection()!=null){
                    day = Day.WEDNESDAY;
                    food1 = wed1.isSelected() ? food.get(8) : food.get(9);
                }
                if (food1!=null){
                    if (! Student.addFood(day,food1,currentUser.getUsername())){
                        JOptionPane.showMessageDialog(null,"Balance is not enough or you have reserved food.",
                                "Reserve failed",JOptionPane.ERROR_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null,"Food Reserved.",
                                "Reservation is done",JOptionPane.INFORMATION_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"No selection.",
                            "No selection",JOptionPane.WARNING_MESSAGE);
                }
                satGroup.clearSelection();
                sunGroup.clearSelection();
                monGroup.clearSelection();
                tueGroup.clearSelection();
                wedGroup.clearSelection();
            }
        }
    }
}
