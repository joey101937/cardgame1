/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Joseph
 */
public class Window extends Canvas {
    /* FIELDS */
    public JFrame frame;
    
    public Window(int width, int height, String title, Board board) {
        frame = new JFrame(title);
        frame.setMinimumSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setAutoRequestFocus(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(title);
        frame.add(board);

        
        
        board.setBounds(0, 0, width, height);

        board.start();
    }
    
    
    
    
    
}
