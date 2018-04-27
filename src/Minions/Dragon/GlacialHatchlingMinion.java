/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Dragon;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import static Minions.Tribe.Dragon;
import cardgame1.SpriteHandler;
import Minions.DragonInterface;

/**
 *
 * @author Joseph
 */
public class GlacialHatchlingMinion extends Minion implements DragonInterface{

    public int turnsRemaining = 2;
    
    public GlacialHatchlingMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 1;
        originalAttack = attack;
        maxHealth = 3;
        health = maxHealth;
        tribe = Tribe.Hatchling;
        name = "Glacial Hatchling";
        sprite = SpriteHandler.whiteHatchlingMinion;
    }
    
    @Override
    public void onTurnEnd(){
        turnsRemaining--;
        this.proc();
        if(turnsRemaining<=0){
            grow();
        }
    }

    @Override
    public void breath() {
        System.out.println("trying to get breath from a hatchling " + this);
        return;
    }

    @Override
    public Minion getAdultForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTurnsRemaining() {
       return this.turnsRemaining;
    }

    @Override
    public void grow() {
             int place = owner.minions.indexOf(this);
            owner.minions.remove(this);
            owner.minions.add(place, getAdultForm());
    }

}
