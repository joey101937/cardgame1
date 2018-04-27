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
import Minions.Dragon.DragonTamerMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class DragonTamerCard extends Card{
        public DragonTamerCard() {
        name = "Dragon Tamer";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
         summon = new DragonTamerMinion(this);
        cardText = "On Summon: \n Summon a random \n Dragon Hatchling.";
        sprite = SpriteHandler.dragonTamerCard;
        cost = 3;
        heroClass = HeroClass.Dragon;
    }
}
