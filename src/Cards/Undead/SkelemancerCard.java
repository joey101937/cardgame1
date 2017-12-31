/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Undead;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Undead.SkelemancerMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class SkelemancerCard extends Card {

    public SkelemancerCard() {
        name = "Skelemancer";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "On Turn End: \n Summon a 2/1 \n skeleton";
        intrinsicValue = 3;
        sprite = SpriteHandler.skelemancerCard;
        cost = 5;
        summon = new SkelemancerMinion(this);
    }
}
