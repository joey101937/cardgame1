/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Cards.Card;
import CustomDecks.CustomDeck;
import CustomDecks.HeroClass;
import Multiplayer.Phantom;
import cardgame1.Board;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Joseph
 */
public class MultiplayerFrame extends JFrame{
    private BackgroundPane panel;
    private JLabel chooseDeckTitle;
    private JLabel playerPort; //class portraits, based on selected class
    private JLabel loadedLabel; //displayed the currently loaded deck
    private JComboBox playerCombo;
    private JLabel backButton;
    private JLabel hostButton,joinButton;
    public ChosenDeck playerDeck = getDeck("Base Deck");
    public CustomDeck loadedCustom = null;
    private static Font classTitleFont = new Font("Arial",Font.BOLD,35);
    private static Font loadedFont = new Font("Arial",Font.BOLD,16);
    /**
     * constructor
     */
    public MultiplayerFrame(){
        init();
        this.setVisible(true);
    }
    /**
     * initializes swing and awt components
     */
    private void init(){
        //this setup
        System.out.println("setting location to " + LandingPage.metaX + " " + LandingPage.metaY);
        this.setLocation(LandingPage.metaX, LandingPage.metaY);
        SpriteHandler.Initialize();
        this.setSize(700, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIconImage(SpriteHandler.swordsSmall);
        //panel setup
        panel = new BackgroundPane(Main.BackgroundImage);
        panel.setLayout(null);
        add(panel);
        //play button setup
        hostButton= new JLabel();
        //hostButton.setIcon(new ImageIcon(SpriteHandler.HOSTtext));
        hostButton.setText("Host");
        hostButton.setFont(classTitleFont);
        hostButton.setSize(100, 100);
        hostButton.setLocation(200,450);
        joinButton = new JLabel();
        //joinButton.setIcon(new ImageIcon(SpriteHandler.JOINtext));
        joinButton.setText("Join");
        joinButton.setFont(classTitleFont);
        joinButton.setSize(100,100);
        joinButton.setLocation(400,450);
        panel.add(hostButton);
        panel.add(joinButton);
      
        joinButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            launchMP(false);
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {
            joinButton.setForeground(Color.red);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            joinButton.setForeground(Color.darkGray);
            }
        });
        hostButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            launchMP(true);
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {
            hostButton.setForeground(Color.red);}
            @Override
            public void mouseExited(MouseEvent e) {
            hostButton.setForeground(Color.darkGray);}
        });
        //loadedLabel setup
        loadedLabel = new JLabel();
        loadedLabel.setSize(200, 40);
        loadedLabel.setLocation(200, 380);
        loadedLabel.setForeground(Color.black);
        loadedLabel.setFont(loadedFont);
        panel.add(loadedLabel);
       //backbutton setup
       backButton = new JLabel();
       backButton.setIcon(new ImageIcon(SpriteHandler.backArrow));
       backButton.setSize(70,70);
       backButton.setLocation(0, 590);
       backButton.setToolTipText("Return to main menu");
       backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { 
            dispose();
            new LandingPage();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
       panel.add(backButton);
        //setup nameplates
        chooseDeckTitle = new JLabel();
        chooseDeckTitle.setSize(300,40);
        chooseDeckTitle.setText("Choose Deck");
        chooseDeckTitle.setLocation(235,300);
        chooseDeckTitle.setFont(classTitleFont);
        panel.add(chooseDeckTitle);
        //setup portraits
        playerPort = new JLabel();
        playerPort.setSize(300,200);
        playerPort.setLocation(240,50);
        playerPort.setIcon(new ImageIcon(SpriteHandler.ashePortrait.getSubimage(0, 60, 300, 110))); //should be set by chosen deck in future
        panel.add(playerPort);
       
        //setup JComboBox for deck selection
        playerCombo = new JComboBox();
        playerCombo.setSize(300, 40);
        playerCombo.setLocation(200, 350);
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
        playerCombo.setFont(loadedFont);
        panel.add(playerCombo);    
        setDeck(playerCombo);
    }
    
    public static void main(String[] args) {
        new MultiplayerFrame();
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
            loadedCustom = null; //because we are not loading custom, we must be setting to preset and therefore must reset the loaded deck label
        }
        updatePortrait();
    }

    /**
     * sets portraits to loaded decks
     */
    public void updatePortrait(){
        playerPort.setIcon(new ImageIcon(playerDeck.hClass.getHeroPortrait().getSubimage(50, 00, 200, 200)));
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
    
    private void launchMP(boolean isServer){
        System.out.println("launchMP " + isServer);
       if (this.playerCombo.getSelectedItem().equals("Load Custom...")) {
            if (this.loadedCustom == null) {
                JOptionPane.showMessageDialog(null, "Custom deck not loaded");
                return; 
            }
        }
        Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize(); //size of screen
        Hero bot = new Hero("user", playerDeck.cards, playerDeck.hClass.getHeroPortrait());
        Hero top = new Hero("top", new ArrayList<Card>(), SpriteHandler.knightHero); //emptyDeck because phantom will populate it
        if(!isServer)Phantom.connectionAddress = JOptionPane.showInputDialog("Enter Connection Address");
        if(Phantom.connectionAddress==null)return;
        top.isAIControlled=false;
        Board board;
        String notice = "Game will now wait 25 seconds for another player to connect.";
        try{
             //InetAddress inetAddress = InetAddress.getLocalHost(); //gets local ip
            notice+= ("\nServer Address: " + Main.getPublicIP() + "\nPort: 444");
        }catch(Exception e){
            notice += "\nApp an error getting public IP";
            e.printStackTrace();
        }
        if(isServer)JOptionPane.showMessageDialog(null, notice);
        try {
             board = new Board(top, bot, screenRes, isServer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void dispose() {
        LandingPage.metaX = this.getLocationOnScreen().x;
        LandingPage.metaY = this.getLocationOnScreen().y;
        super.dispose();
    }
}
