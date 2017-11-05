/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import Minions.Minion;
import cardgame1.SpriteHandler;
import Minions.archerMinion;

/**
 *
 * @author Joseph
 */
public class ArcherCard extends Card{
    /* FIELDS  */
    int summonDamage = 1;
    
    public ArcherCard() {
        name = "Archer";
        cardType = CardType.Minion;
        isTargeted = true;
        cardText = "Deal " + summonDamage + " Damage";
        sprite = SpriteHandler.archerCard;
        cost = 1;
        summon = new archerMinion(this);
    }
    
    
    
    @Override
    public int cast(Minion target) {
        int outcome = defaultMinionSummon();
        if(outcome == 1){
            target.takeDamage(summonDamage);
        }
        return outcome;
    }
    
}
