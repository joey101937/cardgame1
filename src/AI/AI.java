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
    Hero enemy;
    public AI(Hero host){
        self = host;
        if(self == Board.botHero){
            enemy = Board.topHero;
        }
        if(self == Board.topHero){
            enemy = Board.botHero;
        }
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
    public static int getWorthAfterCombat(Minion attacker, Minion defender){
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
        int myDif = AI.getWorthAfterCombat(attacker, defender) - AI.getWorth(attacker);
        int theirDif = AI.getWorthAfterCombat(defender, attacker) - AI.getWorth(defender);
        return myDif > theirDif;
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
        int bestValue = -99;
        Minion bestTarget = null;
        if(attacker.owner == Board.topHero){
            for(Minion m : Board.botHero.minions.getStorage()){
                if(m == null) continue;
                if(getTradeValue(attacker, m) > bestValue){
                    bestValue = getTradeValue(attacker,m);
                    bestTarget = m;
                }
            }
        }else{
           for(Minion m : Board.topHero.minions.getStorage()){
                if(m == null) continue;
                if(getTradeValue(attacker, m) > bestValue){
                    bestValue = getTradeValue(attacker,m);
                    bestTarget = m;
                }
            } 
        }
        return bestTarget;
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
        int myNewValue = AI.getWorthAfterCombat(attacker, defender);
        int theirNewValue = AI.getWorthAfterCombat(defender, attacker);
        int ValueGained = (theirPreviousValue-theirNewValue) - (myPreviousValue-myNewValue);
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
     * if the minion can be traded favorably against by the opponent
     * @param m
     * @return 
     */
    public static boolean isVulnerable(Minion m){
        if(m.owner == Board.topHero){
            for(Minion min : Board.botHero.minions.getStorage()){
                if(min == null)continue;
               if(AI.isFavorableTrade(min, m)){
                   return true;
               }
            }
        }else{
               for (Minion min : Board.topHero.minions.getStorage()) {
                   if(min == null)continue;
                if (AI.isFavorableTrade(min, m)) {
                    return true;
                }
            }
        }
        return false;
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
 
        int value = 0;
        switch(c.cardPurpose){
            case VanillaMinion:
                value = c.summon.attack + c.summon.health;
                if(c.getOwner().minions.isFull()) return 0; //if there is no place to summon the minion, it has 0 value.
                if(isVulnerable(c.summon)){
                    value = c.summon.attack;
                }
                return value;
            case BattlecryMinionDamage:
                if(c.getOwner().minions.isFull()) return 0; //if there is no place to summon the minion, it has 0 value.
                    Minion target = AI.getBestTarget(new SimulatedMinion(c.spellDamage,99,c.getOwner()));
                    System.out.println("best target for " + c + " is " + target);
                    if(target!=null)value += getWorth(target) -AI.getWorthAfterDamage(target, c.spellDamage);
                    else value+=c.spellDamage/2; //there are no minions to use it on so this is the value of using the damage on the opponent's hero
                    value += c.summon.attack + c.summon.maxHealth;
                return value;
            case BattlecryMinionHeal:
                if(c.getOwner().minions.isFull()) return 0; //if there is no place to summon the minion, it has 0 value.
                for(Minion m : c.getOwner().minions.getStorage()){
                    if(m==null)continue;
                    if(isVulnerable(m)){  
                        if(!isVulnerable(new SimulatedMinion(m.attack,m.health+c.spellDamage,c.getOwner()))){  //if we can save a minion
                            if(getWorth(m) > value){
                                value = getWorth(m);    //the value of the card becomes teh value of the minion saved. use the value of the most important minion we can save
                            }
                        }
                    }
                }
                if(value == 0) value = c.spellDamage;
                value += getWorth(c.summon);
                return value;
            case AOEDamage:
                if (c.getOwner() == Board.botHero) {
                    for (Minion t : Board.topHero.minions.getStorage()) {
                        if(t==null)continue;
                        value += (AI.getWorth(t) - AI.getWorthAfterDamage(t, c.spellDamage));
                    }
                } else { //tophero
                    for (Minion t : Board.botHero.minions.getStorage()) {
                        if(t==null) continue;
                        value += (AI.getWorth(t) - AI.getWorthAfterDamage(t, c.spellDamage));
                    }
                }
                return value;
            case DirectDamage:
                if(c.getOwner() == Board.topHero){
                    for(Minion m : Board.botHero.minions.getStorage()){
                        if(m==null)continue;
                        int thisValue = getWorth(m) - AI.getWorthAfterDamage(m, c.spellDamage);
                        if(thisValue > value) value = thisValue;
                    }
                }else{
                    for(Minion m : Board.topHero.minions.getStorage()){
                        if(m==null)continue;
                        int thisValue = getWorth(m) - AI.getWorthAfterDamage(m, c.spellDamage);
                        if(thisValue > value) value = thisValue;
                    }
                }
                return value;
            case Debuff:
                if(c.getOwner() == Board.botHero){
                    if(Board.topHero.minions.numOccupants() == 0) return -1;
                    if(AI.canTradeUp(AI.getBiggestThreatOf(Board.topHero))){
                        if(!canTradeUp(new SimulatedMinion(getBiggestThreatOf(Board.topHero).attack-c.spellDamage,getBiggestThreatOf(Board.topHero).health,c.getOwner()))){
                            //if the enemy's biggest threat can no longer trade up
                            value += 1;
                        }
                      if(!AI.isVulnerable(getBiggestThreatOf(Board.topHero))){
                          if(isVulnerable(new SimulatedMinion(getBiggestThreatOf(Board.topHero).attack-c.spellDamage,getBiggestThreatOf(Board.topHero).health,c.getOwner()))){
                              //if the enemy's biggest threat is now vulnerable
                              value += 1;
                          }
                      }
                    }
                    if(getWorth(getBiggestThreatOf(Board.topHero)) > c.spellDamage){
                        value += getBiggestThreatOf(Board.topHero).health;
                    }else{
                        value += c.spellDamage;
                    }
                }else{
                     if(Board.botHero.minions.numOccupants() == 0) return -1;
                    if(AI.canTradeUp(AI.getBiggestThreatOf(Board.botHero))){
                        if(!canTradeUp(new SimulatedMinion(getBiggestThreatOf(Board.botHero).attack-c.spellDamage,getBiggestThreatOf(Board.botHero).health,c.getOwner()))){
                            //if the enemy's biggest threat can no longer trade up
                            value += 1;
                        }
                      if(!AI.isVulnerable(getBiggestThreatOf(Board.botHero))){
                          if(isVulnerable(new SimulatedMinion(getBiggestThreatOf(Board.botHero).attack-c.spellDamage,getBiggestThreatOf(Board.botHero).health,c.getOwner()))){
                              //if the enemy's biggest threat is now vulnerable
                              value += 1;
                          }
                      }
                    }
                    if(getWorth(getBiggestThreatOf(Board.botHero)) > c.spellDamage){
                        value += getBiggestThreatOf(Board.botHero).health;
                    }else{
                        value += c.spellDamage;
                    }  
                }
            return value;
            case AOEHeal:
                for(Minion m : c.getOwner().minions.getStorage()){
                    if(m==null)continue;
                    int missingHealth = (m.maxHealth-m.health);
                    if(missingHealth > c.spellDamage){
                        value += c.spellDamage;
                    }else{
                        value += missingHealth;
                    }
                }
            return value;
            case DirectHeal:
               //TODO: Analyze hero hp for potential hero heal
                for (Minion m : c.getOwner().minions.getStorage()) {
                    if (m == null) continue;
                    if (isVulnerable(m)) {
                        if (!isVulnerable(new SimulatedMinion(m.attack, m.health + c.spellDamage,c.getOwner()))) {  //if we can save a minion
                            if (getWorth(m) > value) {
                                value = getWorth(m);    //the value of the card becomes teh value of the minion saved. use the value of the most important minion we can save
                            }
                        }
                    }
                }
                if (value == 0) {
                    value = c.spellDamage;
                }
                return value;
            case Buff:
                for(Minion m : c.getOwner().minions.getStorage()){
                    if(!canTradeUp(m)){
                        if(canTradeUp(new SimulatedMinion(m.attack+c.spellDamage,m.health,m.owner))){
                            value += AI.getTradeValue(new SimulatedMinion(m.attack+c.spellDamage,m.health,m.owner), AI.getBestTarget(new SimulatedMinion(m.attack+c.spellDamage,m.health,m.owner)));
                        }
                    }
                }
                if(value == 0) value = c.spellDamage;
                return value;
            case ChargeMinion:
                if(getBestTarget(c.summon) == null) return getWorth(c.summon);
                value += getTradeValue(new SimulatedMinion(c.summon),getBestTarget(new SimulatedMinion(c.summon))) + AI.getWorthAfterCombat(c.summon,getBestTarget(c.summon));
                System.out.println("best target for " + c + " is " + getBestTarget(new SimulatedMinion(c.summon)));
                return value;
        }
        return value;
    }
}
