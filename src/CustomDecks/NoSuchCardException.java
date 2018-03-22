/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomDecks;

/**
 *
 * @author Joseph
 */
public class NoSuchCardException extends Exception{
     
    public NoSuchCardException(){
        
    }
    
    public NoSuchCardException(Throwable clause){
        super(clause);
    }
    
    public NoSuchCardException(String message){
        super(message);
    }
    
    public NoSuchCardException(String message, Throwable clause){
        super(message,clause);
    }
}
