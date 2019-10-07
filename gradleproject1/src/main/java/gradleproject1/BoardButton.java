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

    public BoardButton() {
    }
    
    private boolean isClicked;             //is specific square clicked
    static private boolean somethingClicked;    //is anything on the board clicked, can't be static
     
    //Gameboard
    
    static private char c;
    static private short rowOffset = 1;           //idk why I put these here as globals since I only need it in draw()
    
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
    
     //Grid offset arrays. White at bottom. For black pieces flips position and reads from that value of array
    private static double[][] gridOffsetPawn = new double[][]
    {
        {0, 0, 0, 0, 0, 0, 0, 0},
        {5, 5, 5, 5, 5, 5, 5, 5},
        {1, 1, 2, 3, 3, 2, 1, 1},
        {0.5, 0.5, 1, 2.5, 2.5, 1, 0.5, 0.5},
        {0, 0, 0, 2, 2, 0, 0, 0},
        {0.5, -0.5, -1, 0, 0, -1, -0.5, 0.5},
        {0.5, 1, 1, -2, -2, 1, 1, 0.5},
        {0, 0, 0, 0, 0, 0, 0, 0}
    };
    
    private static double[][] gridOffsetKnight = new double[][]
    {
        {-5, -4, -3, -3, -3, -3, -4, -5},
        {-4, -2, 0, 0, 0, 0, -2, -4},
        {-3, 0, 1, 1.5, 1.5, 1, 0, -3},
        {-3, 0.5, 1.5, 2, 2, 1.5, 0.5, -3},
        {-3, 0.5, 1.5, 2, 2, 1.5, 0.5, -3},
        {-3, 0.5, 1, 1.5, 1.5, 1, 0.5, -3},
        {-4, -2, 0, 0.5, 0.5, 0, -2, -4},
        {-5, -4, -3, -3, -3, -3, -4, -5}
        
    };
    
    private static double[][] gridOffsetBishop = new double[][]
    {
        {-2, -1, -1, -1, -1, -1, -1, -2},
        {-1, 0, 0, 0, 0, 0, 0, -1},
        {-1, 0, 0.5, 1, 1, 0.5, 0, -1},
        {-1, 0.5, 0.5, 1, 1, 0.5, 0.5, -1},
        {-1, 0, 1, 1, 1, 1, 0, -1},
        {-1, 1, 1, 1, 1, 1, 1, -1},
        {-1, 0.5, 0, 0, 0, 0, 0.5, -1},
        {-2, -1, -1, -1, -1, -1, -1, -2}
    };
            
    private static double[][] gridOffsetRook = new double[][]{
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0.5, 1, 1, 1, 1, 1, 1, 0.5},
        {-.5, 0, 0, 0, 0, 0, 0, -.5},
        {-.5, 0, 0, 0, 0, 0, 0, -.5},
        {-.5, 0, 0, 0, 0, 0, 0, -.5},
        {-.5, 0, 0, 0, 0, 0, 0, -.5},
        {-.5, 0, 0, 0, 0, 0, 0, -.5},  
        {0, 0, 0, 0.5, 0.5, 0, 0, 0}       
    };
    
    private static double[][] gridOffsetQueen = new double[][]{
        {-2, -1, -1, -0.5, -0.5, -1, -1, -2},
        {-1, 0, 0, 0, 0, 0, 0, -1},
        {-1, 0, 0.5, 0.5, 0.5, 0.5, 0, -1},
        {-0.5, 0, 0.5, 0.5, 0.5, 0.5, 0, -0.5},
        {0, 0, 0.5, 0.5, 0.5, 0.5, 0, -0.5},
        {-1, 0.5, 0.5, 0.5, 0.5, 0.5, 0, -1},
        {-1, 0, 0.5, 0, 0, 0, 0, -1},
        {-2, -1, -1, -0.5, -0.5, -1, -1, -2}   
    };
    
    private static double[][] gridOffsetKing = new double[][]{
        {-3, -4, -4, -5, -5, -4, -4, -3},
        {-3, -4, -4, -5, -5, -4, -4, -3},
        {-3, -4, -4, -5, -5, -4, -4, -3},
        {-3, -4, -4, -5, -5, -4, -4, -3},
        {-2, -3, -3, -4, -4, -3, -3, -2},
        {-1, -2, -2, -2, -2, -2, -2, -1},
        {2, 2, 0, 0, 0, 0, 2, 2},
        {2, 3, 1, 0, 0, 1, 3, 2} 
    };
    
    
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
            row = row2 + 8;                                  //Add 8 to convert chess layout to Memory layout
            
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
                return (double)0;
            }
        }
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