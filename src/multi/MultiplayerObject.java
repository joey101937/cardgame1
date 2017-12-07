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
     * Not sure (as of yet) how to hook networking into the actual game and the classes already available
     *
     * UPDATE: Most likely chance of this working will be to hook into the Hero class
     */
    private InetAddress address;
    private int port;
    private String username;
    
    public MultiplayerObject(InetAddress address, int port, String username)
    {
        this.address = address;
        this.port = port;
        this.username = username;
    }
    
    public InetAddress getAddress()
    {
        return address;
    }
    
    public int getPort()
    {
        return port;
    }
    
    public String getUsername()
    {
        return username;
    }
}
