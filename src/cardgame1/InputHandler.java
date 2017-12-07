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
import java.awt.event.KeyEvent;
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
    private static int keyTimer = 0; // used to prevent too many key presses in quick succession
    /**
     * ticks every gametime second
     */
    public static void tick(){
        if(keyTimer > 0){
            keyTimer--;
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX()/Board.xScale + ", " + e.getY()/Board.yScale);
        if(!Board.playerHero.turn) return;  //if its not our turn, ignore it
        Card clickedCard = InputHandler.getCardAt(e.getX()/Board.xScale, e.getY()/Board.yScale);
        if(clickedCard != null && clickedCard.isTargeted == false){
            clickedCard.cast(null); //cast with null param becuase there is no target
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        double x = e.getX() / Board.xScale;
        double y = e.getY() / Board.yScale;
        System.out.println(getMinionAt(x,y));
        System.out.println(getCardAt(x,y));
        if(!Board.playerHero.turn) return;
        if(getMinionAt(x,y) !=null){
         if(getMinionAt(x,y).owner == Board.playerHero)InputHandler.selectedMinion = getMinionAt(x,y);   
        }
        if(getCardAt(x,y) != null){
          if(getCardAt(x,y).getOwner() == Board.playerHero)InputHandler.selectedCard = getCardAt(x,y);          
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!Board.playerHero.turn) return;
        Minion target = getMinionAt(e.getX()/Board.xScale,e.getY()/Board.yScale);
        Hero targetH = getHeroAt(e.getX()/Board.xScale,e.getY()/Board.yScale);
        if(target != null && selectedMinion != null && selectedMinion.owner != target.owner){
            selectedMinion.attack(target); //Minion attacking minion
        }
        if(targetH != null && selectedMinion!=null && selectedMinion.owner != targetH){
            selectedMinion.attack(targetH); //Minion attacking hero
        }
        if(target != null && selectedCard != null && selectedCard.isTargeted){
            selectedCard.cast(target); //card cast with minion target
        }
        if(targetH != null && selectedCard != null && selectedCard.isTargeted){
            selectedCard.castOnHero(targetH);
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
    }
    
       @Override
    public void keyPressed(KeyEvent e) {
        if (keyTimer > 0) {
            return;
        }
        switch (e.getKeyChar()) {
            case ' ':
                Board.controller.nextTurn();
                break;
            case 'q':
                for(Card c : Board.playerHero.hand){
                    System.out.println(c + " == " + AI.AI.getValueOfCard(c));
                }
        }
        keyTimer = 10;
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
    
        /**
     * returns the hero being rendered at the given coordinates
     * @param x x-coordinate of mouse click AFTER ADJUSTING FOR RESOLUTION SCALING
     * @param y x-coordinate of mouse click AFTER ADJUSTING FOR RESOLUTION SCALING
     * @return hero, either top or bot. null if neither
     */
    public static Hero getHeroAt(double x, double y){
        if(x > 400 && x < 400 + Board.topHero.picture.getWidth() && y > 0 && y < Board.topHero.picture.getHeight()){
            return Board.topHero;
        }
        if(x > 400 && x < 400 + Board.botHero.picture.getWidth() && y > 850 && y < 850 + Board.botHero.picture.getHeight()){
            return Board.botHero;
        }
        return null;
    }
}
