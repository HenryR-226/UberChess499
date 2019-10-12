/*
 *  Singleton object intended to be collections of players, handle 
 *  win/loss/tie, do backend housekeeping, and allow for references to 
 *  player objects to be passed nicely based on color. 
 */
package gradleproject1;

/**
 *
 * @author Tyrone Lamar
 */
public class GameState {
    private int cores;                                //Number of CPU hardware threads available to JVM at run time
    private Player playerWhite;
    private Player playerBlack;
    
    public int getCurrentThreads(){                    //Determines optimal number of threads to start for AI calculations
        int cores = Runtime.getRuntime().availableProcessors();
        return cores;
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
    
}
