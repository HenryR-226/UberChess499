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
public class Rook extends Piece {
    public Rook(int moveID, boolean team){
       this.moveID=moveID;
       this.setIsWhite(team);
       setAbbreviation('r');
       this.points=5;
    }
    
   @Override
   public ArrayList<BoardButton> getMoves(Piece p){
   }
   
    private static double[][] gridOffsetRook = new double[][]{
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0.5, 1, 1, 1, 1, 1, 1, 0.5},
        {-.5, 0, 0, 0, 0, 0, 0, -.5},
        {-.5, 0, 0, 0, 0, 0, 0, -.5},
        {-.5, 0, 0, 0, 0, 0, 0, -.5},
        {-.5, 0, 0, 0, 0, 0, 0, -.5},
        {-.5, 0, 0, 0, 0, 0, 0, -.5},  
        {0, 0, 0, 0.5, 0.5, 0, 0, 0}       
    };
}
