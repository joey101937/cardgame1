/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import Minions.Minion;
import Minions.VengefullKnightMinion;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class VengefullKnightCard extends Card{

    public VengefullKnightCard() {
        name = "Vengefull Knight";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.ChargeMinion;
        cardText = "Charge";
        sprite = SpriteHandler.knightChargeCard;
        cost = 4;
        summon = new VengefullKnightMinion(this);
    }

    @Override
    public int cast(Minion target) {
        return defaultMinionSummon();
    }

    @Override
    public int castOnHero(Hero target) {
        return defaultMinionSummon();
    }
    
}
