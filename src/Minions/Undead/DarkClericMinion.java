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
public class DarkClericMinion extends Minion{
      public DarkClericMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 2;
        originalAttack = attack;
        maxHealth = 4;
        health = maxHealth;
        tribe = Tribe.none;
        name = "Dark Cleric";
        sprite = SpriteHandler.darkClericMinion;
        intrinsicValue = 2;
    }
         @Override
    public void attack(Minion target) {
         if(canAttack() && (target.tribe==tribe.Undead || target.hasTurnedUndead())){
             target.takeDamage(99);
         }
        super.attack(target);
    }

    @Override
    public void onAttacked(Minion attacker) {
        if(attacker.canAttack())return;
         if(attacker.tribe==tribe.Undead || attacker.hasTurnedUndead()){
             attacker.takeDamage(99);
         }
        super.onAttacked(attacker);
    }
}
