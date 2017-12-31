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
public class SeaSerpentMinion extends Minion{
    public SeaSerpentMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 6;
        tribe = Tribe.Fish;
        maxHealth = 5;
        health = maxHealth;
        name = "Sea Witch";
        sprite = SpriteHandler.seaSerphantMinion;
    }
}