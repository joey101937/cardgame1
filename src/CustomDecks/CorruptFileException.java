/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomDecks;

/**
 * used if the saved dataset file is not formatted correctly
 * @author Joseph
 */
public class CorruptFileException extends Exception{
    
    public CorruptFileException(){
        
    }
    
    public CorruptFileException(Throwable clause){
        super(clause);
    }
    
    public CorruptFileException(String message){
        super(message);
    }
    
    public CorruptFileException(String message, Throwable clause){
        super(message,clause);
    }
}
