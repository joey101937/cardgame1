/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Cards.Card;
import CustomDecks.CustomDeck;
import CustomDecks.HeroClass;
import cardgame1.Board;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Main frame for duel menu, PvE skirmish
 * @author Joseph
 */
public class DuelFrame extends JFrame{
    private BackgroundPane panel;
    private JPanel line;            //center devider
    private JLabel playerName, enemyName; //text names
    private JLabel playerPort, enemyPort; //class portraits, based on selected class
    private JLabel vsLabel; //vs image
    private JLabel loadedLabel; //displayed the currently loaded deck
    private JComboBox playerCombo, enemyCombo;
    private JLabel backButton;
    private JLabel playButton;
    public ChosenDeck playerDeck = getDeck("Base Deck"), enemyDeck = getDeck("Base Deck");
    public CustomDeck loadedCustom = null;
    private static Font classTitleFont = new Font("Arial",Font.BOLD,26);
    private static Font loadedFont = new Font("Arial",Font.BOLD,16);
    /**
     * constructor
     */
    public DuelFrame(){
        init();
        this.setVisible(true);
    }
    /**
     * initializes swing and awt components
     */
    private void init(){
        //this setup
        SpriteHandler.Initialize();
        this.setSize(700, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //panel setup
        panel = new BackgroundPane(Main.BackgroundImage);
        panel.setLayout(null);
        add(panel);
        //play button setup
        playButton = new JLabel();
        playButton.setIcon(new ImageIcon(SpriteHandler.PLAYtext));
        playButton.setSize(300, 100);
        playButton.setLocation(230,500);
        playButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
            dispose();
            Hero enemy = new Hero("enemyHero",enemyDeck.cards,enemyDeck.hClass.getHeroPortrait());
            Hero player = new Hero("playerHero",playerDeck.cards,playerDeck.hClass.getHeroPortrait());
            Board b = new Board(enemy,player, Toolkit.getDefaultToolkit().getScreenSize()); //toolkit gets the screen resolutionz
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        panel.add(playButton);
        //vs label setup
        vsLabel = new JLabel();
        vsLabel.setIcon(new ImageIcon(SpriteHandler.VS));
        vsLabel.setSize(150, 100);
        vsLabel.setLocation(270, 110);
        panel.add(vsLabel);
        //loadedLabel setup
        loadedLabel = new JLabel();
        loadedLabel.setSize(200, 40);
        loadedLabel.setLocation(15, 480);
        loadedLabel.setForeground(Color.black);
        loadedLabel.setFont(loadedFont);
        panel.add(loadedLabel);
        //black devider setup REMOVED
        line = new JPanel();
        line.setSize(7, 700);
        line.setLocation(325, 0);
        line.setBackground(Color.black);
       // panel.add(line); //removed
       //backbutton setup
       backButton = new JLabel();
       backButton.setIcon(new ImageIcon(SpriteHandler.backArrow));
       backButton.setSize(70,70);
       backButton.setLocation(0, 590);
       backButton.setToolTipText("Return to main menu");
       panel.add(backButton);
        //setup nameplates
        playerName = new JLabel();
        playerName.setSize(300,40);
        playerName.setText("Player");
        playerName.setLocation(95,300);
        playerName.setFont(classTitleFont);
        panel.add(playerName);
        enemyName = new JLabel();
        enemyName.setSize(300,40);
        enemyName.setText("AI");
        enemyName.setLocation(490, 300);
        enemyName.setFont(classTitleFont);
        panel.add(enemyName);
        //setup portraits
        playerPort = new JLabel();
        playerPort.setSize(300,200);
        playerPort.setLocation(40,50);
        playerPort.setIcon(new ImageIcon(SpriteHandler.ashePortrait.getSubimage(0, 60, 300, 110))); //should be set by chosen deck in future
        panel.add(playerPort);
        ////
        enemyPort = new JLabel();
        enemyPort.setSize(300,200);
        enemyPort.setLocation(420,50);
        enemyPort.setIcon(new ImageIcon(SpriteHandler.knightHero.getSubimage(0, 60, 300, 110))); //should be set by chosen deck in future
        panel.add(enemyPort);
        //setup JComboBox for deck selection
        playerCombo = new JComboBox();
        playerCombo.setSize(300, 40);
        playerCombo.setLocation(10, 350);
        playerCombo.addItem("Base Deck");
        playerCombo.addItem("Undead Deck");
        playerCombo.addItem("Feeding Frenzy Deck");
        playerCombo.addItem("Deep Sea Deck");
        playerCombo.addItem("Empire Deck");
        playerCombo.addItem("Dragon Deck");
        playerCombo.addItem("Elemental Deck");
        playerCombo.addItem("Load Custom...");
        playerCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDeck(playerCombo);
            }
        });
        panel.add(playerCombo);
        enemyCombo = new JComboBox();
        enemyCombo.setSize(300, 40);
        enemyCombo.setLocation(360, 350);
        enemyCombo.addItem("Base Deck");
        enemyCombo.addItem("Undead Deck");
        enemyCombo.addItem("Feeding Frenzy Deck");
        enemyCombo.addItem("Deep Sea Deck");
        enemyCombo.addItem("Empire Deck");
        enemyCombo.addItem("Dragon Deck");
        enemyCombo.addItem("Elemental Deck");
        enemyCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDeck(enemyCombo);
            }
        });
        panel.add(enemyCombo);
        setDeck(playerCombo);
        setDeck(enemyCombo);
        

    }
    
    public static void main(String[] args) {
        new DuelFrame();
    }
    
    /**
     * gets the build in deck with given title
     * NOTE: Returns base deck if given unknown name
     *       Returns null if loading custom deck
     * @param title title of the wanted deck
     * @return chosen deck of given name.
     */
    private ChosenDeck getDeck(String title) {
        ArrayList<Card> deck = new ArrayList<Card>();
        switch (title) {
            case "Base Deck":
                deck = LegacyGUI.getBaseDeck();
                return new ChosenDeck(deck, HeroClass.Neutral);
            case "Undead Deck":
                deck = LegacyGUI.getUndeadDeck();
                return new ChosenDeck(deck, HeroClass.Undead);
            case "Feeding Frenzy Deck":
                deck = LegacyGUI.getFishDeck();
                return new ChosenDeck(deck,HeroClass.Ocean);
            case "Deep Sea Deck":
                deck = LegacyGUI.getDeepSeaDeck();
                return new ChosenDeck(deck,HeroClass.Ocean);
            case "Elemental Deck":
                deck = LegacyGUI.getElementalDeck();
                return new ChosenDeck(deck,HeroClass.Elemental);
            case "Dragon Deck":
                deck = LegacyGUI.getDragonDeck();
                return new ChosenDeck(deck,HeroClass.Dragon);
            case "Empire Deck":
                deck = LegacyGUI.getEmpireDeck();
                return new ChosenDeck(deck,HeroClass.Empire);
            case "Load Custom...":
                  DeckLoaderScratch dls = new DeckLoaderScratch(this);
                  this.setEnabled(false);
                  return null;
            default:
                JOptionPane.showMessageDialog(null, "Error: No built-in deck found with name " + title);
                return getDeck("Base Deck");
        }
    }

    public void setPlayerDeck(ArrayList<Card> deck, HeroClass c){
        playerDeck = new ChosenDeck(deck,c);
    }

    public void setEnemyDeck(ArrayList<Card> deck, HeroClass c) {
        enemyDeck = new ChosenDeck(deck, c);
    }

    /**
     * updates the portraits and decks based on what the user chooses
     */
    public void setDeck(JComboBox combo) {
        //assign a deck to the get value. if the player is loading a custom deck, this will return null so we dont need to assign anything
        ChosenDeck deck = getDeck(combo.getSelectedItem().toString());
        if (deck == null) { //loading custom deck
            updatePortrait();
            return;
        }
        if (combo == playerCombo) {

            playerDeck = getDeck(combo.getSelectedItem().toString());
            System.out.println("resetting custom");
            loadedCustom = null; //because we are not loading custom, we must be setting to preset and therefore must reset the loaded deck label
        } else {
            enemyDeck = getDeck(combo.getSelectedItem().toString());
        }
        updatePortrait();
    }

    /**
     * sets portraits to loaded decks
     */
    public void updatePortrait(){
        playerPort.setIcon(new ImageIcon(playerDeck.hClass.getHeroPortrait().getSubimage(50, 00, 200, 200)));
        enemyPort.setIcon(new ImageIcon(enemyDeck.hClass.getHeroPortrait().getSubimage(50, 00, 200, 200)));
        if(loadedCustom != null)loadedLabel.setText("Loaded Deck: " + loadedCustom.deckName);
        else loadedLabel.setText("");
        repaint(); 
    }
    /**
     * holds a arraylist of cards and a hero class
     */
    public class ChosenDeck{
        public ArrayList<Card> cards;
        public HeroClass hClass;
        public ChosenDeck(ArrayList<Card> ac, HeroClass hc){
            hClass = hc;
            cards = ac;
        }
    }
}
