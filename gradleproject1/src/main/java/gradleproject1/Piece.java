/*
 *  SUPERCLASS For all piece objects
 *  Handles Point values for AI, Abbreviations, Move ID for special moves determination,
 *  and team affiliation, True= white, false = black
 *  Each piece Subclass handles an Overrided getMoves() called by the central getMoves() to
 *  generate piece specific moves based on location and move rules.
 */

package gradleproject1;

import java.util.ArrayList;

public class Piece {
	protected int row;			//Array notation column and row.
	protected int col;
	protected boolean isWhite;
	private char abbreviation;
	private String name;
	public String pieceID; 			// unique string to identify a particular piece object
	double points;
	private String location;

	private ArrayList<BoardButton> possibleMoves; 		// Each piece keeps track of where it can currently move.

	public ArrayList<BoardButton> getPossibleMoves() {
		return possibleMoves;
	}

	public void deleteMoveSquare(BoardButton b) throws Exception {
		try {
			possibleMoves.remove(b);
		} catch (Exception e) {
			System.out.println("deleteMoveSquare called with invalid board square argument.");
		}
	}

	public void addMoveSquare(BoardButton b) throws Exception {
		possibleMoves.add(b);
	}

	public char getAbbrev() {
		return this.abbreviation;
	}

	public double getPoints() {
		return this.points;
	}

	public Boolean isWhite() {
		try {
			return this.isWhite;
		} catch (Exception e) {
			return null;
		}
	}

	public void setIsWhite(boolean white) {
		this.isWhite = white;
	}

	public void setAbbreviation(char abbrev) {
		this.abbreviation = abbrev;
	}

	//Returns CHESS notation
	public String getLocation() {
		return this.location;
	}
	
	//Oleg wanted these. Returns ARRAY NOTATION
	public int getCol() {
		return this.col;
	}
	public int getRow() {
		return this.row;
	}

	//Auto updates the location vars, both of them
	public void setLocation(String loc) {
		try {
			assert (loc.length() == 2);
			char[] raw = loc.toCharArray();
			char colc = Character.toUpperCase(raw[0]);
			//System.out.println("Line 84 on Piece, col character val: " + colc);
			assert (colc >='A' && colc <='H');
			int rows = (int) raw[1] - '0' - 1;
			//System.out.println("Line 87 on Piece, row integer val: " + rows);
			assert (rows <=7 && rows>=0);
			this.location = loc;
			this.row = rows;
			this.col = colc-'A';
			//System.out.println("Line 92 on Piece, constructor succeeded with raw values of col: " + colc + " col: " + rows);
		} catch (Exception e) {
			System.out.println("Invalid string sent to setLocation in Piece class, line 78");
			e.printStackTrace();
		}
	}
		

	public void setName(String s) {
		this.name = s;
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<BoardButton> getMovesOverloaded(Piece p, BoardButton[][] board) {
		return null;
	}

	/**
	 * @author Henry Rheault A method to return an array list of all valid board
	 *         button squares valid to move a piece onto.
	 *
	 *         Calls a particular piece's method for move generation for RAW
	 *         (unfiltered) candidate moves, then weeds through them here for GUI/AI
	 *         purposes.
	 *
	 * @param piece object
	 * @return all valid moves allowed
	 */

	// Cocurrent modification:
	// https://stackoverflow.com/questions/18448671/how-to-avoid-concurrentmodificationexception-while-removing-elements-from-arr
	public ArrayList<BoardButton> getMoves(Piece p, BoardButton[][] board) {
		ArrayList<BoardButton> candidateMoves = new ArrayList<BoardButton>(); // Returned list from Piece subclass to be
																				// sifted through based on game rules
		possibleMoves = new ArrayList<BoardButton>();
		boolean team = p.isWhite();
		char c = Character.toUpperCase(p.getAbbrev());
		
		switch (c) {
		case 'P':
			candidateMoves = getMoves((Pawn) p, board);
			break;
		case 'N':
			candidateMoves = getMoves((Knight) p, board);
			break;
		case 'B':
			candidateMoves = getMoves((Bishop) p, board);
			break;
		case 'R':
			candidateMoves = getMoves((Rook) p, board);
			break;
		case 'Q':
			candidateMoves = getMoves((Queen) p, board);
			break;
		case 'K':
			candidateMoves = getMoves((King) p, board);
			System.out.println("Switch Size " + candidateMoves.size());
			break;
		}

		for (int i = 0; i < candidateMoves.size(); i++) { // Get valid moves for particular piece object
			// if king not put in check - No need to test for 'is full' or 'can attack' as
			// each piece method handles that now

			// if (/*King not in check*/){
			this.possibleMoves.add(candidateMoves.get(i));

			/**
			 * This breaks the Loop when all possible moves have been added from candidate
			 * Moves. For some reason with out this break and if statement the for loop
			 * counter "i" goes past candidateMoves.size() and goes until memory crash.
			 */
			if (possibleMoves.size() == candidateMoves.size()) {
				break;
			}
		}

		if (c == 'K')
			possibleMoves.remove(possibleMoves.size() - 1);
		return this.possibleMoves;
	}
	

	public Double getOffset() {
		return 69.69;
	}

	// Started by Henry Rheault on 11/3/2019
	// Designed to sort an array list into actual legible alphabetical order since I
	// don't understand the <T> pattern and want the BBs sorted by string
	// lexiographal order. But way too much work to implement for QOL in printouts

	public ArrayList<BoardButton> sort(ArrayList<BoardButton> a) {

		ArrayList<BoardButton> ret = new ArrayList<BoardButton>();

		return ret;
	}

	/*
	 * Started by James. I don't remember what for. Kurwa.
	 */
	public void deleteThis() {
		String newGuy = "D5";

	}
	//Bishop grid offset to stop compiler error about not having a gridOffset value
    private static final double[][] gridOffset = new double[][]
    {
        {99, 99, 99, 99, 99, 99, 99, 99},
        {99, 99, 99, 99, 99, 99, 99, 99},
        {99, 99, 99, 99, 99, 99, 99, 99},
        {99, 99, 99, 99, 99, 99, 99, 99},
        {99, 99, 99, 99, 99, 99, 99, 99},
        {99, 99, 99, 99, 99, 99, 99, 99},
        {99, 99, 99, 99, 99, 99, 99, 99},
        {99, 99, 99, 99, 99, 99, 99, 99}
    };
}