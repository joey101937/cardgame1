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
import Minions.Dragon.FaerieDragonMinion;
import Minions.DragonInterface;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class FaerieDragonCard extends Card{
     public FaerieDragonCard() {
        summon = new FaerieDragonMinion(this);
        name = "Faerie Dragon";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        DragonInterface di = (DragonInterface)summon;
        cardText = "On Summon: \n Destroy one of \n your opponent's \n mana crystals.";
        sprite = SpriteHandler.faerieDragonCard;
        cost = 7;
        heroClass = HeroClass.Dragon;
    }
}
