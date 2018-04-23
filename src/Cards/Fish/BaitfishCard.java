/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Fish;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Fish.BaitfishMinion;
import Minions.Minion;
import cardgame1.PlayArea;
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
        cardText = "On Death: \n Give a Friendly Fish \n +1/+1 and \n Return to Hand";
        intrinsicValue = 1; //more than a 0/2 to AI
        sprite = SpriteHandler.baitfishCard;
        cost = 1;
        summon = new BaitfishMinion(this);
        heroClass = HeroClass.Ocean;
    }
    
    @Override
    public void tick(){
        intrinsicValue = 1;
        if(owner.minions.numOccupants() == PlayArea.MAX_SIZE-1){
        for(Minion m : owner.minions.getOccupants()){
                if(!m.name.equals("Baitfish") || m.attack > 1){
                return;
                }
            }
            //3 of 4 boardslots are taken up with baitfish
            for(Card c : owner.hand){
                if(c.name.equals("frenzy"))return;
            }
            //only one slot left and we have all inactive baitfish so we cant play another
            intrinsicValue = -10;
        }
    }
    
}
