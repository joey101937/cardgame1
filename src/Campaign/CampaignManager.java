/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Campaign;

import Campaign.campaignGUI.CampaignInterface;
import Campaign.campaignGUI.CardAdderFrame;
import Campaign.campaignGUI.HeroSelector;
import Cards.Base.*;
import Cards.Card;
import Cards.Dragon.DragonBreathCard;
import Cards.Dragon.DragonSoulTrapCard;
import Cards.Dragon.GrayDrakeCard;
import Cards.Dragon.VolcanicDrakeCard;
import Cards.Elemental.*;
import Cards.Empire.ApocalypseCard;
import Cards.Empire.CavalryGeneralCard;
import Cards.Empire.DoubleshotCard;
import Cards.Empire.EnchantedSwordCard;
import Cards.Empire.FireyWhelpCard;
import Cards.Empire.GriffonCard;
import Cards.Empire.SnipeTrapCard;
import Cards.Fish.JellyfishCard;
import Cards.Fish.PirranahCard;
import Cards.Fish.PredationCard;
import Cards.Fish.SeaSerpentTrapCard;
import Cards.Fish.SeaWitchCard;
import Cards.Fish.SwollowCard;
import Cards.Fish.ThrasherCard;
import Cards.Undead.SkelemancerCard;
import Cards.Undead.SkeletonArmySpell;
import Cards.Undead.SkullKingCard;
import Cards.Undead.ZombieBiteSpell;
import Cards.Undead.ZombieTrapCard;
import CustomDecks.CorruptFileException;
import CustomDecks.CustomDeck;
import static CustomDecks.CustomDeck.getCard;
import CustomDecks.HeroClass;
import CustomDecks.NoSuchCardException;
import GUI.LandingPage;
import cardgame1.Board;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import cardgame1.Window;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Controls pacing and meta control of campaign
 * @author Joseph
 */
public class CampaignManager {
    private static String fileName = Main.assets+"SavedGame.campaign";
    public static int level = 1;
    public static ArrayList<Card> playerCards = new ArrayList<>();
    public static HeroClass playerClass = HeroClass.Neutral;
    public static final int NUM_LEVELS = 14; //number of levels in the game
   
    public static void main(String[] args){
    Main.setBackgroundImage();
    SpriteHandler.Initialize();
    init();
    }
    
    /**
     * run when campaign launched
     */
    public static void init() {
        SpriteHandler.Initialize();
        if (savedGameExists()) {
            try {
                String[] options = {"Continue", "Start New Game"};
                int choice = JOptionPane.showOptionDialog(null, "Saved game detected. Continue?", "Continue Prompt", 0, 0, new ImageIcon(SpriteHandler.swordsSmall), options, "init");
                if(choice == -1){
                    //exit back to main menu
                    new LandingPage();
                    return;
                }
                if(choice  == 1){
                    level = 1;
                    playerCards = new ArrayList<>();
                    new HeroSelector();
                    return;
                }
                //-1 = exit, 0 = options[0], 1= options[1]
                //should only get here if user choses continue
                loadGame();
                startGame();
                return;
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading saved game, starting new game");
            }
        }
        //no save or save was corrupt
        level = 1;
        new HeroSelector();
    }

    /**
     * begins the campaign
     */
    public static void startGame(){
        CampaignInterface ci = new CampaignInterface();
    }
    
    
    
    private static boolean savedGameExists(){
        File f = new File(fileName);
        return f.exists();
    }
    
    
    /*   
    saved game files are stored with level on first line, then a list of cards in player's deck following that.
    Example:
    2
    knight
    knight
    knight
    Paladin
    */
    
    
    
    
    /**
     * loads game saved in save game file
     */
    public static void loadGame() throws FileNotFoundException, IOException, CorruptFileException{
        ArrayList<String> lines = new ArrayList<>(); //output is where we store all the lines from the given file
        File file = new File(fileName);
        InputStream ips = new FileInputStream(file);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
         if(lines.size() < 3) throw new CorruptFileException("Too few lines to be valid file: " + file.getName());
         level = Integer.parseInt(lines.get(0));
         playerClass = HeroClass.valueOf(lines.get(1));
                 for(int i = 2; i < lines.size();i++){
            try {
                playerCards.add(getCard(lines.get(i)));
            } catch (NoSuchCardException ex) {
                throw new CorruptFileException(ex.getMessage());
            }
        }
        System.out.println(file.getName() + " loaded");
    }

    /**
     * saves game to save game file
     */
    public static void saveGame() {
        System.out.println("Saving Game...");
        File output = new File(fileName);
        if(output.exists())output.delete();
        ArrayList<String> lines = new ArrayList<>();
        lines.add(String.valueOf(level));
        lines.add(playerClass.toString());
        for (Card c : playerCards) {
            lines.add(c.name);
        }
        try {
            FileWriter fw = new FileWriter(output, false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String s : lines) {
                bw.write(s);
                System.out.println("writing... " + s);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Game Save Complete");
    }
    
    public static ArrayList<Card> getPlayerDeck(){
        ArrayList<Card> output = new ArrayList<>();
        for(Card c : playerCards){
            try {
                output.add(CustomDeck.getCard(c.name));
            } catch (NoSuchCardException ex) {
                ex.printStackTrace();
            }
        }
        return output;
    }
    
    /**
     * run this method when an indevidual round ends
     * @param loser hero that died, ending the round
     */
    public static void onGameEnd(Hero loser){
        if(loser == Board.playerHero){ //player loss
            JOptionPane.showMessageDialog(null, "Round Lost!");
            returnToInterface();
            return;
        }else{
            level++;
            if(level>NUM_LEVELS){
                onCampaignVictory();
                return;
            }
            JOptionPane.showMessageDialog(null, "Round Won!");
            goToCardAdder();
            return;
        }
    }
    
    /**
     * run when user beats the campaign
     */
    private static void onCampaignVictory(){
        JOptionPane.showMessageDialog(null, "YOU HAVE BEATEN CAMAPGIN!");
        returnToInterface(); //let them see the victorious interface, should have no more 'play' button on it
    }
    /**
     * ends the current game instance and returns to campaign interface
     */
    public static void returnToInterface() {
        Board board = Board.getMainBoard();
        board.running = false;
        Window window = board.window;
        JFrame frame = window.frame;
        if (frame == null) {
            System.out.println("frame is null");
            System.exit(1);
        } else {
            frame.dispose();
            board.running = false;
            new CampaignInterface();
            //available = true;
        }
    }
    
    public static void goToCardAdder(){
        Board board = Board.getMainBoard();
        board.running = false;
        Window window = board.window;
        JFrame frame = window.frame;
        if (frame == null) {
            System.out.println("frame is null");
            System.exit(1);
        } else {
            frame.dispose();
            board.running = false;
            if(level<=4)new CardAdderFrame(4);
            else new CardAdderFrame(2);
            //available = true;
        }
    }
    
    /**
     * gets enemy decks for each level
     * @param level
     * @return
     * @throws Exception if no decks are on file for that level
     */
    public static ArrayList<Card> getDeckForLevel(int level) throws Exception{
        ArrayList<Card> deck = new ArrayList<>();
        switch(level){
            case 1:
                for(int i = 0; i<5; i++){
                    deck.add(new ArakkoaCard());
                    deck.add(new KnightCard());
                }
                deck.add(new UndyingSoldierCard());
                break;
            case 2:
                deck.add(new ArakkoaCard());
                deck.add(new ArakkoaCard());
                deck.add(new ArcherCard());
                deck.add(new KnightCard());
                deck.add(new KnightCard());
                deck.add(new KnightCard());
                deck.add(new KnightCard());
                deck.add(new FrostBearCard());
                deck.add(new FrostBearCard());
                deck.add(new FrostBearCard());
                deck.add(new VolcanoCard());
                deck.add(new VolcanoCard());
                break;
            case 3:
                for (int i = 0; i < 3; i++) {
                    deck.add(new ArakkoaCard());
                    deck.add(new ArcherCard());
                    deck.add(new FireBoltCard());
                    deck.add(new FrostBearCard());
                    deck.add(new KnightCard());
                    deck.add(new VengefullKnightCard());
                    deck.add(new VolcanoCard());
                }
                deck.add(new SpellBookCard());
                deck.add(new SpellBookCard());
                deck.add(new FireSpearCard());
                deck.add(new FireSpearCard());
                deck.add(new PaladinCard());
                deck.add(new PaladinCard());
                break;
            case 4: //base deck
                for (int i = 0; i < 3; i++) {
                    deck.add(new ArakkoaCard());
                    deck.add(new ArcherCard());
                    deck.add(new FireBoltCard());
                    deck.add(new FrostBearCard());
                    deck.add(new KnightCard());
                    deck.add(new VengefullKnightCard());
                    deck.add(new VolcanoCard());
                    FrostDragonCard fdc = new FrostDragonCard();
                    //fdc.summon.attack = 4; //frost dragons in this deck are strong than usual
                    //fdc.cost--;
                    deck.add(fdc);
                }
                deck.add(new SpellBookCard());
                deck.add(new SpellBookCard());
                deck.add(new FireSpearCard());
                deck.add(new PaladinCard());
                deck.add(new PaladinCard());
                break;
            case 5: //empire deck
                for (int i = 0; i < 3; i++) {
                    deck.add(new SnipeTrapCard());
                    deck.add(new DoubleshotCard());
                    deck.add(new GriffonCard());
                    deck.add(new KnightCard());
                    deck.add(new ArcherCard());
                }
                for (int i = 0; i < 2; i++) {
                    deck.add(new PaladinCard());
                    deck.add(new SpearmanCard());
                    deck.add(new CavalryGeneralCard());
                    deck.add(new FireyWhelpCard());
                }
                deck.add(new UndyingSoldierCard());
                deck.add(new ApocalypseCard());
                break;
            case 6: //deep sea
                for (int i = 0; i < 3; i++) {
                    deck.add(new JellyfishCard());
                    deck.add(new SeaWitchCard());
                    deck.add(new PirranahCard());
                    deck.add(new SeaSerpentTrapCard());
                    deck.add(new ThrasherCard());
                }
                for (int i = 0; i < 2; i++) {
                    deck.add(new SwollowCard());
                    deck.add(new PredationCard());
                    deck.add(new VolcanoCard());
                    //deck.add(new KelpieCard());
                }
                deck.add(new ThrasherCard());
                deck.add(new SpellBookCard());
                break;
            case 7: //undead
                for (int i = 0; i < 5; i++) {
                    deck.add(new ArcherCard());
                    deck.add(new KnightCard());
                }
                for (int i = 0; i < 4; i++) {
                    deck.add(new SkeletonArmySpell());
                }
                for (int i = 0; i < 2; i++) {
                    deck.add(new SkelemancerCard());
                    deck.add(new SkullKingCard());
                    deck.add(new ZombieTrapCard());
                    deck.add(new FireBoltCard());
                    deck.add(new VolcanoCard());
                }
                deck.add(new ZombieBiteSpell());
                deck.add(new SpellBookCard());
                break;
            case 8:
                deck.add(new StoneGolemCard());
                deck.add(new StoneGolemCard());
                deck.add(new StoneGolemCard());
                deck.add(new GeomancerCard());
                deck.add(new GeomancerCard());
                deck.add(new GeomancerCard());
                deck.add(new SorcererCard());
                deck.add(new SorcererCard());
                deck.add(new SorcererCard());
                deck.add(new EarthInfusionSpell());
                deck.add(new EarthInfusionSpell());
                deck.add(new FireInfusionSpell());
                deck.add(new IceInfusionSpell());
                deck.add(new SandElementalCard());
                deck.add(new SandElementalCard());
                deck.add(new SandElementalCard());
                deck.add(new VolcanoCard());
                deck.add(new VolcanoCard());
                deck.add(new ArcherCard());
                deck.add(new MinotaurCard());
                deck.add(new SpellBookCard());
                deck.add(new SpellBookCard());
                deck.add(new PaladinCard());
                deck.add(new PaladinCard());
                break;
            case 9: //dragon
                for (int i = 0; i < 5; i++) {
                    deck.add(new GrayDrakeCard());
                    deck.add(new VolcanicDrakeCard());
                    deck.add(new FrostDragonCard());
                    deck.add(new ArcherCard());
                    deck.add(new VolcanoCard());
                    deck.add(new PaladinCard());
                }
                for (int i = 0; i < 3; i++) {
                    deck.add(new DragonSoulTrapCard());
                    deck.add(new DragonBreathCard());
                }
                deck.add(new SpearmanCard());
                deck.add(new UndyingSoldierCard());
                break;
            case 10://hard empire (empireburn)
                deck.add(new CavalryGeneralCard());
                deck.add(new CavalryGeneralCard());
                deck.add(new CavalryGeneralCard());
                deck.add(new GriffonCard());
                deck.add(new GriffonCard());
                deck.add(new GriffonCard());
                deck.add(new EnchantedSwordCard());
                deck.add(new EnchantedSwordCard());
                deck.add(new EnchantedSwordCard());
                deck.add(new FireyWhelpCard());
                deck.add(new FireyWhelpCard());
                deck.add(new SpearmanCard());
                deck.add(new SpearmanCard());
                deck.add(new SpearmanCard());
                deck.add(new VengefullKnightCard());
                deck.add(new VengefullKnightCard());
                deck.add(new VengefullKnightCard());
                deck.add(new KnightCard());
                deck.add(new KnightCard());
                deck.add(new KnightCard());
                deck.add(new ArcherCard());
                deck.add(new ArcherCard());
                deck.add(new ArcherCard());
                deck.add(new FrostBearCard());
                deck.add(new FrostBearCard());
                deck.add(new SpellBookCard());
                deck.add(new SpellBookCard());
                break;
            case 11: //hard fish (deepsea again
                for (int i = 0; i < 6; i++) {
                    deck.add(new JellyfishCard());
                    deck.add(new SeaWitchCard());
                    deck.add(new PirranahCard());
                    deck.add(new SeaSerpentTrapCard());
                    deck.add(new ThrasherCard());
                }
                for (int i = 0; i < 4; i++) {
                    deck.add(new SwollowCard());
                    deck.add(new PredationCard());
                    deck.add(new VolcanoCard());
                    //deck.add(new KelpieCard());
                    deck.add(new SpellBookCard());
                }
                deck.add(new ThrasherCard());
                break;
            case 12:
                for (int i = 0; i < 10; i++) {
                    deck.add(new ArcherCard());
                    deck.add(new KnightCard());
                }
                for (int i = 0; i < 6; i++) {
                    deck.add(new SkeletonArmySpell());
                }
                for (int i = 0; i < 4; i++) {
                    deck.add(new SkelemancerCard());
                    deck.add(new SkullKingCard());
                    deck.add(new ZombieTrapCard());
                    deck.add(new FireBoltCard());
                    deck.add(new VolcanoCard());
                    deck.add(new SpellBookCard());
                }
                deck.add(new ZombieBiteSpell());
                deck.add(new ZombieBiteSpell());         
                break;
            case 13:
                deck.add(new StoneGolemCard());
                deck.add(new StoneGolemCard());
                deck.add(new StoneGolemCard());
                deck.add(new StoneGolemCard());
                deck.add(new StoneGolemCard());
                deck.add(new StoneGolemCard());
                deck.add(new GeomancerCard());
                deck.add(new GeomancerCard());
                deck.add(new GeomancerCard());
                deck.add(new SorcererCard());
                deck.add(new SorcererCard());
                deck.add(new SorcererCard());
                deck.add(new SorcererCard());
                deck.add(new EarthInfusionSpell());
                deck.add(new EarthInfusionSpell());
                deck.add(new FireInfusionSpell());
                deck.add(new IceInfusionSpell());
                deck.add(new SandElementalCard());
                deck.add(new SandElementalCard());
                deck.add(new SandElementalCard());
                deck.add(new VolcanoCard());
                deck.add(new VolcanoCard());
                deck.add(new ArcherCard());
                deck.add(new MinotaurCard());
                deck.add(new SpellBookCard());
                deck.add(new SpellBookCard());
                deck.add(new SpellBookCard());
                deck.add(new PaladinCard());
                deck.add(new PaladinCard());
                break;
            case 14:
                for (int i = 0; i < 4; i++) {
                    deck.add(new GrayDrakeCard());
                    deck.add(new VolcanicDrakeCard());
                    deck.add(new FrostDragonCard());
                    deck.add(new ArcherCard());    
                    deck.add(new PaladinCard());
                }
                for (int i = 0; i < 2; i++) {
                    deck.add(new DragonSoulTrapCard());
                    deck.add(new DragonBreathCard());
                    deck.add(new DragonBreathCard());
                     deck.add(new VolcanoCard());
                }
                deck.add(new SpearmanCard());
                deck.add(new SpearmanCard());
                deck.add(new SpellBookCard());
                deck.add(new SpellBookCard());
                deck.add(new SpellBookCard());
                deck.add(new UndyingSoldierCard());
                break;
            default:
                throw new Exception("No deck available for level " + level);
        }
        return deck;
    }

    /**
     * returns the enemy class for the given level
     */
    public static HeroClass getEnemyClassForLevel(int level) {
        switch (level) {
            case 1:
            case 2:
            case 3:
            case 4:
                return HeroClass.Neutral;
            case 5:
            case 10:
                return HeroClass.Empire;
            case 6:
            case 11:
                return HeroClass.Ocean;
            case 7:
            case 12:
                return HeroClass.Undead;
            case 8:
            case 13:
                return HeroClass.Elemental;
            case 9:
            case 14:
                return HeroClass.Dragon;
            default:
                System.out.println("Error, no class listed for level " + level + " -CampaignMangager.getEnemyClassForLevel(" + level + ")");
                return HeroClass.Restricted;
        }
    }
}
