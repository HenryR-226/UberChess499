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
    public Knight(int moveID, boolean team){
       this.moveID=moveID;
       this.setIsWhite(team);
       setAbbreviation('n');
       this.points=3.25;
    }
    
   private static double[][] gridOffsetKnight = new double[][]
    {
        {-5, -4, -3, -3, -3, -3, -4, -5},
        {-4, -2, 0, 0, 0, 0, -2, -4},
        {-3, 0, 1, 1.5, 1.5, 1, 0, -3},
        {-3, 0.5, 1.5, 2, 2, 1.5, 0.5, -3},
        {-3, 0.5, 1.5, 2, 2, 1.5, 0.5, -3},
        {-3, 0.5, 1, 1.5, 1.5, 1, 0.5, -3},
        {-4, -2, 0, 0.5, 0.5, 0, -2, -4},
        {-5, -4, -3, -3, -3, -3, -4, -5}
        
    };
}
