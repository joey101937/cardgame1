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
import java.util.List;

/**
 *
 * @author Knifesurge
 * @since 2017-12-06
 */
public class GameServer extends Thread{
    
    private DatagramSocket socket;
    private List<Object> connected;
    private int port;
    
    public GameServer(int port)
    {
        this.port = port;
        try
        {
            socket = new DatagramSocket(port);
        } catch(SocketException se)
        {
            se.printStackTrace();
        }
    }
    
    private void parsePacket(byte[] data, InetAddress address, int port)
    {
        String strData = new String(data).trim();
 //       PacketType type = 
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
            } finally
            {
                parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
            }
        }
    }
    
}