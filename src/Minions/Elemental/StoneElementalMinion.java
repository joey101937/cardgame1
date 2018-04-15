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
public class StoneElementalMinion extends Minion {

    public StoneElementalMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        originalAttack = attack;
        maxHealth = 4;
        health = maxHealth;
        tribe = Tribe.Elemental;
        name = "Stone Elemental";
        sprite = SpriteHandler.stoneElementalMinion;
    }

}
