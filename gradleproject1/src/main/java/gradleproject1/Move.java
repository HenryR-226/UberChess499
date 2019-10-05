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
    
    //From square TO square
    public Move(Piece p, char row, char column, char r, char c) throws Exception {
        try{
            //Check according to rules for movement
            //Throw out exception if not valid move
            
            BoardButton[][] GameBoard = b.getGameBoard();          //Fetch gameboard object
            int i = 8 -(int)row;                                    //Number row
            int j = (int)column; 
            
            int m = 8 - (int)r;
            int n = (int)c;
            this.old = GameBoard[i][j];
            this.n3w = GameBoard[m][n];
            
            System.out.println(p.getAbbrev() + " moved from " + row + column + " to " + r + c + ".");            
            
            String move = String.valueOf(p.getAbbrev());
            move = move + r + c;
            if (p.isWhite()) whitePlayer.addMove(move);
            else blackPlayer.addMove(move);
            
            //Game.update();
            
        } catch (Exception e) {
            System.out.println("Invalid move. Try again.");
        }
    }
}