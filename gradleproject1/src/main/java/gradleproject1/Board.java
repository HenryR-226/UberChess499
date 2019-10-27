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
        Piece Pawn1 = new Pawn("Pawn1", true);
        Piece Pawn2 = new Pawn("Pawn2", true);
        Piece Pawn3 = new Pawn("Pawn3", true);
        Piece Pawn4 = new Pawn("Pawn4", true);
        Piece Pawn5 = new Pawn("Pawn5", true);
        Piece Pawn6 = new Pawn("Pawn6", true);
        Piece Pawn7 = new Pawn("Pawn7", true);
        Piece Pawn8 = new Pawn("Pawn8", true);
        
        
        Piece Rook1 = new Rook("Rook1", true);
        Piece Rook2 = new Rook("Rook2", true);
        Piece BishopLight = new Bishop("BishopLight", true);
        Piece BishopDark = new Bishop("BishopDark", true);
        Piece Knight1 = new Knight("Knight1", true);
        Piece Knight2 = new Knight("Knight2", true);
        Piece Queen = new Queen("Queen", true);
        Piece Kang = new King("Kang", true);
        BoardButton a;
        
        //set pawns
        a=GameBoard[1][0];
        a.setPiece(Pawn1);
        a=GameBoard[1][1];
        a.setPiece(Pawn2);
        a=GameBoard[1][2];
        a.setPiece(Pawn3);
        a=GameBoard[1][3];
        a.setPiece(Pawn4);
        a=GameBoard[1][4];
        a.setPiece(Pawn5);
        a=GameBoard[1][5];
        a.setPiece(Pawn6);
        a=GameBoard[1][6];
        a.setPiece(Pawn7);
        a=GameBoard[1][7];
        a.setPiece(Pawn8);
        
        a=GameBoard[0][0];
        a.setPiece(Rook1);
        a=GameBoard[0][7];
        a.setPiece(Rook2);
        a=GameBoard[0][1];
        a.setPiece(Knight1);
        a=GameBoard[0][6];
        a.setPiece(Knight2);
        a=GameBoard[0][2];
        a.setPiece(BishopLight);
        a=GameBoard[0][5];
        a.setPiece(BishopDark);
        a=GameBoard[0][4];
        a.setPiece(Kang);
        a=GameBoard[0][3];
        a.setPiece(Queen);
    }
    
     public void initBlack(){
        Piece pawn1 = new Pawn("pawn1", false);
        Piece pawn2 = new Pawn("pawn2", false);
        Piece pawn3 = new Pawn("pawn3", false);
        Piece pawn4 = new Pawn("pawn4", false);
        Piece pawn5 = new Pawn("pawn5", false);
        Piece pawn6 = new Pawn("pawn6", false);
        Piece pawn7 = new Pawn("pawn7", false);
        Piece pawn8 = new Pawn("pawn8", false);
        
        
        Piece rook1 = new Rook("rook1", false);
        Piece rook2 = new Rook("rook2", false);
        Piece bishopLight = new Bishop("bishopLight", false);
        Piece bishopDark = new Bishop("bishopDark", false);
        Piece knight1 = new Knight("knight1", false);
        Piece knight2 = new Knight("knight2", false);
        Piece queen = new Queen("queen", false);
        Piece kang = new King("kang", false);
        
        
        BoardButton a;
        
        a=GameBoard[6][0];
        a.setPiece(pawn1);
        a=GameBoard[6][1];
        a.setPiece(pawn2);
        a=GameBoard[6][2];
        a.setPiece(pawn3);
        a=GameBoard[6][3];
        a.setPiece(pawn4);
        a=GameBoard[6][4];
        a.setPiece(pawn5);
        a=GameBoard[6][5];
        a.setPiece(pawn6);
        a=GameBoard[6][6];
        a.setPiece(pawn7);
        a=GameBoard[6][7];
        a.setPiece(pawn8);
        
        a=GameBoard[7][0];
        a.setPiece(rook1);
        a=GameBoard[7][7];
        a.setPiece(rook2);
        a=GameBoard[7][1];
        a.setPiece(knight1);
        a=GameBoard[7][6];
        a.setPiece(knight2);
        a=GameBoard[7][2];
        a.setPiece(bishopDark);
        a=GameBoard[7][5];
        a.setPiece(bishopLight);
        a=GameBoard[7][4];
        a.setPiece(kang);
        a=GameBoard[7][3];
        a.setPiece(queen);
        
        
    }
     
       //Draws out Ascii art of the gameboard. To be called after every successfully committed move
    public static void draw(){
        BoardButton b;
        byte rowOffset = 7;
        char c;
        System.out.println("   A B C D E F G H");
        for (int i = 7; i >= 0; i--){
            System.out.print((rowOffset+1) + " [");       //Rows starting from 8
            for (int j = 7; j >=0; j--){
                b = GameBoard[i][j];
                if(b.getPiece()!=null){
                c = (char) b.getPiece().getAbbrev();
                System.out.print(c + ",");
                } 
                else if (b.isWhite()) System.out.print("-,");
                else System.out.print("+,");
            
            }
            System.out.println("] " + (rowOffset+1));
            rowOffset--;
        }   
        System.out.println("   A B C D E F G H");     //Letter Grid      
    }        
        
    
    public BoardButton[][] getGameBoard(){
        return GameBoard;
    }       
       
}