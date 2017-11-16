/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import Cards.Card;
import Cards.CardType;
import Minions.Minion;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Joseph
 */
public class VisualEffectHandler {
    public Board board = null;
    private int timeMousedOver = 0; //how long the cursor has been on a particular minion, for use in drawMouseOverCard method
    private Minion mousedOverMinion = null; //currently moused over minion, for use in drawMouseOverCard method
    VisualEffectHandler(Board b){
        board = b;
    }
    
    public void render(Graphics2D g){
        drawLineM(g);  //draw line from selected minion
        drawLineC(g);  //draw line from selected card
        drawMouseOverCard(g);
    }
    
    public void tick(){
        
    }
    
    /**
     * draws the (card) parent of the currently moused over minion if the cursor has been sitting on that minion for a period of time
     * @param g graphics object to draw with
     */
    public void drawMouseOverCard(Graphics2D g){
        int NUM_TICKS_TO_WAIT = 45; //how many frames to wait before we render the moused over card
        if(mousedOverMinion != InputHandler.getMinionAt(Board.mouseX, Board.mouseY)){
           mousedOverMinion = InputHandler.getMinionAt(Board.mouseX, Board.mouseY);
           timeMousedOver = 0;
        }
        if(mousedOverMinion!= null && mousedOverMinion == InputHandler.getMinionAt(Board.mouseX, Board.mouseY)){
            timeMousedOver++;
        }
        if(timeMousedOver > NUM_TICKS_TO_WAIT && mousedOverMinion!=null){
            mousedOverMinion.parent.render(g, Board.mouseX, Board.mouseY, true);
        }
    }
    
    
    /**
     * draws line from selected minion to location of the mouse
     * @param g 
     */
    public void drawLineM(Graphics2D g){
        Minion selected = InputHandler.selectedMinion;
        if(selected == null) return;
        g.drawLine(selected.getXCordinate()+Minion.WIDTH/2, selected.getYcoordinate()+Minion.HEIGHT/2, Board.mouseX, Board.mouseY);
    }
    /**
     * draws a line from the selected card to the location of the mouse
     * @param g 
     */
    public void drawLineC(Graphics2D g){
        Card selected = InputHandler.selectedCard;
        if(selected == null) return;
        if(!selected.isTargeted || !selected.canAfford()) return; //return if card is untargeted or too expensive
        g.setColor(Color.yellow);
        g.drawLine(selected.getXCoordinate()+Card.WIDTH/2, selected.getYCoordinate()+Card.HEIGHT/2, Board.mouseX, Board.mouseY);
    }


}
