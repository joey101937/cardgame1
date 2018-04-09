/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Undead;

import CustomDecks.HeroClass;
import Traps.TrapCard;
import Traps.Undead.ZombieTrap;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class ZombieTrapCard extends TrapCard{
    public ZombieTrapCard(){
        super();
        name = "Zombify";
        cardText = "Whenever your \n opponent summons \n a minion, destroy \n it and summon \n a 3/1 zombie \n in its place.";
        sprite = SpriteHandler.zombieTrap;
        cost = 2;
        myTrap = new ZombieTrap(this);
        heroClass = HeroClass.Undead;
    }
    
    @Override
    public void tick(){
    intrinsicValue = owner.maxResource/2;
    }
}
