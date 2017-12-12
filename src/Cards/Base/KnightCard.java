/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Base;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Base.KnightMinion;
import Minions.Minion;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class KnightCard extends Card{

    public KnightCard(){
        name = "Knight";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "";
        sprite = SpriteHandler.knightCard;
        cost = 2;
        summon = new KnightMinion(this);
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
