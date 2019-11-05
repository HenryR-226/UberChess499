package gradleproject1;

import java.util.ArrayList;

public class King extends Piece {

	public King(String moveID, boolean team, int row, int col) {
		String loc = new String();
		loc = Character.toString((char)row + 'A');
		loc = loc + Integer.toString(col+1);
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
		return moveList;
	}

}