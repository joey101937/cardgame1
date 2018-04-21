/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Cards.Card;
import CustomDecks.CorruptFileException;
import CustomDecks.CustomDeck;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Interface user uses to load saved decks
 * @author Joseph
 */
public class DeckLoaderScratch {
    /*   FIELDS    */
    private ArrayList<File> savedDecks = new ArrayList<>();
    public CustomDeck chosenDeck;
    public static Card mousedOver = null;
    private coreJFrame core;
    private BackgroundPane panel;
    private JLabel titleLabel;
    private JLabel directoryLabel;
    private JLabel deckTitleLabel;
    private JLabel isValidLabel;
    private JComboBox combo;
    private JButton loadButton;
    private CardPreviewPanel cardDisplay;
    public static Font titleFont = new Font("Times", Font.ITALIC, 35);
    public static Font detailFont = new Font("Courier", Font.ITALIC,12);
    public static Font deckTitleFont = new Font("Arial",Font.PLAIN,20);
    public static Font cardTitleFont = new Font("Arial",Font.PLAIN,20);
    private ArrayList<JLabel> cardLabels = new ArrayList<>();
    private LegacyGUI maker;
    private DuelFrame duelFrame;
    private DeckBuilder builder;
    private MultiplayerFrame mpf;
    /**
     * constructor
     */
    public DeckLoaderScratch(){
       SpriteHandler.Initialize();
       setupInitialComponents();
    }

    DeckLoaderScratch(LegacyGUI leg) {
       maker = leg;
       SpriteHandler.Initialize();
       setupInitialComponents();
    }
    
    DeckLoaderScratch(DuelFrame df){
        duelFrame = df;
        SpriteHandler.Initialize();
        setupInitialComponents();
    }
    
    DeckLoaderScratch(MultiplayerFrame mp) {
        mpf = mp;
        SpriteHandler.Initialize();
        setupInitialComponents();
    }
    
    DeckLoaderScratch(DeckBuilder builder){
        this.builder = builder;
        SpriteHandler.Initialize();
        setupInitialComponents();
    }
    /**
     * initializes ui components
     */
    private void setupInitialComponents(){
        core = new coreJFrame(this, maker, builder, duelFrame, mpf);
        core.setSize(625, 720);
        core.setIconImage(SpriteHandler.swords);
        core.setPreferredSize(new Dimension(600,720));
       
        if(maker==null && builder==null && duelFrame == null && mpf ==null)core.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        else{
            System.out.println("dispose on close");
            core.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        
        panel = new BackgroundPane(Main.BackgroundImage);
        panel.setSize(core.getWidth(), core.getHeight());   
        panel.setLayout(null); //allows us to place components at indevidual xy coordinates
        
        titleLabel = new JLabel();
        titleLabel.setText("Select Custom Deck to Use");
        titleLabel.setSize(600,50);
        titleLabel.setFont(titleFont);
        titleLabel.setLocation(90,0);
        panel.add(titleLabel);
        
        directoryLabel = new JLabel();
        directoryLabel.setText("Loading From:  " + System.getProperty("user.dir") + File.separator + "Decks" + File.separator); //sets the text to where we will be looking for decks
        directoryLabel.setLocation(30, 50);
        directoryLabel.setSize(600,20);
        panel.add(directoryLabel);
     
        cardDisplay = new CardPreviewPanel(mousedOver);
        cardDisplay.setSize(200, 300);
        cardDisplay.setLocation(400, 250);
        panel.add(cardDisplay);

        
        isValidLabel = new JLabel();
        isValidLabel.setSize(200, 100);
        isValidLabel.setLocation(400,600);
        isValidLabel.setFont(cardTitleFont);
        panel.add(isValidLabel);
        
         isValidLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(chosenDeck == null) return;
                if(chosenDeck.isValid()){
                    maker.setLoadedCustomDeckPlayer(chosenDeck);
                    maker.setEnabled(true);
                    CardDisplay.close();
                    core.dispose();
                    return;
                }
                String toDisplay = "";
                for(String s : chosenDeck.diagnose()){
                    toDisplay += (s+"\n");
                }
                JOptionPane.showMessageDialog(null,toDisplay);
            }
            @Override
             public void mousePressed(MouseEvent e) {
                 if (chosenDeck == null)return;
                 if(chosenDeck.isValid() && maker!=null){
                    maker.setLoadedCustomDeckPlayer(chosenDeck);
                    maker.setEnabled(true);
                     return; 
                 }
                 if(chosenDeck.isValid() && duelFrame != null) {
                    System.out.println("setting chosen deck for player");
                    duelFrame.setPlayerDeck(chosenDeck.deck, chosenDeck.deckClass);
                    duelFrame.loadedCustom=chosenDeck;
                    duelFrame.setEnabled(true);
                    duelFrame.updatePortrait();
                    core.dispose();
                    return;
                }
                 if(chosenDeck.isValid() && mpf!=null){
                     System.out.println("setting chosen deck for mp");
                     mpf.setPlayerDeck(chosenDeck.deck, chosenDeck.deckClass);
                     mpf.loadedCustom=chosenDeck;
                     mpf.setEnabled(true);
                     mpf.updatePortrait();
                     core.dispose();
                     return;
                 }
                 if(builder!=null){
                     if(!chosenDeck.isValid())JOptionPane.showMessageDialog(null, "Notice: Loading invalid Deck");
                     builder.setEnabled(true);
                     core.dispose();
                     return;
                 }
                 String toDisplay = "";
                 for (String s : chosenDeck.diagnose()) {
                     toDisplay += (s + "\n");
                 }
                 JOptionPane.showMessageDialog(null, toDisplay);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        
        
        deckTitleLabel = new JLabel();
        deckTitleLabel.setText("");
        deckTitleLabel.setSize(600, 45);
        deckTitleLabel.setLocation(20, 150);
        deckTitleLabel.setFont(titleFont);
        panel.add(deckTitleLabel);
        
        combo = new JComboBox();
        combo.setSize(400,40);
        combo.setLocation(20, 90);
        combo.addItem("No Decks Found");
        panel.add(combo);
        
        loadButton = new JButton();
        loadButton.setSize(100, 40);
        loadButton.setText("Load");
        loadButton.setLocation(450, 90);
        panel.add(loadButton);
        
            loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });
        panel.repaint();
        core.add(panel);
        core.setVisible(true);
        core.requestFocus();
        populateCombo();
    }
    /**
     * runs when the load button is pressed
     * @param evt 
     */
    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (savedDecks.isEmpty()) {
            return;
        }
        loadSelected();
    }
    
    
   /**
    * populates the combo box with a list of saved decks
    */
    private void populateCombo(){
        boolean filesFound = false;
        File dir = new File(System.getProperty("user.dir") + File.separator + "Decks" + File.separator); //where we store the dataset files
        if(dir.exists()) System.out.println("found dir");
        else dir.mkdir();
        ArrayList<File> deckFiles = new ArrayList<>();
        for(File f : dir.listFiles()){
            if(!f.getName().endsWith(".deck"))continue; //skip it if it isnt a .ds
            filesFound = true;
            deckFiles.add(f);
        }
        if(filesFound)combo.removeAllItems(); //if we have files to display, get rid of that NONE message
        for(File f : deckFiles){
            combo.addItem(f.getName());
            savedDecks.add(f);
        }
    }
    /**
     * updates the card preview section based on what the used is currently mousing over
     */
    public void updatePreview() {

        panel.remove(cardDisplay);
        cardDisplay = new CardPreviewPanel(mousedOver);
        cardDisplay.setSize(200, 300);
        cardDisplay.setLocation(400, 250);
        panel.add(cardDisplay);
        panel.repaint();
         
    }
/**
 * Loads the deck corresponding to what the combobox has selected.
 */
    private void loadSelected() {
        try {
            for (JLabel jl : cardLabels) {
                panel.remove(jl);
            }
            cardLabels.clear();
            chosenDeck = new CustomDeck(savedDecks.get(combo.getSelectedIndex()));
            deckTitleLabel.setText(chosenDeck.deckName);
            deckTitleLabel.setIcon(new ImageIcon(chosenDeck.deckClass.getHeroPortraitPath()));
            int row = 0; 
            int column = 0;
            for (Card c : chosenDeck.deck) {
                JLabel cardLabel = new CardLabel(c,this);
                cardLabel.setSize(200, 22);
                cardLabel.setLocation(20 + (200*column), 200 + 22 * row);
                panel.add(cardLabel);
                cardLabels.add(cardLabel);
                row++;
                if(row>15){
                row = 0;
                column++;
                }
            }
            if (!chosenDeck.isValid()) {
                System.out.println(chosenDeck.diagnose());
                isValidLabel.setText("Illegal Deck");
                isValidLabel.setIcon(new ImageIcon(Main.assets+"redXsmall.png"));
            }else{
                isValidLabel.setIcon(new ImageIcon(Main.assets+"checkmarkSmall.png"));
                isValidLabel.setText("Confirm");
            }
            panel.repaint();
            core.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "IO Error");
            ex.printStackTrace();
        } catch (CorruptFileException ex) {
            JOptionPane.showMessageDialog(null, "Deck could not be loaded without error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    
    public static void main(String[] args) {
        Main.setBackgroundImage();
        SpriteHandler.Initialize();
        new DeckLoaderScratch();
        
    }
}
