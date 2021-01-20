/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Empire;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class CavalryGeneralMinion extends Minion{

    public CavalryGeneralMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        originalAttack = attack;
        maxHealth = 2;
        health = maxHealth;
        tribe = Tribe.Knight;
        name = "Cavalry General";
        sprite = SpriteHandler.cavalryGeneralMinion;
        intrinsicValue = -1;
    }
    @Override
    public void onTurnStart(){
        this.proc();
        owner.resource--;
        refresh();
    }
}
