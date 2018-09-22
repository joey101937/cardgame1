/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Base;

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
public class FrostDragonMinion extends Minion implements DragonInterface{
     public FrostDragonMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        originalAttack = attack;
        maxHealth = 6;
        health = maxHealth;
        tribe = Tribe.Dragon;
        name = "Frost Dragon";
        sprite = SpriteHandler.frostDragonMinion;
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
         Sticker s = new Sticker(SpriteHandler.snowflakeLarge,this.getXCoordinate() + Minion.WIDTH/2, this.getYCoordinate()+Minion.HEIGHT/2, AI.AI.speed);
         Main.wait(AI.AI.speed);
         for(Minion m : owner.opponent.minions.getOccupants()){
             m.freeze();
         }
    }

    @Override
    public Minion getAdultForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
