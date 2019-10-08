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
        
        
        
        
        /*
        
        
        
            for (byte i =0; i<8; i++){                          //Black pawn row
            char x = (char)('A' + i);              //Letter col var
            //char y = (char)(i+1);                           //Number row var
            GameBoard[1][i] = new BoardButton(1,i,1,x,'7', false);
        */
     
  
      /*
        for (int i =2; i<6; i++){                           //Empty No-Man's Land
            char x,y;
            for (int j = 0; j<8; j++){
                 x = (char)('A' + i-2);                    //Row letters
                 y = (char)(j+1);
                GameBoard[i][j] = new BoardButton(i, j, 0, x, y);
            }
        }      
        for (int i=0; i<8; i++){                            //White pawn row
            char x = (char)('A'+i);
            GameBoard[6][i] = new BoardButton(6, i, 1, x, '7');
        }                                                   //White back line
        GameBoard[7][0] = new BoardButton(0,0,4, 'A', '1');
        GameBoard[7][1] = new BoardButton(0,1,2, 'B', '1');
        GameBoard[7][2] = new BoardButton(0,2,3, 'C', '1');
        GameBoard[7][3] = new BoardButton(0,3,5, 'D', '1');
        GameBoard[7][4] = new BoardButton(0,4,6, 'E', '1');
        GameBoard[7][5] = new BoardButton(0,5,3, 'F', '1');
        GameBoard[7][6] = new BoardButton(0,6,2, 'G', '1');
        GameBoard[7][7] = new BoardButton(0,7,4, 'H', '1');
    }*/
     
       //Draws out Ascii art of the gameboard
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