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
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class EmeraldDragonMinion extends Minion implements DragonInterface{
    
    
 public EmeraldDragonMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        originalAttack = attack;
        maxHealth = 8;
        health = maxHealth;
        tribe = Tribe.Dragon;
        name = "Emerald Dragon";
        sprite = SpriteHandler.greenDragonMinion;
    }
    
    @Override
    public void onTurnEnd(){
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
        if(owner.health>=owner.maxHealth)return;
        proc();
       owner.heal(3);
       owner.proc();
    }

    @Override
    public Minion getAdultForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
