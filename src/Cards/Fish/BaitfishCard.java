/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Fish;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Fish.BaitfishMinion;
import Minions.Minion;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class BaitfishCard extends Card{
    
    public BaitfishCard(){
         name = "Baitfish";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "On Death: \n Give Friendly Fish \n +1/+1 and \n return to hand";
        intrinsicValue = 2; //more than a 0/2 to AI
        sprite = SpriteHandler.baitfishCard;
        cost = 1;
        summon = new BaitfishMinion(this);
    }
    
    
    @Override
    public int cast(Minion target) {
        return defaultMinionSummon();
    }

    @Override
    public int castOnHero(Hero target) {
       return defaultMinionSummon();
    }
    
}
