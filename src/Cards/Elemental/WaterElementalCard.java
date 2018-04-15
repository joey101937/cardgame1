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
import Minions.Elemental.WaterElementalMinion;
import Traps.Trap;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class WaterElementalCard extends Card {

    public WaterElementalCard() {
        name = "Water Elemental";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        isTargeted = false;
        cardText = "On Summon: \n Destroy all enemy \n traps";
        sprite = SpriteHandler.waterElementalCard;
        heroClass = HeroClass.Elemental;
        cost = 3;
        summon = new WaterElementalMinion(this);
    }
    
     
    @Override
    public void tick(){
        int value =0;
        for(Trap t : owner.opponent.traps.getOccupants()){
            value += t.parent.cost;
        }
        intrinsicValue = value;
    }
}
