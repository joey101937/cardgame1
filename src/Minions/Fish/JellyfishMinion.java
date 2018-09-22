/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Fish;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import Traps.TrapListener;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Stickers.Sticker;

/**
 *
 * @author Joseph
 */
public class JellyfishMinion extends Minion{
    public JellyfishMinion(Card parent){
        this.parent=parent;
        this.attack=2;
        originalAttack = attack;
        this.health=5;
        this.maxHealth=health;
        //this.intrinsicValue=2;
        this.tribe = Tribe.Fish;
        this.name="Jellyfish";
        this.owner = parent.getOwner();
        this.sprite = SpriteHandler.jellyfishMinion;
    }
    /*
    @Override
    public void onSummon(){
        Sticker s = new Sticker(SpriteHandler.snowflakeLarge,this,AI.AI.speed);
        Main.wait(AI.AI.speed);
        Minion target = owner.opponent.minions.getOccupants().get((int)(Math.random()*owner.opponent.minions.getOccupants().size()));
        target.freeze();
    }
    */
   /*
    @Override
    public void onAttacked(Minion m){
        m.attack-=1;
        if(m.attack<0) m.attack=0;
        Sticker s = new Sticker(SpriteHandler.bloodMedium,m,400);
    }
    @Override
    public void attack(Minion m){
        super.attack(m);
        if(m.attack>0){
        m.attack-=1;
        if(m.attack<0) m.attack=0;
        Sticker s = new Sticker(SpriteHandler.bloodMedium,m,400);
        TrapListener.onAttack(this, m);
        }
    }
    */
}
