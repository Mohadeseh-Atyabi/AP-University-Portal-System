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
 * This class represents a panel in which the professor adds class.
 */
public class AddClass extends Background {
    User currentUser;
    JTextField nameTxt;
    JComboBox dayComb;
    JComboBox timeComb;
    JTextField capacityTxt;
    JTextField codeTxt;
    JTextField unitTxt;
    JButton save;

    /**
     * Constructor of the class to build the panel
     * @param user Username of the current user
     * @param pass Password of the current user
     */
    public AddClass(String user, String pass){
        super(".\\Images\\3033.jpg",650,700);
        currentUser = new User(user,pass);

        TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.BLACK),"Add Class");
        titledBorder.setTitleFont(new Font("Nani", Font.ITALIC , 15));
        setBorder(new CompoundBorder(new EmptyBorder(10,5,10,10) , titledBorder));

        //Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Nani" , Font.BOLD , 15));
        nameTxt = new JTextField();
        nameTxt.setFont(new Font("Nani", Font.BOLD , 15));

        //Day
        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setFont(new Font("Nani" , Font.BOLD , 15));
        String[] timeList = {"8-10" , "10-12" , "14-16"};
        timeComb = new JComboBox(timeList);
        timeComb.setFont(new Font("Nani" , Font.BOLD , 15));

        //Time
        JLabel dayLabel = new JLabel("Day:");
        dayLabel.setFont(new Font("Nani" , Font.BOLD , 15));
        String[] dayList = {"Saturday" , "Sunday" , "Monday" , "Tuesday" , "Wednesday"};
        dayComb = new JComboBox(dayList);
        dayComb.setFont(new Font("Nani" , Font.BOLD , 15));

        //Capacity
        JLabel capacityLabel = new JLabel("Capacity:");
        capacityLabel.setFont(new Font("Nani" , Font.BOLD , 15));
        capacityTxt = new JTextField();
        capacityTxt.setFont(new Font("Nani", Font.BOLD , 15));

        //Code
        JLabel codeLabel = new JLabel("Code:");
        codeLabel.setFont(new Font("Nani" , Font.BOLD , 15));
        codeTxt = new JTextField();
        codeTxt.setFont(new Font("Nani" , Font.BOLD , 15));

        //Units
        JLabel unitLabel = new JLabel("Unit:");
        unitLabel.setFont(new Font("Nani" , Font.BOLD , 15));
        unitTxt = new JTextField();
        unitTxt.setFont(new Font("Nani", Font.BOLD , 15));

        //Save button
        save = new JButton("Save", new ImageIcon(".\\Images\\Check.png"));
        save.setFont(new Font("Nani" , Font.BOLD, 15));
        Handler handler = new Handler();
        save.addActionListener(handler);

        //Layout of the panel
        GroupLayout groupLayout = new GroupLayout(this);
        setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        .addGap(100)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(nameLabel)
                                        .addGap(40)
                                        .addComponent(nameTxt,300,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(codeLabel)
                                        .addGap(42)
                                        .addComponent(codeTxt,300,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(capacityLabel)
                                        .addGap(17)
                                        .addComponent(capacityTxt,300,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(unitLabel)
                                        .addGap(54)
                                        .addComponent(unitTxt,300,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(dayLabel)
                                        .addGap(55)
                                        .addComponent(dayComb,150,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(timeLabel)
                                        .addGap(48)
                                        .addComponent(timeComb,150,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addGap(100)
                                        .addComponent(save,200,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)))
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addGap(100)
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGap(50)
                        .addComponent(nameLabel)
                        .addComponent(nameTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGap(50)
                        .addComponent(codeLabel)
                        .addComponent(codeTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGap(50)
                        .addComponent(capacityLabel)
                        .addComponent(capacityTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGap(50)
                        .addComponent(unitLabel)
                        .addComponent(unitTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGap(50)
                        .addComponent(dayLabel)
                        .addComponent(dayComb,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGap(50)
                        .addComponent(timeLabel)
                        .addComponent(timeComb,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addGap(25)
                        .addComponent(save,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
        );
    }

    /**
     * Handler class to handle the events on the panel
     */
    public class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(save)){
                //Check valid
                if (!validToClass(nameTxt.getText(),codeTxt.getText(),unitTxt.getText(),capacityTxt.getText())){
                    JOptionPane.showMessageDialog(null,"Invalid input to creat class.",
                            "Failed",JOptionPane.ERROR_MESSAGE);
                }else {
                    Day day = Day.SATURDAY;
                    switch (dayComb.getSelectedIndex()){
                        case 1 -> day = Day.SUNDAY;
                        case 2 -> day = Day.MONDAY;
                        case 3 -> day = Day.TUESDAY;
                        case 4 -> day = Day.WEDNESDAY;
                    }
                    Time time = Time.EIGHT_TEN;
                    switch (timeComb.getSelectedIndex()){
                        case 1 -> time = Time.TEN_TWELVE;
                        case 2 -> time = Time.FOURTEEN_SIXTEEN;
                    }
                    Class newClass = new Class(day,time,nameTxt.getText(),codeTxt.getText(),Integer.parseInt(unitTxt.getText()),
                            Integer.parseInt(capacityTxt.getText()));
                    if (Professor.addClass(currentUser.getUsername(),newClass)){
                        JOptionPane.showMessageDialog(null,"Class added successfully." ,
                                "Added successfully", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null,"Couldn't add the class.",
                                "Failed",JOptionPane.ERROR_MESSAGE);
                    }
                }
                nameTxt.setText("");
                codeTxt.setText("");
                unitTxt.setText("");
                capacityTxt.setText("");
                timeComb.setSelectedIndex(0);
                dayComb.setSelectedIndex(0);
            }
        }
    }

    /**
     * Checks whether the inputs are valid to creat a class or not
     * @param name Name of the class
     * @param code Code of the class
     * @param unit Number of units of the class
     * @param capacity Capacity of the class
     * @return True if valid, false if not
     */
    private boolean validToClass(String name, String code, String unit, String capacity){
        if (name.length()==0 || code.length()==0 || unit.length()==0 || capacity.length()==0){
            return false;
        }
        //Code must has 7 digits
        if (Long.parseLong(code) < 1000000 || Long.parseLong(code) > 9999999){
            return false;
        }
        if (Integer.parseInt(unit) < 1 || Integer.parseInt(unit) > 4){
            return false;
        }
        return Integer.parseInt(capacity) >= 1;
    }
}
