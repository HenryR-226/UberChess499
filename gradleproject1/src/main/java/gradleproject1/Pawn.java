package gradleproject1;

import java.util.ArrayList;

public class Pawn extends Piece {
    //int pawnID;                 //ID of the pawn
    boolean firstMove = true;   //If it is the pawn's first move allowing a double jump
    boolean canAttack;           //If the pawn can attack
    boolean blocked;             //currently blocked in front?
    char column;                 //Pawn's "home" column
    
    
   public Pawn(String pawnID, boolean team){
	   this.setName("Pawn");
       this.pieceID=pawnID;
       this.setIsWhite(team);
       if (team) setAbbreviation('P');
       else if (!team) setAbbreviation('p');
       
       this.points=1;
    }
   
   public boolean firstMove(){
       return this.firstMove;
   }
   
   public void madeFirstMove(){
       this.firstMove = true;
   }
 
}