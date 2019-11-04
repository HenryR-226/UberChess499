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
	public int row, col;
	private boolean isWhite;
	private char abbreviation;
	private String name;
	public String pieceID; // unique string to identify a particular piece object
	double points;
	String location;

	private ArrayList<BoardButton> possibleMoves; // Each piece keeps track of where it can currently move.

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

	public String getLocation() {
		return this.location;
	}

	// BE CAREFUL!! NO CHECKS YET!!
	public void setLocation(String loc) {
		this.location = loc;
	}

	public void setName(String s) {
		this.name = s;
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<BoardButton> getMoves(Piece p, BoardButton[][] board) {
		return null;
	}

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

}