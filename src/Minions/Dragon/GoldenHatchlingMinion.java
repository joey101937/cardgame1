/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Dragon;

import Cards.Card;
import Cards.Dragon.GoldenDragonCard;
import Minions.DragonInterface;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class GoldenHatchlingMinion extends Minion implements DragonInterface{
      public int turnsRemaining = 2;
    
    public GoldenHatchlingMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 1;
        originalAttack = attack;
        maxHealth = 3;
        health = maxHealth;
        tribe = Tribe.Hatchling;
        name = "Golden Hatchling";
        sprite = SpriteHandler.goldHatchlingMinion;
        this.intrinsicValue = 3;
    }
    
     @Override
    public void onTurnStart() {
        super.onTurnStart();
        turnsRemaining--;
        parent.cardText = "After " + getTurnsRemaining() + "* turns, grow \n into a Golden Dragon";
        this.proc();
        if (turnsRemaining <= 0) {
            grow();
        }
    }


    @Override
    public int getTurnsRemaining() {
      return turnsRemaining;
    }

    @Override
    public void grow() {
         int place = owner.minions.indexOf(this);
            owner.minions.remove(this);
            Minion adultForm = getAdultForm();
            owner.minions.add(place, adultForm);
            adultForm.onSummon();   
            intrinsicValue =  3-turnsRemaining;
    }

    @Override
    public void breath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Minion getAdultForm() {
        GoldenDragonCard dragonCard = new GoldenDragonCard();
        dragonCard.setHero(owner);
        return dragonCard.summon;
    }
}
