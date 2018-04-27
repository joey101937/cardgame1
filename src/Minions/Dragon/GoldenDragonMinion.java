/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Dragon;

import Cards.Card;
import Cards.Dragon.PurifyingBlastCard;
import Minions.DragonInterface;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class GoldenDragonMinion extends Minion implements DragonInterface{
    
    public GoldenDragonMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 7;
        originalAttack = attack;
        maxHealth = 5;
        health = maxHealth;
        tribe = Tribe.Dragon;
        name = "Golden Dragon";
        sprite = SpriteHandler.goldDragonMinion;
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
       owner.draw(new PurifyingBlastCard());
    }

    @Override
    public Minion getAdultForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
