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
    
    public coreJFrame(DeckLoaderScratch dls, LegacyGUI leg){
        host = dls;
        parent = leg;
    }
    @Override
    public void dispose(){
        super.dispose();
        if(parent!=null){
            parent.setEnabled(true);
            parent.requestFocus();
        }
    }
}
