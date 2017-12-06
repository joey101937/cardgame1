/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multi.network.packet;

/**
 *
 * @author Knifesurge
 * @since 2017-12-06
 */
public enum PacketType {
    
    INVALID(-1), LOGIN(00), DISCONNECT(01);
    
    int id;
    
    PacketType(int id)
    {
        this.id = id;
    }
    
    public int getID()
    {
        return id;
    }
    
}