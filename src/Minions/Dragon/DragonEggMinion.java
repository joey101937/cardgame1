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
import cardgame1.Hero;
import cardgame1.SpriteHandler;
import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public class DragonEggMinion extends Minion{
     public DragonEggMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 0;
        originalAttack = attack;
        maxHealth = 3;
        health = maxHealth;
        tribe = Tribe.none;
        name = "Dragon Egg";
        sprite = SpriteHandler.dragonEggMinion;
        intrinsicValue = -7;
    }
     
    @Override
    public void onDeath() {
        if(owner.hand.size()>=Hero.maxHandSize)return;
        ArrayList<Card> possibilities = new ArrayList<>();
        for (Card c : Card.getCardList()) {
            if (c.cardType == CardType.Minion) {
                {
                    if (c.summon.tribe == Tribe.Dragon && c.cost == 7) {
                        possibilities.add(c);
                    }
                }
            }
        }
        Card c = possibilities.get((int)(Phantom.random.nextDouble()*possibilities.size()));
        c.cost=0;
        owner.draw(c);
    }
}
