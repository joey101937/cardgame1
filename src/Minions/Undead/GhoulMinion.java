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
public class GhoulMinion extends Minion{
    
        public GhoulMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 1;
        originalAttack = attack;
        maxHealth = 4;
        health = maxHealth;
        tribe = Tribe.Undead;
        name = "Ghoul";
        sprite = SpriteHandler.ghoulMinion;
    }

   @Override
    public void attack(Minion target) {
         if(canAttack())target.turnUndead();
        System.out.println("done");
        super.attack(target);

    }

    @Override
    public void onAttacked(Minion attacker) {
        if(attacker.canAttack())return;
        attacker.turnUndead();
        System.out.println("done");
        super.onAttacked(attacker);
    }
}
