package gradleproject1;

import java.util.ArrayList;

public class Rook extends Piece {
	public Rook(String moveID, boolean team, Board b, int row, int col) {
		this.setName("Rook");
		this.pieceID = moveID;
		String loc = Character.toString((char) (row + 'A'));
		// System.out.println("Loc: "+ loc);
		loc = loc + Integer.toString(col + 1);
		this.setLocation(loc);
		this.setIsWhite(team);
		if (team) {
			this.setAbbreviation('R');
			this.player = b.getWhitePlayer();
		}
		else {
			this.setAbbreviation('r');
			this.player = b.getBlackPlayer();
		}
		this.bb = b.getGameBoard();
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
	
	/**
	 * @author Henry Rheault
	 * 
	 * Castling method for Rook. When called on a rook object will check for the ability to castle for the team and if so, return true
	 * In this instance, add the King's  location to possible moves for the rook.
	 * Has a complement method in King, I didn't deem this heavyweight enough to make Castling an Interface (given it's at most twice a game)
	 */
	
	public boolean castle() {
		boolean result = false; BoardButton iterable = null;
		King kang = this.player.getKing();
		if (!firstMove || !kang.firstMove()) return result; 				//Not able to castle so break out with null
		else {
			int kingRow = kang.getRow(); int kingCol = kang.getCol(); int myCol = this.getCol();
			assert (kingRow == this.getRow()): "Rook and Kings rows are supposedly unequal but firstMove flags not set! Line 94 in Rook";
			int colDiff = kingCol - myCol; 		//Checking for positive or negative to determine which side we're on
					//If positive, this rook is on the LEFT of the king. If neg, it's on the RIGHT
			if(colDiff > 0) {
				//Check LEFT
				for (int i = kingCol; i>0; i--) {
					iterable = bb[myCol][i];
					if (iterable.isFull()) return false;					//Return null
				}
				return true;
			}
			else if (colDiff < 0) {
				//Check RIGHT
				for (int i = kingCol; i<7; i++) {
					iterable = bb[myCol][i];
					if (iterable.isFull()) return false;	
				}
				return true;
			}
			
		}
		return false;
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