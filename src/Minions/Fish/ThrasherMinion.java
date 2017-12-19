/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Fish;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class ThrasherMinion extends Minion{
    public ThrasherMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        maxHealth = 2;
        health = maxHealth;
        tribe = Tribe.Fish;
        name = "Thrasher";
        sprite = SpriteHandler.thrasherMinion;
    }
    
    @Override
    public void onTurnEnd(){
        Sticker s = new Sticker(SpriteHandler.bloodMedium,this,300);
        Main.wait(300);
        
        int roll = (int)(Math.random()*owner.opponent.minions.numOccupants()+1);
        if (roll >= owner.opponent.minions.numOccupants()){
            owner.opponent.takeDamage(attack);
            return;
        }
        Minion target = owner.opponent.minions.getOccupants().get(roll);
        this.canAttack = true;
        this.attack(target);
        //target.takeDamage(this.attack);
        //this.takeDamage(target.attack);
        Main.wait(300);
    }
    
    public static void main(String[] args) {
        for(int i =0; i<100;i++){
            System.out.println((int)(Math.random() * (3 + 2)));
        }
    }
}
