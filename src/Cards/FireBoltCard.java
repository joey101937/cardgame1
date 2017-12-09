/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import Minions.Minion;
import cardgame1.Hero;
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
        isTargeted = true;
        spellDamage = 2;
        cardText = "Deal " + spellDamage + " damage to \n target minion";
        sprite = SpriteHandler.fireBoltCard;
        cost = 1;
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
        target.takeDamage(spellDamage);
        Sticker impactEffect = new Sticker(SpriteHandler.blastEffectSmall,target,20);
        owner.resource -= cost;
        owner.hand.remove(this);
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
        target.takeDamage(spellDamage);
        Sticker impactEffect = new Sticker(SpriteHandler.blastEffectSmall,target,20);
        owner.resource -= cost;
        owner.hand.remove(this);
        return 1; 
    }
}
