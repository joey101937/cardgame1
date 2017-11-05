/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import Minions.FrostBearMinion;
import Minions.Minion;
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
        cardText = "";
        sprite = SpriteHandler.frostBearCard;
        cost = 2;
        summon = new FrostBearMinion(this);
    }

    @Override
    public int cast(Minion target) {
        return defaultMinionSummon();
    }


  
}
