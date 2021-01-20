/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Empire;

import Cards.Base.KnightCard;
import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Minion;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class ReservesCard extends Card{
        public ReservesCard() {
        name = "Reserves";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = false;
        spellDamage = 0;
        cardText = "Add two 3/2 \"Knight\" \n cards to your hand.";
        sprite = SpriteHandler.vigorCard;
        heroClass = HeroClass.Empire;
        cost = 2;
        intrinsicValue = 1; //we need to get at least 2 value out of this card in order for the ai to want to play it
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
    public int cast(Minion m) {
        if(!canAfford())return 0;
        notifyPhantom(null,null);
        owner.hand.remove(this);
        owner.resource -= cost;
        for (int i = 0; i < 2; i++) {
            Card toAdd = new KnightCard();
            toAdd.setHero(owner);
            owner.hand.add(toAdd);
        }
        return 0;
    }
    
    @Override
    public void tick() {
        boolean roomInHand = owner.hand.size() < 5;
        boolean boardLarge = owner.minions.getOccupants().size() > 2;
        if(!roomInHand) intrinsicValue = 0;
        else {
            if(boardLarge) {
                intrinsicValue = 5;
            } else {
                intrinsicValue = 2;
            }
        }
    }
}
