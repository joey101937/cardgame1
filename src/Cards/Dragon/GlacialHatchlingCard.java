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
import Minions.Dragon.GlacialHatchlingMinion;
import Minions.DragonInterface;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class GlacialHatchlingCard extends Card{
     public GlacialHatchlingCard() {
        summon = new GlacialHatchlingMinion(this);
        name = "Glacial Hatchling";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        DragonInterface di = (DragonInterface)summon;
        cardText = "After " + di.getTurnsRemaining() + "* turns, grow \n into a Glacial Dragon";
        sprite = SpriteHandler.whiteHatchlingCard;
        cost = 2;
        heroClass = HeroClass.Restricted;
        this.intrinsicValue = 1;
    }
     
}
