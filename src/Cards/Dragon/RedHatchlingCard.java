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
import Minions.Dragon.RedHatchlingMinion;
import Minions.DragonInterface;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class RedHatchlingCard extends Card{
    public RedHatchlingCard() {
        summon = new RedHatchlingMinion(this);
        name = "Red Hatchling";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        DragonInterface di = (DragonInterface)summon;
        cardText = "After " + di.getTurnsRemaining() + "* turns, grow \n into a Red Dragon";
        sprite = SpriteHandler.redHatchlingCard;
        cost = 2;
        heroClass = HeroClass.Restricted;
        this.intrinsicValue = 1;
    }
}
