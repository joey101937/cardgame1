/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import Cards.Card;
import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public class Deck {
    public ArrayList<Card> cards = new ArrayList<>();
    Hero owner;
    
    
    
    public Deck(Hero hero, ArrayList<Card> cards){
        this.cards = cards;
        this.owner = hero;
    }
    /**
     * returns then removes the top card of the deck
     * @return 
     */
    public Card Draw(){
        return(cards.remove(0));
    }
    
    /**
     * randomizes the card order
     */
    public void shuffle(){
        ArrayList<Card> temp = new ArrayList<>();
        while(!cards.isEmpty()){
            temp.add(cards.remove((int)Math.random()*cards.size()));
        }
        cards = temp;
    }
    
}
