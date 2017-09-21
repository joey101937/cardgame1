/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Joseph
 */
public class Board extends Canvas implements Runnable{
    /* FIELDS */
    Window window;  //main window
    boolean running = false;
    Thread thread = null;
    Dimension d;
    InputHandler ih = new InputHandler();
    /* CONSTRUCTOR */
    public Board(){
        d = Main.takeWindowSize();
        System.out.println("Dimenstion: " + d.toString());
        Window window = new Window(d.width,d.height,"Card Game",this);
        this.addMouseListener(ih);
        this.addMouseMotionListener(ih);
        this.addKeyListener(ih);
    }
    
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) { ///run once at the start
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.red);
        g.drawImage(Main.BackgroundImage, 0, 0, null);
       g.dispose();
       bs.show();
    }
    
    public void tick(){
        
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
