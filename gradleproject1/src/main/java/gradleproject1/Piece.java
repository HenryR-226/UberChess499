 
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
     * @param piece object
     * @return all valid moves allowed
    */
   
    public ArrayList<BoardButton> getMoves(Piece p){
        ArrayList<BoardButton> validMoves = new ArrayList<BoardButton>();      
        ArrayList<BoardButton> candidateMoves = new ArrayList<BoardButton>();             //Returned list from Piece subclass to be sifted through based on game rules
       Board board;
       boolean team = p.isWhite();          
       char c = Character.toUpperCase(p.getAbbrev());
       switch (c){
           case 'P':
               candidateMoves= getPawnMoves(p);
//           case 'N':    
//               candidateMoves= getKnightMoves(p);
//           case 'B':    
//               candidateMoves= getBishopMoves(p);
//           case 'R':
//               candidateMoves=Rook.getRookMoves(p);  
//           case 'Q':    
//               candidateMoves=Queen.getQueenMoves(p);
//           case 'K':    
//               candidateMoves=King.getKingMoves(p);    
       }
       
       for (BoardButton b : candidateMoves){           //Get valid moves for particular piece object
           /*if king not put in check AND (b is not full OR b is full but with an opposite color piece (if a piece is attackable or not
           handled within Pawn subclass)
           */
           if (/*King not in check*/ !b.isFull()||(b.isFull()&&b.getPiece().isWhite()!=team) ){     //If b is empty OR occupied by opposite team
           validMoves.add(b);
           }
        }
       
       return validMoves;
    }
   
    public ArrayList<BoardButton> getPawnMoves(Piece p){
        ArrayList<BoardButton> result = new ArrayList<BoardButton>();
        //boolean hasMoved = p.firstMove();
//       if (firstMove){
//             //can move two forward
//       }
//       if (canAttack){
//            //Call board for two grid locations adjacent
//            //can move forward + over one
//          
//       }
//       else if(!blocked){
//           //can move forward one only
//       }
 
        ArrayList<BoardButton> validY = new ArrayList<BoardButton>();
        ArrayList<BoardButton> valixX = new ArrayList<BoardButton>();
       
        //if()
 
 
        return result;
   }
   
    public ArrayList<String> getBishopMoves(Piece p){
       //https://math.stackexchange.com/questions/1566115/formula-that-describes-the-movement-of-a-bishop-in-chess
       //Moving from x1, y1 to x2, y2 is a valid move if abs(x2-x1) = abs(y2 - y1) > 0.
       
       ArrayList<String> validGrids = new ArrayList<String>();      //Return values
       ArrayList<Integer> validX = new ArrayList<Integer>();
       ArrayList<Integer> validY = new ArrayList<Integer>();
       String s=this.location;                                      //Readability
       char[] c = s.toCharArray();
       Integer col = c[0]-'A';
       Integer row = 8-c[1];
       
       Integer col2=(int)'A';            //Moving to
       Integer row2=1;
       //Populates an array list with strings of Int, for taking all permutations of to get move list grid squares
       for (int i = 65 ; i<=72; i++){                       //65 = 'A' in ASCII. 72 = 'H'
           for (Integer j = 1; j<=8; j++){
               if ((Math.abs(col2 - col)==(Math.abs(row2 - row))) && Math.abs(col2 - col)>0){
               //Add col2, row2 to valid grid squares in Chess notation
               String string;
               char ch = (char)i;
               string = String.valueOf(ch);
               string = string + row2.toString();
               validGrids.add(string);
               }
           }
       
       return validGrids;
       }
       return validGrids;
    }
       
/*    public ArrayList<BoardButton> getKnightMoves(Piece p){
    }
    public ArrayList<BoardButton> getRookMoves(Piece p){
   }
    public ArrayList<BoardButton> getQueenMoves(Piece p){
   } */
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
       //https://stackoverflow.com/questions/17192796/generate-all-combinations-from-multiple-lists
       for (Integer x : validX){
           for (Integer y : validY){
               BoardButton button = board[x][y];
               moveList.add(button);
           }
       }
       
       return moveList;
    }
   
   
    public void deleteThis(){
        String newGuy = "D5";
       
    }
}