/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Campaign.CampaignManager;
import Cards.Card;
import Multiplayer.Phantom;
import cardgame1.Board;
import cardgame1.Hero;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * First Screen user sees when they open the game
 * Main Menu
 * @author Joseph
 */
public class LandingPage{
    private JFrame core;
    private BackgroundPane panel;
    private JLabel campaignLabel, duelLabel, multiplayerLabel, deckBuilderLabel, optionLabel;
    private JLabel helpText;
    private static Font helpFont = new Font("Comic Sans", Font.BOLD, 16);
    /**
     * constructor
     */
    public LandingPage(){
        initComponents();
        initializeMouseListeners();
    }
    /**
     * sets initial component values
     */
    private void initComponents(){
        core = new JFrame();
        core.setSize(700,700);
        core.setIconImage(SpriteHandler.swords);
        core.setPreferredSize(new Dimension(700,700));
        core.setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new BackgroundPane(SpriteHandler.scroll,0,0);
        panel.setSize(core.getWidth(), core.getHeight());
        panel.setLayout(null); //allows us to place components at indevidual xy coordinates
        
        campaignLabel = new JLabel();
        campaignLabel.setIcon(new ImageIcon(SpriteHandler.CAMPAIGNtext));
        campaignLabel.setSize(500,100);
        campaignLabel.setLocation(200,80);
        panel.add(campaignLabel);
        
        duelLabel = new JLabel();
        duelLabel.setIcon(new ImageIcon(SpriteHandler.DUELtext));
        duelLabel.setSize(500,100);
        duelLabel.setLocation(270,180);
        panel.add(duelLabel);
        
        multiplayerLabel = new JLabel();
        multiplayerLabel.setIcon(new ImageIcon(SpriteHandler.MULTIPLAYERtext));
        multiplayerLabel.setSize(500,100);
        multiplayerLabel.setLocation(180,270);
        panel.add(multiplayerLabel);
        
        deckBuilderLabel = new JLabel();
        deckBuilderLabel.setIcon(new ImageIcon(SpriteHandler.DECKBUILDERtext));
        deckBuilderLabel.setSize(500,100);
        deckBuilderLabel.setLocation(150, 370);
        panel.add(deckBuilderLabel);
        
        optionLabel = new JLabel();
        optionLabel.setIcon(new ImageIcon(SpriteHandler.OPTIONStext));
        optionLabel.setSize(500,100);
        optionLabel.setLocation(220,480);
        panel.add(optionLabel);
        
        helpText = new JLabel();
        helpText.setSize(600,40);
        helpText.setText("");
        helpText.setFont(helpFont);
        helpText.setLocation(80, 570);
        panel.add(helpText);
        
        panel.repaint();
        core.add(panel);
        core.setVisible(true);
        core.requestFocus();
    }

    /**
     * main method for testing purposes
     * @param args
     */
    public static void main(String[] args) {
        Main.setBackgroundImage();
        SpriteHandler.Initialize();
     LandingPage lp = new LandingPage();
    }
    
    private void initializeMouseListeners(){
        campaignLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {
            CampaignManager.init();
            core.dispose();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            helpText.setLocation(80, 570);
            helpText.setText("Choose a hero and fight through 14 levels while accumulating cards");
            }
            @Override
            public void mouseExited(MouseEvent e) {
             helpText.setText("");
            }
        });
        duelLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {
            core.dispose();
            new DuelFrame();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            helpText.setLocation(160, 570);
            helpText.setText("Duel the AI with built-in or your own custom decks!");
            }
            @Override
            public void mouseExited(MouseEvent e) {
            helpText.setText("");
            }
        });
            multiplayerLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {
            core.dispose();
            new MultiplayerFrame();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            helpText.setLocation(80, 570);
            helpText.setText("Peer-to-Peer. Server: make sure port 444 is open, Client: Enter server's IP");
            }
            @Override
            public void mouseExited(MouseEvent e) {
            helpText.setText("");
            }
        });
            deckBuilderLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {
            new DeckBuilder();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                 helpText.setLocation(180, 570);
            helpText.setText("Create your own deck to duel vs AI or Friends");
            }
            @Override
            public void mouseExited(MouseEvent e) {
            helpText.setText("");
            }
        });
        optionLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {
            new SettingsPane();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                 helpText.setLocation(300, 570);
            helpText.setText("Options");
            }
            @Override
            public void mouseExited(MouseEvent e) {
            helpText.setText("");
            }
        });
    }
    
    
    
}
