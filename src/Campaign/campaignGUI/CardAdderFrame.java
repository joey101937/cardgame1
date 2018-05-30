/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Campaign.campaignGUI;

import Campaign.CampaignManager;
import Cards.Card;
import CustomDecks.HeroClass;
import GUI.LandingPage;
import Multiplayer.Phantom;
import cardgame1.SpriteHandler;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *  Used in campaign to allow user to add cards to their deck after winning
 * @author Joseph
 */
public class CardAdderFrame extends JFrame{
    private JLabel titleLabel;
    public int draws; //number of cards left to add to deck
    public ArrayList<Card> currentSelection = new ArrayList<>(); //list of cards currently on screen for the player to choose from
    private static Font classTitleFont = new Font("Arial",Font.BOLD,30);
    public CardAdderPanel panel;
    public JButton[] buttons = new JButton[3]; //choose buttons
    /**
     * constructor
     * @param n how many cycles we will go through to add cards to deck
     */
    public CardAdderFrame(int n){
        draws = n;
        initialize();
    }
    
    //initializes starting components
    private void initialize(){
        System.out.println("setting location to " + LandingPage.metaX + " " + LandingPage.metaY);
        this.setLocation(LandingPage.metaX, LandingPage.metaY);
        
        this.setSize(700, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        populateSelectionBasedOnDraws();
        panel = new CardAdderPanel(this);
        panel.setSize(this.getSize());
        this.add(panel);
        panel.setLayout(null);
        titleLabel = new JLabel();
        titleLabel.setSize(550,50);
        titleLabel.setLocation(70, 50);
        titleLabel.setText("Choose a Card to Add to Your Deck");
        titleLabel.setFont(classTitleFont);
        for(int i = 0; i < 3; i++){
            buttons[i] = new JButton();
            buttons[i].setSize(80,30);
            buttons[i].setLocation(80 + 220*i, 500);
            buttons[i].setText("Choose");
            panel.add(buttons[i]);
            buttons[i].addActionListener(new AdderActionListener(currentSelection.get(i)));
        }
         
        panel.add(titleLabel);
        this.setVisible(true);
    }
    
    
    public static void main(String[] args){
        SpriteHandler.Initialize();
        new CardAdderFrame(3);
    }
    /**
     * populates the selection of cards based on how many draws are left.
     */
    public void populateSelectionBasedOnDraws() {
        currentSelection.clear();
        ArrayList<Card> possibilities = new ArrayList<>();
        switch (draws) {
            case 3: //class card
                for (Card c : Card.getCardList()) {
                    if (c.heroClass == CampaignManager.playerClass) {
                        possibilities.add(c);
                    }
                }
                for (int i = 0; i < 3; i++) {
                    currentSelection.add(possibilities.remove((int) (Phantom.random.nextDouble() * possibilities.size())));
                }
                break;
            case 2:       //randomly chooses a neutral or class card  
            case 4:
              if(Phantom.random.nextBoolean()){
                    for (Card c : Card.getCardList()) {
                    if (c.heroClass == CampaignManager.playerClass) {
                        possibilities.add(c);
                    }
                }
                for (int i = 0; i < 3; i++) {
                    currentSelection.add(possibilities.remove((int) (Phantom.random.nextDouble() * possibilities.size())));
                }
                break;
              }else{
                    for (Card c : Card.getCardList()) {
                    if (c.heroClass == HeroClass.Neutral) {
                        possibilities.add(c);
                    }
                }
                for (int i = 0; i < 3; i++) {
                    currentSelection.add(possibilities.remove((int) (Phantom.random.nextDouble() * possibilities.size())));
                }
                break;
              }
            case 1: //neutral card
                for (Card c : Card.getCardList()) {
                    if (c.heroClass == HeroClass.Neutral) {
                        possibilities.add(c);
                    }
                }
                for (int i = 0; i < 3; i++) {
                    currentSelection.add(possibilities.remove((int) (Phantom.random.nextDouble() * possibilities.size())));
                }
                break;
            default:
                break;
        }
    }
    
    
    private void reset(){
        populateSelectionBasedOnDraws();
        for(int i = 0; i < 3; i++){
            panel.remove(buttons[i]);
            buttons[i] = new JButton();
            buttons[i].setSize(80,30);
            buttons[i].setLocation(80 + 220*i, 500);
            buttons[i].setText("Choose");
            panel.add(buttons[i]);
            buttons[i].addActionListener(new AdderActionListener(currentSelection.get(i)));
        }
        panel.repaint();
    }
    
    private class AdderActionListener implements ActionListener{
        Card c;
        AdderActionListener(Card c){
            this.c=c;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            CampaignManager.playerCards.add(c);
            System.out.println("adding to player deck: " + c.name);
            draws--;
            if(draws>0){
                reset();
            }else{
                new CampaignInterface();
                dispose();
            }
        }
        
    }
    
    @Override
    public void dispose(){
        LandingPage.metaX = this.getLocationOnScreen().x;
        LandingPage.metaY = this.getLocationOnScreen().y;
        super.dispose();
    }
}
