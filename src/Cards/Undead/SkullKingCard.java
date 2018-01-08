/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Undead;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Undead.SkullKingMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class SkullKingCard extends Card {

    public SkullKingCard() {
        name = "Skeleton King";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "Whenever this kills \n an enemy, it may \n attack again";
        intrinsicValue = 5; //ai need to know this is a strong card
        sprite = SpriteHandler.skullKingCard;
        cost = 7;
        summon = new SkullKingMinion(this);
    }
 
}
