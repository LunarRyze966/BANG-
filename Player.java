/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bang;

import java.util.*;


public class Player {
    
    private Character character;
    private int currentHP;
    private int maxHP;
    private String role;
    private int arrows;
    private ArrayList<DuelWound> duelWounds;
    private boolean isZombie;
    private boolean chiefArrow;
    
    public Player()
    {
        character = new BlackJack();
        currentHP = 8;
        maxHP = 8;
        role = "Sheriff";
        arrows = 0;
        duelWounds = null;
        isZombie = false;
        chiefArrow = false;
    }
    public Player(String role)
    {
        this.role = role;
    }
    /*
    public Player(Character character, String role)
    {
        this.role = role;
        this.maxHP = character.getHP();
    }
    */
    public Character character()
    {
        return this.character;
    }
    public String role()
    {
        return this.role;
    }
    public int arrows()
    {
        return this.arrows;
    }
    public boolean hasChiefArrow()
    {
        if(this.chiefArrow == true)
        {
            return true;
        }
        else return false;
    }
    public int currentHP()
    {
        return this.currentHP;
    }
    public int maxHP()
    {
        return this.maxHP;
    }
    public ArrayList<DuelWound> getDuelWounds()
    {
        return this.duelWounds;
    }
    public void addDuelWound(DuelWound token)
    {
        this.duelWounds.add(token);
    }
    public void setCharacter(Character character)
    {
        this.character = character;
    }
    public void setMaxHP(int maxHP)
    {
        this.maxHP = maxHP;
    }
    public void setCurrentHP(int hp)
    {
        this.currentHP = hp;
    }
    public void setSheriff()
    {
        this.maxHP += 2;
        this.currentHP = this.maxHP;
    }
    public void setRole(String role)
    {
        this.role = role;
    }
    public void setArrows(int arrows)
    {
        this.arrows = arrows;
    }
    public void addArrows(int arrows)
    {
        this.arrows += arrows;
    }
    public void removeArrows(int arrows)
    {
        this.arrows -= arrows;
    }
    public void heal(int heal)
    {
        this.currentHP += heal;
        if(this.currentHP > this.maxHP)
        {
            this.currentHP = this.maxHP;
        }
        return;
    }
    public void takeDamage(int damage)
    {
        this.currentHP -= damage;
        if(currentHP < 0)
        {
            currentHP = 0;
        }
    }
    public void getChiefArrow()
    {
        this.chiefArrow = true;
    }
    public void removeChiefArrow()
    {
        this.chiefArrow = false;
    }
    public  DuelWound returnDuelWound()
    {
        DuelWound temp = this.duelWounds.get(0);
        this.duelWounds.remove(0);
        return temp;
    }
    public boolean isZombie()
    {
        return isZombie;
    }
    public void setZombie()
    {
        this.isZombie = true;
        
    }
    public boolean isDead()
    {
        if(currentHP == 0)
        {
            return true;
        }
        else return false;
    }
  
    public boolean isAlive()
    {
        if(currentHP > 0)
        {
            return true;
        }
        else return false;
    }
    
}


