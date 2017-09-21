/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame1;

import Cards.Card;

/**
 *
 * @author Joseph
 */
public class BoardSlot {
    /* FIELDS */
    public int x,y;
    public int id;
    public Card occupant;
    
    //IDs ARE GIVEN IN THIS ORDER
            //HERO//
    ///////////////////////////
    //  5   6    7   8      //
    //  1   2    3   4     //
    ///////////////////////////
              //HERO//
    
    public BoardSlot(int id){
        switch(id){
            case 1:
                this.id=1;
                this.x = Main.mainBoard.d.width*(20/100);
                this.y = Main.mainBoard.d.height*(3/4);
                return;
            case 2:               
                this.id=2;
                this.x = Main.mainBoard.d.width*(33/100);
                this.y = Main.mainBoard.d.height*(3/4);
                return;
            case 3: 
                this.id=3;
                this.x = Main.mainBoard.d.width*(65/100);
                this.y = Main.mainBoard.d.height*(3/4);
                return;
            case 4:
                this.id=4;
                this.x = Main.mainBoard.d.width*(85/100);
                this.y = Main.mainBoard.d.height*(3/4);
                return;
            case 5:
                this.id=5;
                this.x = Main.mainBoard.d.width*(20/100);
                this.y = Main.mainBoard.d.height*(1/4);
                return;
            case 6:               
                this.id=2;
                this.x = Main.mainBoard.d.width*(33/100);
                this.y = Main.mainBoard.d.height*(1/4);
                return;
            case 7: 
                this.id=3;
                this.x = Main.mainBoard.d.width*(65/100);
                this.y = Main.mainBoard.d.height*(1/4);
                return;
            case 8:
                this.id=4;
                this.x = Main.mainBoard.d.width*(85/100);
                this.y = Main.mainBoard.d.height*(1/4);
                return;
            default:
                System.out.println("BAD BOARD SLOT INPUT: " + id);
        }
    }
    
    
    
}
