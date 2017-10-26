/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import Minions.Minion;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Joseph
 */
public class InputHandler extends KeyAdapter implements MouseListener, MouseMotionListener{

    /*    FIELDS    */
    public static Minion selected = null;
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX()/Board.xScale + ", " + e.getY()/Board.yScale);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        double x = e.getX() / Board.xScale;
        double y = e.getY() / Board.yScale;
        System.out.println(getMinionAt(x,y));
        InputHandler.selected = getMinionAt(x,y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Minion target = getMinionAt(e.getX()/Board.xScale,e.getY()/Board.yScale);
        if(target != null && selected != null){
            selected.attack(target);
        }
       selected = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Board.mouseX = (int) (e.getX() / Board.xScale);
        Board.mouseY = (int) (e.getY() / Board.yScale);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Board.mouseX = (int)(e.getX()/Board.xScale);
        Board.mouseY = (int)(e.getY()/Board.yScale);
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * gets the minion that is currently being rendered in the clicked location.
     * @param x x-coordinate of mouse click AFTER ADJUSTING FOR RESOLUTION SCALING
     * @param y x-coordinate of mouse click AFTER ADJUSTING FOR RESOLUTION SCALING
     * @return minion at location, null if none is there
     */
    public static Minion getMinionAt(double x, double y){
        double minionX = 0; //the x coordinate of the top left of the minion being checked
        for(Minion m: Board.topHero.minions){
            double mx = ((Board.topHero.minions.indexOf(m) * (Minion.WIDTH + Minion.SPACER_SIZE)) + Minion.SPACER_SIZE); 
            if(x > mx && x < mx+Minion.WIDTH && y > Minion.TOP_Y_OFFSET && y < Minion.TOP_Y_OFFSET + Minion.HEIGHT){
                //if x is within the leftmost and rightmost corners of a minoin and y is within the top and bottom of a minion, then we are clicking on a minion, m
                return m;
            }
        }
        for(Minion m: Board.botHero.minions){
            double mx = ((Board.botHero.minions.indexOf(m) * (Minion.WIDTH + Minion.SPACER_SIZE)) + Minion.SPACER_SIZE); 
            if(x > mx && x < mx+Minion.WIDTH && y > Minion.BOT_Y_OFFSET && y < Minion.BOT_Y_OFFSET + Minion.HEIGHT){
                //if x is within the leftmost and rightmost corners of a minoin and y is within the top and bottom of a minion, then we are clicking on a minion, m
                return m;
            }
        }
        return null;
    }
}
