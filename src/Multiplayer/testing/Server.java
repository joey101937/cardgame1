/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multiplayer.testing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Joseph
 */
public class Server  implements Runnable{

    
    public static void main(String[] args) {
     Server server = new Server(); 
     server.run();
    }

    
    
    @Override
    public void run() {
        try {
            ServerSocket servSocket = new ServerSocket(444);
            Socket socket = servSocket.accept();
            InputStreamReader ir = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(ir);     
            while(true){
                String fromClient = br.readLine();
                if(fromClient!= null){
                    PrintStream ps = new PrintStream(socket.getOutputStream());
                    System.out.println("From client: " + fromClient);
                    ps.println("message received");
                    ps.flush();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}
