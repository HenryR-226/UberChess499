package gradleproject1;
import java.util.*;

public class Player {
    private ArrayList moveList = new ArrayList<String>();
    private ArrayList pieceList = new ArrayList<Piece>();   //Pieces currently on the board
    private ArrayList piecesLost = new ArrayList<Piece>();  //Pieces lost by that gamer
    private boolean hasCastled;                             //Has the gamer castled
    private boolean canCastle;                              //CAN the gamer castle, false if king and/or close side rook moves
   
    private boolean team;    
    private boolean ai = false;
    private double points;                                  //AI points variable for current game state
    private Boolean game;                                   //True = won, false = lost, null = in progress, thus upper case 'B'
   
    private static int numTeams = 0;                    //Don't make more than 2 teams, dingus
   
    private Board b;
    
   public Player(boolean team, boolean ai){
        try{
            assert (numTeams <2);
            this.ai = ai;
            this.team=team;
            numTeams++;
        } catch (Exception e) {
            System.out.println("Already two teams. That or something has gone very very wrong.");
            e.printStackTrace();
        }
    } 
   
    public boolean isTeam() {
        return team;
    }
 
    public void setTeam(boolean team) {
        this.team = team;
    }
 
    public boolean isAi() {
        return ai;
    }
 
    public void setAi(boolean ai) {
        this.ai = ai;
    }
 
    public void addMove(String m){
        moveList.add(m);
    }
   
    public String getLastMove(){
        int index = moveList.size();
        return (String) moveList.get(index-1);              //Cast to string and return while not removing last move
    }
   
    public void setPoints(double d){ //(?°???°)
        this.points=d;
    }
    public double getPoints(){
        return points;
    }    
   
    public ArrayList<Piece> getPieceList(){
        return this.pieceList;
    }    
   
    public void pieceCaptured(Piece p){
        pieceList.remove(p);
        piecesLost.add(p);
    }
   
    public Boolean getGame(){        //See if the game has won or lost
        return game;
    }      
   
    public void setGame(Boolean b){
        this.game = b;
        if (b){
            if (team) System.out.println("Player White wins!");
            else System.out.println("Player Black wins!");
        }    
    }    
    
    public void setBoard(Board board){
        this.b = board;
    }
}