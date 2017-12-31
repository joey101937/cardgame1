/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Traps.Undead;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import Minions.Undead.ZombieMinion;
import Traps.Trap;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class ZombieBiteTrap extends Trap {
    private boolean active = true; //used to ensure it only acivates once
    
    public ZombieBiteTrap(Card parent) {
        owner = parent.getOwner();
        this.parent = parent;
        name = "Zombie Bite";
    }
    
    @Override
    public void onSummon(Minion m) {
        if (m.owner == this.owner || !active) {
            return;
        }
        active = false;
        Sticker reveal = new Sticker(parent, 1700, 200, AI.AI.speed * 6);
        Sticker skull = new Sticker(SpriteHandler.skullEffect, m, AI.AI.speed * 6);
        Main.wait(AI.AI.speed * 6);
        m.destroy();
        m.owner.minions.add(new ZombieMinion(m.owner,3,1));
        owner.traps.remove(this);
    }
}
