package gradleproject1;

import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(String moveID, boolean team){
       this.pieceID=moveID;
       this.setIsWhite(team);
       if (team) setAbbreviation('R');
       else if(!team) setAbbreviation('r');
       this.points=5;
    }
}