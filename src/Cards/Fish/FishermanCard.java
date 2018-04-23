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
import Traps.TrapListener;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class FishermanCard extends Card{
    
    public FishermanCard() {
        name = "Fisherman";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Draw;
        isTargeted = false;
        spellDamage = 1;
        heroClass = HeroClass.Ocean;
        cardText = "Add a Baitfish \n from your deck to \n your hand.";
        sprite = SpriteHandler.fishermanCard;
        cost = 0;
    }
    
      @Override
    public int cast(Minion m){
        if(!canAfford()) return 0;
        notifyPhantom(null,null);
        owner.hand.remove(this); //remove card first
        Card draw = null; //card we are drawing
        for(Card c : owner.deck){
            if(c.name.equals("Baitfish")){
                draw = c;
                break;
            }
        }
        if(draw != null){
            owner.deck.remove(draw);
            owner.draw(draw);
            owner.shuffle();
        }
        owner.resource -= cost;
        TrapListener.onPlay(this);
        return 1;
    }
    
    @Override
    public int castOnHero(Hero h){
        return cast(null);
    }
    
}
