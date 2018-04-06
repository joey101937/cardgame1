/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import AI.AI;
import Multiplayer.Phantom;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * controls meta gameplay and turn order
 * @author Joseph
 */
public class GameController {
    /*   FIELDS   */
    public Hero activeHero;
    public Hero inactiveHero;
    public Board board;

    public GameController(Board b) {
        board = b;
    }
    public void nextTurn() {
        activeHero.onTurnEnd();
        Hero temp = activeHero;
        activeHero = inactiveHero;
        inactiveHero = temp;
        activeHero.onTurnStart();
    }

    public void startGame() {
        for (int i = 0; i < 4; i++) {
            Board.topHero.draw();
            Board.botHero.draw();
        }
        activeHero = Board.botHero;
        activeHero.turn = true;
        inactiveHero = Board.topHero;
        inactiveHero.turn = false;
    }
    
    /**
     * starts the game for multiplayer
     * @param isServer 
     */
    public void startGame(boolean isServer){
        while(!Phantom.syncedRandom){
            System.out.println("waiting for random to sync...");
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("shuffling with seed test " + Phantom.random.nextInt());
        if(isServer){
            Board.botHero.shuffle();
            Board.topHero.shuffle();
        }else{
            Board.topHero.shuffle();
            Board.botHero.shuffle();
        }

         for (int i = 0; i < 4; i++) {
            Board.topHero.draw();
            Board.botHero.draw();
        }
         if(isServer){
             activeHero = Board.playerHero;
             activeHero.turn = true;
             inactiveHero = Board.nonPlayerHero;
             inactiveHero.turn = false;
        } else {
            activeHero = Board.nonPlayerHero;
            activeHero.turn = true;
            inactiveHero = Board.playerHero;
            inactiveHero.turn = false;
        }
        activeHero.maxResource = 1;
        activeHero.resource = 1;
        inactiveHero.resource = 1;
        inactiveHero.maxResource = 1;
    }

}
