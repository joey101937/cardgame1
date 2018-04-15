/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Elemental;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class FireElementalMinion extends Minion {

    public FireElementalMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 6;
        originalAttack = attack;
        maxHealth = 4;
        health = maxHealth;
        name = "Fire Elemental";
        this.tribe = Tribe.Elemental;
        sprite = SpriteHandler.fireElementalMinion;
    }
    
    @Override
    public void onSummon(){
        proc();
        owner.opponent.takeDamage(parent.spellDamage);
    }
}
