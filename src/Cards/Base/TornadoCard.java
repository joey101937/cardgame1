/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Base;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Minion;
import Traps.Trap;
import Traps.TrapListener;
import cardgame1.Hero;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class TornadoCard extends Card {

    public TornadoCard() {
        name = "Tornado";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = false;
        cardText = "Destroy all \n enemy traps";
        sprite = SpriteHandler.tornadoCard;
        cost = 2;
    }
    
    @Override
    public void tick(){
        int value =0;
        for(Trap t : owner.opponent.traps.getOccupants()){
            value += t.parent.cost;
        }
        intrinsicValue = value;
    }
    
    @Override
    public void smartCast(){
        cast(null);
    }
 
    @Override
    public int cast(Minion target) {
        System.out.println("test");
        if (owner.opponent.minions.numOccupants() == 0) {
            return -1;
        }
        if (!canAfford()) {
            return 0; //reutrn 0 if unaffordable
        }
        for(Trap t : owner.opponent.traps.getOccupants()){
            new Sticker(SpriteHandler.redX,t.getXCoordinate()+SpriteHandler.trapSymbol.getWidth()/2,t.getYCoordinate()+SpriteHandler.trapSymbol.getHeight()/2,AI.AI.speed);
            owner.opponent.traps.remove(t);
        }
        notifyPhantom(null,null);
        owner.resource -= cost;
        owner.hand.remove(this);
        TrapListener.onPlay(this);
        return 1;
    }
    
    @Override
    public int castOnHero(Hero h){
        return cast(null);
    }
}
