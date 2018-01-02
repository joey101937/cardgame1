/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Base;

import Cards.Card;
import Cards.CardType;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class KelpieMinion extends Minion{
        public KelpieMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 4;
        originalAttack = attack;
        maxHealth = 3;
        health = maxHealth;
        tribe = Tribe.Fish;
        name = "Kelpie";
        sprite = SpriteHandler.kelpieMinion;
    }
        @Override
        public void onSummon(){
            for(Card c : owner.hand){
                if(c.cardType==CardType.Minion){
                    c.cost+=2;
                }
            }
        }
}
