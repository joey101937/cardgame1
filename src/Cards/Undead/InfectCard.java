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
public class InfectCard extends Card{
        public InfectCard() {
        name = "Infect";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = true;
        damagesHeros = true;
        spellDamage = 2;
        helpText = "Turning something undead allows you to take control of it using the 'Necromancy' card";
        cardText = "Deal " + spellDamage + " damage. If \n target is a minion, \n it becomes undead.";
        sprite = SpriteHandler.infectCard;
        cost = 2;
        heroClass = HeroClass.Undead;
        intrinsicValue=-1; //so the ai will not use it so liberally
    }
     
     @Override
     public void tick(){
         intrinsicValue = -1;
         if(owner.opponent.minions.getOccupants().size()==0)return;
         int bestValue = -1;
         Minion bestTarget = null;
         boolean hasNecro = false;
         for(Card c : owner.hand){
             if(c.name.equals("Necromancy")){
                 hasNecro = true;
                 break;
             }
         }
         if(AI.AI.getBiggestThreatOf(owner.opponent).health >= 4 && hasNecro){
             int value = AI.AI.getWorth(AI.AI.getBiggestThreatOf(owner.opponent))-spellDamage;
             if(value > bestValue){
                bestValue = value;
                bestTarget = AI.AI.getBiggestThreatOf(owner.opponent);
             }
         }
         for(Minion m : owner.opponent.minions.getOccupants()){
             if(m.health<=spellDamage){
                 if(AI.AI.getWorth(m) > bestValue){
                     bestValue = AI.AI.getWorth(m);
                     bestTarget = m;
                 }
             }
         }
         intrinsicValue = bestValue; 
     }
        
     @Override
     public void smartCast(){
         if(owner.opponent.minions.getOccupants().size()==0)return;
         int bestValue = -1;
         Minion bestTarget = null;
         boolean hasNecro = false;
         for(Card c : owner.hand){
             if(c.name.equals("Necromancy")){
                 hasNecro = true;
                 break;
             }
         }
         if(AI.AI.getBiggestThreatOf(owner.opponent).health >= 4 && hasNecro){
             int value = AI.AI.getWorth(AI.AI.getBiggestThreatOf(owner.opponent))-spellDamage;
             if(value > bestValue){
                bestValue = value;
                bestTarget = AI.AI.getBiggestThreatOf(owner.opponent);
             }
         }
         for(Minion m : owner.opponent.minions.getOccupants()){
             if(m.health<=spellDamage){
                 if(AI.AI.getWorth(m) > bestValue){
                     bestValue = AI.AI.getWorth(m);
                     bestTarget = m;
                 }
             }
         }
         cast(bestTarget);
     }
        
     /**
     * deals damage to target minion.
     * @param target
     * @return 1 if success, 0 if too expensive, -1 if null param
     */
    @Override
    public int cast(Minion target) {
        if(target == null) return -1;
        if(!canAfford()) return 0; //reutrn 0 if unaffordable
        notifyPhantom(target,null);
        Sticker impactEffect = new Sticker(SpriteHandler.slashEffect, target, AI.AI.speed/3);
        Main.wait(AI.AI.speed/3);
        target.takeDamage(spellDamage);
        target.turnUndead();
        owner.resource -= cost;
        owner.hand.remove(this);
        TrapListener.onPlay(this);
        return 1;
    }
    
    /**
     * deals damage to target hero
     * @param target
     * @return 1 if success, 0 if too expensive, -1 if null param
     */
    @Override
    public int castOnHero(Hero target) {
        if(target == null) return -1;
        if(!canAfford()) return 0; //reutrn 0 if unaffordable
        notifyPhantom(null,target);
        Sticker impactEffect = new Sticker(SpriteHandler.slashEffect,target,AI.AI.speed/3);
        Main.wait(AI.AI.speed/3);
        target.takeDamage(spellDamage);
        owner.resource -= cost;
        owner.hand.remove(this);
        TrapListener.onPlay(this);
        return 1; 
    }
        
}
