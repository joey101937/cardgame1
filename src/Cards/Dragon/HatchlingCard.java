/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Dragon;

import AI.SimulatedMinion;
import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Minion;
import Minions.Tribe;
import Multiplayer.Phantom;
import cardgame1.Hero;
import cardgame1.SpriteHandler;
import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public class HatchlingCard extends Card{
      public HatchlingCard() {
        name = "Spawn Hatchling";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = false;
        cardText = "Summon a random \n Dragon Hatchling.";
        sprite = SpriteHandler.hatchlingCard;
        cost = 1;
        heroClass = HeroClass.Dragon;
    }
      
    @Override
    public void tick(){
        this.intrinsicValue = 0;
        if(owner.minions.isFull())return;
        else{
            intrinsicValue = AI.AI.getWorth(new SimulatedMinion(1,3,owner))+1;
        }
    }
    
    @Override
    public int cast(Minion m){
        if(owner.minions.isFull())return -1;
        if(!canAfford())return 0;
        notifyPhantom(m,null);
        ArrayList<Minion> pool = new ArrayList<>();
        for(Card c: Card.getCardList()){
            if(c.cardType==CardType.Minion && c.summon.tribe==Tribe.Hatchling){
                pool.add(c.summon);
            }
        }
        summon = pool.get((int)(Phantom.random.nextDouble()*pool.size()));
        summon.parent.setHero(owner);
        return defaultMinionSummon();
    }
    
        @Override
    public void smartCast(){
        this.cast(null);
    }
    
    @Override
    public int castOnHero(Hero h){
        return cast(null);
    }
}
