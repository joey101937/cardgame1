/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomDecks;

import cardgame1.SpriteHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Joseph
 */
public class BackgroundPane extends JPanel{
        BufferedImage img;
        public BackgroundPane(BufferedImage i){
            img = i;
        }
        
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
            g.drawImage(img,0,0,700,1000,200,200,900,1000,null);
        } 
}
