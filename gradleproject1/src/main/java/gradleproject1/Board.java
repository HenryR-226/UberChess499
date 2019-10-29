package gradleproject1;
import java.util.*;
public class Board {
     
    private Player whitePlayer;
    private Player blackPlayer;
    
    public Board(Player whitePlayer, Player blackPlayer){
        this.blackPlayer=blackPlayer;
        this.whitePlayer=whitePlayer;
    }
    
    private BoardButton[][] GameBoard = new BoardButton[8][8];
   
    private ArrayList<Move> possibleMoves;
    
    
    /**
     * @Author Henry Rheault
     *
     * MASTER MOVE METHOD. Calls getPieceList for a particular gamer to get their list of pieces on the board.
     * Then calls Piece.getMoves() to return a semi-filtered list.
     * of candidate moves. getMoves() in piece calls each piece's individual
     * submethods for specific move generation, returns back to Piece.getMoves()
     * to be returned to here.
     *
     * This method then sifts down to make sure the move isn't illegal, converts
     * it as chess notation, and pushes it to array list as a string. This
     * processes all possible moves for a given team.
     */
    public ArrayList<Move> getMoves(Player p) throws Exception {
        ArrayList<ArrayList<BoardButton>> moveSquareList = new ArrayList<ArrayList<BoardButton>>();             //List of Lists, one list for each piece's possible moves
        Move move;                                                            //Return string of given move
        for (Piece piece : p.getPieceList()) {                                   //For each piece in player's list
            moveSquareList.add(piece.getMoves(piece));                              //Add a list of possible board squares that piece can move to
            for (ArrayList<BoardButton> al : moveSquareList) {                      //For each list of boardbuttons in the movesquare list
                for (BoardButton b : al) {                                          //For each boadbutton IN said list of boardbuttons
                    move = new Move(piece, b);                                  //Construct the move
                    possibleMoves.add(move);                                    //Post the move to final move list
                } //if statement for king not in check)
            }     //This will NOT report a space immediately forward of the pawn occupied by enemy piece as valid move.
        }         //This will be tested/weeded out in the Pawn specific candidate generation moves to keep this clean.

        return possibleMoves;                        //Return the final move list. AI selects from this randomly and potential move to be made MUST BE in here
    }
    
    /**
     * @author Henry Rheault Returns specific BoardButtons given the arguments
     * of X & Y coordinates.
     */
    public BoardButton getBoardButton(int x, int y) {
        try {
            BoardButton result = GameBoard[x][y];
            return result;
        } catch (Exception e) {
            System.out.println("Getting board button failed. Probably out of bounds so no such button.");
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    /*
     * Same as above but in chess notation. Don't know which one will be more 
     * convenient or used more.
     */
    public BoardButton getBoardButton(char row, char col) {
        try {
            int x = ((int) row - 'A');
            int y = (int) col;
            BoardButton result = GameBoard[x][y];
            return result;
        } catch (Exception e) {
            System.out.println("Getting board button failed. Probably passed bad Chess notation input.");
            e.printStackTrace();
        } finally {
            return null;
        }
    }
    
    
    /**@author Henry Rheault
     * Method to take in string of piece location and natively/abstractly convert to
     * array memory location.
     * 
     * Needs to be fixed as of 10/27 as the board layout has been altered.
     * 
     * @deprecated 
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
       
        a=GameBoard[0][1];
        a.setPiece(Pawn1);
        a=GameBoard[1][1];
        a.setPiece(Pawn2);
        a=GameBoard[2][1];
        a.setPiece(Pawn3);
        a=GameBoard[3][1];
        a.setPiece(Pawn4);
        a=GameBoard[4][1];
        a.setPiece(Pawn5);
        a=GameBoard[5][1];
        a.setPiece(Pawn6);
        a=GameBoard[6][1];
        a.setPiece(Pawn7);
        a=GameBoard[7][1];
        a.setPiece(Pawn8);
       
        a=GameBoard[0][0];
        a.setPiece(Rook1);
        a=GameBoard[7][0];
        a.setPiece(Rook2);
        a=GameBoard[1][0];
        a.setPiece(Knight1);
        a=GameBoard[6][0];
        a.setPiece(Knight2);
        a=GameBoard[2][0];
        a.setPiece(BishopLight);
        a=GameBoard[5][0];
        a.setPiece(BishopDark);
        a=GameBoard[4][0];
        a.setPiece(Kang);
        a=GameBoard[3][0];
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
       
        a=GameBoard[0][6];
        a.setPiece(pawn1);
        a=GameBoard[1][6];
        a.setPiece(pawn2);
        a=GameBoard[2][6];
        a.setPiece(pawn3);
        a=GameBoard[3][6];
        a.setPiece(pawn4);
        a=GameBoard[4][6];
        a.setPiece(pawn5);
        a=GameBoard[5][6];
        a.setPiece(pawn6);
        a=GameBoard[6][6];
        a.setPiece(pawn7);
        a=GameBoard[7][6];
        a.setPiece(pawn8);
       
        a=GameBoard[0][7];
        a.setPiece(rook1);
        a=GameBoard[7][7];
        a.setPiece(rook2);
        a=GameBoard[1][7];
        a.setPiece(knight1);
        a=GameBoard[6][7];
        a.setPiece(knight2);
        a=GameBoard[5][7];
        a.setPiece(bishopDark);
        a=GameBoard[2][7];
        a.setPiece(bishopLight);
        a=GameBoard[4][7];
        a.setPiece(kang);
        a=GameBoard[3][7];
        a.setPiece(queen);
       
       
    }
     
    //Draws out Ascii art of the gameboard. To be called after every successfully committed move.
    //MASSIVE overhaul/Bugfix on the part of Ryan and Henry on 10/27/2019
    public void draw() {
        BoardButton b;
        byte rowOffset = 7;
        char c;
        System.out.println("   A B C D E F G H");
        for (int j = 7; j >= 0; j--) {
            System.out.print((rowOffset) + " [");       //Rows starting from 8
            for (int i = 0; i <= 7; i++) {
                b = GameBoard[i][j];
                if (b.getPiece() != null) {
                    c = (char) b.getPiece().getAbbrev();
                    System.out.print(c + ",");
                } else if (b.isWhite()) {
                    System.out.print("-,");
                } else {
                    System.out.print("+,");
                }
 
            }
            System.out.println("] " + (rowOffset));
            rowOffset--;
        }
        System.out.println("   A B C D E F G H");     //Letter Grid      
    }
       
   
    public BoardButton[][] getGameBoard(){
        return GameBoard;
    }      
       
}