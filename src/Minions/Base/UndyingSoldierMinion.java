/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Base;

import Cards.Base.UndyingSoldierCard;
import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class UndyingSoldierMinion extends Minion {

    public UndyingSoldierMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 2;
        originalAttack = attack;
        maxHealth = 3;
        health = maxHealth;
        tribe = Tribe.Undead;
        name = "Undying Soldier";
        sprite = SpriteHandler.undeadSoldier;
    }
    
    @Override
    public void onDeath(){
        super.onDeath();
        Card toAdd = new UndyingSoldierCard();
        toAdd.setHero(owner);
        owner.deck.add((int)(Math.random()*owner.deck.size()), toAdd);
    }

}
