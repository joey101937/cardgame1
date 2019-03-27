/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Base;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Base.DemonMinion;
import Minions.Minion;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class DemonCard extends Card{
    
    /*
      INHERITED FIELDS
    public String name;         //name of card
    public CardType cardType;   //type of card
    public String cardText;     //what the card says on it
    public BufferedImage sprite; //visual representation of the card
    public BoardSlot slot; //where on the board it is
    public Hero owner;
    */
    
    public DemonCard(){
        name = "Demon";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        spellDamage = 2;
        cardText = "On Summon: \n Deal " + spellDamage + "damage to \n another random \n minion";
        sprite = SpriteHandler.demonCard;
        cost = 3;
        summon = new DemonMinion(this);
    }
}
