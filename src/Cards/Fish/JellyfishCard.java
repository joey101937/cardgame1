/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Fish;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Fish.JellyfishMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class JellyfishCard extends Card{
        public JellyfishCard() {
        name = "Jellyfish";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
       // cardText = "When this damages \n another minion, \n reduce its attack \n by 1";
        //cardText = "On Summon: \n Freeze a random \n enemy";
        cardText = "";
        this.intrinsicValue = 1;
        sprite = SpriteHandler.jellyfishCard;
        cost = 3;
        summon = new JellyfishMinion(this);
        heroClass = HeroClass.Ocean;
    }
}
