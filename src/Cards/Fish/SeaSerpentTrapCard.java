/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Fish;

import Cards.Card;
import Cards.CardType;
import Minions.Minion;
import Minions.Tribe;
import Traps.Fish.SeaSerpentTrap;
import Traps.TrapCard;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class SeaSerpentTrapCard extends TrapCard{
    
     public SeaSerpentTrapCard(){
        super();
        name = "Sea Serpent Trap";
        cardText = "After playing three \n 2+ cost fish, put \n a Sea Serpent card \n into your hand";
        sprite = SpriteHandler.seaSerphantTrap;
        cost = 1;
        myTrap = new SeaSerpentTrap(this);
    }
     

    @Override
    public int cast(Minion target){
        return super.cast(target);
    }

    @Override
    public int castOnHero(Hero h){
        return super.castOnHero(h);
    }
     
        //intrinsic value of 1. gains value for each applicable fish in hand, plus percent of fish in deck
        @Override
        public void tick(){
            int fishInDeck = 0;
            int fishInHand = 0;
            
            intrinsicValue = 1;
           for(Card c : owner.hand){
               if(c.cardType == CardType.Minion && c.summon.tribe == Tribe.Fish && c.cost > 1){
                   this.intrinsicValue += 4;
                   fishInHand++;
               }
           }
           for(Card c : owner.deck){
               if(c.cardType == CardType.Minion && c.summon.tribe == Tribe.Fish && c.cost > 1){
                   fishInDeck++;
               }
           }
           intrinsicValue += (fishInDeck/owner.deck.size()) * 3;
           if(fishInHand + fishInDeck < 3) intrinsicValue = 0; //if we cant fullfill the 3 fish requirement, this card is worthless
        }
}
