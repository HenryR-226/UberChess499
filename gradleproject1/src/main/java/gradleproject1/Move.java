/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradleproject1;

/**
 *
 * @author Tyrone Lamar
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
            //Check according to rules for movement
            //Throw out exception if not valid move
            
            BoardButton[][] GameBoard = b.getGameBoard();          //Fetch gameboard object
            
            String s = p.getLocation();
            char loc[] = s.toCharArray();
            int i = (int)loc[0];                                    //Number column
            int j = (int)loc[1]; 
            
            int m = 8 - (int)row;
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
}