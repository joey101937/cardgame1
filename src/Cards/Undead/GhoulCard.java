/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Undead;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Undead.ZombieMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class GhoulCard extends Card {

    public GhoulCard() {
        name = "Ghoul";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "Minions attacked \n by this card \n become undead.";
        intrinsicValue = 0;
        sprite = SpriteHandler.ghoulCard;
        cost = 2;
        summon = new ZombieMinion(this);
        heroClass = HeroClass.Undead;
    }

}
