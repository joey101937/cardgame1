/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class ArakkoaCard extends Card{

    /*
      INHERITED FIELDS
    public String name;         //name of card
    public CardType cardType;   //type of card
    public String cardText;     //what the card says on it
    public BufferedImage sprite; //visual representation of the card
    public BoardSlot slot; //where on the board it is
    public Hero owner;
    */
    
    public ArakkoaCard(){
        name = "Arakkoa";
        cardType = CardType.Minion;
        cardText = "Test card";
        sprite = SpriteHandler.arakkoaCard;
    }
    
    @Override
    public int cast() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
