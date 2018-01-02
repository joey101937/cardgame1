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
public class SkeletonMinion extends Minion{
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
}
