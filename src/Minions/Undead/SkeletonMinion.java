/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Undead;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class SkeletonMinion extends Minion{
    public boolean fromArmy = false; //was this summoned by skeleton army?
    
    public SkeletonMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 2;
        originalAttack = attack;
        maxHealth = 1;
        intrinsicValue = -1; // we want these minions to be expendable
        health = maxHealth;
        tribe = Tribe.Undead;
        name = "Skeleton";
        sprite = SpriteHandler.skeletonMinion;
        this.refresh();
    }
        /**
         * used by skelton army to make them do less dmg to heros
         * @param parent
         * @param fromArmy 
         */
       public SkeletonMinion(Card parent, boolean fromArmy) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 2;
        originalAttack = attack;
        maxHealth = 1;
        intrinsicValue = -1; // we want these minions to be expendable
        health = maxHealth;
        tribe = Tribe.Undead;
        name = "Skeleton";
        sprite = SpriteHandler.skeletonMinion;
        this.fromArmy=fromArmy;
        this.refresh();
    }
    
    
    
    @Override
    public void attack(Hero h){
        super.attack(h);
        h.heal(attack/2);
    }
}
