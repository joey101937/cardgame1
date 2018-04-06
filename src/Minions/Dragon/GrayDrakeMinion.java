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
import cardgame1.Main;
import cardgame1.ProcHandler;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class GrayDrakeMinion extends Minion {

    public GrayDrakeMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 2;
        originalAttack = attack;
        maxHealth = 4;
        health = maxHealth;
        tribe = Tribe.Dragon;
        name = "Gray Drake";
        sprite = SpriteHandler.grayDrakeMinion;
    }

    /**
     * reduces random enemy minions attack by 2; minimum of 0
     */
    @Override
    public void onSummon() {
        if(owner.opponent.minions.numOccupants()==0)return;
        int roll = (int) (Phantom.random.nextDouble() * (owner.opponent.minions.numOccupants()));
        Minion target = owner.opponent.minions.getOccupants().get(roll);
        target.attack-=2;
        if(target.attack<0)target.attack=0;
        this.proc();
        target.damagedTicker=20;
        new ProcHandler(target);
    }
}
