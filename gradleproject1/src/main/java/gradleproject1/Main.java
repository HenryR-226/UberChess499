/*
 Uberchess current build : 0.00.01
*/

package gradleproject1;

/**
 *
 * @author Tyrone Lamar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("UberChess Chess Game Initialization");
        
       Board b=new Board();
           b.initBoard();
           b.initBlack();
           b.initWhite();
        
           b.draw();
           System.out.println(" ");
           System.out.println("White: Upper Case, first move:");
           System.out.println("Hint: Type grid square for first pawn move (no need to specify pawn):");
           
    }
    
}

