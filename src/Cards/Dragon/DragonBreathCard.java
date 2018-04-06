/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Dragon;

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
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class DragonBreathCard extends Card {

    public DragonBreathCard() {
        name = "Dragon's Breath";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = true;
        spellDamage = 0;
        cardText = "Activate a dragon's \n On-Summon effect";
        sprite = SpriteHandler.dragonBreathCard;
        cost = 2;
        heroClass = HeroClass.Dragon;
    }
    
    @Override
    public void smartCast(){
        int bestVal=0;
        Minion bestTarget = null;
        for(Minion m : owner.minions.getOccupants()){
            if(m.tribe!=Tribe.Dragon)continue;
            if(m.parent.intrinsicValue>bestVal){
                bestVal = m.parent.intrinsicValue;
                bestTarget = m;
            }
        }
        cast(bestTarget);
    }
    
    @Override
    public void tick(){
        intrinsicValue = 0;
        int bestVal=0;
        Minion bestTarget = null;
        for(Minion m : owner.minions.getOccupants()){
            if(m.tribe!=Tribe.Dragon)continue;
            if(m.parent.intrinsicValue>bestVal){
                m.parent.tick();
                bestVal = m.parent.intrinsicValue;
                bestTarget = m;
            }
        }
        if(bestTarget!=null){
            intrinsicValue = bestVal;
        }
    }
    
    @Override
    public int cast(Minion target){
        if(target == null || target.tribe != Tribe.Dragon)return -1;
        if(!canAfford())return 0;
        Sticker impactEffect = new Sticker(SpriteHandler.iconDragon, target, AI.AI.speed / 2);
        Main.wait(AI.AI.speed / 2);
        target.onSummon();
        owner.resource -= cost;
        notifyPhantom(target,null);
        owner.hand.remove(this);
        TrapListener.onPlay(this);
        return 1;
    }

    @Override
    public int castOnHero(Hero h){
        System.out.println("tried to play dragon breath on a hero");
        return -1;
    }
}
