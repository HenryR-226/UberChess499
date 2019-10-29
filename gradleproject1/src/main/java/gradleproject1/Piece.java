 
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
 
/**
 *
 * @author Tyrone Lamar
 */
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
           /*if king not put in check AND (b is not full OR b is full but with an opposite color piece (if a piece is attackable or not
           handled within Pawn subclass)
           */
           if (/*King not in check*/ !b.isFull()||(b.isFull()&&b.getPiece().isWhite()!=team) ){     //If b is empty OR occupied by opposite team
           possibleMoves.add(b);
           }
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
   
    
    //Bishop raw moves generator
    public ArrayList<BoardButton> getBishopMoves(Piece p){
       //https://math.stackexchange.com/questions/1566115/formula-that-describes-the-movement-of-a-bishop-in-chess
       //Moving from x1, y1 to x2, y2 is a valid move if abs(x2-x1) = abs(y2 - y1) > 0.
       
       ArrayList<BoardButton> validGrids = new ArrayList<BoardButton>();      //Return values
       ArrayList<Integer> validX = new ArrayList<Integer>();
       ArrayList<Integer> validY = new ArrayList<Integer>();
       String s=this.location;                                      //Readability
       char[] c = s.toCharArray();
       Integer col = c[0]-'A';              //0 at A, 7 at H
       Integer row = c[1]+1;                //1 at 0, 8 at 7
       
       Integer col2=col;            //Moving to
       Integer row2=row;
       //Populates an array list with strings of Int, for taking all permutations of to get move list grid squares
       for (Integer i = col2 ; i<=8; i++){                       //65 = 'A' in ASCII. 72 = 'H'
           for (Integer j = row2; j<=8; j++){
               if ((Math.abs(i - col)==(Math.abs(j - row))) && Math.abs(i - col)>0){
               //Add col2, row2 to valid grid squares in Chess notation
               validX.add(i);
               validY.add(j);
               }
           }
       
       return validGrids;
       }
       return validGrids;
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
               moveList.add(button);
           }
       }
       moveList.remove(board[row][col]);
       possibleMoves = moveList;
       return possibleMoves;
    }
    
    public ArrayList<BoardButton> getQueenMoves(Piece p){
        ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
        
        return validSquares;
    }
   
    public ArrayList<BoardButton> getRookMoves(Piece p){
        ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
        
        return validSquares;
    }
    
    public ArrayList<BoardButton> getKnightMoves(Piece p){
        ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
        
        return validSquares;
    }
   
    public void deleteThis(){
        String newGuy = "D5";
       
    }
}