/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Campaign.campaignGUI;

import Campaign.CampaignManager;
import static Campaign.CampaignManager.level;
import GUI.SettingsPane;
import cardgame1.Board;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Main campaign menu
 * @author Joseph
 */
public class CampaignInterface extends JFrame{
    private CampaignPanel panel;
    private JLabel playerPortrait;
    private JLabel optionsLabel;
    private JLabel levelLabel;
    private JLabel enemyPortrait;
    private JLabel enemyName;
    private JLabel playButton;
    private JLabel playTextLabel;
    private static Font classTitleFont = new Font("Arial",Font.BOLD,26);
     private static Font enemyTitleFont = new Font("Arial",Font.BOLD,18);
     private static Font playButtonFont = new Font("Arial",Font.BOLD,30);
    public CampaignInterface(){
        init();
        CampaignManager.saveGame();
    }
    
    private void init() {
        if(panel!=null)panel.removeAll();
        this.setSize(700, 700);
        this.setPreferredSize(new Dimension(700,700));
        this.setIconImage(SpriteHandler.swordsSmall);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new CampaignPanel(Main.BackgroundImage);
        panel.setSize(this.getSize());
        panel.setLayout(null);
        this.add(panel);
        
        playerPortrait = new JLabel();
        playerPortrait.setSize(300,200);
        playerPortrait.setLocation(0, 500);
        playerPortrait.setIcon(new ImageIcon(CampaignManager.playerClass.getHeroPortrait()));
        panel.add(playerPortrait);
        
        optionsLabel = new JLabel();
        optionsLabel.setSize(40,40);
        optionsLabel.setIcon(new ImageIcon(SpriteHandler.gearSmall));
        optionsLabel.setLocation(0, 0);
        optionsLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {new SettingsPane();}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        panel.add(optionsLabel);
        
        enemyPortrait = new JLabel();
        enemyPortrait.setSize(300, 80);
        enemyPortrait.setLocation(0, 0);
        enemyPortrait.setIcon(new ImageIcon(CampaignManager.getEnemyClassForLevel(level).getHeroPortrait().getSubimage(0, 60, 300, 85)));
        if(level<=CampaignManager.NUM_LEVELS)panel.add(enemyPortrait);
        
        enemyName = new JLabel();
        enemyName.setSize(new Dimension(300,35));
        enemyName.setText("Next Opponent: " + CampaignManager.getEnemyClassForLevel(level));
        enemyName.setLocation(0,80);
        enemyName.setFont(enemyTitleFont);
         if(level<=CampaignManager.NUM_LEVELS)panel.add(enemyName);
        
        levelLabel = new JLabel();
        levelLabel.setSize(150,40);
        levelLabel.setLocation(200, 460);
        levelLabel.setText("Level: " + CampaignManager.level);
        levelLabel.setFont(classTitleFont);
        panel.add(levelLabel);
        

        
        playButton = new JLabel();
        playButton.setSize(300,300);
        playButton.setLocation(50, 150);
        playButton.setIcon(new ImageIcon(SpriteHandler.NEXTLEVELtext));
        panel.add(playButton);
        playButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               dispose();
               new Board(CampaignManager.level);
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) {  }
        });
        
        this.setVisible(true);
    }

   

}
