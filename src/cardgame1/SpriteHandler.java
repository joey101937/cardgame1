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
 * Main sprite loader for application
 * @author Joseph
 */
public class SpriteHandler {
    //misc
    public static boolean hasInitialized = false;
    public static BufferedImage cardback; //the back of a card. see this instead of enemy card.
    public static BufferedImage cardbackL; //large cardback
    public static BufferedImage cardback2; //example sprite for use as the image of a card
    public static BufferedImage redX;
    public static BufferedImage checkmark;
    public static BufferedImage fullCrystal;
    public static BufferedImage emptyCrystal;
    public static BufferedImage leftArrow;
    public static BufferedImage skullLarge;
    public static BufferedImage skullMedium;
    public static BufferedImage blastEffectLarge;
    public static BufferedImage blastEffectSmall;
    public static BufferedImage slashEffect;
    public static BufferedImage lightbulb;
    public static BufferedImage swords;
    public static BufferedImage swordsSmall;
    public static BufferedImage snowflakeSmall;
    public static BufferedImage snowflakeLarge;
    public static BufferedImage trapSymbol;
    public static BufferedImage trapPlaceholder;
    public static BufferedImage skullEffect;
    public static BufferedImage canceledEffect;
    public static BufferedImage gearSmall;
    public static BufferedImage iconFish;
    public static BufferedImage iconUndead;
    public static BufferedImage iconUndeadSmall;
    public static BufferedImage iconDragon;
    public static BufferedImage PLAYtext;
    public static BufferedImage NEXTLEVELtext;
    public static BufferedImage CAMPAIGNtext, DUELtext, MULTIPLAYERtext, DECKBUILDERtext, OPTIONStext;
    public static BufferedImage backArrow;
    public static BufferedImage VS;
    public static BufferedImage scroll;
    //advanced fish
    public static BufferedImage fishermanCard;
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
    public static BufferedImage seaWitchCard;
    public static BufferedImage seaWitchMinion;
    public static BufferedImage jellyfishMinion;
    public static BufferedImage jellyfishCard;
    public static BufferedImage swollowTrap;
    public static BufferedImage seaSerphantTrap;
    public static BufferedImage seaSerphantCard;
    public static BufferedImage seaSerphantMinion;
    public static BufferedImage predatoryFishMinion, predatoryFishCard;
    public static BufferedImage underSeaMantisMinion, underSeaMantisCard;
    //neutral
    public static BufferedImage frostBearCard;
    public static BufferedImage frostBearMinion;
    public static BufferedImage archerCard;
    public static BufferedImage archerMinion;
    public static BufferedImage knightCard;
    public static BufferedImage knightMinion;
    public static BufferedImage knightChargeMinion;
    public static BufferedImage knightChargeCard;
    public static BufferedImage arakkoaCard;
    public static BufferedImage arakkoaMinion;
    public static BufferedImage fireBoltCard;
    public static BufferedImage kelpieCard;
    public static BufferedImage kelpieMinion;
    public static BufferedImage volcanoCard;
    public static BufferedImage frostDragonMinion;
    public static BufferedImage frostDragonCard;
    public static BufferedImage spellBookCard;
    public static BufferedImage pirranahCard;
    public static BufferedImage pirranahMinion;
    public static BufferedImage fireSpearCard;
    public static BufferedImage paladinMinion,paladinCard;
    public static BufferedImage tornadoCard;
    public static BufferedImage spearmanMinion, spearmanCard;
    public static BufferedImage minotaurMinion, minotaurCard;
    public static BufferedImage ancientDefenderMinion, ancientDefenderCard;
    public static BufferedImage hellFiendCard, hellFiendMinion;
    public static BufferedImage sandWormCard, sandWormMinion;
    //heros
    public static BufferedImage ashePortrait;
    public static BufferedImage seaWitchHero;
    public static BufferedImage fishManHero;
    public static BufferedImage undeadHero;
    public static BufferedImage knightHero;
    public static BufferedImage alligatorHero;
    public static BufferedImage dragonHero;
    //undead
    public static BufferedImage zombieCard;
    public static BufferedImage swampZombieMinion;
    public static BufferedImage zombieTrap;
    public static BufferedImage undeadSoldier;
    public static BufferedImage undeadSoldierCard;
    public static BufferedImage zombieBiteSpell;
    public static BufferedImage skeletonSpell;
    public static BufferedImage skeletonMinion;
    public static BufferedImage skelemancerMinion;
    public static BufferedImage skelemancerCard;
    public static BufferedImage skullKingMinion;
    public static BufferedImage skullKingCard;
    public static BufferedImage ghoulCard;
    public static BufferedImage ghoulMinion;
    public static BufferedImage necromancyCard;
    public static BufferedImage wraithMinion, wraithCard;
    //Dragons
    public static BufferedImage grayDrakeMinion, grayDrakeCard;
    public static BufferedImage firePlumeCard;
    public static BufferedImage volcanicDrakeCard, volcanicDrakeMinion;
    public static BufferedImage dragonBreathCard;
    public static BufferedImage dragonSoulTrap, dragonSoulSpell;
    public static BufferedImage fireyWhelpCard, fireyWhelpMinion;
    public static BufferedImage whiteHatchlingMinion, whiteHatchlingCard;
    public static BufferedImage whiteDragonMinion, whiteDragonCard;
    public static BufferedImage faerieDragonMinion, faerieDragonCard;
    public static BufferedImage faerieHatchlingMinion, faerieHatchlingCard;
    public static BufferedImage redDragonMinion, redDragonCard;
    public static BufferedImage redHatchlingMinion, redHatchlingCard;
    public static BufferedImage greenDragonMinion, greenDragonCard;
    public static BufferedImage greenHatchlingMinion, greenHatchlingCard;
    public static BufferedImage dragonEggMinion, dragonEggCard;
    public static BufferedImage hatchlingCard;
    public static BufferedImage mothersCardCard;
    public static BufferedImage purifyingBlastCard;
     //Empire
    public static BufferedImage iconEmpire;
    public static BufferedImage empireHero;
    public static BufferedImage apocolypseCard;
    public static BufferedImage snipeTrap;
    public static BufferedImage griffonMinion, griffonCard;
    public static BufferedImage doubleshotCard;
    public static BufferedImage cavalryGeneralMinion, cavalryGeneralCard;
    public static BufferedImage enchantedSwordCard;
    //Elemental
    public static BufferedImage 
            stoneGolemMinion,stoneGolemCard,
            warGolemMinion, warGolemCard,
            earthGolemMinion,earthInfusionSpell,
            moltenGolemMinion, fireInfusionSpell,
            iceGolemMinion, iceInfusionSpell,
            sorcererMinion, sorcererCard,
            geomancerMinion,geomancerCard,
            earthElementalMinion,earthElementalCard,
            fireElementalMinion, fireElementalCard,
            iceElementalMinion,iceElementalCard,
            stoneElementalMinion, stoneElementalCard,
            waterElementalMinion,waterElementalCard,
            sandElementalMinion,sandElementalCard;
    public static BufferedImage elementalHero;
    public static BufferedImage iconElemental;
    public static BufferedImage rock;
    public static BufferedImage iceyWindCard;
    public static void Initialize() {
        try {
            if(SpriteHandler.hasInitialized) return;
            else hasInitialized=true;
            if(Main.BackgroundImage==null)Main.setBackgroundImage();
            cardback = ImageIO.read(new File(Main.assets + "cardBack.png"));
            cardbackL = ImageIO.read(new File(Main.assets + "cardBackL.png"));
            cardback2 = ImageIO.read(new File(Main.assets + "cardBack2.png"));
            redX = load("redXsmall.png");
            checkmark = load("checkmarkSmall.png");
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
            swords = load("swords.png");
            swordsSmall = load("swordsSmall.png");
            snowflakeSmall = load("snowflakeEffect.png");
            snowflakeLarge = load("snowflakeLarge.png");
            trapSymbol = load("warningSign.png");
            trapPlaceholder = load("MysteryTrap.png");
            skullEffect = load("jollyRoger.png");
            canceledEffect = load("canceled.png");
            gearSmall = load("gearSmall.png");
            iconFish = load("iconFish.png");
            iconUndead = load("iconUndead2small.png");
            iconDragon = load("iconDragon2.png");
            iconElemental = load("iconElemental.png");
            PLAYtext = load("PLAY.png");
            NEXTLEVELtext = load("NEXT LEVEL.png");
            DUELtext = load("DUEL.png");
            MULTIPLAYERtext = load("Multiplayer.png");
            DECKBUILDERtext = load("Deck Builder.png");
            OPTIONStext = load("OPTIONS.png");
            CAMPAIGNtext = load("Campaign.png");
            scroll = load("scroll.png");
            
            backArrow = load("backArrowSmall.png");
            VS = load("VS2.png");
            //Advanced Fish
            fishermanCard = load("fishermanCard.png");
            baitfishCard = load("baitFishCard.png");
            baitfishMinion = load("baitfish.png");
            thrasherMinion = load("thrasher.png");
            thrasherCard = load("thrasherCard.png");
            carnifishMinion = load("carnifish.png");
            carnifishCard = load("carnifishCard.png");
            frenzyCard = load("frenzyCard.png");
            predationCard = load("predationCard.png");
            seaWitchCard = load("seaWitchCard.png");
            seaWitchMinion = load("seaWitchPortrait.png");
            jellyfishMinion = load("jellyPortrait.png");
            jellyfishCard = load("jellyCard.png");
            swollowTrap = load("swollowTrap.png");
            seaSerphantTrap = load("seaSerphantTrap.png");
            seaSerphantCard = load("seaSerphantCard.png");
            seaSerphantMinion = load("seaSerphantMinion.png");
            underSeaMantisMinion = load("underseaMantisMinion.png");
            underSeaMantisCard = load("underSeaMantisCard.png");
            predatoryFishCard = load("predatoryFishCard.png");
            predatoryFishMinion = load("predatoryFishMinion.png");
            //Neutral Cards
            fireBoltCard = ImageIO.read((new File(Main.assets + "fireBoltCard.png")));
            frostBearMinion = load("frostBear.png");
            frostBearCard = load("frostBearCard.png");
            archerCard = load("archerCard.png");
            archerMinion = load("archerPortrait.png");
            knightCard = load("KnightCard.png");
            knightMinion = load("Knight.png");
            knightChargeMinion = load("KnightCharge.png");
            knightChargeCard = load("KnightChargeCard.png");
            arakkoaMinion = ImageIO.read(new File(Main.assets + "arakkoaMinionL.png"));
            arakkoaCard = ImageIO.read(new File(Main.assets + "arakkoaCard.png"));
            kelpieCard = load("kelpieCard.png");
            kelpieMinion = load("kelpiePortrait.png");
            volcanoCard = load("volcanoCard.png");
            frostDragonMinion = load("frostDragonPortrait.png");
            frostDragonCard = load("frostDragonCard.png");
            spellBookCard = load("spellBookCard.png");
            pirranahCard = load("pirranahCard.png");
            pirranahMinion = load("pirannahMinion.png");
            fireSpearCard = load("fireSpearCard.png");
            paladinMinion = load("paladin.png");
            paladinCard = load("paladinCard.png");
            tornadoCard = load("tornadoCard.png");
            spearmanCard = load("spearmanCard.png");
            spearmanMinion = load("spearman.png");
            minotaurMinion = load("minotaurMinion.png");
            minotaurCard = load("minotaurCard.png");
            ancientDefenderMinion = load("ancientDefenderMinion.png");
            ancientDefenderCard = load("ancientDefenderCard.png");
            hellFiendMinion = load("hellFiendMinion.png");
            hellFiendCard = load("hellFiendCard.png");
            sandWormCard = load("sandWormCard.png");
            sandWormMinion = load("sandWormMinion.png");
            //heros
            ashePortrait = ImageIO.read(new File(Main.assets + "ashePortrait.png"));
            seaWitchHero = load("seaWitchHero.png");
            fishManHero = load("deepSeaHero.png");
            undeadHero = load("undeadHero.png");
            knightHero = load("knightHero.png");
            alligatorHero = load("alligatorHero.png");
            dragonHero = load("dragonHero.png");
            elementalHero = load("elementalHero.png");
            //undead
            zombieCard = load("zombieCard.png");
            zombieTrap = load("zombieTrap.png");
            swampZombieMinion = load("swampZombieMinion.png");
            undeadSoldier = load("undeadSoldier.png");
            undeadSoldierCard = load("undeadSoldierCard.png");
            zombieBiteSpell = load("zombieBite.png");
            skeletonSpell = load("skeletonSpell.png");
            skeletonMinion = load("skeletonSoldier.png");
            skelemancerMinion = load("skelemancerMinion.png");
            skelemancerCard = load("skelemancerCard.png");
            skullKingMinion = load("skullKingMinion.png");
            skullKingCard = load("skullKingCard.png");
            ghoulMinion = load("ghoulMinion.png");
            ghoulCard = load("ghoulCard.png");
            necromancyCard = load("necromancyCard.png");
            iconUndeadSmall = load("iconUndeadSmall.png");
            wraithMinion = load("wraithMinion.png");
            wraithCard = load("wraithCard.png");
            //Dragons
            grayDrakeMinion = load("grayDrakeMinion.png");
            grayDrakeCard = load("grayDrakeCard.png");
            firePlumeCard = load("firePlumeCard.png");
            volcanicDrakeCard = load("volcanicDrakeCard.png");
            volcanicDrakeMinion = load("volcanicDrakeMinion.png");
            dragonBreathCard = load("dragonBreathSpell.png");
            dragonSoulTrap = load("dragonEyeTrap.png");
            dragonSoulSpell = load("dragonEyeSpell.png");
            fireyWhelpCard = load("fireyWhelpCard.png");
            fireyWhelpMinion = load("fireyWhelpMinion.png");
            whiteHatchlingCard = load("whiteHatchlingCard.png");
            whiteHatchlingMinion = load("whiteHatchlingMinion.png");
            whiteDragonCard = load("whiteDragonCard.png");
            whiteDragonMinion = load("whiteDragonMinion.png");
            faerieDragonMinion = load("faerieDragonMinion.png");
            faerieDragonCard = load("faerieDragonCard.png");
            faerieHatchlingCard = load("faerieHatchlingCard.png");
            faerieHatchlingMinion = load("faerieHatchlingMinion.png");
            redDragonMinion = load("redDragonMinion.png");
            redDragonCard = load("redDragonCard.png");
            redHatchlingCard = load("redHatchlingCard.png");
            redHatchlingMinion = load("redHatchlingMinion.png");
            greenHatchlingMinion = load("greenHatchlingMinion.png");
            greenHatchlingCard = load("greenHatchlingCard.png");
            greenDragonMinion = load("greenDragonMinion.png");
            greenDragonCard = load("greenDragonCard.png");
            dragonEggMinion = load("dragonEggMinion.png");
            dragonEggCard = load("dragonEggCard.png");
            hatchlingCard = load("hatchlingCard.png");
            mothersCardCard = load("mothersCareCard.png");
            purifyingBlastCard = load("purifyingBlastCard.png");
            //Empire
            iconEmpire = load("iconEmpireKnight.png");
            empireHero = load("empireHero.png");
            apocolypseCard = load("apocalypseCard.png");
            snipeTrap = load("snipeTrap.png");
            griffonMinion = load("griffonMinion.png");
            griffonCard = load("griffonCard.png");
            doubleshotCard = load("doubleshotCard.png");
            cavalryGeneralMinion = load("cavalryGeneralMinion.png");
            cavalryGeneralCard = load("cavalryGeneralCard.png");
            enchantedSwordCard = load("enchantedSwordCard.png");
            //Elemental
            stoneGolemMinion = load("stoneGolemMinion.png");
            stoneGolemCard = load("stoneGolemCard.png");
            warGolemMinion = load("warGolemMinion.png");
            warGolemCard = load("warGolemCard.png");
            earthInfusionSpell = load("earthInfusionSpell.png");
            earthGolemMinion = load("earthGolemMinion.png");
            fireInfusionSpell = load("fireInfusionSpell.png");
            moltenGolemMinion = load("moltenGolemMinion.png");
            iceInfusionSpell = load("iceInfusionSpell.png");
            iceGolemMinion = load("iceGolemMinion.png");
            sorcererCard = load("sorcererCard.png");
            sorcererMinion = load("sorcererMinion.png");
            stoneElementalCard = load("stoneElementalCard.png");
            stoneElementalMinion = load("stoneElementalMinion.png");
            fireElementalCard = load("fireElementalCard.png");
            fireElementalMinion = load("fireElementalMinion.png");
            iceElementalCard = load("iceElementalCard.png");
            iceElementalMinion = load("iceElementalMinion.png");
            earthElementalMinion = load("earthElementalMinion.png");
            earthElementalCard = load("earthElementalCard.png");
            geomancerCard = load("geomancerCard.png");
            geomancerMinion = load("geomancerMinion.png");
            waterElementalCard = load("waterElementalCard.png");
            waterElementalMinion = load("waterElementalMinion.png");
            sandElementalMinion = load("sandElementalMinion.png");
            sandElementalCard = load("sandElementalCard.png");
            rock = load("rock.png");
            iceyWindCard = load("icyWindCard.png");
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
