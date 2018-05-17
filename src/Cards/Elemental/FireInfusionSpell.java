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
import Minions.Elemental.MoltenGolemMinion;
import Minions.Minion;
import Minions.Tribe;
import Traps.TrapListener;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class FireInfusionSpell extends Card {

    public FireInfusionSpell() {
        name = "Fire Infusion";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = true;
        helpText = "Fire Plume: Deal 3 Damage for 0 mana";
        cardText = "Transform a Stone \n Golem into a 5/4 \n Molten Golem \n with \"On Attack: \n Add a \"Fire Plume\" \n card to your hand\"";
        sprite = SpriteHandler.fireInfusionSpell;
        cost = 3;
        heroClass = HeroClass.Elemental;
    }
    
    @Override
    public int cast(Minion target){
        if(target==null || !target.name.equals("Stone Golem"))return -1;
        if(!canAfford())return 0;
        notifyPhantom(target,null);
        target.destroy();
        MoltenGolemMinion mgm = new MoltenGolemMinion(this);
        owner.minions.add(mgm);
        mgm.proc();
        owner.hand.remove(this);
        owner.resource-=this.cost;
        if(target.isFrozen)mgm.freeze();
        if(target.canAttack())mgm.refresh();
        TrapListener.onPlay(this);
        return 1;
    }
    
    @Override
    public int castOnHero(Hero h){
        return -1;
    }
    
    @Override
    public void tick(){
        intrinsicValue = 0;
        int bestValue = 0;
        Minion bestTarget = null;
        for(Minion m : owner.minions.getOccupants()){
            if(m.name.equals("Stone Golem")){
                if(bestTarget==null || AI.AI.getWorth(m)<AI.AI.getWorth(bestTarget)){
                    bestValue = AI.AI.getWorth(new SimulatedMinion(5,4,owner)) + 3 - AI.AI.getWorth(m);
                }
            }
        }
        intrinsicValue+=bestValue;
    }
    
    @Override
    public void smartCast(){
        int bestValue = 0;
        Minion bestTarget = null;
        for (Minion m : owner.minions.getOccupants()) {
            if (m.name.equals("Stone Golem")) {
                if (bestTarget == null || AI.AI.getWorth(m) < AI.AI.getWorth(bestTarget)) {
                    bestTarget = m;
                    bestValue = AI.AI.getWorth(new SimulatedMinion(5, 4, owner)) + 3 - AI.AI.getWorth(m);
                    if(!m.canAttack())bestValue-=3;
                }
            }
        }
        cast(bestTarget);
    }
}
