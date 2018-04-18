/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Campaign.campaignGUI;

import Cards.Card;
import cardgame1.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * panel for adder frame
 * renders background and card images
 * @author Joseph
 */
public class CardAdderPanel extends JPanel {

    CardAdderFrame host;
    BufferedImage background = Main.BackgroundImage;
   

    public CardAdderPanel(CardAdderFrame host) {
        this.host = host;
        this.addMouseListener(new MouseListener() {
            @Override
           public void mouseClicked(MouseEvent e) {
           
           }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    /**
     * Same as regular paintComponent except it also draws the given buffered
     * image will also render cards note the image is sourced starting from a
     * 200x200 offset from the top left of the image rather than usual 0x0
     *
     * @param g image to draw
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (background == null) {
            g2d.setBackground(new Color(255, 255, 255, 0));
            return;
        }
        this.setBackground(Color.black);
        //g.drawImage(img, 0, 0, null);
        g.drawImage(background, 0, 0, 1000, 1000, 200, 200, 1000, 1300, null);
        drawCards(g2d);
    }
    
    
    /*
     *Player chooses 3 cards per win
     *First 2 are Class cards
     *Third is Neutral
     */   
    public void drawCards(Graphics2D g){
        g.scale(1, 1);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        for(int i = 0; i<4 ; i++){
            try{
                Card toDraw = host.currentSelection.get(i);
                if(toDraw==null)continue;
                toDraw.render(g, 20 + 220*i, 200, true);
            }catch(IndexOutOfBoundsException ex){
                continue;
            }
        }
    }

}
