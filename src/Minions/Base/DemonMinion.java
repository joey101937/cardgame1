/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Base;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;
import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public class DemonMinion extends Minion{

 public DemonMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        originalAttack = attack;
        maxHealth = 3;
        health = maxHealth;
        tribe = Tribe.none;
        name = "Demon";
        sprite = SpriteHandler.demonMinion;
    }
 
    @Override
    public void onSummon(){
        ArrayList<Minion> targets = new ArrayList<>();
        for(Minion m :owner.minions.getOccupants()){
            if(m==this || m==null)continue;
            targets.add(m);
        }
        for(Minion m : owner.opponent.minions.getOccupants()){
            if(m==null)continue;
            targets.add(m);
        }
        if(targets.size()==0)return;
        Minion target = targets.get((int)(targets.size()*Math.random()));
        Sticker impactEffect = new Sticker(SpriteHandler.blastEffectSmall,target,AI.AI.speed/3);
        Main.wait(AI.AI.speed/3);
        target.takeDamage(parent.spellDamage);
        
    }
    
}
