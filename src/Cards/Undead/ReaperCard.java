/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Undead;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Minion;
import Minions.Undead.ReaperMinion;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Stickers.Sticker;

/**
 *
 * @author Joseph
 */
public class ReaperCard extends Card{
      public ReaperCard() {
        name = "Reaper";
        cardType = CardType.Minion;
        spellDamage = 999;
        cardPurpose = CardPurpose.Special;
        isTargeted = true;
        cardText = "On Summon: \n Destroy target \n minion";
        sprite = SpriteHandler.reaperCard;
        heroClass = HeroClass.Undead;
        cost = 6;
        summon = new ReaperMinion(this);
    }
      
      @Override
      public int castOnHero(Hero h){
          return -1;
      }

      @Override
      public int cast(Minion m){
          if(!canAfford() || owner.opponent.minions.getOccupants().size()==0){
              return 0;
          }
          new Sticker(SpriteHandler.skullEffect,m,AI.AI.speed);
          Main.wait(AI.AI.speed);
          m.takeDamage(99);
          return defaultMinionSummon();
      }
      
    @Override
    public void tick() {
        this.intrinsicValue = -1;
        if (owner.minions.isFull() || owner.opponent.minions.getOccupants().size() == 0) {
            return;
        }
        this.intrinsicValue = AI.AI.getWorth(AI.AI.getBiggestThreatOf(owner.opponent));
        if(intrinsicValue>6){
            intrinsicValue+=7; //more incentivised to use when there is a strong minion to kill
        }
    }
    
    @Override
    public void smartCast(){
        cast(AI.AI.getBiggestThreatOf(owner.opponent));
    }

}
