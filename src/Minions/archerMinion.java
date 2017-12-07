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
public class archerMinion extends Minion{

       public archerMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 2;
        maxHealth = 1;
        health = maxHealth;
        tribe = Tribe.none;
        name = "Archer";
        sprite = SpriteHandler.archerMinion;
    }
    
}
