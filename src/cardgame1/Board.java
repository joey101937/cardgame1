/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import Cards.*;
import Minions.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/*
 * @author Joseph
 */
public class Board extends Canvas implements Runnable {
    /* FIELDS */
    public static Hero topHero; //hero on top side of board
    public static Hero botHero; //hero on bottom side of board
    public static Hero playerHero; //user's hero
    public static Minion selectedMinion;
    public static double xScale, yScale; //scale of the window reletive to testing
    public static int mouseX, mouseY;
    public static int buffer = 50; //extra space between rendered cards in hand
    public VisualEffectHandler visHandler = null;
    public Window window;  //main window
    private boolean running = false;
    private Thread thread = null;
    public Dimension d;
    public InputHandler ih = new InputHandler();
    public static GameController controller;
    /* CONSTRUCTOR */

    public Board(Hero t, Hero b) {
        d = Main.takeWindowSize();
        xScale = (d.getWidth()/1920);
        yScale = d.getHeight()/1080;
        System.out.println("Scale; " + xScale + ", " + yScale);
        System.out.println("Dimenstion: " + d.toString());
        Window window = new Window(d.width, d.height, "Card Game", this);
        topHero = t;
        botHero = b;
        playerHero = b;
        this.addMouseListener(ih);
        this.addMouseMotionListener(ih);
        this.addKeyListener(ih);
        this.visHandler = new VisualEffectHandler(this);
        this.controller = new GameController(this);
        //setupTest();
        controller.startGame();
    }

    /**
     * prepopulates the board and makes heros draw for testing purposes
     */
    private void setupTest() {
        topHero.minions.add(new FrostBearMinion(new FrostBearCard()));
        topHero.minions.add(new ArakkoaMinion(topHero.deck.get(0)));
        topHero.minions.add(new ArakkoaMinion(topHero.deck.get(0)));
        botHero.minions.add(new ArakkoaMinion(botHero.deck.get(0)));
        Board.topHero.draw();
        Board.botHero.draw();
        Board.botHero.draw();
        Board.botHero.draw();
        Board.botHero.draw();
    }


    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) { ///run once at the start
            this.createBufferStrategy(3);
            return;
        }
        Graphics gr = bs.getDrawGraphics();
        Graphics2D g = (Graphics2D)gr;
        g.scale(xScale,yScale);
        g.setColor(Color.white);
        g.drawImage(Main.BackgroundImage, 0, 0, null);
        g.setFont(new Font("TimesRoman", Font.BOLD, 35));
        renderMinions(g);
        renderPlayerHand(g);
        renderEnemyHand(g);
        renderHeros(g);
        this.visHandler.render(g);
        g.dispose();
        bs.show();
    }
    /**
     * renders the cardbacks of the enemy's cards
     * @param g 
     */
    private void renderEnemyHand(Graphics2D g){
        //TODO
    }
    
    private void renderHeros(Graphics2D g){
        Board.topHero.render(g, 400,0);
        Board.botHero.render(g, 400, 850);
    }
    
    private void renderPlayerHand(Graphics2D g){
        ArrayList<Card> leftColumn = new ArrayList<>();
        ArrayList<Card> rightColumn = new ArrayList<>();
        for(Card c : Board.playerHero.hand){
            if(Board.playerHero.hand.indexOf(c) % 2 == 0){
                leftColumn.add(c);
            }else{
                rightColumn.add(c);
            }
        }
        for(Card c : leftColumn){
            c.render(g, 1100, 25 + (Board.buffer * leftColumn.indexOf(c) + (Card.HEIGHT * leftColumn.indexOf(c))),false);
        }
        for (Card c : rightColumn) {
            c.render(g, 1100 + Card.WIDTH + Board.buffer,  25 + (Board.buffer * rightColumn.indexOf(c) + (Card.HEIGHT * rightColumn.indexOf(c))),false);
        }
    }
    /**
     * renders all minions in play, arranged in a line
     * @param g board graphics
     */
    private void renderMinions(Graphics2D g) {
        for (Minion m : topHero.minions.getStorage()) {
            if (m == null) {
                continue;
            }
            m.render(g, topHero.minions.indexOf(m) * (Minion.WIDTH + 100) + 100, Minion.TOP_Y_OFFSET);
        }
        for (Minion m : botHero.minions.getStorage()) {
            if (m == null) {
                continue;
            }
            m.render(g, botHero.minions.indexOf(m) * (Minion.WIDTH + 100) + 100, Minion.BOT_Y_OFFSET);
        }
    }
    
    public void tick() {
        this.visHandler.tick();
        InputHandler.tick();
    }

    //Core game loop 
    @Override
    public void run() {
        this.requestFocus(); ///automatically selects window so you dont have to click on it
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                try{
                this.render();
                }catch(ConcurrentModificationException cme){
                    cme.printStackTrace();
                }
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
                ///this triggers once a second

            }
        }
        stop();
    }

    //starts the main game

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    ///stops the main game
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
