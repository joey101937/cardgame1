/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;


import Cards.Card;
import Minions.Minion;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;

/**
 * Renders an image at a location for a given length of time
 * @author Joseph
 */
public class Sticker implements Runnable{
    public BufferedImage image;
    public Coordinate spawnLocation = new Coordinate(0,0);
    protected Coordinate renderLocation = new Coordinate(0,0);
    public boolean disabled = false;
    public int timeToRender;
    public Card toRender = null;

    /**
     * @param i Image to display
     * @param c location to display
     * @param duration how long to display
     */
    public Sticker(BufferedImage i, Coordinate c, int duration){
        image = i;
        spawnLocation = new Coordinate(c);//where we want the sticker
        timeToRender = duration; //topleft location of sticker used to put center on spawnLocation
        VisualEffectHandler.stickers.add(this);
        Thread t = new Thread(this);
        t.start();
    }
    /**
     * calibrates the render location to center the image on spawn location
     * @param toRender 
     */
    protected void centerCoordinate(BufferedImage toRender) {
        try{
        renderLocation.x = spawnLocation.x - toRender.getWidth() / 2;
        renderLocation.y = spawnLocation.y - toRender.getHeight() / 2;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    
        /**
     * creates a sicker and renders it at a minion's location for duration
     */
    public Sticker(BufferedImage i, Minion m, int duration){
        image = i;
        int mx = m.getXCoordinate();
        int my = m.getYCoordinate();
        spawnLocation.x = mx + Minion.WIDTH/2;
        spawnLocation.y = my + Minion.HEIGHT/2;
        if(mx > 0 && my > 0){
            timeToRender = duration;
            VisualEffectHandler.stickers.add(this);
        }  
        Thread t = new Thread(this);
        t.start();
    }

    /**
     * creates a sticker of i image and renders it at (x,y) for duration
     */
    public Sticker(BufferedImage i, int x, int y, int duration) {
        image = i;
        spawnLocation.x = x ;
        spawnLocation.y = y ;
        if (x > 0 && y > 0) {
            timeToRender = duration;
        }
        VisualEffectHandler.stickers.add(this);
        Thread t = new Thread(this);
        t.start();
    }

    /**
     * creates a sicker and renders it at a card's location for duration
     */
    public Sticker(BufferedImage i, Card c, int duration) {
        image = i;
        int cx = c.getXCoordinate();
        int cy = c.getYCoordinate();
        spawnLocation.x = cx + Card.WIDTH / 2 ;
        spawnLocation.y = cy + Card.HEIGHT / 2;
        if (cx > 0 && cy > 0) {
            timeToRender = duration;
            VisualEffectHandler.stickers.add(this);
        }
        Thread t = new Thread(this);
        t.start();
    }
    
    
        public Sticker(BufferedImage i, Hero h, int duration){
        image = i;
        int hx = h.getXCoordinate();
        int hy = h.getYCoordinate();
        spawnLocation.x = hx + h.picture.getWidth()/2  ;
        spawnLocation.y = hy + h.picture.getHeight()/2 ;
        timeToRender = duration;
        VisualEffectHandler.stickers.add(this);
        Thread t = new Thread(this);
        t.start();
}

    /**
     * renders given card at coordinates for given duration
     *
     * @param toDraw
     * @param x
     * @param y
     * @param duration
     */
    public Sticker(Card toDraw, int x, int y, int duration) {
        System.out.println("test toDraw");
        toRender = toDraw;
        spawnLocation.x = x;
        spawnLocation.y = y;
        if (x > 0 && y > 0) {
            timeToRender = duration;
        }
        VisualEffectHandler.stickers.add(this);
        Thread t = new Thread(this);
        t.start();
    }


    public synchronized void render(Graphics2D g) {
        centerCoordinate(image);
        if (spawnLocation.x < 0 || spawnLocation.y < 0) {
            System.out.println("bad location");
            disable();     //if the coordinates are bad, dont render
        }
        if (!disabled) {
           
            if (image != null) {
                g.drawImage(image, renderLocation.x, renderLocation.y, null);
            }
            if (toRender != null) {
                System.out.println("test");
                toRender.render(g, spawnLocation.x, spawnLocation.y, true);
            }
        }
    }

    public void disable() {
        disabled = true;
        while(VisualEffectHandler.stickers.contains(this)){
            try{
                VisualEffectHandler.stickers.remove(this);
            }catch(ConcurrentModificationException cme){
                cme.printStackTrace();
            }
        }
    }

    
    
    
    @Override
    public void run() {
        Main.wait(timeToRender);
        disable();
    }

}
