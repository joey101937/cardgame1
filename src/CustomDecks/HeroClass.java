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
            return Color.darkGray;
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
            return SpriteHandler.fishManHero;
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
            return Main.assets+"iconUndead2small.png";
        }

        @Override
        public String getHeroPortraitPath() {
            return Main.assets + "undeadHero.png";
        }

        @Override
        public Color getColor() {
            return new Color(56,15,130);
           // return new Color(100,60,200);
        }

        @Override
        public BufferedImage getClassIcon() {
            return SpriteHandler.iconUndead;
        }
    }, Dragon{
        @Override
        public BufferedImage getHeroPortrait() {
            return SpriteHandler.dragonHero;
        }

        @Override
        public String getHeroPortraitPath() {
            return Main.assets+"dragonHero.png";
        }

        @Override
        public String getClassIconPath() {
            return Main.assets+"iconDragon.png";
        }

        @Override
        public Color getColor() {
            //return Color.red;
            return new Color(220,40,40);
        }

        @Override
        public BufferedImage getClassIcon() {
           return SpriteHandler.iconDragon;
        }
        
    }, Empire{
        @Override
        public BufferedImage getHeroPortrait() {
            return SpriteHandler.empireHero;
        }

        
        @Override
        public String getHeroPortraitPath() {
            return Main.assets+"empireHero2.png";
        }

        @Override
        public Color getColor() {
            return Color.gray;
           // return new Color(85,56,10);
        }

        @Override
        public BufferedImage getClassIcon() {
            return SpriteHandler.iconEmpire;
        }

        @Override
        public String getClassIconPath() {
             return Main.assets+"iconEmpire2Knight.png";
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
    
    /**
     * returns a class based on given number
     * -1 = restricted
     * 0 = neutral
     * 1 = Ocean
     * 2 = Undead
     * 3 = Dragon
     * @return cooresponding class
     */
    public static HeroClass getClassByNumber(int given){
        switch (given){
            case -1:
               return HeroClass.Restricted;
            case 0:
                return HeroClass.Neutral;
            case 1:
                return HeroClass.Ocean;
            case 2:
                return HeroClass.Undead;
            case 3:
                return HeroClass.Dragon;
            default:
                return null;
        }
    }
}
