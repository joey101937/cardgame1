/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Dragon;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.DragonInterface;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class MothersCareCard extends Card{
       public MothersCareCard() {
        name = "Mother's Care";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = true;
        cardText = "Instantly grow a \n friendly hatchling, \n or destroy a friendly \n Dragon Egg";
        sprite = SpriteHandler.mothersCareCard;
        cost = 4;
        heroClass = HeroClass.Dragon;
    }
       
       @Override
       public int castOnHero(Hero h){
           return -1;
       }
       @Override
       public int cast(Minion m){
           if(!canAfford())return 0;
           if((!m.name.equals("Dragon Egg") && m.tribe!=Tribe.Hatchling)||m.owner!=owner) return -1;
           notifyPhantom(m,null);
           if(m.tribe==Tribe.Hatchling){
               DragonInterface di = (DragonInterface)m;
               di.grow();
               owner.hand.remove(this);
               owner.resource-=cost;
               return 1;
           }else{
               //must be dragon egg due to earlier condition
               m.takeDamage(99);
               owner.hand.remove(this);
               owner.resource-=cost;
               return 1;
        }
    }

    @Override
    public void tick() {
        this.intrinsicValue = 0;
        int bestValue = 0;
        for (Minion m : owner.minions.getOccupants()) {
            if (m.tribe == Tribe.Hatchling) {
                intrinsicValue = 10;
            }
            if(m.name.equals("Dragon Egg") && intrinsicValue == 0){
                intrinsicValue = 4;
            }
        } 
    }

    @Override
    public void smartCast() {
        for (Minion m : owner.minions.getOccupants()) {
            if (m.tribe == Tribe.Hatchling) {
                cast(m);
                return;
            }
        }
        for (Minion m : owner.minions.getOccupants()) {
            if (m.name.equals("Dragon Egg")) {
                cast(m);
                return;
            }
        }
    }
}
