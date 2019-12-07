package gradleproject1;

import java.util.ArrayList;

public class Pawn extends Piece {
	// int pawnID; //ID of the pawn
	boolean firstMove = true; // If it is the pawn's first move allowing a double jump
	boolean canAttack; // If the pawn can attack
	boolean blocked; // currently blocked in front?
	char column; // Pawn's "home" column
	int col, row;

	public Pawn(String pawnID, boolean team, Board b, int row, int col) {
		this.setRow(row);
		this.setCol(col);
		String loc = Character.toString((char) ((char) row + 'A'));
		loc = loc + Integer.toString(col + 1);
		//System.out.println("Pawn internal loc set as " + loc + ", line 17 in Pawn"); 	//- tested and works, 12/1/2019, certified not a problem
		this.setLocation(loc);
		this.setName("Pawn");
		this.pieceID = pawnID;
		this.setIsWhite(team);
		if (team) {
			setAbbreviation('P');
			this.player = b.getWhitePlayer();
		}
		else if (!team) {
			setAbbreviation('p');
			this.player = b.getBlackPlayer();
		}
		this.bb = b.getGameBoard();
		this.points = 1;
	}

	@Override
	public ArrayList<BoardButton> getMoves(Piece piece, BoardButton[][] board) {
		Pawn p = (Pawn) piece;
		ArrayList<BoardButton> result = new ArrayList<BoardButton>();

		//String location = p.getLocation();

		//ArrayList<Integer> cords = BoardButton.toArray(location);
		int x = piece.getCol()-1;
		System.out.println("X set to " + x + " , line 39 pawn");
		//FIXME - This is probably not right, this references line 56 of BoardButton. ONLY pawn was having an off by one issue. 12/1/2019
		int y = piece.getRow()+1;			//Problem- crashes program when accessed on Black players- line 114 below
		System.out.println("Y ste to " + y + " , line 42 pawn");
		boolean team = p.isWhite();

		BoardButton highSide;
		BoardButton lowSide;
		BoardButton front;

		//System.out.println("\nLocation Entrered: " + location + "\n");

		//System.out.println("Moves Found:");
		if (team) { // White pawn, goes up
//<<<<<<<
			if (x + 1 < 8 && y + 1 < 9) {
//=======

//>>>>>>>
				highSide = board[x + 1][y + 1];
				//System.out.println("HighSide set to: " + (x + 1) + " " + (y + 1));
			} else {
				highSide = null;
				// System.out.println("Highside set to: null ");
			}
			if (x - 1 > -1 && y + 1 < 9) {
				lowSide = board[x - 1][y + 1];
				//System.out.println("LowSide set to: " + (x - 1) + " " + (y + 1));
			} else {
				lowSide = null;
				//System.out.println("Lowside set to: null ");
			}
			if (y + 1 < 9) {
				front = board[x][y + 1];
				//System.out.println("Front set to: " + x + " " + (y + 1));
			} else {
				front = null;
				//System.out.println("Front set to: null");
			}
			if (p.firstMove()) {
				BoardButton front2 = board[x][y + 2];
				if (front != null && !front2.isFull() && !front.isFull()) {
					//System.out.println("Pawn's first move! Should add " + front2.getAbbreviation() + " to list!");

					result.add(front2);
				}
			}

			if (((highSide != null) && (((highSide.isFull())) && !highSide.getPiece().isWhite())))
				result.add(highSide);
			if (((lowSide != null) && ((lowSide.isFull()) && !lowSide.getPiece().isWhite())))
				result.add(lowSide);
			if (!front.isFull() && y != 7)
				result.add(front);

		} else { // Black team, pawn down
			if (x + 1 < 8 && y - 1 > -1) {
				//System.out.println("X value "  + x + " , Y Value " + y);
				highSide = board[x + 1][y - 1];
				//System.out.println("HighSide set to: " + (x + 1) + " " + (y - 1));
			} else {
				highSide = null;
				//System.out.println("Highside set to null ");
			}
			if (x - 1 > -1 && y - 1 > -1) {
				lowSide = board[x - 1][y - 1];
				//System.out.println("LowSide set to: " + (x - 1) + " " + (y - 1));
			} else {
				lowSide = null;
				//System.out.println("Lowside set to null ");
			}
			if (y - 1 > -1) {
				front = board[x][y - 1];
				//System.out.println("Front set to: " + x + " " + (y - 1));
			} else {
				front = null;
				//System.out.println("Front set to null");
			}
			if (p.firstMove()) {
				BoardButton front2 = board[x][y - 2];
				if (!front2.isFull() && !front.isFull()) {
					result.add(front2);
				}
			}

			if (highSide != null && highSide.isFull() && highSide.getPiece().isWhite())
				result.add(highSide);
			if (lowSide != null && lowSide.isFull() && lowSide.getPiece().isWhite())
				result.add(lowSide);
			if (front != null && !front.isFull() && y != 7)
				result.add(front);
		}
		return result;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public boolean firstMove() {
		return this.firstMove;
	}

	public void madeFirstMove() {
		this.firstMove = false;
	}

	@Override
	public Double getOffset() {
		// System.out.println("Calling Pawn getOffset:, line 152 in Pawn");
		if (isWhite) {
			return gridOffsetWhite[col][row];
		} else {
			return gridOffsetBlack[col][row];
		}
	}

	private static final double[][] gridOffsetBlack = new double[][] { { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 5, 5, 5, 5, 5, 5, 5, 5 }, { 1, 1, 2, 3, 3, 2, 1, 1 }, { 0.5, 0.5, 1, 2.5, 2.5, 1, 0.5, 0.5 },
			{ 0, 0, 0, 2, 2, 0, 0, 0 }, { 0.5, -0.5, -1, 0, 0, -1, -0.5, 0.5 }, { 0.5, 1, 1, -2, -2, 1, 1, 0.5 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 } };

	private static final double[][] gridOffsetWhite = new double[][] { { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0.5, 1, 1, -2, -2, 1, 1, 0.5 }, { 0.5, -0.5, -1, 0, 0, -1, -0.5, 0.5 }, { 0, 0, 0, 2, 2, 0, 0, 0 },
			{ 0.5, 0.5, 1, 2.5, 2.5, 1, 0.5, 0.5 }, { 1, 1, 2, 3, 3, 2, 1, 1 }, { 5, 5, 5, 5, 5, 5, 5, 5 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 } };
}