/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Joseph
 */
public class SpriteHandler {

    public static BufferedImage cardback; //the back of a card. see this instead of enemy card.
    public static BufferedImage cardbackL; //large cardback
    public static BufferedImage arakkoaMinion;
    public static BufferedImage cardback2; //example sprite for use as the image of a card
    public static BufferedImage ashePortrait;
    public static BufferedImage arakkoaCard;
    public static BufferedImage fireBoltCard;
    public static BufferedImage redX;
    public static BufferedImage frostBearCard;
    public static BufferedImage frostBearMinion;
    public static BufferedImage archerCard;
    public static BufferedImage archerMinion;
    
    public static void Initialize() {
        try {
            cardback = ImageIO.read(new File(Main.assets + "cardBack.png"));
            cardbackL = ImageIO.read(new File(Main.assets + "cardBackL.png"));
            arakkoaMinion = ImageIO.read(new File(Main.assets + "arakkoaMinionL.png"));
            arakkoaCard = ImageIO.read(new File(Main.assets + "arakkoaCard.png"));
            cardback2 = ImageIO.read(new File(Main.assets + "cardBack2.png"));
            ashePortrait = ImageIO.read(new File(Main.assets + "ashePortrait.png"));
            fireBoltCard = ImageIO.read((new File(Main.assets + "fireBoltCard.png")));
            frostBearMinion = load("frostBear.png");
            frostBearCard = load("frostBearCard.png");
            archerCard = load("archerCard.png");
            archerMinion = load("archerPortrait.png");
            redX = load("redXsmall.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * returns a bufferedImage loaded from the given filename, located in assets folder.
     * @param filename name of file including extension
     * @return buffered image render
     * @throws IOException if file cannot be found or loaded
     */
    private static BufferedImage load(String filename) throws IOException{
        return ImageIO.read(new File(Main.assets + filename));
    }
}
