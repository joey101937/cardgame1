/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomDecks;

import Cards.Card;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Joseph
 */
public class ColorPanel extends JPanel{
            Card card; 
        public ColorPanel(Card c){
            card = c;
        }
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
             if(card==null){
                 g2d.setBackground(new Color(255,255,255,0));
                 return;
             }
            this.setBackground(Color.black);  
            g.setFont(new Font("Arial", Font.BOLD, 35));
            card.render(g2d, 0, 0, true); //this method is known to work in other classes
        }
}
