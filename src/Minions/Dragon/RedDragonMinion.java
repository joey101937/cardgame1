/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Dragon;

import Cards.Card;
import Minions.DragonInterface;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Stickers.Sticker;

/**
 *
 * @author Joseph
 */
public class RedDragonMinion extends Minion implements DragonInterface{

    public RedDragonMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 5;
        originalAttack = attack;
        maxHealth = 7;
        health = maxHealth;
        tribe = Tribe.Dragon;
        name = "Red Dragon";
        sprite = SpriteHandler.redDragonMinion;
    }
        
    @Override
    public void onSummon(){
         for(Minion m : owner.opponent.minions.getOccupants()){
            proc();
            m.takeDamage(parent.spellDamage);
        }
    }
    @Override
    public int getTurnsRemaining() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void grow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void breath() {
         for(Minion m : owner.opponent.minions.getOccupants()){
            proc();
            new Sticker(SpriteHandler.blastEffectSmall,m,AI.AI.speed/3);
            Main.wait(AI.AI.speed/3);
            m.takeDamage(parent.spellDamage);
        }
         
    }

    @Override
    public Minion getAdultForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
