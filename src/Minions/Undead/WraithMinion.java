/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Undead;

import Cards.Card;
import Cards.CardType;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class WraithMinion extends Minion{
    
    public WraithMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 4;
        originalAttack = attack;
        maxHealth = 2;
        health = maxHealth;
        tribe = Tribe.Undead;
        name = "Wraith";
        sprite = SpriteHandler.wraithMinion;
       
    }
    
    @Override
    public void tick(){
        intrinsicValue = 0;
        for(Minion m : owner.minions.getOccupants()){
            if(m.tribe==Tribe.Knight){
            intrinsicValue += AI.AI.getWorth(m);
            m.intrinsicValue=1;
            }
        }
        for(Card c : owner.hand){
            if(c.cardType==CardType.Minion){
                if(c.summon.tribe == Tribe.Knight){
                    intrinsicValue += 2;
                }
            }
        }
        parent.intrinsicValue=intrinsicValue;
    }
    
    @Override
    public void onDeathDetect(Minion m){
        if(m.owner!=owner || m.tribe!=Tribe.Knight)return;
        owner.minions.add(new ZombieMinion(owner,m.attack,m.maxHealth));
    }
        
}
