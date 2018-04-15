/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards.Elemental;

import Cards.Card;
import Cards.CardPurpose;
import Cards.CardType;
import CustomDecks.HeroClass;
import Minions.Elemental.SorcererMinion;
import Minions.Minion;
import Minions.Tribe;
import cardgame1.Hero;
import cardgame1.SpriteHandler;

/**
 *
 * @author Joseph
 */
public class SorcererCard extends Card {

    public SorcererCard() {
        name = "Sorcerer";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.Special;
        isTargeted = true;
        cardText = "On Summon: \n Draw an elemental \n based on target \n golem's attribute";
        sprite = SpriteHandler.sorcererCard;
        heroClass = HeroClass.Elemental;
        cost = 1;
        summon = new SorcererMinion(this);
    }
    
    @Override
    public int cast(Minion m){
        if(!canAfford())return 0;
        if(m.tribe!=Tribe.Golem || m.owner!=owner)return -1;
        if(owner.minions.add(summon)){
        notifyPhantom(m,null);
        owner.hand.remove(this);
        owner.resource-=cost;
        m.proc();
        switch(m.name){
            case "Molten Golem":
                owner.draw(new FireElementalCard());
                return 1;
            case "Ice Golem":
                owner.draw(new IceElementalCard());
                return 1;
            case "Earth Golem":
                owner.draw(new EarthElementalCard());
                return 1;
            default:
                owner.draw(new StoneElementalCard());
                return 1;
             }
        }
        return -1; //no space on board
    }
    
    @Override
    public int castOnHero(Hero h) {
        return -1;
    }
    
    @Override
    public void tick(){
        intrinsicValue = 0;
        if(owner.minions.isFull())return;
        for(Minion m : owner.minions.getOccupants()){
            if(m.tribe!=Tribe.Golem)continue;
            switch(m.name){
            case "Molten Golem":
                intrinsicValue+=3;
                return;
            case "Ice Golem":
                intrinsicValue+=3;
                return;
            case "Earth Golem":
                intrinsicValue+=3;
                return;
            default:
                intrinsicValue+=2;
                return;
            }
        }
    }
    /**
     * play on first special golem. otherwise cast on a regular one if available (stored in backup)
     */
    @Override
    public void smartCast() {
        Minion backup = null;
        for (Minion m : owner.minions.getOccupants()) {
            if (m.tribe != Tribe.Golem) {
                continue;
            }
            switch (m.name) {
                case "Molten Golem":
                    cast(m);
                    return;
                case "Ice Golem":
                    cast(m);
                    return;
                case "Earth Golem":
                    cast(m);
                    return;
                default:
                    backup = m;
            }
        }
        cast(backup);
    }

}
