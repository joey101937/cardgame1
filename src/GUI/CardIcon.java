/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Cards.Card;
import CustomDecks.HeroClass;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * for use in deckbuilder class, displays a card image
 * @author Joseph
 */
public class CardIcon extends JPanel{
    public Card card; //what card to display
    public DeckBuilder host; //what builder this is a part of
    public BufferedImage cardImage;
    /**
     * constructor
     */
    public CardIcon(Card c, DeckBuilder db){
        card = c;
        host = db;
        cardImage = card.sprite;
        this.setSize(new Dimension(cardImage.getWidth(),cardImage.getHeight()));
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {               
            }
            @Override
            public void mousePressed(MouseEvent e) {      
                host.addCard(card);
            }
            @Override
            public void mouseReleased(MouseEvent e) {              
            }
            @Override
            public void mouseEntered(MouseEvent e) {                
            }
            @Override
            public void mouseExited(MouseEvent e) {                
            }
        });
    }
    
    @Override
    public void paintComponent(Graphics g){
         super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
             if(card==null){
                 g2d.setBackground(new Color(255,255,255,0));
                 return;
             }
            this.setBackground(Color.gray);
            g.setFont(new Font("Arial", Font.BOLD, 35));
            card.render(g2d, 0, 0, true);
            if(card.heroClass==HeroClass.Neutral) return;
            g.drawImage(card.heroClass.getClassIcon(), cardImage.getWidth()-card.heroClass.getClassIcon().getWidth(), 30, null);
    }
}
