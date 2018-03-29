/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomDecks;

import cardgame1.Main;
import cardgame1.SpriteHandler;
import java.awt.Color;
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

        @Override
        public String getClassIconPath() {
            return Main.assets+"swordsSmall.png";
        }

        @Override
        public String getHeroPortraitPath() {
            return Main.assets+"knightHero2.png";
        }

        @Override
        public Color getColor() {
            //return new Color(25,25,25);
            return Color.gray;
        }

        @Override
        public BufferedImage getClassIcon() {
            return SpriteHandler.swordsSmall;
        }
    }, Restricted{
        @Override
        public BufferedImage getHeroPortrait() {
            return SpriteHandler.ashePortrait;
        }

        @Override
        public String getClassIconPath() {
            return Main.assets+"redXsmall.png";
        }

        @Override
        public String getHeroPortraitPath() {
            return Main.assets+"ashePortrait.png";
        }

        @Override
        public Color getColor() {
           return Color.RED;
        }

        @Override
        public BufferedImage getClassIcon() {
           return SpriteHandler.redX;
        }
    }, Ocean{
        @Override
        public BufferedImage getHeroPortrait() {
            return SpriteHandler.seaWitchHero;
        }

        @Override
        public String getClassIconPath() {
            return Main.assets+"iconFish.png";
        }

        @Override
        public String getHeroPortraitPath() {
            return Main.assets + "deepSeaHero.png";
        }

        @Override
        public Color getColor() {
            return Color.BLUE;
        }

        @Override
        public BufferedImage getClassIcon() {
            return SpriteHandler.iconFish;
        }
    }, Undead{
        @Override
        public BufferedImage getHeroPortrait() {
            return SpriteHandler.undeadHero;
        }

        @Override
        public String getClassIconPath() {
            return Main.assets+"iconUndead.png";
        }

        @Override
        public String getHeroPortraitPath() {
            return Main.assets + "undeadHero.png";
        }

        @Override
        public Color getColor() {
            return new Color(51,10,150);
           // return new Color(100,60,200);
        }

        @Override
        public BufferedImage getClassIcon() {
            return SpriteHandler.iconUndead;
        }
    };
    /**
     * gets the hero image corresponding to that class
     * @return the image
     */
    public abstract BufferedImage getHeroPortrait();
    /**
     * Gets the filepath to the class's hero's portrait
     * @return filepath
     */
    public abstract String getHeroPortraitPath();
    /**
     * path to get the class's icon
     * @return 
     */
    public abstract String getClassIconPath();
    /**
     * gets class color
     * @return 
     */
    public abstract Color getColor();
    
    public abstract BufferedImage getClassIcon();
}
