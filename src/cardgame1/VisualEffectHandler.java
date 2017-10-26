/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import Minions.Minion;
import java.awt.Graphics2D;
import java.awt.Stroke;

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
        drawLine(g);
    }
    /**
     * draws line from selected minion to location of the mouse
     * @param g 
     */
    public void drawLine(Graphics2D g){
        Minion selected = InputHandler.selected;
        if(selected == null) return;
        g.drawLine(selected.getXCordinate()+Minion.WIDTH/2, selected.getYcoordinate()+Minion.HEIGHT/2, Board.mouseX, Board.mouseY);
    }
}
