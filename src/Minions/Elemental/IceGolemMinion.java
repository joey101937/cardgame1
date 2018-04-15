/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Elemental;

import Cards.Card;
import Cards.Elemental.IceyWindCard;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class IceGolemMinion extends Minion {

    public IceGolemMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        originalAttack = attack;
        maxHealth = 7;
        health = maxHealth;
        tribe = Tribe.Golem;
        name = "Ice Golem";
        sprite = SpriteHandler.iceGolemMinion;
        intrinsicValue = 2;
    }
    
    @Override
    public void attack(Minion m){
        if(canAttack()&&attack>0)owner.draw(new IceyWindCard());
        super.attack(m);
    }
    
      @Override
    public void attack(Hero h) {
        if (canAttack() && attack > 0) {
            owner.draw(new IceyWindCard());
        }
        super.attack(h);
    }
}
