/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Base;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class FrostDragonMinion extends Minion{
     public FrostDragonMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 4;
        maxHealth = 6;
        health = maxHealth;
        tribe = Tribe.Dragon;
        name = "Frost Dragon";
        sprite = SpriteHandler.frostDragonMinion;
    }
     
     @Override
     public void onSummon(){
         Sticker s = new Sticker(SpriteHandler.snowflakeLarge,this.getXCordinate() + Minion.WIDTH/2, this.getYcoordinate()+Minion.HEIGHT/2, 400);
         Main.wait(400);
         for(Minion m : owner.opponent.minions.getOccupants()){
             m.freeze();
         }
     }
}
