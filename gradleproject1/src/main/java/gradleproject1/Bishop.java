package gradleproject1;

import java.util.ArrayList;

/**
 * @author Henry Rheault
 */
public class Bishop extends Piece {
	boolean lightSquares; // True for light square bishop, false for dark square

	public Bishop(String moveID, boolean team, int row, int col) {
		this.row = row;
		this.col = col;
		this.setName("Bishop");
		this.pieceID = moveID;
		this.setIsWhite(team);
		if (team)
			setAbbreviation('B');
		else
			setAbbreviation('b');
		this.points = 3;
	}

	// Bishop raw moves generator
	// Tested and verified accurate on Test Board: Henry Rheault, 11/3/2019
	@Override
	public ArrayList<BoardButton> getMoves(Piece piece, BoardButton[][] board) {
		// https://math.stackexchange.com/questions/1566115/formula-that-describes-the-movement-of-a-bishop-in-chess
		// Moving from x1, y1 to x2, y2 is a valid move if abs(x2-x1) = abs(y2 - y1) >
		// 0.
		// Too small brain. Did Rook-style move generation until obstacle instead.
		Bishop p = (Bishop) piece;
		ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>(); // Return values

		int ctrx = piece.col;
		int ctry = piece.row;

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

}
