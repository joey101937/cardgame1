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
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class SpellBookCard extends Card {

    public SpellBookCard() {
        name = "Spell Book";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Draw;
        isTargeted = false;
        spellDamage = 2;
        cardText = "Draw " + spellDamage + " Cards";
        sprite = SpriteHandler.spellBookCard;
        cost = 3;
    }
    
    @Override
    public int cast(Minion m){
        if(!canAfford()) return 0;
        for(int i =0 ; i < spellDamage; i++){
           owner.draw(); 
        }
        owner.resource -= cost;
        owner.hand.remove(this);
        TrapListener.onPlay(this);
        return 1;
    }
    
    @Override
    public int castOnHero(Hero h){
        return cast(null);
    }
}
