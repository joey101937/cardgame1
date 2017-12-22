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
        System.out.println("i saw a minion summoned");
        if(m.owner!=owner && m.health < 4){
            Sticker s = new Sticker(SpriteHandler.bloodMedium, m, AI.AI.speed * 4);
            if(owner==Board.topHero){
                Sticker reveal = new Sticker(parent, this.getXCoordinate(), this.getYCoordinate(), AI.AI.speed * 4);
            }else{
                Sticker reveal = new Sticker(parent, this.getXCoordinate(), this.getYCoordinate()+Card.HEIGHT, AI.AI.speed * 4);
            }
            Main.wait(AI.AI.speed * 4);
            m.destroy();
            owner.traps.remove(this);
            m.destroy();
        }
    }

}
