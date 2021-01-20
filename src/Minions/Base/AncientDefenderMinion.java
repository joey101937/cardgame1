/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Base;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class AncientDefenderMinion extends Minion {

    public AncientDefenderMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 2;
        originalAttack = attack;
        maxHealth = 6;
        health = maxHealth;
        tribe = Tribe.Golem;
        name = "Ancient Defender";
        sprite = SpriteHandler.ancientDefenderMinion;
    }
    
    @Override
    public void tick(){
        intrinsicValue = 1;
        int dif = owner.opponent.minions.getOccupants().size()-owner.minions.getOccupants().size();
        intrinsicValue += (dif*2);
    }
    
    @Override
    public void onAttackHeroDetect(Minion m, Hero h){
        if(h==owner){
            this.proc();
            owner.heal(2);
        }
    }
}
