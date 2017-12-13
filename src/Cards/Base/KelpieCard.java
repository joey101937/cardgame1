/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Base;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Base.KelpieMinion;
import cardgame1.Board;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class KelpieCard extends Card{
        public KelpieCard(){
        name = "Kelpie";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "On Summon: \n Increase the cost of \n all minions in hand \n by 2";
        intrinsicValue = 0; 
        sprite = SpriteHandler.kelpieCard;
        cost = 1;
        summon = new KelpieMinion(this);
    }
        @Override
        public void tick(){
            intrinsicValue = 0;
            for(Card c : owner.hand){
                if(c==this)continue;
                if(c.cardType==CardType.Minion){
                    if(c.canAfford() && c.cost+2 > owner.resource) {
                        this.intrinsicValue-=2;
                    } //higher penalty if the drawback makes a card unaffordable
                    this.intrinsicValue-=5;
                    
                }
            }
        }
        
}
