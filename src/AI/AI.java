/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Cards.Card;
import Cards.CardPurpose;
import static Cards.CardPurpose.Trap;
import Cards.CardType;
import Minions.Minion;
import Traps.Trap;
import cardgame1.Board;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;
import java.util.ArrayList;

/**
 * Driver for computer players
 * @author Joseph
 */
public abstract class AI {
    public static int speed = 800; //how long to wait between making moves so the player is able to follow along
    
    /**
     * core of the AI player, makes plays and casts cards the most efficient way possible and ends turn when done
     * @param h the AI will make plays on behalf on this hero
     */
    
    public static void takeTurn(Hero h) {
        Board.mainBoard.tick();
        Hero enemy = null;
        if(h==Board.topHero) enemy = Board.botHero;
        else enemy = Board.topHero;
        playCardsAboveValue(h,2.5);
        tradeOnBoard(h,false);
        playOutHand(h);
        Main.wait(speed);
        tradeOnBoard(h,false);
        for(Minion m : h.minions.getStorage()){
            if(!AI.isHeroVulnerable(h)){
            if(m==null) continue;
            Main.wait(speed);
            m.attack(enemy);
            }else{
            if(m==null) continue;
            Main.wait(speed);
            m.attack(AI.getBestTarget(m)); //if we are vulnerable, attack on board as best as possible
            }
        }
        Main.wait(speed);
        Board.controller.nextTurn();
        /*
        for(Card c : h.hand){
            System.out.println(c + " == " + AI.getValueOfCard(c));
        }
        */
    }
    
    /**
     * plays all cards we can in the best way possible, recursively
     */
    public static void playOutHand(Hero h){
        Board.getMainBoard().tick();
        boolean playOver = true;
        for(Card c : h.hand){
            if(c.canAfford() && AI.getValueOfCard(c) > 0) playOver = false; //there is something we want to play
        }
        if(playOver)return;
        ArrayList<Card> playable = new ArrayList<>();
        for(Card c : h.hand){
            if(c.canAfford() && getValueOfCard(c) > 0) playable.add(c);
        }
        playable.sort(null); //orders the cards using their comparable interface, ordering based on value rather than worth (value accounts for cost, worth does not)
        if(playable.get(playable.size()-1).cardPurpose == CardPurpose.Trap){
            Sticker s = new Sticker(SpriteHandler.trapPlaceholder,1700,200,speed*3);        //let user know we are playing a trap card
        }else{
            Sticker s = new Sticker(playable.get(playable.size()-1),1700,200,speed*3);      //let user know what non-trap card we are playing
        }
        Main.wait(speed*3);
        System.out.println("attempting to play: " + playable.get(playable.size()-1));
        AI.playCard(playable.get(playable.size()-1));
        playOutHand(h);
    }
    
    /**
     * plays out hand, but restricts plays to only cards whose value-to-cost ratio is above the given value
     * @param par 
     */
    public static void playCardsAboveValue(Hero h, double par){
        Board.getMainBoard().tick();
        boolean playOver = true;
        for(Card c : h.hand){
            if(c.canAfford() && ((double)AI.getValueOfCard(c)/(double)c.cost) > par) playOver = false; //there is something we want to play
        }
        if(playOver)return;
        ArrayList<Card> playable = new ArrayList<>();
        for(Card c : h.hand){
            if(c.canAfford() && getValueOfCard(c) >= par) playable.add(c);
        }
        playable.sort(null); //orders the cards using their comparable interface, ordering based on value rather than worth (value accounts for cost, worth does not)
        if(playable.get(playable.size()-1).cardPurpose == CardPurpose.Trap){
            Sticker s = new Sticker(SpriteHandler.trapPlaceholder,1700,200,speed*3);        //let user know we are playing a trap card
        }else{
            Sticker s = new Sticker(playable.get(playable.size()-1),1700,200,speed*3);      //let user know what non-trap card we are playing
        }
        Main.wait(speed*3);
        System.out.println("attempting to play: " + playable.get(playable.size()-1));
        AI.playCard(playable.get(playable.size()-1));
        playCardsAboveValue(h,par);
    }
    
    
    /**
     * plays card with minion target
     * @param c
     * @param target 
     */
    public static void playCard(Card c){
        Board.getMainBoard().tick();
        System.out.println("casting " + c);
        if(!c.canAfford()){
            System.out.println("cannot play " + c + "; cannot afford");
            return;
        }
        Hero enemy = null;
        if (c.getOwner() == Board.topHero) {
            enemy = Board.botHero;
        } else {
           enemy = Board.topHero;
        }
        int value = 0;
        switch(c.cardPurpose){
            case VanillaMinion:
            case ChargeMinion:
            case AOEDamage:
            case AOEHeal:
            case BattlecryMinionDraw:
            case Trap:
            case Draw:
                c.cast(null);
                break;
            case BattlecryMinionDamage:
                Minion bestTarget = AI.getBestTarget(new SimulatedMinion(c.spellDamage,0,c.getOwner()));
                if(bestTarget!=null) {
                    System.out.println("casting " + c + " on " + bestTarget);
                    c.cast(bestTarget);
                }
                else{
                    c.castOnHero(enemy);
                }
                break;
            case BattlecryMinionHeal:
                value = -999;
                Minion bestHealTarget = null;
                for (Minion m : c.getOwner().minions.getStorage()) {
                    if (m == null)continue;
                    if (isVulnerable(m)) {
                        if (!isVulnerable(new SimulatedMinion(m.attack, m.health + c.spellDamage, c.getOwner()))) {  //if we can save a minion
                            if (getWorth(m) > value) {
                                value = getWorth(m);    //the value of the card becomes teh value of the minion saved. use the value of the most important minion we can save
                                bestHealTarget = m;
                            }
                        }
                    }
                }
                if(bestHealTarget!=null) c.cast(bestHealTarget);
                else c.castOnHero(c.getOwner());
                break;
            case BattleCryMinionBuff:
                value = 0;
                Minion BestFit = null;
                 for (Minion m : c.getOwner().minions.getStorage()) {
                    if (!AI.canFavorablyTrade(m)) {
                        if (canFavorablyTrade(new SimulatedMinion(m.attack + c.spellDamage, m.health, m.owner))) {
                            if(AI.getTradeValue(new SimulatedMinion(m.attack + c.spellDamage, m.health, m.owner), AI.getBestTarget(new SimulatedMinion(m.attack + c.spellDamage, m.health, m.owner))) > value){
                             value = AI.getTradeValue(new SimulatedMinion(m.attack + c.spellDamage, m.health, m.owner), AI.getBestTarget(new SimulatedMinion(m.attack + c.spellDamage, m.health, m.owner)));
                             BestFit = m;
                            }                          
                        }
                    }
                    if(BestFit != null){
                        c.cast(BestFit);
                    }
                    else{
                        if(c.getOwner().minions.numOccupants() > 0){
                            c.cast(AI.getBiggestThreatOf(c.getOwner()));
                        }
                    }
                }
                    break;
            case DirectDamage:
                if(enemy.health <= c.spellDamage){
                    c.castOnHero(enemy);
                    break;
                }
                Minion target = AI.getBestTarget(new SimulatedMinion(c.spellDamage,0,c.getOwner()));
                if(target != null){
                    System.out.println("casting " + c + "on " + target);
                    c.cast(target);
                    break;
                }else{
                    c.castOnHero(enemy);
                    System.out.println("no minion target for " + c);
                    break;
                }
            case Debuff:
                //TODO
                break;
            case DirectHeal:
                if(isHeroVulnerable(c.getOwner())){
                    c.castOnHero(c.getOwner());
                    break;
                }else{
                    Minion potential = null;
                    int valGained = 0;
                    for(Minion m : c.getOwner().minions.getStorage()){
                        if(m==null)continue;
                        if(isVulnerable(m)){
                            if(!isVulnerable(new SimulatedMinion(m.attack,m.health+c.spellDamage,m.owner))){
                                if(getWorth(m) > valGained){
                                    valGained = getWorth(m);
                                    potential = m;
                                }
                            }
                        }
                    }
                    if(valGained!=0) {
                        c.cast(potential);
                        break;
                    }
                    if(AI.getBiggestThreatOf(c.getOwner())!= null) {
                        c.cast(getBiggestThreatOf(c.getOwner()));
                        break;
                    }else{
                        c.castOnHero(c.getOwner());
                        break;
                    }
                }
            case Buff:
                int best = 0;
                Minion buffTarget = null;
                for(Minion m : c.getOwner().minions.getStorage()){
                    if(m==null)continue;
                    if(!canTradeUp(m)){
                        if(canTradeUp(new SimulatedMinion(m.attack+c.spellDamage,m.health,m.owner))){
                            if(m.attack + c.spellDamage > best){
                                best = m.attack + c.spellDamage;
                                buffTarget = m;
                            }
                        }
                    }
                }
                if(buffTarget != null){
                    c.cast(buffTarget);
                    break;
                }
                for(Minion m : c.getOwner().minions.getStorage()){
                    if(m==null)continue;
                    if(!canFavorablyTrade(m)){
                        if(canFavorablyTrade(new SimulatedMinion(m.attack + c.spellDamage,m.health,m.owner))){
                            if(getWorth(m) > best){
                                best = getWorth(m);
                                buffTarget = m;
                            }
                        }
                    }
                }
                if(buffTarget != null){
                    c.cast(buffTarget);
                    break;
                }
                if(c.getOwner().minions.numOccupants() > 0){
                    c.cast(getBiggestThreatOf(c.getOwner()));
                    break;
                }else{
                    System.out.println("No target for " + c);
                }
                break;
            case Special:
                if(!c.isTargeted){
                    c.cast(null);
                }else{
                    c.smartCast();
                    return;
                }
                break;
        }
    }
    
    /**
     * returns true if the given hero would be killed by an attack from each of the opponents minions
     * @param h hero to evaluate
     * @return result
     */
    public static boolean isHeroVulnerable(Hero h){
        Hero enemy = null;
        if(h == Board.topHero){
            enemy = Board.botHero;
        }else{
            enemy = Board.topHero;
        }
        int damagePotential = 0;
        for(Minion m : enemy.minions.getStorage()){
            if(m==null)continue;
            damagePotential += m.attack;
        }
        return damagePotential >= h.health;
    }
    
    /**
     * makes favorable trades on the board until there are no more favorable trades
     * @param should we wait between making plays so the player can see whats going on?
     * @param h 
     */
    private static void tradeOnBoard(Hero h, boolean instant){
        Board.mainBoard.tick();
        int damagePotential = 0;
        Hero enemy;
        if(h == Board.topHero) enemy = Board.botHero;
        else enemy = Board.topHero;
        for(Minion m : h.minions.getStorage()){
            if(m==null || !m.canAttack()) continue;
            damagePotential += m.attack;
        }
        if(damagePotential >= enemy.health){
            for(Minion m : h.minions.getStorage()){
                if(m==null)continue;
                if(!instant) Main.wait(speed);
                m.attack(enemy);
            }
        }
        boolean doneTrading = false;
        while (!doneTrading) {
            doneTrading = true;
            for (Minion m : h.minions.getStorage()) {
                if (m == null) continue;
                if (AI.canFavorablyTrade(m) && m.canAttack() && m.attack>0) {
                    if(!instant)Main.wait(speed);
                    m.attack(AI.getBestTarget(m));
                    doneTrading = false;
                }
            }
        }
        if(h == Board.topHero){
            if(Board.botHero.minions.numOccupants()==0){
                for(Minion m : h.minions.getStorage()){
                    if(m==null || !m.canAttack()) continue;
                     if(!instant)Main.wait(speed);
                    m.attack(Board.botHero);
                }
            }
        }else{
            //we are playing as bothero
             if(Board.topHero.minions.numOccupants()==0){
                for(Minion m : h.minions.getStorage()){
                    if(m==null || !m.canAttack()) continue;
                     if(!instant)Main.wait(speed);
                    m.attack(Board.topHero);
                }
            }
        }
    }


    /**
     * calculates the worth of a minion for purposes of making plays
     * @param m minion to evaluate
     * @return how much a minion is worth, higher is better
     */
    public static int getWorth(Minion m){
        int sumStats = m.health + m.attack;
        sumStats += m.intrinsicValue;
        if(m.attack < m.health) sumStats--; //low attack is slightly worse than high atk
        if(m.health <= 0){
            return 0; //worthless if dead
        }
        if(m.owner.minions.isFull()){
            for(Minion min: m.owner.minions.getStorage()){
                if(m==null)continue;
                if(sumStats > min.attack + min.health){
                    return sumStats;
                }
            }
            //this is the smallest minion on a full field
            for(Card c : m.owner.hand){
                if(c.canAfford() && c.cardType == CardType.Minion && (c.summon.attack + c.summon.health + c.intrinsicValue)> sumStats){
                    return 0; //the minion is taking a slot that a largert minion should have
                }
            }
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
        int sumNewStats = attacker.health - defender.attack + attacker.attack + attacker.intrinsicValue;
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
        return target.health - damage + target.attack + target.intrinsicValue;
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
        if(myDif == theirDif) return attacker.attack < defender.attack;
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
     * if there is a favorable trade on the board for a minion
     * @param m
     * @return 
     */
    public static boolean canFavorablyTrade(Minion m){
        if(AI.getBestTarget(m) == null) return false;
        return(AI.getTradeValue(m, AI.getBestTarget(m)) > 0);
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
        if(attacker.attack < defender.attack) ValueGained++; //minions with higher attack are worth a tad more
        int damagePotential = 0;
        for (Minion m : attacker.owner.minions.getOccupants()) {
            damagePotential += m.attack;
        }
        if(defender.intrinsicValue > attacker.intrinsicValue && defender == AI.getBiggestThreatOf(defender.owner) && damagePotential >= defender.health){
            ValueGained += defender.intrinsicValue;
        }
        if(theirNewValue == 0 && attacker.owner.health < defender.owner.health) ValueGained++; 
        return ValueGained;
    }
    
    /**
     * finds the minion with the highest worth for a particular hero
     * @param h hero to evaluate
     * @return biggest minion. null if none.
     */
    public static Minion getBiggestThreatOf(Hero h){
        Minion biggest = null;
        for(Minion t : h.minions.getOccupants()){
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
     * @param c card to evaluate
     * @return the value playing the card presents
     */
    public static int getValueOfCard(Card c){
        Board.getMainBoard().tick();
        Hero enemy;
        if(c.getOwner() == Board.topHero){
            enemy = Board.botHero;
        }else{
            enemy = Board.topHero;
        }
        
        int value = 0;
        switch(c.cardPurpose){
            case VanillaMinion:
                value = c.summon.attack + c.summon.health + c.intrinsicValue;
                if(c.cost < c.getOwner().minions.numOccupants()){
                    value -= c.cost-c.getOwner().minions.numOccupants(); //small minions are penalized for taking a board slot
                }
                if(c.getOwner().minions.isFull()) return 0; //if there is no place to summon the minion, it has 0 value.
                if(isVulnerable(c.summon)){
                    value = c.intrinsicValue;
                    int largestEnemyHealth = 0;
                    for(Minion m : c.getOwner().opponent.minions.getOccupants()){
                        if(m.health>largestEnemyHealth){
                            largestEnemyHealth = m.health;
                        }
                    }
                    if(c.summon.attack<=largestEnemyHealth) value+=c.summon.attack;
                    else value+=largestEnemyHealth;
                }
                return value;
            case BattlecryMinionDamage:
                if(c.getOwner().minions.isFull()) return 0; //if there is no place to summon the minion, it has 0 value.
                    Minion target = AI.getBestTarget(new SimulatedMinion(c.spellDamage,0,c.getOwner()));
                    //System.out.println("best target for " + c + " is " + target);
                    if(target!=null)value += getWorth(target) -AI.getWorthAfterDamage(target, c.spellDamage);
                    else value+=c.spellDamage/2; //there are no minions to use it on so this is the value of using the damage on the opponent's hero
                    if(isVulnerable(c.summon)){
                        value+=c.summon.attack;
                    }else{
                        value += c.summon.attack + c.summon.maxHealth;
                    } 
                return value+ c.intrinsicValue;
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
                return value+ c.intrinsicValue;
            case AOEDamage:
                value = 0;
                for(Minion m : c.getOwner().opponent.minions.getOccupants()){
                    if(m.health <= c.spellDamage) value += getWorth(m) +1;
                    else value += c.spellDamage/2;
                }
                return value+ c.intrinsicValue;
            case DirectDamage:
                value = 0;
                Minion optimalTarget = null;
                if(c.spellDamage == 0) return -1;
                if(c.getOwner() == Board.topHero){
                    for(Minion m : Board.botHero.minions.getStorage()){
                        if(m==null)continue;
                        int thisValue = getWorth(m) - AI.getWorthAfterDamage(m, c.spellDamage);
                        if(thisValue > value) {
                            value = thisValue;
                            optimalTarget = m;
                        }
                    }
                }else{
                    for(Minion m : Board.topHero.minions.getStorage()){
                        if(m==null)continue;
                        int thisValue = getWorth(m) - AI.getWorthAfterDamage(m, c.spellDamage);
                        if(thisValue > value){
                            value = thisValue;
                            optimalTarget = m;
                        }
                    }
                }
                //System.out.println("best target for " + c + " is " + optimalTarget);
                return value+ c.intrinsicValue;
            case Debuff:
                if(c.getOwner() == Board.botHero){
                    if(Board.topHero.minions.numOccupants() == 0) return -1;
                    if(AI.canFavorablyTrade(AI.getBiggestThreatOf(Board.topHero))){
                        if(!canFavorablyTrade(new SimulatedMinion(getBiggestThreatOf(Board.topHero).attack-c.spellDamage,getBiggestThreatOf(Board.topHero).health,c.getOwner()))){
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
                    if(AI.canFavorablyTrade(AI.getBiggestThreatOf(Board.botHero))){
                        if(!canFavorablyTrade(new SimulatedMinion(getBiggestThreatOf(Board.botHero).attack-c.spellDamage,getBiggestThreatOf(Board.botHero).health,c.getOwner()))){
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
            return value+ c.intrinsicValue;
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
            return value+ c.intrinsicValue;
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
                if(AI.isHeroVulnerable(c.getOwner())){
                    int damagePotential = 0;
                    for(Minion m : enemy.minions.getStorage()){
                        if(m==null)continue;
                        damagePotential += m.attack;
                    }
                    if(damagePotential < c.getOwner().health + c.spellDamage) return 30; //if this heal would take the hero out of lethal range, its worth alot
                }
                return value+ c.intrinsicValue;
            case Buff:
                for(Minion m : c.getOwner().minions.getStorage()){
                    if(!canFavorablyTrade(m)){
                        if(canFavorablyTrade(new SimulatedMinion(m.attack+c.spellDamage,m.health,m.owner))){
                            value += AI.getTradeValue(new SimulatedMinion(m.attack+c.spellDamage,m.health,m.owner), AI.getBestTarget(new SimulatedMinion(m.attack+c.spellDamage,m.health,m.owner)));
                        }
                    }
                }
                if(value == 0) value = c.spellDamage-1;
                return value;
            case BattleCryMinionBuff:
                for (Minion m : c.getOwner().minions.getStorage()) {
                    if (!canFavorablyTrade(m)) {
                        if (canFavorablyTrade(new SimulatedMinion(m.attack + c.spellDamage, m.health, m.owner))) {
                            value += AI.getTradeValue(new SimulatedMinion(m.attack + c.spellDamage, m.health, m.owner), AI.getBestTarget(new SimulatedMinion(m.attack + c.spellDamage, m.health, m.owner)));
                        }
                    }
                }
                if(value==0) value = c.spellDamage-1;
                value+=AI.getWorth(c.summon);
                return value+ c.intrinsicValue;
            case ChargeMinion:
                if(c.getOwner().minions.isFull()) return 0;
                if(getBestTarget(c.summon) == null) return getWorth(c.summon);
                value += getTradeValue(new SimulatedMinion(c.summon),getBestTarget(new SimulatedMinion(c.summon))) + AI.getWorthAfterCombat(c.summon,getBestTarget(c.summon));
               // System.out.println("best target for " + c + " is " + getBestTarget(new SimulatedMinion(c.summon)));
                if(value < 1) return getWorth(c.summon);
                return value+ c.intrinsicValue;
            case BattlecryMinionDraw:
                if(c.getOwner().minions.isFull()) return 0;
                value = getWorth(c.summon) + c.intrinsicValue;
                int numDrawable = Hero.maxHandSize - c.getOwner().hand.size(); //number of cards we can draw.
                if(c.spellDamage>numDrawable) value -= (c.spellDamage-numDrawable)*2;  //reduce value if it will cause overdraw
                else value += (numDrawable - c.spellDamage);
                return value;
            case Draw:
                value = c.intrinsicValue;
                int amountDrawable = Hero.maxHandSize - c.getOwner().hand.size() + 1; //number of cards we can draw.
                if(c.spellDamage>amountDrawable) value -= (c.spellDamage-amountDrawable)*2;  //reduce value if it will cause overdraw
                else value += (amountDrawable - c.spellDamage);
                return value;
            case Special:
                value = c.getValue();
                return value+ c.intrinsicValue;
            case Trap:
                for (Trap t : c.getOwner().traps.getOccupants()) {
                    if (t.name.equals(c.name)) {
                        return 0;
                    }
                }
                value = c.getValue();
                return value + c.intrinsicValue;
        }
        System.out.println("error finding value for " + c);
        return value+ c.intrinsicValue;
    }
}
