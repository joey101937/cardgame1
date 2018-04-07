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
import Traps.TrapListener;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class FirePlumeCard extends Card{
        public FirePlumeCard() {
        name = "Fire Plume";
        cardType = CardType.Spell;
        heroClass = HeroClass.Restricted;
        cardPurpose = CardPurpose.DirectDamage;
        isTargeted = true;
        spellDamage = 3;
        cardText = "Deal " + spellDamage + " damage to \n target";
        sprite = SpriteHandler.firePlumeCard;
        cost = 0;
    }
    
    /**
     * deals damage to target minion.
     * @param target
     * @return 1 if success, 0 if too expensive, -1 if null param
     */
    @Override
    public int cast(Minion target) {
        if(target == null) return -1;
        if(!canAfford()) return 0; //reutrn 0 if unaffordable
        notifyPhantom(target,null);
        Sticker impactEffect = new Sticker(SpriteHandler.blastEffectSmall, target, AI.AI.speed/3);
        Main.wait(AI.AI.speed/3);
        target.takeDamage(spellDamage);
        owner.resource -= cost;
        owner.hand.remove(this);
        TrapListener.onPlay(this);
        return 1;
    }
    
    /**
     * deals damage to target hero
     * @param target
     * @return 1 if success, 0 if too expensive, -1 if null param
     */
    @Override
    public int castOnHero(Hero target) {
        if(target == null) return -1;
        if(!canAfford()) return 0; //reutrn 0 if unaffordable
        notifyPhantom(null,target);
        Sticker impactEffect = new Sticker(SpriteHandler.blastEffectSmall,target,AI.AI.speed/3);
        Main.wait(AI.AI.speed/3);
        target.takeDamage(spellDamage);
        owner.resource -= cost;
        owner.hand.remove(this);
        TrapListener.onPlay(this);
        return 1; 
    }
}
