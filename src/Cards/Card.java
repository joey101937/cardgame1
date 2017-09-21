/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import cardgame1.BoardSlot;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Joseph
 */
public abstract class Card {
    /* FIELDS */
    public String name;         //name of card
    public CardType cardType;   //type of card
    public String cardText;     //what the card says on it
    public BufferedImage sprite; //visual representation of the card
    public BoardSlot slot; //where on the board it is
    
    public void render(Graphics g){
        g.drawImage(sprite, slot.x, slot.y,null);
    }
    /**
     * what happens when the card is played from the hand.
     * returns an int reflecting the outcome. 
     * 0 for success
     * 1 for not cast (not enough mana, etc)
     */
    public abstract int cast();
    
    
    public void destroy(){
        //TODO
    }
    
    @Override
    public String toString(){
        return name;
    }
}
