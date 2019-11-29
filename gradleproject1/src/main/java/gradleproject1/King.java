package gradleproject1;

import java.util.ArrayList;

public class King extends Piece {

	public King(String moveID, boolean team, int row, int col) {
		String loc = new String();
		loc = Character.toString((char) (row + 'A'));
		loc = loc + Integer.toString(col + 1);
		this.setLocation(loc);

		this.setName("Kang");
		this.pieceID = moveID;
		this.setIsWhite(team);
		if (team)
			setAbbreviation('K');
		else if (!team)
			setAbbreviation('k');
		this.points = 90;
	}

	@Override
	public ArrayList<BoardButton> getMoves(Piece p, BoardButton[][] board) {

		ArrayList<BoardButton> moveList = new ArrayList<>(); // Return values
		// ArrayList<String> validGrids = new ArrayList<String>();
		ArrayList<Integer> validX = new ArrayList<>();
		ArrayList<Integer> validY = new ArrayList<>();

		String s = this.getLocation(); // Readability
		char[] c = s.toCharArray(); // converts location into char array to get the column and row
		Integer col = c[0] - 65;
		// System.out.println("King's col: " + col);
		Integer row = c[1] - 49;
		// System.out.println("King's row: " + row);

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
		// System.out.println("Valid X and Y's for King moves");
		for (Integer x : validX) {
			for (Integer y : validY) {
				// System.out.println("X: " + x + " Y: " + y);

				BoardButton button = board[x][y];
				// Now take all permutations of the row and col values, discarding the
				// 'unchanged' move

				if (!button.isFull() || button.isFull() && button.getPiece().isWhite() != p.isWhite())
					moveList.add(button);

				// System.out.println("Y: " + y);
			}
		}

		moveList.remove(board[col][row]);
		return moveList;
	}

	/*
	 * @author Henry Rheault
	 * 
	 * Overloaded version of method above but takes in a hypothetical move. Probably
	 * going to be called from an Enhanced for loop on the list of possible moves,
	 * to weed it down.
	 * 
	 * Could possibly do it without passing in a Piece but that would require
	 * logical torture, a switch statement, etc to figure out WHICH piece (in the
	 * case of duplicates) the Move is to apply to.
	 * 
	 */
	public boolean isInCheck(BoardButton[][] board, ArrayList<Piece> pieces, Piece piece, String move) {
		// First, process the desired move and set up variables
		char[] c = move.toCharArray(); // Logic's been done a hundred times before
		char[] s2 = piece.getLocation().toCharArray();
		char abbrev = c[0];
		char abbrevUpper = Character.toUpperCase(abbrev);
		boolean team = piece.isWhite();
		boolean result = false;
		int x1, y1, x2, y2; // x & y of desired move square, x & y of current move square
		char c1, c2; // Ease of building a new location string to assign a piece to

		// Do low level formatting and housekeeping before method call
		if (c[1] != 'x') { // if Move is NOT a capture, can't discard the 'x'
			c1 = (c[1]);
			c2 = (c[2]);
			x1 = ((int) c1 - 'A');
			y1 = ((int) c2 - '0' - 1);
		} else { // If move is a capture discard the X
			c1 = c[2];
			c2 = c[3];
			x1 = ((int) c1 - 'A');
			y1 = ((int) c2 - '0' - 1);
		} // s2 being the Piece's location, get it in X and Y:
		x2 = piece.getCol();
		y2 = piece.getRow();

		// Now begin setting up hypothetical board
		// Remove piece from piece list
		pieces.remove(piece);
		// Make new piece (doesn't matter what as it's checking for itself being in
		// check)
		// Move to proposed move square
		Pawn test = new Pawn("Test", true, x1, y1);
		board[x1][y1].setPiece(test);
		// Remove piece from board
		board[x2][y2].removePiece();
		pieces.add(test);
		// test.setLocation((Character.toString(c[1] + Character.toString(c[2]))));
		String location = Character.toString(c[1]);
		location = location + Character.toString(c[2]);
		test.setLocation(location);

		// Call the new hypothetical board!
		result = isInCheck(board);

		// Restore everything since I'm not sure that would be done otherwise, Shallow
		// Copy vs Deep Copy
		pieces.remove(test);
		pieces.add(piece);
		board[x1][y1].removePiece();
		board[x2][y2].setPiece(piece);

		// Return the result we got
		return result;
	}

	/**
	 * @author Henry Rheault
	 * 
	 *         Inputs argument of board layout and sees if king is in check. Does it
	 *         for CURRENTLY in check, TBD one taking a board and a possible
	 *         candidate move. Player parameter for piece list access
	 */

	public boolean isInCheck(BoardButton[][] board) {
		// String location
		boolean result = false;
		char[] c = location.toCharArray();
		int x = ((int) c[0] - 'A');
		int y = ((int) c[1] - '0' - 1);
		BoardButton b;
		Piece p;
		char abbrev;
		int ctrx = x;
		int ctry = y;
		boolean team = this.isWhite();
		ArrayList<Integer> validX1 = new ArrayList<Integer>();
		ArrayList<Integer> validX2 = new ArrayList<Integer>();
		ArrayList<Integer> validY1 = new ArrayList<Integer>();
		ArrayList<Integer> validY2 = new ArrayList<Integer>();
		// Straight up Y checks
		do {
			try {
				b = board[x][++ctry];
				if (b.getPiece() != null) {
					p = b.getPiece();
					abbrev = Character.toUpperCase(p.getAbbrev());
					if (p.isWhite() == team)
						break; // Friendly piece? If so no problem
					else {
						if (abbrev == 'K' && ctry == ++y)
							return true;
						if (abbrev == 'R' || abbrev == 'Q')
							return true;
					}
				}
			} catch (Exception e) {
				break;
			}
		} while (!result && ctry < 8);
		ctry = y;
		// Straight down Y
		do {
			try {
				b = board[x][--ctry];
				if (b.getPiece() != null) {
					p = b.getPiece();
					abbrev = Character.toUpperCase(p.getAbbrev());
					if (p.isWhite() == team)
						break; // Friendly piece? If so no problem
					else {
						if (abbrev == 'K' && ctry == --y)
							return true;
						if (abbrev == 'R' || abbrev == 'Q')
							return true;
					}
				}
			} catch (Exception e) {
				break;
			}
		} while (!result && ctry > -1);
		ctry = y;
		// Due left
		do {
			try {
				b = board[--ctrx][y];
				if (b.getPiece() != null) {
					p = b.getPiece();
					abbrev = Character.toUpperCase(p.getAbbrev());
					if (p.isWhite() == team)
						break; // Friendly piece? If so no problem
					else {
						if (abbrev == 'K' && ctry == ++y)
							return true;
						if (abbrev == 'R' || abbrev == 'Q')
							return true;
					}
				}
			} catch (Exception e) {
				break;
			}
		} while (!result && ctrx < 8);
		ctrx = x;
		// Due Right
		do {
			try {
				b = board[++ctrx][y];
				if (b.getPiece() != null) {
					p = b.getPiece();
					abbrev = Character.toUpperCase(p.getAbbrev());
					if (p.isWhite() == team)
						break; // Friendly piece? If so no problem
					else {
						if (abbrev == 'K' && ctry == ++y)
							return true;
						if (abbrev == 'R' || abbrev == 'Q')
							return true;
					}
				}
			} catch (Exception e) {
				break;
			}
		} while (!result && ctrx < 8);
		ctrx = x;
		// Bishop checks
		// Up and Right
		do {
			try {
				b = board[++ctrx][++ctry];
				if (b.getPiece() != null) {
					p = b.getPiece();
					abbrev = Character.toUpperCase(p.getAbbrev());
					if (p.isWhite() == team)
						break; // Friendly piece? If so no problem
					else {
						if (abbrev == 'K' && ctry == ++y)
							return true;
						if (abbrev == 'B' || abbrev == 'Q')
							return true;
						if (team && abbrev == 'P' && ctry == ++y)
							return true;
					}
				}
			} catch (Exception e) {
				break;
			}
		} while (!result && ctry < 8 && ctrx < 8);
		ctry = y;
		ctrx = x;
		// Down and Right
		do {
			try {
				b = board[++ctrx][--ctry];
				if (b.getPiece() != null) {
					p = b.getPiece();
					abbrev = Character.toUpperCase(p.getAbbrev());
					if (p.isWhite() == team)
						break; // Friendly piece? If so no problem
					else {
						if (abbrev == 'K' && ctry == --y)
							return true;
						if (abbrev == 'B' || abbrev == 'Q')
							return true;
						if (!team && abbrev == 'P' && ctry == --y)
							return true;
					}
				}
			} catch (Exception e) {
				break;
			}
		} while (!result && ctry > -1 && ctrx < 8);
		ctry = y;
		ctrx = x;
		// Down and Left
		do {
			try {
				b = board[--ctrx][--ctry];
				if (b.getPiece() != null) {
					p = b.getPiece();
					abbrev = Character.toUpperCase(p.getAbbrev());
					if (p.isWhite() == team)
						break; // Friendly piece? If so no problem
					else {
						if (abbrev == 'K' && ctry == ++y)
							return true;
						if (abbrev == 'R' || abbrev == 'Q')
							return true;
						if (!team && abbrev == 'P' && ctry == --y)
							return true;
					}
				}
			} catch (Exception e) {
				break;
			}
		} while (!result && ctry > -1 && ctrx > -1);
		ctrx = x;
		ctry = y;
		// Up and Left
		do {
			try {
				b = board[--ctrx][++ctry];
				if (b.getPiece() != null) {
					p = b.getPiece();
					abbrev = Character.toUpperCase(p.getAbbrev());
					if (p.isWhite() == team)
						break; // Friendly piece? If so no problem
					else {
						if (abbrev == 'K' && ctry == ++y)
							return true;
						if (abbrev == 'R' || abbrev == 'Q')
							return true;
						if (team && abbrev == 'P' && ctry == ++y)
							return true;
					}
				}
			} catch (Exception e) {
				break;
			}
		} while (!result && ctry < 8 && ctrx > -1);
		ctrx = x;
		ctry = y;
		// Knight checks
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
				p = board[i][j].getPiece();
				if (p != null)
					if (p.getAbbrev() == 'N' || p.getAbbrev() == 'n')
						return true;
			}
		}
		// Combine X+-2 with Y+-1
		for (Integer i : validX1) {
			for (Integer j : validY2) {
				p = board[i][j].getPiece();
				if (p != null)
					if (p.getAbbrev() == 'N' || p.getAbbrev() == 'n')
						return true;
			}
		}
		// Not in check! Returns default of False. Should there be an error causing it
		// to
		// somehow not break out but still set to 'true' probably best to return that
		// and not just hardcode return 'false'.
		return result;
	}

	@Override
	public Double getOffset() {
		if (isWhite) {
			return gridOffsetWhite[col][row];
		} else {
			return gridOffsetBlack[col][row];
		}
	}

	private static final double[][] gridOffsetWhite = new double[][] { { 2, 3, 1, 0, 0, 1, 3, 2 },
			{ 2, 2, 0, 0, 0, 0, 2, 2 }, { -3, -4, -4, -5, -5, -4, -4, -3 }, { -3, -4, -4, -5, -5, -4, -4, -3 },
			{ -3, -4, -4, -5, -5, -4, -4, -3 }, { -2, -3, -3, -4, -4, -3, -3, -2 }, { -1, -2, -2, -2, -2, -2, -2, -1 },
			{ -3, -4, -4, -5, -5, -4, -4, -3 }, { -3, -4, -4, -5, -5, -4, -4, -3 } };

	private static final double[][] gridOffsetBlack = new double[][] { { -3, -4, -4, -5, -5, -4, -4, -3 },
			{ -3, -4, -4, -5, -5, -4, -4, -3 }, { -3, -4, -4, -5, -5, -4, -4, -3 }, { -3, -4, -4, -5, -5, -4, -4, -3 },
			{ -2, -3, -3, -4, -4, -3, -3, -2 }, { -1, -2, -2, -2, -2, -2, -2, -1 }, { 2, 2, 0, 0, 0, 0, 2, 2 },
			{ 2, 3, 1, 0, 0, 1, 3, 2 } };
}