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
import Minions.Fish.SeaSerpentMinion;
import cardgame1.SpriteHandler;

/**
 * this is the card reward from completing the trap quest
 * @author Joseph
 */
public class SeaSerpentCard extends Card {

    public SeaSerpentCard() {
        name = "Sea Serpent";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        spellDamage = 1;
        cardText = "On Summon: \n Deal " + spellDamage + " damage to \n all non-fish minions";
        sprite = SpriteHandler.seaSerphantCard;
        cost = 1;
        summon = new SeaSerpentMinion(this);
        heroClass = HeroClass.Restricted;
    }
}
