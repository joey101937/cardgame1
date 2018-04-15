/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Elemental;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class EarthGolemMinion extends Minion {

    public EarthGolemMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        originalAttack = attack;
        maxHealth = 7;
        health = maxHealth;
        tribe = Tribe.Golem;
        name = "Earth Golem";
        sprite = SpriteHandler.earthGolemMinion;
        intrinsicValue = 2;
    }
    
 @Override
    public void tick(){
        intrinsicValue = 2;
        int dif = owner.opponent.minions.getOccupants().size()-owner.minions.getOccupants().size();
        intrinsicValue += (dif*2);
    }
    
    @Override
    public void onAttackHeroDetect(Minion m, Hero h){
        if(h==owner){
            this.proc();
            h.proc();
            owner.heal(2);
        }
    }
}
