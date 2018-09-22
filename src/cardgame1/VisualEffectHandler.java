/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import Cards.Card;
import Minions.Minion;
import Traps.Trap;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.LinkedList;

/**
 *
 * @author Joseph
 */
public class VisualEffectHandler {
    public Board board = null;
    private int timeMousedOver = 0; //how long the cursor has been on a particular minion, for use in drawMouseOverCard method
    private Minion mousedOverMinion = null; //currently moused over minion, for use in drawMouseOverCard method
    private Trap mousedOverTrap = null;
    private static Card currentlyDisplayed = null; //currently presented card used for Ai playing cards
    private static int timeLeftOnCard = 0;
    public static LinkedList<Sticker> stickers = new LinkedList<>();
    VisualEffectHandler(Board b){
        board = b;
    }
    public void resetMouseOverTime(){
        timeMousedOver= 0;
    }
    public void render(Graphics2D g){
        drawLineM(g);  //draw line from selected minion
        drawLineC(g);  //draw line from selected card
        drawMouseOverCard(g);
        drawMouseOverTrap(g);
        drawGivenCard(g);
        renderStickers(g);
        renderOptionsMouseOverText(g);
    }
    
    /**
     * writes "Options" mouse over text for the little gear at the top left of the board
     * @param g
     */
    private void renderOptionsMouseOverText(Graphics2D g) {
        if (Board.mouseX < 35 && Board.mouseY < 35) {
            Font prev = g.getFont();
            g.setFont(new Font("Arial",Font.BOLD,18));
            g.drawString("Options", Board.mouseX, Board.mouseY + 35);
        }
    }
    
    public void tick(){
        
    }
    
    public void renderStickers(Graphics2D g){
        for(Sticker s : stickers){
            s.render(g);
        }
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
        if(timeMousedOver > NUM_TICKS_TO_WAIT && mousedOverMinion!=null && Board.playerHero.turn){
            mousedOverMinion.parent.render(g, Board.mouseX, Board.mouseY, true);
        }
    }
    
    public void drawMouseOverTrap(Graphics2D g){
        int NUM_TICKS_TO_WAIT = 45; //how many frames to wait before we render the moused over trap
        if(mousedOverTrap != InputHandler.getTrapAt(Board.mouseX, Board.mouseY)){
        mousedOverTrap = InputHandler.getTrapAt(Board.mouseX, Board.mouseY);
        timeMousedOver = 0;
        }
        if(mousedOverTrap!= null && mousedOverTrap == InputHandler.getTrapAt(Board.mouseX, Board.mouseY)){
            timeMousedOver++;
        }
        if(timeMousedOver > NUM_TICKS_TO_WAIT && mousedOverTrap!=null && Board.playerHero.turn){
            //time to render the card, but only reveal what it is if it is the player's
            int toRenderY = Board.mouseY;
            if(mousedOverTrap.owner == Board.botHero) toRenderY -= Card.HEIGHT; //draw it higher than usual if its on the bottom of the screen
            if(mousedOverTrap.owner == Board.playerHero){
                mousedOverTrap.renderRevealed(g, Board.mouseX, toRenderY);
            }else{
                g.drawImage(SpriteHandler.trapPlaceholder,Board.mouseX,toRenderY,null);
            }
        }
    }
    
    /**
     * draws line from selected minion to location of the mouse
     * @param g 
     */
    public void drawLineM(Graphics2D g){
        Minion selected = InputHandler.selectedMinion;
        if(selected == null) return;
        g.drawLine(selected.getXCoordinate()+Minion.WIDTH/2, selected.getYCoordinate()+Minion.HEIGHT/2, Board.mouseX, Board.mouseY);
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
    /**
     * 
     */
    private void drawGivenCard(Graphics2D g) {
        if(timeLeftOnCard > 0 && currentlyDisplayed !=null){
            timeLeftOnCard--;
            currentlyDisplayed.render(g, 1700, 200, true);
        }
    }
    /**
     * displayed a card to the player for a duration
     * @param c the card
     * @param duration how long in ticks
     */
    public static void displayCard(Card c, int duration){
            currentlyDisplayed = c;
            timeLeftOnCard = duration;
    }


    

}
