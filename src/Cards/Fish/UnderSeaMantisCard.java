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
import Minions.Fish.UnderSeaMantisMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class UnderSeaMantisCard extends Card{
     public UnderSeaMantisCard(){
        name = "Undersea Mantis";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "Whenever this \n attacks a minion, \n deal 4 damage to the \n opponent's hero.";
        intrinsicValue = 0;
        sprite = SpriteHandler.underSeaMantisCard;
        cost = 4;
        summon = new UnderSeaMantisMinion(this);
        heroClass = HeroClass.Ocean;
    }
}
