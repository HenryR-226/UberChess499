package gradleproject1;

import java.util.ArrayList;

public class Knight extends Piece {

	boolean first = true;
	public Knight(String moveID, boolean team, Board b, int row, int col) {
		String loc = new String();
		loc = Character.toString((char) (row + 'A'));
		loc = loc + Integer.toString(col + 1);
		this.setLocation(loc);

		this.setName("Knight");
		this.pieceID = moveID;
		this.setIsWhite(team);
		if (team) {
			setAbbreviation('N');
			this.player = b.getWhitePlayer();
		}
		else if (!team) {
			setAbbreviation('n');
			this.player = b.getBlackPlayer();
		}
		this.points = 3.25;
		this.bb = b.getGameBoard();
		if (first==true){
			this.alpha=true;
			first=false;
		} else {
			this.alpha=false;
		}
	}

	/**
	 * @author Henry Rheault
	 *
	 *         Implements Knight move rules. My method is to make lists of 1 and 2
	 *         squares off respectively, then take all combinations of opposing
	 *         number lists.
	 */

	// Tested and verified to work. Ryan Brodsky, 11/3/2019
	@Override
	public ArrayList<BoardButton> getMoves(Piece piece, BoardButton[][] board) {
		Knight p = (Knight) piece;
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

	@Override
	public Double getOffset() {
		if (isWhite) {
			return gridOffsetWhite[col][row];
		} else {
			return gridOffsetBlack[col][row];
		}
	}

	private static final double[][] gridOffsetWhite = new double[][] { { -5, -4, -3, -3, -3, -3, -4, -5 },
			{ -4, -2, 0, 0.5, 0.5, 0, -2, -4 }, { -3, 0, 1, 1.5, 1.5, 1, 0, -3 }, { -3, 0.5, 1.5, 2, 2, 1.5, 0.5, -3 },
			{ -3, 0.5, 1.5, 2, 2, 1.5, 0.5, -3 }, { -3, 0.5, 1, 1.5, 1.5, 1, 0.5, -3 }, { -4, -2, 0, 0, 0, 0, -2, -4 },
			{ -5, -4, -3, -3, -3, -3, -4, -5 }

	};

	private static final double[][] gridOffsetBlack = new double[][] { { -5, -4, -3, -3, -3, -3, -4, -5 },
			{ -4, -2, 0, 0, 0, 0, -2, -4 }, { -3, 0, 1, 1.5, 1.5, 1, 0, -3 }, { -3, 0.5, 1.5, 2, 2, 1.5, 0.5, -3 },
			{ -3, 0.5, 1.5, 2, 2, 1.5, 0.5, -3 }, { -3, 0.5, 1, 1.5, 1.5, 1, 0.5, -3 },
			{ -4, -2, 0, 0.5, 0.5, 0, -2, -4 }, { -5, -4, -3, -3, -3, -3, -4, -5 }

	};

}
