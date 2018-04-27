/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Dragon;

import Cards.Card;
import Minions.DragonInterface;
import Minions.Minion;
import Minions.Tribe;
import Multiplayer.Phantom;
import cardgame1.ProcHandler;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class GrayDrakeMinion extends Minion implements DragonInterface{

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

    //casts this dragons breath on summon
    @Override
    public void onSummon() {
      breath(); 
    }

    @Override
    public int getTurnsRemaining() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void grow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        /**
     * reduces random enemy minions attack by 2; minimum of 0
     */
    @Override
    public void breath() {
         if(owner.opponent.minions.numOccupants()==0)return;
        int roll = (int) (Phantom.random.nextDouble() * (owner.opponent.minions.numOccupants()));
        Minion target = owner.opponent.minions.getOccupants().get(roll);
        target.attack-=2;
        if(target.attack<0)target.attack=0;
        this.proc();
        target.damagedTicker=20;
        new ProcHandler(target);
    }

    @Override
    public Minion getAdultForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
