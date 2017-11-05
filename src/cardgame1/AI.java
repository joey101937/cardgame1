/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import Cards.Card;
import Minions.Minion;

/**
 * Driver for computer players
 * @author Joseph
 */
public class AI {
    /* FIELDS     */
    Hero self;
    
    public AI(Hero host){
        self = host;
    }    
    
    /**
     * calculates the worth of a minion for purposes of making plays
     * @param m minion to evaluate
     * @return how much a minion is worth, higher is better
     */
    public static int getWorth(Minion m){
        int sumStats = m.health + m.attack; 
        if(m.health <= 0){
            return 0; //worthless if dead
        }
        return sumStats;
    }
    
    /**
     * finds what the worth of a minion will be after combat with another minion
     * @param attacker minion we are evaluating
     * @param defender supposed target
     * @return new worth of attacker
     */
    public static int getValueAfterCombat(Minion attacker, Minion defender){
        if((attacker.health - defender.attack) <= 0) return 0;  //if the minion would die from combat, its worth would become 0
        int sumNewStats = attacker.health - defender.attack + attacker.attack;
        return sumNewStats;
    }
    
    /**
     * gets the worth of a minion after taking damage
     * @param target minion to damage
     * @param damage amount of dmg
     * @return new worth
     */
    public static int getWorthAfterDamage(Minion target, int damage){
        if(target.health <= damage) return 0;
        return target.health - damage + target.attack;
    }
    
    /**
     * returns true if combat would reduce the defender's worth by more than it would reduce the attacker's worth
     * @param attacker
     * @param Defender
     * @return 
     */
    public static boolean isFavorableTrade(Minion attacker, Minion defender){
        int myDif = AI.getValueAfterCombat(attacker, defender) - AI.getWorth(attacker);
        int theirDif = AI.getValueAfterCombat(defender, attacker) - AI.getWorth(defender);
        return myDif < theirDif;
    }
    
    /**
     * checks to see if there is a favorable trade on the board for the minion m
     * @param m minion to evaluate
     * @return weather or not there is a favorable trade target on the board
     */
    public static boolean canTradeUp(Minion m){
        if(m.owner == Board.topHero){
            for(Minion t : Board.botHero.minions.getStorage()){
               if (t == null) continue;
               if(AI.getWorth(t) > AI.getWorth(m)){
                   //if the value of the target is greater than the value of this minion
                   if(isFavorableTrade(m,t)){
                       return true; //if the bigger minion is worth equal to or less than us after combat, we should make the trade
                   }
               }
            }
        }else{
            for(Minion t : Board.botHero.minions.getStorage()){
                if (t == null) continue;
               if(AI.getWorth(t) > AI.getWorth(m)){
                   //if the value of the target is greater than the value of this minion
                   if(isFavorableTrade(m,t)){
                       return true; //if the bigger minion is worth equal to or less than us after combat, we should make the trade
                   }
               }
            }
        }
        return false;
    }
    
    public static void tradeUp(Minion m){
        //TODO
    }
    
    /**
     * finds the minion with the highest worth for a particular hero
     * @param h hero to evaluate
     * @return biggest minion. null if none.
     */
    public static Minion getBiggestThreatOf(Hero h){
        Minion biggest = null;
        for(Minion t : h.minions.getStorage()){
            if(biggest == null || AI.getWorth(biggest) < AI.getWorth(t)){
                biggest = t;
            }
        }
        return biggest;
    }
    
    /**
     * is casting the spell c a good value?
     * @param c untargeted spell to cast
     * @return if the spell is a favorable thing to do
     */
    public static boolean isFavorableCast(Card c){
        int intrinsicValue = (c.cost*2) + 1;
        int rawGains = 0; //raw value it would provide
        if (c.getOwner() == Board.botHero){
                for (Minion t : Board.topHero.minions.getStorage()) {
                    rawGains += (AI.getWorth(t) - AI.getWorthAfterDamage(t, c.spellDamage));
                }
        }else{ //tophero
            for(Minion t : Board.botHero.minions.getStorage()){
                rawGains += (AI.getWorth(t) - AI.getWorthAfterDamage(t, c.spellDamage));
            }
        }
        return rawGains > intrinsicValue;
    }

}
