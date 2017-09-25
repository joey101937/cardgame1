/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import Cards.ArakkoaCard;
import Cards.Card;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Joseph
 */
public class Main {

    //Main Fields

    public static String assets = "Assets" + File.separator;
    public static Thread gameThread;
    public static BufferedImage BackgroundImage;
    public static Board mainBoard;
    
    public static ArrayList<Card> enemyCards = new ArrayList<>();
    public static ArrayList<Card> playerCards = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        setBackgroundImage();
        SpriteHandler.Initialize();
        for(int i = 0; i < 10 ; i++){
            enemyCards.add(new ArakkoaCard());
            playerCards.add(new ArakkoaCard());
        }
        
        Hero enemy = new Hero("AI Hero", enemyCards);
        Hero player = new Hero("Player Hero", playerCards);
        mainBoard = new Board(enemy, player);
    }

    
    
    
    //UTILITY METHODS
    public static String getDir() {
        String output = System.getProperty("user.dir") + File.separator;
        return output;
        
    }

    private static void setBackgroundImage() {
        try {
           // BackgroundImage = ImageIO.read(new File(Main.getDir() + Main.assets + "BGtest.png"));
            BackgroundImage = ImageIO.read(new File(Main.getDir() + Main.assets + "paper.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
        /**
     * displays a dialogue box with a text input field. And a question for the user.
     * returns the text area's contents.
     * @param question
     * @return 
     */
    public static String prompt(String question){
        return JOptionPane.showInputDialog(question);
    }
    
            /**
         * displays a dialog box give the user a message, String s.
         * pauses the thread until the user hits OK
         * @param s 
         */
        public static void display(String s){
        JOptionPane.showMessageDialog(null, s);
    }
    
    /**
     * asks the user what size window they want
     * @return the dimension they want
     */
    public static Dimension takeWindowSize() {
        Integer input = null;
        try {
            input = new Integer(prompt("Desired Window length in pixels (700-1900)"));
        } catch (Exception e) {
            display("Must enter Number 700-1900");
            return takeWindowSize();
        }
        if (input <= 700) {
            return new Dimension(700,700);
        }
        if (input >= 1900) {
            return new Dimension(1900,1050);
        }
        while (input % 50 != 0) {
            input--;    //always multiple of 50
        }
        int h = (input*2)/3;
        while(h % 50 != 0){
            h--;        //always multiple of 50
        }        
        return new Dimension(input,h);
    }
    
            /**
         * returns a random integer between the given parameters
         * @return the number
         */
        public static int generateRandom(int min, int max){
            if(min == max) return min; //if they are the same return that number
            if(max < min){
                //if the numbers are entered backwards, rerun the method with the correct order
                return generateRandom(max, min);
            }else{
                //here is the body of our method
                int diff = max - min;
                int output = (int)(Math.random()*diff); //generates a random number between 0 and the difference between the numbers
                return (min + output);                //returns that random number plus the min
            }
        }
        /**
         * clamps the input to a given set of constraints and returns the max or min rather than the inputed value 
         * if the input is outside the given range
         * @param input the number to test
         * @param max maximum value
         * @param min minimum value
         * @return if the number is between max and min then return input, else return the min or max respectively
         */
    public static int clamp(int input, int max, int min) {
        if (input > max) {
            return input = max;
        }
        if (input < min) {
            return input = min;
        }
        return input;
    }
}
