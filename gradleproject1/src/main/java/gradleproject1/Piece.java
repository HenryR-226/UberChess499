/*
 *  SUPERCLASS For all piece objects
 *  Handles Point values for AI, Abbreviations, Move ID for special moves determination,
 *  and team affiliation, True= white, false = black
 *  Each piece Subclass handles an Overrided getMoves() called by the central getMoves() to 
 *  generate piece specific moves based on location and move rules.
 */
package gradleproject1;
import java.util.*;
import java.lang.Object;

/**
 *
 * @author Tyrone Lamar
 */
public class Piece {
    private boolean isWhite;
    private char abbreviation;
    public int moveID;
    double points;
    String location;                                //Bite me
    
    public char getAbbrev(){
        return this.abbreviation;
    }    
    
    public double getPoints(){
        return this.points;
    }    
    
    public boolean isWhite(){
        return isWhite;
    }    
    
    public int moveID(){
        return this.moveID;
    }    
    
    public void setIsWhite(boolean white){
        this.isWhite=white;
    }    
    
    public void setAbbreviation(char abbrev){
        this.abbreviation = abbrev;
    }
    /**
     * @author Henry Rheault 
     * A method to return an array list of all valid board button squares valid to move a piece onto.
     * @param piece object
    */
    
    public ArrayList<BoardButton> getMoves(Piece p){
        ArrayList<BoardButton> validMoves = new ArrayList<BoardButton>();      
       Board board; 
       boolean team = p.isWhite();          
       for (BoardButton b : getMoves(p)){           //Get valid moves for particular piece object
           /*if king not put in check AND (b is not full OR b is full but with an opposite color piece (if a piece is attackable or not
           handled within Pawn subclass) 
           */
           if (/*King not in check*/ !b.isFull()||(b.isFull()&&b.getPiece().isWhite()!=team) ){
           validMoves.add(b);
           }
        }
       return validMoves;
    }
    
}
