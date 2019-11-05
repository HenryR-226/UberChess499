package gradleproject1;

import java.util.ArrayList;

/**
 * @author Henry Rheault
 */
public class Bishop extends Piece {
	boolean lightSquares; // True for light square bishop, false for dark square

	public Bishop(String moveID, boolean team, int row, int col) {
		String loc = new String();
		loc = Character.toString((char)row + 'A');
		loc = loc + Integer.toString(col+1);
		this.setLocation(loc);
		
		this.setName("Bishop");
		this.pieceID = moveID;
		this.setIsWhite(team);
		if (team)
			setAbbreviation('B');
		else
			setAbbreviation('b');
		this.points = 3;
	}

	// Bishop raw moves generator
	// Tested and verified accurate on Test Board: Henry Rheault, 11/3/2019
	@Override
	public ArrayList<BoardButton> getMoves(Piece piece, BoardButton[][] board) {
		// https://math.stackexchange.com/questions/1566115/formula-that-describes-the-movement-of-a-bishop-in-chess
		// Moving from x1, y1 to x2, y2 is a valid move if abs(x2-x1) = abs(y2 - y1) >
		// 0.
		// Too small brain. Did Rook-style move generation until obstacle instead.
		Bishop p = (Bishop) piece;
		ArrayList<BoardButton> validSquares = new ArrayList<BoardButton>(); // Return values

		String location = p.getLocation();
		char[] c = location.toCharArray();
		int x = ((int) c[0] - 'A');
		int y = (int) c[1] - '0' - 1;
		int ctrx = x;
		//System.out.println("This is x : " + ctrx);
		int ctry = y;
		BoardButton b = null;
		// if ((Math.abs(i - col)==(Math.abs(j - row))) && Math.abs(i - col)>0){
		boolean team = p.isWhite();
		//boolean enemyTeam = !p.isWhite();
		//boolean testBool = false;
		
		// Go in each of 4 diagonals.
		// Plus X, Plus Y:
		do {
			try {
				ctrx++;
				ctry++;
				b = board[ctrx][ctry];
				//System.out.println("Bishop ++ checking " + ctrx + " and " + ctry);
				//testBool = ((!b.isFull() || (b.getPiece().isWhite()!=team)) && ctry < 8 && ctrx < 8);
				if ((!b.isFull() || (b.getPiece().isWhite()!=team)) && ctry < 8 && ctrx < 8)
					validSquares.add(b);
				//if (!b.isFull() && crty<8 && ctrx)
				//System.out.println("Is " + b.getAbbreviation() + " full: " + b.isFull());
				//System.out.println("Or " + b.isWhite()!= p.isWhite() + ".");
				//System.out.println("Boolean result for " + b.getAbbreviation() + ": " + testBool);
			} catch (Exception e) {
				//break;
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
				//System.out.println("Bishop -+ checking " + ctrx + " and " + ctry);
				if ((!b.isFull() || (b.getPiece().isWhite()!=team)) && ctry < 8 && ctrx > -1)
					validSquares.add(b);
				//System.out.println("Is " + b.getAbbreviation() + " full: " + !b.isFull());
				//System.out.println("Or " + b.isWhite()!= p.isWhite() + ".");
			} catch (Exception e) {
				//break;
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
				//System.out.println("Bishop -- checking " + ctrx + " and " + ctry);
				if ((!b.isFull() || b.isWhite()!= team) && ctry > -1 && ctrx > -1)
					validSquares.add(b);
				//System.out.println("Is " + b.getAbbreviation() + " full: " + !b.isFull());
				//System.out.println("Or " + b.isWhite()!= p.isWhite() + ".");
			} catch (Exception e) {
				//break;
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
				//System.out.println("Bishop +- checking " + ctrx + " and " + ctry);
				if ((!b.isFull() || b.isWhite() != team) && ctry > -1 && ctrx < 8)
					validSquares.add(b);
				//System.out.println("Is " + b.getAbbreviation() + " full: " + !b.isFull());
				//System.out.println("Or " + b.isWhite()!= p.isWhite() + ".");
			} catch (Exception e) {
				//break;
			}
		} while (!b.isFull() && ctrx < 8 && ctry > -1);

		return validSquares;
	}

}
