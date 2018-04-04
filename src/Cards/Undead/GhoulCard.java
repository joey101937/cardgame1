/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Undead;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Undead.GhoulMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class GhoulCard extends Card {

    public GhoulCard() {
        name = "Ghoul";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "Minions damaged \n by this card \n become undead.";
        intrinsicValue = 0;
        sprite = SpriteHandler.ghoulCard;
        cost = 2;
        summon = new GhoulMinion(this);
        heroClass = HeroClass.Undead;
    }

}
