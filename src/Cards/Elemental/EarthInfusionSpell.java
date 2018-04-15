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
import Minions.Elemental.EarthGolemMinion;
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
public class EarthInfusionSpell extends Card{
    
    public EarthInfusionSpell() {
        name = "Earth Infusion";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = true;
        cardText = "Transform a Stone \n Golem into a 3/7 \n Earth Golem, which \n heals your hero \n for 2 whenever your \n hero is attacked";
        sprite = SpriteHandler.earthInfusionSpell;
        cost = 3;
        heroClass = HeroClass.Elemental;
    }

    @Override
    public int cast(Minion target){
        if(target==null || !target.name.equals("Stone Golem"))return -1;
        if(!canAfford())return 0;
        notifyPhantom(target,null);
        target.destroy();
        EarthGolemMinion egm = new EarthGolemMinion(this);
        owner.minions.add(egm);
        egm.proc();
        owner.hand.remove(this);
        owner.resource-=this.cost;
        if(target.isFrozen)egm.freeze();
        if(target.canAttack())egm.refresh();
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
                    bestValue = 11 - AI.AI.getWorth(m);
                    bestValue += owner.opponent.minions.getOccupants().size() * 3;
                }
            }
        }
        intrinsicValue+=bestValue;
    }
 
    @Override
    public void smartCast() {
        int bestValue = 0;
        Minion bestTarget = null;
        for (Minion m : owner.minions.getOccupants()) {
            if (m.name.equals("Stone Golem")) {
                if (bestTarget == null || AI.AI.getWorth(m) < AI.AI.getWorth(bestTarget)) {
                    bestTarget = m;
                   bestValue = AI.AI.getWorth(new SimulatedMinion(2, 7, owner)) + 1 - AI.AI.getWorth(m);
                    if (AI.AI.isHeroVulnerable(owner)) {
                        bestValue += owner.opponent.minions.getOccupants().size() * 3;
                    }
                }
            }
        }
        System.out.println("trying to cast earth infuse on " + bestTarget);
        cast(bestTarget);
    }
}
