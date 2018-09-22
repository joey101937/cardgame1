/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Undead;

import AI.AI;
import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Minion;
import Minions.Tribe;
import Traps.TrapListener;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Stickers.Sticker;

/**
 *
 * @author Joseph
 */
public class NecromancyCard extends Card {

    public NecromancyCard() {
        name = "Necromancy";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = true;
        cardText = "Take control of \n an enemy that \n has been turned \n undead by your \n card effect(s)";
        sprite = SpriteHandler.necromancyCard;
        cost = 2;
        heroClass = HeroClass.Undead;
    }
    
     @Override
    public int cast(Minion target) {
        if(target == null) return -1;
        if(target.hasTurnedUndead()==false) return -1;
        if(owner.minions.isFull()) return -1;
        if(!canAfford()) return 0; //reutrn 0 if unaffordable
        Sticker impactEffect = new Sticker(SpriteHandler.skullEffect, target, AI.speed/3);
        Main.wait(AI.speed/3);
        notifyPhantom(target,null);
        owner.opponent.minions.remove(target);
         target.owner = owner;
         target.parent.setHero(owner);
         owner.minions.add(target);
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
    
    @Override
    public void tick(){
        if(owner.minions.isFull()){
            intrinsicValue = -1;
            return;
        }
        int bestValue = -10;
        for(Minion m : owner.opponent.minions.getOccupants()){
            if(m.hasTurnedUndead()==false)continue;
            if(AI.getWorth(m) > bestValue){
                bestValue = AI.getWorth(m);
            }
        }
        intrinsicValue = bestValue;
    }
    
    @Override
    public void smartCast() {
        if (owner.minions.isFull()) {
            System.out.println("trying to play necromancy with full board");
            return;
        }
        Minion bestValue = null;
        for (Minion m : owner.opponent.minions.getOccupants()) {
            if (m.hasTurnedUndead() == false) {
                continue;
            }
            if (bestValue == null || AI.getWorth(bestValue) < AI.getWorth(m)) {
                bestValue = m;
            }
        }
        if (bestValue != null) {
            this.cast(bestValue);
        } else {
            System.out.println("trying to cast necromancy without valid target");
        }
    }

}
