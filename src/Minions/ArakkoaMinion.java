/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions;

import Cards.Card;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class ArakkoaMinion extends Minion{

    /*
    MINION FIELDS
    public String name;         //name of card
    public String cardText;     //what the card says on it
    public Integer attack;          //attack value
    public Integer health;          //current health
    public Integer maxHealth;       //current health
    public CardTribe tribe;
    public BufferedImage sprite;
    public Card parent; //card that summoned it, used for righclick for details.
    */
    
    
    public ArakkoaMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 1;
        maxHealth = 3;
        health = maxHealth;
        tribe = Tribe.none;
        name = "Arakkoa";
        sprite = SpriteHandler.arakkoaMinion;
    }
    


    
}
