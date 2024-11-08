/*
Sofia Gorgees
04-29-2024
CS-2100A: Battleship Final
Move Class: defines a move item which
holds the column and row for a move
*/
public class Move {
    private int row;
    private int col;

    /**
     * constructor that takes int value
     * for the row and column of a move
     * @param r row
     * @param c column
     */
    public Move(int r, int c)
    {
        row = r;
        col = c;
    }

    /**
     * constructor that takes a String with
     * letter value as first character for the
     * row and number as the second character for
     * the column
     * @param s row and column ex: "E2"
     */
    public Move (String s)
    {
        char c = s.toUpperCase().charAt(0);
        row = c - 65;
        col = Integer.parseInt(s.substring(1))-1;
    }

    /**
     * accessor for row
     * @return row
     */
    public int row(){
        return row;
    }

    /**
     * accessor for column
     * @return col
     */
    public int col(){
        return col;
    }

    /**
     * string that holds row as letter and
     * column as number
     * @return string of move
     */
    public String toString(){
        char c = (char)(row + 65);
        return(String.format("%c%d", c, col+1));
    }

}

