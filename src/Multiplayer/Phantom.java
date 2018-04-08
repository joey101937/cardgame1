/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multiplayer;

import static AI.AI.speed;
import Cards.Card;
import Cards.CardPurpose;
import CustomDecks.CustomDeck;
import cardgame1.Board;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * drives the non-user hero, controlled by remote opponenet player
 * @author Joseph
 */
public final class Phantom implements Runnable{
    /*        FIELDS          */
    public Hero host;
    public boolean isServer = false;
    public static boolean syncedRandom = false;
    public boolean receivedDeck = false;
    public static int port = 444;
    private static final int rSeed = (int)(Math.random()*9999);
    public static String connectionAddress = "localhost";
    public static Random random = new Random(rSeed);
    public ServerSocket serverSocket;
    public Socket socket;
    public InputStreamReader inputStream;
    public PrintStream printStream;
    public BufferedReader br;
    public static Phantom mainPhantom; //last created phantom
    
    public Phantom(Hero host, boolean isServer) throws Exception{
        System.out.println("making new phantom");
        mainPhantom = this;
        this.host = host;
        this.isServer = isServer;
        Main.isMulitiplayerGame=true;
        System.out.println("isServer: " + isServer);
        if (isServer) {
            setupServer();
        } else {
            setupClient();
        }
        System.out.println("phantom setup complete ");
        Thread t = new Thread(this);
        t.start();
    }
    
    public void setupServer() throws Exception{
        Thread t = new Thread();
        serverSocket = new ServerSocket(444);
        System.out.println("server Inet Address: "+serverSocket.getInetAddress()); //thi
        socket = serverSocket.accept();        
        inputStream = new InputStreamReader(socket.getInputStream());
        br = new BufferedReader(inputStream);
        printStream = new PrintStream(socket.getOutputStream());
        random = new Random(rSeed);
    }
    
    public void setupClient() throws Exception{
       socket = new Socket(Phantom.connectionAddress,444);
        inputStream = new InputStreamReader(socket.getInputStream());
        printStream = new PrintStream(socket.getOutputStream());
        br = new BufferedReader(inputStream);
    }
    

    
    public synchronized void communicateMessage(String s){
        System.out.println("sending message: " + s);
        printStream.println(s);
    }
    
    @Override
    public void run() {
        try{
        if(isServer){
            //server code
            printStream.println("randSeed: " + rSeed);
            
            while (true) {
                String message = br.readLine();
                System.out.println("server got message: " + message);
                interperateMessage(message);
            }
        } else {
            //client code
            while (true) {
                    String message = br.readLine();
                    System.out.println("client got message: " + message);
                    interperateMessage(message);                    
            }
        }
        }catch(SocketException se){
        if(se.getMessage().equals("Connection reset")){
            JOptionPane.showMessageDialog(null, "Lost Connection");
            System.exit(0);
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }    
    
    public synchronized void sendDeck(ArrayList<Card> input){
        communicateMessage("deckSend");
        for(Card c : input){
            communicateMessage("card:"+c.name);
        }
        communicateMessage("deckEnd");
    }
    /*
        HOW PLAY COMMANDS ARE TRANSMITTED
        
    Format:  
            actorType-actorIndex-targetType-targetIndex
    
    0 actorType: what we are calling to perform an action. c - card, m - minion;
    1 actorIndex: what index its at, 0-5 in hand, 0-3 for minion slots on board;
    2 targetType: what thing we are targeting; fm for minion, em for enemy minion, fh for friendly hero, eh for enemy hero.
    3 target index: what is the index that we are targeting, 0-3 for minions on board, 
       
    "end" = end turn
    ("randSeed: " + int) sets random seed to int
    */
    private boolean receivingDeck = false; //if this is true, we are receiving deck

    private synchronized void  interperateMessage(String message) {
        try{
        if (message == null) return; 
        if(message.equals("gotRandom")){ //acknowledgement that client got our random
        syncedRandom = true;
        return;
        }
        if(message.equals("deckSend")){
            host.deck.clear();
            receivingDeck = true;  //if we get "decksend" we begin populating the phantom's deck with cards to be send in later messages
            return;
        }
        if(message.equals("deckEnd")){
        receivingDeck=false;    //send deckEnd when we are done receiving cards
        receivedDeck=true;
        }
        if(receivingDeck && message.startsWith("card:")){
            Card toAdd = CustomDeck.getCard(message.substring(5));
            toAdd.setHero(host);
            host.deck.add(toAdd);
        }
        if (message.startsWith("randSeed: ")) {  //set random to servers seed
            if(syncedRandom)return;
            int seed = Integer.parseInt(message.split(" ")[1]);
            System.out.println("setting seed to " + seed);
            random = new Random(seed);
            syncedRandom = true;
            communicateMessage("gotRandom");
            return;
        }
              if (message.equals("end")) {
                Main.wait(300);
                Board.controller.nextTurn(); //end turn
                return;
            }
        if(!host.turn)return;//messages after this will not execute if its not our turn
        String[] contents = message.split("-");
        int actorIndex = Integer.parseInt(contents[1]);
        int targetIndex = Integer.parseInt(contents[3]);
        //play commands
        Card toUse = null;
        switch(contents[0]){
            case "c": //casting a card
                switch(contents[2]){
                    case "fm": //on a friendly minion
                        System.out.println("casting card on friednly minion");
                        toUse = host.hand.get(actorIndex);
                        displayCard(toUse);
                        toUse.cast(host.minions.get(targetIndex));  
                        break;
                    case "em": //on an enemy minion
                        System.out.println("casting card on enemy minion");
                        toUse = host.hand.get(actorIndex);
                        displayCard(toUse);
                        toUse.cast(host.opponent.minions.get(targetIndex));              
                        break;
                    case "fh": //on friednly hero
                        System.out.println("casting card on friednly hero");
                        toUse = host.hand.get(actorIndex);
                        displayCard(toUse);
                        toUse.castOnHero(host);
                        break;
                    case "eh": //on enemy hero
                        System.out.println("casting card on enemy hero");
                        toUse = host.hand.get(actorIndex);
                        displayCard(toUse);
                        toUse.castOnHero(host.opponent);
                        break;
                    case "n":
                        System.out.println("casting card on null " + host.hand.get(actorIndex));
                        toUse = host.hand.get(actorIndex);
                        displayCard(toUse);
                        toUse.cast(null);;
                        break;
                }
                break;
            case "m": //minoin is issuing an attacking
                switch (contents[2]) {
                    case "fm": //on a friendly minion. NOTE SHOULD NOT BE POSSIBLE!
                        System.out.println("attacking friendly minion");
                        host.minions.get(actorIndex).attack(host.minions.get(targetIndex));
                        break;
                    case "em": //on an enemy minion
                        System.out.println("attacking enemy minion");
                        host.minions.get(actorIndex).attack(host.opponent.minions.get(targetIndex));
                        break;
                    case "fh": //on friednly hero. NOTE SHOULD NOT BE POSSIBLE
                        System.out.println("attacking friendly hero");
                        host.minions.get(actorIndex).attack(host);
                        break;
                    case "eh": //on enemy hero
                        System.out.println("attacking enemy hero");
                        host.minions.get(actorIndex).attack(host.opponent);
                        break;
                }
                break;
        }
        
        }catch(Exception e){
            System.out.println("error with message " + message);
            e.printStackTrace();
        }
    }
    
        private void displayCard(Card c) {
            System.out.println("displaying card " + c.name + "from phantom play");
        if (c.cardPurpose == CardPurpose.Trap) {
            Sticker s = new Sticker(SpriteHandler.trapPlaceholder, 1700, 200, speed * 2);        //let user know we are playing a trap card
        } else {
            Sticker s = new Sticker(c, 1700, 200, speed * 2);      //let user know what non-trap card we are playing
        }
        Main.wait(speed * 2);
    }
}
