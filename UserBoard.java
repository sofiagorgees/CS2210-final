/*
Sofia Gorgees
04-29-2024
CS-2100A: Battleship Final
UserBoard Class - child class of board,
has methods for changing user board
*/
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class UserBoard extends Board
{
    private ArrayList<Move> moves;
    private Random rand;

    /**
     * constructor that takes file of users ship
     * locations
     * @param s file
     */
    public UserBoard(String s) throws IOException
    {
        super(s);
        rand = new Random();
        moves = new ArrayList<>();
        for(int r = 0; r < SIZE; r++)
        {
            for(int c = 0; c < SIZE; c++)
            {
                moves.add(new Move(r,c));
            }
        }
    }
    /**
     * Computer move against UserBoard. Selects and makes
     * a move AGAINST this board. Returns a string array
     * if a ship was sunk, updates the layout to
     * change _HIT values to _SUNK values
     * @return array of two Strings. The first is the move
     * the computer made in user readable form. The second
     * is either null, or, if the move resulted in a ship being
     * sunk, a string saying sunk
     */
    public String[] makeComputerMove()
    {
        //string array with default message and null
        String[] message = {"message", null};
        //generates random index for moves
        int index = rand.nextInt(0,moves.size());
        //save the returned cell status
        CellStatus cs = applyMoveToLayout(moves.get(index));
        message[0] = String.format("Computer chose: %s", moves.get(index));
        boolean sunk;
        //checks for which ship the cellStatus is and updates the fleet to reflect
        //being hit and sets sunk variable to whether that hit sunk the ship
        //if it was sunk then all cell statuses of that ship are set to be sunk
        if(cs == CellStatus.BATTLESHIP) {
            sunk = getFleet().updateFleet(ShipType.ST_BATTLESHIP);
            if (sunk)
            {
                changeAllCellStatuses(CellStatus.BATTLESHIP_HIT, CellStatus.BATTLESHIP_SUNK);
                message[1] = "Your Battleship was sunk";
            }
        } else if (cs == CellStatus.AIRCRAFT_CARRIER){
            sunk = getFleet().updateFleet(ShipType.ST_AIRCRAFT_CARRIER);
            if(sunk)
            {
                changeAllCellStatuses(CellStatus.AIRCRAFT_CARRIER_HIT, CellStatus.AIRCRAFT_CARRIER_SUNK);
                message[1] = "Your Aircraft Carrier was sunk";
            }
        } else if (cs == CellStatus.CRUISER){
            sunk = getFleet().updateFleet(ShipType.ST_CRUISER);
            if(sunk) {
                changeAllCellStatuses(CellStatus.CRUISER_HIT, CellStatus.CRUISER_SUNK);
                message[1] = "Your cruiser was sunk";
            }
        } else if (cs == CellStatus.SUB){
            sunk = getFleet().updateFleet(ShipType.ST_SUB);
            if(sunk) {
                changeAllCellStatuses(CellStatus.SUB_HIT, CellStatus.SUB_SUNK);
                message[1] = "Your submarine was sunk";
            }
        } else if (cs == CellStatus.DESTROYER){
            sunk = getFleet().updateFleet(ShipType.ST_DESTROYER);
            if(sunk) {
                changeAllCellStatuses(CellStatus.DESTROYER_HIT, CellStatus.DESTROYER_SUNK);
                message[1] = "Your Destroyer was sunk";
            }
        }
        moves.remove(index);
        //returning value either string or null
        return message;
    }
    /**
     * representation users 10x10 board
     * @return string user board
     */
    public String toString()
    {
        String gameBoard = "USER\n";
        gameBoard+="   1  2  3  4  5  6  7  8  9  10\n";
        for (int r = 0; r < SIZE; r++){
            char charRow = (char)(r + 65);
            gameBoard += charRow + "  ";
            for(int c = 0; c < SIZE; c++){
                gameBoard += getLayout().get(r).get(c).toString().substring(1);
                gameBoard += "  ";
            }
            gameBoard += "\n";
        }
        return gameBoard;
    }

    /**
     * private method that iterates through entire
     * layout arrayList and checks for cellStatus c1 and
     * if found changes it to c2
     * @param c1 cell status to look for
     * @param c2 cell status to change to
     */
    private void changeAllCellStatuses(CellStatus c1, CellStatus c2)
    {
        for(int r = 0; r < SIZE; r++)
        {
            for(int c = 0; c < SIZE; c++)
            {
                if(getLayout().get(r).get(c) == c1)
                {
                    getLayout().get(r).set(c, c2);
                }
            }
        }
    }


}
