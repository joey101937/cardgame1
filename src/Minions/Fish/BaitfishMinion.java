/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Fish;

import Cards.Card;
import Cards.Fish.BaitfishCard;
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
public class BaitfishMinion extends Minion{
       public BaitfishMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 0;
        originalAttack = attack;
        maxHealth = 2;
        intrinsicValue = -2; //low value; owning ai wants to kill it
        health = maxHealth;
        tribe = Tribe.Fish;
        name = "Baitfish";
        sprite = SpriteHandler.baitfishMinion;
    } 
       
       @Override
       public void onDeath(){
           Sticker s = new Sticker(SpriteHandler.skullMedium,this,600);
           if(isSilenced) return;
           ArrayList<Minion> targets = new ArrayList<>();
           for(Minion m : owner.minions.getOccupants()){
           if(m.tribe==Tribe.Fish && m!=this)targets.add(m);
           }
           if(targets.isEmpty()){
               System.out.println("no targets for baitfish death effect");
               owner.draw(new BaitfishCard());
               return;
           }
           Minion target = targets.get(Main.generateRandom(0, targets.size()));
           target.attack+=1;
           target.health += 1;
           target.proc();
           owner.draw(new BaitfishCard());
       }
   
       /**
        * every tick, the baitfish will check if there are targets for it
        */
    @Override
    public void tick() {
        for (Minion m : owner.minions.getOccupants()) {
            if (m.tribe == Tribe.Fish && m != this) {
                this.intrinsicValue = -3;
                return;
            }
        }
        this.intrinsicValue = 0;
        return;
    }
}
