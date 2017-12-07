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
public class Connect01Packet extends Packet
{

    private String username;
    
    public Connect01Packet(byte... data) {
        super(new byte[]{01});
        this.username = readData(data);
    }

    public Connect01Packet(String username)
    {
        super(new byte[]{01});
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
       return ("01" + this.username).getBytes();
    }

    public String getUsername()
    {
        return username;
    }
    
}
