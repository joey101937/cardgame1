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
import Minions.Fish.PredatoryFishMinion;
import Minions.Minion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class PredatoryFishCard extends Card{
      public PredatoryFishCard(){
        name = "Predatory Fish";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "On Summon: \n Kill all friendly \n Baitfish";
        intrinsicValue = 0;
        sprite = SpriteHandler.predatoryFishCard;
        cost = 1;
        summon = new PredatoryFishMinion(this);
        heroClass = HeroClass.Ocean;
    }
        
    @Override
    public void tick(){
        this.intrinsicValue = 0;
        for(Minion m : owner.minions.getOccupants()){
            if(m.name.equals("Baitfish")){
                this.intrinsicValue++;
                if(m.attack>1){                    
                    intrinsicValue-=(m.attack+m.health+1);
                }
            }
        }
    }
}
