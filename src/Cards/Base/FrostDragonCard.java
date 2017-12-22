/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Base;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import Minions.Base.FrostDragonMinion;
import Minions.Minion;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class FrostDragonCard extends Card{
        public FrostDragonCard() {
        name = "Frost Dragon";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        cardText = "On Summon: \n Freeze all enemies";
        sprite = SpriteHandler.frostDragonCard;
        cost = 6;
        summon = new FrostDragonMinion(this);
    }
    /**
     * every tick this minion evaluates the boardstate to determine any bonus value from playing
     */
    @Override
    public void tick(){
        this.intrinsicValue=0;
        for(Minion m : owner.opponent.minions.getOccupants()){
        if(m.attack > 0 && !m.isFrozen && !m.name.equals("Frost Bear")){ //frost bear cant be frozen so ignore it
                if(AI.AI.canFavorablyTrade(m)){
                    this.intrinsicValue+=2;
                }else{
                    this.intrinsicValue+=1;
                }
            }    
        }
    }
}