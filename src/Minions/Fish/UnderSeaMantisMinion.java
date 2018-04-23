/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Fish;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class UnderSeaMantisMinion extends Minion{
    
    public UnderSeaMantisMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 4;
        originalAttack = attack;
        maxHealth = 4;
        health = maxHealth;
        tribe = Tribe.Fish;
        name = "Undersea Mantis";
        sprite = SpriteHandler.underSeaMantisMinion;
    }
    
    @Override
    public void attack(Minion m){
        if(canAttack()&&attack>0){
            owner.opponent.takeDamage(4);
        }
        super.attack(m);
    }

}
