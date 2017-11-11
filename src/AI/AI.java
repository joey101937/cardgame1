/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Cards.Card;
import Minions.Minion;
import cardgame1.Board;
import cardgame1.Hero;
import java.util.ArrayList;

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
   
    /**
     * returns the best target for the given minion
     * @param attacker
     * @return 
     */
    public static Minion getBestTarget(Minion attacker){
        //TODO
        return null;
    }
    
    /**
     * gets the value presented by making the attacker attack the defneder. May be negative, higher is better.
     * @param attacker minion that we are using
     * @param defender potential target
     * @return how good of an idea it is to attack
     */
    public static int getTradeValue(Minion attacker, Minion defender){
        int myPreviousValue = AI.getWorth(attacker);
        int theirPreviousValue = AI.getWorth(defender);
        int myNewValue = AI.getValueAfterCombat(attacker, defender);
        int theirNewValue = AI.getValueAfterCombat(defender, attacker);
        int ValueGained = theirPreviousValue-theirNewValue - myPreviousValue-myNewValue;
        return ValueGained;
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
    /**
     * gets the total value of all minions in an arraylist
     * @param input list to evaluate
     * @return sum of all minion's worths
     */
    public static int getWorthOfBoard(ArrayList<Minion> input){
        int output = 0;
        for(Minion m : input){
            if(m != null) output += AI.getWorth(m);
        }
        return output;
    }
    
    /**
     * gets the value that playing a card represents
     * NOT DONE
     * @param c card to evaluate
     * @return the value playing the card presents
     */
    public static int getValueOfCard(Card c){
        int output = 0;
        ArrayList<SimulatedMinion> myMinions = new ArrayList<>();
        ArrayList<SimulatedMinion> enemyMinions = new ArrayList<>();
        if(Board.topHero == c.getOwner()){
            for(Minion m : Board.topHero.minions.getStorage()){
                myMinions.add(new SimulatedMinion(m)); //tophero's card so popualte friendly vs enemy lists accordingly
            }
            for(Minion m : Board.botHero.minions.getStorage()){
                enemyMinions.add(new SimulatedMinion(m));
            }
        }else if(Board.botHero == c.getOwner()){ //bot hero's card so populate enemy vs friendly list accordingly
            for(Minion m : Board.botHero.minions.getStorage()){
                myMinions.add(new SimulatedMinion(m));
            }
            for(Minion m : Board.topHero.minions.getStorage()){
                enemyMinions.add(new SimulatedMinion(m));
            }
        }else{
            //card belongs to neither hero
            System.out.println("ERROR CANNOT FIND VALUE OF CARD WITH UNKNOWN ERROR: " + c.toString());
            return -1;
        }
        //at this point, both arrays are populated
        //TODO evaluate value based on what the card is supposed to do
        switch(c.cardPurpose){
            case VanillaMinion:
                int value = c.summon.attack + c.summon.health;
                if(c.getOwner().minions.isFull()) return 0; //if there is no place to summon the minion, it has 0 value.
                for(Minion enemy : enemyMinions){
                    if(AI.isFavorableTrade(enemy, c.summon)) value --; //reducde value slightly if the minion would be favorably traded against
                }
                return value;
            case BattlecryMinion:
                if(c.getOwner().minions.isFull()) return 0; //if there is no place to summon the minion, it has 0 value.
                break;
            case AOEDamage:
                break;
            case DirectDamage:
                break;
            case Debuff:
                break;
            case AOEHeal:
                break;
            case DirectHeal:
                break;
            case Buff:
                break;
        }
        return output;
    }
}
