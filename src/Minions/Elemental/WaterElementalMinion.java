/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Elemental;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import Traps.Trap;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class WaterElementalMinion extends Minion {

    public WaterElementalMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 2;
        originalAttack = attack;
        maxHealth = 2;
        health = maxHealth;
        name = "Water Elemental";
        this.tribe = Tribe.Elemental;
        sprite = SpriteHandler.waterElementalMinion;
    }
 

}
