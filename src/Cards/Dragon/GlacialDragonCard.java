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
import Minions.Dragon.GlacialDragonMinion;
import Minions.DragonInterface;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class GlacialDragonCard extends Card{
      public GlacialDragonCard() {
        summon = new GlacialDragonMinion(this);
        name = "Glacial Dragon";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        DragonInterface di = (DragonInterface)summon;
        cardText = "On Summon: \n Deal 3 damage and \n freeze a random \n enemy minion.";
        sprite = SpriteHandler.whiteDragonCard;
        cost = 8;
        heroClass = HeroClass.Dragon;
    }
}
