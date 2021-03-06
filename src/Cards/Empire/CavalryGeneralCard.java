/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Empire;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Empire.CavalryGeneralMinion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class CavalryGeneralCard extends Card {

    public CavalryGeneralCard() {
        name = "Cavalry General";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "On Turn Start: \n Expend a crystal.";
        sprite = SpriteHandler.cavalryGeneralCard;
        cost = 1;
        summon = new CavalryGeneralMinion(this);
        heroClass = HeroClass.Empire;
        intrinsicValue = 0;
    }
    
    @Override
    public void tick(){
        intrinsicValue = 0;
        for(Card c : owner.hand){
            if(c.cost==owner.maxResource+1){
            intrinsicValue-=2;
            }
        }
    }
}
