/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Empire;

import AI.AI;
import static AI.AI.canFavorablyTrade;
import AI.SimulatedMinion;
import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class EnchantedSwordCard extends Card {

    public EnchantedSwordCard() {
        name = "Enchanted Sword";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = true;
        spellDamage = 3;
        cardText = "Give a knight \n +3/+2";
        sprite = SpriteHandler.enchantedSwordCard;
        cost = 2;
    }

    @Override
    public void smartCast() {
        int bestValue = -1;
        Minion best = null;
        for(Minion m : owner.minions.getOccupants()){
            if(m.tribe!= Tribe.Knight)continue;
            this.intrinsicValue = 5;
            if(!AI.canFavorablyTrade(m)){
                if(AI.canFavorablyTrade(new SimulatedMinion(m.attack+3,m.health+2,m.owner))){
                    if(AI.getTradeValue(new SimulatedMinion(m.attack+3,m.health+2,m.owner), AI.getBestTarget(new SimulatedMinion(m.attack+3,m.health+2,m.owner)))>bestValue){
                        bestValue = AI.getTradeValue(new SimulatedMinion(m.attack+3,m.health+2,m.owner), AI.getBestTarget(new SimulatedMinion(m.attack+3,m.health+2,m.owner)));
                        best = m;
                    }
                }
            }
        }
        if (best != null) {
            cast(best);
        } else {
            if (getOwner().minions.numOccupants() > 0) {
                cast(AI.getBiggestThreatOf(getOwner()));
            }
        }
    }

    @Override
    public void tick(){
        this.intrinsicValue = -3;
        int bestValue = -1;
        Minion best = null;
        for(Minion m : owner.minions.getOccupants()){
            if(m.tribe!= Tribe.Knight)continue;
            this.intrinsicValue = 5;
            if(!AI.canFavorablyTrade(m)){
                if(AI.canFavorablyTrade(new SimulatedMinion(m.attack+3,m.health+2,m.owner))){
                    if(AI.getTradeValue(new SimulatedMinion(m.attack+3,m.health+2,m.owner), AI.getBestTarget(new SimulatedMinion(m.attack+3,m.health+2,m.owner)))>bestValue){
                        bestValue = AI.getTradeValue(new SimulatedMinion(m.attack+3,m.health+2,m.owner), AI.getBestTarget(new SimulatedMinion(m.attack+3,m.health+2,m.owner)));
                        best = m;
                    }
                }
            }
            if(bestValue>intrinsicValue)intrinsicValue=bestValue;
        } 
    }
    
    
    @Override
    public int cast(Minion target){
        if(!canAfford())return 0;
        if(target==null || target.tribe!=Tribe.Knight) return -1;
        notifyPhantom(target,null);
        target.attack+=3;
        target.maxHealth+=2;
        target.health+=2;
        owner.resource-=cost;
        owner.hand.remove(this);
        return 1;
    }
    
    @Override
    public int castOnHero(Hero target){
        return -1;
    }
}
