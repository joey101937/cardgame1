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
public class IceyWindCard extends Card {

    public IceyWindCard() {
        name = "Icey Wind";
        cardType = CardType.Spell;
        heroClass = HeroClass.Restricted;
        cardPurpose = CardPurpose.DirectDamage;
        isTargeted = true;
        spellDamage = 1;
        cardText = "Freeze target \n minion";
        sprite = SpriteHandler.iceyWindCard;
        cost = 0;
    }
    
    @Override
    public int castOnHero(Hero h){
    return -1;
    }
    
    @Override
    public int cast(Minion target){
        if(target == null) return -1;
        if(!canAfford()) return 0; //reutrn 0 if unaffordable
        notifyPhantom(target,null);
        Sticker impactEffect = new Sticker(SpriteHandler.snowflakeLarge, target, AI.AI.speed/3);
        Main.wait(AI.AI.speed/3);
        target.freeze();
        owner.resource -= cost;
        owner.hand.remove(this);
        TrapListener.onPlay(this);
        return 1;
    }
    
}
