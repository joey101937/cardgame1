/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import Minions.Minion;
import cardgame1.Board;
import cardgame1.Hero;
import cardgame1.InputHandler;
import cardgame1.SpriteHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Card itself, these are played from the hand to perform functions or generate Minions
 * @author Joseph
 */
public abstract class Card {
    public static final Integer WIDTH = 200;
    public static final Integer HEIGHT = 300;
    /*   AI FLAGS       */
    /** will this deal damage (true) or heal (false). used to determine which side of the field to target */
    public boolean isOffensive = false;
    
    /* FIELDS */
    public String name;         //name of card
    public CardType cardType;   //Minion or spell
    public CardPurpose cardPurpose; //purpose of the card, used with AI
    public boolean isTargeted = false; //does this card's cast method want to take a target?
    public int spellDamage = 0;     //damage dealt on cast, if applicable
    public Minion summon;       //if this is a minion card, the minion it summons. if spell, this is null
    public String cardText;     //what the card says on it
    public int cost;            //casting cost
    public BufferedImage sprite; //visual representation of the card
    protected Hero owner;
    
    /**
     * renders a particular card, cardback if its an enemy's card.
     * @param g graphics to use
     * @param x where to draw(x)
     * @param y  where to draw (y)
     * @param override if set to true, the method always renders the actual card (enemies see the card, not cardback)
     */
    public void render(Graphics2D g, int x, int y, boolean override){
       if(owner == Board.playerHero || override){
           g.drawImage(sprite, x, y, null);   //the card is ours so we can see what it is
           g.setColor(Color.yellow);
           g.drawString(String.valueOf(cost), x + 10, y+35);
           this.renderCardText(g, x, y);
           if(summon != null){
               g.setColor(Minion.attackRed);
               g.drawString(summon.attack.toString(), x + 60 - (summon.attack.toString().length() * 10), y + (Card.HEIGHT*12)/14);
               g.setColor(Minion.healthGreen);
               g.drawString(summon.health.toString(), x + Card.WIDTH - 60 - (summon.health.toString().length() * 10), y + (Card.HEIGHT*12)/14);
           }
           if(InputHandler.selectedCard == this){
               if(owner.resource >= cost){// if selected and we can afford to cast it, color it green
                   g.setColor(new Color(0,255,0,50));
                   g.fillRect(x, y, Card.WIDTH, Card.HEIGHT);
               }else{
                   g.drawImage(SpriteHandler.redX, x, y, null);
               }
           }
       }else{
           g.drawImage(SpriteHandler.cardback, x, y, null);    //if we arent the owner of the card, we see the cardback, not the card itself
       }
    }
    
    
    
    
    private void renderCardText(Graphics2D g, int x , int y){
        Font original = g.getFont();
        Font toUse = new Font("TimesRoman", Font.BOLD, 20);
        g.setColor(Color.black);
        g.setFont(toUse);
        String[] lines = cardText.split(" \n ");
        for(int i = 0; i < lines.length; i++){
            g.drawString(lines[i], x + 10, y + 175 + (i * 20));
        }
        g.setFont(original);
    }
    
    /**
     * gets the int value of the topleft corner of the rendered card based on location in hand.
     * @return x value of topleft corner NOT ADJUSTED FOR SCALING; -1 if card is not in hand.
     */
    public int getXCoordinate(){
        for(Card c : Board.playerHero.hand){
            if(c != this) continue;
            if(Board.playerHero.hand.indexOf(c) % 2 == 0){
                //left column, even
                return 1100;
            }else{
                //right column, odd
                return 1100 + Board.buffer + Card.WIDTH;
            }
        }
        return -1;
    }
      /**
     * gets the int value of the topleft corner of the rendered card based on
     * location in hand.
     * @return y value of topleft corner NOT ADJUSTED FOR SCALING; -1 if card is
     * not in hand.
     */
    public int getYCoordinate(){
        for(Card c : Board.playerHero.hand){
            if(c != this) continue;
            if(Board.playerHero.hand.indexOf(c) < 2){
                return 25 + Board.buffer;       //top level
            }else if(Board.playerHero.hand.indexOf(c) < 4){
                return 25 + Board.buffer + Minion.HEIGHT;  //middle level
            }else if(Board.playerHero.hand.indexOf(c) < 6){
                return 25 + Board.buffer + Minion.HEIGHT + Minion.HEIGHT; //bottom level
            }
        }
        return -1;
    }
    
    /**
     * what happens when the card is played from the hand.
     * @param target target minion if applicable. may be null
     * returns an int reflecting the outcome. 
     * 0 for success
     * 1 for not cast (not enough mana, etc)
     */
    public abstract int cast(Minion target);
    /**
     * if the owner has enough resources left to afford the cast cost of this card
     * @return 
     */
    public boolean canAfford(){
        return owner.resource >= cost;
    }
    public void destroy(){
        //TODO
    }
    
    /**
     * default method to use for simple summon cards
     * @return  1 if success, -1 if error, 0 if could not afford
     */
    protected int defaultMinionSummon(){
        if (canAfford()) {
            if (owner.minions.add(summon)) {
                owner.resource -= cost;
                owner.hand.remove(this);
                summon.onSummon();
                return 1;
            } else {
                //could not legally summon. likely not enough board slots
                return -1;
            }
        }
        return 0;
    }
    
    
    @Override
    public String toString(){
        return "Card " + name;
    }
    public void setHero(Hero h){
        owner = h;
        if(summon != null)summon.owner = h;
    }
    public Hero getOwner(){
        return owner;
    }
}
