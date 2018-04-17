/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Campaign.campaignGUI;

import Campaign.CampaignManager;
import CustomDecks.HeroClass;
import cardgame1.SpriteHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Joseph
 */
public class CampaignPanel extends JPanel{
      BufferedImage background;
      private static Font levelTitleFont = new Font("Arial",Font.BOLD,20);
      /**
       * @param i Image to be used as the background
       */
        public CampaignPanel(BufferedImage i){
            background = i;
        }
        /**
         * Same as regular paintComponent except it also draws the given buffered image
         * and also draws level list and relevant info
         * note the image is sourced starting from a 200x200 offset from the top left of the image rather than usual 0x0
         * @param g image to draw
         */
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
             if(background==null){
                 g2d.setBackground(new Color(255,255,255,0));
                 return;
             }
            this.setBackground(Color.black);  
            //g.drawImage(img, 0, 0, null);
            g.drawImage(background,0,0,1000,1000,200,200,1000,1300,null);
            drawLevelList(g2d);
        } 
        /**
         * Draws the list of levels on the right side of the screen to show player progress
         * @param g 
         */
        private void drawLevelList(Graphics g){
            for(int i = CampaignManager.NUM_LEVELS; i > 0; i--){
                g.setColor(Color.darkGray);
                g.fillRect(400, (40*i), 200, 25);
                g.setColor(Color.black);
                g.setFont(levelTitleFont);
                g.drawString(String.valueOf(i), 370, (40*i)+20);
                if(CampaignManager.getEnemyClassForLevel(i)!=HeroClass.Neutral){
                    BufferedImage toDraw = CampaignManager.getEnemyClassForLevel(i).getClassIcon();
                    if(CampaignManager.getEnemyClassForLevel(i)==HeroClass.Elemental){
                        g.drawImage(toDraw.getScaledInstance(toDraw.getWidth()/2, toDraw.getHeight()/2, 0), 600, (40*i)+3, null); //elemental icon is smaller so move down 3
                    }else{
                        g.drawImage(toDraw.getScaledInstance(toDraw.getWidth()/2, toDraw.getHeight()/2, 0), 600, (40*i)-5, null);//icon slightly large, so move up 5 to center
                    }
                }
                if(i<CampaignManager.level){
                    g.drawImage(SpriteHandler.checkmark.getScaledInstance(40, 40, 0), 480, (40*i)-10, null);
                }
                if(i==CampaignManager.level){
                    g.drawImage(SpriteHandler.swordsSmall, 480, (40*i)-10, null);
                }
            }
        }
}
