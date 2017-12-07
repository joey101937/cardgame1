/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multi.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents the Client that connects to a Server to play with other Clients.
 * @author Knifesurge
 * @since 2017-12-06
 */
public class GameClient extends Thread
{
    /**
     * Allows the Client to send and receive data
     */
    private DatagramSocket socket;
    /**
     * Address of this Client
     */
    private InetAddress address;
    /**
     * The IP Address of the Server that this Client is connected to.
     */
    private InetAddress serverAddress;
    /**
     * Port of this Client
     */
    private int port;
    /**
     * The port of the Server that this Client is connected to
     */
    private int serverPort;
    /**
     * Username of this Client
     */
    private String username;
    /**
     * Constructor. Chooses a random port to hook to and gets the IP Address by the provided String
     * @param address - IP Address to hook to, if able to
     */
    public GameClient(String address)
    {
        try
        {
            // Create Random (local to this thread!) to randomly choose port
            this.port = ThreadLocalRandom.current().nextInt(2500, 9999);
            // Get the IP Address provided by String address
            this.address = InetAddress.getByName(address.trim());
            // Initialize Socket (chooses random port and random address to hook to)
            this.socket = new DatagramSocket();
        } catch(SocketException se)
        {
            se.printStackTrace();
        } catch(UnknownHostException uhe)
        {
            uhe.printStackTrace();
        }
    }
    
    @Override
    public void run()
    {
        // Loop forever and ever and ever...
        while(true)
        {
            // Byte[] holds the data to be received
            byte[] data = new byte[1024];
            // Create Packet object to receive some yummy data (yum yum)
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try
            {
                // Receive previously mentioned Packet object
                socket.receive(packet);
            } catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
            System.out.println("SERVER>> " + new String(packet.getData()));
        }
    }
    
    /**
     * Sends data with this Client's Socket object.
     * @param data - Data to send away
     */
    public void sendData(byte[] data, int port)
    {
        this.serverPort = port;
        DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverPort);
        try
        {
            System.out.println("Sending DatagramPacket with data: " + new String(data) + "\nTo: " + serverAddress + "\nOn: " + serverPort);
            socket.send(packet);
        } catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    
    /**
     * Returns this Client's IP Addresss
     * @return the IP Address of the Client
     */
    public InetAddress getAddress()
    {
        return address;
    }
    
    public void setServerAddress(String address)
    {
        try
        {
            this.serverAddress = InetAddress.getByName(address);
        } catch(UnknownHostException uhe)
        {
            uhe.printStackTrace();
        }
    }
    
    public void setServerAddress(InetAddress address)
    {
        this.serverAddress = address;
    }
    
    /**
     * Returns this Client's port
     * @return the port of this Client
     */
    public int getPort()
    {
        return port;
    }
    
    public void setServerPort(int port)
    {
        this.serverPort = port;
    }
    
    /**
     * Returns the port of the Server that this Client is connected to
     * @return port of Server
     */
    public int getPortOfServer()
    {
        return serverPort;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getUsername()
    {
        return username;
    }
}