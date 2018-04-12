/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Undead;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Minion;
import Traps.TrapListener;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;
import AI.*;
import CustomDecks.HeroClass;
import Minions.Tribe;
/**
 *
 * @author Joseph
 */
public class ZombieBiteSpell extends Card{
    private static int attack = 4;
    private static int health = 2;
    
      public ZombieBiteSpell() {
        name = "Zombie Bite";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = true;
        cardText = "Transform a minion \n into a 4/2 zombie \n with charge";
        sprite = SpriteHandler.zombieBiteSpell;
        cost = 2;
        heroClass = HeroClass.Undead;
    }
      
      
    @Override
    public int cast(Minion target) {
        if(target == null) return -1;
        if(!canAfford()) return 0; //reutrn 0 if unaffordable
        Sticker impactEffect = new Sticker(SpriteHandler.bloodMedium, target, AI.speed/3);
        Main.wait(AI.speed/3);
        
        target.name = "Zombie";
        target.tribe = Tribe.Undead;
        target.attack = attack;
        target.health = health;
        target.maxHealth = health;
        target.refresh();
        target.sprite = SpriteHandler.swampZombieMinion;
        target.turnUndead();
        notifyPhantom(target,null);
        owner.resource -= cost;
        owner.hand.remove(this);
        TrapListener.onPlay(this);
        return 1;
    }
    
    /**
     * does nothing. must cast on minion
     * @param target
     * @return 1 if success, 0 if too expensive, -1 if null param
     */
    @Override
    public int castOnHero(Hero target) {
        return -1;
    }
    
    /**
     * constantly updates value of the card for AI
     */
    @Override
    public void tick(){
        if(owner.minions.numOccupants() == 0 && owner.opponent.minions.numOccupants()==0){
            intrinsicValue = -2;
            return;
        }
        intrinsicValue = -2;
         int valueOnEnemy = 0;
        Minion bestTargetEnemy = null;
        for (Minion m : owner.opponent.minions.getOccupants()) {
            if (m.attack + m.health > (attack+health)) {
                int potentialValue = m.attack + m.health - (attack+health);
                if (potentialValue > valueOnEnemy) {
                    valueOnEnemy = potentialValue;
                    bestTargetEnemy = m;
                }
            }
        }
        if (valueOnEnemy > 1 && AI.isVulnerable(new SimulatedMinion(attack, health, owner.opponent))) {
            valueOnEnemy += 2;
        }
        int valueOnSelf = 0;
        Minion bestTargetSelf = null;
        for(Minion m : owner.minions.getOccupants()){
            if(m.attack+m.health < (attack+health)){
                int potentialValue = (attack+health) - m.attack - m.health;
                if(AI.isVulnerable(m) && !AI.isVulnerable(new SimulatedMinion(attack,health,owner))){
                    potentialValue = AI.getWorth(m);
                }
                if(potentialValue > valueOnSelf) {
                    valueOnSelf = potentialValue;
                    bestTargetSelf = m;
                }
            }
        }
        if(valueOnEnemy>valueOnSelf){
            intrinsicValue += valueOnEnemy;
        }else{
            intrinsicValue += valueOnSelf;
        }
    }
    /**
     * plays the card the best way possible
     */
    @Override
    public void smartCast(){
        int valueOnEnemy = 0;
        Minion bestTargetEnemy = null;
        for (Minion m : owner.opponent.minions.getOccupants()) {
            if (m.attack + m.health > (attack+health)) {
                int potentialValue = m.attack + m.health - (attack+health);
                if (potentialValue > valueOnEnemy) {
                    valueOnEnemy = potentialValue;
                    bestTargetEnemy = m;
                }
            }
        }
        if (valueOnEnemy > 1 && AI.isVulnerable(new SimulatedMinion(attack, health, owner.opponent))) {
            valueOnEnemy += 2;
        }
        int valueOnSelf = 0;
        Minion bestTargetSelf = null;
        for(Minion m : owner.minions.getOccupants()){
            if(m.attack+m.health < (attack+health)){
                int potentialValue = (attack+health) - m.attack - m.health;
                if((!AI.canFavorablyTrade(m) || !m.canAttack()) && AI.canFavorablyTrade(new SimulatedMinion(4,2,owner))){
                    potentialValue = AI.getWorth(AI.getBestTarget(new SimulatedMinion(4,2,owner)));
                }
                if(potentialValue > valueOnSelf) {
                    valueOnSelf = potentialValue;
                    bestTargetSelf = m;
                }
            }
        }
        if(valueOnEnemy>valueOnSelf){
            //better to cast on an enemy
            this.cast(bestTargetEnemy);
            return;
        }else{
            //better to cast on self
            this.cast(bestTargetSelf);
            return;
        }
    }


    
}
