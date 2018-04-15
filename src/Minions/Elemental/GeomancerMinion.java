/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Elemental;

import Cards.Card;
import Cards.Elemental.StoneGolemCard;
import Minions.Minion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class GeomancerMinion extends Minion {

    public GeomancerMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 1;
        originalAttack = attack;
        maxHealth = 1;
        health = maxHealth;
        name = "Geomancer";
        sprite = SpriteHandler.geomancerMinion;
    }
    
    @Override
    public void onSummon(){
        StoneGolemCard sgc = new StoneGolemCard();
        sgc.setHero(owner);
        StoneGolemMinion sgm = new StoneGolemMinion(sgc);
        sgm.proc();
        owner.minions.add(sgm);
    }
}
