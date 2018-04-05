/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Base;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Base.UndyingSoldierMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class UndyingSoldierCard extends Card {

    public UndyingSoldierCard() {
        name = "Undying Soldier";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "On Death: \n Shuffle this card \n back into your deck.";
        intrinsicValue = 0;
        sprite = SpriteHandler.undeadSoldierCard;
        cost = 2;
        summon = new UndyingSoldierMinion(this);
    }

}
