package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * It is a panel which background is a photo.
 */
public class Background extends JPanel {
    Image pic;
    int width,height;

    public Background(String address,int width, int height){
        ImageIcon obj = new ImageIcon(address);
        pic = obj.getImage();
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(pic,0,0,width,height,null);
    }

}
