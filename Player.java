/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bang;

/**
 *
 * @author ThatP
 */
public class Player {
    
    private Character character;
    private int currentHP;
    private int maxHP;
    private String role;
    private int arrows;
    private boolean isUser = false;
    
    public Player()
    {
        
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
    public int currentHP()
    {
        return this.currentHP;
    }
    public int maxHP()
    {
        return this.maxHP;
    }
    public boolean isUser()
    {
        return this.isUser;
    }
    public void setCharacter(Character character)
    {
        this.character = character;
    }
    public void setSheriff()
    {
        this.maxHP += 2;
    }
    public void setRole(String role)
    {
        this.role = role;
    }
    public void setUser()
    {
        this.isUser = true;
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
    
    
}


