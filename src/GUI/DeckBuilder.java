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
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    public ArrayList<JLabel> cardLabels = new ArrayList<>(); //holds list of cards currently in deck
    private JLabel titleLabel;
    private JLabel classTitle;
    private JComboBox classCombo; //select deck class with this
    private JLabel classLabel; //displays current class of deck
    private JLabel isValidLabel;
    private JButton saveButton;
    private JButton loadButton;
    private JButton clearButton;
    private JLabel backButton;
    public CustomDeck product = new CustomDeck("Unnamed", new ArrayList<Card>(), HeroClass.Neutral); //deck we are building
    public String loadedDeckName = null; //name of the deck we loaded via the deck loader, if applicable
    private static Font titleFont = new Font("Times", Font.BOLD, 35);
    private static Font classTitleFont = new Font("Arial",Font.PLAIN,20);
    public static DeckBuilder mainBuilder;
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
        mainBuilder = this;
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
        classCombo.addItem(HeroClass.Dragon);
        classCombo.addItem(HeroClass.Empire);
        classCombo.addItem(HeroClass.Elemental);
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
        isValidLabel.setLocation(770,580);
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
        
        backButton = new JLabel();
        backButton.setSize(70,70);
        backButton.setIcon(new ImageIcon(SpriteHandler.backArrow));
        backButton.setLocation(0, 600);
        panel.add(backButton);
        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            dispose();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) { }
        });
        
        loadButton = new JButton();
        loadButton.setSize(100,50);
        loadButton.setLocation(510,600);
        loadButton.setText("Load...");
        loadButton.setFont(classTitleFont);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainBuilder.setEnabled(false);
                DeckLoaderScratch dl = new DeckLoaderScratch(mainBuilder); 
            }
        });
        panel.add(loadButton);
        
        clearButton = new JButton();
        clearButton.setSize(100,50);
        clearButton.setLocation(410,600);
        clearButton.setText("Clear");
        clearButton.setFont(classTitleFont);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                product.deck.clear();
                mainBuilder.updateList();
            }
        });
        panel.add(clearButton);
        
        saveButton = new JButton();
        saveButton.setSize(100,50);
        saveButton.setText("Save...");
        saveButton.setFont(classTitleFont);
        saveButton.setLocation(610,600);
        panel.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DeckBuilder.mainBuilder.loadedDeckName != null) {
                    String[] options = {"Save Over " + product.deckName, "Save as..."};
                    int choice = JOptionPane.showOptionDialog(null, "Overwrite existing deck: " + product.deckName + "?", "Overwrite Prompt", 0, 0, null, options, "init");
                    System.out.println(choice);
                    //-1 = exit, 0 = overwrite, 1= save as
                    if(choice == -1)return;
                    if(choice == 0){
                        product.export();
                        return;
                    }
                    //if choice == 1, continue to next stage for saving
                }              
                String givenName = JOptionPane.showInputDialog("Save as...");
                if(givenName==null || givenName.trim().equals("")){
                    JOptionPane.showMessageDialog(null, "No name detected");
                    return;   
                }
                if(!isStringSafe(givenName)){
                    JOptionPane.showMessageDialog(null, "Name cannot contain the following characters:\n ~ # % * { } [ ] < > ? /\\ + |");
                    return;
                }
                product.deckName = givenName;
                File f = new File("Decks/"+givenName+".deck");
                if(f.exists()){
                    int shouldOverwrite = JOptionPane.showConfirmDialog(null, "Deck already exists. Overwrite?");
                    if(shouldOverwrite != 0) return; //user doesnt want to overwrite
                }
                product.export();
                JOptionPane.showMessageDialog(null, "Deck Saved!\n" + f.getAbsolutePath());
            }
        });
        
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
       if(Card.getCardList().size()%3 != 0) row++; //makes sure that it expands to account for incomeplete rows
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
    void updateList(){
        for(JLabel cl : cardLabels){
            panel.remove(cl);
        }
        cardLabels.clear();
        product.deck.sort(new CardOrderComparator()); //sorts based on cost then name
        int i = 0;
        for(Card c : this.product.deck){
            CardLabel cl = new CardLabel(c);
            cardLabels.add(cl);
            cl.setSize(300, 22);
            cl.setLocation(800, 40 + 22 * i);
            panel.add(cl);  
            i++;
        }
        JLabel numCards = new JLabel();
        numCards.setText(product.deck.size()+"/"+CustomDeck.MIN_NUM_CARDS);
        numCards.setSize(300,22);
        numCards.setLocation(800,40+22*i);
        cardLabels.add(numCards);
        panel.add(numCards);
       classLabel.setIcon(new ImageIcon(product.deckClass.getClassIcon()));
       updateIsValidLabel();
       this.panel.repaint();
       setVisible(true);
    }
    
    /**
     * reloads the list of cards with only neutral and selected class cards
     */
    private void updateScrollPane(){
        scroll.remove(interior);
        interior = new JPanel();
        interior.setLocation(0,0);
        interior.setSize(700,1000);
        interior.setPreferredSize(interior.getSize());
        interior.setBackground(Color.gray);
        
        int column = 0;
        int row = 0;
        for(Card c : Card.getCardList()){
            if(product.deckClass!=HeroClass.Neutral && product.deckClass != c.heroClass && c.heroClass!=HeroClass.Neutral)continue;
            CardIcon ci = new CardIcon(c, this);
            ci.setLocation(10 + column*(c.sprite.getWidth() + 30), row*(c.sprite.getHeight() + 20));
            column++;
            if(column > 2){
            row++;
            column = 0;
            }
            interior.add(ci);
        }
       row++; //makes sure that it expands to account for incomeplete rows
       interior.setPreferredSize(new Dimension(700, row*(300+20)));        
        
        scroll.add(interior);
        scroll.setViewportView(interior);
        interior.setLayout(null);
    }
    
    /**
     * updates deck class using combo
     */
    private void updateDeckClass(){
        HeroClass hc;
        hc = (HeroClass)classCombo.getItemAt(classCombo.getSelectedIndex());
        product.deckClass = hc;
        classLabel.setIcon(new ImageIcon(product.deckClass.getClassIcon()));
        updateScrollPane();
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
    /**
     * returns true if the string does not contain illegal characters for windows file names
     * @param input
     * @return 
     */
    private boolean isStringSafe(String input){
        if(input.contains("/"))return false;
        if(input.contains("\\"))return false;
        if(input.contains("?"))return false;
        if(input.contains(">"))return false;
        if(input.contains("<"))return false;
        if(input.contains("%"))return false;
        if(input.contains("~"))return false;
        if(input.contains("&"))return false;
        if(input.contains("*"))return false;
        if(input.contains("{"))return false;
        if(input.contains("}"))return false;
        if(input.contains("["))return false;
        if(input.contains("]"))return false;
        if(input.contains(":"))return false;
        if(input.contains("+"))return false;
        if(input.contains("|"))return false;
        if(input.contains("\""))return false;
        return true;
    }
    
    public void loadDeck(CustomDeck cd){
        if(cd ==null) return;
        product = cd;
        this.updateIsValidLabel();
        this.updateList();
        product.deckClass = cd.deckClass;
        classCombo.setSelectedItem(cd.deckClass);
        this.loadedDeckName = cd.deckName;
    }
    
    @Override
    public void dispose(){
        super.dispose();
        mainBuilder = null;
    }
    
    /**
     * compares cards first based on cost, then on name
     */
    private class CardOrderComparator implements Comparator<Card> {

        @Override
        public int compare(Card o1, Card o2) {
            if (o1.cost == o2.cost) {
                return o1.name.compareTo(o2.name); //if same cost then use alphabetical order
            } else {
                if (o1.cost > o2.cost) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

    }
}
