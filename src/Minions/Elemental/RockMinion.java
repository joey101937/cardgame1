/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Elemental;

import Cards.Card;
import Minions.Minion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class RockMinion extends Minion{
        public RockMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 0;
        originalAttack = attack;
        maxHealth = 3;
        health = maxHealth;
        name = "Rock";
        sprite = SpriteHandler.rock;
        this.intrinsicValue=-5;
    }
}
