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
import Minions.Dragon.RedDragonMinion;
import Minions.DragonInterface;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class RedDragonCard extends Card{
     public RedDragonCard() {
        summon = new RedDragonMinion(this);
        name = "Red Dragon";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        DragonInterface di = (DragonInterface)summon;
        cardText = "On Summon: \n Deal 1 damage to \n all enemy minions.";
        sprite = SpriteHandler.redDragonCard;
        cost = 8;
        spellDamage = 1;
        heroClass = HeroClass.Dragon;
    }
}
