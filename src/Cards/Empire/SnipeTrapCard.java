/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Empire;

import CustomDecks.HeroClass;
import Traps.TrapCard;
import Traps.Empire.SnipeTrap;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class SnipeTrapCard extends TrapCard {

    public SnipeTrapCard() {
        super();
        name = "Snipe Trap";
        spellDamage = 3;
        cardText = "Whenever your \n opponent summons \n a minion, deal " + spellDamage + " \n damage to it.";
        sprite = SpriteHandler.snipeTrap;
        cost = 1;
        heroClass = HeroClass.Empire;
        myTrap = new SnipeTrap(this);
    }
    
    //intrinsic value of 1. gains alittle vlaue for each card in opponent's hand
    @Override
    public void tick() {
        intrinsicValue = 1;
        intrinsicValue += owner.opponent.hand.size() / 2;
    }
}
