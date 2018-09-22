/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Base;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Base.SandWormMinion;
import Minions.Minion;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Stickers.Sticker;

/**
 *
 * @author Joseph
 */
public class SandWormCard extends Card{
    
     public SandWormCard() {
        name = "Sand Worm";
        cardType = CardType.Minion;
        spellDamage = 2;
        cardPurpose = CardPurpose.Special;
        isTargeted = true;
        cardText = "On Summon: \n Deal " + spellDamage + " Damage to a \n friendly minion. If \n it dies, gain 2 health";
        sprite = SpriteHandler.sandWormCard;
        cost = 3;
        summon = new SandWormMinion(this);
    }
    
    @Override
    public void tick(){
        intrinsicValue = 0;
        Minion bestTarget = null;
        int bestValue = -9999;
        for(Minion m : owner.minions.getOccupants()){
            if(m.health <= 3){ //it would die
                int value = 2 - AI.AI.getWorth(m);
                if(value > bestValue){
                    bestValue = value;
                    bestTarget = m;
                }
            }else{
                //it would live
                int value = -2;
                 if(value > bestValue){
                    bestValue = value;
                    bestTarget = m;
                }
            }
        }
        if(bestTarget == null){
            intrinsicValue = -1; //no minions to cast on
            return;
        }else{
            intrinsicValue = bestValue + 6; //+6 for the 3/3 body
        }
    }
    
    @Override
    public void smartCast(){
                Minion bestTarget = null;
        int bestValue = -9999;
        for(Minion m : owner.minions.getOccupants()){
            if(m.health <= 3){ //it would die
                int value = 2 - AI.AI.getWorth(m);
                if(value > bestValue){
                    bestValue = value;
                    bestTarget = m;
                }
            }else{
                //it would live
                int value = -2;
                 if(value > bestValue){
                    bestValue = value;
                    bestTarget = m;
                }
            }
        }
        if(bestTarget == null){
            System.out.println("trying to play sand worm with no targets");
            return; //no targets
        }else{
            if(bestTarget.canAttack())bestTarget.attack(owner.opponent);
            cast(bestTarget);
            return;
        }
    }
    
    @Override
    public int cast(Minion target){
        if(owner.minions.isFull() || !canAfford())return 0;
        if(target == null || target.owner!=owner)return -1;
        notifyPhantom(target,null);
        new Sticker(SpriteHandler.bloodMedium,target,AI.AI.speed);
        Main.wait(AI.AI.speed);
        target.takeDamage(spellDamage);
        if(target.health<=0) {
            summon.health+=2;
            summon.maxHealth+=2;
        }
        return defaultMinionSummon();
    }
    
    @Override
    public int castOnHero(Hero h){
        return -1;
    }
}
