/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multi.network.packet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import multi.network.GameClient;
import multi.network.GameServer;

/**
 *
 * @author Knifesurge
 * @since 2017-12-06
 */
public abstract class Packet {
    
    private byte packetID;
    
    public Packet(byte id)
    {
        packetID = id;
    }
    
    public abstract void writeData(GameClient client);
    public abstract void writeData(GameServer server);
    public abstract byte[] getData();
    
    public static String readData(byte[] data)
    {
        String strData = new String(data).trim();
        return strData.substring(0, 2);
    }
    
    public static PacketType lookupPacket(int packetID)
    {
        for(PacketType type : PacketType.values())
        {
            if(type.getID() == packetID)
                return type;
        }
        
        return PacketType.INVALID;
    }
    
    public static PacketType lookupPacket(String packetID)
    {
        try
        {
            return lookupPacket(Integer.parseInt(packetID));
        } catch(NumberFormatException nfe)
        {
            nfe.printStackTrace();
        }
        return PacketType.INVALID;
    }
}