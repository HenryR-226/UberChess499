/*
 *  SUPERCLASS For all piece objects
 *  Handles Point values for AI, Abbreviations, Move ID for special moves determination,
 *  and team affiliation, True= white, false = black
 */
package gradleproject1;

/**
 *
 * @author Tyrone Lamar
 */
public class Piece {
    private boolean isWhite;
    private char abbreviation;
    int moveID;
    int points;
    
    public char getAbbrev(){
        return this.abbreviation;
    }    
    
    public int getPoints(){
        return this.points;
    }    
    
    public boolean isWhite(){
        return this.isWhite();
    }    
    
    public int moveID(){
        return this.moveID;
    }    
    
    
    
}
