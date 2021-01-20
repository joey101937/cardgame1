/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Empire;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class GriffonMinion extends Minion {

    public GriffonMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        originalAttack = attack;
        maxHealth = 5;
        health = maxHealth;
        tribe = Tribe.Beast;
        name = "Griffon";
        sprite = SpriteHandler.griffonMinion;
        intrinsicValue = -1*(this.attack+this.health-4);
    }
    
    @Override
    public void tick(){
        intrinsicValue = -1*(this.attack+this.health-2);
        boolean knightsPresent = false;
        for(Minion m : owner.minions.getOccupants()){
            if(m.tribe==Tribe.Knight){
                m.intrinsicValue = this.attack;
                this.intrinsicValue = -1;
                knightsPresent = true;
            }
        }
        if(!knightsPresent){
            this.bind();
        }else{
            this.unbind();
        }
    }
    
    @Override
    public void onDeath() {
        super.onDeath();
        for (Minion m : owner.minions.getOccupants()) {
            if (m.tribe == Tribe.Knight) {
                m.intrinsicValue = 0;
            }
        }
    }
    
    @Override
    public void attack(Minion target) {
        for (Minion m : owner.minions.getOccupants()) {
            if (m.tribe == Tribe.Knight) {
                super.attack(target);
                return;
            }
        }
    }
    
    @Override
    public void attack(Hero target) {
        for (Minion m : owner.minions.getOccupants()) {
            if (m.tribe == Tribe.Knight) {
                super.attack(target);
                return;
            }
        }
    }
   
    

}
