/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import Minions.Minion;
import java.util.ArrayList;

/**
 * specialized list-like class where removed items are replaced with null instead of being removed. null indexes are then replaced
 * with a new addition when a new element is added.
 * @author Joseph
 */
public class PlayArea<E>{
   private ArrayList<E> storage = new ArrayList<>();
   public static final int MAX_SIZE = 4;
   
    public E get(int index){
        return storage.get(index);
    }
    
    public ArrayList<E> getStorage(){
        return storage;
    }
    
    public int indexOf(E e){
        return storage.indexOf(e);
    }
    /**
     * tries to add minion to play area. fills null slots if applicable.
     * returns false if adding would violate max size requirement
     * @param m minion to add
     * @return was successful?
     */
    public Boolean add(E m){
        if(storage.size() >= PlayArea.MAX_SIZE){
            return false;
        }
        for(int i = 0; i < storage.size(); i++){
            if(storage.get(i) == null){
                storage.set(i, m);
                return true;
            }
        }
        return storage.add(m);
    }
    /**
     * adds minion to specified index. overrides size requirements.
     * @param index place to add
     * @param m minion to add
     */
    public void add(int index, E m){
        storage.add(index, m);
    }
    /**
     * removes minion at given index and sets it to null.
     * @param i index
     */
    public void remove(int i){
        storage.set(i, null);
    }
    /**
     * removes minion from Play Area and sets the index it was at to null
     * @param m minion to remove
     * @return was successful?
     */
    public boolean remove(E m){
        for(E min : storage){
            if(m == min){
                this.remove(storage.indexOf(min));
                return true;
            }
        }
        return false;
    }
    
 
    }
