/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bang;

import java.util.*;
import java.lang.Math; 

public class BANG {
    
    static Pile pile = new Pile();
    static int arrowPile = 9;
    static int turn = 0;
    static Player pc;
    static Player currPlayer;
    static ArrayList<Character> characters = new ArrayList<Character>();
    static ArrayList<Player> players = new ArrayList<Player>();
    
    public static void main(String[] args) {
        
        int i;
        Die[] dice = {new Die(), new Die(), new Die(), new Die(), new Die()};
        Random random = new Random();
        
        
        //ArrayList<Character> characters = new ArrayList<Character>();
        
        characters.add(new BlackJack());
        characters.add(new JesseJones());
        characters.add(new PaulRegret());
        characters.add(new SuzyLafayette());
        characters.add(new VultureSam());
        characters.add(new Jourdannais());
        characters.add(new BelleStar());
        characters.add(new GregDigger());
        characters.add(new ApacheKid());
        characters.add(new BillNoface()); 
                
        
        //ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("sheriff"));
        players.add(new Player("renegade"));
        players.add(new Player("outlaw"));
        players.add(new Player("outlaw"));
        players.add(new Player("deputy"));
        players.add(new Player("outlaw"));
        players.add(new Player("deputy"));
        
       
        
        Collections.shuffle(characters);
        i = 0;
        for(i = 0 ; i < players.size(); i++)
        {
            players.get(i).setCharacter(characters.get(i));
        }
        int rand = Math.abs((random.nextInt())%players.size());
        
        //Select one of the players to be the user
        pc = players.get(rand);
        
      
        //set players maxHP
        for(Player player : players)
        {
            player.setMaxHP(player.character().getHP());
            player.setCurrentHP(player.maxHP());
        }
         
        //shuffle the order of players
        Collections.shuffle(players);
        
        //set the sheriff as the first player
        for(i = 0; i< players.size(); i++)
        {
            if (players.get(i).role() == "sheriff")
            {
                players.get(i).setSheriff();
                pc = players.get(i);
                turn = i;
            }
        }
       
        System.out.println("You are playing as: " + pc.character().name());
        
        displayPlayers();
        rollAll(dice);
        printDiceValues(dice);
        interpretFinalRoll(activePlayer(), dice);
      
        
        /*
        Scanner scan = new Scanner(System.in);
        
        System.out.println("How many player do you want to play with(3-7)?");
        int input = scan.nextInt();
        
        int playerCount = input;
        System.out.println("Shuffling players");
        */
        
        //Player user = new Player();

    }
    public static void reRoll(Player player, Die[] dice)
    {
        //System.out.println("Do you want to re-roll the dice?");
    }
    public static void interpretRoll(Player player, Die[] dice)
    {
       takeArrows(player, dice);
       returnArrows(player,dice);
       dynamiteCheck(player, dice);
    }
    public static void interpretFinalRoll(Player player, Die[] dice)
    {
       takeArrows(player, dice);
       returnArrows(player,dice);
       whiskeyCheck(player,dice);
       shootCheck(player, dice);
       beerCheck(player, dice);
       gatlingGunCheck(player,dice);
       duelCheck(player, dice);
    }
    public static void rollAll(Die[] dice)
    {
        for(Die die: dice)
        {
            die.setFaceValue(die.roll());
        }
    }
    public static void printDiceValues(Die[] dice)
    {
        for(Die die: dice)
        {
            System.out.print(die.getFaceValue());
            if(die.getFaceValue() == "Dynamite")
            {
                System.out.println(": Cannot re-roll");
            }
            else
            System.out.println("");
        }
    }
    public static void takeArrows(Player player, Die[] dice)
    {
        for(int i = 0; i< dice.length; i++)
        {
            if(dice[i].getFaceValue() == "Arrow")
            {
                player.addArrows(1);
                System.out.println(player.character().name+ " draws an arrow");
                arrowPile--;
                if(arrowPile == 0)
                {
                     indianAttack();
                }
            }
        }
 
    }
    public static void returnArrows(Player player, Die[] dice)
    {
        for(int i = 0; i< dice.length; i++)
        {
            if(dice[i].getFaceValue() == "Broken Arrow")
            {
                if(player.arrows() > 0)
                {
                    player.removeArrows(1);
                    System.out.println(player.character().name+ " returns an arrow");
                    arrowPile++;
                }
            }
        }
    }
    public static void indianAttack()
    {
        System.out.println("Indian Attack!");
        Player safePlayer = null;
        if(mostArrows() != null)
        {
            safePlayer = mostArrows();
        }
        for(Player player: players)
        {
            //if you have the indian chiefs arrows and most arrows
            if(player == safePlayer)
            {
                
            }
            //if you aren't Jourdannias
            else if(player.character().name() == "Jourdannias")
            {
                player.takeDamage(player.arrows());
            }
            //if you are Jourdannias
            else if(player.character().name() == "Jourdannias")
            {
                //if your jourdannias and you have at least 1 arrow
                if(player.arrows() > 0)
                {
                    player.takeDamage(1);
                }
            }
        }
        //reset the arrowpile
        arrowPile = 9;
    }
    public static void gatlingGun(Player gunner)
    {
        for(Player player: players)
        {
            //if you are the shooter or you are paal regret, take no damage
            if(player != gunner || player.character().name() != "Pail Regret")
            {
                player.takeDamage(1);
            }
        }
        arrowPile += gunner.arrows();
        gunner.setArrows(0);
    }
    public static void displayPlayers()
    {
        for(Player player : players)
        {
            System.out.println(player.role() + ": " + player.character().name());
        }
    }
    public static void dynamiteCheck(Player player, Die[] dice)
    {
        int count = 0;
        for(Die die : dice)
        {
            if(die.getFaceValue() == "Dynamite")
            {
                count++;
            }
        }
        if(count >=3)
        {
            System.out.println("Dynamite ends your turn and you take 1 damage");
            player.takeDamage(1);
            endTurn(player, dice);
        }
    }
    public static void endTurn(Player player, Die[] dice)
    {
        interpretFinalRoll(player, dice);
        turn = (turn +1)%players.size();
        
    }
    public static void whiskeyCheck(Player player, Die[] dice)
    {
        for(Die die: dice)
        {
            if(die.getFaceValue() == "Whiskey Bottle")
            {
               player.heal(1);
               if(player.getDuelWounds() != null)
               {
                   pile.returnDuelWound(player.returnDuelWound());
               }
            }
        }
    }
    public static void beerCheck(Player player, Die[] dice)
    {
        int drinkWounds = 0;
        if(player.getDuelWounds() != null)
        {
            for(DuelWound wound : player.getDuelWounds())
            {
                if(wound.type() == "drink")
                {
                drinkWounds++;
                }
            }
        }
        
        for(Die die: dice)
        {
            if(die.getFaceValue() == "Drink" && drinkWounds == 0)
            {
                if(player == pc)
                {
                    drinkMenu(1);
                    if(player.character().name() == "Greg Digger")
                    {
                        drinkMenu(1);
                    }
                }
            }
            else
            {
                drinkWounds--;
            }
        }
    }
    public static void shootCheck(Player player, Die[] dice)
    {
        boolean suzyBonus = true;
        for(Die die: dice)
        {
            if(die.getFaceValue() == "ShootFirst")
            {
                suzyBonus = false;
                if(player == pc)
                {
                    shootMenu(1);
                }
            }
            else if(die.getFaceValue() == "ShootSecond")
            {
                suzyBonus = false;
                if(player == pc)
                {
                    shootMenu(2);
                }
            }
        }
        //if there were no shoot rolls and the current player is Suzy
        if(activePlayer().character().name() == "Suzy Lafayette" && suzyBonus)
        {
            activePlayer().heal(2);
        }
    }
    public static void gatlingGunCheck(Player player, Die[] dice)
    {
        int count = 0;
        for(Die die: dice)
        {
            if(die.getFaceValue() == "Gatling")
            {
                count++;
            }
            else if(die.getFaceValue() == "Double Gatling")
            {
                count += 2;
            }
        }
        if(count >= 3)
        {
            gatlingGun(player);
        }
    }
    public static void duelCheck(Player player, Die[] dice)
    {
        for(Die die: dice)
        {
            if(die.getFaceValue() == "Fight a Duel")
            {
                System.out.println("Duel Rolled");
            }
        }
    }
    public static void shootMenu(int shootValue)
    {
        System.out.println("Available targets");
        System.out.println(availableTargetRight(activePlayer(), shootValue).character().name());
        System.out.println(availableTargetLeft(activePlayer(), shootValue).character().name());
    }
    public static Player mostArrows()
    {
        Player mostArrows = players.get(0);
        boolean hasMost = true;
        for(int i = 0; i < players.size() ; i++)
        {
            if(players.get(i).arrows() > mostArrows.arrows())
            {
                mostArrows = players.get(i);
            }
            else if(mostArrows.arrows() == players.get(i).arrows())
            {
                hasMost = false;
            }
        }
        if(hasMost && mostArrows().hasChiefArrow())
        {
            return mostArrows;
        }
        else{
            return null;
        }
        
    }
    public static void drinkMenu(int drink)
    {
        System.out.println("Who do you want to heal?");
        healTarget(activePlayer(), drink);
    }
    public static Player activePlayer()
    {
        return players.get(turn);
    }
    public static void healTarget(Player player,int heal)
    {
        //Jesse Jones special Ability
        if(player == activePlayer() && player.character().name() == "Jesse Jones" && player.currentHP() <= 4)
        {
           player.heal(heal*2);
        }
        player.heal(heal);
    }
    public static void damageTarget(Player player, int damage)
    {
        player.takeDamage(damage);
        if(player.currentHP() == 0)
        {
            playerDies(player);
        }
    }
    public static void playerDies(Player deceased){
        //find and heal vulture sam if he is alive
        for(Player player: players)
        {
            if(player.character().name() == "Vulture Sam" && player.isAlive())
            {
                player.heal(2);
            }
        }
        checkWinConditions();
    }
    public static Player availableTargetRight(Player player, int distance)
    {
        int index = players.indexOf(player);
        if(players.get((index + distance)%players.size()).isAlive())
        {
            return players.get((index+distance)%players.size());
        }
        else
        {
            return availableTargetRight(nextPlayer(player), 1);
        }
    }
    public static Player availableTargetLeft(Player player, int distance)
    {
        int index = players.indexOf(player);
        if(players.get(Math.floorMod(index - distance,players.size())).isAlive())
        {
            return players.get(Math.floorMod(index - distance,players.size()));
        }
        else
        {
            return availableTargetRight(previousPlayer(player), 1);
        }
    }
    public static void checkWinConditions()
    {
    }
    public static Player nextPlayer(Player player)
    {
       return players.get((players.indexOf(player)+1)%players.size());
    }
    public static Player previousPlayer(Player player)
    {
       return players.get(Math.floorMod((players.indexOf(player))-1,players.size()));
    }
}
