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
import Minions.DragonInterface;
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
        setCardText();
        sprite = SpriteHandler.dragonSoulSpell;
        cost = 1;
    }
    /**
     * sets card text to be card text of dragon that you cast it on, excluding the first line.
     * (usually a trigger effect like "on summon:"
     */
    private void setCardText(){
        cardText = "";
        String[] lines = host.parent.cardText.split(" \n ");
        for(int i = 1; i < lines.length; i ++){
            cardText += lines[i]+" \n ";
        }
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
        DragonInterface di = (DragonInterface)host;
        di.breath();
        owner.resource -= cost;
        TrapListener.onPlay(this);
        return 1;
    }

}
