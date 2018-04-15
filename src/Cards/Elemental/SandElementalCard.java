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
import Minions.Elemental.SandElementalMinion;
import Minions.Minion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class SandElementalCard extends Card{
       public SandElementalCard() {
        name = "Sand Elemental";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        spellDamage = 1;
        isTargeted = false;
        cardText = "On Summon: \n Deal " + spellDamage +" damage to \n all enemy minions";
        sprite = SpriteHandler.sandElementalCard;
        heroClass = HeroClass.Elemental;
        cost = 3;
        summon = new SandElementalMinion(this);
    }
       
       @Override
       public void tick(){
           intrinsicValue = 0;
           for(Minion m : owner.opponent.minions.getOccupants()){
               if(m.health<=spellDamage){
                   intrinsicValue+=AI.AI.getWorth(m);
               }
               else{
                   intrinsicValue+=1;
               }
           }
       }
}
