package gradleproject1;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
	private ArrayList<Move> moveList = new ArrayList<Move>();
	private ArrayList<Piece> pieceList = new ArrayList<Piece>(); // Pieces currently on the board
	private ArrayList<Piece> piecesLost = new ArrayList<Piece>(); // Pieces lost by that gamer
	private boolean hasCastled; // Has the gamer castled
	private boolean canCastle; // CAN the gamer castle, false if king and/or close side rook moves

	private boolean team;
	private boolean ai = false;
	private static double whitePoints; // AI points variable for current game state
	private static double blackPoints;
	private Boolean g; // True = won, false = lost, null = in progress, thus upper case 'B'
	private GameState game;

	private static int numTeams = 0; // Don't make more than 2 teams, dingus

	private Board b;
	private BoardButton[][] bb;
	
	public Player(boolean team, boolean ai, Integer depth) {
		try {
			assert (numTeams < 2);
			this.ai = ai;
			this.team = team;
			numTeams++;
			this.depth = depth;
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
	
	public void removePiece(Piece p) {
		pieceList.remove(p);
		piecesLost.add(p);
	}

	public void addMove(Move moveIteration) {
		moveList.add(moveIteration);
		//System.out.println("Move added: " + moveIteration);
	}

	public String getLastMoveAbbrev() {
		int index = moveList.size();
		return moveList.get(index - 1).getAbbreviation(); // Cast to string and return while not removing last move
	}
	
	public Move getLastMove() {
		int index = moveList.size();
		return moveList.get(index-1);
	}

	public ArrayList<Move> getMoves(){
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
		if (p.isWhite()) b.getBlackPlayer().removePiece(p);
		else b.getWhitePlayer().removePiece(p); 
	}

	public Boolean getGame() { // See if the game has won or lost
		return g;
	}

	public void setGame(Boolean b) {
		this.g = b;
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
	
	//Evaluates the points on the board given a particular move object
	public double evalPoints(Move m) {
		ArrayList<Piece> instanceOfPieces = new ArrayList<Piece>();
		instanceOfPieces = pieceList;
		Piece p = m.getPiece();
		if (m.wasCaptured()) instanceOfPieces.remove(m.getCaptured());
		p.setLocation(m.getNew().getAbbreviation());				//Set the piece instance to the new place on the board
		double points = evalPoints();							//Get the goods
		p.setLocation(m.getOld().getAbbreviation());			//Reset the move
		return points;
	}
	
	/**
	 * @author Henry Rheault, Ryan Brodsky
	 * Helper method to make an AI call in ONE call only. Calls setUp, bestMove and cleanUp.
	 * Depth is immutible (for now) from GameState and gotten below.
	 */
	public Move generateAIMove() {
		setUpAI(depth);
		iterableTeam = this.isTeam();
		result = bestMove(this, depth);
		Move m = result;
		cleanUpAI();
		return m;
	}

	/**
	 * @author Henry Rheault
	 * Helper method to allocate all the AI's instance variables to what we need. To be called in before an AI call, potentially
	 * by another helper method.
	 * 
	 * Intended to be where heavy/slow/'deep copy' calls are made so they're only run ONCE. Okay here, not okay in bestMove().
	 */
	public void setUpAI(int depth) {
		iterable = depth * 2;
		blackPieceList = b.getBlackPlayer().getPieceList();
		whitePieceList = b.getWhitePlayer().getPieceList();
		blackModifiedPieceList = new ArrayList<Piece>();
		whiteModifiedPieceList = new ArrayList<Piece>();
		possibleMoves = new ArrayList<Move>();
		whiteMoves = new char[depth][4];												//Hypothetical moves of white player 
		blackMoves = new char[depth][4];	
		result = null;
		alteredGameBoard = bb.clone();
	}
	
	//AI vars/flags to be set outside of the method so it's not re-allocating them or doing anything funny every loopthrough
	private ArrayList<Piece> blackPieceList;          //BOTH STATIC AND UNCHANGING THROUGH METHOD CALL
	private ArrayList<Piece> whitePieceList;
	private ArrayList<Piece> blackModifiedPieceList;  //Used to reflect potential changes in the recursive calls of hypothetical game states
	private ArrayList<Piece> whiteModifiedPieceList;  //Arrays of Char in lieu of String so that this executes faster. Wrapper class because this is passed by REFERENCE thus a bit faster.
	private ArrayList<Move> possibleMoves;
	private char[][] whiteMoves;												//Hypothetical moves of white player 
	private char[][] blackMoves;												//Hypothetical moves of black player for 
	private Integer depth;
	private double points, maxpoints = -99999999;
	private Move result;															//Move to return
	private int iterable;															//Loop counter of recursive calls, depth*2 at the start
	private boolean iterableTeam = true;
	private BoardButton[][] alteredGameBoard;
	/**
	 * @author Henry Rheault
	 * Recursive Method for the AI to generate the best possible moves, and return the one with the most points.
	 * @param player- the player we're starting on- and depth- the amount of moves down (both sides) to eval.
	 */
	public Move bestMove(Player gamer, int depth) { // Iterable team is handled in setUpAI() and will be the same team as the 'gamer' object/instance
		if (depth != -1) { // -1 means we're at the end
			char[][] blackArray = blackMoves.clone();						//Clone the char array for each step of the game tree down
			char[][] whiteArray = whiteMoves.clone();
			possibleMoves = b.getAllMoves(gamer.getPieceList(), alteredGameBoard);
			for (Move m : possibleMoves) {  		//Get the piece associated with this move, update it's location. If it's a capture make a clone of pieces lists and make the alterations
				if (iterableTeam)					 //Then recursively call bestM ove with depth = iterable - 1
					whiteArray[(int) depth - iterable / 2] = m.getAbbreviation().toCharArray();   //Set this particular row (move) 's move abbreviation to this particular move object
				else
					blackArray[(int) (depth - iterable / 2)] = m.getAbbreviation().toCharArray(); // TODO- check math/invariant, this may not be right or give ArrayOutOfBounds
				Piece p = m.getPiece();
				Piece cap = m.getCaptured();
				points = evalPoints(m);
				if (m.wasCaptured()) {
					if (iterableTeam) /* Is the move made by white or black */ {
						blackModifiedPieceList = blackPieceList; // Copy black piece list to make a change to it
						blackModifiedPieceList.remove(cap); // Remove this piece hypothetically captured to
					} else {
						whiteModifiedPieceList = whitePieceList; //
						whiteModifiedPieceList.remove(cap);
					}
				}
				if (depth == 0 || possibleMoves.size() == 0) { // If we've reached the bottom OR there's no valid moves
																// => end of game
					points = this.evalPoints();
					if (points > maxpoints) {
						System.out.println("New best found! " + points + " is better than " + maxpoints);
						result = m;
						maxpoints = points;
						if (iterableTeam)
							whiteMoves = whiteArray;
						else
							blackMoves = blackArray;
						// whitemoves or blackmoves set to THIS array of character
					}
				}
				iterableTeam = !iterableTeam;
				Move move = bestMove(b.getOtherPlayer(gamer), depth - 1);				//Recursive Call
			}
		}	//else if (depth==-1){														//Reach this block if depth = -1 meaning we've hit 0 and are now evaluating best move
		else {  
			Move move = bestMove(b.getOtherPlayer(gamer), depth); 						//I think
		}
		  return result;
	}
	  
	  /**
	   * @author Henry Rheault
	   * A clean-up method to reset all the variables to make sure nothing funny happens when returning from an
	   * AI call. Sets all the fields needed by AI to null so they're available for garbage collection.
	   */
	  public void cleanUpAI() {
		  blackPieceList = null;
		  whitePieceList = null;
		  blackModifiedPieceList = null;
		  whiteModifiedPieceList = null;
		  whiteMoves = null;
		  blackMoves = null;
		  result = null;
		  iterable = 0;
	  }
	  
	  /**
	   * @author Henry Rheault
	   * Chooses best move out of the list of possible moves passed in at 0 steps down.
	   * Tested and works, 11/23/2019, by Henry and Ryan
	   */
	  public Move bestMove(ArrayList<Move> possibles) {
		  //0 length => Checkmate, future
		  assert(possibles.size()!=0) : "Yo dummy you passed in a 0 length array list, Line 172 of Player, no valid moves here";
		  double result = -99999999;
		  Move returnStatement = null;
		  double points = -99999999;
		  for (Move m : possibles) {
			  //Eval the points this would result in
			  points = evalPoints(m);
			  if (points>=result) { result = points; returnStatement = m; }
		  }
		  return returnStatement;
	  }
}