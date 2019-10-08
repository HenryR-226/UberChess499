package gradleproject1;
/**
 * @author Tyrone Lamar
 */
public class BoardButton {
    int row;
    int column;
    private Piece piece;
    private String abbreviation;
    private boolean isWhite;                  //Is the square dark or light
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
    
    /**
     *@author Henry Rheault 
     * 
     * Method for assigning the boardbutton a chessboard abbreviation that it can
     * simply report back. Verifies format with code cloned from GridOffset
     */
    public void setAbbreviation(String s){
        boolean userErrorFlag = false;
        try{
            assert (s.length() == 2);
            char[] temp = s.toCharArray();

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
            this.abbreviation = s;
            
        } catch (Exception e){
            if (s.length()!=2 || userErrorFlag){
                System.out.println("You did something wrong. Invalid format.");
            }    
            else {
                System.out.println("Some shit went down.");
                e.printStackTrace();
            }    
        }    
    }    
    
    public String getAbbreviation(){
        return this.abbreviation;
    }
    
    public void clicked(){
        
    }
    
    public BoardButton(int row, int column){
        this.row=row;
        this.column=column;
       
    }
    
   public void setColor(boolean c){
       this.isWhite = c;
   }
   public boolean isWhite(){
       return this.isWhite;
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