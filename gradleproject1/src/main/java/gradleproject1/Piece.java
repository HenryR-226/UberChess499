 
/*
 *  SUPERCLASS For all piece objects
 *  Handles Point values for AI, Abbreviations, Move ID for special moves determination,
 *  and team affiliation, True= white, false = black
 *  Each piece Subclass handles an Overrided getMoves() called by the central getMoves() to
 *  generate piece specific moves based on location and move rules.
 */
 package gradleproject1;
import java.util.*;
import java.lang.*;
 

public class Piece {
    private boolean isWhite;
    private char abbreviation;
    public String pieceID;          //unique string to identify a particular piece object
    double points;
    String location;                                
    private static BoardButton[][] board;
    private static Board b;
   
    private ArrayList<BoardButton> possibleMoves;               //Each piece keeps track of where it can currently move.
    
    public ArrayList<BoardButton> getPossibleMoves(){
        return possibleMoves;
    }
    
    public void deleteMoveSquare(BoardButton b) throws Exception{
        try{
        possibleMoves.remove(b);
        } catch (Exception e) {
            System.out.println("deleteMoveSquare called with invalid board square argument.");
        }
    }
    
    public void addMoveSquare(BoardButton b) throws Exception{
         possibleMoves.add(b);
    }
    
    public static BoardButton[][] getBoard() {
        return board;
    }
 
    public static void setBoard(Board boardInput) {
        Piece.b = boardInput;
        Piece.board=b.getGameBoard();
    }
 
    public char getAbbrev(){
        return this.abbreviation;
    }    
   
    public double getPoints(){
        return this.points;
    }    
   
    public boolean isWhite(){
        return isWhite;
    }    
   
   
    public void setIsWhite(boolean white){
        this.isWhite=white;
    }    
   
    public void setAbbreviation(char abbrev){
        this.abbreviation = abbrev;
    }
   
    public String getLocation(){
        return this.location;
    }
   
   
    /**
     * @author Henry Rheault
     * A method to return an array list of all valid board button squares valid to move a piece onto.
     * 
     * Calls a particular piece's method for move generation for RAW (unfiltered) candidate moves, then
     * weeds through them here for GUI/AI purposes.
     * 
     * @param piece object
     * @return all valid moves allowed
    */
   
    public ArrayList<BoardButton> getMoves(Piece p){     
        ArrayList<BoardButton> candidateMoves = new ArrayList<BoardButton>();             //Returned list from Piece subclass to be sifted through based on game rules
       Board board;
       boolean team = p.isWhite();          
       char c = Character.toUpperCase(p.getAbbrev());
       switch (c){
           case 'P':
               candidateMoves= getPawnMoves((Pawn) p);
           case 'N':    
               candidateMoves= getKnightMoves(p);
           case 'B':    
               candidateMoves= getBishopMoves(p);
           case 'R':
               candidateMoves=getRookMoves(p);  
           case 'Q':    
               candidateMoves=getQueenMoves(p);
           case 'K':    
               candidateMoves=getKingMoves(p);    
       }
       
       for (BoardButton b : candidateMoves){           //Get valid moves for particular piece object
           //if king not put in check - No need to test for 'is full' or 'can attack' as each piece method handles that now
           
           //if (/*King not in check*/){    
           possibleMoves.add(b);
           //}
        }
       
       return possibleMoves;
    }
   
    /**
     * @author Henry Rheault
     * Generates possible pawn moves. Board will not have to sift through 
     * these as it generates only valid moves for itself, as a consequence of needing
     * to check whether it can attack.
     */
    public ArrayList<BoardButton> getPawnMoves(Pawn p) {
        ArrayList<BoardButton> result = new ArrayList<BoardButton>();
        String location = p.getLocation();
        char[] c = location.toCharArray();
        int x = ((int) c[0] - 1);
        int y = (int) c[1];
        boolean team = p.isWhite();

        BoardButton highSide;
        BoardButton lowSide;
        BoardButton front;
        if (team) {                      //White pawn, goes up
            if (x + 1 < 9) highSide = board[x + 1][y + 1];
            else  highSide = null;
            if (x - 1 > -1) lowSide = board[x - 1][y + 1];
            else lowSide = null;
            if (y + 1 < 9) front = board[x][y + 1];  
            else front = null;
            if (p.firstMove()) {
                BoardButton front2 = board[x][y + 2];
                if (!front2.isFull()) {
                    result.add(front2);
                }
            }
            if (!highSide.getPiece().isWhite() && highSide.getPiece() != null && x != 7) result.add(highSide);
            if (!lowSide.getPiece().isWhite() && highSide.getPiece() != null && x != 0)  result.add(lowSide);
            if (!front.isFull() && y != 7) result.add(front);
                
        } else {                          //Black team, pawn down
            if (x + 1 < 9) highSide = board[x + 1][y - 1];
            else highSide = null;
            if (x - 1 > -1) lowSide = board[x - 1][y - 1];
            else lowSide = null;
            if (y - 1 > -1) front = board[x][y - 1];
            else front = null;
            if (p.firstMove()) {
                BoardButton front2 = board[x][y - 2];
                if (!front2.isFull()) {
                    result.add(front2);
                }
            }
            if (highSide.getPiece() != null && highSide.getPiece().isWhite() && x != 7) result.add(highSide);
            if (lowSide.getPiece().isWhite() && highSide.getPiece() != null && x != 0) result.add(lowSide);
            if (!front.isFull() && y != 0) result.add(front);
        }
        return result;
    }
   
    
    

    //Returns LIST of BOARDBUTTONS which will be at Indexes the piece is allowed to move to
    public ArrayList<BoardButton> getKingMoves(Piece p){
     
       ArrayList<BoardButton> moveList = new ArrayList<>();      //Return values
       //ArrayList<String> validGrids = new ArrayList<String>();
       ArrayList<Integer> validX = new ArrayList<>();
       ArrayList<Integer> validY = new ArrayList<>();
       
       String s=this.location;                                  //Readability
       char[] c = s.toCharArray();                              //converts location into char array to get the column and row
       Integer col = c[0]-'A';
       Integer row = 8-c[1];
       
       //Populates an array list with strings of Int, for taking all permutations of to get move list grid squares
       if (col!=0&&col!=7){
           validX.add(col);
           col = col+1;
           validX.add(col);
           col = col-2;
           validX.add(col);
           col = col+1;                     //Reset to original position
       }    else if (col==0){
           validX.add(col);
           col = col+1;
           validX.add(col);
           col = col-1;
       }
       else if(col==7){
           validX.add(col);
           col = col-1;
           validX.add(col);
           col = col+1;
       }
       
       if (row!=7&&row!=0){
           validY.add(row);
           row = row+1;
           validY.add(row);
           row = row-2;
           validY.add(row);
           row = row+1;
       }
       else if (row==0){
           validY.add(row);
           row = row+1;
           validY.add(row);
           row = row-1;
       } else if (row==7){
           validY.add(row);
           row = row-1;
           validY.add(row);
           row = row+1;
       }
       //Now take all permutations of the row and col values, discarding the 'unchanged' move
       for (Integer x : validX){
           for (Integer y : validY){
               BoardButton button = board[x][y];
               if (!button.isFull() || button.isFull() && button.getPiece().isWhite()!= p.isWhite()) moveList.add(button);
           }
       }
       moveList.remove(board[row][col]);
       possibleMoves = moveList;
       return possibleMoves;
    }
    
    /*
    *@author Henry Rheault
    *
    * Generates Queen move squares. Calls Rook and Bishop methods to get
    * what would be valid in either case, then combines the lists.
    * Big Brain!!!!!
    */
    public ArrayList<BoardButton> getQueenMoves(Piece p){
        ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
        validSquares = p.getBishopMoves(p);
        ArrayList<BoardButton> validRook = p.getRookMoves(p);
        for (BoardButton b : validRook) validSquares.add(b);
        return validSquares;
    }
    
    
    //Bishop raw moves generator
    public ArrayList<BoardButton> getBishopMoves(Piece p){
       //https://math.stackexchange.com/questions/1566115/formula-that-describes-the-movement-of-a-bishop-in-chess
       //Moving from x1, y1 to x2, y2 is a valid move if abs(x2-x1) = abs(y2 - y1) > 0.
       //Too small brain. Did Rook-style move generation until obstacle instead.
       
       ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();      //Return values
       String s=this.location;                                      //Readability
       char[] c = s.toCharArray();
       Integer col = c[0]-'A';              //0 at A, 7 at H
       Integer row = (int) c[1];                //0 at 1, 7 at 8
       
       int ctrx = col;
       int ctry = row;
       
       BoardButton b;
       // if ((Math.abs(i - col)==(Math.abs(j - row))) && Math.abs(i - col)>0){
       
       //Go in each of 4 diagonals.
       // Plus X, Plus Y:
       do {
           ctrx++; ctry++;
           try { 
               b = board[ctrx][ctry];
               if (!b.isFull() || b.isWhite()!=p.isWhite() && ctry<8 && ctrx<8) validSquares.add(b);
           } catch (Exception e) { break; }
       } while (!b.isFull() && ctrx<8 && ctry<8);
       
       //Minus X, Plus Y
       ctrx=col;
       ctry=row;
       do {
           ctrx--; ctry++;
           try { 
               b = board[ctrx][ctry];
               if (!b.isFull() || b.isWhite()!=p.isWhite() && ctry<8 && ctrx>-1) validSquares.add(b);
           } catch (Exception e) { break; }
           
       } while (!b.isFull() && ctrx>-1 && ctry<8);
       
       //Minus X, Minus Y:
       ctrx=col;
       ctry=row;
       do {
           ctrx--; ctry--;
           try { 
               b = board[ctrx][ctry];
               if (!b.isFull() || b.isWhite()!=p.isWhite() && ctry>-1 && ctrx>-1) validSquares.add(b);
           } catch (Exception e) { break; }
       } while (!b.isFull() && ctrx>-1 && ctry>-1);
           
       //Plus X, Minus Y:
       ctrx=col;
       ctry=row;
       do {
           ctrx++; ctry++;
           try{ 
                b = board[ctrx][ctry];
                if (!b.isFull() || b.isWhite()!=p.isWhite() && ctry>-1 && ctrx<8) validSquares.add(b);
           } catch (Exception e) { break; }
       } while (!b.isFull() && ctrx<8 && ctry>-1);
       
       return validSquares;
    }
    
    
   /**
    * @author Henry Rheault
    * 
    * So I was thinking that I don't want the Board to waste time checking possible squares
    * if they are blocked by another piece on the 'infinite range' piece classes.
    * So it checks for piece occupying a square in front of it and stops generating moves in that
    * direction once the square is occupied in front of it.
    */ 
    
    public ArrayList<BoardButton> getRookMoves(Piece p){
        ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
        BoardButton b;
        String location = p.getLocation();
        char[] c = location.toCharArray();
        int x = ((int)c[0]-'A');
        int y = (int) c[1];
        int ctrx = x;
        int ctry = y;
        do {
            ctry++;
            try{
                b = board[x][ctry];                           //Go positive Y down it's col
                if (!b.isFull() || b.isWhite()!=p.isWhite() && ctry<8) validSquares.add(b);
            } catch (Exception e) { break; }            
        } while (!b.isFull() && ctry<8);              //Stop at first occupied or out of bounds square
        ctry=y;
        do {
            ctry--;
            try{
                b = board[x][ctry];                              //Go negative Y down it's col
                if (!b.isFull() || b.isWhite()!=p.isWhite() && ctry>-1) validSquares.add(b);
            } catch (Exception e) { break; }
        } while (!b.isFull() && ctry>-1);              
        do {
            ctrx++;
            try{
                b = board[ctrx][y];                           //Go positive X down it's row
                if (!b.isFull() || b.isWhite()!=p.isWhite() && ctrx<8) validSquares.add(b);
            } catch (Exception e) { break; }
        } while (!b.isFull() && ctrx<8);              
        ctrx=x;
        do {
            ctrx--;
            try{
                b = board[ctrx][y];                              //Negative X down it's row
                if (!b.isFull() || b.isWhite()!=p.isWhite() && ctrx>-1) validSquares.add(b);
            } catch (Exception e) { break; }
        } while (!b.isFull() && ctrx>-1);              //Stop at first occupied or out of bounds square
        
        return validSquares;
    }
    
    /**
     * @author Henry Rheault
     * 
     * Implements Knight move rules. My method is to make lists of 1 and 2 squares off respectively,
     * then take all combinations of opposing number lists.
     */
    public ArrayList<BoardButton> getKnightMoves(Piece p){
        ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
        ArrayList<Integer> validX1 = new ArrayList<Integer>();      //x +-1
        ArrayList<Integer> validY1 = new ArrayList<Integer>();      //x +-2
        ArrayList<Integer> validX2 = new ArrayList<Integer>();      //y +-1
        ArrayList<Integer> validY2 = new ArrayList<Integer>();      //y +-2
        
        String location = p.getLocation();
        char[] c = location.toCharArray();
        int x = ((int)c[0]-'A');
        int y = (int) c[1];
        
        //Generate X valuses valid for each list
        validX1.add(x-1);
        validX2.add(x-2);
        validX1.add(x+1);
        validX2.add(x+2);
        for (Integer i : validX1){                              //Prune out of bounds values
            if (i<0 || i>7) validX1.remove(i);
        }
        for (Integer i : validX2){
            if (i<0 || i>7) validX2.remove(i);
        }
        //Generate Y values valid for each list
        validY1.add(y-1);
        validY2.add(y-2);
        validY1.add(y+1);
        validY2.add(y+2);
        for (Integer j : validY1){
            if (j<0 || j>7) validY1.remove(j);
        }  
        for (Integer j : validY2){ 
            if (j<0 || j>7) validY2.remove(j);
        }
        //Combine X+-1 with Y+-2
        for (Integer i : validX1){
            for (Integer j : validY2){ 
                if (!board[i][j].isFull() || board[i][j].isFull() && board[i][j].getPiece().isWhite()!= p.isWhite()) validSquares.add(board[i][j]);
            }
        }    
        //Combine X+-2 with Y+-1
        for (Integer i : validX2){
            for (Integer j : validY1){
                if (!board[i][j].isFull() || board[i][j].isFull() && board[i][j].getPiece().isWhite()!= p.isWhite()) validSquares.add(board[i][j]);
            }    
        }
        return validSquares;
    }
   
    /*
    * Started by James.
    * I don't remember what for.
    * Kurwa.
    */
    public void deleteThis(){
        String newGuy = "D5";
       
    }
}