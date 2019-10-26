/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradleproject1;

import java.util.ArrayList;

/**
 *
 * @author Tyrone Lamar
 */
public class Knight extends Piece {
    public Knight(String moveID, boolean team){
       this.pieceID=moveID;
       this.setIsWhite(team);
       if(team) setAbbreviation('N');
       else if(!team) setAbbreviation('n');
       this.points=3.25;
    }
    
    
    
  
}
