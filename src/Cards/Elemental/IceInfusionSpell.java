/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Elemental;

import AI.SimulatedMinion;
import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Elemental.IceGolemMinion;
import Minions.Minion;
import Traps.TrapListener;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class IceInfusionSpell extends Card{
     public IceInfusionSpell() {
        name = "Ice Infusion";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = true;
        cardText = "Transform a Stone \n Golem into a 3/7 \n Ice Golem, which \n freezes anything it \n damages";
        sprite = SpriteHandler.iceInfusionSpell;
        cost = 3;
        heroClass = HeroClass.Elemental;
    }
     
    @Override
    public int cast(Minion target){
        if(target==null || !target.name.equals("Stone Golem"))return -1;
        if(!canAfford())return 0;
        notifyPhantom(target,null);
        target.destroy();
        IceGolemMinion igm = new IceGolemMinion(this);
        owner.minions.add(igm);
        igm.proc();
        owner.hand.remove(this);
        owner.resource-=this.cost;
        if(target.isFrozen)igm.freeze();
        if(target.canAttack())igm.refresh();
        TrapListener.onPlay(this);
        return 1;
    }
    
        
    @Override
    public int castOnHero(Hero h){
        return -1;
    }

    @Override
    public void smartCast() {
        int bestValue = 0;
        Minion bestTarget = null;
        for (Minion m : owner.minions.getOccupants()) {
            if (m.name.equals("Stone Golem")) {
                if (bestTarget == null || AI.AI.getWorth(m) < AI.AI.getWorth(bestTarget)) {
                    bestTarget = m;
                    bestValue = AI.AI.getWorth(new SimulatedMinion(3, 6, owner)) + 3 - AI.AI.getWorth(m);
                }
            }
        }
        System.out.println("trying to cast ice infuse on " + bestTarget);
        cast(bestTarget);
    }
    
    @Override
    public void tick(){
        intrinsicValue = 0;
                int bestValue = 0;
        Minion bestTarget = null;
        for (Minion m : owner.minions.getOccupants()) {
            if (m.name.equals("Stone Golem")) {
                if (bestTarget == null || AI.AI.getWorth(m) < AI.AI.getWorth(bestTarget)) {
                    bestValue = AI.AI.getWorth(new SimulatedMinion(3, 6, owner)) + 3 - AI.AI.getWorth(m);
                }
            }
        }
        intrinsicValue+=bestValue;
    }
}
