/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Cards.Base.ArakkoaCard;
import Cards.Base.ArcherCard;
import Cards.Base.FireBoltCard;
import Cards.Base.FireSpearCard;
import Cards.Base.FrostBearCard;
import Cards.Base.FrostDragonCard;
import Cards.Base.KnightCard;
import Cards.Base.PaladinCard;
import Cards.Base.SpellBookCard;
import Cards.Base.VengefullKnightCard;
import Cards.Base.VolcanoCard;
import Cards.Card;
import CustomDecks.HeroClass;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Main frame for duel menu
 * @author Joseph
 */
public class DuelFrame extends JFrame{
    private BackgroundPane panel;
    private JPanel line;            //center devider
    private JLabel playerName, enemyName; //text names
    private JLabel playerPort, enemyPort; //class portraits, based on selected class
    private ChosenDeck playerDeck, enemyDeck;
    private static Font classTitleFont = new Font("Arial",Font.BOLD,26);
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
        //black devider setup
        line = new JPanel();
        line.setSize(7,700);
        line.setLocation(325, 0);
        line.setBackground(Color.black);
        panel.add(line);
        //setup nameplates
        playerName = new JLabel();
        playerName.setSize(300,40);
        playerName.setText("Player");
        playerName.setLocation(120, 70);
        playerName.setFont(classTitleFont);
        panel.add(playerName);
        enemyName = new JLabel();
        enemyName.setSize(300,40);
        enemyName.setText("AI");
        enemyName.setLocation(490, 70);
        enemyName.setFont(classTitleFont);
        panel.add(enemyName);
        //setup portraits
        playerPort = new JLabel();
        playerPort.setSize(300,200);
        playerPort.setLocation(10,60);
        playerPort.setIcon(new ImageIcon(SpriteHandler.ashePortrait.getSubimage(0, 60, 300, 110))); //should be set by chosen deck in future
        panel.add(playerPort);
        
        enemyPort = new JLabel();
        enemyPort.setSize(300,200);
        enemyPort.setLocation(350,60);
        enemyPort.setIcon(new ImageIcon(SpriteHandler.knightHero.getSubimage(0, 60, 300, 110))); //should be set by chosen deck in future
        panel.add(enemyPort);
    }
    
    public static void main(String[] args) {
        new DuelFrame();
    }
    
    /**
     * gets the build in deck with given title
     * @param title title of the wanted deck
     * @return chosen deck of given name. if 
     */
    private ChosenDeck getDeck(String title){
        ArrayList<Card> deck = new ArrayList<Card>();
        switch (title) {
            case "Base Deck":
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
                deck.add(new FireSpearCard());
                deck.add(new PaladinCard());
                deck.add(new PaladinCard());
                return new ChosenDeck(deck, HeroClass.Neutral);
            default:
                JOptionPane.showMessageDialog(null, "Error: No built-in deck found with name " + title);
                return getDeck("Base Deck");
        }
    }


    
    /**
     * holds a arraylist of cards and a hero class
     */
    private class ChosenDeck{
        public ArrayList<Card> cards;
        public HeroClass hClass;
        public ChosenDeck(ArrayList<Card> ac, HeroClass hc){
            hClass = hc;
            cards = ac;
        }
    }
}
