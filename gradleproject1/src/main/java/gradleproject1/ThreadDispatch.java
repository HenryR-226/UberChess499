package gradleproject1;

import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Handles all thread objects. Deploys a flag to signal the Runnable interface class
 * which method to call.
 */

public class ThreadDispatch {

    static GameState g = Player.getGameState();
    Piece piece;
    char abbrev;
    boolean team;
    static public int[] crash = new int[0];      //Access any value to crash with exception, dodges handholding garbage
    int diagonal;              //0: top right, 1 : top left, 2: bottom left, 3: bottom right: in king, 1 means 'check for check'
    static int ctr0=0;         //Number of threads spawned
    Boolean alpha = null;
    Boolean kingInCheck = null;
    private static boolean even = true;

    //Move generation vars
    ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
    BoardButton b = null;
    int ctrx = 0; int ctry = 0;
    int x; int y;
    String location;
    static BoardButton[][] board = Board.GameBoard;

    public int whichThread;

    public ThreadDispatch(Piece p, int flag){
        this.piece = p;
        this.abbrev=p.getAbbrev();
        alpha =p.getAlpha();
        this.team=p.isWhite;
        this.diagonal=flag;

        location = p.getLocation();
        char[] c = location.toCharArray();
        int x = ((int) c[0] - 'A');
        int y = (int) c[1] - '0' - 1;
        ctrx = x;
        ctry = y;
        BoardButton b = null;
        //if (("" + p.getAbbrev()).compareToIgnoreCase("K")
        whatToRun();
    }
    public ArrayList<BoardButton> getValidSquares() {
        return this.validSquares;
    }

    BishopMoveGenerator pootis = null;


    public void whatToRun(){
        switch(abbrev){
            case 'p':
                PawnMoveGenerator foo = new PawnMoveGenerator(piece);
                pawnGenPool.execute(foo);
                validSquares.addAll(foo.result);
                break;
            case 'P':
                PawnMoveGenerator bar = new PawnMoveGenerator(piece);
                pawnGenPool.execute(bar);
                validSquares.addAll(bar.result);
                break;
            case 'b':
                switch(diagonal){
                    case 0:
                        if (alpha) { //Black bishop 'A' going top right
                            BishopMoveGenerator pootis = new BishopMoveGenerator(piece, 26);
                            bishop1GenPool.execute(pootis);
                            synchronized(validSquares){
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else { //Black bishop 'B' going top right
                            BishopMoveGenerator p0w = new BishopMoveGenerator(piece, 30);
                            bishop2GenPool.execute(p0w);
                            synchronized (validSquares) {
                                validSquares.addAll(p0w.validSquares);
                            }
                        }
                        break;
                    case 1:
                        if (alpha) {  //Black bishop 'A' going top left
                            BishopMoveGenerator pootis = new BishopMoveGenerator(piece, 27);
                            bishop1GenPool.execute(pootis);
                            synchronized (validSquares) {
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else {  //Black bishop 'B' going top left
                            BishopMoveGenerator p0w = new BishopMoveGenerator(piece, 31);
                            bishop2GenPool.execute(p0w);
                            validSquares=p0w.validSquares;
                        }
                        break;
                    case 2:
                        if (alpha) {   //Black bishop 'A' going bottom left
                            BishopMoveGenerator pootis = new BishopMoveGenerator(piece, 28);
                            bishop1GenPool.execute(pootis);
                            synchronized (validSquares) {
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else {
                            BishopMoveGenerator p0w = new BishopMoveGenerator(piece, 32);
                            bishop2GenPool.execute(p0w);
                            validSquares=p0w.validSquares;
                        }
                        break;
                    case 3:
                        if (alpha) {
                            BishopMoveGenerator pootis = new BishopMoveGenerator(piece, 29);
                            bishop1GenPool.execute(pootis);
                            synchronized (validSquares) {
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else {
                            BishopMoveGenerator p0w = new BishopMoveGenerator(piece, 33);
                            bishop2GenPool.execute(p0w);
                            validSquares=p0w.validSquares;
                        }
                        break;
                    default:
                        int crashMe=crash[1];
                }
                break;
            case 'B':
                switch(diagonal){
                    case 0:
                        if (alpha) { //Black bishop 'A' going top right
                            BishopMoveGenerator pootis = new BishopMoveGenerator(piece, 26);
                            bishop1GenPool.execute(pootis);
                            synchronized (validSquares) {
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else { //Black bishop 'B' going top right
                            BishopMoveGenerator p0w = new BishopMoveGenerator(piece, 30);
                            bishop2GenPool.execute(p0w);
                            synchronized (validSquares) {
                                validSquares.addAll(p0w.validSquares);
                            }
                        }
                        break;
                    case 1:
                        if (alpha) {  //Black bishop 'A' going top left
                            BishopMoveGenerator pootis = new BishopMoveGenerator(piece, 27);
                            bishop1GenPool.execute(pootis);
                            synchronized (validSquares) {
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else {  //Black bishop 'B' going top left
                            BishopMoveGenerator p0w = new BishopMoveGenerator(piece, 31);
                            bishop2GenPool.execute(p0w);
                            synchronized (validSquares) {
                                validSquares.addAll(p0w.validSquares);
                            }
                        }
                        break;
                    case 2:
                        if (alpha) {   //Black bishop 'A' going bottom left
                            BishopMoveGenerator pootis = new BishopMoveGenerator(piece, 28);
                            bishop1GenPool.execute(pootis);
                            synchronized (validSquares) {
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else {
                            BishopMoveGenerator p0w = new BishopMoveGenerator(piece, 32);
                            bishop2GenPool.execute(p0w);
                            synchronized (validSquares) {
                                validSquares.addAll(p0w.validSquares);
                            }
                        }
                        break;
                    case 3:
                        if (alpha) {
                            BishopMoveGenerator pootis = new BishopMoveGenerator(piece, 29);
                            bishop1GenPool.execute(pootis);
                            synchronized (validSquares) {
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else {
                            BishopMoveGenerator p0w = new BishopMoveGenerator(piece, 33);
                            bishop2GenPool.execute(p0w);
                            synchronized (validSquares) {
                                validSquares.addAll(p0w.validSquares);
                            }
                        }
                        break;
                    default:
                        int crashMe=crash[1];
                }
                break;
            case 'n':
                if (alpha) {    //Black knight 'A'
                    KnightMoveGenerator pootis = new KnightMoveGenerator(piece, 34);
                    KnightMoveGenerator p0w = new KnightMoveGenerator(piece, 35);
                    knight1GenPool.execute(pootis); knight1GenPool.execute(p0w);
                    synchronized(validSquares){
                        validSquares.addAll(pootis.validSquares);
                        validSquares.addAll(p0w.validSquares);
                    }
                }
                else {          //Black knight 'B'
                    KnightMoveGenerator pootis = new KnightMoveGenerator(piece, 36);
                    KnightMoveGenerator p0w = new KnightMoveGenerator(piece, 37);
                    knight2GenPool.execute(pootis); knight2GenPool.execute(p0w);
                    synchronized(validSquares) {
                        validSquares.addAll(pootis.validSquares);
                        validSquares.addAll(p0w.validSquares);
                    }
                }
                break;
            case 'N':
                if (alpha) {    //White knight 'A'
                    KnightMoveGenerator pootis = new KnightMoveGenerator(piece, 34);
                    KnightMoveGenerator p0w = new KnightMoveGenerator(piece, 35);
                    knight1GenPool.execute(pootis); knight1GenPool.execute(p0w);
                    synchronized(validSquares){
                        validSquares.addAll(pootis.validSquares);
                        validSquares.addAll(p0w.validSquares);
                    }
                }
                else {   //White knight 'B'
                    KnightMoveGenerator pootis = new KnightMoveGenerator(piece, 36);
                    KnightMoveGenerator p0w = new KnightMoveGenerator(piece, 37);
                    knight2GenPool.execute(pootis); knight2GenPool.execute(p0w);
                    synchronized(validSquares) {
                        validSquares.addAll(pootis.validSquares);
                        validSquares.addAll(p0w.validSquares);
                    }
                }
                break;
            case 'r':
                switch(diagonal){
                    case 0:
                        if (alpha) {   //Black rook 'A' going right
                            RookMoveGenerator pootis = new RookMoveGenerator(piece, 18);
                            rook1GenPool.execute(pootis);
                            synchronized (validSquares) {
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else {    //Black rook 'B' going right
                            RookMoveGenerator p0w = new RookMoveGenerator(piece, 22);
                            rook1GenPool.execute(p0w);
                            synchronized (validSquares) {
                                validSquares.addAll(p0w.validSquares);
                            }
                        }
                        break;
                    case 1:
                        if (alpha) {   //Black rook 'A' going right
                            RookMoveGenerator pootis = new RookMoveGenerator(piece, 19);
                            rook1GenPool.execute(pootis);
                            synchronized (validSquares) {
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else {   //Black rook 'B' going right
                            RookMoveGenerator p0w = new RookMoveGenerator(piece, 23);
                            rook1GenPool.execute(p0w);
                            synchronized (validSquares) {
                                validSquares.addAll(p0w.validSquares);
                            }
                        }
                        break;
                    case 2:
                        if (alpha) {   //Black rook 'A' going left
                            RookMoveGenerator pootis = new RookMoveGenerator(piece, 20);
                            rook1GenPool.execute(pootis);
                            synchronized (validSquares) {
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else {   //Black rook 'B' going right
                            RookMoveGenerator p0w = new RookMoveGenerator(piece, 24);
                            rook1GenPool.execute(p0w);
                            synchronized (validSquares) {
                                validSquares.addAll(p0w.validSquares);
                            }
                        }
                        break;
                    case 3:
                        if (alpha) {   //Black rook 'A' going down
                            RookMoveGenerator pootis = new RookMoveGenerator(piece, 21);
                            rook1GenPool.execute(pootis);
                            synchronized (validSquares) {
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else {   //Black rook 'B' going right
                            RookMoveGenerator p0w = new RookMoveGenerator(piece, 25);
                            rook1GenPool.execute(p0w);
                            synchronized (validSquares) {
                                validSquares.addAll(p0w.validSquares);
                            }
                        }
                        break;
                    default:
                        int crashMe=crash[1];
                }
                break;
            case 'R':
                switch(diagonal){
                    case 0:
                        if (alpha) {   //Black rook 'A' going right
                            RookMoveGenerator pootis = new RookMoveGenerator(piece, 18);
                            rook1GenPool.execute(pootis);
                            synchronized (validSquares) {
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else {    //Black rook 'B' going right
                            RookMoveGenerator p0w = new RookMoveGenerator(piece, 22);
                            rook1GenPool.execute(p0w);
                            synchronized (validSquares) {
                                validSquares.addAll(p0w.validSquares);
                            }
                        }
                        break;
                    case 1:
                        if (alpha) {   //Black rook 'A' going right
                            RookMoveGenerator pootis = new RookMoveGenerator(piece, 19);
                            rook1GenPool.execute(pootis);
                            synchronized (validSquares) {
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else {   //Black rook 'B' going right
                            RookMoveGenerator p0w = new RookMoveGenerator(piece, 23);
                            rook1GenPool.execute(p0w);
                            synchronized (validSquares) {
                                validSquares.addAll(p0w.validSquares);
                            }
                        }
                        break;
                    case 2:
                        if (alpha) {   //Black rook 'A' going left
                            RookMoveGenerator pootis = new RookMoveGenerator(piece, 20);
                            rook1GenPool.execute(pootis);
                            synchronized (validSquares) {
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else {   //Black rook 'B' going right
                            RookMoveGenerator p0w = new RookMoveGenerator(piece, 24);
                            rook1GenPool.execute(p0w);
                            synchronized (validSquares) {
                                validSquares.addAll(p0w.validSquares);
                            }
                        }
                        break;
                    case 3:
                        if (alpha) {   //Black rook 'A' going down
                            RookMoveGenerator pootis = new RookMoveGenerator(piece, 21);
                            rook1GenPool.execute(pootis);
                            synchronized (validSquares) {
                                validSquares.addAll(pootis.validSquares);
                            }
                        }
                        else {   //Black rook 'B' going right
                            RookMoveGenerator p0w = new RookMoveGenerator(piece, 25);
                            rook2GenPool.execute(p0w);
                            synchronized (validSquares) {
                                validSquares.addAll(p0w.validSquares);
                            }
                        }
                        break;
                    default:
                        int crashMe=crash[1];
                }
                break;
            case 'q':
                if (even) {
                    //Call Rook and Bishop together, then add all the lists together
                    Stack<ArrayList<BoardButton>> temp = new Stack<ArrayList<BoardButton>>();
                    BishopMoveGenerator[] pootis = {
                            new BishopMoveGenerator(piece, 26),
                            new BishopMoveGenerator(piece, 27),
                            new BishopMoveGenerator(piece, 28),
                            new BishopMoveGenerator(piece, 29)
                    };
                    for (BishopMoveGenerator b : pootis) bishop1GenPool.execute(b);
                    RookMoveGenerator[] p0w = {
                            new RookMoveGenerator(piece, 18),
                            new RookMoveGenerator(piece, 19),
                            new RookMoveGenerator(piece, 20),
                            new RookMoveGenerator(piece, 21),
                    };
                    for (RookMoveGenerator r : p0w) rook1GenPool.execute(r);
                    for (BishopMoveGenerator b : pootis) {
                        temp.push(b.validSquares);
                    }
                    for (RookMoveGenerator r : p0w) {
                        temp.push(r.validSquares);
                    }
                    for (ArrayList<BoardButton> inception : temp) validSquares.addAll(inception);
                    even = !even;
                } else {
                    Stack<ArrayList<BoardButton>> temp = new Stack<ArrayList<BoardButton>>();
                    BishopMoveGenerator[] pootis = {
                            new BishopMoveGenerator(piece, 30),
                            new BishopMoveGenerator(piece, 31),
                            new BishopMoveGenerator(piece, 32),
                            new BishopMoveGenerator(piece, 33)
                    };
                    for (BishopMoveGenerator b : pootis) bishop2GenPool.execute(b);
                    RookMoveGenerator[] p0w = {
                            new RookMoveGenerator(piece, 22),
                            new RookMoveGenerator(piece, 23),
                            new RookMoveGenerator(piece, 24),
                            new RookMoveGenerator(piece, 25),
                    };
                    for (RookMoveGenerator r : p0w) rook2GenPool.execute(r);
                    for (BishopMoveGenerator b : pootis) {
                        temp.push(b.validSquares);
                    }
                    for (RookMoveGenerator r : p0w) {
                        temp.push(r.validSquares);
                    }
                    for (ArrayList<BoardButton> inception : temp) validSquares.addAll(inception);
                    even = !even;
                }
                break;
            case 'Q':
                if (even) {
                    //Call Rook and Bishop together, then add all the lists together
                    Stack<ArrayList<BoardButton>> temp = new Stack<ArrayList<BoardButton>>();
                    BishopMoveGenerator[] pootis = {
                            new BishopMoveGenerator(piece, 26),
                            new BishopMoveGenerator(piece, 27),
                            new BishopMoveGenerator(piece, 28),
                            new BishopMoveGenerator(piece, 29)
                    };
                    for (BishopMoveGenerator b : pootis) bishop1GenPool.execute(b);
                    RookMoveGenerator[] p0w = {
                            new RookMoveGenerator(piece, 18),
                            new RookMoveGenerator(piece, 19),
                            new RookMoveGenerator(piece, 20),
                            new RookMoveGenerator(piece, 21),
                    };
                    for (RookMoveGenerator r : p0w) rook1GenPool.execute(r);
                    for (BishopMoveGenerator b : pootis) {
                        temp.push(b.validSquares);
                    }
                    for (RookMoveGenerator r : p0w) {
                        temp.push(r.validSquares);
                    }
                    for (ArrayList<BoardButton> inception : temp) validSquares.addAll(inception);
                    even = !even;
                } else {
                    Stack<ArrayList<BoardButton>> temp = new Stack<ArrayList<BoardButton>>();
                    BishopMoveGenerator[] pootis = {
                            new BishopMoveGenerator(piece, 30),
                            new BishopMoveGenerator(piece, 31),
                            new BishopMoveGenerator(piece, 32),
                            new BishopMoveGenerator(piece, 33)
                    };
                    for (BishopMoveGenerator b : pootis) bishop2GenPool.execute(b);
                    RookMoveGenerator[] p0w = {
                            new RookMoveGenerator(piece, 22),
                            new RookMoveGenerator(piece, 23),
                            new RookMoveGenerator(piece, 24),
                            new RookMoveGenerator(piece, 25),
                    };
                    for (RookMoveGenerator r : p0w) rook2GenPool.execute(r);
                    for (BishopMoveGenerator b : pootis) {
                        temp.push(b.validSquares);
                    }
                    for (RookMoveGenerator r : p0w) {
                        temp.push(r.validSquares);
                    }
                    for (ArrayList<BoardButton> inception : temp) validSquares.addAll(inception);
                    even = !even;
                }
                break;
            case 'k':
                if (diagonal==1){   //Check for check
                    if (alpha) {    //Hypothetical future move
                        synchronized (piece){
                        KingCheckGenerator[] pootis = { new KingCheckGenerator(piece, 0, false),
                        new KingCheckGenerator(piece, 1, false),
                        new KingCheckGenerator(piece, 2, false),
                        new KingCheckGenerator(piece, 3, false),
                        new KingCheckGenerator(piece, 4, false),
                        new KingCheckGenerator(piece, 5, false),
                        new KingCheckGenerator(piece, 6, false),
                        new KingCheckGenerator(piece, 7, false) };
                        while (KingCheckGenerator.result != true)
                            for (KingCheckGenerator k : pootis) {
                            kingCheckPool.execute(k);
                            }
                        }
                    }
                    kingInCheck=KingCheckGenerator.result;
                    }
                else {   //generate moves
                    KingCheckGenerator _pootis = new KingCheckGenerator(piece, 38, true);
                    KingCheckGenerator _p0w = new KingCheckGenerator(piece, 39, true);
                    kingGenPool.execute(_pootis); kingGenPool.execute(_p0w);
                    synchronized (validSquares){
                        validSquares.addAll(_pootis.validSquares);
                        validSquares.addAll(_p0w.validSquares);
                    }
                }
                break;
            case 'K':
                if (diagonal==1){   //Check for check
                    if (alpha) {    //Hypothetical future move
                        synchronized (piece){
                            KingCheckGenerator[] pootis = { new KingCheckGenerator(piece, 0, false),
                                    new KingCheckGenerator(piece, 1, false),
                                    new KingCheckGenerator(piece, 2, false),
                                    new KingCheckGenerator(piece, 3, false),
                                    new KingCheckGenerator(piece, 4, false),
                                    new KingCheckGenerator(piece, 5, false),
                                    new KingCheckGenerator(piece, 6, false),
                                    new KingCheckGenerator(piece, 7, false) };
                            while (KingCheckGenerator.result != true)
                                for (KingCheckGenerator k : pootis) {
                                    kingCheckPool.execute(k);
                                }
                        }
                    }
                    kingInCheck=KingCheckGenerator.result;
                }
                else {   //generate moves
                    KingCheckGenerator pootis = new KingCheckGenerator(piece, 38, true);
                    KingCheckGenerator p0w = new KingCheckGenerator(piece, 39, true);
                    kingGenPool.execute(pootis); kingGenPool.execute(p0w);
                    synchronized (validSquares){
                        validSquares.addAll(pootis.validSquares);
                        validSquares.addAll(p0w.validSquares);
                    }
                }
                break;
            default:           //Should literally never be here
                int crashMe = crash[1];
        }

    }

    //10 threads for King Check tests
    //8 for queen, 4 for each rook + bishop, 2 for each knight, 2 for king, 1 for pawn
    //10 + 8 + 8 + 8 + 2 + 2 + 2 + 8 = 48
//    public static Thread[] kingCheck = new Thread[10];
//    public static Thread[] queenGen = new Thread[8];
//    public static Thread[] rook1Gen = new Thread[4];
//    public static Thread[] rook2Gen = new Thread[4];
//    public static Thread[] bishop1Gen = new Thread[4];
//    public static Thread[] bishop2Gen = new Thread[4];
//    public static Thread[] knight1Gen = new Thread[2];
//    public static Thread[] knight2Gen = new Thread[2];
//    public static Thread[] kingGen = new Thread[2];
//    public static Thread[] pawnGen = new Thread[8];

    public static String[] baseStrings = { "king_check_test", "queen_generator", "rook1_generator", "rook2_generator",
            "bishop1_generator", "bishop2_generator", "knight1_generator", "knight2_generator",
            "king_move_generator", "pawn_generator" };

    //Make threads for their given purposes, named in debuggable way
    static ThreadFactory namingThings;

    private static void initNamingThings(){
        namingThings = new ThreadFactory() {
            int ctr = 0;
            @Override
            public Thread newThread(Runnable r) {
                if (ctr0 < 10) {
                    Thread t = new Thread(r, baseStrings[0].concat(Integer.toString(ctr)));
                    ctr++; ctr0++; return t;
                } else if (ctr0 < 18) {
                    Thread t = new Thread(r, baseStrings[1].concat(Integer.toString(ctr - 10)));
                    ctr++; ctr0++; return t;
                } else if (ctr0 < 22) {
                    Thread t = new Thread(r, baseStrings[2].concat(Integer.toString(ctr - 18)));
                    ctr++; ctr0++; return t;
                } else if (ctr0 < 26) {
                    Thread t = new Thread(r, baseStrings[3].concat(Integer.toString(ctr - 22)));
                    ctr++; ctr0++; return t;
                } else if (ctr0 < 30) {
                    Thread t = new Thread(r, baseStrings[4].concat(Integer.toString(ctr - 26)));
                    ctr++; ctr0++; return t;
                } else if (ctr0 < 34) {
                    Thread t = new Thread(r, baseStrings[5].concat(Integer.toString(ctr - 30)));
                    ctr++; ctr0++; return t;
                } else if (ctr0 < 36) {
                    Thread t = new Thread(r, baseStrings[6].concat(Integer.toString(ctr - 34)));
                    ctr++; ctr0++; return t;
                } else if (ctr0 < 38) {
                    Thread t = new Thread(r, baseStrings[7].concat(Integer.toString(ctr - 36)));
                    ctr++; ctr0++; return t;
                } else if (ctr0 < 40) {
                    Thread t = new Thread(r, baseStrings[8].concat(Integer.toString(ctr - 38)));
                    ctr++; ctr0++; return t;
                } else if (ctr0 < 48) {
                    Thread t = new Thread(r, baseStrings[9].concat(Integer.toString(ctr - 40)));
                    ctr++; ctr0++; return t;
                } else if (ctr0 == 48) {
                    Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
                    for (Thread t : threadSet) {
                        System.out.println("T: " + t.getName());
                    }
                }
                return null;
            }
        };
    }


    //Mild performance hit at startup, 48 threads started. These should stay for the life of the JVM
    public static ExecutorService kingCheckPool;
    public static ExecutorService queenGenPool;
    public static ExecutorService rook1GenPool;
    public static ExecutorService rook2GenPool;
    public static ExecutorService bishop1GenPool;
    public static ExecutorService bishop2GenPool;
    public static ExecutorService knight1GenPool;
    public static ExecutorService knight2GenPool;
    public static ExecutorService kingGenPool;
    public static ExecutorService pawnGenPool;

    public static void main(String[] args) {
        initNamingThings();
        kingCheckPool = Executors.newFixedThreadPool(10, namingThings);
        queenGenPool = Executors.newFixedThreadPool(8, namingThings);
        rook1GenPool = Executors.newFixedThreadPool(4, namingThings);
        rook2GenPool = Executors.newFixedThreadPool(4, namingThings);
        bishop1GenPool = Executors.newFixedThreadPool(4, namingThings);
        bishop2GenPool = Executors.newFixedThreadPool(4, namingThings);
        knight1GenPool = Executors.newFixedThreadPool(2, namingThings);
        knight2GenPool = Executors.newFixedThreadPool(2, namingThings);
        kingGenPool = Executors.newFixedThreadPool(2, namingThings);
        pawnGenPool = Executors.newFixedThreadPool(8, namingThings);
    }

}
