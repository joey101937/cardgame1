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
    private DuelFrame duel;
    private MultiplayerFrame mpf;
    public coreJFrame(DeckLoaderScratch dls, LegacyGUI leg, DeckBuilder build, DuelFrame df, MultiplayerFrame mpf){
        host = dls;
        parent = leg;
        builder = build;
        duel=df;
        this.mpf=mpf;
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
        if(duel!=null){
            duel.setEnabled(true);
            duel.requestFocus();
        }
        if(mpf !=null){
            mpf.setEnabled(true);
            mpf.requestFocus();
        }
    }
}
