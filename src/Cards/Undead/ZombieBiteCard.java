/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Undead;

import Traps.TrapCard;
import Traps.Undead.ZombieBiteTrap;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class ZombieBiteCard extends TrapCard{
    public ZombieBiteCard(){
        super();
        name = "Zombie Bite";
        cardText = "Whenever your \n opponent summons \n a minion, destroy \n it and summon \n a 3/1 zombie \n in its place.";
        sprite = SpriteHandler.zombieTrap;
        cost = 2;
        myTrap = new ZombieBiteTrap(this);
    }
    
    @Override
    public void tick(){
    intrinsicValue = owner.maxResource/2;
    }
}
