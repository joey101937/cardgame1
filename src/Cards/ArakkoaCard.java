/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import Minions.ArakkoaMinion;
import Minions.Minion;
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
        cardText = "";
        sprite = SpriteHandler.arakkoaCard;
        cost = 1;
        summon = new ArakkoaMinion(this);
    }

    /**
     * attempts to use the card. Returns value based on outcome
     * 1: success
     * 0: Canceled- could not afford
     * -1: could not add to play area. (max minions reached?)
     * @return
     */
    @Override
    public int cast(Minion target) {
       return defaultMinionSummon();
    }

}
