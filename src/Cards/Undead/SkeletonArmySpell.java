/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Undead;

import AI.SimulatedMinion;
import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Minion;
import Minions.Undead.SkeletonMinion;
import Traps.TrapListener;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.PlayArea;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class SkeletonArmySpell extends Card {

    public SkeletonArmySpell() {
        name = "Skeleton Army";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = false;
        cardText = "Summon up to \n four 2/1 skeletons \n with charge";
        sprite = SpriteHandler.skeletonSpell;
        cost = 6;
    }
    
    @Override
    public void tick(){
        this.intrinsicValue = -4;
        if(owner.minions.numOccupants() ==0 ) intrinsicValue+=2;
        int numSpawned = PlayArea.MAX_SIZE-owner.minions.numOccupants();
        intrinsicValue += (3*numSpawned);
        for(Minion m : owner.opponent.minions.getOccupants()){
            if(m.health<=2 == AI.AI.getWorth(m) > 3){
            intrinsicValue -=3; //if a minion will die to a skeleton, gain that minion's value instead of the skeleton's
            intrinsicValue += AI.AI.getWorth(m);
            }
        }
    }
    
    @Override
    public void smartCast(){
        this.cast(null);
    }
    
    
    @Override
    public int castOnHero(Hero h){
        return cast(null);
    }

    @Override
    public int cast(Minion target) {
      if(!canAfford())return 0;
      for(int i = 0; i < 4; i++){
          owner.minions.add(new SkeletonMinion(this));
          Main.wait(AI.AI.speed/4);
      }
        owner.resource -= cost;
        owner.hand.remove(this);
        TrapListener.onPlay(this);
        return 1;
    }

}
