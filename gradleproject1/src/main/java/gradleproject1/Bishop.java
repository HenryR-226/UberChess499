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
public class Bishop extends Piece {
    boolean lightSquares;       //True for light square bishop, false for dark square
    
    public Bishop(int moveID, boolean team){
       this.moveID=moveID;
       this.setIsWhite(team);
       setAbbreviation('b');
       this.points=3;
    }
   @Override
   public ArrayList<BoardButton> getMoves(Piece p){
   }
}
