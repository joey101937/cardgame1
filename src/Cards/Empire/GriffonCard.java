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
        cardText = "Can only attack \n if you control \n a knight";
        sprite = SpriteHandler.griffonCard;
        cost = 3;
        summon = new GriffonMinion(this);
        heroClass = HeroClass.Empire;
        intrinsicValue = 0;
    }
}
