/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Undead;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class SkelemancerMinion extends Minion {

    public SkelemancerMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        maxHealth = 6;
        intrinsicValue = 3; // we want these minions to be expendable
        health = maxHealth;
        tribe = Tribe.Undead;
        name = "Skelemancer";
        sprite = SpriteHandler.skelemancerMinion;
    }
    
    @Override
    public void onTurnEnd(){
        Sticker s = new Sticker(SpriteHandler.skullEffect,this,AI.AI.speed/2);
        Main.wait(AI.AI.speed/2);
        owner.minions.add(new SkeletonMinion(this.parent));
    }
    
}
