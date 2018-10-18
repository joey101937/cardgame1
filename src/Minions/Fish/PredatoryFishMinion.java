/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Fish;

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
public class PredatoryFishMinion extends Minion{
        public PredatoryFishMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 2;
        originalAttack = attack;
        maxHealth = 1;
        health = maxHealth;
        tribe = Tribe.Fish;
        name = "Predatory Fish";
        sprite = SpriteHandler.predatoryFishMinion;
    }
    
    @Override
    public void onSummon(){
        for(Minion m : owner.minions.getOccupants()){
            if(m.name.equals("Baitfish")){
                Sticker s = new Sticker(SpriteHandler.bloodMedium,this,AI.AI.speed);
                Main.wait(AI.AI.speed);
                m.destroy();
            }
        }
    }
}
