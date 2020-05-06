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
    //static int arrowPile = 9;
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
        
        //Instantiates are possible characters
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
        
        //instantiates possible roles they are instantiated in the order in which they are added per player amount
        players.add(new Player("sheriff"));
        players.add(new Player("renegade"));
        players.add(new Player("outlaw"));
        players.add(new Player("outlaw"));
        players.add(new Player("deputy"));
        players.add(new Player("outlaw"));
        players.add(new Player("deputy"));
        
       
        //shuffle the characters then go through and give a character to each player
        Collections.shuffle(characters);
        i = 0;
        for(i = 0 ; i < players.size(); i++)
        {
            players.get(i).setCharacter(characters.get(i));
        }
        
        //Select one of the players to be the user
        int rand = Math.abs((random.nextInt())%players.size());
        pc = players.get(rand);
        
      
        //set players maxHP
        for(Player player : players)
        {
            player.setMaxHP(player.character().getHP());
            player.setCurrentHP(player.maxHP());
        }
         
        //shuffle the order of players
        Collections.shuffle(players);
        
        //set the sheriff as the first player and increase his health by 2
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
    public static void beginTurn(Player player, Die[] dice)
    {
        if(player.isZombie())
        {
            pile.drawBoneyardCard();
        }
        rollAll(dice);
    }
    public static void reRoll(Player player, Die[] dice)
    {
        //System.out.println("Do you want to re-roll the dice?");
    }
    //interperets rolls between re-rolls
    public static void interpretRoll(Player player, Die[] dice)
    {
       takeArrows(player, dice);
       returnArrows(player,dice);
       bulletCheck(player,dice);
       dynamiteCheck(player, dice);
    }
    //interperets the final roll of the turn
    public static void interpretFinalRoll(Player player, Die[] dice)
    {
       takeArrows(player, dice);
       returnArrows(player,dice);
       bulletCheck(player,dice);
       whiskeyCheck(player,dice);
       shootCheck(player, dice);
       beerCheck(player, dice);
       gatlingGunCheck(player,dice);
       duelCheck(player, dice);
    }
    //rolls all the dice
    public static void rollAll(Die[] dice)
    {
        for(Die die: dice)
        {
            die.setFaceValue(die.basicRoll());
        }
    }
    //prints the dice values for debugging
    public static void printDiceValues(Die[] dice)
    {
        for(Die die: dice)
        {
            System.out.print(die.FaceValue());
            if(die.FaceValue() == "Dynamite")
            {
                System.out.println(": Cannot re-roll");
            }
            else
            System.out.println("");
        }
    }
    //a player(arg1) takes as many arrows that occur on the dice(arg2)
    public static void takeArrows(Player player, Die[] dice)
    {
        for(int i = 0; i< dice.length; i++)
        {
            if(dice[i].FaceValue() == "Arrow")
            {
                player.addArrows(1);
                System.out.println(player.character().name+ " draws an arrow");
                pile.takeArrow(1);//arrowPile--;
                if(pile.arrows() == 0)
                {
                     indianAttack();
                }
            }
        }
 
    }
    //a player(arg1) returns as many arrows that occur on the dice(arg2)
    public static void returnArrows(Player player, Die[] dice)
    {
        for(int i = 0; i< dice.length; i++)
        {
            if(dice[i].FaceValue() == "Broken Arrow")
            {
                if(player.arrows() > 0)
                {
                    player.removeArrows(1);
                    System.out.println(player.character().name+ " returns an arrow");
                    pile.returnArrow(1);
                }
            }
        }
    }
    //a player(arg1) checks to see how many times he takes damage from bullets roled in arg2
    public static void bulletCheck(Player player, Die[] dice)
    {
        for(int i = 0; i< dice.length; i++)
        {
            if(dice[i].FaceValue() == "Bullet")
            {
                player.takeDamage(1);
                System.out.println(player.character().name+ " takes 1 damage");
            }
        }
    }
    //indian attack triggered by the removal of all arrows from the pile
    public static void indianAttack()
    {
        System.out.println("Indian Attack!");
        //safe player is the player that holds the chief arrow, and has the most arrows
        Player safePlayer = null;
        //checks to see if anybody has the most arrows
        if(mostArrows() != null)
        {
            safePlayer = mostArrows();
        }
        //for each player
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
                if(player.hasChiefArrow())
                {
                    player.takeDamage(2);
                }
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
        pile.setArrows(9);
    }
    //gatling gun attack triggered by a player with three gatling guns
    //arg 'gunner' is the activating player and takes no damage
    public static void gatlingGun(Player gunner)
    {
        //each player takes damage
        for(Player player: players)
        {
            //if you are the shooter or you are paul regret, take no damage
            if(player != gunner || player.character().name() != "Pail Regret")
            {
                player.takeDamage(1);
            }
        }
        pile.returnArrow(gunner.arrows());
        gunner.setArrows(0);
    }
    //displays the players
    public static void displayPlayers()
    {
        for(Player player : players)
        {
            System.out.println(player.role() + ": " + player.character().name());
        }
    }
    //checks to see how much dynamite player(arg1) rolled with (arg2)
    public static void dynamiteCheck(Player player, Die[] dice)
    {
        int count = 0;
        for(Die die : dice)
        {
            if(die.FaceValue() == "Dynamite")
            {
                count++;
            }
        }
        if(count >=3)
        {
            //if you rolled three dynamite, your turn is over and you take 1 damage
            System.out.println("Dynamite ends your turn and you take 1 damage");
            player.takeDamage(1);
            endTurn(player, dice);
        }
    }
    //Handles the end of turn events such as interpeting the final roll and advancing the turn order.
    public static void endTurn(Player player, Die[] dice)
    {
        interpretFinalRoll(player, dice);
        turn = (turn +1)%players.size();
        
    }
    //checks the amount of whiskey bottles rolled by a player
    public static void whiskeyCheck(Player player, Die[] dice)
    {
        for(Die die: dice)
        {
            if(die.FaceValue() == "Whiskey Bottle")
            {
               player.heal(1);
               //if the player has duel wounds then remove one
               if(player.getDuelWounds() != null)
               {
                   pile.returnDuelWound(player.returnDuelWound());
               }
            }
        }
    }
    //checks the amount of Beers rolled by a player
    public static void beerCheck(Player player, Die[] dice)
    {
        //if the player has wounds of type 'Beer' then the wound cancels out a single Beer
        int beerWounds = 0;
        if(player.getDuelWounds() != null)
        {
            for(DuelWound wound : player.getDuelWounds())
            {
                if(wound.type() == "Beer")
                {
                    beerWounds++;
                }
            }
        }
        
        for(Die die: dice)
        {
            if(die.FaceValue() == "Beer" && beerWounds == 0)
            {
                if(player == pc)
                {
                    beerMenu(1);
                    if(player.character().name() == "Greg Digger")
                    {
                        beerMenu(1);
                    }
                }
            }
            //remove a drink wound if the player had a drink wound and rolled a drink
            else if(die.FaceValue() == "Beer")
            {
                beerWounds--;
            }
        }
    }
    //check to see how many shots a player rolled
    public static void shootCheck(Player player, Die[] dice)
    {
        boolean suzyBonus = true;  //used to check if the player rolled no shots this turn for Suzy Lafayettes special ability
        for(Die die: dice)
        {
            if(die.FaceValue() == "Bull's Eye 1")
            {
                suzyBonus = false;
                if(player == pc)
                {
                    shootMenu(1);
                }
            }
            else if(die.FaceValue() == "Bull's Eye 2")
            {
                suzyBonus = false;
                if(player == pc)
                {
                    shootMenu(2);
                }
            }
        }
        //if there were no shoot rolls and the current player is Suzy
        if(player.character().name() == "Suzy Lafayette" && suzyBonus)
        {
            player.heal(2);
        }
    }
    //checks to see if three gatling guns have been rolled on a player turn
    public static void gatlingGunCheck(Player player, Die[] dice)
    {
        int count = 0;
        for(Die die: dice)
        {
            //Add one to count for gatling and 2 for double gatling
            if(die.FaceValue() == "Gatling")
            {
                count++;
            }
            else if(die.FaceValue() == "Double Gatling")
            {
                count += 2;
            }
        }
        //if there were more than three gatlings then trigger the gatling gun attack
        if(count >= 3)
        {
            gatlingGun(player);
        }
    }
    //check to see how many duels a player rolled on their turn
    public static void duelCheck(Player player, Die[] dice)
    {
        for(Die die: dice)
        {
            if(die.FaceValue() == "Fight a Duel")
            {
                System.out.println("Duel Rolled");
            }
        }
    }
    //displays the available targets for the shot rolls
    public static void shootMenu(int shootValue)
    {
        System.out.println("Available targets");
        System.out.println(availableTargetRight(activePlayer(), shootValue).character().name());
        System.out.println(availableTargetLeft(activePlayer(), shootValue).character().name());
    }
    //finds the player with the most arrows if one exists
    public static Player mostArrows()
    {
        //select the first player
        Player mostArrows = players.get(0);
        boolean hasMost = true;     //checks to see if a player has more than other players since equal doesnt count
        for(int i = 0; i < players.size() ; i++)
        {
            //assign the player with the most arrows to mostArrows
            if(players.get(i).arrows() > mostArrows.arrows())
            {
                mostArrows = players.get(i);
            }
        }
        for(int i = 0; i < players.size() ; i++)
        {
            //go back through the list to make sure the player with the most arrows is the only one with that many
            if(mostArrows.arrows() == players.get(i).arrows())
            {
                hasMost = false;
            }
        }
        //if there is a player with the most and this player has the chief arrow then return the player
        if(hasMost && mostArrows().hasChiefArrow())
        {
            return mostArrows;
        }
        //otherwise there is no player with the most arrows
        else{
            return null;
        }
        
    }
    public static void beerMenu(int beer)
    {
        System.out.println("Who do you want to heal?");
        healTarget(activePlayer(), beer);
    }
    //returns who the active player in the turn order is
    public static Player activePlayer()
    {
        return players.get(turn);
    }
    //heals a target player by 'heal' amount
    public static void healTarget(Player player,int heal)
    {
        //Jesse Jones special Ability(only works if he uses the drink on himself
        if(player == activePlayer() && player.character().name() == "Jesse Jones" && player.currentHP() <= 4)
        {
           player.heal(heal*2);
        }
        player.heal(heal);
    }
    //deals 'damage' amount of damage to a player
    public static void damageTarget(Player player, int damage)
    {
        player.takeDamage(damage);
        if(player.currentHP() == 0)
        {
            playerDies(player);
        }
    }
    //a function to determine what happens when a player dies
    public static void playerDies(Player deceased){
        //find and heal vulture sam if he is alive
        for(Player player: players)
        {
            if(player.character().name() == "Vulture Sam" && player.isAlive())
            {
                player.heal(2);
            }
        }
        //win conditions need only be checked when a player dies
        checkWinConditions();
    }
    //finds an available target to the right of a player, distance determines whether the search starts 1 or more to the right.
    public static Player availableTargetRight(Player player, int distance)
    {
        int index = players.indexOf(player);
        //if the player is alive, then he is a valid target
        if(players.get((index + distance)%players.size()).isAlive())
        {
            return players.get((index+distance)%players.size());
        }
        //if he is dead then check the player to his right
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
    //checks to see if a player has won the game
    public static void checkWinConditions()
    {
    }
    //returns the player to the right of a player
    public static Player nextPlayer(Player player)
    {
       return players.get((players.indexOf(player)+1)%players.size());
    }
    //returns the player to the left of a player
    public static Player previousPlayer(Player player)
    {
       return players.get(Math.floorMod((players.indexOf(player))-1,players.size()));
    }
}
