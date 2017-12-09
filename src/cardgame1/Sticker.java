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

/**
 * an image overlay that renders on the field for visual effects
 * @author Joseph
 */
public class Sticker implements Runnable{
    /*  FIELDS    */
    public BufferedImage image = null;
    public int x,y;
    public boolean disabled = false;
    public int timeToRender;
    
    protected void render(Graphics2D g){
        if(x < 0 || y < 0) disable();     //if the coordinates are bad, dont render
        if(!disabled)g.drawImage(image, x, y, null);
    }
    /**
     * creates a sicker and renders it at a minion's location for duration
     */
    public Sticker(BufferedImage i, Minion m, int duration){
        image = i;
        int mx = m.getXCordinate();
        int my = m.getYcoordinate();
        x = mx + Minion.WIDTH/2 - i.getWidth()/2;
        y = my + Minion.HEIGHT/2 - i.getHeight()/2;
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
    public Sticker(BufferedImage i, int x, int y, int duration){
        image = i;
        this.x = x - i.getWidth()/2;
        this.y = y - i.getHeight()/2;
        if(x > 0 && y > 0)timeToRender = duration;
        VisualEffectHandler.stickers.add(this);
        Thread t = new Thread(this);
        t.start();
    }
     /**
     * creates a sicker and renders it at a card's location for duration
     */
    public Sticker(BufferedImage i, Card c, int duration){
        image = i;
        int cx = c.getXCoordinate();
        int cy = c.getYCoordinate();
        x = cx + Card.WIDTH/2  - i.getWidth()/2;
        y = cy + Card.HEIGHT/2 - i.getHeight()/2;
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
        x = hx + h.picture.getWidth()/2  - i.getWidth()/2;
        y = hy + h.picture.getHeight()/2 - i.getHeight()/2;
        timeToRender = duration;
        VisualEffectHandler.stickers.add(this);
        Thread t = new Thread(this);
        t.start();
    }
    
    public void disable(){
        disabled = true;
    }

    @Override
    public void run() {
        System.out.println("starting run");
        Main.wait(timeToRender);
        System.out.println("disabling");
        disable();
    }
}
