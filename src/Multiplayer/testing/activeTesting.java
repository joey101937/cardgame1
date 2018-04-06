/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multiplayer.testing;

import cardgame1.Board;
import cardgame1.Hero;
import cardgame1.Main;
import static cardgame1.Main.setBackgroundImage;
import cardgame1.SpriteHandler;
import java.awt.Dimension;

/**
 *
 * @author Joseph
 */
public class activeTesting {
        public static boolean isServer = false;
        
        
        public static void main(String[] args) throws Exception {
        setBackgroundImage();
        SpriteHandler.Initialize();
        Hero bot = new Hero("bottom", Main.getTestDeck(), SpriteHandler.ashePortrait);
        Hero top = new Hero("top", Main.getTestDeck(), SpriteHandler.knightHero);
        top.isAIControlled = false;
      //  top.attachPhantom(isServer);
        Board board = new Board(top, bot, new Dimension(1600, 900), isServer);   
    }
}
