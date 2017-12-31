/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Base;

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
public class PirranahMinion extends Minion{
    
    public PirranahMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        maxHealth = 2;
        health = maxHealth;
        tribe = Tribe.Fish;
        name = "Pirranah";
        sprite = SpriteHandler.pirranahMinion;
    }
    
        @Override
    public void onTurnEnd(){
        if(isFrozen){
            //do not attack if frozen
            isFrozen = false;
            return;
        }
        Sticker s = new Sticker(SpriteHandler.bloodMedium,this,AI.AI.speed/2); 
        Main.wait(AI.AI.speed/2);
        
        int roll = (int)(Math.random()*(owner.opponent.minions.numOccupants()+1));
        if (roll >= owner.opponent.minions.numOccupants()){
            owner.opponent.takeDamage(attack);
            return;
        }
        Minion target = owner.opponent.minions.getOccupants().get(roll);
        this.refresh();
        this.attack(target);
        Main.wait(AI.AI.speed/2);
    }
}