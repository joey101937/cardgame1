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
public class Handshake00Packet extends Packet{

    public Handshake00Packet(byte... id) {
        super(new byte[]{00});
    }

    @Override
    public void writeData(GameClient client) {
        throw new UnsupportedOperationException("Not supported yet. (With every intention)"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeData(GameServer server) {
        throw new UnsupportedOperationException("Not supported yet. (With every intention)"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] getData() {
        return ("00" + "Handshake").getBytes();
    }
    
}
