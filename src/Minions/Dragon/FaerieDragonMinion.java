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
import cardgame1.ProcHandler;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class FaerieDragonMinion extends Minion implements DragonInterface{

    
    
    public FaerieDragonMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 6;
        originalAttack = attack;
        maxHealth = 3;
        health = maxHealth;
        tribe = Tribe.Dragon;
        name = "Faerie Dragon";
        sprite = SpriteHandler.faerieDragonMinion;
    }
    
    @Override
    public void onSummon(){
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
        proc();
        owner.opponent.damageTicker = 20;
        new ProcHandler(owner.opponent);
        owner.opponent.maxResource--;
    }

    @Override
    public Minion getAdultForm() {
             throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
