/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Joseph
 */
public class SpriteHandler {

    public static BufferedImage cardback;
    public static BufferedImage cardbackL;
    public static BufferedImage arakkoaMinion;
    public static BufferedImage cardback2;
    public static BufferedImage ashePortrait;
    
    public static void Initialize() {
        try {
            cardback = ImageIO.read(new File(Main.assets + "cardBack.png"));
            cardbackL = ImageIO.read(new File(Main.assets + "cardBackL.png"));
            arakkoaMinion = ImageIO.read(new File(Main.assets + "arakkoaMinionL.png"));
            cardback2 = ImageIO.read(new File(Main.assets + "cardBack2.png"));
            ashePortrait = ImageIO.read(new File(Main.assets + "ashePortrait.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
