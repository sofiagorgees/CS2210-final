import java.io.IOException;
/*
Sofia Gorgees
04-29-2024
CS-2100A: Battleship Final
Game Class
*/
public class Game
{
    private ComputerBoard computer;
    private UserBoard user;
    /**
     * Creates the two boards (file names are fixed
     * “compFleet.txt” and “userFleet.txt”)
     */
    public Game() throws IOException
    {
        computer = new ComputerBoard("compFleet.txt");
        user = new UserBoard("userFleet.txt");
    }
    /**
     * Calls a method on the player board which makes a move
     * against that board. Returns an array of two Strings. The
     * first is the move the computer made in user readable
     * form. The second is either null, or, a string saying sunk
     * @return string array
     */
    public String[] makeComputerMove()
    {
        return user.makeComputerMove();
    }
    /**
     * Calls a method on the computer board which makes a
     * move against that board. Returns either null, or,
     * if the move resulted in a ship being sunk, a string for
     * the ship being sunk
     * @param s String for Move object
     * @return string of null or saying ship was sunk
     */
    public String makePlayerMove(String s)
    {
        Move move = new Move(s);
        return computer.makePlayerMove(move);
    }
    /**
     * Checks to see if the computer has been defeated.
     * @return true if all computer ships have been sunk, false otherwise
     */
    public boolean computerDefeated()
    {
        return computer.gameOver();
    }
    /**
     * Checks to see if the player has been defeated
     * @return true if all player ships have been sunk, false otherwise
     */
    public boolean userDefeated()
    {
        return user.gameOver();
    }
    /**
     * Returns a string representation of both boards well labelled
     * @return computer and user boards
     */
    public String toString()
    {
        return(String.format("%s\n%s", computer, user));
    }
}
