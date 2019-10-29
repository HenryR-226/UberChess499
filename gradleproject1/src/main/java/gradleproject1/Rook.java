package gradleproject1;

import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(String moveID, boolean team){
       this.pieceID=moveID;
       this.setIsWhite(team);
       if (team) setAbbreviation('R');
       else if(!team) setAbbreviation('r');
       this.points=5;
    }
    
   
   
   
    
    
  /*  public double getGridOffset(){
         try {
            String s = this.getLocation();

            char loc[] = s.toCharArray();
            int i = (int) loc[0];                                    //Number column
            int j = (int) loc[1];
            int n, m;                       //n: X, col. m: Y, row
            if (this.isWhite()){            //White piece
                    m = 8 - (int) loc[1];
                    n = (int) loc[0]-'A';
            } else {                        //Black piece, get teh mirror image of the board
                
                
            }                      
            
                
            m = (int) loc[0];
            
            return this.gridOffset[n][m];

        } catch (Exception e) {
            System.out.println("Something fucked up in grid offset.");
            e.printStackTrace();
            return 0;
        }
    }*/
}
