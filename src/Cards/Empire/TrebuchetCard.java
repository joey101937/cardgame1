/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Empire;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Empire.TrebuchetMinion;
import Minions.Minion;
import cardgame1.SpriteHandler;
/**
 *
 * @author Joseph
 */
public class TrebuchetCard extends Card{
     public TrebuchetCard() {
        name = "Trebuchet";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        heroClass = HeroClass.Empire;
        spellDamage = 3;
        cardText = "On Turn End: \n Deal " + spellDamage + " to the minion \n directly across from \n this. (If exists)";
        sprite = SpriteHandler.trebuchetCard;
        cost = 7;
        summon = new TrebuchetMinion(this);
    }
     
     /**
     * index where the minion would be summoned
     * @return -1 if full, otherwise index to be added to
     */
    private int getOpenSlot() {
        for (int i = 0; i < 4; i++) {
            try {
                if (owner.minions.get(i) == null) {
                    return i;
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
        if(owner.opponent.minions.getOccupants().isEmpty()) {
            intrinsicValue = 19;
            return;
        }
        if(getOpenSlot()==-1)return;
        Minion target = owner.opponent.minions.getStorage().get(getOpenSlot());
        if(target==null)return;
        this.intrinsicValue = AI.AI.getWorth(target)-AI.AI.getWorthAfterDamage(target, spellDamage) + 8;
    }
    
}
