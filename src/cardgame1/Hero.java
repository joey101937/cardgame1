/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import Minions.Minion;
import Cards.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * the player character, takes direct attacks
 * @author Joseph
 */
public class Hero {
    /*   STATICS   */
    public static int maxHandSize = 6; //max amount of cards to have in your hand
    private static int idBank = 0;     //determines what id a hero will have
    /*   FIELDS   */
    public int health = 30;
    public int maxHealth = 30;
    public int resource, maxResource;   //mana used to play cards
    public BufferedImage picture; //visual representation
    public String name;
    public PlayArea<Minion> minions = new PlayArea<Minion>();
    public ArrayList<Card> hand = new ArrayList<>();
    public ArrayList<Card> deck;
    public int id;
    public boolean turn = false; //is it our turn?
    //CONSTRUCTOR
    public Hero(String name, ArrayList<Card> deck, BufferedImage portrait){
        this.name = name;
        this.deck = deck;
        this.picture = portrait;
        this.resource = 3;
        this.maxResource = 3;
        for(Card c: deck){
            c.setHero(this);
        }
        id = idBank++;
    }
    
    public void draw(){
        if(hand.size()>= maxHandSize){// if we have too many cards in hand
            System.out.println(("player Milled Card: ") + deck.remove(0));
        }else{
            hand.add(deck.remove(0));
        }
    }
    
    /**
     * randomizes the card order
     */
    public void shuffle() {
        ArrayList<Card> temp = new ArrayList<>();
        while (!deck.isEmpty()) {
            temp.add(deck.remove((int) Math.random() * deck.size()));
        }
        deck = temp;
    }
    
    public void turnStart(){       
    }
    
    
    public void takeDamage(int amount){
        this.health-=amount;
    }
}
