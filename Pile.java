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
    public static ArrayList<DuelWound> duelWounds = new ArrayList<DuelWound>();
    
    
    public Pile()
    {
        arrows = 9;
        chiefArrow = 1;
        
        
        duelWounds.add(new DuelWound("Drink"));
        duelWounds.add(new DuelWound("Drink"));
        duelWounds.add(new DuelWound("Drink"));
        duelWounds.add(new DuelWound("ShootFirst"));
        duelWounds.add(new DuelWound("ShootFirst"));
        duelWounds.add(new DuelWound("ShootFirst"));
        duelWounds.add(new DuelWound("ShootFirst"));
        duelWounds.add(new DuelWound("ShootFirst"));
        duelWounds.add(new DuelWound("ShootSecond"));
        duelWounds.add(new DuelWound("ShootSecond"));
        duelWounds.add(new DuelWound("ShootSecond"));
        duelWounds.add(new DuelWound("ShootSecond"));
        duelWounds.add(new DuelWound("ShootSecond"));
        duelWounds.add(new DuelWound("Dynamite"));
        duelWounds.add(new DuelWound("Dynamite"));
        
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
}
