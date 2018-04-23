/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import Cards.Empire.FireyWhelpCard;
import Cards.Base.SpearmanCard;
import AI.AI;
import Cards.Base.*;
import Cards.Dragon.*;
import Cards.Elemental.*;
import Cards.Empire.*;
import Cards.Fish.*;
import Cards.Undead.*;
import CustomDecks.HeroClass;
import Minions.Minion;
import Minions.Tribe;
import Multiplayer.Phantom;
import Traps.TrapListener;
import cardgame1.Board;
import cardgame1.Hero;
import cardgame1.InputHandler;
import cardgame1.Main;
import cardgame1.SpriteHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Card itself, these are played from the hand to perform functions or generate Minions
 * @author Joseph
 */
public abstract class Card implements Comparable{
    public static final Integer WIDTH = 200;
    public static final Integer HEIGHT = 300;
    /*   AI FLAGS       */
    /** will this deal damage (true) or heal (false). used to determine which side of the field to target */
    public boolean isOffensive = false;
    
    /* FIELDS */
    public String name;         //name of card
    public CardType cardType;   //Minion or spell
    public CardPurpose cardPurpose; //purpose of the card, used with AI
    public int intrinsicValue; //bonusValue given to a card, used by AI
    public boolean isTargeted = false; //does this card's cast method want to take a target?
    public int spellDamage = 0;     //damage dealt on cast, if applicable
    public Minion summon;       //if this is a minion card, the minion it summons. if spell, this is null
    public String cardText;     //what the card says on it
    public int cost;            //casting cost
    public BufferedImage sprite; //visual representation of the card
    public HeroClass heroClass = HeroClass.Neutral; //neutral by default
    protected Hero owner;
    public static boolean showValue = false;
    
    /**
     * renders a particular card, cardback if its an enemy's card.
     * @param g graphics to use
     * @param x where to draw(x)
     * @param y  where to draw (y)
     * @param override if set to true, the method always renders the actual card (enemies see the card, not cardback)
     */
    public void render(Graphics2D g, int x, int y, boolean override){
       if(owner == Board.playerHero || override){
           g.drawImage(sprite, x, y, null);   //the card is ours so we can see what it is
           g.setColor(Color.yellow);
           g.drawString(String.valueOf(cost), x + 10, y+35);
           this.renderCardText(g, x, y);
           if(summon != null){
               g.setColor(Minion.attackRed);
               g.drawString(summon.attack.toString(), x + 60 - (summon.attack.toString().length() * 10), y + (Card.HEIGHT*12)/14);
               g.setColor(Minion.healthGreen);
               g.drawString(summon.health.toString(), x + Card.WIDTH - 60 - (summon.health.toString().length() * 10), y + (Card.HEIGHT*12)/14);
               if(summon.isSilenced){
                   g.drawImage(SpriteHandler.canceledEffect, x + Card.WIDTH/2 - 27, y + Card.HEIGHT/2 + 30, null);
               }
           }
           if(InputHandler.selectedCard == this){
               if(owner.resource >= cost){// if selected and we can afford to cast it, color it green
                   g.setColor(new Color(0,255,0,50));
                   g.fillRect(x, y, Card.WIDTH, Card.HEIGHT);
               }else{
                   g.drawImage(SpriteHandler.redX, x, y, null);
               }
           }
       }else{
           g.drawImage(SpriteHandler.cardback, x, y, null);    //if we arent the owner of the card, we see the cardback, not the card itself
       }
            if(showValue && (owner==Board.playerHero || override)){
            g.setColor(Color.gray);
            g.drawString(String.valueOf(AI.getValueOfCard(this)), x, y);
        }
    }
    /**
     * every tick while in hand
     */
    public void tick(){};
    /**
     * used if this is considered a special card by ai, used as cast method
     */
    public void smartCast(){};
    
    private void renderCardText(Graphics2D g, int x , int y){
        Font original = g.getFont();
        Font toUse = new Font("Arial", Font.BOLD, 18);
        g.setColor(Color.black);
        g.setFont(toUse);
        String[] lines = cardText.split(" \n ");
        for(int i = 0; i < lines.length; i++){
            g.drawString(lines[i], x + 10, y + 165 + (i * 20));
        }
        g.setColor(Color.black);
        g.drawString(name,x+40, y+20);
        if(summon!=null && summon.tribe!= Tribe.none){
        g.setFont(new Font("Arial", Font.BOLD,15));
        g.setColor(Color.white);
        g.drawString(summon.tribe.toString(), x+110 - summon.tribe.toString().length()*5, y+Card.HEIGHT-10);
        }
        g.setFont(original);
    }
    
    /**
     * gets the int value of the topleft corner of the rendered card based on location in hand.
     * @return x value of topleft corner NOT ADJUSTED FOR SCALING; -1 if card is not in hand.
     */
    public int getXCoordinate(){
        for(Card c : Board.playerHero.hand){
            if(c != this) continue;
            if(Board.playerHero.hand.indexOf(c) % 2 == 0){
                //left column, even
                return 1100;
            }else{
                //right column, odd
                return 1100 + Board.buffer + Card.WIDTH;
            }
        }
        return -1;
    }
      /**
     * gets the int value of the topleft corner of the rendered card based on
     * location in hand.
     * @return y value of topleft corner NOT ADJUSTED FOR SCALING; -1 if card is
     * not in hand.
     */
    public int getYCoordinate(){
        for(Card c : Board.playerHero.hand){
            if(c != this) continue;
            if(Board.playerHero.hand.indexOf(c) < 2){
                return 25 + Board.buffer;       //top level
            }else if(Board.playerHero.hand.indexOf(c) < 4){
                return 25 + Board.buffer*2 + Minion.HEIGHT;  //middle level
            }else if(Board.playerHero.hand.indexOf(c) < 6){
                return 25 + Board.buffer*3 + Minion.HEIGHT + Minion.HEIGHT; //bottom level
            }
        }
        return -1;
    }
    
    /**
     * what happens when the card is played from the hand.
     * @param target target minion if applicable. may be null
     * returns an int reflecting the outcome. 
     * 1 for success
     * 0 for not cast (not enough mana, etc)
     */
    public int cast(Minion target){
    return defaultMinionSummon();
    }
    /**
     * what happens when the card is played from the hand onto a hero.
     * @param target
     * @return an int reflecting outcome
     * 1 for success
     * 0 for not cast (not enough mana, cant target heros, etc)
     */
    public int castOnHero(Hero target){
    return defaultMinionSummon();
    }
    /**
     * if the owner has enough resources left to afford the cast cost of this card
     * @return 
     */
    public boolean canAfford(){
        return owner.resource >= cost;
    }
    public void destroy(){
        //TODO
    }
    /**
     * special method for specialist card-purpose. used by ai to get value
     * does not include intrinsic value
     * @return 
     */
    public int getValue(){return 0;}
    
    /**
     * default method to use for simple summon cards
     * @return  1 if success, -1 if error, 0 if could not afford
     */
    protected synchronized int defaultMinionSummon(){
        if (canAfford()) {
            if (owner.minions.add(summon)) {
                if (owner.opponent.isPhantomControlled && (this.cardPurpose==CardPurpose.VanillaMinion || cardPurpose==CardPurpose.ChargeMinion || cardPurpose==CardPurpose.BattlecryMinionDraw)) {
                    notifyPhantom(null,null);
                }
                owner.resource -= cost;
                owner.hand.remove(this);
                summon.onSummon();
                TrapListener.onPlay(this);
                return 1;
            } else {
                //could not legally summon. likely not enough board slots
                return -1;
            }
        }
        return 0;
    }
    /**
     * if needed, notifies the phantom and sends the appropriate message
     */
    public synchronized void notifyPhantom(Minion targetMinion, Hero targetHero){
        /*
        if (!Main.isMulitiplayerGame){
            System.out.println("returning because not a mp game. if mp, this is an error");
            return;
        }
        //System.out.println("notifying: "+name + " target: " + targetMinion.name+","+targetHero); //MAKE THIS PRINT TARGETS WHEN I GET AROUND TO IT
        String message = "c-" + owner.hand.indexOf(this) + "-";
        if (targetMinion == null && targetHero == null) {
            message+="n-0";
            Board.nonPlayerHero.getPhantom().communicateMessage(message);
            return;
        }
        if (targetMinion != null) {
           if(targetMinion.owner!=owner){
               message+="em-";
               message+=owner.opponent.minions.indexOf(targetMinion);
               Board.nonPlayerHero.getPhantom().communicateMessage(message);
               return;
           }else{
               message+="fm-";
               message+=owner.minions.indexOf(targetMinion);
               Board.nonPlayerHero.getPhantom().communicateMessage(message);
           }
           return;
        }else{
            if(targetHero==owner){
                message+="fh-";
            }else{
                message+="eh-";
            }
            message+="0";
            Board.nonPlayerHero.getPhantom().communicateMessage(message);
        }
        */
        if (!Main.isMulitiplayerGame) {
            System.out.println("returning because not a mp game. if mp, this is an error");
            return;
        }
        if(owner.isPhantomControlled)return;//prevent loopback
        try{
            //Thread.sleep(450); //IF WE WANT A DELAY
        }catch(Exception e){
            e.printStackTrace();
        }
        String actor ="c";
        int actorIndex = owner.hand.indexOf(this);
        String target;
        int targetIndex;
        String output;
        if(targetMinion == null && targetHero==null){
            output = actor+"-"+actorIndex+"-"+"n"+"-"+0;
            Phantom.mainPhantom.communicateMessage(output);
            return;
        }
        if(targetMinion != null){
            if(targetMinion.owner==owner){
                target = "fm";
                targetIndex = owner.minions.indexOf(targetMinion);
            }else{
                target = "em";
                targetIndex = owner.opponent.minions.indexOf(targetMinion);
            }
            output = actor+"-"+actorIndex+"-"+target+"-"+targetIndex;
            Phantom.mainPhantom.communicateMessage(output);
            return;
        }
        if(targetHero!= null){
            if(targetHero == owner){
                target = "fh";
                targetIndex =0;
            }else{
                target = "eh";
                targetIndex = 0;
            }
            output = actor+"-"+actorIndex+"-"+target+"-"+targetIndex;
            Phantom.mainPhantom.communicateMessage(output);
            return;
        }
    }
    
    @Override
    public String toString(){
        return "Card " + name;
    }
    public void setHero(Hero h){
        owner = h;
        if(summon != null)summon.owner = h;
    }
    public Hero getOwner(){
        return owner;
    }

    @Override
    public int compareTo(Object o) {
        Card c = (Card) o;
        Integer myVal;
        Integer theirVal;
        if (cost != 0) {
            myVal = AI.getValueOfCard(this) / this.cost;
        } else {
            myVal = AI.getValueOfCard(this);
        }
        if (c.cost != 0) {
            theirVal = AI.getValueOfCard(c) / c.cost;
        } else {
            theirVal = AI.getValueOfCard(c);
        }
        return myVal.compareTo(theirVal);
    }
    /**
     * returns a list of all cards in the game
     * @return 
     */
    public static ArrayList<Card> getCardList(){
        ArrayList<Card> output = new ArrayList<>();
        output.add(new ArakkoaCard());
        output.add(new ArcherCard());
        output.add(new FireBoltCard());
        output.add(new FireSpearCard());
        output.add(new FrostBearCard());
        output.add(new FrostDragonCard());
        output.add(new KelpieCard());
        output.add(new KnightCard());
        output.add(new PaladinCard());
        output.add(new SpellBookCard());
        output.add(new VengefullKnightCard());
        output.add(new VolcanoCard());
        output.add(new TornadoCard());
        output.add(new UndyingSoldierCard());
        output.add(new SpearmanCard());
        output.add(new MinotaurCard());
        output.add(new AncientDefenderCard());
        
        output.add(new FishermanCard());
        output.add(new BaitfishCard());
        output.add(new PredatoryFishCard());
        output.add(new CarnifishCard());
        output.add(new FrenzyCard());
        output.add(new JellyfishCard());
        output.add(new PirranahCard());
        output.add(new PredationCard());
        output.add(new SeaSerpentCard());
        output.add(new SeaWitchCard());
        output.add(new SwollowCard());
        output.add(new SeaSerpentTrapCard());
        output.add(new ThrasherCard());
        output.add(new UnderSeaMantisCard());
              
        output.add(new SkelemancerCard());
        output.add(new SkeletonArmySpell());
        output.add(new SkullKingCard());
        output.add(new ZombieCard());
        output.add(new ZombieBiteSpell());
        output.add(new ZombieTrapCard());
        output.add(new GhoulCard());
        output.add(new NecromancyCard());
        output.add(new WraithCard());
        
        output.add(new GrayDrakeCard());
        output.add(new FirePlumeCard());
        output.add(new VolcanicDrakeCard());
        output.add(new DragonBreathCard());
        output.add(new DragonSoulTrapCard());
        
        output.add(new FireyWhelpCard());
        output.add(new ApocalypseCard());
        output.add(new SnipeTrapCard());
        output.add(new DoubleshotCard());
        output.add(new GriffonCard());
        output.add(new CavalryGeneralCard());
        output.add(new EnchantedSwordCard());
        
        output.add(new StoneGolemCard());
        output.add(new WarGolemCard());
        output.add(new GeomancerCard());
        output.add(new SorcererCard());
        output.add(new FireInfusionSpell());
        output.add(new EarthInfusionSpell());
        output.add(new IceInfusionSpell());
        output.add(new SandElementalCard());
        output.add(new WaterElementalCard());
        
        output.add(new FireElementalCard());
        output.add(new IceElementalCard());
        output.add(new EarthElementalCard());
        output.add(new StoneElementalCard());
        output.add(new IceyWindCard());
        return output;
    }
    
}
