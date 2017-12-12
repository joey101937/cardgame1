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
    public static BufferedImage knightCard;
    public static BufferedImage knightMinion;
    public static BufferedImage knightChargeMinion;
    public static BufferedImage knightChargeCard;
    public static BufferedImage fullCrystal;
    public static BufferedImage emptyCrystal;
    public static BufferedImage leftArrow;
    public static BufferedImage skullLarge;
    public static BufferedImage skullMedium;
    public static BufferedImage blastEffectLarge;
    public static BufferedImage blastEffectSmall;
    public static BufferedImage slashEffect;
    public static BufferedImage lightbulb;
    //fish
    public static BufferedImage baitfishCard;
    public static BufferedImage baitfishMinion;
    public static BufferedImage thrasherMinion;
    public static BufferedImage thrasherCard;
    public static BufferedImage carnifishMinion;
    public static BufferedImage carnifishCard;
    public static BufferedImage bloodLarge;
    public static BufferedImage bloodMedium;
    public static BufferedImage frenzyCard;
    public static BufferedImage predationCard;
    
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
            knightCard = load("KnightCard.png");
            knightMinion = load("Knight.png");
            knightChargeMinion = load("KnightCharge.png");
            knightChargeCard = load("KnightChargeCard.png");
            redX = load("redXsmall.png");
            fullCrystal = load("gemCSmall.png");
            emptyCrystal = load("gemSmall.png");
            leftArrow = load("arrow-left.png");
            skullLarge = load("skullLarge.png");
            skullMedium = load("skullMedium.png");
            blastEffectLarge = load("blastEffectLarge.png");
            blastEffectSmall = load("blastEffectSmall.png");
            slashEffect = load("slashEffect.png");
            lightbulb = load("lightbulb.png");
            bloodLarge = load("blood.png");
            bloodMedium = load("bloodMed.png");
            //fish
            baitfishCard = load("baitFishCard.png");
            baitfishMinion = load("baitfish.png");
            thrasherMinion = load("thrasher.png");
            thrasherCard = load("thrasherCard.png");
            carnifishMinion = load("carnifish.png");
            carnifishCard = load("carnifishCard.png");
            frenzyCard = load("frenzyCard.png");
            predationCard = load("predationCard.png");
        } catch (Exception e) {
            e.printStackTrace();
            Main.display("Error loading all assets. Please Verify Assets folder.");
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
    
    /**
     * returns the buffered image associated with the given filename in the Assets directory.
     * @param filename name of file with extension
     * @return buffered image representation
     */
    public static BufferedImage fetch(String filename){
        try{
            return ImageIO.read(new File(Main.assets + filename));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
