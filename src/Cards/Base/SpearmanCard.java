/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Base;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Base.SpearmanMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class SpearmanCard extends Card {

    public SpearmanCard() {
        name = "Spearman";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "";
        sprite = SpriteHandler.spearmanCard;
        cost = 3;
        summon = new SpearmanMinion(this);
    }
}
