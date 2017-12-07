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

/**
 * Represents the Client that connects to a Server to play with other Clients.
 * @author Knifesurge
 * @since 2017-12-06
 */
public class GameClient extends Thread
{
    private DatagramSocket socket;
    private InetAddress address;
    private int port;
    
    public GameClient(String address)
    {
        try
        {
            Random rand = new Random();
            this.port = rand.nextInt(9999);
            this.address = InetAddress.getByName(address);
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
        while(true)
        {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try
            {
                socket.receive(packet);
            } catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
            System.out.println("SERVER>> " + new String(packet.getData()));
        }
    }
    
    public void sendData(byte[] data)
    {
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        try
        {
            socket.send(packet);
        } catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    
    public InetAddress getAddress()
    {
        return address;
    }
    
    public int getPort()
    {
        return port;
    }
}