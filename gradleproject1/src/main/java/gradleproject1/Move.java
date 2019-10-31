package gradleproject1;

/**
 *
 * @author Henry Rheault
 * 
 * Move started as a specific type of string, eg, nE4, KG7. It has changed 
 * to store the old and new board squares it was involving, it's notation,
 * and the piece that moved.
 */
public class Move {
    
    private BoardButton old;
    private BoardButton n3w;
    
    private String abbreviation;
    private Piece piece;
    private boolean isWhite;
    
    private Player whitePlayer;
    private Player blackPlayer;
    
    private Board b;
    
    
    /** @author Henry Rheault
    *
    * Commits a move to a gamer's move list. Assumes move is valid when being called.
    * Try-Catch for good measure and debugging ease to see if the error is within Move or elsewhere.
    * 
    * Adds a move to a particular gamer's move list for demo purposes. 
    */
    public Move(Piece p, char row, char column) throws Exception {
        try{
            
            BoardButton[][] GameBoard = b.getGameBoard();          //Fetch gameboard object
            
            String s = p.getLocation();
            char loc[] = s.toCharArray();
            int i = (int)loc[0];                                    //Number column
            int j = (int)loc[1]; 
            
            //  10/27/19: Update and make sure with new grid layout this reports/converts the correct grid notations! 
            // A1 - 0,0; H8 - 7,7, etc.
            int m = (int)row;
            int n = (int)column;
            this.old = GameBoard[i][j];
            this.n3w = GameBoard[m][n];
            
            System.out.println(p.getAbbrev() + " moved from " + loc[0] + loc[1] + " to " + row + column + ".");            
            setAbbreviation(String.valueOf(p.getAbbrev())+String.valueOf(row) + String.valueOf(column));
            
            String move = String.valueOf(p.getAbbrev());
            move = move + row + column;
            if (p.isWhite()) whitePlayer.addMove(move);
            else blackPlayer.addMove(move);
            
            String location = this.n3w.getAbbreviation();
            p.setLocation(location);
            
        } catch (Exception e) {
            System.out.println("Invalid move constructor. Try again.");
        }
    }
    
    public Move(Piece p, BoardButton button) throws Exception {          //Overloaded constructor, simply declare a boardbutton to attempt a move to
        try {
            BoardButton[][] GameBoard = b.getGameBoard();          //Fetch gameboard object

            String s = p.getLocation();
            char loc[] = s.toCharArray();
            int i = (int) loc[0];                                    //Number column
            int j = (int) loc[1];

            this.old = GameBoard[i][j];
            this.n3w = button;
            String abbrev = n3w.getAbbreviation();
            System.out.println(p.getAbbrev() + " moved from " + loc[0] + loc[1] + " to " + abbrev + ".");

            String move = String.valueOf(p.getAbbrev());
            move = move + abbrev;
            setAbbreviation(move);
            
            if (p.isWhite()) {
                whitePlayer.addMove(move);
            } else {
                blackPlayer.addMove(move);
            }

        } catch (Exception e) {
            System.out.println("Invalid move constructor taking board square. Try again.");
            e.printStackTrace();
        }
    }
    
    //Get the boardbutton the move was FROM
    public BoardButton getOld(){
        return this.old;
    }
    
    //Get the boardbutton the move was TO
    public BoardButton getNew(){
        return this.n3w;
    }
    
    //Return string/algebraic notation
    public String getAbbreviation(){
        return this.abbreviation;
    }
    
    //To be updated: Make it take in args of char and do the operations/checks as done in the Move constructors above
    //To be equivalent to proper Object Oriented form. IE: "no funky data"
    private void setAbbreviation(String s){
        try{
            assert(s.length() <= 3);
            char[] c = s.toCharArray();
            String result;
            if (c[0] == 'p' || c[0] == 'P'){            //If it was a pawn move, remove abbrev for said pawn move
                char c2[] = { c[1], c[2]};
                assert (c[1]>= 'A' && c[2] <= 'H');
                assert (c[2]>='1' && c[2]<='8');
                result = String.valueOf(c[1]);
                result = result + String.valueOf(c[2]);
                this.abbreviation = result;
            }       //Else it wasn't a pawn move
            assert(Character.toUpperCase(c[0]) == 'N' || Character.toUpperCase(c[0]) == 'B' 
            || Character.toUpperCase(c[0]) == 'K' || Character.toUpperCase(c[0]) == 'Q' || Character.toUpperCase(c[0]) == 'R');
            assert(c[1] >= 'A' && c[1] <='H' || c[1] >= 'a' && c[1] <= 'h');
            assert (c[2] >= '1' && c[2]<= '8');
            result = String.valueOf(c[0]);
            result = result + String.valueOf(c[1]);
            result = result + String.valueOf(c[2]);
            this.abbreviation = result;
        } catch (Exception e) {
            System.out.println("Yo, invalid move passed in to set abbreviation!");
            System.out.println("Move abbrev passed: " + s);
            e.printStackTrace();
        }
    }
}
