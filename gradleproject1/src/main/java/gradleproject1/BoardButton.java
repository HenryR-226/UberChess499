package gradleproject1;
import java.util.ArrayList;

public class BoardButton {
    GameState g;
    int row;
    int column;
    private Piece piece;
    private String abbreviation;
    private boolean isWhite;                  //Is the square dark or light
    private boolean isFull;                   //Is something there
    private boolean highlighted = false;              //Should I be highlighted right now?
    
    public void setGameState(GameState game){
        this.g = game;
    }  

    public BoardButton() {
    }
    
    private boolean isClicked;             //is specific square clicked
    static private boolean somethingClicked;    //is anything on the board clicked, can't be static
     
    //Gameboard
    
    static private char c;
    static private short rowOffset = 1;           //idk why I put these here as globals since I only need it in draw()
    
    
    public Piece getPiece() throws NullPointerException {
        try {
            return piece;
        } catch (NullPointerException npe) {
            return null;
        }
    }
    
    //Tested and works A: 11/1/2019
    //Converts STRING notation to ARRAY notation (col/row).
    public static ArrayList<Integer> toArray (String s){
        ArrayList<Integer> reeturn = new ArrayList<Integer>();
        char[] c = s.toCharArray();
        int x = ((int) c[0] - 65);
        reeturn.add(x);
        int y = (int) c[1] - 49;
        reeturn.add(y);
        return reeturn;
    }
    
    //Tested and works A : 11/1/2019
    //Converts ARRAY notation to STRING notation
    public static String toChess(ArrayList<Integer> xy){
        try {
            assert (xy.size()==2);
            String s = new String();
            int x = xy.get(0);
            int y = xy.get(1);
            x =(char) x + 65;
            s = s+String.valueOf(x);
            y = (char) y + 49;
            s = s+String.valueOf(y);
            return s;
        } catch (Exception e) {
            System.out.println("Input argument not correct length, or you did something more catastrophic.");
            e.printStackTrace();
            return null;
        }
    }
    
    //Assumes check has been run for empty square
    public void setPiece(Piece p){
        this.isFull = true;
        this.piece = p;
    }
    
    //Piece moves off voluntarily
    public void removePiece(){
        this.piece=null;
        this.isFull=false;
    }
    /**
     * @author Henry Rheault
     * 
     * Method for a piece being captured, takes argument of a piece doing the capturing.
     * Adds the piece to a player's lost pieces list and removes from their active piece list.
     * Then rewrites the piece on the given BB as the argument piece.
     */
    public void removePiece(Piece p){
        Player player; 
        boolean team = p.isWhite();
        if (team) player = g.getBlack();           //If p is white -> black lost piece
        else player = g.getWhite();                //If p is black -> white lost piece
        player.pieceCaptured(this.piece);
        this.piece = p;
        this.isFull=true;
    } 
    
    public boolean isFull(){
        return this.isFull;
    }
    
    /**
     *@author Henry Rheault 
     * 
     * Method for assigning the BoardButton a chessboard abbreviation that it can
     * simply report back. Verifies format with code cloned from GridOffset.
     * If not upper case when passed in it is converted to be such. MUST BE UPPERCASE
     * as arithmetic checks are being run on it compared to 'A' and 'H' both upper case.
     */
    public void setAbbreviation(String s){
        boolean userErrorFlag = false;
        System.out.println("String passed: " + s);
        try{
            assert (s.length() == 2);
            char[] temp = s.toCharArray();
            //FIXME 
            //Take string, convert to chars, and get Memory Array location from the Chess String location
            char col = (char) temp[0];                             //A-H, not case sensitive, input argument
            System.out.println("Col: " + col);
            char r0w = (char) (temp[1]);                             //1-8, input argument
            System.out.println("Row: " + row);
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
            String string = String.valueOf(col);
            this.abbreviation = string + String.valueOf(r0w);
           
        } catch (Exception e){
            if (s.length()!=2 || userErrorFlag){
                System.out.println("You did something wrong. Invalid format.");
                System.out.println("Input : " + s);
            }    
            else {
                System.out.println("Some shit went down.");
                System.out.println("Input : " + s);
                e.printStackTrace();
            }    
        }    
    }    
   
    //FIX ME
    //https://pastebin.com/N71TiVif
    public void setAbbreviation(int col, int row){
        try{
            assert (col>='A' && col<='H');
            assert (row>=1 && row<=8);
            System.out.println("Row is " + row);
            String s = String.valueOf((char) col);
            String s2 = s.concat(String.valueOf((char) row));
            System.out.println("S val passed: " + s2);
            
            setAbbreviation(s2);
           
        } catch (Exception e) {
            System.out.println("Invalid col & row passed.");
            System.out.println("Input : " + col + " " + row);
            e.printStackTrace();
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