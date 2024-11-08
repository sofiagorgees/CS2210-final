/*
Sofia Gorgees
04-29-2024
CS-2100A: Battleship Final
Fleet Class: holds the 5 ships
in a fleet for user and computer
*/
public class Fleet
{
    private Ship battleShip;
    private Ship aircraftCarrier;
    private Ship cruiser;
    private Ship sub;
    private Ship destroyer;
    private static int sunkNum;

    /**
     * default constructor for fleet
     * instantiating all ships
     */
    public Fleet()
    {
        battleShip = new Battleship();
        aircraftCarrier = new AircraftCarrier();
        cruiser = new Cruiser();
        sub = new Sub();
        destroyer = new Destroyer();
    }
    /**
     * informs the appropriate ship that it has been
     * hit, and returns true if this sank the
     * ship, and false if it did not
     * @param s shipType to update
     * @return true if sunk, false otherwise
     */
    public boolean updateFleet(ShipType s)
    {
        if(s == ShipType.ST_BATTLESHIP){
            battleShip.hit();
            return battleShip.getSunk();
        }else if(s == ShipType.ST_AIRCRAFT_CARRIER) {
            aircraftCarrier.hit();
            return aircraftCarrier.getSunk();
        }else if(s == ShipType.ST_DESTROYER){
            destroyer.hit();
            return destroyer.getSunk();
        }else if(s == ShipType.ST_SUB) {
            sub.hit();
            return sub.getSunk();
        }else if(s == ShipType.ST_CRUISER){
            cruiser.hit();
            return cruiser.getSunk();
        }
        return false;
    }
    /**
     * checks status of all ships
     * @return true if all ships have been sunk, false if not
     */
    public boolean gameOver()
    {
        return(cruiser.getSunk() &&
                sub.getSunk() &&
                battleShip.getSunk() &&
                aircraftCarrier.getSunk() &&
                destroyer.getSunk());
    }
}
