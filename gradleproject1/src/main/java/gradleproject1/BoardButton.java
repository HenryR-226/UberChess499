
package gradleproject1;

/**
 * @author Tyrone Lamar
 */
public class BoardButton {

    
    int row;
    int column;
    private Piece piece;
    /*
    private static final int PAWN=1;
    private static final int KNIGHT=2;
    private static final int BISHOP=3;
    private static final int ROOK=4;
    private static final int QUEEN = 5;
    private static final int KING = 6;
    private static final int NULL=0;
    //specific piece value stored here
*/   
    
    private boolean isClicked;             //is specific square clicked
    static private boolean somethingClicked;    //is anything on the board clicked, can't be static
    
    
    //Gameboard
    
    
    static private char c;
    static private short rowOffset = 1;           //idk why I put this here as a global since I only need it in draw()
    
    public Piece getPiece(){
        return piece;
    }
    
    public void setPiece(Piece p){
        this.piece = p;
    }
    
    
    
    public void clicked(){
        
    }
    
    public BoardButton(int row, int column){
        this.row=row;
        this.column=column;
       
    }
    
 
    
    //Testable Main, mostly to check println offsets.
    public static void Main(String[] args){      
      
       
    }
    
  
     /*  
        
        GameBoard[0][0] = new BoardButton(0,0);
        GameBoard[0][1] = new BoardButton(0,1);
        GameBoard[0][2] = new BoardButton(0,2);
        GameBoard[0][3] = new BoardButton(0,3);
        GameBoard[0][4] = new BoardButton(0,4);
        GameBoard[0][5] = new BoardButton(0,5);
        GameBoard[0][6] = new BoardButton(0,6);
        GameBoard[0][7] = new BoardButton(0,7);

        
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
    }
}

*/
}