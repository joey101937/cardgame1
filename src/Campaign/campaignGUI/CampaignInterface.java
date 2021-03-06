/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Campaign.campaignGUI;

import Campaign.CampaignManager;
import static Campaign.CampaignManager.level;
import GUI.LandingPage;
import GUI.SettingsPane;
import cardgame1.Board;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
    private JLabel backButton;
    private static Font classTitleFont = new Font("Arial",Font.BOLD,26);
     private static Font enemyTitleFont = new Font("Arial",Font.BOLD,18);
     private static Font playButtonFont = new Font("Arial",Font.BOLD,30);
    public CampaignInterface(){
        init();
        CampaignManager.saveGame();
        repaint();
    }
    
    private void init() {
        System.out.println("setting location to " + LandingPage.metaX + " " + LandingPage.metaY);
        this.setLocation(LandingPage.metaX, LandingPage.metaY);
        
        if(panel!=null)panel.removeAll();
        this.setSize(700, 700);
        this.setPreferredSize(new Dimension(700,700));
        this.setIconImage(SpriteHandler.swordsSmall);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new CampaignPanel(Main.BackgroundImage);
        panel.setSize(this.getSize());
        panel.setLayout(null);
        this.add(panel);
        
        backButton = new JLabel();
        backButton.setSize(70, 70);
        backButton.setLocation(0, 590);
        backButton.setIcon(new ImageIcon(SpriteHandler.backArrow));
        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            dispose();
            new LandingPage();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        panel.add(backButton);

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
        levelLabel.setLocation(450, 600);
        levelLabel.setForeground(Color.black);
        levelLabel.setText("Level: " + CampaignManager.level);
        levelLabel.setFont(classTitleFont);
        panel.add(levelLabel);
        
        playButton = new JLabel();
        if(level<=CampaignManager.NUM_LEVELS){
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
                public void mouseExited(MouseEvent e) {
                }
            });
        } else {
            playButton.setSize(300, 300);
            playButton.setLocation(50, 150);
            playButton.setIcon(new ImageIcon(SpriteHandler.NEXTLEVELtext));
            panel.add(playButton);
            playButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(null, "You have beaten the " + CampaignManager.playerClass + " campaign! \n Choose \"New Game\" to play again as another hero!");
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
                public void mouseExited(MouseEvent e) {
                }
            });
            JButton restartButton = new JButton();
            restartButton.setLocation(0, 0);
            restartButton.setSize(200, 50);
            restartButton.setText("New Game!");
            restartButton.setFont(classTitleFont);
            restartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    level = 1;
                    new HeroSelector();
                }
            });
            panel.add(restartButton);
        }
        this.setVisible(true);
    }

   
    @Override
    public void dispose(){
        LandingPage.metaX = this.getLocationOnScreen().x;
        LandingPage.metaY = this.getLocationOnScreen().y;
        super.dispose();
    }
}
