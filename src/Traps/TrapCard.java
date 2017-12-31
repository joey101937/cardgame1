/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Traps;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Minion;
import cardgame1.Hero;

/**
 *
 * @author Joseph
 */
public class TrapCard extends Card{
    public Trap myTrap; //the trap to put in play
    
    public TrapCard(){
        cardType = CardType.Spell;
        isTargeted = false;
        cardPurpose = CardPurpose.Trap;
    }
    
    /**
     * 0 for success
     * 1 for not cast for any reason
     */
    @Override
    public int cast(Minion target){
        return defaultTrapSummon();
    }
    /**
     * 1 for success
     * 0 for not cast for any reason
     * @param h
     * @return 
     */
    @Override
    public int castOnHero(Hero h){
        return defaultTrapSummon();
    }
    /**
     * default method for putting a trap into play
     * @return 1 if success, 0 if not
     */
    protected int defaultTrapSummon(){
        if(!canAfford())return 0;
        for(Trap t : owner.traps.getOccupants()){
            if(t.name.equals(this.name)) return 0; //no having two of the same trap out
        }
       if(owner.traps.add(myTrap)){
           myTrap.owner = owner;
           owner.resource-=cost;
           owner.hand.remove(this);
           TrapListener.onPlay(this);
           return 1; //if this code runs, the trap has been added successfully
       }else {
           System.out.println("could not add trap, likely overpopulated");
           return 0; //could not add trap for some reason
       } 
    }
    
    
    /**
     * same as regular can afford but returns false if there is already an active trap of this type in play
     * @return 
     */
    @Override
    public boolean canAfford(){
        for(Trap t : owner.traps.getOccupants()){
            if(t.name.equals(name)) {
                return false;
            }
        }
        return super.canAfford();
    }
    
}
