/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomDecks;

import cardgame1.SpriteHandler;
import java.awt.image.BufferedImage;

/**
 * Classes of hero, determine what kinds of cards you can put into the deck
 * @author Joseph
 */
public enum HeroClass {
    Neutral{
        @Override
        public BufferedImage getHeroPortrait() {
            return SpriteHandler.knightHero;
        }
    }, Restricted{
        @Override
        public BufferedImage getHeroPortrait() {
            return SpriteHandler.ashePortrait;
        }
    }, Ocean{
        @Override
        public BufferedImage getHeroPortrait() {
            return SpriteHandler.seaWitchHero;
        }
    }, Undead{
        @Override
        public BufferedImage getHeroPortrait() {
            return SpriteHandler.undeadHero;
        }
    };
    /**
     * gets the hero image corresponding to that class
     * @return hte image
     */
    public abstract BufferedImage getHeroPortrait();
}
