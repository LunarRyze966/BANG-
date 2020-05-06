/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bang;

import java.util.*;
/**
 *
 * @author ThatP
 */
public class Pile {
    private int arrows;
    private int chiefArrow;
    private static ArrayList<DuelWound> duelWounds = new ArrayList<DuelWound>();
    private static ArrayList<BoneyardCard> boneyardCards = new ArrayList<BoneyardCard>();
    private int totalHands;
    
    
    public Pile()
    {
        arrows = 9;
        chiefArrow = 1;
        
        
        duelWounds.add(new DuelWound("Beer"));
        duelWounds.add(new DuelWound("Beer"));
        duelWounds.add(new DuelWound("Beer"));
        duelWounds.add(new DuelWound("Bull's Eye 1"));
        duelWounds.add(new DuelWound("Bull's Eye 1"));
        duelWounds.add(new DuelWound("Bull's Eye 1"));
        duelWounds.add(new DuelWound("Bull's Eye 1"));
        duelWounds.add(new DuelWound("Bull's Eye 1"));
        duelWounds.add(new DuelWound("Bull's Eye 2"));
        duelWounds.add(new DuelWound("Bull's Eye 2"));
        duelWounds.add(new DuelWound("Bull's Eye 2"));
        duelWounds.add(new DuelWound("Bull's Eye 2"));
        duelWounds.add(new DuelWound("Bull's Eye 2"));
        duelWounds.add(new DuelWound("Dynamite"));
        duelWounds.add(new DuelWound("Dynamite"));
        
        
        boneyardCards.add(new BoneyardCard(0));
        boneyardCards.add(new BoneyardCard(0));
        boneyardCards.add(new BoneyardCard(0));
        boneyardCards.add(new BoneyardCard(0));
        boneyardCards.add(new BoneyardCard(1));
        boneyardCards.add(new BoneyardCard(1));
        boneyardCards.add(new BoneyardCard(1));
        boneyardCards.add(new BoneyardCard(1));
        boneyardCards.add(new BoneyardCard(2));
        boneyardCards.add(new BoneyardCard(2));
        boneyardCards.add(new BoneyardCard(2));
        
        Collections.shuffle(boneyardCards);
        Collections.shuffle(duelWounds);
        
    }
    
    public int arrows()
    {
        return this.arrows;
    }
    public boolean hasChiefArrow()
    {
        if(chiefArrow == 1)
        {
            return true;
        }
        else return false;
    }
    public void takeArrow(int arrows)
    {
        this.arrows -= arrows;
    }
    public void returnArrow(int arrows)
    {
        this.arrows += arrows;
    }
    public void setArrows(int arrows)
    {
        this.arrows = arrows;
    }
    public DuelWound drawDuelWound()
    {
        DuelWound temp = this.duelWounds.get(0);
        this.duelWounds.remove(0);
        return temp;
    }
    public void returnDuelWound(DuelWound token)
    {
        this.duelWounds.add(token);
    }
    public BoneyardCard drawBoneyardCard()
    {
        BoneyardCard temp = this.boneyardCards.get(0);
        this.totalHands += temp.hands();
        //if it is a hand card it stays out
        if(temp.hands() > 0)
        {
            this.boneyardCards.remove(0);
        }
        //otherwise it is shuffled back into the deck
        else
        {
            Collections.shuffle(boneyardCards);
        }
        return temp;
    }
}
