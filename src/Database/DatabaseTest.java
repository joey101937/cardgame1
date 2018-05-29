/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Cards.Card;
import Minions.Minion;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Joseph
 */
public class DatabaseTest {
    private static final String USER = "root";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/udemy?serverTimezone=UTC";
    private static String password;
    private static ResultSet results = null;
    
    
    public static void main(String[] args){
        Connection connection = null;
        Statement statement = null;
        password = JOptionPane.showInputDialog("Password for " + USER);  
        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL,USER,password);
            statement = connection.createStatement();
            String command = "INSERT INTO Cards (Name, Type, Cost, Summon, CardText, Class) VALUES ";
            for(Card c : Card.getCardList()){
                System.out.println(c);
                command += "(";
                command += "\'"+c.name.replace('\'', '`') + "\', " +  "\'"+c.cardType + "\'" + ", " + c.cost + ", \'" + describe(c.summon) +  "\',\' -" + c.cardText.replace('\'', '`').replace("\n", "") +"\', " + "\'"+ c.heroClass + "\'";
                command += "),";
                
            } 
             if(command.endsWith(",")){
                 command = command.substring(0, command.length()-1);
             }
             System.out.println(command+"|");
             String yesNo = JOptionPane.showInputDialog("ok?");
             if(yesNo.toLowerCase().startsWith("y")){
                 statement.execute(command);
             }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                //results.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static String describe(Minion m){
        if(m==null)return"NULL";
        String output = m.name + " " + m.attack +"/" + m.health;
        return output;
    }
}
