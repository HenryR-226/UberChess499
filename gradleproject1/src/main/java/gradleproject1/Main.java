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
        System.out.println("ayylmao");
        
       Board b=new Board();
           b.initBoard();
           b.initWhite();
           b.initBlack();
           b.draw();
    }
    
}

