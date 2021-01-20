/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Base;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Base.HellFiendMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class HellFiendCard extends Card{
      
    public HellFiendCard(){
        name = "Hell Fiend";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "";
        sprite = SpriteHandler.hellFiendCard;
        cost = 8;
        summon = new HellFiendMinion(this);
        intrinsicValue = 20;
    }
}
