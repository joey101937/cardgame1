/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Fish;

import Cards.Base.KelpieCard;
import Cards.Card;
import Cards.CardType;
import Cards.Fish.*;
import CustomDecks.HeroClass;
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
        private static ArrayList<Card> options = null; //possible draw options
    
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
        if(options==null){
            options = new ArrayList<Card>();
            for(Card c : Card.getCardList()){
                if(c.heroClass == HeroClass.Ocean || (c.cardType==CardType.Minion && c.summon.tribe==Tribe.Fish)){
                    options.add(c);
                }
            }
        }
        this.proc();
        Main.wait(AI.AI.speed/3);
        owner.draw(options.get((int)(Phantom.random.nextDouble()*options.size())));
        }
}
