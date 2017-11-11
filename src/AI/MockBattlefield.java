/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Minions.Minion;
import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public class MockBattlefield {
    public ArrayList<SimulatedMinion> friendlies, enemies;
    
    public MockBattlefield(ArrayList<SimulatedMinion> friendlies, ArrayList<SimulatedMinion> enemies){
        this.friendlies = friendlies;
        this.enemies = enemies;
    }

//AI METHODS
    
     /**
     * returns the best minoin target for the given minion
     * sometimes there is no good trade, and this method will return the "least bad" trade.
     * @param attacker minion that will be attacking
     * @return Best target
     */
    public Minion getBestTarget(Minion attacker){
       int bestValue = -99999999;
       Minion bestTarget = null; 
        if(friendlies.contains(attacker)){ //is a friendly minion
          for(Minion target : enemies){
              int value = AI.getTradeValue(attacker, target);
              if (value > bestValue){
                  bestValue = value;
                  bestTarget = target;
                }
            }
            return bestTarget;
        } else if (enemies.contains(attacker)) { //is an enemy minion
            for (Minion target : friendlies) {
                int value = AI.getTradeValue(attacker, target);
                if (value > bestValue) {
                    bestValue = value;
                    bestTarget = target;
                }
            }
            return bestTarget;
        }
        System.out.println("error finding best target for " + attacker);
        return null;
    }

}
