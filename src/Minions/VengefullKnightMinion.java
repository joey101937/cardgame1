/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions;

import Cards.Card;
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
        maxHealth = 2;
        health = maxHealth;
        tribe = Tribe.knight;
        name = "Vengefull Knight";
        sprite = SpriteHandler.knightChargeMinion;
    }
    @Override
    public void onSummon(){
        this.canAttack = true;
    }
}
