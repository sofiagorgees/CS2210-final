/*
Sofia Gorgees
04-29-2024
CS-2100A: Battleship Final
Board Class - abstract super class
for computer and user boards
*/
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Board
{
    private ArrayList<ArrayList<CellStatus>> layout;
    private Fleet fleet;
    public static int SIZE = 10;

    /**
     * instantiates whole board with nothing cell status
     * and uses file to place ships
     * @param fileName file with players ship locations
     * @throws IOException
     */
    public Board(String fileName) throws IOException
    {
        //initializes fleet
        fleet = new Fleet();
        //initializes board
        layout = new ArrayList<>(SIZE);
        //iterates through each row and column updating
        //the cell status to nothing
        for(int r = 0; r < SIZE; r++)
        {
            ArrayList<CellStatus> colList = new ArrayList<>(SIZE);
            for(int c = 0; c < SIZE; c++)
            {
                colList.add(CellStatus.NOTHING);
            }
            layout.add(colList);
        }
        //instantiate boardFile
        Scanner boardFile = null;
        //input validation that exits program if fileName is invalid
        try {
            boardFile = new Scanner(new File(fileName));
        }catch (IOException e ){
            System.out.println("File not found");
            System.exit(1);
        }
        //iterates through board file lines
        while(boardFile.hasNext())
        {
            String line = boardFile.nextLine();
            String[] lineList = line.split(" ");
            //for Destroyer
            if(lineList[0].equals("D")) {
                if (lineList[1].substring(0, 1).equals(lineList[2].substring(0, 1))) {
                    horizontal(CellStatus.DESTROYER, 2, lineList[1]);
                } else {
                    vertical(CellStatus.DESTROYER, 2, lineList[1]);
                }
            //for aircraft carrier
            } else if(lineList[0].equals("A")) {
                if (lineList[1].substring(0, 1).equals(lineList[2].substring(0, 1))) {
                    horizontal(CellStatus.AIRCRAFT_CARRIER, 5, lineList[1]);
                } else {
                    vertical(CellStatus.AIRCRAFT_CARRIER, 5, lineList[1]);
                }
            //for battleship
            } else if(lineList[0].equals("B")) {
                if (lineList[1].substring(0, 1).equals(lineList[2].substring(0, 1))) {
                    horizontal(CellStatus.BATTLESHIP, 4, lineList[1]);
                } else {
                    vertical(CellStatus.BATTLESHIP, 4, lineList[1]);
                }
            //for cruiser
            } else if(lineList[0].equals("C")) {
                if (lineList[1].substring(0, 1).equals(lineList[2].substring(0, 1))) {
                    horizontal(CellStatus.CRUISER, 3, lineList[1]);
                } else {
                    vertical(CellStatus.CRUISER, 3, lineList[1]);
                }
            //for submarine
            } else {
                if (lineList[1].substring(0, 1).equals(lineList[2].substring(0, 1))) {
                    horizontal(CellStatus.SUB, 3, lineList[1]);
                } else {
                    vertical(CellStatus.SUB, 3, lineList[1]);
                }
            }
        }
        boardFile.close();

    }

    /**
     * Applies a move to layout. If the targeted
     * cell does not contain a ship, it is set to
     * CellStatus.NOTHING_HIT. If it contains a ship, the cell
     * is changed to HIT
     * @param m move of targeted cell
     * @return cellStatus of original cell
     */
    public CellStatus applyMoveToLayout(Move m)
    {
        //checks if is cell is empty
        if(layout.get(m.row()).get(m.col()) == CellStatus.NOTHING)
        {
            layout.get(m.row()).set(m.col(), CellStatus.NOTHING_HIT);
            return CellStatus.NOTHING;
        }
        //stores original CellStatus
        CellStatus cs = layout.get(m.row()).get(m.col());
        // checks what for cell status for each ship
        //and sets it to hit
        if(cs == CellStatus.BATTLESHIP){
            changeCellStatus(m, CellStatus.BATTLESHIP_HIT);
        } else if(cs == CellStatus.CRUISER){
            changeCellStatus(m, CellStatus.CRUISER_HIT);
        } else if(cs == CellStatus.DESTROYER){
            changeCellStatus(m, CellStatus.DESTROYER_HIT);
        } else if(cs == CellStatus.AIRCRAFT_CARRIER) {
            changeCellStatus(m, CellStatus.AIRCRAFT_CARRIER_HIT);
        } else if(cs == CellStatus.SUB) {
            changeCellStatus(m, CellStatus.SUB_HIT);
        }
        return cs;
    }
    /**
     * checks if move is available to take
     * @param m move
     * @return if move is available
     */
    public boolean moveAvailable(Move m)
    {
        if (layout.get(m.row()).get(m.col()) == CellStatus.NOTHING ||
                layout.get(m.row()).get(m.col()) == CellStatus.NOTHING_HIT ||
                layout.get(m.row()).get(m.col()) == CellStatus.AIRCRAFT_CARRIER ||
                layout.get(m.row()).get(m.col()) == CellStatus.SUB ||
                layout.get(m.row()).get(m.col()) == CellStatus.DESTROYER ||
                layout.get(m.row()).get(m.col()) == CellStatus.CRUISER ||
                layout.get(m.row()).get(m.col()) == CellStatus.BATTLESHIP) {
            return true;
        }
        return false;
    }
    /**
     * Accesses layout variable
     * @return layout reference
     */
    public ArrayList<ArrayList<CellStatus>> getLayout()
    {
        return layout;
    }
    /**
     * accesses fleet variable
     * @return fleet reference
     */
    public Fleet getFleet()
    {
        return fleet;
    }
    /**
     * checks if all ships have been sunk
     * @return true if all ships have been sunk, false otherwise.
     */
    public boolean gameOver()
    {
        if(fleet.gameOver()) return true;
        return false;
    }

    /**
     * changes the cell status for the move
     * objects cell to the inputted cell status
     * @param m cell to targer
     * @param cs cell status to change to
     */
    private void changeCellStatus(Move m, CellStatus cs)
    {
        layout.get(m.row()).set(m.col(), cs);
    }
    /**
     * private method to add cell statuses
     * in horizontal direction
     * @param cs CellStatus of Ship
     * @param num length of ship
     * @param start cell to start in
     */
    private void horizontal(CellStatus cs, int num, String start)
    {
        int rowNum = start.charAt(0) - 65;
        int colNum = Integer.parseInt(start.substring(1))-1;
        for(int i = 0; i < num; i++)
        {
            layout.get(rowNum).set(colNum + i, cs);
        }

    }
    /**
     *  private method to add cell statuses
     *  in vertical direction
     * @param cs CellStatus of Ship
     * @param num length of ship
     * @param start cell to start in
     */
    private void vertical(CellStatus cs, int num, String start)
    {
        int rowNum = start.charAt(0) - 65;
        int colNum = Integer.parseInt(start.substring(1))-1;
        for(int i = 0; i < num; i++)
        {
            layout.get(rowNum+i).set(colNum, cs);
        }
    }
}




