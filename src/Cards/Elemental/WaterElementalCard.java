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
import Minions.Elemental.WaterElementalMinion;
import Minions.Minion;
import Traps.Trap;
import cardgame1.SpriteHandler;
import cardgame1.Stickers.Sticker;

/**
 *
 * @author Joseph
 */
public class WaterElementalCard extends Card {

    public WaterElementalCard() {
        name = "Water Elemental";
        cardType = CardType.Minion;
        cardPurpose = CardPurpose.VanillaMinion;
        isTargeted = false;
        cardText = "On Summon: \n Destroy all enemy \n traps";
        sprite = SpriteHandler.waterElementalCard;
        heroClass = HeroClass.Elemental;
        cost = 3;
        summon = new WaterElementalMinion(this);
    }

    @Override
    public int cast(Minion m) {
        if (owner.minions.isFull() || !canAfford()) {
            return 0;
        }
        for (Trap t : owner.opponent.traps.getOccupants()) {
            new Sticker(SpriteHandler.redX, t.getXCoordinate() + SpriteHandler.trapSymbol.getWidth() / 2, t.getYCoordinate() + SpriteHandler.trapSymbol.getHeight() / 2, AI.AI.speed);
            owner.opponent.traps.remove(t);
        }
        return super.cast(m);
    }

    @Override
    public void tick() {
        int value =0;
        for(Trap t : owner.opponent.traps.getOccupants()){
            value += t.parent.cost;
        }
        intrinsicValue = value;
    }
}
