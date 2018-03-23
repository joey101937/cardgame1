/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomDecks;

import Cards.Base.FireBoltCard;
import Cards.Card;
import cardgame1.SpriteHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * widow that displays but a single card
 * @author Joseph
 */
public class CardDisplay extends JFrame{
    public Card toDisplay; //the card we want to display in the canvas
    public static CardDisplay display;
    private ColorPanel panel;
    public CardDisplay(Card c){
        toDisplay = c;
        initComponents();
    }
    
    private void initComponents(){
        this.setSize(220,340);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel = new ColorPanel(toDisplay);
        panel.setSize(200,200);
        this.setResizable(false);
        add(panel);
        this.setVisible(true);
    }
    public static void display(Card c){
        if(display!= null)display.dispose();
        display = new CardDisplay(c);
    }
    

}
