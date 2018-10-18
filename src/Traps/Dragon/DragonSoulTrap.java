/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Traps.Dragon;

import Cards.Card;
import Cards.Dragon.DragonSoulSpell;
import Minions.Minion;
import Minions.Tribe;
import Traps.Trap;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class DragonSoulTrap extends Trap {
    
    public DragonSoulTrap(Card parent) {
        owner = parent.getOwner();
        this.parent = parent;
        name = "Dragon Soul Trap";
    }
    
    @Override
    public void onMinionDeath(Minion m){
        if(owner.hand.size()>=Hero.maxHandSize)return;
        if(m.owner != owner || m.tribe != Tribe.Dragon)return;
        Sticker reveal = new Sticker(parent, 1700, 200, AI.AI.speed * 6);
        Sticker indecator = new Sticker(SpriteHandler.iconDragon, m, AI.AI.speed * 6);
        Main.wait(AI.AI.speed * 6);       
        owner.draw(new DragonSoulSpell(m));
        owner.traps.remove(this);
    }
}
