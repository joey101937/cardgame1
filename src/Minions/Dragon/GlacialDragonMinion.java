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
import Multiplayer.Phantom;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class GlacialDragonMinion extends Minion implements DragonInterface{

    public GlacialDragonMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 4;
        originalAttack = attack;
        maxHealth = 6;
        health = maxHealth;
        tribe = Tribe.Dragon;
        name = "Glacial Dragon";
        sprite = SpriteHandler.whiteDragonMinion;
    }

    @Override
    public void onSummon(){
        breath();
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
       if(owner.opponent.minions.getOccupants().size()==0){
        this.proc();
        return;
       }
        int roll = (int) (Phantom.random.nextDouble() * (owner.opponent.minions.numOccupants()));
        Minion target = owner.opponent.minions.getOccupants().get(roll);
        new Sticker(SpriteHandler.snowflakeLarge,target,AI.AI.speed);
        Main.wait(AI.AI.speed);
        target.freeze();
        target.takeDamage(3);
        this.proc();
    }

    @Override
    public Minion getAdultForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
