/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradleproject1;

/**
 *
 * @author Henry Rheault
 * 
 * Move is a special type of string consisting of 3 characters: Piece Abbrev, Col char and Row char.
 * EG: NE3, kG7.
 */
public class Move {
    
    private BoardButton old;
    private BoardButton n3w;
    
    private Piece piece;
    private boolean isWhite;
    
    private Player whitePlayer;
    private Player blackPlayer;
    
    private Board b;
    
    /** @author Henry Rheault
    *
    * Commits a move to a gamer's move list. Assumes move is valid when being called.
    * Try-Catch for good measure and debugging ease to see if the error is within Move or elsewhere.
    * 
    * Adds a move to a particular gamer's move list for demo purposes. 
    */
    public Move(Piece p, char row, char column) throws Exception {
        try{
            
            BoardButton[][] GameBoard = b.getGameBoard();          //Fetch gameboard object
            
            String s = p.getLocation();
            char loc[] = s.toCharArray();
            int i = (int)loc[0];                                    //Number column
            int j = (int)loc[1]; 
            
            //  10/27/19: Update and make sure with new grid layout this reports/converts the correct grid notations! 
            // A1 - 0,0; H8 - 7,7, etc.
            int m = (int)row;
            int n = (int)column;
            this.old = GameBoard[i][j];
            this.n3w = GameBoard[m][n];
            
            System.out.println(p.getAbbrev() + " moved from " + loc[0] + loc[1] + " to " + row + column + ".");            
            
            String move = String.valueOf(p.getAbbrev());
            move = move + row + column;
            if (p.isWhite()) whitePlayer.addMove(move);
            else blackPlayer.addMove(move);
            
            //Game.update();
            
        } catch (Exception e) {
            System.out.println("Invalid move constructor. Try again.");
        }
    }
       public Move (Piece p, BoardButton button) throws Exception {          //Overloaded constructor, simply declare a boardbutton to attempt a move to
           try{
           BoardButton[][] GameBoard = b.getGameBoard();          //Fetch gameboard object
            
            String s = p.getLocation();
            char loc[] = s.toCharArray();
            int i = (int)loc[0];                                    //Number column
            int j = (int)loc[1]; 
               
            this.old = GameBoard[i][j];
            this.n3w = button;
            String abbrev = n3w.getAbbreviation();
            System.out.println(p.getAbbrev() + " moved from " + loc[0] + loc[1] + " to " + abbrev + ".");
            
            String move = String.valueOf(p.getAbbrev());
            move = move + abbrev;
            if (p.isWhite()) whitePlayer.addMove(move);
            else blackPlayer.addMove(move);
            
            
            
           } catch (Exception e) {
               System.out.println("Invalid move constructor taking board square. Try again.");
               e.printStackTrace();
       }}
}