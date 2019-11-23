package gradleproject1;

import java.util.ArrayList;

public class Player {
	private ArrayList<String> moveList = new ArrayList<String>();
	private ArrayList<Piece> pieceList = new ArrayList<Piece>(); // Pieces currently on the board
	private ArrayList<Piece> piecesLost = new ArrayList<Piece>(); // Pieces lost by that gamer
	private boolean hasCastled; // Has the gamer castled
	private boolean canCastle; // CAN the gamer castle, false if king and/or close side rook moves

	private boolean team;
	private boolean ai = false;
	private static double whitePoints; // AI points variable for current game state
	private static double blackPoints;
	private Boolean game; // True = won, false = lost, null = in progress, thus upper case 'B'

	private static int numTeams = 0; // Don't make more than 2 teams, dingus

	private Board b;
	private BoardButton[][] bb;
	
	public Player(boolean team, boolean ai) {
		try {
			assert (numTeams < 2);
			this.ai = ai;
			this.team = team;
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

	public void addMove(String moveIteration) {
		moveList.add(moveIteration);
		//System.out.println("Move added: " + moveIteration);
	}

	public String getLastMove() {
		int index = moveList.size();
		return (String) moveList.get(index - 1); // Cast to string and return while not removing last move
	}

	public ArrayList<String> getMoves(){
		//for (String s : moveList) System.out.println(s);
		return this.moveList;
	}
	
	public void setWhitePoints(double d) { // (?°???°)
		whitePoints = d;
	}

	public double getWhitePoints() {
		return whitePoints;
	}
	
	public void setBlackPoints(double d) {
		blackPoints = d;
	}
	
	public double getBlackPoints() {
		return blackPoints;
	}

	public void addPiece(Piece p) {
		this.pieceList.add(p);
	}

	public ArrayList<Piece> getPieceList() {
		return this.pieceList;
	}

	public void pieceCaptured(Piece p) {
		pieceList.remove(p);
		piecesLost.add(p);
	}

	public Boolean getGame() { // See if the game has won or lost
		return game;
	}

	public void setGame(Boolean b) {
		this.game = b;
		if (b) {
			if (team)
				System.out.println("Player White wins!");
			else
				System.out.println("Player Black wins!");
		}
	}

	public void setBoard(Board board) {
		this.b = board;
		this.bb = board.getGameBoard();
	}
	
    /**
     * @author Henry Rheault
     * Method to calculate the points state on the board for @param piece list (Normally hypothetical piece list for AI calculations)
     * @return the player's points
     */
   
	public double evalPoints(){                                     
        double result = 0;
        //BoardButton[][] board;               //Get locations
        ArrayList<Double> pointsList = new ArrayList<Double>(); //Create list of point objects
        for(Piece piece : pieceList){                             //For each row and column of BoardButton
            result = result + piece.getPoints() + piece.getOffset();    //Find pieces belonging to player p and summate points
        }                                                        //This code isn't clean and needs to run hundreds of thousands of times
                                                                //So it needs to be better than "check every square for where there's
                                                                //A piece on that team, every time"
        //System.out.println("Line 127 in Player, total score for team is " + result);
        return result;    
        }
	
	/**
	 * @author Henry Rheault
	 * Method for the AI to generate the best possible moves, and return the one with the most points.
	 * @param player- the player we're starting on- and depth- the amount of moves down (both sides) to eval.
	 */
	
	  public String bestMove(Player gamer, int depth) {
		  int iterable = depth*2;
		  String result=null;
		  String[] movesDepth = new String[depth];
		  double points, maxPoints = 0;
		  ArrayList<String> possibleMoves = new ArrayList<String>();
		  
		  possibleMoves = b.getAllMoves(gamer.getPieceList(), bb);
		  for (String s : possibleMoves) {
			  //TODO- change to Move type object
			  //Get the piece associated with this move, update it's location. If it's a capture make a clone of pieces lists and make the alterations
			  //Then recursively call bestMove with depth = iterable - 1
		  }
		  
		  
		  return result;
	  }
}