/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Campaign;

import GUI.BackgroundPane;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import javax.swing.JFrame;

/**
 * Main campaign menu
 * @author Joseph
 */
public class CampaignInterface extends JFrame{
    private BackgroundPane panel;
    
    public CampaignInterface(){
        init();
        this.setVisible(true);
    }
    
    private void init(){
     this.setSize(700,700);
     this.setIconImage(SpriteHandler.swordsSmall);
     this.setDefaultCloseOperation(EXIT_ON_CLOSE);
     panel = new BackgroundPane(Main.BackgroundImage);   
     panel.setSize(this.getSize());
     this.add(panel);
    
    
    }
    /**
     * for testing purposes
     * @param args 
     */
    public static void main(String[] args) {
        Main.setBackgroundImage();
        SpriteHandler.Initialize();
        new CampaignInterface();
    }
}
