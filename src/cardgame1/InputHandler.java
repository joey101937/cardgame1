/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import Cards.Card;
import Cards.CardType;
import Minions.Minion;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Representation of a summoned monster, rendered on the board and can fight/die
 * @author Joseph
 */
public class InputHandler extends KeyAdapter implements MouseListener, MouseMotionListener{

    /*    FIELDS    */
    public static Minion selectedMinion = null;
    public static Card selectedCard = null;
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX()/Board.xScale + ", " + e.getY()/Board.yScale);
        Card clickedCard = InputHandler.getCardAt(e.getX()/Board.xScale, e.getY()/Board.yScale);
        if(clickedCard != null && clickedCard.cardType == CardType.Minion){
            clickedCard.cast();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        double x = e.getX() / Board.xScale;
        double y = e.getY() / Board.yScale;
        System.out.println(getMinionAt(x,y));
        System.out.println(getCardAt(x,y));
        InputHandler.selectedMinion = getMinionAt(x,y);
        InputHandler.selectedCard = getCardAt(x,y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Minion target = getMinionAt(e.getX()/Board.xScale,e.getY()/Board.yScale);
        if(target != null && selectedMinion != null && selectedMinion.owner != target.owner){
            selectedMinion.attack(target);
        }
       selectedMinion = null;
       selectedCard = null;
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
        for(Minion m: Board.topHero.minions.getStorage()){
            double mx = ((Board.topHero.minions.indexOf(m) * (Minion.WIDTH + Minion.SPACER_SIZE)) + Minion.SPACER_SIZE); 
            if(x > mx && x < mx+Minion.WIDTH && y > Minion.TOP_Y_OFFSET && y < Minion.TOP_Y_OFFSET + Minion.HEIGHT){
                //if x is within the leftmost and rightmost corners of a minoin and y is within the top and bottom of a minion, then we are clicking on a minion, m
                return m;
            }
        }
        for(Minion m: Board.botHero.minions.getStorage()){
            double mx = ((Board.botHero.minions.indexOf(m) * (Minion.WIDTH + Minion.SPACER_SIZE)) + Minion.SPACER_SIZE); 
            if(x > mx && x < mx+Minion.WIDTH && y > Minion.BOT_Y_OFFSET && y < Minion.BOT_Y_OFFSET + Minion.HEIGHT){
                //if x is within the leftmost and rightmost corners of a minoin and y is within the top and bottom of a minion, then we are clicking on a minion, m
                return m;
            }
        }
        return null;
    }
    
    /**
     * returns the card in the players hand being rendered at the given coordinates
     * @param x x-coordinate of mouse click AFTER ADJUSTING FOR RESOLUTION SCALING
     * @param y x-coordinate of mouse click AFTER ADJUSTING FOR RESOLUTION SCALING
     * @return card at the coordinate. null if no card is there
     */
    public static Card getCardAt(double x, double y){
        Card output = null;
        //left column
        if(x > 1100 && x < 1100 + Card.WIDTH){
            for(int i = 0; i < 3; i++){
                if(y > 25 + (Board.buffer*i) + (Card.HEIGHT * i) && y < 25 + (Board.buffer*i) + (Card.HEIGHT * i) + Card.HEIGHT){
                    if(Board.playerHero.hand.size() <= 2*i) return null;
                    return Board.playerHero.hand.get(2*i);
                }
            }
        }
        //right column
        if (x > (1100 + Board.buffer + Card.WIDTH) && x < 1100 + Board.buffer + (2 * Card.WIDTH)) {
            for (int i = 0; i < 3; i++) {
                if (y > 25 + (Board.buffer*i) + (Card.HEIGHT * i) && y < 25 + (Board.buffer*i) + (Card.HEIGHT * i) + Card.HEIGHT) {
                    if(Board.playerHero.hand.size() <= (2*i)+1) return null;
                    return Board.playerHero.hand.get((2 * i) + 1);
                }
            }
        }
        return output;
    }
}
