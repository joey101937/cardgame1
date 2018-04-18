/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Base;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Base.MinotaurMinion;
import Minions.Minion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class MinotaurCard extends Card {

    public MinotaurCard() {
        name = "Minotaur";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        spellDamage = 3;
        cardText = "On Summon: \n Deal " + spellDamage + " to the minion \n directly across from \n this. (If exists)";
        sprite = SpriteHandler.minotaurCard;
        cost = 4;
        summon = new MinotaurMinion(this);
    }
    
    
    /**
     * index where the minotaur would be summoned
     *
     * @return
     */
    private int getOpenSlot() {
        for (int i = 0; i < 3; i++) {
            try {
                if (owner.minions.get(i) == null) {
                    return 0;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return -1;
    }

    
    @Override
    public void tick(){
        this.intrinsicValue=0;
        if(owner.opponent.minions.getOccupants().size()==0)return;
        if(getOpenSlot()==-1)return;
        Minion target = owner.opponent.minions.getStorage().get(getOpenSlot());
        if(target==null)return;
        this.intrinsicValue = AI.AI.getWorth(target)-AI.AI.getWorthAfterDamage(target, spellDamage);
    }
}
