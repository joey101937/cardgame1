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
public class SkullKingMinion extends Minion {

    public SkullKingMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 5;
        originalAttack = attack;
        maxHealth = 8;
        intrinsicValue = 4; // we want these minions to be expendable
        health = maxHealth;
        tribe = Tribe.Undead;
        name = "Skeleton King";
        sprite = SpriteHandler.skullKingMinion;
    }
    
    @Override
    public void attack(Minion m){
        super.attack(m);
        if(m != null && m.health <= 0){
            this.refresh();
        }
    }
    
    @Override
    public void tick(){
        intrinsicValue =4;
        if(health < 3) intrinsicValue=0;
    }
}
