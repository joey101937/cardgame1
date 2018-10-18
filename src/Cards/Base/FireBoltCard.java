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
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class FireBoltCard extends Card{

        /*
      INHERITED FIELDS
    public String name;         //name of card
    public CardType cardType;   //type of card
    public String cardText;     //what the card says on it
    public BufferedImage sprite; //visual representation of the card
    public BoardSlot slot; //where on the board it is
     public Hero owner;
     */
    
    /*   FIELDS    */
    
    
    public FireBoltCard() {
        name = "FireBolt";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.DirectDamage;
        damagesHeros = true;
        isTargeted = true;
        spellDamage = 2;
        cardText = "Deal " + spellDamage + " damage to \n target minion";
        sprite = SpriteHandler.fireBoltCard;
        cost = 1;
        intrinsicValue=-1; //so the ai will not use it so liberally
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
