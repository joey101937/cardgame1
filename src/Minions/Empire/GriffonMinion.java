/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Empire;

import Cards.Card;
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
public class GriffonMinion extends Minion {

    public GriffonMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 6;
        originalAttack = attack;
        maxHealth = 6;
        health = maxHealth;
        tribe = Tribe.Beast;
        name = "Griffon";
        sprite = SpriteHandler.griffonMinion;
        intrinsicValue = 0;
    }
    
    @Override
    public void onSummon(){
        for(Minion m : owner.minions.getOccupants()){
            if(m.tribe==Tribe.Knight){
                this.refresh();
                return;
            }
        }
    }
    
}
