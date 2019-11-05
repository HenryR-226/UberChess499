package gradleproject1;

import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(String moveID, boolean team, int row, int col) {
		//System.out.println("Row: " + row + " , col: " + col);
		//System.out.println((char) (row + 'A'));
		String loc = Character.toString((char)row + 'A');
		//System.out.println("Loc: "+ loc);
		loc = loc + Integer.toString(col+1);
		//System.out.println("Loc: " + loc);
		this.setLocation(loc);
		
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
		Rook rook = new Rook("Rook1", piece.isWhite(), piece.getRow(), piece.getCol());
		rook.setLocation(piece.getLocation());
		Bishop bishop = new Bishop("fggfd", piece.isWhite(), piece.getRow(), piece.getCol());
		bishop.setLocation(piece.getLocation());
		ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>();
		validSquares = bishop.getMoves(bishop, board);
		System.out.print(validSquares.size());
		ArrayList<BoardButton> validRook = rook.getMoves(rook, board);
		for (BoardButton b : validRook)
			validSquares.add(b);
		return validSquares;
	}

}