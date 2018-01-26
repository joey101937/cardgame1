/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import AI.AI;
import java.awt.event.MouseListener;

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
        if(activeHero == Board.playerHero){
            //player ending their turn, so disable input
            Board.getMainBoard().removeKeyListener(Board.ih);
            Board.getMainBoard().removeMouseListener(Board.ih);
            Board.getMainBoard().removeMouseMotionListener(Board.ih);
        }else{
            //enemy ending turn so player regains control
            Board.getMainBoard().addKeyListener(Board.ih);
            Board.getMainBoard().addMouseListener(Board.ih);
            Board.getMainBoard().addMouseMotionListener(Board.ih);
        }     
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

}
