/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 *
 * @author Joseph
 */
public class LineManager implements Runnable{
    private static ArrayList<int[]> toRender= new ArrayList<>();
    private int duration; 
    private int[] line;
    
    private LineManager(int dur, int[] line){  
        this.duration = dur;
        this.line = line;
        Thread t= new Thread(this);
        t.start();
    }
    
    public static void drawLine(int x1, int y1, int x2, int y2, int duration){
        int [] toAdd = new int[4];
        toAdd[0]=x1;
        toAdd[1]=y1;
        toAdd[2]=x2;
        toAdd[3]=y2;
        toRender.add(toAdd);
        new LineManager(duration,toAdd);
    }
    
    @Override
    public void run() {
         Main.wait(duration);
         while(toRender.contains(line)){
           try{
               toRender.remove(line);
           }catch(ConcurrentModificationException cme){
               System.out.println("line manager cme");
           }
         }
         
    }
    
    public static void render(Graphics2D g){
        g.setColor(Color.red);
        for(int[] ar : toRender){
            g.drawLine(ar[0], ar[1], ar[2], ar[3]);
        }
    }
    
}
