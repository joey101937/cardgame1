/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Fish;

import Cards.Base.KelpieCard;
import Cards.Card;
import Cards.Fish.*;
import Minions.Minion;
import Minions.Tribe;
import Multiplayer.Phantom;
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
        attack = 2;
        originalAttack = attack;
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
        options.add(new SeaSerpentTrapCard());
        
        options.add(new ThrasherCard());
        options.add(new JellyfishCard());
        options.add(new CarnifishCard());
        options.add(new BaitfishCard());
        options.add(new SeaWitchCard());
        
        options.add(new KelpieCard());
        options.add(new PirranahCard());
        //
        this.proc();
        Main.wait(AI.AI.speed/3);
        owner.draw(options.get((int)(Phantom.random.nextDouble()*options.size())));
        }
}
