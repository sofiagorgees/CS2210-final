/*
Sofia Gorgees
04-29-2024
CS-2100A: Battleship Final
ComputerBoard Class - child class of board,
has methods for changing computer board
*/
import java.io.IOException;
public class ComputerBoard extends Board
{
    /**
     * Passes the filename on to the Board constructor
     * @param s computer file name
     * @throws IOException
     */
    public ComputerBoard(String s) throws IOException
    {
        super(s);
    }
    /**
     * Takes a move and makes it AGAINST this board. Takes
     * in move to be applied. if a ship was sunk, updates
     * the layout to change _HIT values to _SUNK values
     * @param m move to be made
     * @return null, or, if the move sank a ship a String
     */
    public String makePlayerMove(Move m)
    {
        //declare a string set to null
        String message = null;
        CellStatus cs = applyMoveToLayout(m); //save the returned cell status
        boolean sunk = false;
        //checks for which ship the cellStatus is and updates the fleet to reflect
        //being hit and sets sunk variable to whether that hit sunk the ship
        //if it was sunk then all cell statuses of that ship are set to be sunk
        if(cs == CellStatus.BATTLESHIP) {
            sunk = getFleet().updateFleet(ShipType.ST_BATTLESHIP);
            if (sunk)
            {
                changeAllCellStatuses(CellStatus.BATTLESHIP_HIT, CellStatus.BATTLESHIP_SUNK);
                message = "You sank my Battleship!";
            }
        } else if (cs == CellStatus.AIRCRAFT_CARRIER){
            sunk = getFleet().updateFleet(ShipType.ST_AIRCRAFT_CARRIER);
            if(sunk)
            {
                changeAllCellStatuses(CellStatus.AIRCRAFT_CARRIER_HIT, CellStatus.AIRCRAFT_CARRIER_SUNK);
                message = "You sank my Aircraft Carrier!";
            }
        } else if (cs == CellStatus.CRUISER){
            sunk = getFleet().updateFleet(ShipType.ST_CRUISER);
            if(sunk) {
                changeAllCellStatuses(CellStatus.CRUISER_HIT, CellStatus.CRUISER_SUNK);
                message = "You sank my cruiser!";
            }
        } else if (cs == CellStatus.SUB){
            sunk = getFleet().updateFleet(ShipType.ST_SUB);
            if(sunk) {
                changeAllCellStatuses(CellStatus.SUB_HIT, CellStatus.SUB_SUNK);
                message = "You sank my submarine!";
            }
        } else if (cs == CellStatus.DESTROYER){
            sunk = getFleet().updateFleet(ShipType.ST_DESTROYER);
            if(sunk) {
                changeAllCellStatuses(CellStatus.DESTROYER_HIT, CellStatus.DESTROYER_SUNK);
                message = "You sank my destroyer!";
            }
        }
        //returning value either string or null
        return message;
    }
    /**
     * Representation computers 10x10 board
     * @return string computer board
     */
    public String toString()
    {
        String gameBoard = "COMPUTER\n";
        gameBoard+="   1  2  3  4  5  6  7  8  9  10\n";
        for (int r = 0; r < SIZE; r++){
            char charRow = (char)(r + 65);
            gameBoard += charRow + "  ";
            for(int c = 0; c < SIZE; c++){
                gameBoard += getLayout().get(r).get(c).toString().substring(0,1);
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
