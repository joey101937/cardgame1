/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//testing on mint laptop
package cardgame1;

import Cards.Base.FireBoltCard;
import Cards.Base.FrostBearCard;
import Cards.Base.VengefullKnightCard;
import Cards.Base.ArcherCard;
import Cards.Base.KnightCard;
import Cards.Base.ArakkoaCard;
import Cards.*;
import Cards.Dragon.GrayDrakeCard;
import Cards.Fish.*;
import GUI.LandingPage;
import Multiplayer.Phantom;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
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
    public static boolean isMulitiplayerGame = false;
    public static boolean showFPS = false;
    public static boolean showCostInBuilder = false;
    public static boolean removeServerTimeout = false;
    public static ArrayList<Card> enemyCards = new ArrayList<>();
    public static ArrayList<Card> playerCards = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        setBackgroundImage();
        setFontScale();
        SpriteHandler.Initialize();
        //LegacyGUI og = new LegacyGUI();
        new LandingPage();
    }

    /**
     * old main method commands for referance
     */
    private void legacyMain(){
        setBackgroundImage();
        SpriteHandler.Initialize();
        for (int i = 0; i < 5; i++) {
            enemyCards.add(new ArakkoaCard());
            enemyCards.add(new FireBoltCard());
            enemyCards.add(new FrostBearCard());
            enemyCards.add(new ArcherCard());
            enemyCards.add(new KnightCard());
            enemyCards.add(new VengefullKnightCard());

        }
        giveFishPackage();
        Hero enemy = new Hero("AI Hero", enemyCards, SpriteHandler.ashePortrait);
        Hero player = new Hero("Player Hero", playerCards, SpriteHandler.ashePortrait);
        mainBoard = new Board(enemy, player);
    }
    
    
    /**
     * fills the player deck with a fish deck
     * @param h 
     */
    private static void giveFishPackage(){
        for(int i = 0; i<4 ; i++){
            playerCards.add(new BaitfishCard());
            playerCards.add(new ThrasherCard());
            playerCards.add(new CarnifishCard());
            //playerCards.add(new FireBoltCard());
            playerCards.add(new FrenzyCard());
            playerCards.add(new PredationCard());
        }

    }
    
    public static ArrayList<Card> getTestDeck(){
        ArrayList<Card> output = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            output.add(new ArakkoaCard());
            output.add(new FireBoltCard());
            output.add(new ArcherCard());
            output.add(new PirranahCard());
            output.add(new GrayDrakeCard());
        }
        return output;
    }
    
    /**
     * returns font scale and also sets it in Card.java
     * */
    public static double setFontScale(){
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        double given = screensize.getWidth()*screensize.getHeight();
        double scale = screensize.getWidth()/1920; //devide by 1920
        scale +=1;
        scale/=2; //make it less extremes
        Card.fontScale = scale;
        return scale;
    }
    
    
    //UTILITY METHODS
    public static String getDir() {
        String output = System.getProperty("user.dir") + File.separator;
        return output;
        
    }
    /**
     * sleeps the thread
     * @param duration 
     */
    public static void wait(int duration){
        try{
            Thread.sleep(duration);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void setBackgroundImage() {
        try {
           //BackgroundImage = ImageIO.read(new File(Main.getDir() + Main.assets + "BGtest.png"));
            BackgroundImage = ImageIO.read(new File(Main.getDir() + Main.assets + "paper.png"));
        } catch (Exception e) {
            e.printStackTrace();
            display("Missing Asset: paper.png\n Please Verify assets folder.");
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
            return new Dimension(700,500);
        }
        if (input >= 1900) {
            return new Dimension(1920,1080);
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
                int output = (int)(Phantom.random.nextDouble()*diff); //generates a random number between 0 and the difference between the numbers
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

    public static String getPublicIP() {
        try{
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
        String ip = in.readLine(); //you get the IP as a String
        return ip;
        }catch(Exception e){
         return "<public IP unknown>";   
        }
    }
}
