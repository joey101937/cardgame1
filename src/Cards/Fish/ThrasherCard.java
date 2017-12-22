/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Fish;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Fish.ThrasherMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class ThrasherCard extends Card {

    public ThrasherCard() {
        name = "Thrasher";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "At the end of \n your turn, attack \n a random enemy";
        sprite = SpriteHandler.thrasherCard;
        cost = 2;
        summon = new ThrasherMinion(this);
    }
}
