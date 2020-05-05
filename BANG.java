/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bang;

import java.util.*;

public class BANG {
    
   
    static int arrowPile = 9;
    static int turn = 0;
    static Player pc;
    static Player currPlayer;
    //, new , new , new , new , new , new , new , new };
    //Player[] players = {new , new ,new , new , new , new , new };
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
        
        
        //shuffle the order of players
        Collections.shuffle(players);
        
        //set the sheriff as the first player
        for(i = 0; i< players.size(); i++)
        {
            if (players.get(i).role() == "sheriff")
            {
                players.get(i).setSheriff();
                turn = i;
            }
        }
       
        System.out.println("You are playing as: " + pc.character().name());
        rollAll(dice);
        printDiceValues(dice);
        interpretRoll(players.get(turn), dice);
        displayPlayers();
        
   
        
        
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
       dynamiteCheck(player, dice);
    }
    public static void interpretFinalRoll(Player player, Die[] dice)
    {
       takeArrows(player, dice);
       shootCheck(player, dice);
       beerCheck(player, dice);
       gatlingGunCheck(player,dice);
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
            if(player != safePlayer)
            {
            player.takeDamage(player.arrows());
            }
        }
        arrowPile = 9;
    }
    public static void gatlingGun(Player gunner)
    {
        for(Player player: players)
        {
            if(player != gunner)
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
    public static void beerCheck(Player player, Die[] dice)
    {
        for(Die die: dice)
        {
            if(die.getFaceValue() == "Drink")
            {
                if(player == pc)
                {
                    drinkMenu();
                }
            }
        }
    }
    public static void shootCheck(Player player, Die[] dice)
    {
        for(Die die: dice)
        {
            if(die.getFaceValue() == "ShootFirst")
            {
                if(player == pc)
                {
                    shootMenu(1);
                }
            }
            else if(die.getFaceValue() == "ShootSecond")
            {
                if(player == pc)
                {
                    shootMenu(2);
                }
            }
        }
    }
    public static void gatlingGunCheck(Player player, Die[] dice)
    {
        int count = 0;
        for(Die die: dice)
        {
            if(die.getFaceValue() == "GatlingGun")
            {
                count++;
            }
        }
        if(count >= 3)
        {
            gatlingGun(player);
        }
    }
    public static void shootMenu(int shootValue)
    {
        System.out.println("Available targets");
        System.out.println(players.get(turn+shootValue).character().name());
        System.out.println(players.get(turn-shootValue).character().name());
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
    public static void drinkMenu()
    {
        System.out.println("Who do you want to heal?");
    }
    public Player activePlayer()
    {
        return players.get(turn);
    }
}
