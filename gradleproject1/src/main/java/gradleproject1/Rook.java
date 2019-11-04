package gradleproject1;

import java.util.ArrayList;

public class Rook extends Piece {
	public Rook(String moveID, boolean team, int row, int col) {
		this.setName("Rook");
		this.pieceID = moveID;
		this.setIsWhite(team);
		if (team)
			setAbbreviation('R');
		else if (!team)
			setAbbreviation('r');
		this.points = 5;
	}

	public ArrayList<BoardButton> getMoves(Rook piece, BoardButton[][] board) {
		Rook p = (Rook) piece;
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
}