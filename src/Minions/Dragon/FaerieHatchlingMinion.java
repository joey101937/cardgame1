/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Dragon;

import Cards.Card;
import Cards.Dragon.FaerieDragonCard;
import Minions.DragonInterface;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class FaerieHatchlingMinion extends Minion implements DragonInterface{
    
     public int turnsRemaining = 2;
    
    public FaerieHatchlingMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 1;
        originalAttack = attack;
        maxHealth = 3;
        health = maxHealth;
        tribe = Tribe.Hatchling;
        name = "Faerie hatchling";
        sprite = SpriteHandler.faerieHatchlingMinion;
        this.intrinsicValue = 3;
    }

    @Override
    public void onTurnStart() {
        super.onTurnStart();
        turnsRemaining--;
        parent.cardText = "After " + getTurnsRemaining() + "* turns, grow \n into a Faerie Dragon";
        this.proc();
        if (turnsRemaining <= 0) {
            grow();
        }
    }
    
    @Override
    public int getTurnsRemaining() {
            return this.turnsRemaining;
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
        FaerieDragonCard dragonCard = new FaerieDragonCard();
        dragonCard.setHero(owner);
        return dragonCard.summon;
    }

}
