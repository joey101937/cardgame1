/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Dragon;

import Cards.Card;
import Cards.Dragon.EmeraldDragonCard;
import Cards.Dragon.RedDragonCard;
import Minions.DragonInterface;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class EmeraldHatchlingMinion extends Minion implements DragonInterface{
    public int turnsRemaining = 2;
    
    public EmeraldHatchlingMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 1;
        originalAttack = attack;
        maxHealth = 3;
        health = maxHealth;
        tribe = Tribe.Hatchling;
        name = "Emerald Hatchling";
        sprite = SpriteHandler.greenHatchlingMinion;
        this.intrinsicValue = 3;
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
        EmeraldDragonCard dragonCard = new EmeraldDragonCard();
        dragonCard.setHero(owner);
        return dragonCard.summon;
    }
    
}
