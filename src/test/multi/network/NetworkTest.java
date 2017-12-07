/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.multi.network;

import java.awt.Canvas;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import multi.network.GameClient;
import multi.network.GameServer;

/**
 *
 * @author Knifesurge
 * @since 2017-12-06
 */
public class NetworkTest extends Canvas implements Runnable{
    
    GameServer server;
    GameClient client;
    
    JFrame frame;
    
    Thread thread;
    boolean isRunning;
    
    public synchronized void start()
    {
        if(isRunning) return;
        isRunning = true;
        thread = new Thread(this);
        
        thread.start();
    }
    
    public synchronized void stop()
    {
        if(!isRunning) return;
        try
        {
            thread.join();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void init()
    {
        System.out.println("Init");
        frame = new JFrame("Network Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 400);
        frame.setVisible(true);
        
        if(JOptionPane.showConfirmDialog(this, "Run the server?") == 0)
        {
            System.out.println("Start Server");
            server = new GameServer(9456);
            server.start();
        }
        System.out.println("Create and start Client");
        client = new GameClient("localhost");
        client.start();
    }
 
    @Override
    public void run()
    {
        init();
        
        System.out.println("Client output");
        client.sendData("Hello!".getBytes());
    }
    
    public static void main(String[] args)
    {
        new NetworkTest().start();
    }
    
}
