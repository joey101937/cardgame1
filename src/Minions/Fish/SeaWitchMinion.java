/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Fish;

import Cards.Card;
import Cards.Fish.FrenzyCard;
import Cards.Fish.PredationCard;
import Cards.Fish.SwollowCard;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public class SeaWitchMinion extends Minion{
        public SeaWitchMinion(Card parent){
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 3;
        tribe = Tribe.none;
        maxHealth = 4;
        health = maxHealth;
        name = "Sea Witch";
        sprite = SpriteHandler.seaWitchMinion;
    }
        
        @Override
        public void onSummon() {
        ArrayList<Card> options = new ArrayList<>();
        //possible draw options
        options.add(new FrenzyCard());
        options.add(new PredationCard());
        options.add(new SwollowCard());
        //
        this.proc();
        Main.wait(AI.AI.speed/3);
        owner.draw(options.get((int)(Math.random()*options.size())));
        }
}
