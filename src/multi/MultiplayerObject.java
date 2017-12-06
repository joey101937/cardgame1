/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multi;

import java.net.InetAddress;

/**
 *
 * @author Knifesurge
 * @since 2017-12-06
 */
public class MultiplayerObject
{
    /* NOTE: May have this object 'hook' into an InputHandler so each client has a separate InputHandler (prevents P2 controlling P1's cards, etc)
    */
    private InetAddress address;
    private int port;
    
    public MultiplayerObject(InetAddress address, int port)
    {
        this.address = address;
        this.port = port;
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
