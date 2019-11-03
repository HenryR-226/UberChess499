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
	private boolean isWhite;
	private char abbreviation;
	private String name;
	public String pieceID; // unique string to identify a particular piece object
	double points;
	String location;
	private static BoardButton[][] board;
	private static Board b;

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

	public static BoardButton[][] getBoard() {
		return board;
	}

	public static void setBoard(Board boardInput) {
		Piece.b = boardInput;
		Piece.board = b.getGameBoard();
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
	public ArrayList<BoardButton> getMoves(Piece p) {
		ArrayList<BoardButton> candidateMoves = new ArrayList<BoardButton>(); // Returned list from Piece subclass to be
																				// sifted through based on game rules
		Board board;
		possibleMoves = new ArrayList<BoardButton>();
		boolean team = p.isWhite();
		char c = Character.toUpperCase(p.getAbbrev());
		switch (c) {
		case 'P':
			candidateMoves = getPawnMoves((Pawn) p);
			break;
		case 'N':
			candidateMoves = getKnightMoves(p);
			break;
		case 'B':
			candidateMoves = getBishopMoves(p);
			break;
		case 'R':
			candidateMoves = getRookMoves(p);
			break;
		case 'Q':
			candidateMoves = getQueenMoves(p);
			break;
		case 'K':
			candidateMoves = getKingMoves(p);
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

	/**
	 * @author Henry Rheault Generates possible pawn moves. Board will not have to
	 *         sift through these as it generates only valid moves for itself, as a
	 *         consequence of needing to check whether it can attack.
	 */
	public ArrayList<BoardButton> getPawnMoves(Pawn p) {
		ArrayList<BoardButton> result = new ArrayList<BoardButton>();

		String location = p.getLocation();
		// char[] c = location.toCharArray();
		// int x = ((int) c[0] - 65);
		// int y = (int) c[1] - 49;

		ArrayList<Integer> cords = BoardButton.toArray(location);
		int x = cords.get(0);
		int y = cords.get(1);
		boolean team = p.isWhite();

		BoardButton highSide;
		BoardButton lowSide;
		BoardButton front;

		System.out.println("\nLocation Entrered: " + location + "\n");

		System.out.println("Moves Found:");
		if (team) { // White pawn, goes up
			if (x + 1 < 8) {
				highSide = board[x + 1][y + 1];
				System.out.println("HighSide set to: " + (x + 1) + " " + (y + 1));
			} else {
				highSide = null;
				System.out.println("Highside set to: null ");
			}
			if (x - 1 > -1) {
				lowSide = board[x - 1][y + 1];
				System.out.println("LowSide set to: " + (x - 1) + " " + (y + 1));
			} else {
				lowSide = null;
				System.out.println("Lowside set to: null ");
			}
			if (y + 1 < 9) {
				front = board[x][y + 1];
				System.out.println("Front set to: " + x + " " + (y + 1));
			} else {
				front = null;
				System.out.println("Front set to: null");
			}
			if (p.firstMove()) {
				BoardButton front2 = board[x][y + 2];
				if (!front2.isFull()) {
					System.out.println("Pawn's first move! Should add " + front2.getAbbreviation() + " to list!");

					result.add(front2);
				}
			}

			if (((highSide != null) && (((highSide.getPiece() == null)) || (!highSide.getPiece().isWhite()))))
				result.add(highSide);
			if (((lowSide != null) && (((lowSide.getPiece() == null)) || (!lowSide.getPiece().isWhite()))))
				result.add(lowSide);
			if (!front.isFull() && y != 7)
				result.add(front);

		} else { // Black team, pawn down
			if (x + 1 < 8) {
				highSide = board[x + 1][y - 1];
				System.out.println("HighSide set to: " + (x + 1) + " " + (y - 1));
			} else {
				highSide = null;
				System.out.println("Highside set to null ");
			}
			if (x - 1 > -1) {
				lowSide = board[x - 1][y - 1];
				System.out.println("LowSide set to: " + (x - 1) + " " + (y - 1));
			} else {
				lowSide = null;
				System.out.println("Lowside set to null ");
			}
			if (y - 1 > -1) {
				front = board[x][y - 1];
				System.out.println("Front set to: " + x + " " + (y - 1));
			} else {
				front = null;
				System.out.println("Front set to null");
			}
			if (p.firstMove()) {
				BoardButton front2 = board[x][y - 2];
				if (!front2.isFull()) {
					result.add(front2);
				}
			}

			if (highSide != null && highSide.isFull() && highSide.getPiece().isWhite())
				result.add(highSide);
			if (lowSide != null && lowSide.isFull() && lowSide.getPiece().isWhite())
				result.add(lowSide);
			if (!front.isFull() && y != 7)
				result.add(front);
		}
		return result;
	}

	// Returns LIST of BOARDBUTTONS which will be at Indexes the piece is allowed to
	// move to
	// Tested and verified to be working: Henry Rheault, 11/3/2019
	public ArrayList<BoardButton> getKingMoves(Piece p) {

		ArrayList<BoardButton> moveList = new ArrayList<>(); // Return values
		// ArrayList<String> validGrids = new ArrayList<String>();
		ArrayList<Integer> validX = new ArrayList<>();
		ArrayList<Integer> validY = new ArrayList<>();

		String s = this.location; // Readability
		char[] c = s.toCharArray(); // converts location into char array to get the column and row
		Integer col = c[0] - 65;
		System.out.println("King's col: " + col);
		Integer row = c[1] - 49;
		System.out.println("King's row: " + row);

		// Populates an array list with strings of Int, for taking all permutations of
		// to get move list grid squares
		if (col != 0 && col != 7) {
			validX.add(col);
			col = col + 1;
			validX.add(col);
			col = col - 2;
			validX.add(col);
			col = col + 1; // Reset to original position
		} else if (col == 0) {
			validX.add(col);
			col = col + 1;
			validX.add(col);
			col = col - 1;
		} else if (col == 7) {
			validX.add(col);
			col = col - 1;
			validX.add(col);
			col = col + 1;
		}

		if (row != 7 && row != 0) {
			validY.add(row);
			row = row + 1;
			validY.add(row);
			row = row - 2;
			validY.add(row);
			row = row + 1;
		} else if (row == 0) {
			validY.add(row);
			row = row + 1;
			validY.add(row);
			row = row - 1;
		} else if (row == 7) {
			validY.add(row);
			row = row - 1;
			validY.add(row);
			row = row + 1;
		}
		System.out.println("Valid X and Y's for King moves");
		for (Integer x : validX) {
			for (Integer y : validY) {
				System.out.println("X: " + x + " Y: " + y);

				BoardButton button = board[x][y];
				// Now take all permutations of the row and col values, discarding the
				// 'unchanged' move

				if (!button.isFull() || button.isFull() && button.getPiece().isWhite() != p.isWhite())
					moveList.add(button);

				// System.out.println("Y: " + y);
			}
		}

		moveList.remove(board[col][row]);
		possibleMoves = moveList;
		System.out.println(possibleMoves.size());
		return possibleMoves;
	}

	/*
	 * @author Henry Rheault
	 *
	 * Generates Queen move squares. Calls Rook and Bishop methods to get what would
	 * be valid in either case, then combines the lists. Big Brain!!!!!
	 */
	public ArrayList<BoardButton> getQueenMoves(Piece p) {
		ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
		validSquares = p.getBishopMoves(p);
		ArrayList<BoardButton> validRook = p.getRookMoves(p);
		for (BoardButton b : validRook)
			validSquares.add(b);
		return validSquares;
	}

	// Bishop raw moves generator
	// Tested and verified accurate on Test Board: Henry Rheault, 11/3/2019
	public ArrayList<BoardButton> getBishopMoves(Piece p) {
		// https://math.stackexchange.com/questions/1566115/formula-that-describes-the-movement-of-a-bishop-in-chess
		// Moving from x1, y1 to x2, y2 is a valid move if abs(x2-x1) = abs(y2 - y1) >
		// 0.
		// Too small brain. Did Rook-style move generation until obstacle instead.

		ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>(); // Return values
		String s = this.location; // Readability
		char[] c = s.toCharArray();
		Integer col = c[0] - 'A'; // 0 at A, 7 at H
		Integer row = (int) c[1] - 49; // 0 at 1, 7 at 8

		int ctrx = col;
		int ctry = row;

		BoardButton b;
		// if ((Math.abs(i - col)==(Math.abs(j - row))) && Math.abs(i - col)>0){

		// Go in each of 4 diagonals.
		// Plus X, Plus Y:
		do {
			ctrx++;
			ctry++;
			try {
				b = board[ctrx][ctry];
				if (!b.isFull() || b.isWhite() != p.isWhite() && ctry < 8 && ctrx < 8)
					validSquares.add(b);
			} catch (Exception e) {
				break;
			}
		} while (!b.isFull() && ctrx < 8 && ctry < 8);

		// Minus X, Plus Y
		ctrx = col;
		ctry = row;
		do {
			ctrx--;
			ctry++;
			try {
				b = board[ctrx][ctry];
				if (!b.isFull() || b.isWhite() != p.isWhite() && ctry < 8 && ctrx > -1)
					validSquares.add(b);
			} catch (Exception e) {
				break;
			}

		} while (!b.isFull() && ctrx > -1 && ctry < 8);

		// Minus X, Minus Y:
		ctrx = col;
		ctry = row;
		do {
			ctrx--;
			ctry--;
			try {
				b = board[ctrx][ctry];
				if (!b.isFull() || b.isWhite() != p.isWhite() && ctry > -1 && ctrx > -1)
					validSquares.add(b);
			} catch (Exception e) {
				break;
			}
		} while (!b.isFull() && ctrx > -1 && ctry > -1);

		// Plus X, Minus Y:
		ctrx = col;
		ctry = row;
		do {
			ctrx++;
			ctry--;
			try {
				b = board[ctrx][ctry];
				if (!b.isFull() || b.isWhite() != p.isWhite() && ctry > -1 && ctrx < 8)
					validSquares.add(b);
			} catch (Exception e) {
				break;
			}
		} while (!b.isFull() && ctrx < 8 && ctry > -1);

		return validSquares;
	}

	/**
	 * @author Henry Rheault
	 *
	 *         So I was thinking that I don't want the Board to waste time checking
	 *         possible squares if they are blocked by another piece on the
	 *         'infinite range' piece classes. So it checks for piece occupying a
	 *         square in front of it and stops generating moves in that direction
	 *         once the square is occupied in front of it.
	 */
	// Tested and found accurate: Henry Rheault, 11/3/2019
	public ArrayList<BoardButton> getRookMoves(Piece p) {
		ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
		BoardButton b;
		String location = p.getLocation();
		char[] c = location.toCharArray();
		int x = ((int) c[0] - 'A');
		int y = (int) c[1] - '0' - 1;
		int ctrx = x;
		int ctry = y;
		do {
			ctry++;
			try {
				b = board[x][ctry]; // Go positive Y down it's col

				if (!b.isFull() || b.isWhite() != p.isWhite() && ctry < 8)
					validSquares.add(b);
			} catch (Exception e) {
				break;
			}
		} while (!b.isFull() && ctry < 8); // Stop at first occupied or out of bounds square
		ctry = y;
		do {
			ctry--;
			try {
				b = board[x][ctry]; // Go negative Y down it's col
				System.out.println("Checking " + x + " and " + ctry);
				if (!b.isFull() || b.isWhite() != p.isWhite() && ctry > -1)
					validSquares.add(b);
			} catch (Exception e) {
				break;
			}
		} while (!b.isFull() && ctry > -1);
		do {
			ctrx++;
			try {
				b = board[ctrx][y]; // Go positive X down it's row
				if (!b.isFull() || b.isWhite() != p.isWhite() && ctrx < 8)
					validSquares.add(b);
			} catch (Exception e) {
				break;
			}
		} while (!b.isFull() && ctrx < 8);
		ctrx = x;
		do {
			ctrx--;
			try {
				b = board[ctrx][y]; // Negative X down it's row
				if (!b.isFull() || b.isWhite() != p.isWhite() && ctrx > -1)
					validSquares.add(b);
			} catch (Exception e) {
				break;
			}
		} while (!b.isFull() && ctrx > -1); // Stop at first occupied or out of bounds square

		return validSquares;
	}

	/**
	 * @author Henry Rheault
	 *
	 *         Implements Knight move rules. My method is to make lists of 1 and 2
	 *         squares off respectively, then take all combinations of opposing
	 *         number lists.
	 */

	// Tested and verified to work. Ryan Brodsky, 11/3/2019
	public ArrayList<BoardButton> getKnightMoves(Piece p) {
		ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
		ArrayList<Integer> validX1 = new ArrayList<Integer>(); // x +-1
		ArrayList<Integer> validY1 = new ArrayList<Integer>(); // x +-2
		ArrayList<Integer> validX2 = new ArrayList<Integer>(); // y +-1
		ArrayList<Integer> validY2 = new ArrayList<Integer>(); // y +-2

		String location = p.getLocation();
		char[] c = location.toCharArray();
		int x = ((int) c[0] - 'A');
		int y = (int) c[1] - '0' - 1;

		// Generate X valuses valid for each list

		validX1.add(x - 1);
		validX2.add(x - 2);
		validX1.add(x + 1);
		validX2.add(x + 2);

		int lsize = validX1.size();
		for (int i = 0; i < lsize; i++) {
			Integer a = validX1.get(i);
			if (a < 0 || a > 7) {
				lsize--;
				validX1.remove(a);
			}
		}
		lsize = validX2.size();
		for (int i = 0; i < lsize; i++) {
			System.out.print(i);
			Integer a = validX2.get(i);
			if (a < 0 || a > 7) {
				lsize--;
				validX2.remove(a);
			}
		}
		// Generate Y values valid for each list
		validY1.add(y - 1);
		validY2.add(y - 2);
		validY1.add(y + 1);
		validY2.add(y + 2);
		lsize = validY1.size();
		for (int i = 0; i < lsize; i++) {
			Integer a = validY1.get(i);
			if (a < 0 || a > 7) {
				lsize--;
				validY1.remove(a);
			}
		}
		lsize = validY2.size();
		System.out.print(lsize);
		for (int i = 0; i < lsize; i++) {

			Integer a = validY2.get(i);
			if (a < 0 || a > 7) {
				lsize--;
				validY2.remove(a);
			}
		}
		// Combine X+-1 with Y+-2
		for (Integer i : validX1) {
			for (Integer j : validY2) {
				if (!board[i][j].isFull() || board[i][j].isFull() && board[i][j].getPiece().isWhite() != p.isWhite())
					validSquares.add(board[i][j]);
			}
		}
		// Combine X+-2 with Y+-1
		for (Integer i : validX2) {
			for (Integer j : validY1) {
				if (!board[i][j].isFull() || board[i][j].isFull() && board[i][j].getPiece().isWhite() != p.isWhite())
					validSquares.add(board[i][j]);
			}
		}

		return validSquares;
	}

	// Started by Henry Rheault on 11/3/2019
	// Designed to sort an array list into actual legible alphabetical order since I
	// don't understand the <T> nonsense and want the BBs sorted by string
	// lexiographal order
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