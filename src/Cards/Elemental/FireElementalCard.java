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
        spellDamage = 3;
        cardText = "On Turn End: \n Deal " + spellDamage + " Damage to a \n random enemy \n minion";
        sprite = SpriteHandler.fireElementalCard;
        cost = 8;
        heroClass = HeroClass.Restricted;
        summon = new FireElementalMinion(this);
        intrinsicValue = 4;
    }
    
    
    @Override
    public void tick() {
        intrinsicValue = 4;
        int damagePotential = 0;
        for(Minion m : owner.opponent.minions.getOccupants()){
            if(m.health<=spellDamage){
                damagePotential+=AI.AI.getWorth(m);
            }else{
                damagePotential+=spellDamage;
            }
        }
        this.intrinsicValue+=damagePotential;
    }
}
