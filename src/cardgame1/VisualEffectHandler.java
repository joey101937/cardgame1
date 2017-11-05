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
    VisualEffectHandler(Board b){
        board = b;
    }
    
    public void render(Graphics2D g){
        drawLineM(g);
        drawLineC(g);
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
