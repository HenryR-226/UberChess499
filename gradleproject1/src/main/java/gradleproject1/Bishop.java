package gradleproject1;
import java.util.ArrayList;
/**
 * @author Henry Rheault
 */
public class Bishop extends Piece {
	boolean lightSquares; // True for light square bishop, false for dark square

	static boolean first = true;
	public Bishop(String moveID, boolean team, Board b, int row, int col) {
		String loc = new String();
		loc = Character.toString((char) (row + 'A'));
		loc = loc + Integer.toString(col + 1);
		this.setLocation(loc);

		this.setName("Bishop");
		this.pieceID = moveID;
		this.setIsWhite(team);
		if (team) {
			setAbbreviation('B');
			this.player = b.getWhitePlayer(); 
		}
		else {
			setAbbreviation('b');
			this.player = b.getBlackPlayer();
		}
		this.bb = b.getGameBoard();
		this.points = 3;
		if (first==true){
			this.alpha=true;
			first=false;
		} else {
			this.alpha=false;
		}
	}

	// Bishop raw moves generator
	// Tested and verified accurate on Test Board: Henry Rheault, 11/3/2019
	@Override
	public ArrayList<BoardButton> getMoves(Piece piece, BoardButton[][] board) {
		// Too small brain. Did Rook-style move generation until obstacle instead.
		Bishop p = (Bishop) piece;
		ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>(); // Return values

		String location = p.getLocation();
		char[] c = location.toCharArray();
		int x = ((int) c[0] - 'A');
		int y = (int) c[1] - '0' - 1;
		int ctrx = x;
		int ctry = y;
		BoardButton b = null;
		boolean team = p.isWhite();

		// Go in each of 4 diagonals.
		// Plus X, Plus Y:
		do {
			try {
				ctrx++;
				ctry++;
				b = board[ctrx][ctry];
				if ((!b.isFull() || (b.getPiece().isWhite() != team)) && ctry < 8 && ctrx < 8)
					validSquares.add(b);
			} catch (Exception e) {
				break;
			}
		} while (!b.isFull() && ctrx < 8 && ctry < 8);

		// Minus X, Plus Y
		ctrx = x;
		ctry = y;
		do {

			try {
				ctrx--;
				ctry++;
				b = board[ctrx][ctry];
				if ((!b.isFull() || (b.getPiece().isWhite() != team)) && ctry < 8 && ctrx > -1)
					validSquares.add(b);
			} catch (Exception e) {
				break;
			}

		} while (!b.isFull() && ctrx > -1 && ctry < 8);

		// Minus X, Minus Y:
		ctrx = x;
		ctry = y;
		do {

			try {
				ctrx--;
				ctry--;
				b = board[ctrx][ctry];
				if ((!b.isFull() || b.getPiece().isWhite() != team) && ctry > -1 && ctrx > -1) {

					validSquares.add(b);
				}
			} catch (Exception e) {
				break;
			}
		} while (!b.isFull() && ctrx > -1 && ctry > -1);

		// Plus X, Minus Y:
		ctrx = x;
		ctry = y;
		do {

			try {
				ctrx++;
				ctry--;
				b = board[ctrx][ctry];
				if ((!b.isFull() || b.getPiece().isWhite() != team) && ctry > -1 && ctrx < 8)
					validSquares.add(b);
			} catch (Exception e) {
				break;
			}
		} while (!b.isFull() && ctrx < 8 && ctry > -1);

		return validSquares;
	}

}
