package gradleproject1;
import java.util.*;
import java.lang.*;
/**
 *
 * @author Tyrone Lamar
 * 
 * Class for AI agent and all related methods/logic, makes moves on the AI's behalf
 * via the Move ADT/API.
 * 
 * Brainchild of Henry Rheault.
 * 
 * 
 * "Big Brain!" 
 *      -Felix Kjellberg
 * 
 */
public class AI extends Player {
    private double blackPoints;
    private double whitePoints;
    
    
    /** @Author Henry Rheault
     * 
     * Method to access grid offset points for AI points calculation.
     * @param location string, 2 characters, IE: E4, not case sensitive
     * @param piece, single char, not case sensitive
     * @param team, white true black false.
     * 
     * @Return double (wrapper) for offset to add to points value for AI calculation.
    */
    public Double getGridOffset(String location, char piece, boolean team){
        boolean userErrorFlag = false;                      //User F'd up or not?
        try {
            assert (location.length() == 2);
            char[] temp = location.toCharArray();

            //Take string, convert to chars, and get Memory Array location from the Chess String location
            char col = temp[0];                             //A-H, not case sensitive, input argument
            char r0w = temp[1];                             //1-8, input argument
            col = Character.toUpperCase(col);               //Ensures that the column character is upper case for ease of assert
            if (!(Character.isLetter(col) || col>'H')){
                userErrorFlag = true;                          //Make sure col starts with an actual letter between A and H
                assert (Character.isLetter(col) && col<='H');  //Set user error flag and break out of try if not
            }  
            if (r0w >8 || r0w<0){
                userErrorFlag = true;                        //Make sure row given is less than 8
                assert (r0w <=8 && r0w>0);                   //Set user error flag and break out of try if not
            }                     
            //Format verified, convert Letter-Number to Array indexes
            short column = (short) (col - 'A');              //A is 0, B is 1, etc. Returns numeric difference between A & input letter
            short row2 = (short) r0w;                        //cast Char to Short because it gets mad if you cast to int
            short row = (short) (row2 + 8);                                  //Add 8 to convert chess layout to Memory layout
            
            //Flip the black team's characters back to lowercase
            if (team) {
                piece = Character.toUpperCase(piece);       //Does nothing if true but kept for readability
            } else {
                piece = Character.toLowerCase(piece);
            }
            
            switch (piece) {
                //white pieces
                case 'P':
                    return gridOffsetPawn[row][column];
                case 'N':
                    return gridOffsetKnight[row][column];
                case 'B':
                    return gridOffsetBishop[row][column];
                case 'R':
                    return gridOffsetRook[row][column];
                case 'Q':
                    return gridOffsetQueen[row][column];
                case 'K':
                    return gridOffsetKing[row][column];
                //here be black pieces    
                case 'p':
                    return gridOffsetPawn[8 - row][8 - column];
                case 'n':
                    return gridOffsetKnight[8 - row][8 - column];
                case 'b':
                    return gridOffsetBishop[8 - row][8 - column];
                case 'r':
                    return gridOffsetRook[8 - row][8 - column];
                case 'q':
                    return gridOffsetQueen[8 - row][8 - column];
                case 'k':
                    return gridOffsetKing[8 - row][8 - column];
                //Should never get here but best not to break things with a stupid value    
                default:
                    return (double)0;
            }
        } catch (Exception e) {
            if(location.length()>2|| userErrorFlag) {
                System.out.println("Error, invalid move. Retrying:");
                return null;
            }
            
            else {
                System.out.println("Offset program fucked up! Returning default of 0 offset.");
                e.printStackTrace();
                return 0.0;
            }
        }
    }
    /**
     * @author Henry Rheault
     * Method to calculate the points state on the board for @param player
     * @return the player's points
     */
    
    private double evalPoints(Player p, Board b){
        double result = 0;
        BoardButton[][] board = b.getGameBoard();               //Get locations
        ArrayList<Double> pointsList = new ArrayList<Double>(); //Create list of point objects
        for(Piece piece : p.getPieceList()){                             //For each row and column of BoardButton
            result = result + piece.getPoints()+                                                    //Find pieces belonging to player p and summate points
                                                                //This code isn't clean and needs to run hundreds of thousands of times
                                                                //So it needs to be better than "check every square for where there's
                                                                //A piece on that team, every time"
            
            
        
        //Piece doesn't have it's own location, only BoardButton does
        return result;    
        }
        }
        
        return result;
    }
    
     /* Grid offset arrays. White at bottom. For black pieces flips position 
    * and reads from that value of array within offset getter.
    * @author Henry Rheault, taken from 
    * https://www.freecodecamp.org/news/simple-chess-ai-step-by-step-1d55a9266977/
    */
    
    
    
    
    
    
            
   
    
    
    
    
        
 }    
    

