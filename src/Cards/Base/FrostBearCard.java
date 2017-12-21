/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Base;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Base.FrostBearMinion;
import Minions.Minion;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 * vanilla midrange minion
 *
 * @author Joseph
 */
public class FrostBearCard extends Card {

    public FrostBearCard() {
        name = "Frost Bear";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "Cannot be frozen";
        sprite = SpriteHandler.frostBearCard;
        cost = 4;
        summon = new FrostBearMinion(this);
    }
  
}
