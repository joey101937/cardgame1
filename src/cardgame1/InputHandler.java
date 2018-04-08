/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import AI.AI;
import Cards.Card;
import GUI.LegacyGUI;
import GUI.SettingsPane;
import Minions.Minion;
import Traps.Trap;
import Traps.TrapHolder;
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
    public static boolean enabled = true;
    public static int enablingTimer = 0; //used to delay reactivation
    /**
     * ticks every gametime second
     */
    public static void tick(){
        if(keyTimer > 0){
            keyTimer--;
        }
        if(enablingTimer > 0){
            enablingTimer--;
        }else{
            if(!enabled && Board.playerHero.turn) enabled =true;
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        double x = e.getX() / Board.xScale;
        double y = e.getY() / Board.yScale;
        System.out.println(e.getX()/Board.xScale + ", " + e.getY()/Board.yScale);
        if(!Board.playerHero.turn || !enabled) return;  //if its not our turn, ignore it
        Card clickedCard = InputHandler.getCardAt(e.getX()/Board.xScale, e.getY()/Board.yScale);
        if(clickedCard != null && clickedCard.isTargeted == false){
            clickedCard.cast(null); //cast with null param becuase there is no target
        }
        if(x < 35 && y < 35){//options gear is in top left, 0,0 and is 35x35
            LegacyGUI.settings = new SettingsPane();
        }
        if(x > 400 + Board.playerHero.picture.getWidth() && x < 400 + Board.playerHero.picture.getWidth() + SpriteHandler.leftArrow.getWidth()){
             if(Board.playerHero == Board.botHero){
                 if(y > 850 && y < 850 + SpriteHandler.leftArrow.getHeight()){
                     onEndTurn();
                 }
             }else{
                 //player is tophero
                 if(y < SpriteHandler.leftArrow.getHeight()){
                     onEndTurn();
                 }
             }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        double x = e.getX() / Board.xScale;
        double y = e.getY() / Board.yScale;
        System.out.println(getMinionAt(x,y));
        System.out.println(getCardAt(x,y));
        if(!Board.playerHero.turn || !enabled) return;
        if(getMinionAt(x,y) !=null){
         if(getMinionAt(x,y).owner == Board.playerHero)InputHandler.selectedMinion = getMinionAt(x,y);   
        }
        if(getCardAt(x,y) != null){
          if(getCardAt(x,y).getOwner() == Board.playerHero)InputHandler.selectedCard = getCardAt(x,y);          
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!Board.playerHero.turn || !enabled) return;
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
    /**
     * code to be executed whenever the player attempts to end his turn.
     */
    private synchronized static void onEndTurn() {
        if (keyTimer > 0) {
            System.out.println("Key pressed to fast");
            return;
        }
        Board.visHandler.resetMouseOverTime();
        Board.controller.nextTurn();
        if(Main.isMulitiplayerGame)Board.nonPlayerHero.getPhantom().communicateMessage("end");
        keyTimer = 15;
    }

       @Override
    public void keyPressed(KeyEvent e) {
        if (keyTimer > 0) {
            System.out.println("Key pressed to fast");
            return;
        }
        switch (e.getKeyChar()) {
            case ' ':
           // Board.controller.nextTurn();
            onEndTurn();
                break;
            case 'q':
                for(Card c : Board.playerHero.hand){
                    System.out.println(c + " == " + AI.getValueOfCard(c));
                }
            break;
            case 'w':
                for(Card c : Board.playerHero.hand){
                   if(c.canAfford()){
                       System.out.println("Value of " + c +" : " + AI.getValueOfCard(c) + "/" + c.cost);
                   }
                }
                break;
            case 'c':
                for(Card c : Board.nonPlayerHero.hand){
                    System.out.println(c+" : " + AI.getValueOfCard(c) + "/" + c.cost);
                }
                break;
            case 'k':
                 AI.takeTurn(Board.playerHero);
                 break;
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
    /**
     * returns the trap being rendered at the specified coordinates
     * @param x x-coordinate of mouse click AFTER ADJUSTING FOR RESOLUTION SCALING
     * @param y x-coordinate of mouse click AFTER ADJUSTING FOR RESOLUTION SCALING
     * @return Trap at location. null if none
     */
    public static Trap getTrapAt(double x, double y){
        if(y>100 && y < 50+SpriteHandler.trapSymbol.getHeight()){
            //top hero area
            for(int i = 0; i < TrapHolder.MAX_SIZE; i++){
                int pos = 50 + (SpriteHandler.trapSymbol.getWidth() * i) + ((i * Board.buffer)); //x-coordinate of the top left corner of the trap
                if(x > pos && x < pos + SpriteHandler.trapSymbol.getWidth()){
                    return Board.topHero.traps.get(i);
                }
            }
        } else if (y > 925 && y < 925 + SpriteHandler.trapSymbol.getHeight()) {
            //botHero area
            for (int i = 0; i < TrapHolder.MAX_SIZE; i++) {
                int pos = 50 + (SpriteHandler.trapSymbol.getWidth() * i) + ((i * Board.buffer)); //x-coordinate of the top left corner of the trap
                if (x > pos && x < pos + SpriteHandler.trapSymbol.getWidth()) {
                    return Board.botHero.traps.get(i);
                }
            }
        }
        return null;
    }
}
