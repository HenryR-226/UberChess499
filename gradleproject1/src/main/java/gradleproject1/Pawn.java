package gradleproject1;

import java.util.ArrayList;
import java.util.Scanner;

public class Pawn extends Piece {
	// int pawnID; //ID of the pawn
	//boolean firstMove = true; 	// If it is the pawn's first move allowing a double jump
	boolean canAttack; 			// If the pawn can attack
	boolean blocked; 			// currently blocked in front?
	char column; 				// Pawn's "home" column
	int col, row;
	Board b;
	private static Scanner scan;

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
		scan = Main.scan;
	}

	@Override
	public ArrayList<BoardButton> getMoves(Piece piece, BoardButton[][] board) {
		Pawn p = (Pawn) piece;
		ArrayList<BoardButton> result = new ArrayList<BoardButton>();

		String location = p.getLocation();

		ArrayList<Integer> cords = BoardButton.toArray(location);
		int x = cords.get(0);
		//System.out.println("X set to " + x + " , line 39 pawn");
		//FIXME - This is probably not right, this references line 56 of BoardButton. ONLY pawn was having an off by one issue. 12/1/2019
		int y = cords.get(1)-1;			//Problem- crashes program when accessed on Black players- line 114 below
		//System.out.println("Y ste to " + y + " , line 42 pawn");
		boolean team = p.isWhite();

		BoardButton highSide;
		BoardButton lowSide;
		BoardButton front;

		//System.out.println("\nLocation Entrered: " + location + "\n");

		//System.out.println("Moves Found:");
		if (team) { // White pawn, goes up

			if (x + 1 < 8 && y + 1 < 9) {

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
	
	public boolean canPromote() {
		if (this.rank == 5) {
			//BoardButton b;
			if (this.isWhite) {
				if (!bb[this.col][++this.row].isFull()) return true;
				else return false;
			}
			else {
				if (!bb[this.col][--this.row].isFull()) return true;
				else return false;
			}
		}
		return false;
	}
	
	/**
	 * @author Henry Rheault
	 * Helper Method for do-while in promote() method, returns false if invalid or pulls char out of the s.nextLine
	 * returns false if pawn or king entered, returns null as default
	 */
	
	private boolean isValid(Character c) {
		switch (Character.toUpperCase(c)) {
			case 'Q':
				return true;
			case 'N':
				return true;
			case 'R':	
				return true;
			case 'B':
				return true;
			default:	
				return false;
		}
	}
	
	/**
	 * @author Henry Rheault
	 * Handles promotions, if rank == 6 (after it moves to the end of the board) will handle dialog of what piece to construct in it's place 
	 * (Realistically only Queen, or Knight in the case of a super big brain gamer)
	 * Returns piece created to be added to the board
	 */
	public Piece promote() {
		Piece result = null;
		if (this.canPromote()) {
			String s;
			Character c = 'Z';
			System.out.println("Pawn reached end of board, type character of piece you'd like to promote it to:");
			do {
				c = scan.nextLine().charAt(0);
				if (isValid(c))
					break;
				else
					System.out.println("Invalid character. Try again:");
			} while (scan.hasNextLine() && !isValid(c));
			switch (c) { // Not toUpperCase, we're going to use the case to determine the team and deal
							// with it here
			case 'Q':
				this.player.removePiece(this);
				Queen promotedQueen = new Queen("promotedQueen", true, this.player.getBoard(), this.row, this.col);
				this.player.addPiece(promotedQueen);
				result = promotedQueen;
				break;
			case 'q':
				this.player.removePiece(this);
				Queen promotedqueen = new Queen("promotedQueen", false, this.player.getBoard(), this.row, this.col);
				this.player.addPiece(promotedqueen);
				result = promotedqueen;
				break;
			case 'N':
				this.player.removePiece(this);
				Knight promotedKnight = new Knight("promotedKnight", true, this.player.getBoard(), this.row, this.col);
				this.player.addPiece(promotedKnight);
				result = promotedKnight;
				break;
			case 'n':
				this.player.removePiece(this);
				Knight promotedknight = new Knight("promotedKnight", false, this.player.getBoard(), this.row, this.col);
				this.player.addPiece(promotedknight);
				result = promotedknight;
				break;
			case 'B':
				this.player.removePiece(this);
				Bishop promotedBishop = new Bishop("promotedBishop", true, this.player.getBoard(), this.row, this.col);
				this.player.addPiece(promotedBishop);
				result = promotedBishop;
				break;
			case 'b':
				this.player.removePiece(this);
				Bishop promotedbishop = new Bishop("promotedBishop", false, this.player.getBoard(), this.row, this.col);
				this.player.addPiece(promotedbishop);
				result = promotedbishop;
				break;
			case 'R':
				this.player.removePiece(this);
				Rook promotedRook = new Rook("promotedRook", true, this.player.getBoard(), this.row, this.col);
				this.player.addPiece(promotedRook);
				result = promotedRook;
				break;
			case 'r':
				this.player.removePiece(this);
				Rook promotedrook = new Rook("promotedrook", false, this.player.getBoard(), this.row, this.col);
				this.player.addPiece(promotedrook);
				result = promotedrook;
				break;
			}	//Do NOT close the scan, it's the main's scan as well!!!
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