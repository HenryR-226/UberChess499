package gradleproject1;

import java.util.ArrayList;


public class Queen extends Piece {
    
    
    
    public Queen(String moveID, boolean team){
       this.pieceID=moveID;
       this.setIsWhite(team);
       if (team) setAbbreviation('Q');
       else if(!team) setAbbreviation('q');
       this.points=9;
    }
    
    
   
   
   
   /*
   public double getGridOffset(){
         try {
            String s = this.getLocation();

            char loc[] = s.toCharArray();
            int i = (int) loc[0];                                    //Number column
            int j = (int) loc[1];

            int n = 8 - (int) loc[1];
            int m = (int) loc[0];

            return this.gridOffset[m][n];

        } catch (Exception e) {
            System.out.println("Something fucked up in grid offset.");
            e.printStackTrace();
            return 0;
        }
    } */
}
