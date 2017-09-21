/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

/**
 * the creature summoned by cards to fight
 * @author Joseph
 */
public abstract class Minion extends Card{
    /* FIELDS */
    public int attack;          //attack value
    public int health;          //current health
    public int maxHealth;       //current health
    public CardTribe tribe;
    
    public Minion(){
        this.cardType = cardType.Minion;
    }
    /**
     * runs every tick
     */
    public abstract void tick();
    
    /*   MINION GAMEPLAY      */
    /**
     * Attacks another minion, each combatant takes damage based on the other's attack. If 
     * either has their health reduced to 0, destroy that minion
     * @param target 
     */
    public void attack(Minion target){
        target.health -= this.attack;
        this.health -= target.attack;
        if(target.health <= 0){
            target.destroy();
        }
       if(this.health <= 0){
           this.destroy();
       }
    }
    /**
     * attacks enemy hero/lifepoints directly
     */
    public void directAttack(){
        //TODO
    }
}
