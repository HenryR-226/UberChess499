package gradleproject1;

import java.util.ArrayList;


public class Queen extends Piece {
    
    
    
    public Queen(String moveID, boolean team){
       this.pieceID=moveID;
       this.setIsWhite(team);
       if (team) setAbbreviation('Q');
       else if(!team) setAbbreviation('q');
       this.points=9;
    }

}