/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multiplayer;

import java.net.ServerSocket;
import javax.swing.JOptionPane;

/**
 *
 * @author Joseph
 */
public class TimeoutController implements Runnable{

    public int time;
    public ServerSocket socket;
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
