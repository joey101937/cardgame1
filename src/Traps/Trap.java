/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Traps;

import Cards.Card;
import Minions.Minion;
import cardgame1.Board;
import cardgame1.Hero;
import cardgame1.SpriteHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Joseph
 */
public abstract class Trap {
    /*      FIELDS        */
    public Hero owner;
    public Card parent; //the card that put this Trap into play
    public String name;
    /**
     * renders the trap icon at location
     */
    public void render(Graphics2D g, int x, int y){
        g.drawImage(SpriteHandler.trapSymbol, x,y,null);
    }
    /**
     * renders the card's actual sprite, revealing the true effect of the trap
     */
    public void renderRevealed(Graphics2D g, int x, int y){
        parent.render(g, x, y, true);
    }
    
    /**
     * gets the coordinate of the top left of where the trap symbol is being rendered.
     * not adjusted for scaling
     * @return 
     */
    public int getXCoordinate(){
        int output = 100 + (SpriteHandler.trapSymbol.getWidth() * owner.traps.indexOf(this) + (owner.traps.indexOf(this) * Board.buffer));
        return output;
    }
    
    /**
     * gets the coordinate of the top left corner of where the trap symbol for this trap is being rendered. 
     * not adjusted for scaling
     * @return 
     */
    public int getYCoordinate(){
        if(owner == Board.botHero){
            return 925;
        }else{
            return 50;
        }
    }

    public void tick(){}
    
    /*      The following methods use the trapListener class        */
    public void onAttack(Minion attacker, Minion defender){}
    public void onMinionTakeDamage(Minion m){}
    public void onSummon(Minion m){}
    public void onPlay(Card c){}
    public void onMinionDeath(Minion m){};
    public void onAttackHero(Minion attacker, Hero Defender){}
}
