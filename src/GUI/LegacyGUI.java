/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Cards.Base.SpearmanCard;
import Cards.Base.*;
import Cards.Card;
import Cards.Dragon.*;
import Cards.Empire.*;
import Cards.Fish.*;
import Cards.Undead.*;
import CustomDecks.CustomDeck;
import Multiplayer.Phantom;
import cardgame1.Board;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Joseph
 */
public class LegacyGUI extends javax.swing.JFrame {
    private static String useCustomText = "Load Custom Deck...";
    /*    FIELDS      */
    private ArrayList<Card> AIDeck;
    private ArrayList<Card> PlayerDeck;
    private CustomDeck loadedCustomDeckPlayer;
    private CustomDeck loadedCustomDeckAI;
    public static SettingsPane settings = null;
    private BufferedImage enemyHeroPortrait = SpriteHandler.ashePortrait; //image the enemy hero will have
    
    public void setLoadedCustomDeckPlayer(CustomDeck cd){
        loadedCustomDeckPlayer = cd;
        if(cd!=null){
        customDeckLabel.setForeground(Color.red);
        customDeckLabel.setText("Loaded: "+cd.deckName);
        }
    }
    public void setLoadedCustomDeckAI(CustomDeck cd){
        loadedCustomDeckAI = cd;
    }
    
    /**
     * Creates new form OpeningGUI
     */
    public LegacyGUI() {
        initComponents();
        populateCombo();
        this.setVisible(true);
    }
    
    private void populateCombo(){
        this.yourDeckCombo.addItem("Base Deck");
        this.yourDeckCombo.addItem("Feeding Frenzy");
        this.yourDeckCombo.addItem("Deep Sea");
        this.yourDeckCombo.addItem("Undead");
        this.yourDeckCombo.addItem("Dragon");
        this.yourDeckCombo.addItem("Empire");
        this.yourDeckCombo.addItem("Load Custom Deck...");
                
        this.AIDeckCombo.addItem("Base Deck");
        this.AIDeckCombo.addItem("Feeding Frenzy");
        this.AIDeckCombo.addItem("Deep Sea");
        this.AIDeckCombo.addItem("Undead");
        this.AIDeckCombo.addItem("Dragon");
        this.AIDeckCombo.addItem("Empire");       
        repaint();
    }

    private ArrayList<Card> getBaseDeck(){
        //Base
        ArrayList<Card> deck = new ArrayList<>();
        for(int i = 0; i < 3; i++){
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
        return deck;
    }

        private ArrayList<Card> getFishDeck(){
        //Base
        ArrayList<Card> deck = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            deck.add(new BaitfishCard());
            deck.add(new CarnifishCard());
            deck.add(new PirranahCard());
            deck.add(new PredationCard());
            deck.add(new FrenzyCard());
            deck.add(new ThrasherCard());
        }
        return deck;
    }
        
     private ArrayList<Card> getDeepSeaDeck() {
        ArrayList<Card> deck = new ArrayList<>();
        for (int i = 0; i < 3; i++) {  
            deck.add(new JellyfishCard()); 
            deck.add(new SeaWitchCard());
            deck.add(new PirranahCard());
            deck.add(new SeaSerpentTrapCard());
            deck.add(new ThrasherCard());
        }
        for(int i = 0; i < 2; i++) {
            deck.add(new SwollowCard()); 
            deck.add(new PredationCard());
            deck.add(new VolcanoCard());
            //deck.add(new KelpieCard());
         }
            deck.add(new SpellBookCard());
        return deck;
        }
     
    private ArrayList<Card> getUndeadDeck() {
        ArrayList<Card> deck = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            deck.add(new ArcherCard());
            deck.add(new KnightCard());
        }
        for(int i = 0; i < 4; i++){
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
        return deck;
    }
     private ArrayList<Card> getEmpireDeck() {
        ArrayList<Card> deck = new ArrayList<>();
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
        return deck;
    }

    private ArrayList<Card> getDragonDeck() {
        ArrayList<Card> deck = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            deck.add(new GrayDrakeCard());
            deck.add(new VolcanicDrakeCard());
            deck.add(new FrostDragonCard());
            deck.add(new ArcherCard());
            deck.add(new VolcanoCard());
            deck.add(new PaladinCard());
        }
        for(int i = 0; i < 2; i++){
            deck.add(new DragonSoulTrapCard());
            deck.add(new DragonBreathCard());
        }
        deck.add(new SpearmanCard());
        deck.add(new UndyingSoldierCard());
        
        return deck;
    }
        /**
     * sets the decks based on combo boxes
     */
    private void assignDecks() {
        switch (this.AIDeckCombo.getSelectedIndex()) {
            case 0: //base
                AIDeck = this.getBaseDeck();
                enemyHeroPortrait = SpriteHandler.knightHero;
                break;
            case 1: //fish
                AIDeck = this.getFishDeck();
                enemyHeroPortrait = SpriteHandler.alligatorHero;
                break;
            case 2: //deep sea
                AIDeck = this.getDeepSeaDeck();
                enemyHeroPortrait = SpriteHandler.fishManHero;
                break;
            case 3: //undead
                AIDeck = this.getUndeadDeck();
                enemyHeroPortrait = SpriteHandler.undeadHero;
                break;
            case 4: //dragon
                AIDeck = this.getDragonDeck();
                enemyHeroPortrait = SpriteHandler.dragonHero;
                break;
            case 5: //empire
                AIDeck = this.getEmpireDeck();
                enemyHeroPortrait = SpriteHandler.empireHero;
                break;
        }
        if(yourDeckCombo.getSelectedItem().equals(useCustomText)){
            if(loadedCustomDeckPlayer==null){
                return;
            }
            PlayerDeck = loadedCustomDeckPlayer.deck;
            return;
        }
        switch (this.yourDeckCombo.getSelectedIndex()) {
            case 0: //base
                PlayerDeck = this.getBaseDeck();
                break;
            case 1: //fish
                PlayerDeck = this.getFishDeck();
                break;
            case 2: //deep sea
                PlayerDeck = this.getDeepSeaDeck();
                break;
            case 3: //undead
                PlayerDeck = this.getUndeadDeck();
                break;
            case 4://dragon
                PlayerDeck = this.getDragonDeck();
                break;
            case 5: //empire
                PlayerDeck = this.getEmpireDeck();
                break;
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        resX = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        resY = new javax.swing.JTextField();
        button1080 = new javax.swing.JButton();
        button720 = new javax.swing.JButton();
        settingsButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        yourDeckLabel = new javax.swing.JLabel();
        opponentDeckLabel = new javax.swing.JLabel();
        yourDeckCombo = new javax.swing.JComboBox();
        AIDeckCombo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        customDeckLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        mpTestingButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titleLabel.setText("CARDGAME ALPHA ");

        resX.setText("1920");
        resX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resXActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Resolution:");

        jLabel2.setText("x");

        resY.setText("1080");
        resY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resYActionPerformed(evt);
            }
        });

        button1080.setText("1080p");
        button1080.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1080ActionPerformed(evt);
            }
        });

        button720.setText("720p");
        button720.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button720ActionPerformed(evt);
            }
        });

        settingsButton.setText("Settings...");
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });

        playButton.setText("Play!");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        yourDeckLabel.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        yourDeckLabel.setText("Your Deck");

        opponentDeckLabel.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        opponentDeckLabel.setText("AI Deck");

        yourDeckCombo.setToolTipText("");
        yourDeckCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yourDeckComboActionPerformed(evt);
            }
        });

        jLabel3.setText("Note: Spacebar = End Turn");

        jButton1.setText("Deck Builder...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        mpTestingButton.setText("Multiplayer testing");
        mpTestingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mpTestingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(resX, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resY, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(button1080)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button720))
                            .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(yourDeckLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(268, 268, 268)))
                .addGap(76, 76, 76))
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(yourDeckCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(opponentDeckLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(AIDeckCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(customDeckLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(mpTestingButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(settingsButton)
                        .addGap(115, 115, 115)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(playButton)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(resY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1080)
                    .addComponent(button720))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yourDeckLabel)
                    .addComponent(opponentDeckLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yourDeckCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AIDeckCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(customDeckLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(mpTestingButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(settingsButton)
                    .addComponent(playButton)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resXActionPerformed

    private void resYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resYActionPerformed

    private void button1080ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1080ActionPerformed
        this.resX.setText("1920");
        this.resY.setText("1080");
    }//GEN-LAST:event_button1080ActionPerformed

    private void button720ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button720ActionPerformed
        this.resX.setText("1280");
        this.resY.setText("720");
    }//GEN-LAST:event_button720ActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        if(this.yourDeckCombo.getSelectedItem().equals(useCustomText)){
            if(this.loadedCustomDeckPlayer==null)return;
        }
        if(this.AIDeckCombo.getSelectedItem().equals(useCustomText)){
            if(this.loadedCustomDeckAI==null)return;
        }
        this.assignDecks();
        int x = 0, y =0;
        try{
            x = Integer.parseInt(this.resX.getText());
            y = Integer.parseInt(this.resY.getText());
        }catch(NumberFormatException nfe){
            Main.display("Invalid dimensions. Must be numeric.");
            return;
        }catch(Exception e){
            e.printStackTrace();
        }
        Hero enemy = new Hero("AI Hero", AIDeck, enemyHeroPortrait);
        enemy.setAIControlled(true);
        Hero player = new Hero("Player Hero", PlayerDeck, SpriteHandler.ashePortrait);
        if(x < 480 || y < 480){
            Main.display("Warning, window too small");
            return;
        }
        if(x > 2560 || y > 2560){
            Main.display("Warning, window too big");
            return;
        }
        Board b = new Board(enemy, player, new Dimension(x,y));
        this.dispose();
    }//GEN-LAST:event_playButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        if(settings!=null)settings.dispose();
        settings = new SettingsPane();
    }//GEN-LAST:event_settingsButtonActionPerformed

    private void yourDeckComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yourDeckComboActionPerformed
        if(yourDeckCombo.getSelectedItem().equals(useCustomText)){
            DeckLoaderScratch dls = new DeckLoaderScratch(this);
            this.setEnabled(false);
        }
    }//GEN-LAST:event_yourDeckComboActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(DeckBuilder.mainBuilder == null){
            new DeckBuilder();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void mpTestingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mpTestingButtonActionPerformed
        if (this.yourDeckCombo.getSelectedItem().equals(useCustomText)) {
            if (this.loadedCustomDeckPlayer == null) {
                JOptionPane.showMessageDialog(null, "Custom deck not loaded");
                return;
            }
        }
        int x= 0, y = 0;
        try {
            x = Integer.parseInt(this.resX.getText());
            y = Integer.parseInt(this.resY.getText());
            if (x < 480 || y < 480) {
                Main.display("Warning, window too small");
                return;
            }
            if (x > 2560 || y > 2560) {
                Main.display("Warning, window too big");
                return;
            }
        } catch (NumberFormatException nfe) {
            Main.display("Invalid dimensions. Must be numeric.");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] options = {"Client", "Server"};
        int choice = JOptionPane.showOptionDialog(null, "Use as Client or Server?", "Client/Server Prompt", 0, 0, null, options, "init");
        //-1 = exit, 0 = client, 1= server
        if(choice == -1)return;
        boolean isServer = choice==1;
        this.assignDecks();
        Hero bot = new Hero("user", PlayerDeck, SpriteHandler.ashePortrait);
        Hero top = new Hero("top", new ArrayList<Card>(), SpriteHandler.knightHero); //emptyDeck because phantom will populate it
        if(!isServer)Phantom.connectionAddress = JOptionPane.showInputDialog("Enter Connection Address");
        if(Phantom.connectionAddress==null)return;
        top.isAIControlled=false;
        Board board;
        if(isServer)JOptionPane.showMessageDialog(null, "Game will now wait 25 seconds for another player connects");
        try {
             board = new Board(top, bot, new Dimension(x, y), isServer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_mpTestingButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox AIDeckCombo;
    private javax.swing.JButton button1080;
    private javax.swing.JButton button720;
    private javax.swing.JLabel customDeckLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton mpTestingButton;
    private javax.swing.JLabel opponentDeckLabel;
    private javax.swing.JButton playButton;
    private javax.swing.JTextField resX;
    private javax.swing.JTextField resY;
    private javax.swing.JButton settingsButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JComboBox yourDeckCombo;
    private javax.swing.JLabel yourDeckLabel;
    // End of variables declaration//GEN-END:variables
}
