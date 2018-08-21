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
import Minions.Base.ArcherMinion;
import Traps.TrapListener;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class ArcherCard extends Card{
    
    public ArcherCard() {
        name = "Archer";
        cardType = CardType.Minion;
        spellDamage = 1;
        cardPurpose = CardPurpose.BattlecryMinionDamage;
        damagesHeros = true;
        isTargeted = true;
        cardText = "On Summon: \n Deal " + spellDamage + " Damage";
        sprite = SpriteHandler.archerCard;
        cost = 1;
        summon = new ArcherMinion(this);
    }
    
    
    
    @Override
    public int cast(Minion target) {
        //int outcome = defaultMinionSummon();
        int outcome = 0;
        if(!canAfford())return 0;
        if(owner.minions.add(summon)){
            notifyPhantom(target,null);
            owner.resource -= cost;
            owner.hand.remove(this);
            summon.onSummon();
            TrapListener.onPlay(this);
            outcome = 1;
            Sticker s = new Sticker(SpriteHandler.slashEffect,target,AI.AI.speed/3);
            Main.wait(AI.AI.speed/3);
            target.takeDamage(spellDamage);         
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
            target.takeDamage(spellDamage); 
        }
        return outcome;
    }
    
}
