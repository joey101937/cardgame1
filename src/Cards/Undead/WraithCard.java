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
import Minions.Undead.WraithMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class WraithCard extends Card {

    public WraithCard() {
        name = "Wraith";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "Whenever a friendly \n knight dies, raise \n it as a zombie";
        sprite = SpriteHandler.wraithCard;
        cost = 4;
        summon = new WraithMinion(this);
        heroClass = HeroClass.Undead;
    }

    @Override
    public void tick() {
        summon.tick();
    }
    
}
