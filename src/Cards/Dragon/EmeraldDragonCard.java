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
import Minions.Dragon.EmeraldDragonMinion;
import Minions.DragonInterface;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class EmeraldDragonCard extends Card{
      public EmeraldDragonCard() {
        summon = new EmeraldDragonMinion(this);
        name = "Emerald Dragon";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        DragonInterface di = (DragonInterface)summon;
        cardText = "On Turn End: \n Restore 3 health \n to your hero.";
        sprite = SpriteHandler.greenDragonCard;
        cost = 7;
        heroClass = HeroClass.Dragon;
    }
}
