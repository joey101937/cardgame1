/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multiplayer;

import java.net.ServerSocket;
import javax.swing.JOptionPane;

/**
 * Simple timeout class for Phantom
 * Closes the serversocket of a phantom if there is no connection after a delay
 * @author Joseph
 */
public class TimeoutController implements Runnable{
    public int time;
    public ServerSocket socket;
    
    /**
     * Creates a timeout for the given phantom, which expires after given ms
     * @param duration Time to wait in ms
     * @param socket Socket we will close after timeout if not connected
     */
    public TimeoutController(int duration, ServerSocket socket){
        time = duration;
        this.socket = socket;
        Thread t = new Thread(this);
        t.start();
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(time);
            if (!Phantom.connected) {
                socket.close();
                JOptionPane.showMessageDialog(null, "Connection Timeout");
            }   
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}
