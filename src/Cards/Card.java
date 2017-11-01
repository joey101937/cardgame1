/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import cardgame1.Board;
import cardgame1.Hero;
import cardgame1.InputHandler;
import cardgame1.SpriteHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Card itself, these are played from the hand to perform functions or generate Minions
 * @author Joseph
 */
public abstract class Card {
    public static final Integer WIDTH = 200;
    public static final Integer HEIGHT = 300;
    /* FIELDS */
    public String name;         //name of card
    public CardType cardType;   //type of card
    public String cardText;     //what the card says on it
    public int cost;            //casting cost
    public BufferedImage sprite; //visual representation of the card
    private Hero owner;
    
    public void render(Graphics2D g, int x, int y){
       if(owner == Board.playerHero){
           g.drawImage(sprite, x, y, null);   //the card is ours so we can see what it is
           if(InputHandler.selectedCard == this){
               if(owner.resource >= cost){// if selected and we can afford to cast it, color it green
                   g.setColor(new Color(0,255,0,50));
                   g.fillRect(x, y, Card.WIDTH, Card.HEIGHT);
               }else{
                   g.setColor(new Color(0,0,255,40)); //if we cannot afford it, color it blue
                   g.fillRect(x, y, Card.WIDTH, Card.HEIGHT);
               }
           }
       }else{
           g.drawImage(SpriteHandler.cardback, x, y, null);    //if we arent the owner of the card, we see the cardback, not the card itself
       }
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
        return "Card " + name;
    }
    public void setHero(Hero h){
        owner = h;
    }
    public Hero getOwner(){
        return owner;
    }
}
