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
import Minions.Dragon.FaerieHatchlingMinion;
import Minions.DragonInterface;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class FaerieHatchlingCard extends Card{
         public FaerieHatchlingCard() {
        summon = new FaerieHatchlingMinion(this);
        name = "Faerie Hatchling";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        DragonInterface di = (DragonInterface)summon;
        cardText = "After " + di.getTurnsRemaining() + "* turns, grow \n into a Faerie Dragon";
        sprite = SpriteHandler.faerieHatchlingCard;
        cost = 2;
        heroClass = HeroClass.Restricted;
        this.intrinsicValue = 1;
    }
}
