/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import Minions.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Joseph
 */
public class Board extends Canvas implements Runnable {
    /* FIELDS */
    public static Hero topHero; //hero on top side of board
    public static Hero botHero; //hero on bottom side of board
    public static Hero playerHero; //user's hero
    public static double xScale, yScale; //scale of the window reletive to testing
    Window window;  //main window
    boolean running = false;
    Thread thread = null;
    Dimension d;
    InputHandler ih = new InputHandler();
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
        topHero.minions.add(new ArakkoaMinion(null));
        topHero.minions.add(new ArakkoaMinion(null));
        topHero.minions.add(new ArakkoaMinion(null));
        botHero.minions.add(new ArakkoaMinion(null));
        botHero.minions.add(new ArakkoaMinion(null));
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
        //render top and bot hero's minions in a line
        for(Minion m : topHero.minions){
            m.render(g, topHero.minions.indexOf(m)*(Minion.WIDTH+100) + 100, 200);
        }
        for(Minion m : botHero.minions){
             m.render(g, botHero.minions.indexOf(m)*(Minion.WIDTH+100) + 100, 655);
        }
        g.drawImage(topHero.picture, 710, 0, null);
        g.drawImage(SpriteHandler.cardbackL,800,500,null);
        g.dispose();
        bs.show();
    }
    
    public void tick() {

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
                this.render();
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
