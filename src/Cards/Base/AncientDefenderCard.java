/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Base;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Base.AncientDefenderMinion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class AncientDefenderCard extends Card{
       
    public AncientDefenderCard() {
        name = "Ancient Defender";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "Whenever your hero \n is attacked, restore \n 2 health to it";
        sprite = SpriteHandler.ancientDefenderCard;
        cost = 4;
        summon = new AncientDefenderMinion(this);
    }
    
    @Override
    public void tick(){
        intrinsicValue = 1;
        if(AI.AI.isHeroVulnerable(owner)){
            intrinsicValue += owner.opponent.minions.getOccupants().size()*2;
        }
    }
    
}
