/*
 * To change this lic
    
    //enum for piece values
    private static final int PAWN = 1;
    private static final int KNIGHT = 2;
    private static final int BISHOP = 3;
    private static final int ense header, choose License Headers in Project Properties.
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
    
    private static final int PAWN=1;
    private static final int KNIGHT=2;
    private static final int BISHOP=3;
    private static final int ROOK=4;
    private static final int QUEEN = 5;
    private static final int KING = 6;
    private static final int NULL=0;
    //specific piece value stored here
    private static int pieceNumber;
    
    static boolean isClicked;      //is specific square clicked
    static boolean isWhite;        //is specific square white in color, else false
    static boolean isPieceWhite;    //Is piece on specific square white, else false
    private boolean somethingClicked;    //is anything on the board clicked, can't be static
    
    //Gameboard
    static private BoardButton[][] GameBoard = new BoardButton[rows][columns];
    
    static private char c;
    static private short rowOffset;
    
    public static int getPiece(){
        return pieceNumber;
    }
    
    //Draws out Ascii art of the gameboard
    public static void draw(){
        for (int i = 0; i < 8; i++){
            System.out.println(rowOffset + " [");       //Rows starting from 8
            for (int j = 0; j < 8; j++){
                c = (char)GameBoard[i][j].getAbbrev(pieceNumber);
                if(isWhite) c=java.lang.Character.toUpperCase(c);
                System.out.print("," + c + ",");
            }
            System.out.println("]");
            rowOffset--;
        }   
        System.out.println("   A  B  C  D  E  F  G  H");     //Letter Grid      
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
    
    //White piece constructor
    public BoardButton(int row, int column, int piece){
        this.row=row;
        this.column=column;
        this.pieceNumber=piece;
    }
    
    //Black piece constructor
    public BoardButton(int row, int column, int piece, boolean color){
        this.row=row;
        this.column=column;
        this.pieceNumber=piece;
        this.isPieceWhite=false;
    }
    
    //Testable Main, mostly to check println offsets.
    public static void Main(String[] args){
       initializeGame();
       draw();  
    }
    
    public static void initializeGame(){                          //Black back line
        GameBoard[0][0] = new BoardButton(0,0,4, false);
        GameBoard[0][1] = new BoardButton(0,1,2, false);
        GameBoard[0][2] = new BoardButton(0,2,3, false);
        GameBoard[0][3] = new BoardButton(0,3,5, false);
        GameBoard[0][4] = new BoardButton(0,4,6, false);
        GameBoard[0][5] = new BoardButton(0,5,3, false);
        GameBoard[0][6] = new BoardButton(0,6,2, false);
        GameBoard[0][7] = new BoardButton(0,7,4, false);
        for (int i =0; i<8; i++){                          //Black pawn row
            GameBoard[1][i] = new BoardButton(1,i,1,false);
        }
        for (int i =2; i<6; i++){                           //Empty No-Man's Land
            for (int j = 0; j<8; j++){
                GameBoard[i][j] = new BoardButton(i, j, 0);
            }
        }      
        for (int i=0; i<8; i++){                            //White pawn row
            GameBoard[6][i] = new BoardButton(6, i, 1);
        }                                                   //White back line
        GameBoard[7][0] = new BoardButton(0,0,4);
        GameBoard[7][1] = new BoardButton(0,1,2);
        GameBoard[7][2] = new BoardButton(0,2,3);
        GameBoard[7][3] = new BoardButton(0,3,5);
        GameBoard[7][4] = new BoardButton(0,4,6);
        GameBoard[7][5] = new BoardButton(0,5,3);
        GameBoard[7][6] = new BoardButton(0,6,2);
        GameBoard[7][7] = new BoardButton(0,7,4);
    }
}


