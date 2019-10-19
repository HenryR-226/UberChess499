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
    
    public Bishop(int moveID, boolean team){
       this.moveID=moveID;
       this.setIsWhite(team);
       setAbbreviation('b');
       this.points=3;
    }
  
   public ArrayList<String> getMoves(Piece p){
       //https://math.stackexchange.com/questions/1566115/formula-that-describes-the-movement-of-a-bishop-in-chess
       //Moving from x1, y1 to x2, y2 is a valid move if abs(x2-x1) = abs(y2 - y1) > 0.
       
       ArrayList<String> validGrids = new ArrayList<String>();      //Return values
       ArrayList<Integer> validX = new ArrayList<Integer>();
       ArrayList<Integer> validY = new ArrayList<Integer>();
       String s=this.location;                                      //Readability
       char[] c = s.toCharArray();
       Integer col = c[0]-'A';
       Integer row = 8-c[1];
       
       Integer col2=(int)'A';            //Moving to
       Integer row2=1;
       //Populates an array list with strings of Int, for taking all permutations of to get move list grid squares
       for (int i = 65 ; i<=72; i++){
           for (Integer j = 1; j<=8; j++){
               if ((Math.abs(col2 - col)==(Math.abs(row2 - row))) && Math.abs(col2 - col)>0){
               //Add col2, row2 to valid grid squares in Chess notation
               String string;
               char ch = (char)i;
               string = String.valueOf(ch);
               string = string + row2.toString();
               validGrids.add(string);
               }
           }
       
       return validGrids;
       
   }
   private static double[][] gridOffsetBishop = new double[][]
    {
        {-2, -1, -1, -1, -1, -1, -1, -2},
        {-1, 0, 0, 0, 0, 0, 0, -1},
        {-1, 0, 0.5, 1, 1, 0.5, 0, -1},
        {-1, 0.5, 0.5, 1, 1, 0.5, 0.5, -1},
        {-1, 0, 1, 1, 1, 1, 0, -1},
        {-1, 1, 1, 1, 1, 1, 1, -1},
        {-1, 0.5, 0, 0, 0, 0, 0.5, -1},
        {-2, -1, -1, -1, -1, -1, -1, -2}
    };
}
