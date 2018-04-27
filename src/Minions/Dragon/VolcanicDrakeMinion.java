/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Dragon;

import Cards.Card;
import Cards.Dragon.FirePlumeCard;
import Minions.DragonInterface;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class VolcanicDrakeMinion extends Minion implements DragonInterface{
    public VolcanicDrakeMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 5;
        originalAttack = attack;
        maxHealth = 4;
        health = maxHealth;
        tribe = Tribe.Dragon;
        name = "Volcanic Drake";
        sprite = SpriteHandler.volcanicDrakeMinion;
    }

    /**
     * reduces random enemy minions attack by 2; minimum of 0
     */
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

    @Override
    public void breath() {
         this.proc();
        owner.draw(new FirePlumeCard());
        owner.draw(new FirePlumeCard());
    }

    @Override
    public Minion getAdultForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
