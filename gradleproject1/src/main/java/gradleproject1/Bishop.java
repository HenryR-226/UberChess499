package gradleproject1;

import java.util.ArrayList;
import java.lang.Math;
import java.math.*;


/**
 * @author Henry Rheault
 */
public class Bishop extends Piece {
    boolean lightSquares;       //True for light square bishop, false for dark square
    
    public Bishop(String moveID, boolean team){
       this.setName("Bishop");
       this.pieceID=moveID;
       this.setIsWhite(team);
       if(team) setAbbreviation('B');
       else setAbbreviation('b');
       this.points=3;
    }
  
   
  
}
