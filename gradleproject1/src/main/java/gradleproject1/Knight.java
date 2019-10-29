package gradleproject1;

import java.util.ArrayList;

public class Knight extends Piece {
    
    public Knight(String moveID, boolean team){
       this.pieceID=moveID;
       this.setIsWhite(team);
       if(team) setAbbreviation('N');
       else if(!team) setAbbreviation('n');
       this.points=3.25;
    }
    
}
