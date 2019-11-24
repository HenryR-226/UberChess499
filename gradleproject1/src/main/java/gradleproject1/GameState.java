/*
 *  Singleton object intended to be collections of players, handle 
 *  win/loss/tie, do backend housekeeping, and allow for references to 
 *  player objects to be passed nicely based on color. 
 */
package gradleproject1;

public class GameState {
    private int cores;                                //Number of CPU hardware threads available to JVM at run time
    private Player playerWhite;
    private Player playerBlack;
    boolean turn = true;						//True = white turn, false = black turn
    private Integer depth = 4;							//Number of turns to go down
    
    public int getCurrentThreads(){                    //Determines optimal number of threads to start for AI calculations
        int cores = Runtime.getRuntime().availableProcessors();
        return cores;
    }
    public void setWhite(Player white) {
    	this.playerWhite = white;
    }
    public void setBlack(Player black) {
    	this.playerBlack=black;
    }
    
    //These two methods needed for Piece Capturing implementation
    public Player getWhite(){
        return this.playerWhite;
    }
    public Player getBlack(){
        return this.playerBlack;
    }    
    
    public GameState getGameState(){
        return this;
    }
    
    public boolean whoseTurn() {
    	return this.turn;
    }
    
    public void turn() {							//Turn made, next player's turn
    	this.turn = !turn;
    }
    
    public Integer getDepth() {
    	return this.depth;
    }
}
