package gradleproject1;

import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(String moveID, boolean team, Board b, int row, int col) {
		String loc = Character.toString((char) ((char) row + 'A'));
		loc = loc + Integer.toString(col + 1);
		this.setLocation(loc);

		this.setName("Queen");
		this.pieceID = moveID;
		this.setIsWhite(team);
		if (team) {
			setAbbreviation('Q');
			this.player = b.getWhitePlayer();
		}
		else if (!team) {
			setAbbreviation('q');
			this.player = b.getBlackPlayer();
		}
		this.points = 9;
		this.bb = b.getGameBoard();
	}

	@Override
	public ArrayList<BoardButton> getMoves(Piece piece, BoardButton[][] board) {
		Rook rook = new Rook("Rook1", piece.isWhite(), this.player.getBoard(), piece.getRow(), piece.getCol());
		rook.setLocation(piece.getLocation());
		Bishop bishop = new Bishop("fggfd", piece.isWhite(), this.player.getBoard(), piece.getRow(), piece.getCol());
		bishop.setLocation(piece.getLocation());
		ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
		validSquares = bishop.getMoves(bishop, board);
		ArrayList<BoardButton> validRook = rook.getMoves(rook, board);
		for (BoardButton b : validRook)
			validSquares.add(b);
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

	private static final double[][] gridOffsetWhite = new double[][] { { -2, -1, -1, -0.5, -0.5, -1, -1, -2 },
			{ -1, 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0.5, 0.5, 0.5, 0.5, 0, -1 }, { 0, 0, 0.5, 0.5, 0.5, 0.5, 0, -0.5 },
			{ -0.5, 0, 0.5, 0.5, 0.5, 0.5, 0, -0.5 }, { -1, 0.5, 0.5, 0.5, 0.5, 0.5, 0, -1 },
			{ -1, 0, 0.5, 0, 0, 0, 0, -1 }, { -2, -1, -1, -0.5, -0.5, -1, -1, -2 } };

	private static final double[][] gridOffsetBlack = new double[][] { { -2, -1, -1, -0.5, -0.5, -1, -1, -2 },
			{ -1, 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, 0.5, 0.5, 0.5, 0.5, 0, -1 },
			{ -0.5, 0, 0.5, 0.5, 0.5, 0.5, 0, -0.5 }, { 0, 0, 0.5, 0.5, 0.5, 0.5, 0, -0.5 },
			{ -1, 0.5, 0.5, 0.5, 0.5, 0.5, 0, -1 }, { -1, 0, 0.5, 0, 0, 0, 0, -1 },
			{ -2, -1, -1, -0.5, -0.5, -1, -1, -2 } };

}