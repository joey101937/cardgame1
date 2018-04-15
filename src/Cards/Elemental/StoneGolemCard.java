/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Elemental;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Elemental.StoneGolemMinion;
import Minions.Minion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class StoneGolemCard extends Card{
   
    public StoneGolemCard() {
        name = "Stone Golem";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "";
        sprite = SpriteHandler.stoneGolemCard;
        cost = 2;
        heroClass = HeroClass.Elemental;
        summon = new StoneGolemMinion(this);
    }
    
    @Override
    public void tick() {
        intrinsicValue = 1;
        for(Minion m : owner.minions.getOccupants()){
            if(m.name.equals("Stone Golem")){
                return; //dont increase value if we already have a stone golem in play
            }
        }
        for (Card c : owner.hand) {
            if(c==null || !c.canAfford())continue;
            switch (c.name) {
                case "Fire Infusion":
                case "Earth Infusion":
                case "Ice Infusion":
                    intrinsicValue += 5;
                    break;
                default:
                    break;
            }
        }
    }

}
