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
import cardgame1.Stickers.Sticker;

/**
 *
 * @author Joseph
 */
public class VolcanoCard extends Card{
        public VolcanoCard() {
        name = "Volcano";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.AOEDamage;
        isTargeted = false;
        spellDamage = 2;
        cardText = "Deal " + spellDamage + " Damage to \n All Enemy Minions";
        sprite = SpriteHandler.volcanoCard;
        cost = 4;
        intrinsicValue = -2; //we need to get at least 3 value out of this card in order for the ai to want to play it
    }
        
        
        /**
     * deals damage to target minion.
     * @param t
     * @return 1 if success, 0 if too expensive, -1 if opponent has no minions
     */
    @Override
    public int cast(Minion t) {
        if(owner.opponent.minions.numOccupants() == 0) return -1;
        if(!canAfford()) return 0; //reutrn 0 if unaffordable
        notifyPhantom(null,null);
        for(Minion target : owner.opponent.minions.getOccupants()){
            Sticker impactEffect = new Sticker(SpriteHandler.blastEffectSmall,target,AI.AI.speed/3);
        } //first put the fire sticker on all targets, then apply damage. easier to follow this way.
        Main.wait(AI.AI.speed/3);
        for(Minion target : owner.opponent.minions.getOccupants()){
            target.takeDamage(spellDamage);
        }        
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
        return cast(null);
    }

}
