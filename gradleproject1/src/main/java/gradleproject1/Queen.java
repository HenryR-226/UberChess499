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
public class Queen extends Piece {
    
    
    
    public Queen(int moveID, boolean team){
       this.moveID=moveID;
       this.setIsWhite(team);
       setAbbreviation('q');
       this.points=9;
    }
    
    @Override
   public ArrayList<BoardButton> getMoves(Piece p){
   }
}