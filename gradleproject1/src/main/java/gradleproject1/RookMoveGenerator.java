package gradleproject1;

import java.util.ArrayList;

public class RookMoveGenerator implements Runnable{
    static BoardButton[][] board = Board.GameBoard;

    public ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
    int task;
    boolean alpha;
    Piece p;
    String location;
    int x; int y; int ctrx; int ctry;
    BoardButton b = null;
    boolean team;

    public RookMoveGenerator(Piece me, int flag){
        this.p = me;
        this.task = flag;
    }

    public void init(){
        location = p.getLocation();
        char[] c = location.toCharArray();
        x = ((int) c[0] - 'A');
        y = (int) c[1] - '0' - 1;
        ctrx = x;
        ctry = y;
        team = p.isWhite();
    }

    @Override
    public void run(){
        switch(task){
            case 18:
                init();
                do {
                    ctrx++;
                    try {
                        b = board[ctrx][y]; // Go positive X down it's row
                        if (!b.isFull() || b.getPiece().isWhite() != p.isWhite() && ctrx < 8)
                            validSquares.add(b);
                    } catch (Exception e) {
                        break;
                    }
                } while (!b.isFull() && ctrx < 8);
                break;
            case 19:
                init();
                do {
                    try {
                        ctry++;
                        b = board[x][ctry]; // Go positive Y down it's col
                        if (!b.isFull() || b.getPiece().isWhite() != team && ctry < 8)
                            validSquares.add(b);
                    } catch (Exception e) {
                        break;
                    }
                } while (!b.isFull() && ctry < 8); // Stop at first occupied or out of bounds square
                break;
            case 20:
                init();
                do {
                    ctrx--;
                    try {
                        b = board[ctrx][y]; // Negative X down it's row
                        if (!b.isFull() || b.getPiece().isWhite() != p.isWhite() && ctrx > -1)
                            validSquares.add(b);
                    } catch (Exception e) {
                        break;
                    }
                } while (!b.isFull() && ctrx > -1); // Stop at first occupied or out of bounds square
                break;
            case 21:
                init();
                do {
                    ctry--;
                    try {
                        b = board[x][ctry]; // Go negative Y down it's col
                        if (!b.isFull() || b.getPiece().isWhite() != team && ctry > -1)
                            validSquares.add(b);
                    } catch (Exception e) {
                        break;
                    }
                } while (!b.isFull() && ctry > -1);
                break;
            //Piece B
            case 22:
                init();
                // Plus X, Plus Y:
                do {
                    ctrx++;
                    try {
                        b = board[ctrx][y]; // Go positive X down it's row
                        if (!b.isFull() || b.getPiece().isWhite() != p.isWhite() && ctrx < 8)
                            validSquares.add(b);
                    } catch (Exception e) {
                        break;
                    }
                } while (!b.isFull() && ctrx < 8);
                break;
            case 23:
                init();
                do {
                    try {
                        ctry++;
                        b = board[x][ctry]; // Go positive Y down it's col
                        if (!b.isFull() || b.getPiece().isWhite() != team && ctry < 8)
                            validSquares.add(b);
                    } catch (Exception e) {
                        break;
                    }
                } while (!b.isFull() && ctry < 8); // Stop at first occupied or out of bounds square
                break;
            case 24:
                init();
                do {
                    ctrx--;
                    try {
                        b = board[ctrx][y]; // Negative X down it's row
                        if (!b.isFull() || b.getPiece().isWhite() != p.isWhite() && ctrx > -1)
                            validSquares.add(b);
                    } catch (Exception e) {
                        break;
                    }
                } while (!b.isFull() && ctrx > -1); // Stop at first occupied or out of bounds square
                break;
            case 25:
                init();
                do {
                    ctry--;
                    try {
                        b = board[x][ctry]; // Go negative Y down it's col
                        if (!b.isFull() || b.getPiece().isWhite() != team && ctry > -1)
                            validSquares.add(b);
                    } catch (Exception e) {
                        break;
                    }
                } while (!b.isFull() && ctry > -1);
                break;
                default:
                    int crashMe = ThreadDispatch.crash[1];
        }
    }

}