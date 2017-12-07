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
public class KnightMinion extends Minion{

        
    public KnightMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        maxHealth = 2;
        health = maxHealth;
        tribe = Tribe.knight;
        name = "Knight";
        sprite = SpriteHandler.knightMinion;
    }
    
}
