package gradleproject1;

import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(String moveID, boolean team, int row, int col) {
		this.col = col;
		this.row = row;
		this.setName("Queen");
		this.pieceID = moveID;
		this.setIsWhite(team);
		if (team)
			setAbbreviation('Q');
		else if (!team)
			setAbbreviation('q');
		this.points = 9;
	}

	@Override
	public ArrayList<BoardButton> getMoves(Piece piece, BoardButton[][] board) {
		Rook rook = new Rook("Rook1", piece.isWhite(), piece.row, piece.col);
		rook.location = piece.location;
		Bishop bishop = new Bishop("fggfd", piece.isWhite(), piece.row, piece.col);
		bishop.location = piece.location;
		ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
		validSquares = bishop.getMoves(bishop, board);
		System.out.print(validSquares.size());
		ArrayList<BoardButton> validRook = rook.getMoves(rook, board);
		for (BoardButton b : validRook)
			validSquares.add(b);
		return validSquares;
	}

}