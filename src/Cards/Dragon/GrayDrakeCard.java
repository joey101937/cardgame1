/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Dragon;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Dragon.GrayDrakeMinion;
import Minions.Minion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class GrayDrakeCard extends Card{
       public GrayDrakeCard() {
        name = "Gray Drake";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "On Summon: \n Reduce a random \n enemy's attack\n by 2";
        sprite = SpriteHandler.grayDrakeCard;
        cost = 4;
        summon = new GrayDrakeMinion(this);
        heroClass = HeroClass.Dragon;
    }
       
    @Override
    public void tick() {
        intrinsicValue = 0;
        for (Minion m : owner.opponent.minions.getOccupants()) {
            if(m.attack>=2){
                intrinsicValue = 2;
                return;
            }
        }
    }
}
