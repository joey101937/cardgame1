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
import Minions.Elemental.FireElementalMinion;
import Minions.Minion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class FireElementalCard extends Card {

    public FireElementalCard() {
        name = "Fire Elemental";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        spellDamage = 5;
        cardText = "On Summon: \n Deal " + spellDamage + " damage to \n enemy hero.";
        sprite = SpriteHandler.fireElementalCard;
        cost = 6;
        heroClass = HeroClass.Restricted;
        summon = new FireElementalMinion(this);
        intrinsicValue = 4;
    }
    
    
    @Override
    public void tick() {
        intrinsicValue = 4;
        int damagePotential = 0;
        for (Minion m : owner.opponent.minions.getStorage()) {
            if (m == null) {
                continue;
            }
            damagePotential += m.attack;
        }
        if(damagePotential+spellDamage >= owner.opponent.health){
        intrinsicValue = 10;
        }
    }
}
