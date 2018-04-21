/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import cardgame1.SpriteHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Modified JPanel that displays a given image; for use as a background
 * @author Joseph
 */
public class BackgroundPane extends JPanel{
        BufferedImage img;
        public int xOffset = 200, yOffset = 200;
        /**
         * creates background pane with desired image at 200x200 offset
         * @param i 
         */
        public BackgroundPane(BufferedImage i){
            img = i;
        }
        /**
         *  creates background with desired image at set offsets
         * @param i img to draw
         * @param xOff x offset
         * @param yOff  y offset
         */
        public BackgroundPane(BufferedImage i, int xOff, int yOff){
            img = i;
            xOffset = xOff;
            yOffset = yOff;
        }
        /**
         * Same as regular paintComponent except it also draws the given buffered image
         * note the image is sourced starting from a 200x200 offset from the top left of the image rather than usual 0x0
         * @param g image to draw
         */
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
             if(img==null){
                 g2d.setBackground(new Color(255,255,255,0));
                 return;
             }
            this.setBackground(Color.black);  
            //g.drawImage(img, 0, 0, null);
            g.drawImage(img,0,0,1000,1000,xOffset,yOffset,1000,1300,null);
        } 
}
