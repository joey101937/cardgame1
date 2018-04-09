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
import cardgame1.SpriteHandler;

/**
 * This card is created via the dragon soul trap card. it is a card that activates the on summon effect of the minion that triggerd the trap
 * @author Joseph
 */
public class DragonSoulSpell extends Card {
    Minion host = null;
    public DragonSoulSpell(Minion m) {
        host = m;
        name = m.name.split(" ")[0] + " Soul";
        cardType = CardType.Spell;
        heroClass = HeroClass.Restricted;
        cardPurpose = CardPurpose.Special;
        isTargeted = false;
        spellDamage = 0;
        cardText = m.parent.cardText.substring(13); //get battlecry description
        sprite = SpriteHandler.dragonSoulSpell;
        cost = 1;
    }
    
    @Override
    public void tick(){
        host.parent.tick();
        this.intrinsicValue = host.parent.intrinsicValue;
    }
    
    @Override
    public void smartCast(){
        cast(null);
    }
    
    @Override
    public int castOnHero(Hero h){
        return -1;
    }
    @Override
    public int cast(Minion m){
        if(!canAfford())return 0;
         notifyPhantom(null,null);
        owner.hand.remove(this);
        host.onSummon();
        owner.resource -= cost;
        TrapListener.onPlay(this);
        return 1;
    }

}
