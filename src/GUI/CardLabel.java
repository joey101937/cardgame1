/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Cards.Card;
import static GUI.DeckBuilder.mainBuilder;
import cardgame1.Main;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

/**
 * JLabel for displaying cards
 * @author Joseph
 */
public class CardLabel extends JLabel{
    public Card card;
    private static Font cardTitleFont = new Font("Arial",Font.PLAIN,20);
    private DeckLoaderScratch host;
    private CardLabel me = this;
    public CardLabel(Card c){
        super();
        init(c);
    }
    /**
     * constructor that also takes deckloaderscratch param to call the updatepreview method 
     * on whenevever the component is moused over
     * @param c card we are holding and to display
     * @param d UI frame to update
     */
    public CardLabel(Card c, DeckLoaderScratch d){
        super();
        host = d;
        init(c);
    }
    /**
     * initializes starting values based on given card
     * @param c given card
     */
    private void init(Card c){
        card = c;
        setForeground(c.heroClass.getColor());
        if(Main.showCostInBuilder)setText(c.cost + " "+c.name);
        else setText(c.name);
        setFont(cardTitleFont);
        this.setToolTipText(c.cardText);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {  
            }
            @Override
             public void mousePressed(MouseEvent e) {   
                 if(e.getButton()==1)CardDisplay.display(card); //l;eft click
                if(e.getButton()==3 && mainBuilder!=null && mainBuilder.isEnabled()){
                    mainBuilder.product.deck.remove(mainBuilder.cardLabels.indexOf(me));
                    mainBuilder.updateList();
                }//right click
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                DeckLoaderScratch.mousedOver=c;
                if(host!= null) host.updatePreview();
            }
            @Override
            public void mouseExited(MouseEvent e) {
            DeckLoaderScratch.mousedOver=null;
            if(host != null) host.updatePreview();
            }
        });
    }
}
