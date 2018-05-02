/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Empire;

import Cards.Card;
import CustomDecks.HeroClass;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class TrebuchetMinion extends Minion{
    
    private Minion nextTarget = null;
    
      public TrebuchetMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 1;
        originalAttack = attack;
        maxHealth = 7;
        health = maxHealth;
        tribe = Tribe.none;
        name = "Trebuchet";
        sprite = SpriteHandler.trebuchetMinion;
        this.intrinsicValue=6;
    }
      
      @Override
      public void onTurnEnd(){
          Minion target = owner.opponent.minions.get(owner.minions.indexOf(this));
          if(target!=null){
              proc();
              target.takeDamage(6);
          }
      }
      
      @Override
      public void tick(){
          intrinsicValue = 0;
          Minion target = owner.opponent.minions.get(owner.minions.indexOf(this));
          if(target!=nextTarget && target!=null)target.intrinsicValue-=6;
          nextTarget = target;
          if(target!=null){
              intrinsicValue = AI.AI.getWorth(target)-AI.AI.getWorthAfterDamage(target, 6);
          }
      }
}
