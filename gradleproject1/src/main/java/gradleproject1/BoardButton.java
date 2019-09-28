/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradleproject1;

/**
 * @author Tyrone Lamar
 */
public class BoardButton {
    static final int rows = 8;
    static final int columns = 8;
    
    int row;
    int column;
    
    //enum for piece values
    private static final int PAWN = 1;
    private static final int KNIGHT = 2;
    private static final int BISHOP = 3;
    private static final int ROOK = 4;
    private static final int QUEEN = 5;
    private static final int KING = 6;
    //specific piece value stored here
    private int pieceNumber;
    
    boolean isClicked;      //is specific square clicked
    boolean isWhite;        //is specific square occupied by white piece
    private static boolean somethingClicked;    //is anything on the board clicked
    
    //Gameboard
    private BoardButton[][] GameBoard = new BoardButton[rows][columns];
    
    public int getPiece(){
        return this.pieceNumber;
    }
    
    //Draws out Ascii art of the gameboard
    public void draw(){
        char c;
        for (int i = 0; i < 8; i++){
            System.out.println("[");
            for (int j = 0; j < 8; j++){
                c = (char)GameBoard[i][j].getAbbrev(pieceNumber);
                if(isWhite) c=java.lang.Character.toUpperCase(c);
                System.out.print("," + c + ",");
            }
            System.out.println("]");
        }    
    }    
    
    public char getAbbrev(int pieceNo){
        switch(pieceNo) {
            case 1:
                return 'p';
            case 2:    
                return 'n';
            case 3:    
                return 'b';
            case 4:
                return 'r';
            case 5:    
                return 'q';
            case 6:    
                return 'k';
            default:    
                return '-';
        }    
    }
    
    public void clicked(){
        
    }
    
    public BoardButton(int row, int column, int piece){
        this.row=row;
        this.column=column;
        this.pieceNumber=piece;
    }
    
}


