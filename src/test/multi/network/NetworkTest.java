/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.multi.network;

import java.awt.Canvas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import multi.network.GameClient;
import multi.network.GameServer;
import multi.network.packet.types.Connect01Packet;
import multi.network.packet.types.Disconnect02Packet;

/**
 *
 * @author Knifesurge
 * @since 2017-12-06
 */
public class NetworkTest extends Canvas implements Runnable{
    
    GameServer server = null;
    GameClient client = null;
    
    private static final String TITLE = "Networking Testing";
    
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
        frame = new JFrame(TITLE);
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 400);
        frame.setVisible(true);
        
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent event)
            {
                System.out.println("Window closing!");
                Disconnect02Packet discPacket = new Disconnect02Packet(client.getUsername());
                discPacket.writeData(client);
                System.exit(0);
            }
        });
        
        System.out.println("Create and start Client");
        client = new GameClient("localhost");   // Client runs localhost
        
        if(JOptionPane.showConfirmDialog(this, "Run the server?") == 0)
        {
            System.out.println("Start Server");
            String ipAddress = JOptionPane.showInputDialog(this, "Enter ip address to bind to: ");
            if(ipAddress.equals("") || ipAddress == null)
                server = new GameServer("localhost");
            else
                server = new GameServer(ipAddress);
            frame.setTitle(TITLE + " - Server");
            server.start();
            
            System.out.println("Server started on [" + server.getAddress() + ":" + server.getPort() + "]");
            
            client.setServerAddress(server.getAddress());
        } else
        {
            String ipAddress = JOptionPane.showInputDialog(this, "Enter IP Address of Server to connect to: ");
            if(ipAddress.equals("") || ipAddress == null)
                client.setServerAddress(client.getAddress());
            else
                client.setServerAddress(ipAddress);
            
            
        }
        
        client.setServerPort(9456); // 9456 will ALWAYS be the Server's port
        client.start();
    }
 
    @Override
    public void run()
    {
        init();
        
        Connect01Packet packet = new Connect01Packet(JOptionPane.showInputDialog(this, "Please enter a username"));
        client.setUsername(packet.getUsername());
        packet.writeData(client);
    }
 
    public GameServer getServer()
    {
        return server;
    }
    
    public GameClient getClient()
    {
        return client;
    }
    
    public static void main(String[] args)
    {
        new NetworkTest().start();
    }
    
}
