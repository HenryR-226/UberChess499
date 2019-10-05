
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
    
    private boolean isClicked;             //is specific square clicked
    private final boolean isWhite;        //is specific square white in color, else false
    private boolean isPieceWhite;       //Is piece on specific square white, else false
    static private boolean somethingClicked;    //is anything on the board clicked, can't be static
    
    private final char letter;
    private final char number;
    
    //Gameboard
    static private BoardButton[][] GameBoard = new BoardButton[rows][columns];
    
    static private char c;
    static private short rowOffset;
    
    public static int getPiece(){
        return pieceNumber;
    }
    
    //Draws out Ascii art of the gameboard
    public void draw(){
        for (int i = 0; i < 8; i++){
            System.out.println(rowOffset + " [");       //Rows starting from 8
            for (int j = 0; j < 8; j++){
                c = (char)GameBoard[i][j].getAbbrev(pieceNumber);
                if(isPieceWhite) c=java.lang.Character.toUpperCase(c);
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
    public BoardButton(int row, int column, int piece, char letter, char number){
        this.row=row;
        this.column=column;
        this.pieceNumber=piece;
        this.letter = letter;
        this.number=number;
        this.isPieceWhite=true;
    }
    
    //Black piece constructor
    public BoardButton(int row, int column, int piece, char letter, char number, boolean color){
        this.row=row;
        this.column=column;
        this.pieceNumber=piece;
        this.letter=letter;
        this.number=number;
        this.isPieceWhite=false;
    }
    
    //Testable Main, mostly to check println offsets.
    public static void Main(String[] args){
       initializeGame();      
    }
    
    public static void initializeGame(){                          //Black back line
        GameBoard[0][0] = new BoardButton(0,0,4,'A','8', false);
        GameBoard[0][1] = new BoardButton(0,1,2,'B', '8', false);
        GameBoard[0][2] = new BoardButton(0,2,3,'C', '8', false);
        GameBoard[0][3] = new BoardButton(0,3,5,'D', '8', false);
        GameBoard[0][4] = new BoardButton(0,4,6,'E', '8', false);
        GameBoard[0][5] = new BoardButton(0,5,3,'F', '8', false);
        GameBoard[0][6] = new BoardButton(0,6,2,'G', '8', false);
        GameBoard[0][7] = new BoardButton(0,7,4,'H', '8', false);
        for (byte i =0; i<8; i++){                          //Black pawn row
            char x = (char)('A' + i);              //Letter col var
            //char y = (char)(i+1);                           //Number row var
            GameBoard[1][i] = new BoardButton(1,i,1,x,'7', false);
        }
        for (int i =2; i<6; i++){                           //Empty No-Man's Land
            for (int j = 0; j<8; j++){
                char x = (char)('A' + i-2);                    //Row letters
                char y = (char)(j+1);
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


