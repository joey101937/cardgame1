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
import Traps.TrapListener;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class FireSpearCard extends Card {

    public FireSpearCard() {
        name = "Holy Spear";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = true;
        spellDamage = 0;
        cardText = "Target a minion with \n 5+ attack. Deal 5 \n Damage to it and \n reduce its attack \n by 4";
        sprite = SpriteHandler.fireSpearCard;
        cost = 3;
    }

    /**
     * deals damage to target minion.
     *
     * @param target
     * @return 1 if success, 0 if too expensive, -1 if null param
     */
    @Override
    public int cast(Minion target) {
        if (target == null || target.attack < 5) {
            return -1;
        }
        if (!canAfford()) {
            return 0; //reutrn 0 if unaffordable
        }
        notifyPhantom(target,null);
        Sticker impactEffect = new Sticker(SpriteHandler.blastEffectSmall, target, AI.AI.speed/2);
        Main.wait(AI.AI.speed/2);
        target.attack -= 4;
        target.takeDamage(5);
        owner.resource -= cost;
        owner.hand.remove(this);
        TrapListener.onPlay(this);
        return 1;
    }
    
    //cannot cast on heros
    @Override
    public int castOnHero(Hero h){
        return -1;
    }
    
    
    @Override
    public void tick(){
        intrinsicValue = - 2;
        int potential = 0;
        for(Minion m : owner.opponent.minions.getOccupants()){
            if(m.attack >= 5 && AI.AI.getWorth(m) > potential){
                potential = AI.AI.getWorth(m);
            }
        }
        intrinsicValue += potential;
    }
    
    @Override
    public void smartCast(){
        int bestvalue = -10;
        Minion bestTarget = null;
        for(Minion m : owner.opponent.minions.getOccupants()){
            if(m.attack >= 5 && AI.AI.getWorth(m) > bestvalue){
                bestvalue = AI.AI.getWorth(m);
                bestTarget = m;
            }
        }
        if(bestTarget == null) System.out.println("Error finding target for fire spear");
        this.cast(bestTarget);
    }
}