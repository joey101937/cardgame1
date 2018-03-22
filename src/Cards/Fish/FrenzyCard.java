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
public class FrenzyCard extends Card {

    public FrenzyCard() {
        name = "Frenzy";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = false;
        spellDamage = 3;
        cardText = "Give all friendly \n Baitfish +3 attck";
        sprite = SpriteHandler.frenzyCard;
        cost = 4;
        heroClass = HeroClass.Ocean;
    }

    
    @Override
    public int cast(Minion target){
        if(canAfford()){
            for(Minion m : owner.minions.getOccupants()){
                if(m.name.equals("Baitfish")){
                    m.attack+=3; 
                    m.proc();
                }
            }
                owner.resource-=cost;
                owner.hand.remove(this);
                TrapListener.onPlay(this);
                return 1;
        }
        return 0;
    }
    
    @Override
    public int castOnHero(Hero target){
       return cast(null); 
    }
    
    @Override
    public int getValue(){
        int output = 0;
        for(Minion m : owner.minions.getOccupants()){
            if(m.name.equals("Baitfish")){
                output+=3;
            }
        }
        return output;
    }
}
