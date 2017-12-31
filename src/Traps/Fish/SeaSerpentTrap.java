/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Traps.Fish;

import Cards.Card;
import Cards.Fish.SeaSerpentCard;
import Minions.Minion;
import Minions.Tribe;
import Traps.Trap;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class SeaSerpentTrap extends Trap{
        private int numSummoned = 0;
        private boolean activated = false;
        
        public SeaSerpentTrap(Card parent) {
        owner = parent.getOwner();
        this.parent=parent;
        name = "Sea Serpent Trap";
    }
        
        @Override
        public void onSummon(Minion m){
            if(m.owner == this.owner && m.tribe == Tribe.Fish && m.parent.cost > 1){
                numSummoned += 1;
            }
            if(numSummoned >= 3 && !owner.minions.isFull() && !activated){
                activated = true;
                Sticker reveal = new Sticker(parent, 1700, 200, AI.AI.speed * 6);
                Sticker blood = new Sticker(SpriteHandler.bloodMedium,m,AI.AI.speed*6);
                Main.wait(AI.AI.speed*6);
                //owner.minions.add(new SeaSerpentMinion(this.parent));
                owner.draw(new SeaSerpentCard());
                owner.traps.remove(this);
            }
        }
    
        @Override
        public void tick(){
            parent.cardText = "After playing three \n 2+ cost fish, put \n a Sea Serpent card \n into your hand \n (" + this.numSummoned + ")";
        }
}
