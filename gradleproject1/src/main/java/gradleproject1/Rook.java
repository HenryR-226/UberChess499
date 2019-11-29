package gradleproject1;

import java.util.ArrayList;

public class Rook extends Piece {
	public Rook(String moveID, boolean team, int row, int col) {
		this.setName("Rook");
		this.pieceID = moveID;
		String loc = Character.toString((char) (row + 'A'));
		// System.out.println("Loc: "+ loc);
		loc = loc + Integer.toString(col + 1);
		this.setLocation(loc);
		this.setIsWhite(team);
		if (team)
			this.setAbbreviation('R');
		else
			this.setAbbreviation('r');
		this.points = 5;
	}

	@Override
	public ArrayList<BoardButton> getMoves(Piece piece, BoardButton[][] board) {
		Rook p = (Rook) piece;
		ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
		BoardButton b;
		String location = p.getLocation();
		char[] c = location.toCharArray();
		int x = ((int) c[0] - 'A');
		int y = (int) c[1] - '0' - 1;
		int ctrx = x;
		int ctry = y;
		boolean team = p.isWhite();

		do {
			try {
				ctry++;
				b = board[x][ctry]; // Go positive Y down it's col
				// System.out.println("Rook checking " + ctrx + " and " + ctry);
				if (!b.isFull() || b.getPiece().isWhite() != team && ctry < 8)
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
				// System.out.println("Rook checking " + x + " and " + ctry);
				if (!b.isFull() || b.getPiece().isWhite() != team && ctry > -1)
					validSquares.add(b);
			} catch (Exception e) {
				break;
			}
		} while (!b.isFull() && ctry > -1);
		do {
			ctrx++;
			try {
				b = board[ctrx][y]; // Go positive X down it's row
				// System.out.println("Rook checking " + ctrx + " and " + ctry);
				if (!b.isFull() || b.getPiece().isWhite() != p.isWhite() && ctrx < 8)
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
				// System.out.println("Rook checking " + ctrx + " and " + ctry);
				if (!b.isFull() || b.getPiece().isWhite() != p.isWhite() && ctrx > -1)
					validSquares.add(b);
			} catch (Exception e) {
				break;
			}
		} while (!b.isFull() && ctrx > -1); // Stop at first occupied or out of bounds square

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

	private static final double[][] gridOffsetWhite = new double[][] { { 0, 0, 0, 0.5, 0.5, 0, 0, 0 },
			{ -.5, 0, 0, 0, 0, 0, 0, -.5 }, { -.5, 0, 0, 0, 0, 0, 0, -.5 }, { -.5, 0, 0, 0, 0, 0, 0, -.5 },
			{ -.5, 0, 0, 0, 0, 0, 0, -.5 }, { -.5, 0, 0, 0, 0, 0, 0, -.5 }, { 0.5, 1, 1, 1, 1, 1, 1, 0.5 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 } };

	private static final double[][] gridOffsetBlack = new double[][] { // H
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0.5, 1, 1, 1, 1, 1, 1, 0.5 }, { -.5, 0, 0, 0, 0, 0, 0, -.5 },
			{ -.5, 0, 0, 0, 0, 0, 0, -.5 }, { -.5, 0, 0, 0, 0, 0, 0, -.5 }, { -.5, 0, 0, 0, 0, 0, 0, -.5 },
			{ -.5, 0, 0, 0, 0, 0, 0, -.5 }, { 0, 0, 0, 0.5, 0.5, 0, 0, 0 } };
}