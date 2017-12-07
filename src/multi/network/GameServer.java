/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multi.network;

import multi.network.packet.PacketType;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import multi.MultiplayerObject;
import multi.network.packet.Packet;

/**
 * Represents the Server that the game is running on. GameClients connect to this Server
 * 
 * @author Knifesurge
 * @since 2017-12-06
 */
public class GameServer extends Thread{
    
    /** Handles all incoming and outgoing network functions */
    private DatagramSocket socket;
    /** List of connected clients (Might change MultiplayerObject to GameClient, tbd) */
    private List<MultiplayerObject> connected;
    /** Port this server is running on */
    private int port;
    
    /** Constructor. Initializes this Server's DatagramSocket object. 
     * @param port - The port to run this Server on.
     */
    public GameServer(int port)
    {
        this.port = port;
        try
        {
            connected = new ArrayList<>();
            socket = new DatagramSocket(port);
        } catch(SocketException se)
        {
            se.printStackTrace();
        }
    }
    
    /**
     * Determines which PacketType based on data passed and handles events based on that type.
     * @param data - byte[] of data held by the received packet
     * @param address - address of the Client that sent the packet
     * @param port - port of the Client that sent the packet
     */
    private void parsePacket(byte[] data, InetAddress address, int port)
    {
        // Take String representation of data and strip leading and trailing whitespace
        String strData = new String(data).trim();
        PacketType type = Packet.lookupPacket(strData.substring(0,2));
        switch(type)
        {
            case CONNECT:
                break;
            case DISCONNECT:
                break;
            case INVALID:
                break;
        }
    }
    
    @Override
    public void run()
    {
        // Server runs until thread is terminated
        while(true)
        {
            // Holds the data to be received
            byte[] data = new byte[1024];
            // Packet being received (holds the byte[] just made)
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try
            {
                // Receive the packet created above
                socket.receive(packet);
            } catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
            String msg = new String(packet.getData()).trim();
            System.out.println("CLIENT >> " + msg);
            if(msg.equals("Hello!"))
                sendData("Why hello there!".getBytes(), packet.getAddress(), packet.getPort());
            // Determine which PacketType the packet is and continue accordingly
//                parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
        }
    }
    
    /**
     * Send data to a Client specified by the InetAddress provided, on the port provided.
     * @param data - byte[] of data to send
     * @param address - address of Client to send to
     * @param port - port of Client to send on
     */
    public void sendData(byte[] data, InetAddress address, int port)
    {
        // Create a packet to send, filling it with our data and Client information
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        try
        {
            // Send the packet with on our socket
            socket.send(packet);
        } catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    
    /**
     * Send data to all connected Clients
     * @param data - byte[] of data to send
     */
    public void sendDataToAll(byte[] data)
    {
        // Use a lambda expression to eliminate most of the boilerplate code. Sends data to each Client
        connected.forEach(player -> sendData(data, player.getAddress(), player.getPort()));
    }
}