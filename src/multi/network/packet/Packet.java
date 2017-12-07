/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multi.network.packet;

import multi.network.packet.types.PacketType;
import multi.network.GameClient;
import multi.network.GameServer;

/**
 *
 * @author Knifesurge
 * @since 2017-12-06
 */
public abstract class Packet {
    
    /**
     * The ID of our packet
     */
    private byte packetID;
    
    /**
     * Constructor. Takes a byte ID
     * @param id 
     */
    public Packet(byte[] id)
    {
        packetID = id[0];
    }
    
    public abstract void writeData(GameClient client);
    public abstract void writeData(GameServer server);
    public abstract byte[] getData();
    
    /**
     * Parse the byte[] of data that is received and return everything AFTER the ID of the Packet
     * @param data
     * @return the contents of the Packet
     */
    public static String readData(byte[] data)
    {
        // Convert data to String representation and remove any trailing or leading whitespace
        String strData = new String(data).trim();
        // Return everything AFTER the ID
        return strData.substring(2);
    }
    
    /**
     * Determine the PacketID of the packet based on the provided int representation of the packetID
     * @param packetID - The packet ID of the packet
     * @return PacketType with an ID of packetID. Returns PacketType#INVALID if invalid ID passed.
     */
    public static PacketType lookupPacket(int packetID)
    {
        // For each PacketType in the PacketType enum
        for(PacketType type : PacketType.values())
        {
            // If the ID of the current PacketType matches the passed packetID
            if(type.getID() == packetID)
                // Return it
                return type;
        }
        // Invalid PacketID, so return an INVALID PacketType
        return PacketType.INVALID;
    }
    
    /**
     * Determine the PacketID of the packet based on the provided String representation of the packetID. Just parses the String for int
     * representation and passes it to Packet#lookupPacket(int packetID)
     * @param packetID - The packet ID of the packet as a String
     * @return PacketType with an ID of Integer.parseInt(packetID). Returns PacketType#INVALID if invalid ID passed.
     */
    public static PacketType lookupPacket(String packetID)
    {
        try
        {
            // Use the other method
            return lookupPacket(Integer.parseInt(packetID));
        } catch(NumberFormatException nfe)
        {
            nfe.printStackTrace();
        }
        // Invalid PacketID, so return an INVALID PacketType
        return PacketType.INVALID;
    }
}