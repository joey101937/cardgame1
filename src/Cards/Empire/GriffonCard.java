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
import Minions.Empire.GriffonMinion;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class GriffonCard extends Card {

    public GriffonCard() {
        name = "Griffon";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "On Summon: \n Gain charge if you \n control a knight";
        sprite = SpriteHandler.griffonCard;
        cost = 6;
        summon = new GriffonMinion(this);
        heroClass = HeroClass.Empire;
        intrinsicValue = summon.intrinsicValue;
    }
    @Override
    public void tick(){
        this.cardPurpose = CardPurpose.VanillaMinion;
        for(Minion m : owner.minions.getOccupants()){
            if(m.tribe==Tribe.Knight){
                this.cardPurpose = CardPurpose.ChargeMinion;
                return;
            }
        }
    }
}
