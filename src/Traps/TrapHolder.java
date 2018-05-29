/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Traps;

import java.util.ArrayList;

/**
 * class holder that stores a hero's active trap cards.
 * @author Joseph
 */
public class TrapHolder {
    public static final int MAX_SIZE = 2;
    private ArrayList<Trap> storage = new ArrayList<>();
    
    
        /**
     * tries to add trap to play area. fills null slots if applicable.
     * returns false if adding would violate max size requirement
     * @param t minion to add
     * @return was successful?
     */
    public Boolean add(Trap t){
        if(numOccupants() >= TrapHolder.MAX_SIZE){
            System.out.println("failed to summon, storage full");
            return false;
        }
        for(int i = 0; i < storage.size(); i++){
            if(storage.get(i) == null){
                storage.set(i, t);
                return true;
            }
        }
        return storage.add(t);
    }
        
    /**
     * returns the number of non null items in the array
     * @return 
     */
    public int numOccupants(){
        int output = 0;
        for(int i = 0; i < storage.size(); i++){
            if(storage.get(i) != null){
              output++;  
            }
        }
        return output;
    }

    /**
     * removes trap at given index and sets it to null.
     * @param i index
     */
    public void remove(int i) {
        storage.set(i, null);
    }
    
    
        /**
         * is storage at max capacity, excluding null items?
         * @return 
         */
        public boolean isFull(){
        if(storage.size() < MAX_SIZE) return false;
        if(storage.size() > MAX_SIZE) return true; //return true if the play area is somehow over popualted
        for(int i = 0; i < MAX_SIZE; i++){
            if(storage.get(i) == null) return false;
        }
        return true;
    }
        /**
         * returns a list of all non-null items
         * @return 
         */
      public ArrayList<Trap> getOccupants() {
        ArrayList<Trap> output = new ArrayList<>();
        for (Trap e : storage) {
            if (e == null) {
                continue;
            }
            output.add(e);
        }
        return output;
    }

      public int indexOf(Trap t){
          return storage.indexOf(t);
      }
      
      public void remove(Trap t){
          storage.set(storage.indexOf(t), null);
      }
      
      public Trap get(int index){
          try{
               return storage.get(index);
          }catch(IndexOutOfBoundsException ie){
              return null;
          }
         
      }
}
