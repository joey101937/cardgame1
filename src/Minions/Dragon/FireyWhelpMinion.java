/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Dragon;

import Cards.Base.FireBoltCard;
import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class FireyWhelpMinion extends Minion{
        public FireyWhelpMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 2;
        originalAttack = attack;
        maxHealth = 1;
        health = maxHealth;
        tribe = Tribe.Dragon;
        name = "Firey Whelp";
        sprite = SpriteHandler.fireyWhelpMinion;
    }

    /**
     * reduces random enemy minions attack by 2; minimum of 0
     */
    @Override
    public void onSummon() {
        this.proc();
        owner.draw(new FireBoltCard());
    }
}
