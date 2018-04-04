/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Dragon;

import AI.SimulatedMinion;
import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Dragon.VolcanicDrakeMinion;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class VolcanicDrakeCard extends Card {

    public VolcanicDrakeCard() {
        name = "Volcanic Drake";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "On Summon: \n Add 2 \"Fire Plume\" \n cards to your hand";
        sprite = SpriteHandler.volcanicDrakeCard;
        cost = 9;
        summon = new VolcanicDrakeMinion(this);
        heroClass = HeroClass.Dragon;
    }
    
    @Override
    public void tick(){
        intrinsicValue = 0;
        if(owner.hand.size() == Hero.maxHandSize) return;
        if(AI.AI.canFavorablyTrade(new SimulatedMinion(3,0,owner))){
           intrinsicValue+=AI.AI.getWorth(AI.AI.getBestTarget(new SimulatedMinion(3,0,owner)));
        }
        else intrinsicValue+=3;
        if(!(owner.hand.size()+1 == Hero.maxHandSize)) intrinsicValue+=3; //if we get both cards (hand not too full for both plumes), we also add the value of the second plume
    }
}
