/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Dragon;

import Cards.Card;
import Cards.Dragon.GlacialDragonCard;
import Minions.Minion;
import Minions.Tribe;
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
        this.intrinsicValue = 3;
    }
    
    @Override
    public void onTurnStart(){
        super.onTurnStart();
        turnsRemaining--;
        parent.cardText = "After " + getTurnsRemaining() + "* turns, grow \n into a Glacial Dragon";
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
        GlacialDragonCard dragonCard = new GlacialDragonCard();
        dragonCard.setHero(owner);
        return dragonCard.summon;
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
            owner.minions.add( adultForm);
            adultForm.onSummon();   
            intrinsicValue =  3-turnsRemaining;
    }

}
