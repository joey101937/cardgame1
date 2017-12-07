/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Minions.Minion;

/**
 * minion placeholder for use in AI class
 * @author Joseph
 */
public class SimulatedMinion extends Minion{

    public SimulatedMinion(Minion m){
        this.attack = m.attack;
        this.health = m.health;
        this.maxHealth = m.maxHealth;
        this.tribe = m.tribe;
        this.owner = m.owner;
    }
    
    public SimulatedMinion(int attack, int health){
        this.attack = attack;
        this.health = health;
        this.maxHealth = health;
    }
    
    
    
    @Override
    public void tick() {
        return;
    }

    @Override
    public void onSummon() {
      return;
    }
    
}
