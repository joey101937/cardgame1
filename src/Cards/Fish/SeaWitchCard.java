/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Fish;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Fish.SeaWitchMinion;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class SeaWitchCard extends Card{
        public SeaWitchCard() {
        name = "Sea Witch";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "On Summon: \n Gain attack equal to \n the attack of your \n strongest fish (0)";
        sprite = SpriteHandler.seaWitchCard;
        cost = 4;
        summon = new SeaWitchMinion(this);
    }
        
        @Override
        public void tick(){
            intrinsicValue = 0;
            int value = 0;
            for(Minion m : owner.minions.getOccupants()){
                if(m.tribe==Tribe.Fish && m.attack > value){
                    value = m.attack;
                }
            }
            intrinsicValue = value;
            cardText = "On Summon: \n Gain attack equal to \n the attack of your \n strongest fish (" + value + ")" ;
        }
}
