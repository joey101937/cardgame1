/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multi.network;

import multi.network.packet.types.PacketType;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import multi.MultiplayerObject;
import multi.network.packet.Packet;
import multi.network.packet.types.Connect01Packet;
import multi.network.packet.types.Disconnect02Packet;

/**
 * Represents the Server that the game is running on. GameClients connect to this Server to play
 * 
 * @author Knifesurge
 * @since 2017-12-06
 */
public class GameServer extends Thread{
    
    /** Handles all incoming and outgoing network functions */
    private DatagramSocket socket;
 //TODO:   /** List of connected clients (Might change MultiplayerObject to GameClient, tbd) */
    private List<MultiplayerObject> connected;
    /** Port this server is running on */
    private int port;
    
    /** Constructor. Initializes this Server's DatagramSocket object. 
     * @param address - The address that the Server should bind to
     */
    public GameServer(String address)
    {
        this.port = 9456;
        try
        {
            connected = new ArrayList<>();
            socket = new DatagramSocket(port, InetAddress.getByName(address.trim()));
        } catch(SocketException se)
        {
            se.printStackTrace();
        } catch(UnknownHostException uhe)
        {
            uhe.printStackTrace();
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
                Connect01Packet packetConnect = new Connect01Packet(data);
                MultiplayerObject obj = new MultiplayerObject(address, port, packetConnect.getUsername());
                this.connected.add(obj);
                System.out.println("<<SERVER>> " + "[" + address.getHostAddress() + ":" + port + "] " + packetConnect.getUsername() + " has connected!");
                sendDataToAll(("[" + address.getHostAddress() + ":" + port + "] " + packetConnect.getUsername() + " has connected!").getBytes());
                break;
            case DISCONNECT:
                Disconnect02Packet packetDisconnect = new Disconnect02Packet(data);
                MultiplayerObject object = new MultiplayerObject(address, port, packetDisconnect.getUsername());
                this.connected.remove(object);
                System.out.println("<<SERVER>> " + "[" + address.getHostAddress() + ":" + port + "] " + packetDisconnect.getUsername() + " has disconnected!");
                sendDataToAll(("[" + address.getHostAddress() + ":" + port + "] " + packetDisconnect.getUsername() + " has disconnected!").getBytes());
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
            parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
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
        // Use a dead-ass lambda expression to eliminate most of the boilerplate code *dab*. Sends data to each Client
        connected.forEach(player -> sendData(data, player.getAddress(), player.getPort()));
    }
    
    public InetAddress getAddress()
    {
        return socket.getLocalAddress();
    }
    
    /**
     * Returns the port that this Server is running on
     * @return the port that the Server is running on
     */
    public int getPort()
    {
        return port;
    }
}