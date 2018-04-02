/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JFrame;

/**
 * modified jframe for use in deckloaderscratch class
 * @author Joseph
 */
public class coreJFrame extends JFrame{
    private DeckLoaderScratch host;
    private LegacyGUI parent;
    private DeckBuilder builder;
    
    public coreJFrame(DeckLoaderScratch dls, LegacyGUI leg, DeckBuilder build){
        host = dls;
        parent = leg;
        builder = build;
    }
    @Override
    public void dispose(){
        super.dispose();
        if(parent!=null){
            parent.setEnabled(true);
            parent.requestFocus();
        }
        if(builder != null){
            builder.setEnabled(true);
            builder.requestFocus();
            builder.loadDeck(host.chosenDeck);
        }
    }
}
