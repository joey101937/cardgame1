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
    private JLabel titleLabel;
    private JLabel playLabel;
    private JLabel editorLabel;
    private JLabel optionsLabel;
    private JLabel exitLabel;
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
        core.setSize(1000,700);
        core.setIconImage(SpriteHandler.swords);
        core.setPreferredSize(new Dimension(1000,700));
        core.setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new BackgroundPane(Main.BackgroundImage);
        panel.setSize(core.getWidth(), core.getHeight());
        panel.setLayout(null); //allows us to place components at indevidual xy coordinates
        
        titleLabel = new JLabel();
        titleLabel.setFont(titleFont);
        titleLabel.setText("Main Menu");
        titleLabel.setSize(600,100);
        titleLabel.setLocation(380, 20);
        panel.add(titleLabel);
        
        playLabel = new JLabel();
        playLabel.setFont(titleFont);
        playLabel.setSize(300,60);
        playLabel.setText("Play");
        playLabel.setLocation(380, 100);
        panel.add(playLabel);
        
        
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
