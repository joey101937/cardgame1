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
import Minions.Dragon.GoldenDragonMinion;
import Minions.DragonInterface;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class GoldenDragonCard extends Card{
      public GoldenDragonCard() {
        summon = new GoldenDragonMinion(this);
        name = "Golden Dragon";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        DragonInterface di = (DragonInterface)summon;
        cardText = "On Summon: \n Add a \"Purifying \n Impact\" card to \n your hand.";
        helpText = "Purifying Impact: 2-cost spell, \"Destroy Target Minion\"";
        sprite = SpriteHandler.goldDragonCard;
        cost = 9;
        heroClass = HeroClass.Dragon;
    }
}
