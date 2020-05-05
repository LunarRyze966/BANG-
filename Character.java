/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bang;

/**
 *
 * @author Grant Henderson R11314979
 */

public abstract class Character {
    
    public int HP = 0;
    public String name = "";
    public String abilityDescript ="";
    
    public String getAbilityDescript()
    {
        return this.abilityDescript;
    }
    public int getHP()
    {
        return this.HP;
    }
    public String name()
    {
        return this.name;
    }
}
class BlackJack extends Character{
    public BlackJack(){
        HP = 8;
        name = "Black Jack";
        abilityDescript = "You may re-roll dynamite <i>(not if you roll three or more!).If  you  roll  three  or  more  Dynamite  at  once  (or  in  total  if  you  didn’t  re-roll  them),  follow  the  usual  rules (your turn ends, etc.)</i>.";
    }
}
class JesseJones extends Character{
    public JesseJones(){
        HP = 9;
        name = "Jesse Jones";
        abilityDescript="If you have 4 life points or less, you gain two if you use a beer on yourself <i>For example if you have four life points and use two beers you get four life points</i>";
    }
}
class Jourdannais extends Character{
    public Jourdannais(){
        HP = 7;
        name = "Jourdannais";
        abilityDescript="You never lose more than 1 life points to indians";
    }
}
class PaulRegret extends Character{
    public PaulRegret(){
       HP = 9;
       name = "Paul Regret";
       abilityDescript = "You never lose life points to the Gatling Gun";
    }
    
}
class SuzyLafayette extends Character{
    public SuzyLafayette(){
        HP = 8;
        name = "Suzy Lafayette";
        abilityDescript = "if you didn't roll any ShootFirst or ShootSecond you gain two life points. <i>This only applies at the end of your turn, not during the re-rolls.</i>";
    }
    
}
class VultureSam extends Character{
    public VultureSam(){
        HP = 9;
        name = "Vulture Sam";
        abilityDescript = "Each time another player is eliminated you gain 2 life points";
    }
}
class BelleStar extends Character{
    public BelleStar(){
        HP = 8;
        name = "Belle Star";
        abilityDescript = "After each of your dice rolls you can change one dynamite to a gatling gun (not if you rolled three dynamite!)";
    }
}
class GregDigger extends Character{
    public GregDigger(){
        HP = 7;
        name = "Greg Digger";
        abilityDescript ="You may use each Whiskey you roll twice";
    }
    
}
class ApacheKid extends Character{
    public ApacheKid(){
        HP = 9;
        name = "Apache Kid";
        abilityDescript = "If you roll an arrow, you may take the indian Chief's arrow from another player.";
    }
}
class BillNoface extends Character{
    public BillNoface(){
        HP = 9;
        name = "Bill Noface";
        abilityDescript = "Apply arrow results only after your last roll. <i>Your last roll isn’t necessarily the third one, you may stop earlier, as normal.</i>";
    }
}

