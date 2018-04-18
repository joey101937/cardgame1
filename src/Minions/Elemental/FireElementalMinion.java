/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minions.Elemental;

import Cards.Card;
import Minions.Minion;
import Minions.Tribe;
import Multiplayer.Phantom;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Sticker;

/**
 *
 * @author Joseph
 */
public class FireElementalMinion extends Minion {

    public FireElementalMinion(Card parent) {
        this.parent = parent;
        this.owner = parent.getOwner();
        attack = 6;
        originalAttack = attack;
        maxHealth = 6;
        health = maxHealth;
        name = "Fire Elemental";
        this.tribe = Tribe.Elemental;
        sprite = SpriteHandler.fireElementalMinion;
    }
    
    @Override
    public void onTurnEnd() {
        if(owner.opponent.minions.numOccupants()==0)return;
        int roll = (int)(Phantom.random.nextDouble()*(owner.opponent.minions.numOccupants()));
        Minion target = owner.opponent.minions.getOccupants().get(roll);
        if(target==null)return;
        Sticker launchEffect = new Sticker(SpriteHandler.blastEffectSmall, this, AI.AI.speed / 3);
        Sticker targetEffect = new Sticker(SpriteHandler.blastEffectSmall, target, AI.AI.speed / 3);
        Main.wait(AI.AI.speed / 3);
        target.takeDamage(parent.spellDamage);
    }
}
