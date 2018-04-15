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
public class WarGolemMinion extends Minion{
        public WarGolemMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        originalAttack = attack;
        maxHealth = 3;
        health = maxHealth;
        tribe = Tribe.Golem;
        name = "War Golem";
        sprite = SpriteHandler.warGolemMinion;
    }
        
        
   @Override
    public void tick(){
        intrinsicValue = 0;
        for(Minion m : owner.minions.getOccupants()){
            if(m.name.equals("Stone Golem")){
                intrinsicValue++;
                if(m.canAttack())intrinsicValue++;
            }
        }
        for(Card c : owner.hand){
            if(c.name.equals("Stone Golem")){
                intrinsicValue++;
            }
        }               
    }
    
    @Override
    public void onSummon() {
        for(Minion m : owner.minions.getOccupants()){
            if(m.name.equals("Stone Golem")){
                m.attack+=2;
            }
        }
    }
    
    @Override
    public void onSummonDetect(Minion m){
    if(m.name.equals("Stone Golem")){
                m.attack+=2;
            }
    }
    
    @Override
    public void onDeath() {
        for (Minion m : owner.minions.getOccupants()) {
            if (m.name.equals("Stone Golem")) {
                m.attack -= 2;
            }
        }
    }
}
