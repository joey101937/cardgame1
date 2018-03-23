/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomDecks;

import Cards.Card;
import cardgame1.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Interface user uses to load saved decks
 * @author Joseph
 */
public class DeckLoaderScratch {
    /*   FIELDS    */
    private ArrayList<File> savedDecks = new ArrayList<>();
    public CustomDeck chosenDeck;
    private JFrame core;
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel directoryLabel;
    private JLabel deckTitleLabel;
    private JLabel isValidLabel;
    private JComboBox combo;
    private JButton loadButton;
    private static Font titleFont = new Font("Times", Font.ITALIC, 35);
    private static Font detailFont = new Font("Courier", Font.ITALIC,12);
    private static Font deckTitleFont = new Font("Arial",Font.PLAIN,20);
    private static Font cardTitleFont = new Font("Arial",Font.PLAIN,20);
    private ArrayList<JLabel> cardLabels = new ArrayList<>();
    /**
     * constructor
     */
    public DeckLoaderScratch(){
       setupInitialComponents();
    }
    
    private void setupInitialComponents(){
        this.core = new JFrame();
        core.setSize(600, 600);
        core.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        panel.setSize(600, 600);
        panel.setBackground(Color.white);   
        panel.setLayout(null); //allows us to place components at indevidual xy coordinates
        
        titleLabel = new JLabel();
        titleLabel.setText("Select Deck to Use");
        titleLabel.setSize(400,50);
        titleLabel.setFont(titleFont);
        titleLabel.setLocation(120,0);
        panel.add(titleLabel);
        
        directoryLabel = new JLabel();
        directoryLabel.setText("Loading From:  " + System.getProperty("user.dir") + File.separator + "Decks" + File.separator); //sets the text to where we will be looking for decks
        directoryLabel.setLocation(30, 50);
        directoryLabel.setSize(600,20);
        panel.add(directoryLabel);
        
        isValidLabel = new JLabel();
        isValidLabel.setSize(200, 100);
        isValidLabel.setLocation(400,400);
        isValidLabel.setFont(cardTitleFont);
       // isValidLabel.setText("Illegal Deck");
       //isValidLabel.setIcon(new ImageIcon(Main.assets+"redXsmall.png"));
        panel.add(isValidLabel);
         isValidLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(chosenDeck == null || chosenDeck.isValid()) return;
                String toDisplay = "";
                for(String s : chosenDeck.diagnose()){
                    toDisplay += (s+"\n");
                }
                JOptionPane.showMessageDialog(null,toDisplay);
            }
            @Override
            public void mousePressed(MouseEvent e) {
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
        deckTitleLabel.setText("<title>");
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

        core.add(panel);
        core.setVisible(true);
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

    private void loadSelected() {
        try {
            for (JLabel jl : cardLabels) {
                panel.remove(jl);
            }
            cardLabels.clear();
            chosenDeck = new CustomDeck(savedDecks.get(combo.getSelectedIndex()));
            deckTitleLabel.setText(chosenDeck.deckName);
            deckTitleLabel.setIcon(new ImageIcon(chosenDeck.deckClass.getClassIconPath()));
            int i = 0;  
            for (Card c : chosenDeck.deck) {
                JLabel cardLabel = new JLabel();
                cardLabel.setText(c.name);
                cardLabel.setFont(cardTitleFont);
                cardLabel.setSize(300, 25);
                cardLabel.setLocation(20, 200 + 25 * i);
                panel.add(cardLabel);
                cardLabels.add(cardLabel);
                i++;
            }
            if (!chosenDeck.isValid()) {
                System.out.println(chosenDeck.diagnose());
                isValidLabel.setText("Illegal Deck");
                isValidLabel.setIcon(new ImageIcon(Main.assets+"redXsmall.png"));
            }else{
                isValidLabel.setText("");
                isValidLabel.setIcon(null);
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
        new DeckLoaderScratch();
    }
}
