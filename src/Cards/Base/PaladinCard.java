/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Base;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Base.PaladinMinion;
import Minions.Minion;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class PaladinCard extends Card {

    public PaladinCard() {
        name = "Paladin";
        this.isTargeted = true;
        this.spellDamage = 4;
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.BattlecryMinionHeal;
        cardText = "On Summon: \n restore " + spellDamage +" health to \n target hero or minion";
        sprite = SpriteHandler.paladinCard;
        cost = 4;
        summon = new PaladinMinion(this);
    }
  @Override
    public int cast(Minion target) {
        int outcome = defaultMinionSummon();
        if(outcome == 1){
            target.proc();
            Main.wait(AI.AI.speed/3);
            //target.health+=spellDamage;
            target.heal(spellDamage);
        }
        return outcome;
    }

    @Override
    public int castOnHero(Hero target) {
        int outcome = defaultMinionSummon();
        if(outcome == 1){
            target.proc();
            Main.wait(AI.AI.speed/3);
            //target.health+=spellDamage;
            target.heal(spellDamage);
        }
        return outcome;
    }
    
}
