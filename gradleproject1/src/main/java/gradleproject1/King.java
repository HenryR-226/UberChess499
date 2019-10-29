package gradleproject1;

import java.util.*;
import java.lang.*;

public class King extends Piece{
    
    Board b;
    
    
    
    public King(String moveID, boolean team){
       this.pieceID=moveID;
       this.setIsWhite(team);
       if(team) setAbbreviation('K');
       else if(!team) setAbbreviation('k');
       this.points=90;
    }
    
   
       
   /*  OLD
           So Oleg, what I'm looking for is a clean-ish way to make permutations of moves. Ie: "These columns and These rows are allowed, create all combinations of them." 
In my example, the king is at E-2 (piece has native access to location), so it can move to row 1 & 3, and to columns D and F, so valid moves are D-3, D-2, D-1, E-3, E-1, F-3, F-2, F-1.
Also gonna make each piece in charge of it's own offset and have a getter for it.           
    
    @Override
   public ArrayList<BoardButton> getMoves(Piece p){
       ArrayList<BoardButton> moveList = new ArrayList<BoardButton>();      //Return values
       ArrayList<String> validSquares = new ArrayList<String>();
       BoardButton b;
       String s=this.location;                        //Readability
       char[] c = s.toCharArray();
       char[] allowedCol = new char[3];
       char[] allowedRow = new char[3];
       char col = c[0];
       char row = c[1];
       if (col != 'A' && col!='H'){                                     //King is not along the edges
           for (int i =-1; i<2; i++) allowedCol[i+1] = (char)(col+i);
       }
       else if (col == 'A'){
           allowedCol[0] = 'A';
           allowedCol[1] = 'B';
           allowedCol[2] = ' '; 
       }
       else {
           allowedCol[0] = 'H';
           allowedCol[1] = 'G';
           allowedCol[2] = ' ';
       }
       if (row != '1' && row!='8'){                                     //King is not along top or bottom
           for (int j =-1; j<2; j++) allowedRow[j+1] = (char)(row+j);
       }
       else if (row == '1'){
          allowedRow[0] = '1';
          allowedRow[1] = '2';
          allowedRow[2] = ' ';
       }   
       else {
           allowedRow[0] = '8';
           allowedRow[0] = '7';
           allowedRow[2] = ' ';
       } 
       //Add strings of valid squares to the array list of possible moves acceptable
       for (int i =0; i<=2; i++){
           for (int j=0; j<=2; j++){
               String string = String.valueOf(allowedRow[i]);
               string = string + String.valueOf(allowedRow[j]);           
               validSquares.add(string);
           }    
       }
       //Now take those algebraic notation squares and convert them to Array notation and return that list
       //Looking for an elegant way to say "find the boardbutton where getAbbreviation() == s
       for (String a : validSquares){           
            if (b.getAbbreviation().contentEquals(a)) moveList.add(b);
       }
       return moveList;
   }
    */
    
//    public static double getGridOffset(int row, int col){
//        return gridOffsetKing[row][col];
//    }    
    
    public double getGridOffset(){
        String s = this.location;
        
    return 10;
    }
 }