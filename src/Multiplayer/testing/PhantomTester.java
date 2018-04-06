/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multiplayer.testing;

import Multiplayer.Phantom;
import cardgame1.Hero;
import cardgame1.Main;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph
 */
public class PhantomTester implements Runnable{
    
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new PhantomTester());
        t.start();
        Hero clientHero = new Hero("Client", Main.getTestDeck(), null);
        Phantom clientPhantom = new Phantom(clientHero, false);
        System.out.println("client phantom created");
        Thread.sleep(100);
        clientPhantom.communicateMessage("c-0-n-0");
    }

    @Override
    public void run() {      
        try {
            Hero serverHero = new Hero("Server",Main.getTestDeck(),null);
            Phantom serverPhantom = new Phantom(serverHero,true);
             System.out.println("server phantom created");
        } catch (Exception ex) {
            Logger.getLogger(PhantomTester.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
