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
import Minions.Undead.DarkClericMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class DarkClericCard extends Card{
     public DarkClericCard() {
        name = "Dark Cleric";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "Destroy any undead \n that this battles";
        intrinsicValue = 1;
        sprite = SpriteHandler.darkClericCard;
        cost = 3;
        summon = new DarkClericMinion(this);
        heroClass = HeroClass.Undead;
    }
}
