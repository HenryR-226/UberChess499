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
import java.util.*;
public class Board {
     
    static private BoardButton[][] GameBoard = new BoardButton[8][8];
    
    /**@author Henry Rheault
     * Method to take in string of piece location and natively/abstractly convert to 
     * array memory location
     */
    
    public BoardButton toArray(String s){
        BoardButton b;
        int x=-1;
        int y=-1;
        try{
            assert (s.length() == 2);
            char[] temp = s.toCharArray();

            //Take string, convert to chars, and get Memory Array location from the Chess String location
            char col = temp[0];                             //A-H, not case sensitive, input argument
            char r0w = temp[1];                             //1-8, input argument
            col = Character.toUpperCase(col);               //Ensures that the column character is upper case for ease of assert
            if (!(Character.isLetter(col) || col>'H')) assert (Character.isLetter(col) && col<='H');  //Set user error flag and break out of try if not  
            else x = col - 'A';
                      
                
            if (r0w >8 || r0w<0) assert (r0w <=8 && r0w>0);                   //Set user error flag and break out of try if not
            else y = 8-r0w;  
            b = GameBoard[x][y];
            return b;
        } catch (Exception e) {
            System.out.println("You should probably not call publically available methods with random input.");
            e.printStackTrace();
        }  finally { return null;  }
    }    
    
    public void initBoard(){
        boolean white = false;
             for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    BoardButton b= new BoardButton(i,j);
                    b.setColor(white);
                    GameBoard[i][j] =b;
                    white = !white;                         //Flip color
                 
                 }                 
            white = !white;    
            }
    } 
    
    
    
    public void initWhite(){
        Piece pawn = new Pawn(1, true);
        Piece rook = new Rook(4, true);
        Piece bishop = new Bishop(3, true);
        Piece knight = new Knight(2, true);
        Piece queen = new Queen(5, true);
        Piece kang = new King(6, true);
        BoardButton a;
        
        //set pawns
        for(int i = 0; i<8; i++){
            a=GameBoard[1][i];
            a.setPiece(pawn);
        }
        
        a=GameBoard[0][0];
        a.setPiece(rook);
        a=GameBoard[0][7];
        a.setPiece(rook);
        a=GameBoard[0][1];
        a.setPiece(knight);
        a=GameBoard[0][6];
        a.setPiece(knight);
        a=GameBoard[0][2];
        a.setPiece(bishop);
        a=GameBoard[0][5];
        a.setPiece(bishop);
        a=GameBoard[0][4];
        a.setPiece(kang);
        a=GameBoard[0][3];
        a.setPiece(queen);
        
        
    
       
    }
    
     public void initBlack(){
        Piece blackPawn = new Pawn(1, false);
        Piece blackRook = new Rook(4, false);
        Piece blackBishop = new Bishop(3, false);
        Piece blackKnight = new Knight(2, false);
        Piece blackQueen = new Queen(5, false);
        Piece blackKang = new King(6, false);
         BoardButton a;
         
        //set pawns
        for(int i = 0; i<8; i++){
            a=GameBoard[6][i];
            a.setPiece(blackPawn);
        }
        
        a=GameBoard[7][0];
        a.setPiece(blackRook);
        a=GameBoard[7][7];
        a.setPiece(blackRook);
        a=GameBoard[7][1];
        a.setPiece(blackKnight);
        a=GameBoard[7][6];
        a.setPiece(blackKnight);
        a=GameBoard[7][2];
        a.setPiece(blackBishop);
        a=GameBoard[7][5];
        a.setPiece(blackBishop);
        a=GameBoard[7][4];
        a.setPiece(blackKang);
        a=GameBoard[7][3];
        a.setPiece(blackQueen);
        
        
    }
     
       //Draws out Ascii art of the gameboard. To be called after every successfully committed move
    public static void draw(){
        BoardButton b;
        byte rowOffset = 7;
        char c;
        for (int i = 7; i >= 0; i--){
            System.out.print((rowOffset+1) + " [");       //Rows starting from 8
            for (int j = 7; j >=0; j--){
                b = GameBoard[i][j];
                if(b.getPiece()!=null){
                c = (char) b.getPiece().getAbbrev();
                if(b.getPiece().isWhite()) c=java.lang.Character.toUpperCase(c);
                System.out.print(c + ",");
                } 
                else if (b.isWhite()) System.out.print("-,");
                else System.out.print("+,");
            
            }
            
        
            System.out.println("]");
            rowOffset--;
        }   
        System.out.println("   A B C D E F G H");     //Letter Grid      
    }        
        
    
    public BoardButton[][] getGameBoard(){
        return GameBoard;
    }       
       
}