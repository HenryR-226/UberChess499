package gradleproject1;
import java.util.ArrayList;

public class BishopMoveGenerator implements Runnable {

    static BoardButton[][] board = Board.GameBoard;

    public ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
    int task;
    boolean alpha;
    Piece me;
    String location;
    int x; int y; int ctrx; int ctry;
    BoardButton b = null;
    boolean team;

    public BishopMoveGenerator(Piece p, int flag){
        this.me = p;
        this.task = flag;
    }

    public void init(){
        location = me.getLocation();
        char[] c = location.toCharArray();
        x = ((int) c[0] - 'A');
        y = (int) c[1] - '0' - 1;
        ctrx = x;
        ctry = y;
        team = me.isWhite();
    }

    @Override
    public void run(){
        switch(task){
            case 26:
                init();
                // Plus X, Plus Y:
                do {
                    try {
                        ctrx++;
                        ctry++;
                        b = board[ctrx][ctry];
                        if ((!b.isFull() || (b.getPiece().isWhite() != team)) && ctry < 8 && ctrx < 8)
                            validSquares.add(b);
                    } catch (Exception e) {
                        break;
                    }
                } while (!b.isFull() && ctrx < 8 && ctry < 8);
                break;
            case 27:
                init();
                // Minus X, Plus Y
                ctrx = x;
                ctry = y;
                do {
                    try {
                        ctrx--;
                        ctry++;
                        b = board[ctrx][ctry];
                        if ((!b.isFull() || (b.getPiece().isWhite() != team)) && ctry < 8 && ctrx > -1)
                            validSquares.add(b);
                    } catch (Exception e) {
                        break;
                    }

                } while (!b.isFull() && ctrx > -1 && ctry < 8);
                break;
            case 28:
                init();
                do {
                    try {
                        ctrx--;
                        ctry--;
                        b = board[ctrx][ctry];
                        if ((!b.isFull() || b.getPiece().isWhite() != team) && ctry > -1 && ctrx > -1) {

                            validSquares.add(b);
                        }
                    } catch (Exception e) {
                        break;
                    }
                } while (!b.isFull() && ctrx > -1 && ctry > -1);
                break;
            case 29:
                init();
                do {
                    try {
                        ctrx++;
                        ctry--;
                        b = board[ctrx][ctry];
                        if ((!b.isFull() || b.getPiece().isWhite() != team) && ctry > -1 && ctrx < 8)
                            validSquares.add(b);
                    } catch (Exception e) {
                        break;
                    }
                } while (!b.isFull() && ctrx < 8 && ctry > -1);
                break;
            //Piece B
            case 30:
                init();
                // Plus X, Plus Y:
                do {
                    try {
                        ctrx++;
                        ctry++;
                        b = board[ctrx][ctry];
                        if ((!b.isFull() || (b.getPiece().isWhite() != team)) && ctry < 8 && ctrx < 8)
                            validSquares.add(b);
                    } catch (Exception e) {
                        break;
                    }
                } while (!b.isFull() && ctrx < 8 && ctry < 8);
                break;
            case 31:
                init();
                // Minus X, Plus Y
                ctrx = x;
                ctry = y;
                do {
                    try {
                        ctrx--;
                        ctry++;
                        b = board[ctrx][ctry];
                        if ((!b.isFull() || (b.getPiece().isWhite() != team)) && ctry < 8 && ctrx > -1)
                            validSquares.add(b);
                    } catch (Exception e) {
                        break;
                    }

                } while (!b.isFull() && ctrx > -1 && ctry < 8);
                break;
            case 32:
                init();
                do {
                    try {
                        ctrx--;
                        ctry--;
                        b = board[ctrx][ctry];
                        if ((!b.isFull() || b.getPiece().isWhite() != team) && ctry > -1 && ctrx > -1) {

                            validSquares.add(b);
                        }
                    } catch (Exception e) {
                        break;
                    }
                } while (!b.isFull() && ctrx > -1 && ctry > -1);
                break;
            case 33:
                init();
                do {
                    try {
                        ctrx++;
                        ctry--;
                        b = board[ctrx][ctry];
                        if ((!b.isFull() || b.getPiece().isWhite() != team) && ctry > -1 && ctrx < 8)
                            validSquares.add(b);
                    } catch (Exception e) {
                        break;
                    }
                } while (!b.isFull() && ctrx < 8 && ctry > -1);
                break;
            default:
                int crashMe = ThreadDispatch.crash[1];
        }
    }

}
