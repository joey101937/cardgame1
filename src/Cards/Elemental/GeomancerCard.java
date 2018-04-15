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
import Minions.Elemental.GeomancerMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class GeomancerCard extends Card{
        public GeomancerCard() {
        name = "Geomancer";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "On Summon: \n Summon a friendly \n \"Stone Golem\"";
        sprite = SpriteHandler.geomancerCard;
        cost = 3;
        heroClass = HeroClass.Elemental;
        summon = new GeomancerMinion(this);
        intrinsicValue = 4;
    }
}
