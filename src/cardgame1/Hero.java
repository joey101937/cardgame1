/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import Cards.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * the player character, takes direct attacks
 * @author Joseph
 */
public class Hero {
    /*   STATICS   */
    public static int maxHandSize = 5; //max amount of cards to have in your hand
    private static int idBank = 0;     //determines what id a hero will have
    /*   FIELDS   */
    public int health = 30;
    public int maxHealth = 30;
    public BufferedImage picture; //visual representation
    public String name;
    public ArrayList<Minion> minions = new ArrayList<>(); //currently in play minions.
    public ArrayList<Card> hand = new ArrayList<>();
    public Deck deck;
    public int id;
    public boolean turn = false; //is it our turn?
    //CONSTRUCTOR
    public Hero(String name, Deck deck){
        this.name = name;
        this.deck = deck;
        deck.shuffle();
        id = idBank++;
    }
    
    public void draw(){
        if(hand.size()>= maxHandSize){// if we have too many cards in hand
            System.out.println(("player Milled Card: ") + deck.Draw());
        }else{
            hand.add(deck.Draw());
        }
    }
    
    public void turnStart(){
        
    }
}
