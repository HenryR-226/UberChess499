package gradleproject1;

import java.util.ArrayList;

public class KnightMoveGenerator implements Runnable {
    static BoardButton[][] board = Board.GameBoard;

    public ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
    int task;
    boolean alpha;
    Piece p;
    String location;
    int x; int y; int ctrx; int ctry;
    BoardButton b = null;
    boolean team;

    public KnightMoveGenerator(Piece me, int flag){
        this.p = me;
        this.task = flag;
    }

    Integer[] validX = new Integer[2]; // x +-1
    Integer[] validY = new Integer[2]; // x +-2

    public void init(){
        String location = p.getLocation();
        char[] c = location.toCharArray();
        int x = ((int) c[0] - 'A');
        int y = (int) c[1] - '0' - 1;
    }

    @Override
    public void run() {
        switch (task) {
            case 34:
                init();
                validX[0]=(x - 1);
                validX[1]=(x + 1);
                validY[0]=(y - 2);
                validY[1]=(y + 2);
                for (Integer i : validX) {
                    if (i > 0 && i<7) {
                        for (Integer j : validY) {
                            if (j > 0 && j<7) {
                                if (!board[i][j].isFull() || board[i][j].isFull() && board[i][j].getPiece().isWhite() != p.isWhite())
                                    validSquares.add(board[i][j]);
                            }
                        }
                    }
                }
                break;
            case 35:
                init();
                validX[0]=(x - 2);
                validX[1]=(x + 2);
                validY[0]=(y - 1);
                validY[1]=(y + 1);
                for (Integer i : validX) {
                    if (i > 0 && i<7) {
                        for (Integer j : validY) {
                            if (j > 0 && j<7) {
                                if (!board[i][j].isFull() || board[i][j].isFull() && board[i][j].getPiece().isWhite() != p.isWhite())
                                    validSquares.add(board[i][j]);
                            }
                        }
                    }
                }
            //Piece B
            case 36:
                init();
                validX[0]=(x - 1);
                validX[1]=(x + 1);
                validY[0]=(y - 2);
                validY[1]=(y + 2);
                for (Integer i : validX) {
                    if (i > 0 && i<7) {
                        for (Integer j : validY) {
                            if (j > 0 && j<7) {
                                if (!board[i][j].isFull() || board[i][j].isFull() && board[i][j].getPiece().isWhite() != p.isWhite())
                                    validSquares.add(board[i][j]);
                            }
                        }
                    }
                }
                break;
            case 37:
                init();
                validX[0]=(x - 2);
                validX[1]=(x + 2);
                validY[0]=(y - 1);
                validY[1]=(y + 1);
                for (Integer i : validX) {
                    if (i > 0 && i<7) {
                        for (Integer j : validY) {
                            if (j > 0 && j<7) {
                                if (!board[i][j].isFull() || board[i][j].isFull() && board[i][j].getPiece().isWhite() != p.isWhite())
                                    validSquares.add(board[i][j]);
                            }
                        }
                    }
                }
                break;
            default:
                int crashMe = ThreadDispatch.crash[1];
        }
    }
}
