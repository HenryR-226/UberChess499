/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradleproject1;

import java.util.ArrayList;
import java.lang.Math;
import java.math.*;


/**
 *
 * @author Tyrone Lamar
 */
public class Bishop extends Piece {
    boolean lightSquares;       //True for light square bishop, false for dark square
    
    public Bishop(String moveID, boolean team){
       this.pieceID=moveID;
       this.setIsWhite(team);
       if(team) setAbbreviation('B');
       else setAbbreviation('b');
       this.points=3;
    }
  
   
  
}
