/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Elemental;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class EarthElementalMinion extends Minion {

    public EarthElementalMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 4;
        originalAttack = attack;
        maxHealth = 5;
        health = maxHealth;
        tribe = Tribe.Elemental;
        name = "Earth Elemental";
        sprite = SpriteHandler.earthElementalMinion;
        intrinsicValue = 0;
    }
}
