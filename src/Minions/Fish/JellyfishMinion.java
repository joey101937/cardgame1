/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Fish;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class JellyfishMinion extends Minion{
    public JellyfishMinion(Card parent){
        this.parent=parent;
        this.attack=1;
        this.health=5;
        this.maxHealth=health;
        this.intrinsicValue=2;
        this.tribe = Tribe.Fish;
        this.name="Jellyfish";
        this.owner = parent.getOwner();
        this.canAttack=false;
        this.sprite = SpriteHandler.jellyfishMinion;
    }
    
    @Override
    public void onAttacked(Minion m){
        m.attack--;
        Sticker s = new Sticker(SpriteHandler.bloodMedium,m,400);
    }
    @Override
    public void attack(Minion m){
        super.attack(m);
        if(m.attack>0){
        m.attack--;
        Sticker s = new Sticker(SpriteHandler.bloodMedium,m,400);
        }
    }
}
