/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Elemental;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Elemental.EarthElementalMinion;
import Minions.Elemental.RockMinion;
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
public class EarthElementalCard extends Card{
    
        public EarthElementalCard() {
        name = "Earth Elemental";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.Special;
        cardText = "On Summon: \n Target minion is \n transformed into a \n 0/3 rock";
        sprite = SpriteHandler.earthElementalCard;
        cost = 8;
        this.isTargeted = true;
        heroClass = HeroClass.Restricted;
        summon = new EarthElementalMinion(this);
    }

        
    @Override
    public int cast(Minion target) {
        int outcome = 0;
        if(!canAfford())return 0;
        if(owner.minions.add(summon)){
            notifyPhantom(target,null);
            owner.resource -= cost;
            owner.hand.remove(this);
            summon.onSummon();
            TrapListener.onPlay(this);
            outcome = 1;
            Sticker s = new Sticker(SpriteHandler.iconElemental,target,AI.AI.speed/3);
            Main.wait(AI.AI.speed/3);
            target.destroy();
            target.owner.minions.add(new RockMinion(target.parent));
        }     
        return outcome;
    }
    
    
    @Override
    public int castOnHero(Hero h){
        return -1;
    }

    @Override
    public void tick() {
        intrinsicValue = 0;
         if(owner.minions.isFull())return;
        int bestValue = 0;
        Minion bestTarget = null;
        for (Minion m : owner.opponent.minions.getOccupants()) {
            int value = AI.AI.getWorth(m);
            if (value >= bestValue) {
                bestValue = value;
                bestTarget = m;
            }
        }
        if (bestTarget != null) {
            intrinsicValue += bestValue + summon.attack + summon.health;
        }
    }
    
    @Override
    public void smartCast(){
        int bestValue = 0;
        Minion bestTarget = null;
        for (Minion m : owner.opponent.minions.getOccupants()) {
            int value = AI.AI.getWorth(m);
            if (value >= bestValue) {
                bestValue = value;
                bestTarget = m;
            }
        }
        cast(bestTarget);
    }
}
