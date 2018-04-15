/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Elemental;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Main;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class SandElementalMinion extends Minion{
        public SandElementalMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        originalAttack = attack;
        maxHealth = 2;
        health = maxHealth;
        name = "Sand Elemental";
        this.tribe = Tribe.Elemental;
        sprite = SpriteHandler.sandElementalMinion;
    }
        
    @Override
    public void onSummon(){
        for(Minion m : owner.opponent.minions.getOccupants()){
            proc();
            m.takeDamage(parent.spellDamage);
        }
    }
}
