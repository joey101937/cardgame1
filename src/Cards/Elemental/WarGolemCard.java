/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Elemental;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Elemental.WarGolemMinion;
import Minions.Minion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class WarGolemCard extends Card {

    public WarGolemCard() {
        name = "War Golem";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "Your Stone Golems \n have +2 attack";
        sprite = SpriteHandler.warGolemCard;
        cost = 3;
        heroClass = HeroClass.Elemental;
        summon = new WarGolemMinion(this);
    }
    
    @Override
    public void tick(){
        intrinsicValue = 0;
        for(Minion m : owner.minions.getOccupants()){
            if(m.name.equals("Stone Golem")){
                intrinsicValue++;
                if(m.canAttack())intrinsicValue++;
            }
        }
        for(Card c : owner.hand){
            if(c.name.equals("Stone Golem")){
                intrinsicValue++;
            }
        }               
    }
   
}
