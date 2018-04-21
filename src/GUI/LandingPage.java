/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import cardgame1.Main;
import cardgame1.SpriteHandler;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;

/**
 * First Screen user sees when they open the game
 * Main Menu
 * @author Joseph
 */
public class LandingPage{
    private JFrame core;
    private BackgroundPane panel;
    private JLabel campaignLabel, duelLabel, multiplayerLabel, deckBuilderLabel, optionLabel;
    private static Font titleFont = new Font("Comic Sans", Font.BOLD, 35);
    /**
     * constructor
     */
    public LandingPage(){
        initComponents();

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
}
