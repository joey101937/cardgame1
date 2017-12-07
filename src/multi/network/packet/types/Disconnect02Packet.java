/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multi.network.packet.types;

import multi.network.GameClient;
import multi.network.GameServer;
import multi.network.packet.Packet;

/**
 *
 * @author Knifesurge
 * @since 2017-12-07
 */
public class Disconnect02Packet extends Packet
{

    private String username;
    
    public Disconnect02Packet(byte... data) {
        super(new byte[]{02});
        this.username = readData(data);
    }

    public Disconnect02Packet(String username)
    {
        super(new byte[]{02});
        this.username = username;
    }
    
    @Override
    public void writeData(GameClient client) {
        client.sendData(getData(), client.getPortOfServer());
    }

    @Override
    public void writeData(GameServer server) {
        server.sendDataToAll(getData());
    }

    @Override
    public byte[] getData() {
        return ("02" + this.username).getBytes();
    }
    
    public String getUsername()
    {
        return username;
    }
    
}
