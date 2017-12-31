/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Fish;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Fish.CarnifishMinion;
import Minions.Minion;
import cardgame1.Board;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class CarnifishCard extends Card{
    public CarnifishCard(){
        name = "Carnifish";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "On Summon: \n Kill all friendly \n Baitfish";
        intrinsicValue = 0;
        sprite = SpriteHandler.carnifishCard;
        cost = 2;
        summon = new CarnifishMinion(this);
    }
        
    @Override
    public void tick(){
        this.intrinsicValue = 0;
        for(Minion m : owner.minions.getOccupants()){
            if(m.name.equals("Baitfish")){
                this.intrinsicValue++;
                if(m.attack>1){                    
                    intrinsicValue-=(m.attack+m.health+1);
                }
            }
        }
    }
}
