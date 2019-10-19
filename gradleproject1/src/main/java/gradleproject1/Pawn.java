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
public class Pawn extends Piece {
    boolean firstMove;          //If it is the pawn's first move allowing a double jump
    boolean canAttack;           //If the pawn can attack
    boolean blocked;            //currently blocked in front?
    char column;                //Pawn's "home" column
    
    
   public Pawn(int moveID, boolean team){
       this.moveID=moveID;
       this.setIsWhite(team);
       setAbbreviation('p');
       this.points=1;
    }
   
   @Override
   public ArrayList<BoardButton> getMoves(Piece p){
       if (firstMove){
             //can move two forward
       }
       if (canAttack){
            //Call board for two grid locations adjacent
            //can move forward + over one
           
       }
       else if(!blocked){
           //can move forward one only
       }
       
   }
   
   private static double[][] gridOffsetPawn = new double[][]
    {
        {0, 0, 0, 0, 0, 0, 0, 0},
        {5, 5, 5, 5, 5, 5, 5, 5},
        {1, 1, 2, 3, 3, 2, 1, 1},
        {0.5, 0.5, 1, 2.5, 2.5, 1, 0.5, 0.5},
        {0, 0, 0, 2, 2, 0, 0, 0},
        {0.5, -0.5, -1, 0, 0, -1, -0.5, 0.5},
        {0.5, 1, 1, -2, -2, 1, 1, 0.5},
        {0, 0, 0, 0, 0, 0, 0, 0}
    };
}