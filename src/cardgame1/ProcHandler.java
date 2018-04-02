/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import Minions.Minion;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * reduces proc and damage timers for given characters over time
 * @author Joseph
 */
public class ProcHandler implements Runnable{
    private Hero hero;
    private Minion minion;

    public ProcHandler(Hero h) {
        this.hero = h;
        Thread t = new Thread(this);
        t.start();
    }
    public ProcHandler(Minion m){
        minion = m;
        Thread t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        if(minion!=null){
            while(minion.procTimer>0 || minion.damagedTicker>0){
                if(minion.procTimer>0)minion.procTimer-=1;
                if(minion.damagedTicker>0)minion.damagedTicker-=1;
                try {
                    Thread.sleep(8);
                } catch (InterruptedException ex) {    
                    Logger.getLogger(ProcHandler.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
            }
        }
        if (hero != null) {
            while (hero.procTimer > 0 || hero.damageTicker>0) {
                if(hero.procTimer>0)hero.procTimer -= 1;
                if(hero.damageTicker>0)hero.damageTicker-=1;
                try {
                    Thread.sleep(8);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProcHandler.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
            }
        }
    }
    
}
