/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Campaign.campaignGUI;

import CustomDecks.HeroClass;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * used with hero selector, displays a class portrait and starts campaign when clicked
 * @author Joseph
 */
public class HeroClassLabel extends JLabel{
    public HeroClassLabel(HeroClass hClass, HeroSelector parent){
        this.setSize(300,200);
        this.setIcon(new ImageIcon(hClass.getHeroPortrait().getSubimage(0, 60, 300, 85)));
        this.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                parent.chosenClass = hClass;
                System.out.println("chose hero: " + hClass);
                parent.dispose();
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
    }
}
