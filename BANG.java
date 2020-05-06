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
    static ArrayList<Character> characters = new ArrayList<Character>();
    static ArrayList<Player> players = new ArrayList<Player>();
    static Die[] dice = {new Die(), new Die(), new Die(), new Die(), new Die()};
    static boolean UoAEnabled = false;  //determines if the the undead or alive expansion is enabled or not
    static boolean OTSEnabled = false; //determines if the Old Time Saloon expansion is enabled or not
    static int specialDieEnabled = 0; //0 = no special die // 1 = loudMouth enabled // 2 = cowardEnabled
    static int playerDied = 0; //an int that keeps track of how many players died each turn >0 means someone died
    static boolean gameOver = false;
    
    
    public static void main(String[] args) {
        
        int i;
        
        //ArrayList<Character> characters = new ArrayList<Character>();
        
        //Instantiates a list of possible characters
        characters.add(new BlackJack());
        characters.add(new JesseJones());
        characters.add(new PaulRegret());
        characters.add(new SuzyLafayette());
        characters.add(new VultureSam());
        characters.add(new Jourdannais());
        characters.add(new BelleStar());
        if(UoAEnabled == true)
        {
            
            characters.add(new GregDigger());
        }
        if(OTSEnabled == true)
        {
            characters.add(new ApacheKid());
            characters.add(new BillNoface()); 
        }
                
        
        //ArrayList<Player> players = new ArrayList<Player>();
        
        //instantiates possible roles. They are instantiated in the order in which they are added per player amount
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
        //int rand = Math.abs((random.nextInt())%players.size());
        
        
      
        //set players maxHP
        for(Player player : players)
        {
            player.setMaxHP(player.character().getHP());
            player.setCurrentHP(player.maxHP());
        }
         
        //shuffle the order of players
        Collections.shuffle(players);
        //assign the pc as the first player
        pc = players.get(0);
        //set the sheriff as the first turn and increase his health by 2
        for(i = 0; i< players.size(); i++)
        {
            if (players.get(i).role() == "sheriff")
            {
                players.get(i).setSheriff();
                turn = i;
            }
        }
       
        System.out.println("You are playing as: " + pc.character().name());
        
        displayPlayers();
        //while(gameOver != true)
        //{
            beginTurn(activePlayer());
       //}
        System.out.println("END");
        //rollAll(activePlayer());
        //printDiceValues();
        //interpretFinalRoll(activePlayer());
      
        
        /*
        Scanner scan = new Scanner(System.in);
        
        System.out.println("How many player do you want to play with(3-7)?");
        int input = scan.nextInt();
        
        int playerCount = input;
        System.out.println("Shuffling players");
        */
        
        //Player user = new Player();

    }
    public static void beginTurn(Player player)
    {
        if(UoAEnabled == true)
        {
            if(player.isDead())
            {
                pile.drawBoneyardCard();
            }
        }
        if(OTSEnabled == true)
        {
            replaceDieDecide();
        }
        if(player.isDead())
        {
            advanceTurn();
        }
        rollAll(player);
    }
    public static void replaceDieDecide()
    {   
        //replaceDice();
    }
    public static void replaceDice(char die)
    {
        if(die == 'L')
        {
            specialDieEnabled = 1;
        }
        else if(die == 'C')
        {
             specialDieEnabled = 2;       
        }
    }
    public static void reRoll(Player player)
    {
        //System.out.println("Do you want to re-roll the dice?");
    }
    //interperets rolls between re-rolls
    public static void interpretRoll(Player player)
    {
       takeArrows(player);
       returnArrows(player);
       bulletCheck(player);
       dynamiteCheck(player);
    }
    //interperets the final roll of the turn
    public static void interpretFinalRoll(Player player)
    {
       /*takeArrows(player);
       returnArrows(player);
       bulletCheck(player);*/
       whiskeyCheck(player);
       shootCheck(player);
       beerCheck(player);
       gatlingGunCheck(player);
       duelCheck(player);
    }
    //rolls all the dice
    public static void rollAll(Player player)
    {
        //if undead expansion enabled roll the duel dice, if the OTS expansion is disabled, then specialDieEnabled will always = 0.
        if(UoAEnabled == true)
        {
            int i = 0;
            if(specialDieEnabled == 0)
            { 
                for(i = 0; i<2 ; i++)
                {
                    dice[i].setFaceValue(dice[i].duelRoll());
                }
                for(i = 2; i<dice.length; i++)
                {
                    dice[i].setFaceValue(dice[i].basicRoll());
                }
            }
            //if OTS and UoA is enabled and the player has selected the special die
            else
            {
                for(i = 0; i<2 ; i++)
                {
                    dice[i].setFaceValue(dice[i].duelRoll());
                }
                //if the special dice is enabled roll loudmouth for 1 and coward for 2
                if(specialDieEnabled == 1)
                {
                    dice[2].setFaceValue(dice[2].loudmouthRoll());
                }
                else if(specialDieEnabled == 2)
                {
                    dice[2].setFaceValue(dice[2].cowardRoll());
                }
                for(i = 3; i<dice.length; i++)
                {
                    dice[i].setFaceValue(dice[i].basicRoll());
                }
            }
        }
        //UoA is disabled
        else
        {
            //UoA is disabled and either OTS is disabled or the dice is not being used
            if(specialDieEnabled == 0)
            {
                for(Die die: dice)
                {
                    die.setFaceValue(die.basicRoll());
                }
            }
            //UoA is disabled but the OTS is enabled and a die is being used
            else
            {
                int i = 0;
                if(specialDieEnabled == 1)
                {
                    dice[0].setFaceValue(dice[0].loudmouthRoll());
                }
                else if(specialDieEnabled == 2)
                {
                    dice[0].setFaceValue(dice[0].cowardRoll());
                }
                for(i = 1; i<dice.length; i++)
                {
                    dice[i].setFaceValue(dice[i].basicRoll());
                }
            }
        }
        printDiceValues();
        interpretRoll(player);
        if(isBot(player))
        {
            endTurn(player);
        }
    }
    //prints the dice values for debugging
    public static void printDiceValues()
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
    public static void takeArrows(Player player)
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
    public static void returnArrows(Player player)
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
    public static void bulletCheck(Player player)
    {
        for(int i = 0; i< dice.length; i++)
        {
            if(dice[i].FaceValue() == "Bullet")
            {
                player.takeDamage(1);
                System.out.println(player.character().name+ " takes 1 damage");
                if(player.isDead())
                {
                    playerDies(player);
                }
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
            if(player.isDead())
            {
                playerDies(player);
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
            if(player != gunner || player.character().name() != "Paul Regret")
            {
                player.takeDamage(1);
            }
            if(player.isDead())
            {
                playerDies(player);
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
    public static void dynamiteCheck(Player player)
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
            if(player.isDead())
            {
                playerDies(player);
            }
            endTurn(player);
        }
    }
    //Handles the end of turn events such as interpeting the final roll and advancing the turn order.
    public static void endTurn(Player player)
    {
        interpretFinalRoll(player);
        specialDieEnabled = 0;
        advanceTurn();
        
        
    }
    public static void advanceTurn()
    {
        turn = (turn +1)%players.size();
        beginTurn(activePlayer());;
    }
    //checks the amount of whiskey bottles rolled by a player
    public static void whiskeyCheck(Player player)
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
    public static void beerCheck(Player player)
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
                    drinkMenu(1);
                    if(player.character().name() == "Greg Digger")
                    {
                        drinkMenu(1);
                    }
                }
                else
                {
                    botDrink(player);
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
    public static void shootCheck(Player player)
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
                else
                {
                    botShoot(player,1);
                }
            }
            else if(die.FaceValue() == "Bull's Eye 2")
            {
                suzyBonus = false;
                if(player == pc)
                {
                    shootMenu(2);
                }
                else
                {
                    botShoot(player,2);
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
    public static void gatlingGunCheck(Player player)
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
    public static void duelCheck(Player player)
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
    public static void drinkMenu(int beer)
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
        if(player.isDead())
        {
            return;
        }
        player.heal(heal);
    }
    //deals 'damage' amount of damage to a player
    public static void damageTarget(Player player, int damage)
    {
        player.takeDamage(damage);
        if(player.isDead())
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
        System.out.println(deceased.character().name() + " has died");
        //win conditions need only be checked when a player dies
        checkWinConditions();
    }
    //finds an available target to the right of a player, distance determines whether the search starts 1 or more to the right.
    public static Player availableTargetRight(Player player, int distance)
    {
        int index = (players.indexOf(player) + distance) % players.size();
        boolean targetFound = false;
        Player target = null;
        while(targetFound == false)
        {
            if(players.get(index).isAlive() && players.get(index) != player)
            {
                target = players.get(index);
                targetFound = true;
            }
            else
            {
                index = (index+1)%players.size();
            }
        }
        
        return target;
        
        /*
        //if the player is alive, then he is a valid target
        if(players.get(Math.floorMod(index + distance,players.size())).isAlive())
        {
            return players.get(Math.floorMod(index + distance,players.size()));
        }
        //if he is dead then check the player to his right
        else
        {
            return availableTargetRight(nextPlayer(player), 1);
        }
        */
    }
    public static Player availableTargetLeft(Player player, int distance)
    {
        int index = Math.floorMod(players.indexOf(player) - distance, players.size());
        boolean targetFound = false;
        Player target = null;
        while(targetFound == false)
        {
            if(players.get(index).isAlive() && players.get(index) != player)
            {
                target = players.get(index);
                targetFound = true;
            }
            else
            {
                index = Math.floorMod(index - 1,players.size());
            }
        }
        
        return target;
        /*
        int index = players.indexOf(player);
        if(players.get(Math.floorMod(index - distance,players.size())).isAlive())
        {
            return players.get(Math.floorMod(index - distance,players.size()));
        }
        else
        {
            return availableTargetRight(previousPlayer(player), 1);
        }
        */
    }
    public static ArrayList<Player> allAvailableTargets(Player player, int distance)
    {
        ArrayList<Player> availableTargets = new ArrayList<Player>();
        availableTargets.add(availableTargetLeft(player, distance));
        availableTargets.add(availableTargetRight(player, distance));
        return availableTargets;
        
    }
    //checks to see if a player has won the game
    public static void checkWinConditions()
    {
        Player sheriff = getSheriff();
        if(sheriff.isAlive())
        {
            if(getLivingPlayers() == getLawPlayers())
            {
                lawWin();
            }
        }
        else if(sheriff.isDead())
        {
            if(getLivingPlayers().size() == 1)
            {
                if(getLivingPlayers().get(0).role() == "renegade")
                {
                    renegadeWin();
                }
            }
            else
            {
                outlawWin();
            }
        }
    }
    //returns the player to the right of a player
    public static Player nextPlayer(Player player)
    {
        return players.get(Math.floorMod((players.indexOf(player))+1,players.size()));
    }
    //returns the player to the left of a player
    public static Player previousPlayer(Player player)
    {
       return players.get(Math.floorMod((players.indexOf(player))-1,players.size()));
    }
    public static Player getSheriff()
    {
        Player sheriff = null;
        for(Player player: players)
        {
            if(player.role() == "sheriff")
            {
                sheriff = player;
            }
        }
        return sheriff;

    }
    public static ArrayList<Player> getLivingPlayers()
    {
        ArrayList<Player> livingPlayers = new ArrayList<Player>();
        //if a player is alive add them to the living player array
        for(Player player: players)
        {
            if(player.isAlive())
            {
                livingPlayers.add(player);
            }
        }
        return livingPlayers;
    }
    public static ArrayList<Player> getDeadPlayers()
    {
        ArrayList<Player> deadPlayers = new ArrayList<Player>();
        //if a player is alive add them to the living player array
        for(Player player: players)
        {
            if(player.isDead())
            {
                deadPlayers.add(player);
            }
        }
        return deadPlayers;
    }
    public static ArrayList<Player> getLawPlayers()
    {
        ArrayList<Player> lawPlayers = new ArrayList<Player>();
        //if a player is alive add them to the living player array
        for(Player player: players)
        {
            if(player.isAlive() && (player.role() == "sheriff" || player.role() == "deputy"))
            {
                lawPlayers.add(player);
            }
        }
        return lawPlayers;
    }
    public static ArrayList<Player> getOutlawPlayers()
    {
        ArrayList<Player> outlawPlayers = new ArrayList<Player>();
        //if a player is alive add them to the living player array
        for(Player player: players)
        {
            if(player.isAlive() && player.role() == "outlaw")
            {
                outlawPlayers.add(player);
            }
        }
        return outlawPlayers;
    }
    public static void lawWin()
    {
        gameOver = true;
        System.out.println("law Wins");
    }
    public static void renegadeWin()
    {
        gameOver = true;
        System.out.println("renegade Wins");
    }
    public static void outlawWin()
    {
        gameOver = true;
        System.out.println("outlaw Wins");
    }
    public static boolean isBot(Player player)
    {
        if(player == pc)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public static void botTurn(Player bot)
    {
        rollAll(bot);
        endTurn(bot);
    }
    public static  void botShoot(Player bot, int distance)
    {
        
        Random random = new Random();
        Player targetPlayer = bot;
        ArrayList<Player> targets = new ArrayList<Player>();
        targets = allAvailableTargets(bot,distance);
        
        
        int rand = (Math.abs((random.nextInt())%targets.size())); 
        targetPlayer = targets.get(rand);
        damageTarget(targetPlayer,1);
        System.out.println(bot.character().name() + " shoots " + targetPlayer.character().name());
        
        
        /*
        int leastHP = 99;
        
        for(Player player : targets)
        {
            if(player.currentHP() < leastHP)
            {
                targetPlayer = player;
            }
        }
        damageTarget(targetPlayer,1);
        System.out.println(bot.character().name() + " shoots " + targetPlayer.character().name());
        */
    }
    public static void botDrink(Player bot)
    {
        if(bot.role() == "deputy")
        {
            healTarget(getSheriff(), 1);
            System.out.println(bot.character().name() + " heals " + getSheriff().character().name());
        }
        else
        {
            healTarget(bot,1);
            System.out.println(bot.character().name() + " heals him or herself");
        }
    }
}
