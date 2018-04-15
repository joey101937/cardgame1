/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Elemental;

import Cards.Card;
import Cards.Dragon.FirePlumeCard;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class MoltenGolemMinion extends Minion {

    public MoltenGolemMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 5;
        originalAttack = attack;
        maxHealth = 4;
        health = maxHealth;
        tribe = Tribe.Golem;
        name = "Molten Golem";
        sprite = SpriteHandler.moltenGolemMinion;
        intrinsicValue =3;
    }
    
    @Override
    public void attack(Minion target){
        if(canAttack() && attack>0)owner.draw(new FirePlumeCard());
        super.attack(target);    
    }
}
