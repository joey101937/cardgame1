/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Traps;

import Cards.Card;
import Minions.Minion;
import cardgame1.Board;
import cardgame1.Hero;
import cardgame1.LineManager;

/**
 * listens to events on the board and relays those to active traps
 * @author Joseph
 */
public abstract class TrapListener {

    public static void onAttack(Minion attacker, Minion defender) {
        for (Trap t : Board.topHero.traps.getOccupants()) {
            t.onAttack(attacker, defender);
        }
        for (Trap t : Board.botHero.traps.getOccupants()) {
            t.onAttack(attacker, defender);
        }
        for (Minion min : Board.topHero.minions.getOccupants()) {
            min.onAttackDetect(attacker, defender);
        }
        for (Minion min : Board.botHero.minions.getOccupants()) {
            min.onAttackDetect(attacker, defender);
        }
        
    }

    public static void onAttackHero(Minion attacker, Hero defender) {
        for (Trap t : Board.topHero.traps.getOccupants()) {
            t.onAttackHero(attacker, defender);
        }
        for (Trap t : Board.botHero.traps.getOccupants()) {
            t.onAttackHero(attacker, defender);
        }
        for (Minion min : Board.topHero.minions.getOccupants()) {
            min.onAttackHeroDetect(attacker,defender);
        }
        for (Minion min : Board.botHero.minions.getOccupants()) {
           min.onAttackHeroDetect(attacker,defender);
        }
         
    }

    public static void onMinionTakeDamage(Minion m) {
        for (Trap t : Board.topHero.traps.getOccupants()) {
            t.onMinionTakeDamage(m);
        }
        for (Trap t : Board.botHero.traps.getOccupants()) {
            t.onMinionTakeDamage(m);
        }
    }

    public static void onSummon(Minion m) {
        for (Trap t : Board.topHero.traps.getOccupants()) {
            t.onSummon(m);
        }
        for (Trap t : Board.botHero.traps.getOccupants()) {
            t.onSummon(m);
        }
        for(Minion min : Board.topHero.minions.getOccupants()){
            min.onSummonDetect(m);
        }
        for(Minion min : Board.botHero.minions.getOccupants()){
            min.onSummonDetect(m);
        }
    }

    public static void onPlay(Card c) {
        for (Trap t : Board.topHero.traps.getOccupants()) {
            t.onPlay(c);
        }
        for (Trap t : Board.botHero.traps.getOccupants()) {
            t.onPlay(c);
        }
    }

    public static void onMinionDeath(Minion m) {
        for (Trap t : Board.topHero.traps.getOccupants()) {
            t.onMinionDeath(m);
        }
        for (Trap t : Board.botHero.traps.getOccupants()) {
            t.onMinionDeath(m);
        }
        for (Minion min : Board.topHero.minions.getOccupants()) {
            min.onDeathDetect(m);
        }
        for (Minion min : Board.botHero.minions.getOccupants()) {
            min.onDeathDetect(m);
        }
    }

}
