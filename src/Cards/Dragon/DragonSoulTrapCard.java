/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Dragon;

import CustomDecks.HeroClass;
import Minions.Minion;
import Minions.Tribe;
import Traps.Dragon.DragonSoulTrap;
import Traps.TrapCard;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class DragonSoulTrapCard extends TrapCard {

    public DragonSoulTrapCard() {
        super();
        name = "Dragon Soul Trap";
        cardText = "Whenever a friendly \n dragon dies, gain \n its effect as a spell";
        sprite = SpriteHandler.dragonSoulTrap;
        cost = 1;
        heroClass = HeroClass.Dragon;
        myTrap = new DragonSoulTrap(this);
    }
    //intrinsic value of 1. gains alittle vlaue for each card in opponent's hand

    @Override
    public void tick() {
        intrinsicValue = 1;
        Minion best = null;
        for(Minion m : owner.minions.getOccupants()){
            if(m.tribe != Tribe.Dragon) continue;
            if(best==null || best.intrinsicValue < m.intrinsicValue){
                best = m;
            }
        }
        if(best!=null)intrinsicValue=best.intrinsicValue + 1;
    }
}
