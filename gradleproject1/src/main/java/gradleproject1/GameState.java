/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradleproject1;

/**
 *
 * @author Tyrone Lamar
 */
public class GameState {
    private int cores;                                //Number of CPU hardware threads available to JVM at run time
    
    public int getCurrentThreads(){                    //Determines optimal number of threads to start for AI calculations
        int cores = Runtime.getRuntime().availableProcessors();
        return cores;
    }
    
    
    
}
