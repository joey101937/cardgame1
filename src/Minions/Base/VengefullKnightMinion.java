/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Base;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class VengefullKnightMinion extends Minion{ 
    public VengefullKnightMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 4;
        originalAttack = attack;
        maxHealth = 2;
        health = maxHealth;
        tribe = Tribe.Knight;
        name = "Vengefull Knight";
        sprite = SpriteHandler.knightChargeMinion;
    }
    @Override
    public void onSummon(){
        this.refresh();
    }
}
