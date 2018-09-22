/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Traps.Empire;

import Cards.Card;
import Minions.Minion;
import Traps.Trap;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Stickers.Sticker;


/**
 *
 * @author Joseph
 */
public class SnipeTrap extends Trap{
    
    public SnipeTrap(Card parent) {
        owner = parent.getOwner();
        this.parent=parent;
        name = "Snipe Trap";
    }
    
    @Override
    public void onSummon(Minion m){
        if(m.owner!=owner){
            Sticker reveal = new Sticker(parent, 1700, 200, AI.AI.speed * 6);
            Sticker impact = new Sticker(SpriteHandler.slashEffect,m,AI.AI.speed*6);
            Main.wait(AI.AI.speed*6);
            m.takeDamage(parent.spellDamage);
            owner.traps.remove(this);
        }
    }
}
