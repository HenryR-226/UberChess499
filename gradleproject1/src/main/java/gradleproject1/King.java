package gradleproject1;

import java.util.*;
import java.lang.*;

public class King extends Piece{

    public King(String moveID, boolean team){
       this.pieceID=moveID;
       this.setIsWhite(team);
       if(team) setAbbreviation('K');
       else if(!team) setAbbreviation('k');
       this.points=90;
    }
   
 }