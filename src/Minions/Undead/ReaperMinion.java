/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Undead;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class ReaperMinion extends Minion{
     public ReaperMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 6;
        originalAttack = attack;
        maxHealth = 1;
        health = maxHealth;
        tribe = Tribe.Undead;
        name = "Reaper";
        sprite = SpriteHandler.reaperMinion;
    }
}
