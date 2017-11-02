/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions;

import Cards.Card;
import cardgame1.Board;
import cardgame1.Hero;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * the creature summoned by cards to fight
 * @author Joseph
 */
public abstract class Minion{
    /* FIELDS */
    public String name;         //name of card
    public Integer attack;          //attack value
    public Integer health;          //current health
    public Integer maxHealth;       //current health
    public Tribe tribe;             //"classification" or "type"
    public BufferedImage sprite;    //visual representation
    public int damagedTicker = 0; //set to positive value when damaged, passively ticks down to 0 over time.
    public Card parent; //card that summoned it, used for righclick for details.
    public Hero owner;  //hero controling this minion
    public static final Integer WIDTH = 150; //width of minion in pixel
    public static final Integer HEIGHT = 225; //heigh in pixels
    public static final Integer TOP_Y_OFFSET = 200; //how far the minion is rendered from the top of the screen for the tophero
    public static final Integer BOT_Y_OFFSET = 700; //how far the minion is rendered from the top of the screen for the bot hero
    public static final Integer SPACER_SIZE = 100; //this plus WIDTH is how far apart to render each minion
    public static Color attackRed = new Color(250,20,20,255);
    public static Color healthGreen = new Color(30,200,0,255);
    public Minion(){
    }
    /**
     * runs every tick
     */
    public abstract void tick();
    /**
     * runs when the minion is summoned
     */
    public abstract void onSummon();
    /**
     * runs every render
     * @param g 
     */
    public void render(Graphics2D g, int x , int y){
        g.drawImage(sprite, x, y, null);
        g.setColor(attackRed); //red for attack
        g.drawString(attack.toString(), x-20, y+Minion.HEIGHT);
        g.setColor(healthGreen); //green color for health
        g.drawString(health.toString(), x+Minion.WIDTH, y+Minion.HEIGHT);
        //damaging effect
        if(damagedTicker > 0){
        g.setColor(new Color(255,0,0,(this.damagedTicker)*12));
        damagedTicker--;
        g.fillRect(x, y, Minion.WIDTH, Minion.HEIGHT);
        }
    }
    
    /*   MINION GAMEPLAY      */
    /**
     * Attacks another minion, each combatant takes damage based on the other's attack. If 
     * either has their health reduced to 0, destroy that minion
     * @param target 
     */
    public void attack(Minion target){
        target.takeDamage(this.attack);
        this.takeDamage(target.attack);
    }
    /**
     * removes from the game
     */
    public void destroy(){
        for(Minion m : Board.topHero.minions.getStorage()){
            if(m == this){
                Board.topHero.minions.remove(m);
                return;
            }
        }
        for(Minion m : Board.botHero.minions.getStorage()){
            if(m == this){
                Board.botHero.minions.remove(m);
                return;
            }
        }
        System.out.println("ERROR: could not find minion to remove in either hero's minion pool.  " + this );
    }
    
    /**
     * damages the minion and applies any needed effects
     * @param amount 
     */
    public void takeDamage(int amount){
        this.health-=amount;
        this.damagedTicker = 20;
        if(this.health <= 0){
            this.destroy();
        }
    }
    
    
    /**
     * attacks enemy hero/lifepoints directly
     */
    public void directAttack(Hero target){
        target.takeDamage(attack);
        if(target.health<=0){
            //TODO: <win game>
        }
    }
    
    /**
     * gets the x coordinate of the top left corner of the rendering, assuming the minion is in play. returns -1 if the minion is not
     * @return x coordinate of top left corner, -1 if not in play
     */
    public int getXCordinate(){
        for(Minion m : Board.topHero.minions.getStorage()){
            if(m == this){
                return (Minion.SPACER_SIZE + (Board.topHero.minions.indexOf(m) * (Minion.WIDTH + Minion.SPACER_SIZE)));
            }
        }
        for (Minion m : Board.botHero.minions.getStorage()) {
            if (m == this) {
                return (Minion.SPACER_SIZE + (Board.botHero.minions.indexOf(m) * (Minion.WIDTH + Minion.SPACER_SIZE)));
            }
        }
        return -1;
    }
    /**
     * returns the y coordinate of the topleft corner of the minion render based on owner. 
     * if owner is neither top nor bottom hero, return -1
     * @return 
     */
    public int getYcoordinate(){
        if(this.owner == Board.topHero){
            return Minion.TOP_Y_OFFSET;
        }
        if(this.owner == Board.botHero){
            return Minion.BOT_Y_OFFSET;
        }
        return -1;
    }
    
    @Override
    public String toString(){
        return (this.name + "  "+ this.attack + "/" + this.health + " " + this.owner.name);
    }
}
