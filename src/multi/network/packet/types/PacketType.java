/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multi.network.packet.types;

/**
 *
 * @author Knifesurge
 * @since 2017-12-06
 */
public enum PacketType {
    
    INVALID(-1), HANDSHAKE(00), CONNECT(01), DISCONNECT(02), ATTACK(03), MESSAGE(04), PLAY(05);
    
    /**
     * ID of the Packet
     */
    int id;
    
    PacketType(int id)
    {
        this.id = id;
    }
    
    /**
     * Returns this PacketType's unique ID
     * @return ID of this Packet
     */
    public int getID()
    {
        return id;
    }
    
}