/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Dragon;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Dragon.DragonEggMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class DragonEggCard extends Card{
     public DragonEggCard() {
        summon = new DragonEggMinion(this);
        name = "Dragon Egg";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "On Death: \n Add a random \n dragon to your hand, \n it costs (2)";
        sprite = SpriteHandler.dragonEggCard;
        cost = 3;
        heroClass = HeroClass.Dragon;
    }
}
