import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/*
Sofia Gorgees
04-29-2024
CS-2100A: Battleship Final
Battleship Driver: implements all
classes for battleship game
*/
public class BattleshipDriver
{
    public static void main(String[] args) throws IOException
    {
        //game object
        Game game = new Game();
        Scanner scan = new Scanner(System.in);
        //list to hold moves user has used
        ArrayList<String> userMoves = new ArrayList<>();
        System.out.println("\nWelcome to Battleship!\n\n\n" + game);
        //randomized coin toss
        boolean computerTurn = false;
        Random rand = new Random();

        if(rand.nextInt(2) == 0)
        {
            System.out.println("The Computer won the coin toss and gets to go first");
            computerTurn = true;
        }else{
            System.out.println("You won the coin toss and get to go first.");
        }
        //loop for while neither board has been sunk
        while(!game.computerDefeated() && !game.userDefeated())
        {
            if(computerTurn)
            {
                System.out.print("Computer Turn. Press enter to continue:");
                scan.nextLine();
                String[] move = game.makeComputerMove();
                System.out.print(move[0] + "\n");
                if(move[1] != null)
                {
                    //prints if ship was sunk
                    System.out.println(move[1] + "\n");
                }
                computerTurn = false;
            }else{
                boolean goodMove = false;
                System.out.print("Your Turn: ");
                String userMove = "A1";
                while(!goodMove)
                {
                    userMove = scan.nextLine();
                    //input validation for move
                    try
                    {
                        userMove = userMove.toUpperCase();
                        if (userMove.length() == 2 || userMove.length() == 3)
                        {
                            char value1 = userMove.charAt(0);
                            int value2 = Integer.parseInt(userMove.substring(1));
                            if(value1 >= 65 && value1 <= 74 && value2 >0 && value2 <=10)
                            {
                                if(!userMoves.contains(userMove))
                                {
                                    userMoves.add(userMove);
                                    goodMove = true;
                                }else{
                                    System.out.print("Invalid move, try again: ");
                                }
                            }else{
                                System.out.print("Invalid move, try again: ");
                            }
                        }else{
                            System.out.print("Invalid move, try again: ");
                        }
                    }catch (NumberFormatException e){
                        System.out.print("Invalid move, try again: ");
                    }
                }
                String message = game.makePlayerMove(userMove);
                if(message != null) System.out.println("The Computer says: " + message);
                //switches the turn
                computerTurn = true;
            }
            //prints the boards
            System.out.println(game);
        }
        if(game.userDefeated()){
            System.out.println(" --- GAME OVER --- \nYOU LOST! COMPUTER WON");
        }else if(game.computerDefeated()){
            System.out.println(" --- GAME OVER --- \nYOU WON! COMPUTER LOST");
        }
    }
}
