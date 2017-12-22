/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Fish;

import Minions.Minion;
import Traps.Fish.SwollowTrap;
import Traps.TrapCard;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class SwollowCard extends TrapCard{
        public SwollowCard(){
        super();
        name = "Swollow Trap";
        cardText = "Whenever your \n opponent summons \n a minion with less \n than 4 health, \n destroy it";
        sprite = SpriteHandler.swollowTrap;
        cost = 2;
        myTrap = new SwollowTrap(this);
    }
        
        @Override
        public void tick(){
            intrinsicValue = 1;
            intrinsicValue += owner.opponent.hand.size()/2;
        }
}
