/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Base;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Minion;
import cardgame1.SpriteHandler;
import Minions.Base.archerMinion;
import Traps.TrapListener;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.Sticker;

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
        cardPurpose = CardPurpose.BattlecryMinionDamage;
        isTargeted = true;
        cardText = "On Summon: \n Deal " + summonDamage + " Damage";
        sprite = SpriteHandler.archerCard;
        cost = 1;
        summon = new archerMinion(this);
    }
    
    
    
    @Override
    public int cast(Minion target) {
        //int outcome = defaultMinionSummon();
        int outcome = 0;
        if(!canAfford())return 0;
        if(owner.minions.add(summon)){
            notifyPhantom(target, null);
            owner.resource -= cost;
            owner.hand.remove(this);
            summon.onSummon();
            TrapListener.onPlay(this);
            outcome = 1;
        }
        if(outcome == 1){
            Sticker s = new Sticker(SpriteHandler.slashEffect,target,AI.AI.speed/3);
            Main.wait(AI.AI.speed/3);
            target.takeDamage(summonDamage);
            notifyPhantom(target,null);
        }
        return outcome;
    }

    @Override
    public int castOnHero(Hero target) {
        int outcome = 0;
        if (!canAfford()) {
            return 0;
        }
        if (owner.minions.add(summon)) {
             notifyPhantom(null, target);
            owner.resource -= cost;
            owner.hand.remove(this);
            summon.onSummon();
            TrapListener.onPlay(this);         
            outcome = 1;
        }
        if(outcome == 1){
            Sticker s = new Sticker(SpriteHandler.slashEffect,target,AI.AI.speed/3);
            Main.wait(AI.AI.speed/3);
            target.takeDamage(summonDamage);
            notifyPhantom(null,target);
        }
        return outcome;
    }
    
}
