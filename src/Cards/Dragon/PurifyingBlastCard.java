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
import Minions.Minion;
import Traps.TrapListener;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Stickers.Sticker;

/**
 *
 * @author Joseph
 */
public class PurifyingBlastCard extends Card {

    public PurifyingBlastCard() {
        name = "Purifying Blast";
        cardType = CardType.Spell;
        heroClass = HeroClass.Restricted;
        cardPurpose = CardPurpose.Special;
        isTargeted = true;
        spellDamage = 99;
        cardText = "Destroy target \n minion.";
        sprite = SpriteHandler.purifyingBlastCard;
        cost = 2;

    }

    @Override
    public void tick() {
        intrinsicValue = 0;
        int bestValue = 0;
        Minion bestTarget = null;
        for (Minion m : owner.opponent.minions.getOccupants()) {
            int value = 0;
            if (AI.AI.getWorth(m) < 6) {
                continue; //must be at least a 3/3
            }
            if (AI.AI.getWorth(m) < 10) {
                //between 6 and 10 value on minion
                value = AI.AI.getWorth(m) - 3;
                if (value > bestValue) {
                    bestValue = value;
                    bestTarget = m;
                }
                if (AI.AI.getWorth(m) > 10) {
                    //more than a 5/5
                    value = AI.AI.getWorth(m);
                    if (value > bestValue) {
                        bestValue = value;
                        bestTarget = m;
                    }
                }
            }
        }
        this.intrinsicValue = bestValue;
    }

    @Override
    public void smartCast(){
                intrinsicValue = 0;
        int bestValue = -1;
        Minion bestTarget = null;
        for (Minion m : owner.opponent.minions.getOccupants()) {
            int value = 0;
            if (AI.AI.getWorth(m) < 6) {
                continue; //must be at least a 3/3
            }
            if (AI.AI.getWorth(m) < 10) {
                //between 6 and 10 value on minion
                value = AI.AI.getWorth(m) - 3;
                if (value > bestValue) {
                    bestValue = value;
                    bestTarget = m;
                }
                if (AI.AI.getWorth(m) > 10) {
                    //more than a 5/5
                    value = AI.AI.getWorth(m);
                    if (value > bestValue) {
                        bestValue = value;
                        bestTarget = m;
                    }
                }
            }
        }
        this.intrinsicValue = bestValue;
        cast(bestTarget);
    }
    
    
    /**
     * deals damage to target minion.
     *
     * @param target
     * @return 1 if success, 0 if too expensive, -1 if null param
     */
    @Override
    public int cast(Minion target) {
        if (target == null) {
            return -1;
        }
        if (!canAfford()) {
            return 0; //reutrn 0 if unaffordable
        }
        notifyPhantom(target, null);
        Sticker impactEffect = new Sticker(SpriteHandler.blastEffectSmall, target, AI.AI.speed / 3);
        Main.wait(AI.AI.speed / 3);
        target.takeDamage(target.health + 1);
        owner.resource -= cost;
        owner.hand.remove(this);
        TrapListener.onPlay(this);
        return 1;
    }

    @Override
    public int castOnHero(Hero target) {
        return -1;
    }

}
