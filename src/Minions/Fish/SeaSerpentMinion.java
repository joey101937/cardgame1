/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Fish;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Stickers.Sticker;

/**
 *
 * @author Joseph
 */
public class SeaSerpentMinion extends Minion{
    public SeaSerpentMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 5;
        originalAttack = attack;
        tribe = Tribe.Fish;
        maxHealth = 5;
        health = maxHealth;
        name = "Sea Witch";
        sprite = SpriteHandler.seaSerphantMinion;
    }
    
    @Override
    public void onSummon() {
        Sticker s = new Sticker(SpriteHandler.bloodMedium,this,AI.AI.speed/2);
         Main.wait(AI.AI.speed/2);
        for (Minion m : owner.minions.getOccupants()) {
            if (m.tribe != Tribe.Fish) {      
                m.takeDamage(1);
            }
        }
        for (Minion m : owner.opponent.minions.getOccupants()) {
            if (m.tribe != Tribe.Fish) {
                m.takeDamage(1);
            }
        }
    }
}
