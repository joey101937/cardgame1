/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Dragon;

import Cards.Card;
import Cards.CardType;
import Minions.Minion;
import Minions.Tribe;
import Multiplayer.Phantom;
import cardgame1.SpriteHandler;
import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public class DragonTamerMinion extends Minion{
     public DragonTamerMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 2;
        originalAttack = attack;
        maxHealth = 1;
        health = maxHealth;
        tribe = Tribe.none;
        name = "Dragon Tamer";
        sprite = SpriteHandler.dragonTamerMinion;
    }
     
     @Override
     public void onSummon() {
       Minion summon = null;
        if (owner.minions.isFull()) return;
        ArrayList<Minion> pool = new ArrayList<>();
        for (Card c : Card.getCardList()) {
            if (c.cardType == CardType.Minion && c.summon.tribe == Tribe.Hatchling) {
                pool.add(c.summon);
            }
        }
        summon = pool.get((int) (Phantom.random.nextDouble() * pool.size()));
        summon.parent.setHero(owner);
        owner.minions.add(summon);
    }
}
