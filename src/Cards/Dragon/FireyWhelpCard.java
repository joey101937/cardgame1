/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Dragon;

import AI.SimulatedMinion;
import Cards.Base.FireBoltCard;
import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Dragon.FireyWhelpMinion;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class FireyWhelpCard extends Card{
    public FireyWhelpCard() {
        name = "Firey Whelp";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "On Summon: \n Add a \"Fire Bolt\" \n card to your hand";
        sprite = SpriteHandler.fireyWhelpCard;
        cost = 2;
        summon = new FireyWhelpMinion(this);
        heroClass = HeroClass.Dragon;
    }

    @Override
    public void tick() {
        intrinsicValue = 0;
        if (owner.hand.size() == Hero.maxHandSize || (owner.hand.size() == Hero.maxHandSize-2 && owner.resource-cost == 0)) {
            //no extra value if hand is full or if playing this card would cause a full hand without the ability to play the firebolt immediately
            return;
        }
        if (AI.AI.canFavorablyTrade(new SimulatedMinion(2, 0, owner))) {
            intrinsicValue += AI.AI.getWorth(AI.AI.getBestTarget(new SimulatedMinion(2, 0, owner)));
        } else {
            intrinsicValue += 2;
        }
    }
}
