/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Campaign;

import Campaign.campaignGUI.CampaignInterface;
import Campaign.campaignGUI.HeroSelector;
import Cards.Base.*;
import Cards.Card;
import CustomDecks.CorruptFileException;
import CustomDecks.CustomDeck;
import static CustomDecks.CustomDeck.getCard;
import CustomDecks.HeroClass;
import CustomDecks.NoSuchCardException;
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
import javax.swing.JOptionPane;

/**
 * Controls pacing and meta control of campaign
 * @author Joseph
 */
public class CampaignManager {
    private static String fileName = "SavedGame.campaign";
    public static int level = 1;
    public static ArrayList<Card> playerCards = new ArrayList<>();
    public static HeroClass playerClass = HeroClass.Neutral;
    
    /**
     * run when campaign launched
     */
    public static void init() {
        if (savedGameExists()) {
            try {
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
    private static void loadGame() throws FileNotFoundException, IOException, CorruptFileException{
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
                 for(int i = 1; i < lines.size();i++){
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
    private static void saveGame() {
        File output = new File(fileName);
        ArrayList<String> lines = new ArrayList<>();
        lines.add(String.valueOf(level));
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
    }
    
    public ArrayList<Card> getPlayerDeck(){
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
     * gets enemy decks for each level
     * @param level
     * @return
     * @throws Exception if no decks are on file for that level
     */
    public ArrayList<Card> getDeckForLevel(int level) throws Exception{
        ArrayList<Card> deck = new ArrayList<>();
        switch(level){
            case 1:
                for(int i = 0; i<3; i++){
                    deck.add(new ArakkoaCard());
                    deck.add(new KnightCard());
                    deck.add(new KelpieCard());
                }
                deck.add(new UndyingSoldierCard());
                break;
            case 2:
                //todo
                break;
            default: throw new Exception("No deck available for level " + level);
        }
        return deck;
    }
}
