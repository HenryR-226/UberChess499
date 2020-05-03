package gradleproject1;
import java.util.ArrayList;
public class KingCheckGenerator implements Runnable {

    public static Boolean result = null;        //Checked by while() loop in ThreadDispatch, resets to Null upon new creation of object

    public ArrayList<BoardButton> validSquares = null;
    public boolean currentMove;

    Piece p;
    int task;

    String location;
    int x; int y; int ctrx; int ctry;
    BoardButton[][] board = Board.GameBoard;
    BoardButton b = null;
    boolean team;
    char abbrev;

    int[] validX = new int[2];
    int[] validY = new int[2];

    public KingCheckGenerator(Piece me, int flag, boolean currentMove){
        if (currentMove) validSquares = new ArrayList<BoardButton>();
        this.p = me;
        this.task = flag;
        result = null;
    }

    private void init(){
        String location = p.getLocation();
        char[] c = location.toCharArray();
        int x = ((int) c[0] - 'A');
        int y = (int) c[1] - '0' - 1;
        ctrx = x; ctry = y;
        team = p.isWhite;
    }

    @Override
    public void run() {
        if(currentMove){    //Current move
            switch(task){
                case 0:
                    init();
                    do {
                        try {
                            b = board[++ctrx][y];
                            if (b.getPiece() != null) {
                                p = b.getPiece();
                                abbrev = Character.toUpperCase(p.getAbbrev());
                                if (p.isWhite() == team)
                                    break; // Friendly piece? If so no problem
                                else {
                                    if (abbrev == 'K' && ctry == ++y)
                                        result=true;
                                    if (abbrev == 'R' || abbrev == 'Q')
                                        result=true;
                                }
                            }
                        } catch (Exception e) {
                            break;
                        }
                    } while (!result && ctrx < 8);
                    break;
                case 1:
                    init();
                    do {
                        try {
                            b = board[++ctrx][++ctry];
                            if (b.getPiece() != null) {
                                p = b.getPiece();
                                abbrev = Character.toUpperCase(p.getAbbrev());
                                if (p.isWhite() == team)
                                    break; // Friendly piece? If so no problem
                                else {
                                    if (abbrev == 'K' && ctry == ++y)
                                        result = true;
                                    if (abbrev == 'B' || abbrev == 'Q')
                                        result = true;
                                    if (team && abbrev == 'P' && ctry == ++y)
                                        result = true;
                                }
                            }
                        } catch (Exception e) {
                            break;
                        }
                    } while (!result && ctry < 8 && ctrx < 8);
                    break;
                case 2:
                    init();
                    do {
                        try {
                            b = board[x][++ctry];
                            if (b.getPiece() != null) {
                                p = b.getPiece();
                                abbrev = Character.toUpperCase(p.getAbbrev());
                                if (p.isWhite() == team)
                                    break; // Friendly piece? If so no problem
                                else {
                                    if (abbrev == 'K' && ctry == ++y)
                                        result = true;
                                    if (abbrev == 'R' || abbrev == 'Q')
                                        result = true;
                                }
                            }
                        } catch (Exception e) {
                            break;
                        }
                    } while (!result && ctry < 8);
                    break;
                case 3:
                    init();
                    do {
                        try {
                            b = board[--ctrx][++ctry];
                            if (b.getPiece() != null) {
                                p = b.getPiece();
                                abbrev = Character.toUpperCase(p.getAbbrev());
                                if (p.isWhite() == team)
                                    break; // Friendly piece? If so no problem
                                else {
                                    if (abbrev == 'K' && ctry == ++y)
                                        result = true;
                                    if (abbrev == 'R' || abbrev == 'Q')
                                        result = true;
                                    if (team && abbrev == 'P' && ctry == ++y)
                                        result = true;
                                }
                            }
                        } catch (Exception e) {
                            break;
                        }
                    } while (!result && ctry < 8 && ctrx > -1);
                    break;
                case 4:
                    init();
                    do {
                        try {
                            b = board[--ctrx][y];
                            if (b.getPiece() != null) {
                                p = b.getPiece();
                                abbrev = Character.toUpperCase(p.getAbbrev());
                                if (p.isWhite() == team)
                                    break; // Friendly piece? If so no problem
                                else {
                                    if (abbrev == 'K' && ctry == ++y)
                                        result = true;
                                    if (abbrev == 'R' || abbrev == 'Q')
                                        result = true;
                                }
                            }
                        } catch (Exception e) {
                            break;
                        }
                    } while (!result && ctrx < 8);
                    break;
                case 5:
                    init();
                    do {
                        try {
                            b = board[--ctrx][--ctry];
                            if (b.getPiece() != null) {
                                p = b.getPiece();
                                abbrev = Character.toUpperCase(p.getAbbrev());
                                if (p.isWhite() == team)
                                    break; // Friendly piece? If so no problem
                                else {
                                    if (abbrev == 'K' && ctry == ++y)
                                        result = true;
                                    if (abbrev == 'R' || abbrev == 'Q')
                                        result = true;
                                    if (!team && abbrev == 'P' && ctry == --y)
                                        result = true;
                                }
                            }
                        } catch (Exception e) {
                            break;
                        }
                    } while (!result && ctry > -1 && ctrx > -1);
                    break;
                case 6:
                    init();
                    do {
                        try {
                            b = board[x][--ctry];
                            if (b.getPiece() != null) {
                                p = b.getPiece();
                                abbrev = Character.toUpperCase(p.getAbbrev());
                                if (p.isWhite() == team)
                                    break; // Friendly piece? If so no problem
                                else {
                                    if (abbrev == 'K' && ctry == --y)
                                        result = true;
                                    if (abbrev == 'R' || abbrev == 'Q')
                                        result = true;
                                }
                            }
                        } catch (Exception e) {
                            break;
                        }
                    } while (!result && ctry > -1);
                    break;
                case 7:
                    init();
                    do {
                        try {
                            b = board[++ctrx][--ctry];
                            if (b.getPiece() != null) {
                                p = b.getPiece();
                                abbrev = Character.toUpperCase(p.getAbbrev());
                                if (p.isWhite() == team)
                                    break; // Friendly piece? If so no problem
                                else {
                                    if (abbrev == 'K' && ctry == --y)
                                        result = true;
                                    if (abbrev == 'B' || abbrev == 'Q')
                                        result = true;
                                    if (!team && abbrev == 'P' && ctry == --y)
                                        result = true;
                                }
                            }
                        } catch (Exception e) {
                            break;
                        }
                    } while (!result && ctry > -1 && ctrx < 8);
                    break;
                //Knight checks
                case 8:
                    init();
                    validX[0]=(x - 1);
                    validX[1]=(x + 1);
                    validY[0]=(y - 2);
                    validY[1]=(y + 2);
                    for (Integer i : validX) {
                        if (i > 0 && i<7) {
                            for (Integer j : validY) {
                                if (j > 0 && j<7) {
                                    if (!board[i][j].isFull() || board[i][j].isFull() && board[i][j].getPiece().isWhite() != p.isWhite() || (""+ board[i][j].getPiece().getAbbrev()).compareToIgnoreCase("n")!=0)
                                        result=true;
                                }
                            }
                        }
                    }
                    break;
                case 9:
                    init();
                    validX[0]=(x - 2);
                    validX[1]=(x + 2);
                    validY[0]=(y - 1);
                    validY[1]=(y + 1);
                    for (Integer i : validX) {
                        if (i > 0 && i<7) {
                            for (Integer j : validY) {
                                if (j > 0 && j<7) {
                                    if (!board[i][j].isFull() || board[i][j].isFull() && board[i][j].getPiece().isWhite() != p.isWhite() || (""+ board[i][j].getPiece().getAbbrev()).compareToIgnoreCase("n")!=0)
                                        result=true;
                                }
                            }
                        }
                    }
                    break;
                    //Move generations
                case 38:
                    init();
                    int[] validX = new int[3];
                    int[] validY = new int[2];
                    if (x>0) {
                        validX[ctrx] = x--;
                        ctrx++;
                    } else validX[ctrx--] = -1;
                    validX[ctrx] = x;
                    if (x<7){
                        validX[ctrx] = x++;
                    }
                    if (y>0){
                        validY[ctry] = y--;
                        ctry++;
                    } else validY[ctry--]=-1;
                    validY[ctry] = y;
                    for (Integer i : validX) {
                        if (i > 0) {
                            for (Integer j : validY) {
                                if (j > 0) {
                                    if (!board[i][j].isFull() || board[i][j].isFull() && board[i][j].getPiece().isWhite() != p.isWhite()) {
                                        //Test for check for this move
                                    }
                                    validSquares.add(board[i][j]);
                                }
                            }
                        }
                    }
                    break;
                case 39:
                    init();
                    validX = new int[3];
                    validY = new int[2];
                    if (x>0) {
                        validX[ctrx] = x--;
                        ctrx++;
                    } else validX[ctrx--] = -1;
                    validX[ctrx] = x;
                    if (x<7){
                        validX[ctrx] = x++;
                    }
                    if (y<7){
                        validY[ctry] = y++;
                        ctry++;
                    } else validY[ctry--]=-1;
                    validY[ctry] = y;
                    for (Integer i : validX) {
                        if (i > 0) {
                            for (Integer j : validY) {
                                if (j > 0) {
                                    if (!board[i][j].isFull() || board[i][j].isFull() && board[i][j].getPiece().isWhite() != p.isWhite()) {
                                        //Test for check for this move
                                    }
                                    validSquares.add(board[i][j]);
                                }
                            }
                        }
                    }
                    break;
            }
        }
        else {      //future move

        }
    }
}