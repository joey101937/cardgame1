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
        attack = 2;
        originalAttack = attack;
        maxHealth = 3;
        health = maxHealth;
        tribe = Tribe.Knight;
        name = "Cavalry General";
        sprite = SpriteHandler.cavalryGeneralMinion;
        intrinsicValue = 4;
    }
    @Override
    public void onSummonDetect(Minion m){
        if(m.owner == owner && m.tribe == Tribe.Knight && m!=this) {
            m.refresh();
        }
    }
}
