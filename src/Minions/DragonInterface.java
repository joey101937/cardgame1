/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions;

/**
 *
 * @author Joseph
 */
public interface DragonInterface {
    
    /**
     * gets the number of turns left to pass before this hatchling transforms into its adult form
     */
    public int getTurnsRemaining();
    
    
    /**
     * grows into adult form
     */
    public void grow();
    
    /**
     * this is the breath effect that dragon soul and dragon breath copy
     */
    public void breath();
    
    /**
     * if it is a hatchling, get the adult form of that hatchling
     * @return 
     */
    public Minion getAdultForm();
}
