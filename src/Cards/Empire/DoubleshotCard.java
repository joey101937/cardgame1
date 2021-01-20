/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Empire;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Minion;
import Multiplayer.Phantom;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class DoubleshotCard extends Card{
        public DoubleshotCard() {
        name = "Double Shot";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = false;
        spellDamage = 2;
        cardText = "Deal " + spellDamage +" damage to \n two random enemy \n minions";
        sprite = SpriteHandler.doubleshotCard;
        heroClass = HeroClass.Empire;
        cost = 3;
        intrinsicValue = -1; //we need to get at least 2 value out of this card in order for the ai to want to play it
    }
        
    @Override
    public void tick(){
        intrinsicValue = -3;
        if(owner.opponent.minions.getOccupants().size()==0)return;
        if(owner.opponent.minions.getOccupants().size()==1){
            if(owner.opponent.minions.getOccupants().get(0).health<=2){
                intrinsicValue = AI.AI.getWorth(owner.opponent.minions.getOccupants().get(0));
                return;
            }else{
                intrinsicValue = 1;
            }
        }else{
        int value = 0;
            for (Minion m : owner.opponent.minions.getOccupants()) {
                if (m.health <= spellDamage) {
                    value += AI.AI.getWorth(m)+1;
                } else {
                    value += 2;
                }
            }
            //reduce damage by avged chance based on something not getting hit
            if(owner.opponent.minions.getOccupants().size()>2){
                value -= value/owner.opponent.minions.getOccupants().size()+2;
            }     
            intrinsicValue = value;
        } 
        if(cost==owner.maxHealth)intrinsicValue ++;
    }
    @Override
    public int cast(Minion target){
        if(!canAfford())return 0;
        notifyPhantom(null,null);
        if(owner.opponent.minions.getOccupants().size()<=2){
            for(Minion m : owner.opponent.minions.getOccupants()){
                m.takeDamage(spellDamage);
            }
        }else{
            int t1, t2;
            t1 = (int)(Phantom.random.nextDouble()*owner.opponent.minions.getOccupants().size());
            owner.opponent.minions.getOccupants().get(t1).takeDamage(spellDamage);
            t2 = (int)(Phantom.random.nextDouble()*owner.opponent.minions.getOccupants().size());
            owner.opponent.minions.getOccupants().get(t2).takeDamage(spellDamage);
        }
        owner.resource-=cost;
        owner.hand.remove(this);
        return 1;
    }
    
    @Override
    public int castOnHero(Hero h){
    return cast(null);
    }
}
