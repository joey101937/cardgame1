/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multiplayer.testing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Joseph
 */
public class Client implements Runnable {
    public static void main(String[] args) {
        Client c = new Client();
        c.run();
    }
    
    @Override
    public void run() {
        try {
            Socket sock = new Socket("localhost",444);
            PrintStream ps = new PrintStream(sock.getOutputStream()); //output stream is what we are sending
            InputStreamReader ir = new InputStreamReader(sock.getInputStream());
            BufferedReader br = new BufferedReader(ir);
            while(true){
                ps.println(JOptionPane.showInputDialog("Message:"));
                ps.flush();
                String fromServer = br.readLine();
                if(fromServer!= null)System.out.println("from server: " + fromServer);
            }
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        
    }
    
}
