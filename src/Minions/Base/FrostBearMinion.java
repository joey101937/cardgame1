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
public class FrostBearMinion extends Minion{

       public FrostBearMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        maxHealth = 5;
        health = maxHealth;
        tribe = Tribe.Beast;
        name = "Frost Bear";
        sprite = SpriteHandler.frostBearMinion;
    }
    
}
