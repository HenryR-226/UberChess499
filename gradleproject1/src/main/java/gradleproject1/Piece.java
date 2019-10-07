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
    public int moveID;
    double points;
    
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
    
    
    /*
    public char getAbbrev(int pieceNo){
        switch(pieceNo) {
            case 1:
                return 'p';
            case 2:    
                return 'n';
            case 3:    
                return 'b';
            case 4:
                return 'r';
            case 5:    
                return 'q';
            case 6:    
                return 'k';
            default:    
                return '-';
        }    
    }
    */
}
