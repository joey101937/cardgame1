/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Fish;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Minion;
import Minions.Tribe;
import Traps.TrapListener;
import cardgame1.Hero;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class PredationCard extends Card {
    public PredationCard(){
        name = "Predation";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.DirectDamage;
        isTargeted = true;
        spellDamage = 0;
        cardText = "Deal Damage equal \n to the attack of \n your strongest fish \n (" + spellDamage +")";
        sprite = SpriteHandler.predationCard;
        cost = 2;
        heroClass = HeroClass.Ocean;
    }
    
    
    @Override
    public void tick(){
        int highest = 0;
        for(Minion m : owner.minions.getOccupants()){
            if(m.tribe==Tribe.Fish){
                if(m.attack>highest) highest = m.attack;
            }
        }
        spellDamage = highest;
        cardText = "Deal Damage equal \n to the attack of \n your strongest fish \n (" + spellDamage +")";
    }
    
    @Override
    public int cast(Minion target){
        if(target == null || spellDamage < 1) return -1;
        if(!canAfford()) return 0; //reutrn 0 if unaffordable
        notifyPhantom(target,null);
        target.takeDamage(spellDamage);
        Sticker impactEffect = new Sticker(SpriteHandler.bloodMedium,target,300);
        owner.resource -= cost;      
        owner.hand.remove(this);
        TrapListener.onPlay(this);
        return 1;
    }
    
    @Override
    public int castOnHero(Hero target){
        if(target == null || spellDamage < 1) return -1;
        if(!canAfford()) return 0; //reutrn 0 if unaffordable
        notifyPhantom(null,target);
        target.takeDamage(spellDamage);
        Sticker impactEffect = new Sticker(SpriteHandler.bloodMedium,target,300);
        owner.resource -= cost;
        owner.hand.remove(this);
        TrapListener.onPlay(this);
        return 1;
    }
}
