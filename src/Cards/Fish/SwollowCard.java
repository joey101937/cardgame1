/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Fish;

import CustomDecks.HeroClass;
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
        cardText = "Whenever your \n opponent summons \n a minion with 2 or \n less health,  \n silence then \n destroy it";
        sprite = SpriteHandler.swollowTrap;
        cost = 1;
        heroClass = HeroClass.Ocean;
        myTrap = new SwollowTrap(this);
    }
        //intrinsic value of 1. gains alittle vlaue for each card in opponent's hand
        @Override
        public void tick(){
            intrinsicValue = 1;
            intrinsicValue += owner.opponent.hand.size()/2;
        }
}
