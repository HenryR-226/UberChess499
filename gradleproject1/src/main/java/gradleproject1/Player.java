package gradleproject1;
import java.util.ArrayList;
/**
 *
 * @author Tyrone Lamar
 */
public class Player {
    private ArrayList moveList = new ArrayList<String>();
    
    public void addMove(String m){
        moveList.add(m);
    }
    
    private boolean team;            
}
