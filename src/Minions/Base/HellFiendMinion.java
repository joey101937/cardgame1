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
 * big ole minion
 * @author Joseph
 */
public class HellFiendMinion extends Minion{
        public HellFiendMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 7;
        originalAttack = attack;
        maxHealth = 8;
        health = maxHealth;
        tribe = Tribe.none;
        name = "Hell Fiend";
        sprite = SpriteHandler.hellFiendMinion;
    }
}
