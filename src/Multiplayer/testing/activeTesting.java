/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multiplayer.testing;

import Cards.Card;
import cardgame1.Board;
import cardgame1.Hero;
import static cardgame1.Main.setBackgroundImage;
import cardgame1.SpriteHandler;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public class activeTesting {
        public static boolean isServer = true;
        
        
        public static void main(String[] args) throws Exception {
        setBackgroundImage();
        SpriteHandler.Initialize();
        //Hero bot = new Hero("bottom", Main.getTestDeck(), SpriteHandler.ashePortrait);
        Hero bot = new Hero("bottom", Card.getCardList(), SpriteHandler.ashePortrait);
        //Hero top = new Hero("top", Main.getTestDeck(), SpriteHandler.knightHero);
        Hero top = new Hero("top", new ArrayList<Card>(), SpriteHandler.knightHero);
        top.isAIControlled = false;
      //  top.attachPhantom(isServer);
        Board board = new Board(top, bot, new Dimension(1600, 900), isServer);   
    }
}
