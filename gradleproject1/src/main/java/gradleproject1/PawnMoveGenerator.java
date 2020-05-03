package gradleproject1;

import java.util.ArrayList;

public class PawnMoveGenerator implements Runnable {

    boolean firstMove;
    boolean team;
    Piece p;
    public ArrayList<BoardButton> result = new ArrayList<BoardButton>();

    String location;
    int x; int y; int ctrx; int ctry;

    BoardButton[][] board = Board.GameBoard;

    public PawnMoveGenerator(Piece p){
        this.p = p;
        team = p.isWhite;
    }

    public void init(){
        location = p.getLocation();
        firstMove=p.firstMove();

        char[] c = location.toCharArray();
        x = ((int) c[0] - 'A');
        y = (int) c[1] - '0' - 1;
        ctrx = x;
        ctry = y;
    }

    @Override
    public void run() {
        if (team){
            if (x == 0){
                if (board[1][y++].isFull() && board[1][y++].getPiece().isWhite()!=team) result.add(board[1][y++]);
            } else if (x==7){
                if (board[1][y++].isFull() && board[6][y++].getPiece().isWhite()!=team) result.add(board[6][y++]);
            } else {
                if (board[1][y++].isFull() && board[1][y++].getPiece().isWhite()!=team) result.add(board[1][y++]);
                if (board[1][y++].isFull() && board[6][y++].getPiece().isWhite()!=team) result.add(board[6][y++]);
            } if (firstMove) if (!board[x][y+2].isFull()) result.add(board[x][y+2]);
            if (!board[x][y++].isFull()) result.add(board[x][y++]);
            if (y==5){      //En Passant Capture
                if (board[x++][5].getPiece().getAbbrev()=='p' && board[x++][4].getPiece().getRank()==1) result.add(board[x++][4]);
                if (board[x--][5].getPiece().getAbbrev()=='p' && board[x--][4].getPiece().getRank()==1) result.add(board[x--][4]);
                }
            } else {
            if (x == 0){
                if (board[1][y--].isFull() && board[1][y--].getPiece().isWhite()!=team) result.add(board[1][y--]);
            } else if (x==7){
                if (board[1][y--].isFull() && board[6][y--].getPiece().isWhite()!=team) result.add(board[6][y--]);
            } else {
                if (board[1][y--].isFull() && board[1][y--].getPiece().isWhite()!=team) result.add(board[1][y--]);
                if (board[1][y--].isFull() && board[6][y--].getPiece().isWhite()!=team) result.add(board[6][y--]);
            } if (firstMove) if (!board[x][y-2].isFull()) result.add(board[x][y-2]);
            if (!board[x][y--].isFull()) result.add(board[x][y--]);
            if (y==5){      //En Passant Capture
                if (board[x++][3].getPiece().getAbbrev()=='p' && board[x++][3].getPiece().getRank()==1) result.add(board[x++][3]);
                if (board[x--][3].getPiece().getAbbrev()=='p' && board[x--][3].getPiece().getRank()==1) result.add(board[x--][3]);
            }
        }
    }
}
