/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Base;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class ArcherMinion extends Minion{

       public ArcherMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 2;
        originalAttack = attack;
        maxHealth = 1;
        health = maxHealth;
        tribe = Tribe.none;
        name = "Archer";
        sprite = SpriteHandler.archerMinion;
    }
    
}
