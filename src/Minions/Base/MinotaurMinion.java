/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Base;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class MinotaurMinion extends Minion{
 
    
    public MinotaurMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 2;
        originalAttack = attack;
        maxHealth = 3;
        health = maxHealth;
        tribe = Tribe.none;
        name = "Minotaur";
        sprite = SpriteHandler.minotaurMinion;
    }

    
    @Override
    public void onSummon(){
        try{
        if(owner.opponent.minions.getStorage().get(owner.minions.getStorage().indexOf(this))!=null){
            owner.opponent.minions.getStorage().get(owner.minions.getStorage().indexOf(this)).takeDamage(parent.spellDamage);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
