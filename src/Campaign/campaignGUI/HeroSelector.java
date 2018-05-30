/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Campaign.campaignGUI;

import Campaign.CampaignManager;
import Cards.Card;
import CustomDecks.HeroClass;
import GUI.BackgroundPane;
import GUI.LandingPage;
import GUI.LegacyGUI;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * displays 6 buttons, one for each class for the player to choose from
 * sets player class and starting deck based on what they choose
 * @author Joseph
 */
public class HeroSelector extends JFrame{
    public HeroClass chosenClass;
    private BackgroundPane panel;
    private JLabel titleLabel;
    private static Font titleFont = new Font("Times", Font.BOLD, 35);
    private static Font classTitleFont = new Font("Arial",Font.PLAIN,24);
    
    public HeroSelector(){
        init();
    }

    private void init() {      
        System.out.println("setting location to " + LandingPage.metaX + " " + LandingPage.metaY);
        this.setLocation(LandingPage.metaX, LandingPage.metaY);
        
        this.setSize(700, 700);
        this.setIconImage(SpriteHandler.swordsSmall);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new BackgroundPane(Main.BackgroundImage);
        panel.setSize(this.getSize());
        panel.setLayout(null);
        this.add(panel);
        
        titleLabel = new JLabel();
        titleLabel.setLocation(150, 25);
        titleLabel.setSize(500,50);
        titleLabel.setFont(titleFont);
        titleLabel.setText("CHOOSE YOUR HERO");
        panel.add(titleLabel);
        
        
        //left column
        HeroClassLabel oceanLabel = new HeroClassLabel(HeroClass.Ocean,this);
        oceanLabel.setLocation(20,70);
        JLabel oceanName = new JLabel();
        oceanName.setSize(100,35);
        oceanName.setLocation(120, 205);
        oceanName.setText("Ocean");
        oceanName.setFont(classTitleFont);
        panel.add(oceanName);
        
        HeroClassLabel dragonLabel = new HeroClassLabel(HeroClass.Dragon,this);
        dragonLabel.setLocation(20, 200);
        JLabel dragonName = new JLabel();
        dragonName.setSize(100, 35);
        dragonName.setLocation(120, 335);
        dragonName.setText("Dragon");
        dragonName.setFont(classTitleFont);
        panel.add(dragonName);
        
        HeroClassLabel elementalLabel = new HeroClassLabel(HeroClass.Elemental, this);
        elementalLabel.setLocation(20, 330);
        JLabel elementalName = new JLabel();
        elementalName.setSize(150, 35);
        elementalName.setLocation(120, 465);
        elementalName.setText("Elemental");
        elementalName.setFont(classTitleFont);
        panel.add(elementalName);
        //right column
        HeroClassLabel undeadLabel = new HeroClassLabel(HeroClass.Undead, this);
        undeadLabel.setLocation(355, 70);
        JLabel undeadName = new JLabel();
        undeadName.setSize(100, 35);
        undeadName.setLocation(455, 205);
        undeadName.setText("Undead");
        undeadName.setFont(classTitleFont);
        panel.add(undeadName);
        
        HeroClassLabel empireLabel = new HeroClassLabel(HeroClass.Empire,this);
        empireLabel.setLocation(355, 200);
        undeadLabel.setLocation(355, 70);
        JLabel empireName = new JLabel();
        empireName.setSize(100, 35);
        empireName.setLocation(455, 335);
        empireName.setText("Empire");
        empireName.setFont(classTitleFont);
        panel.add(empireName);
    
        HeroClassLabel neutralLabel = new HeroClassLabel(HeroClass.Neutral, this);
        neutralLabel.setLocation(355, 330);
        JLabel neutralName = new JLabel();
        neutralName.setSize(200, 35);
        neutralName.setLocation(435, 465);
        neutralName.setText("Neutral (HARD)");
        neutralName.setFont(classTitleFont);
        neutralName.setForeground(Color.red);
        panel.add(neutralName);

        panel.add(oceanLabel);
        panel.add(dragonLabel);
        panel.add(elementalLabel);
        panel.add(undeadLabel);
        panel.add(empireLabel);
        panel.add(neutralLabel);
        this.setVisible(true);
    }

    @Override
    public void dispose(){
        LandingPage.metaX = this.getLocationOnScreen().x;
        LandingPage.metaY = this.getLocationOnScreen().y;
        super.dispose();
        CampaignManager.playerClass = chosenClass;
        //CampaignManager.playerCards = getStartingDeck(chosenClass);
        getStartingDeck(chosenClass);
        CampaignManager.startGame();
    }
    
    private ArrayList<Card> getStartingDeck(HeroClass hClass){
        switch(hClass){
            case Neutral:
                CampaignManager.playerCards =  LegacyGUI.getBaseDeck();
                return LegacyGUI.getBaseDeck();
            case Undead:
                  CampaignManager.playerCards =  LegacyGUI.getUndeadDeck();
                return LegacyGUI.getUndeadDeck();
            case Ocean:
                  CampaignManager.playerCards =  LegacyGUI.getFishDeck();
                    return LegacyGUI.getFishDeck();
            case Dragon:
                  CampaignManager.playerCards =  LegacyGUI.getDragonDeck();
                return LegacyGUI.getDragonDeck();
            case Elemental:
                  CampaignManager.playerCards =  LegacyGUI.getElementalDeck();  
                return LegacyGUI.getElementalDeck();
            case Empire:
                  CampaignManager.playerCards =  LegacyGUI.getEmpireDeck();
                return LegacyGUI.getEmpireDeck();
            default:
                JOptionPane.showMessageDialog(null,"Error trying to get starting deck for restricted class");
                return null;
        }
    }
    /*
    private ArrayList<Card> getStartingDeck(HeroClass hClass){
        ArrayList<Card> output = new ArrayList<>();
        switch(hClass){
            case Neutral:
                output.add(new ArcherCard());
                output.add(new ArcherCard());
                output.add(new ArcherCard());
                output.add(new KnightCard());
                output.add(new KnightCard());
                output.add(new PaladinCard());
                output.add(new PaladinCard());
                output.add(new FrostBearCard());
                output.add(new FrostBearCard());
                output.add(new MinotaurCard());
                output.add(new MinotaurCard());
                output.add(new VolcanoCard());
                break;
            case Ocean:
                output.add(new ArcherCard());
                output.add(new ArcherCard());
                output.add(new KnightCard());
                output.add(new PirranahCard());
                output.add(new PirranahCard());
                output.add(new JellyfishCard());
                output.add(new JellyfishCard());
                output.add(new PaladinCard());
                output.add(new FrostBearCard());
                output.add(new FrostBearCard());
                output.add(new VolcanoCard());
                break;
            case Undead:
                output.add(new ArcherCard());
                output.add(new ArcherCard());
                output.add(new ArcherCard());
                output.add(new KnightCard());
                output.add(new KnightCard());
                output.add(new GhoulCard());
                output.add(new GhoulCard());
                output.add(new SkeletonArmySpell());
                output.add(new PaladinCard());
                output.add(new FrostBearCard());
                output.add(new FrostBearCard());
                break;
            case Dragon:
                output.add(new ArcherCard());
                output.add(new ArcherCard());
                output.add(new KnightCard());
                output.add(new KnightCard());
                output.add(new GrayDrakeCard());
                output.add(new GrayDrakeCard());
                output.add(new PaladinCard());
                output.add(new PaladinCard());
                output.add(new MinotaurCard());
                output.add(new MinotaurCard());
                output.add(new VolcanoCard());
                break;
            case Empire:
                output.add(new ArcherCard());
                output.add(new ArcherCard());
                output.add(new KnightCard());
                output.add(new KnightCard());
                output.add(new SpearmanCard());
                output.add(new SpearmanCard());
                output.add(new GriffonCard());
                output.add(new GriffonCard());
                output.add(new PaladinCard());
                output.add(new DoubleshotCard());
                output.add(new UndyingSoldierCard());
                break;
            case Elemental:
                output.add(new ArcherCard());
                output.add(new ArcherCard());
                output.add(new StoneGolemCard());
                output.add(new StoneGolemCard());
                output.add(new StoneGolemCard());
                output.add(new GeomancerCard());
                output.add(new PaladinCard());
                output.add(new PaladinCard());
                output.add(new MinotaurCard());
                output.add(new MinotaurCard());
                output.add(new VolcanoCard());
                break;
        }
        for(Card c : output){
        CampaignManager.playerCards.add(c);
        }
        return output;
    }
    */
    
    /**
     * for testing purposes
     * @param args
     */
    public static void main(String[] args) {
        Main.setBackgroundImage();
        SpriteHandler.Initialize();
        new HeroSelector();
    }

}
