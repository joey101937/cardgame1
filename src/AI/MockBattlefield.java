/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public class MockBattlefield {
    public ArrayList<SimulatedMinion> frindlies, enemies;
    
    public MockBattlefield(ArrayList<SimulatedMinion> friendlies, ArrayList<SimulatedMinion> enemies){
        this.frindlies = friendlies;
        this.enemies = enemies;
    }
}
