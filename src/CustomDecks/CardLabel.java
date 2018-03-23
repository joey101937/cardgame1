/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomDecks;

import Cards.Card;
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
    public CardLabel(Card c){
        super();
        init(c);
    }
    public CardLabel(Card c, DeckLoaderScratch d){
        super();
        host = d;
        init(c);
    }
    
    private void init(Card c){
        card = c;
        setForeground(c.heroClass.getColor());
        setText(c.name);
        setFont(cardTitleFont);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CardDisplay.display(card);
            }
            @Override
             public void mousePressed(MouseEvent e) {   
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
