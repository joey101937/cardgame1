/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Empire;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Minion;
import Traps.TrapListener;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class ApocalypseCard extends Card {

    public ApocalypseCard() {
        name = "Apocalypse";
        cardType = CardType.Spell;
        cardPurpose = CardPurpose.Special;
        isTargeted = false;
        cardText = "Destroy all Minions";
        sprite = SpriteHandler.apocolypseCard;
        heroClass = HeroClass.Empire;
        cost = 9;
        intrinsicValue = -3; //we need to get at least 4 value out of this card in order for the ai to want to play it
    }
    
    @Override
    public void smartCast(){
        cast(null);
    }
    @Override
    public int castOnHero(Hero h) {
        return cast(null);
    }
    
    @Override
    public int cast(Minion m){
        if(!canAfford())return 0;
        notifyPhantom(null,null);
        for(Minion target : owner.opponent.minions.getOccupants()){
            Sticker impactEffect = new Sticker(SpriteHandler.blastEffectSmall,target,AI.AI.speed/2);
        }
        for(Minion friendly : owner.minions.getOccupants()){
            Sticker impactEffect = new Sticker(SpriteHandler.blastEffectSmall,friendly,AI.AI.speed/2);
        }
        Main.wait(AI.AI.speed/2);
        for(Minion enemy : owner.opponent.minions.getOccupants()){
            enemy.destroy();
        }
        for(Minion friend : owner.minions.getOccupants()){
        friend.destroy();
        }
        owner.resource-=cost;
        owner.hand.remove(this);
        TrapListener.onPlay(this);
        return 1;
    }
    
    @Override
    public void tick(){
        intrinsicValue = -2;
        int valGained = AI.AI.getWorthOfBoard(owner.opponent.minions.getOccupants());
        int valLost = AI.AI.getWorthOfBoard(owner.minions.getOccupants());
        intrinsicValue = valGained - valLost;
    }
}
