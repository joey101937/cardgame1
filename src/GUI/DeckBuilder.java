/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Cards.Card;
import CustomDecks.CustomDeck;
import CustomDecks.HeroClass;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JScrollPane;

/**
 * Interface for user to create new deck
 * @author Joseph
 */
public class DeckBuilder extends JFrame{
    /*       FIELDS        */
    private JPanel panel; //main content holder
    private JScrollPane scroll;
    private JPanel interior; //panel inside scrollpane
    private ArrayList<JLabel> cardLabels = new ArrayList<>(); //holds list of cards currently in deck
    private JLabel titleLabel;
    private JLabel classTitle;
    private JComboBox classCombo; //select deck class with this
    private JLabel classLabel; //displays current class of deck
    private JLabel isValidLabel;
    public CustomDeck product = new CustomDeck("Unnamed", new ArrayList<Card>(), HeroClass.Neutral); //deck we are building
    private static Font titleFont = new Font("Times", Font.BOLD, 35);
    private static Font classTitleFont = new Font("Arial",Font.PLAIN,20);
    
    /**
     * constructor
     */
    public DeckBuilder(){
        this.setSize(1000, 700);
        this.setPreferredSize(new Dimension(1000,700));
        this.setIconImage(SpriteHandler.swords);
        init();
        this.setVisible(true);
        this.requestFocus();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    /**
     * initializes core components
     */
    private void init(){
        panel = new BackgroundPane(Main.BackgroundImage);
        panel.setSize(this.getSize());
        panel.setLayout(null);
        this.add(panel);
        
        scroll = new JScrollPane();
        scroll.setSize(700,500);
        scroll.setLocation(10, 100);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scroll.getHorizontalScrollBar().setEnabled(false);
        interior = new JPanel();
        interior.setLocation(0,0);
        interior.setSize(700,1000);
        interior.setPreferredSize(interior.getSize());
        interior.setBackground(Color.gray);
        scroll.add(interior);
        scroll.setViewportView(interior);
        panel.add(scroll);
        interior.setLayout(null);
        
        titleLabel = new JLabel();
        titleLabel.setSize(300,50);
        titleLabel.setText("DECK BUILDER");
        titleLabel.setLocation(400,20);
        titleLabel.setFont(titleFont);
        panel.add(titleLabel);
        
        classTitle = new JLabel();
        classTitle.setFont(classTitleFont);
        classTitle.setText("Deck Class:");
        classTitle.setLocation(20,10);
        classTitle.setSize(200,30);
        panel.add(classTitle);
        
        classCombo = new JComboBox();
        classCombo.setSize(new Dimension(200,25));
        classCombo.setLocation(20,50);
        classCombo.addItem(HeroClass.Neutral);
        classCombo.addItem(HeroClass.Ocean);
        classCombo.addItem(HeroClass.Undead);
        classCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               updateDeckClass();
            }
        });
        panel.add(classCombo);
        
        classLabel = new JLabel();
        classLabel.setSize(80,80);
        classLabel.setLocation(230, 20);
        classLabel.setIcon(new ImageIcon(product.deckClass.getClassIcon()));
        panel.add(classLabel);
        
        isValidLabel = new JLabel();
        isValidLabel.setSize(200,100);
        isValidLabel.setLocation(750,570);
        isValidLabel.setFont(classTitleFont);
        updateIsValidLabel();
        isValidLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                if (!product.isValid()) {
                    String toDisplay = "";
                    for (String s : product.diagnose()) {
                        toDisplay += (s + "\n");
                    }
                    JOptionPane.showMessageDialog(null, toDisplay);
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        panel.add(isValidLabel);
        
        int column = 0;
        int row = 0;
        for(Card c : Card.getCardList()){
            CardIcon ci = new CardIcon(c, this);
            ci.setLocation(10 + column*(c.sprite.getWidth() + 30), row*(c.sprite.getHeight() + 20));
            column++;
            if(column > 2){
            row++;
            column = 0;
            }
            interior.add(ci);
        }
       interior.setPreferredSize(new Dimension(700, row*(300+20)));
              
    }
    
    public static void main(String[] args) {
        Main.setBackgroundImage();
        SpriteHandler.Initialize();
        DeckBuilder db = new DeckBuilder();
        db.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * updates the list of added cards in the deck
     */
    private void updateList(){
        for(JLabel cl : cardLabels){
            panel.remove(cl);
        }
        cardLabels.clear();
        int i = 0;
        for(Card c : this.product.deck){
            CardLabel cl = new CardLabel(c);
            cardLabels.add(cl);
            cl.setSize(300, 22);
            cl.setLocation(800, 100 + 22 * i);
            panel.add(cl);  
            i++;
        }
        JLabel numCards = new JLabel();
        numCards.setText(product.deck.size()+"/"+CustomDeck.MIN_NUM_CARDS);
        numCards.setSize(300,22);
        numCards.setLocation(800,100+22*i);
        cardLabels.add(numCards);
        panel.add(numCards);
       classLabel.setIcon(new ImageIcon(product.deckClass.getClassIcon()));
       updateIsValidLabel();
       this.panel.repaint();
       setVisible(true);
    }
    
    /**
     * updates deck class using combo
     */
    private void updateDeckClass(){
        HeroClass hc;
        hc = (HeroClass)classCombo.getItemAt(classCombo.getSelectedIndex());
        product.deckClass = hc;
        classLabel.setIcon(new ImageIcon(product.deckClass.getClassIcon()));
        panel.repaint();
    }
    
    /**
     * Attempts to add a card to deck.
     * If the card is not able to be added due to custom deck restrictions, it will prompt the user and not add the card
     * @param c card to add
     */
    public void addCard(Card c){
        if(c.heroClass != product.deckClass && c.heroClass!=HeroClass.Neutral){
            JOptionPane.showMessageDialog(null, c.heroClass + " card cannot be added to " + product.deckClass + " deck.");
            return;
        }
        if(product.deck.size()+1 > CustomDeck.MAX_NUM_CARDS){
            JOptionPane.showMessageDialog(null,"Maximum number of cards in deck");
            return;
        }
        int numCopies = 0;
        for(Card card: product.deck){
        if(card.name == c.name){
            numCopies++;
            }
        }
        if(numCopies+1 > CustomDeck.MAX_NUM_COPIES){
            JOptionPane.showMessageDialog(null,"Cannot have more than " + CustomDeck.MAX_NUM_COPIES + " copies of the same card in a deck");
            return;
        }
        product.deck.add(c);
        updateList();
    }
    
    /**
     * updates teh is valid label
     */
    private void updateIsValidLabel() {
        if (product.isValid()) {
            isValidLabel.setText("Valid Deck");
            isValidLabel.setIcon(new ImageIcon(SpriteHandler.checkmark));
        } else {
            isValidLabel.setText("Invalid Deck");
            isValidLabel.setIcon(new ImageIcon(SpriteHandler.redX));
        }
    }
    
    /**
     * attempts to remove a card from the deck
     * @param c card to remove
     */
    public void removeCard(Card c){
        for(Card card : product.deck){
            if(card.name.equals(c.name)){
                product.deck.remove(card);
                break;
            }
        }
        updateList();
    }
}
