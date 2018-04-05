/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import AI.AI;
import Minions.Minion;
import Cards.*;
import GUI.DeckLoaderScratch;
import GUI.LegacyGUI;
import Traps.TrapHolder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * the player character, takes direct attacks
 * @author Joseph
 */
public class Hero {
    /*   STATICS   */
    public static int maxHandSize = 6; //max amount of cards to have in your hand
    private static int idBank = 0;     //determines what id a hero will have
    public static final int MAX_POSSIBLE_RESOURCE = 10; //max resource per turn cap
    public static boolean endGameOnDeath = true;
    /*   FIELDS   */
    public int health = 30;
    public int maxHealth = 30;
    public int resource, maxResource;   //mana used to play cards
    public BufferedImage picture; //visual representation
    public String name;
    public PlayArea<Minion> minions = new PlayArea<Minion>();
    public TrapHolder traps = new TrapHolder();
    public ArrayList<Card> hand = new ArrayList<>();
    public ArrayList<Card> deck;
    public int id;
    public int procTimer=0; //used for green proc animation
    public Hero opponent;
    public int damageTicker = 0; //used to apply the red on damage effect
    public boolean turn = false; //is it our turn?
    public boolean isAIControlled = false;
    private boolean available = true;//makes it so we can only restart once at a time
    private boolean hasNotifiedDeckEmpty = false; //has the player been alerted that their deck is empty?
       
    //CONSTRUCTOR
    public Hero(String name, ArrayList<Card> deck, BufferedImage portrait){
        this.name = name;
        this.deck = deck;
        this.picture = portrait;
        this.resource = 1;
        this.maxResource = 1;
        for(Card c: deck){
            c.setHero(this);
        }
        shuffle();
        id = idBank++;
    }
    
    
    public void setAIControlled(boolean b){
        this.isAIControlled = b;
    }
    
    
    /**
     * Attempts to put card from top of deck into hand. 
     * If hand is too full, the top card of the deck is simply discarded.
     */
    public void draw(){
        if(deck.size() == 0){
            if(!this.hasNotifiedDeckEmpty && this==Board.playerHero){
                JOptionPane.showMessageDialog(null, "No more cards in deck!");
                hasNotifiedDeckEmpty = true;
            }
            System.out.println(this + " deck empty");
            return;
        }
        if(hand.size()>= maxHandSize){// if we have too many cards in hand
            System.out.println(("player Milled Card: ") + deck.remove(0));
        }else{
            hand.add(deck.remove(0));
        }
        System.out.println("deck size: " + deck.size());
    }
    /**
     * attempts to draw specific card
     * @param c 
     */
    public void draw(Card c){
        if(hand.size() >= maxHandSize){
             System.out.println("hand too full to draw " + c);
            return;
        }else{
            c.setHero(this);
            hand.add(c);
        }
    }
    
    /**
     * randomizes the card order
     */
    public void shuffle() {
        ArrayList<Card> temp = new ArrayList<>();
        Main.wait(10);
        while (!deck.isEmpty()) {
            temp.add(deck.remove((int) (Math.random() * deck.size())));
        }
        deck = temp;
       
    }
    
    public void onTurnStart(){
        System.out.println("on turn start: " + this);
        if(Board.playerHero == this) InputHandler.enablingTimer=20;
        ArrayList<Minion> done = new ArrayList<>();
        while(true){
        try{
        for(Minion m : minions.getStorage()){
            if(m!=null && !done.contains(m)){
                m.onTurnStart();
                done.add(m);
                
            }
        }
        break;
        }catch(ConcurrentModificationException cme){
            System.out.println("caught concurrent modication exception in Hero.onTurnStart()");
            cme.printStackTrace();
        }
        }
        this.draw();
        if(this.maxResource<Hero.MAX_POSSIBLE_RESOURCE) this.maxResource++;
        this.resource=maxResource;
        this.turn = true;
        if(this.isAIControlled){
            AI.takeTurn(this);
        }
    }
    
    public void onTurnEnd() {
        if(Board.playerHero == this) InputHandler.enabled=false;
        ArrayList<Minion> done = new ArrayList<>();
        while (true) {
            try {
                for (Minion m : minions.getStorage()) {
                    if (m != null && !done.contains(m)) {
                        m.onTurnEnd();
                        done.add(m);
                    }
                }
                break;
            } catch (ConcurrentModificationException cme) {
                System.out.println("cme at hero 134");
            }
            
        }
        this.turn=false;
    }
    
    
    public synchronized void takeDamage(int amount){
        this.health-=amount;
        this.damageTicker = 20;
        new ProcHandler(this);
        if(health <= 0){
            destroy();
        }
    }
    
    /**
     * what happens when the hero dies. usually ends the game
     * TODO
     */
    public synchronized void destroy(){
        if(!available)return;
        available = false;
        if(!endGameOnDeath)return;
        if (this == Board.playerHero) {
            Main.display("Game over!");
            int selection = JOptionPane.showConfirmDialog(null, "Restart?");
            if(selection == 0){
                try{
                this.restartApplication();
                }catch(Exception e){
                    e.printStackTrace();
                    System.exit(1);
                }
            }else{
                 System.exit(0);
             }
        }
        if(this == Board.nonPlayerHero){
            Main.display("Victory!");
             int selection = JOptionPane.showConfirmDialog(null, "Restart?");
             if(selection==0){
                 try{
                 restartApplication();
                 }catch(Exception e){
                     e.printStackTrace();
                     System.exit(1);
                 }
             }else{
                 System.exit(0);
             }
        }
    }

    public void restartApplication() {
        Board board = Board.getMainBoard();
        board.running= false;
        Window window = board.window;
        JFrame frame = window.frame;
        if(frame==null){
            System.out.println("frame is null");
            System.exit(1);
        }else{
            frame.dispose();
            board.running= false;
            new LegacyGUI();
            //available = true;
        }
    }
    
    /**
     * adds health to a hero up to their maximum health
     * @param amount 
     */
    public void heal(int amount) {
        if (amount + health >= maxHealth) {
            health = maxHealth;
            return;
        } else {
            health += amount;
        }
    }

    public void render(Graphics2D g, int x, int y){
        g.drawImage(picture, x, y, null);
        g.setColor(Color.red);
        g.drawString(this.health + "/" + this.maxHealth, x, y+picture.getHeight()-10);
        for(int i = 0; i < this.maxResource; i++){
            if(i>this.resource-1){
                g.drawImage(SpriteHandler.emptyCrystal, x+(i*50), y, null); //if they are spent resources render differently
            }else{
                g.drawImage(SpriteHandler.fullCrystal, x + (i*50), y, null); //if they are unspent reources
            }
        }
        if(this.turn){
            g.setColor(new Color(255,255,255,100));
            g.drawImage(SpriteHandler.leftArrow,x + picture.getWidth(), y,null);
            if(Board.playerHero==this){
                Font original = g.getFont();
                Font toUse = new Font("Arial", Font.BOLD, 18);
                g.setFont(toUse);
                g.setColor(Color.white);
                g.drawString("End Turn", x+picture.getWidth() + 25, y + SpriteHandler.leftArrow.getHeight()/2 + 5);
                g.setFont(original);
            }
        }
        if(this.damageTicker > 0){
        g.setColor(new Color(255,0,0,(this.damageTicker)*12));
        //damageTicker--;
        g.fillRect(x, y, picture.getWidth(), picture.getHeight());
        }
        if (this.procTimer > 0) {
           //procTimer--;
            g.setColor(new Color(0, 255, 0, procTimer * 10));
            g.fillRect(x, y, this.picture.getWidth(), picture.getHeight());
        }
        Font prevFont = g.getFont();
        g.setColor(Color.black);
        g.setFont(DeckLoaderScratch.detailFont);
        g.drawString(deck.size() + " cards in deck", x+picture.getWidth(), y+picture.getHeight()/2);
        g.setFont(prevFont);
    }
    
    /**
     * temporarily makes the hero tint green
     */
    public void proc(){
        this.procTimer = 20;
        new ProcHandler(this);
    }
    
    @Override
    public String toString(){
        return this.name;
    }
    
    /**
     * gets the x axis coordinate that the hero is being rendered at
     * @return 
     */
    public int getXCoordinate(){
        return 400;
    }
    /**
     * gets the y coordinate the hero is being rendered at.
     * -1 if error
     * @return 
     */
    public int getYCoordinate(){
        if(this == Board.topHero) return 0;
        if(this == Board.botHero) return 850;
        return -1;
    }
}
