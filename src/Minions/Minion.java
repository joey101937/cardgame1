/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions;

import Cards.Card;
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
    public Tribe tribe;
    public BufferedImage sprite;
    public Card parent; //card that summoned it, used for righclick for details.
    public Hero owner;
    public static final Integer WIDTH = 150;
    public static final Integer HEIGHT = 225;
    public static Color attackRed = new Color(190,20,20,255);
    public static Color healthGreen = new Color(30,140,0,255);
    public Minion(){
    }
    /**
     * runs every tick
     */
    public abstract void tick();
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
    }
    
    /*   MINION GAMEPLAY      */
    /**
     * Attacks another minion, each combatant takes damage based on the other's attack. If 
     * either has their health reduced to 0, destroy that minion
     * @param target 
     */
    public void attack(Minion target){
        target.health -= this.attack;
        this.health -= target.attack;
        if(target.health <= 0){
            target.destroy();
        }
       if(this.health <= 0){
           this.destroy();
       }
    }
    /**
     * removes from the game
     */
    public void destroy(){
        //TODO
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
}
