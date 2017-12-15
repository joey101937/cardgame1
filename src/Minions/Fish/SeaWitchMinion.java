/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Fish;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class SeaWitchMinion extends Minion{
        public SeaWitchMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 1;
        tribe = Tribe.none;
        maxHealth = 4;
        health = maxHealth;
        name = "Sea Witch";
        sprite = SpriteHandler.seaWitchMinion;
    }
        
        @Override
        public void onSummon() {
        int highest = 0;
        for (Minion m : owner.minions.getOccupants()) {
            if(m.tribe == Tribe.Fish && m.attack > highest && m!=this){
                highest = m.attack;
            }
        }
        this.attack += highest;
        }
}
