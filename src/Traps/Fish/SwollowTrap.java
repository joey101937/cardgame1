/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Traps.Fish;

import Cards.Card;
import Minions.Minion;
import Traps.Trap;
import cardgame1.Board;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;
import cardgame1.VisualEffectHandler;


/**
 * whenever your opponent plays a minion with less than 4 health, destroy it
 * @author Joseph
 */
public class SwollowTrap extends Trap{

    public SwollowTrap(Card parent) {
        owner = parent.getOwner();
        this.parent=parent;
        name = "Swollow Trap";
    }
    
    @Override
    public void onSummon(Minion m){
        if(m.owner!=owner && m.health < 4){
            Sticker reveal = new Sticker(parent, 1700, 200, AI.AI.speed * 6);
            Sticker blood = new Sticker(SpriteHandler.bloodMedium,m,AI.AI.speed*6);
            Main.wait(AI.AI.speed*6);
            m.destroy();
            owner.traps.remove(this);
            m.destroy();
        }
    }

}
