/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Elemental;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Elemental.StoneElementalMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class StoneElementalCard extends Card {

    public StoneElementalCard() {
        name = "Stone Elemental";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "";
        sprite = SpriteHandler.stoneElementalCard;
        cost = 3;
        heroClass = HeroClass.Restricted;
        summon = new StoneElementalMinion(this);
    }
}
