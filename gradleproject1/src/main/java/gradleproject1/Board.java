package gradleproject1;

import java.util.ArrayList;

public class Board {

	private Player whitePlayer;
	private Player blackPlayer;

	public Board(Player whitePlayer, Player blackPlayer) {
		this.blackPlayer = blackPlayer;
		blackPlayer.setBoard(this);
		this.whitePlayer = whitePlayer;
		whitePlayer.setBoard(this);
	}

	private BoardButton[][] GameBoard = new BoardButton[8][8];

	private ArrayList<Move> possibleMoves;

	/**
	 * @Author Henry Rheault
	 *
	 *         MASTER MOVE METHOD. Calls getPieceList for a particular gamer to get
	 *         their list of pieces on the board. Then calls Piece.getMoves() to
	 *         return a semi-filtered list. of candidate moves. getMoves() in piece
	 *         calls each piece's individual submethods for specific move
	 *         generation, returns back to Piece.getMoves() to be returned to here.
	 *
	 *         This method then sifts down to make sure the move isn't illegal,
	 *         converts it as chess notation, and pushes it to array list as a
	 *         string. This processes all possible moves for a given team.
	 */
	public ArrayList<Move> getMoves(Player p) throws Exception {
		ArrayList<ArrayList<BoardButton>> moveSquareList = new ArrayList<ArrayList<BoardButton>>(); // List of Lists,
																									// one list for each
																									// piece's possible
																									// moves
		Move move; // Return string of given move
		for (Piece piece : p.getPieceList()) { // For each piece in player's list
			moveSquareList.add(piece.getMoves(piece, GameBoard)); // Add a list of possible board squares that piece can
																	// move to
			for (ArrayList<BoardButton> al : moveSquareList) { // For each list of boardbuttons in the movesquare list
				for (BoardButton b : al) { // For each boadbutton IN said list of boardbuttons
					move = new Move(piece, b); // Construct the move
					possibleMoves.add(move); // Post the move to final move list
				} // if statement for king not in check)
			} // This will NOT report a space immediately forward of the pawn occupied by
				// enemy piece as valid move.
		} // This will be tested/weeded out in the Pawn specific candidate generation
			// moves to keep this clean.

		return possibleMoves; // Return the final move list. AI selects from this randomly and potential move
								// to be made MUST BE in here
	}

	/**
	 * @author Henry Rheault Returns specific BoardButton given the arguments of X &
	 *         Y coordinates.
	 */
	public BoardButton getBoardButton(int x, int y) {
		try {
			BoardButton result = GameBoard[x][y];
			return result;
		} catch (Exception e) {
			System.out.println("Getting board button failed. Probably out of bounds so no such button.");
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * Same as above but in chess notation. Don't know which one will be more
	 * convenient or used more.
	 */
	public BoardButton getBoardButton(char row, char col) {
		try {
			int x = ((int) row - 'A');
			int y = (int) col;
			BoardButton result = GameBoard[x][y];
			return result;
		} catch (Exception e) {
			System.out.println("Getting board button failed. Probably passed bad Chess notation input.");
			e.printStackTrace();
		} finally {
			return null;
		}
	}

	/**
	 * @author Henry Rheault Method to take in string of piece location and
	 *         natively/abstractly convert to array memory location.
	 * 
	 *         Needs to be fixed as of 10/27 as the board layout has been altered.
	 * 
	 * @deprecated
	 */

	// public BoardButton toArray(String s){
	/*
	 * BoardButton b; int x=-1; int y=-1; try{ assert (s.length() == 2); char[] temp
	 * = s.toCharArray();
	 * 
	 * //Take string, convert to chars, and get Memory Array location from the Chess
	 * String location char col = temp[0]; //A-H, not case sensitive, input argument
	 * char r0w = temp[1]; //1-8, input argument col = Character.toUpperCase(col);
	 * //Ensures that the column character is upper case for ease of assert if
	 * (!(Character.isLetter(col) || col>'H')) assert (Character.isLetter(col) &&
	 * col<='H'); //Set user error flag and break out of try if not else x = col -
	 * 'A';
	 * 
	 * 
	 * if (r0w >8 || r0w<0) assert (r0w <=8 && r0w>0); //Set user error flag and
	 * break out of try if not else y = 8-r0w; b = GameBoard[x][y]; return b; }
	 * catch (Exception e) { System.out.
	 * println("You should probably not call publically available methods with random input."
	 * ); e.printStackTrace(); } finally { return null; }
	 */

	// }

	/**
	 * @author Henry Rheault
	 * 
	 *         Updated 10/29/2019 and not tested- Added feature to assign board
	 *         abbreviations within init board method. So it goes to each board
	 *         square and tells it 'you are A1/E4', etc. Calls an overloaded method
	 *         that processes the int input and then itself calls the setter.
	 * @throws Exception
	 * 
	 *                   FIXME : Row E and all Knights are not being initialized!!
	 */

	public void initBoard() throws Exception {
		boolean white = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 1; j < 9; j++) {
				BoardButton butn = new BoardButton(i, j);
				butn.setColor(white);
				// ABBREVIATION IS BEING PASSED ASCII VALUES!! NEEDS FIX!
				butn.setAbbreviation(i, j);
				GameBoard[i][j - 1] = butn;
				white = !white; // Flip color

			}
			white = !white;
		}
		initWhite();
		initBlack();

	}

	public void initWhite() {
		Pawn Pawn1 = new Pawn("Pawn1", true, 0, 1);
		Pawn Pawn2 = new Pawn("Pawn2", true, 1, 1);
		Pawn Pawn3 = new Pawn("Pawn3", true, 2, 1);
		Pawn Pawn4 = new Pawn("Pawn4", true, 3, 1);
		Pawn Pawn5 = new Pawn("Pawn5", true, 4, 1);
		Pawn Pawn6 = new Pawn("Pawn6", true, 5, 1);
		Pawn Pawn7 = new Pawn("Pawn7", true, 6, 1);
		Pawn Pawn8 = new Pawn("Pawn8", true, 7, 1);
		// Manually add pieces to piece list. Should be done in piece constructor but
		// w/e
		whitePlayer.addPiece(Pawn1);
		whitePlayer.addPiece(Pawn2);
		whitePlayer.addPiece(Pawn3);
		whitePlayer.addPiece(Pawn4);
		whitePlayer.addPiece(Pawn5);
		whitePlayer.addPiece(Pawn6);
		whitePlayer.addPiece(Pawn7);
		whitePlayer.addPiece(Pawn8);

		Piece Rook1 = new Rook("Rook1", true, 0, 0);
		Piece Rook2 = new Rook("Rook2", true, 0, 7);
		whitePlayer.addPiece(Rook1);
		whitePlayer.addPiece(Rook2);
		Piece BishopLight = new Bishop("BishopLight", true, 0, 2);
		Piece BishopDark = new Bishop("BishopDark", true, 0, 5);
		whitePlayer.addPiece(BishopLight);
		whitePlayer.addPiece(BishopDark);
		Piece Knight1 = new Knight("Knight1", true, 0, 1);
		Piece Knight2 = new Knight("Knight2", true, 0, 6);
		whitePlayer.addPiece(Knight1);
		whitePlayer.addPiece(Knight2);
		Piece Queen = new Queen("Queen", true, 0, 3);
		whitePlayer.addPiece(Queen);
		Piece Kang = new King("Kang", true, 0, 4);
		whitePlayer.addPiece(Kang);
		BoardButton a;

		// Manually placing and declaring to each piece their locations

		GameBoard[Pawn1.getRow()][Pawn1.getCol()].setPiece(Pawn1);
		Pawn1.setLocation("A2");

		GameBoard[Pawn2.getRow()][Pawn2.getCol()].setPiece(Pawn2);
		Pawn2.setLocation("B2");

		GameBoard[Pawn3.getRow()][Pawn3.getCol()].setPiece(Pawn3);
		Pawn3.setLocation("C2");

		GameBoard[Pawn4.getRow()][Pawn4.getCol()].setPiece(Pawn4);
		Pawn4.setLocation("D2");

		GameBoard[Pawn5.getRow()][Pawn5.getCol()].setPiece(Pawn5);
		Pawn5.setLocation("E2");

		GameBoard[Pawn6.getRow()][Pawn6.getCol()].setPiece(Pawn6);
		Pawn6.setLocation("F2");

		GameBoard[Pawn7.getRow()][Pawn7.getCol()].setPiece(Pawn7);
		Pawn7.setLocation("G2");

		GameBoard[Pawn8.getRow()][Pawn8.getCol()].setPiece(Pawn8);
		Pawn8.setLocation("H2");

		a = GameBoard[0][0];
		a.setPiece(Rook1);
		Rook1.setLocation("A1");
		a = GameBoard[7][0];
		a.setPiece(Rook2);
		Rook2.setLocation("H1");
		a = GameBoard[1][0];
		a.setPiece(Knight1);
		Knight1.setLocation("B1");
		a = GameBoard[6][0];
		a.setPiece(Knight2);
		Knight2.setLocation("G1");
		a = GameBoard[2][0];
		a.setPiece(BishopLight);
		BishopLight.setLocation("C1");
		a = GameBoard[5][0];
		a.setPiece(BishopDark);
		BishopDark.setLocation("F1");
		a = GameBoard[4][0];
		a.setPiece(Kang);
		Kang.setLocation("E1");
		a = GameBoard[3][0];
		a.setPiece(Queen);
		Queen.setLocation("D1");
	}

	public void initBlack() {
		Piece pawn1 = new Pawn("pawn1", false, 0, 6);
		Piece pawn2 = new Pawn("pawn2", false, 1, 6);
		Piece pawn3 = new Pawn("pawn3", false, 2, 6);
		Piece pawn4 = new Pawn("pawn4", false, 3, 6);
		Piece pawn5 = new Pawn("pawn5", false, 4, 6);
		Piece pawn6 = new Pawn("pawn6", false, 5, 6);
		Piece pawn7 = new Pawn("pawn7", false, 6, 6);
		Piece pawn8 = new Pawn("pawn8", false, 7, 6);
		blackPlayer.addPiece(pawn1);
		blackPlayer.addPiece(pawn2);
		blackPlayer.addPiece(pawn3);
		blackPlayer.addPiece(pawn4);
		blackPlayer.addPiece(pawn5);
		blackPlayer.addPiece(pawn6);
		blackPlayer.addPiece(pawn7);
		blackPlayer.addPiece(pawn8);

		Piece rook1 = new Rook("rook1", false, 7, 0);
		Piece rook2 = new Rook("rook2", false, 7, 7);
		blackPlayer.addPiece(rook1);
		blackPlayer.addPiece(rook2);
		Piece bishopLight = new Bishop("bishopLight", false, 7, 2);
		Piece bishopDark = new Bishop("bishopDark", false, 7, 5);
		blackPlayer.addPiece(bishopLight);
		blackPlayer.addPiece(bishopDark);
		Piece knight1 = new Knight("knight1", false, 7, 1);
		Piece knight2 = new Knight("knight2", false, 7, 6);
		blackPlayer.addPiece(knight1);
		blackPlayer.addPiece(knight2);
		Piece queen = new Queen("queen", false, 7, 3);
		blackPlayer.addPiece(queen);
		Piece kang = new King("kang", false, 7, 4);
		blackPlayer.addPiece(kang);

		BoardButton a;

		a = GameBoard[0][6];
		a.setPiece(pawn1);
		pawn1.setLocation("A7");
		a = GameBoard[1][6];
		a.setPiece(pawn2);
		a = GameBoard[2][6];
		pawn2.setLocation("B7");
		a.setPiece(pawn3);
		a = GameBoard[3][6];
		pawn3.setLocation("C7");
		a.setPiece(pawn4);
		a = GameBoard[4][6];
		pawn4.setLocation("D7");
		a.setPiece(pawn5);
		a = GameBoard[5][6];
		pawn5.setLocation("E7");
		a.setPiece(pawn6);
		a = GameBoard[6][6];
		pawn6.setLocation("F7");
		a.setPiece(pawn7);
		a = GameBoard[7][6];
		pawn7.setLocation("G7");
		a.setPiece(pawn8);
		pawn8.setLocation("H7");

		a = GameBoard[0][7];
		a.setPiece(rook1);
		rook1.setLocation("A8");
		a = GameBoard[7][7];
		a.setPiece(rook2);
		rook2.setLocation("H8");
		a = GameBoard[1][7];
		a.setPiece(knight1);
		knight1.setLocation("B8");
		a = GameBoard[6][7];
		a.setPiece(knight2);
		knight2.setLocation("G8");
		a = GameBoard[5][7];
		a.setPiece(bishopDark);
		bishopDark.setLocation("F8");
		a = GameBoard[2][7];
		a.setPiece(bishopLight);
		bishopLight.setLocation("C8");
		a = GameBoard[4][7];
		a.setPiece(kang);
		kang.setLocation("E8");
		a = GameBoard[3][7];
		a.setPiece(queen);
		queen.setLocation("D8");

	}

	// Draws out Ascii art of the gameboard. To be called after every successfully
	// committed move.
	// MASSIVE overhaul/Bugfix on the part of Ryan and Henry on 10/27/2019
	public void draw(Board b) {
		BoardButton butn = null;
		byte rowOffset = 8;
		char c;
		System.out.println("   A B C D E F G H");
		for (int j = 7; j >= 0; j--) {
			System.out.print((rowOffset) + " ["); // Rows starting from 8
			for (int i = 0; i <= 7; i++) {
				butn = GameBoard[i][j];
				if (butn.isFull()) {
					c = (char) butn.getPiece().getAbbrev();
					System.out.print(c + ",");
				} else if (butn.isWhite()) {
					System.out.print("-,");
				} else {
					System.out.print("+,");
				}

			}
			System.out.println("] " + (rowOffset));
			rowOffset--;
		}
		System.out.println("   A B C D E F G H"); // Letter Grid
	}

	public BoardButton[][] getGameBoard() {
		return GameBoard;
	}

	// Test board initializations. Used for generating a board with a single piece
	// on it in the center of the board to test move rules.
	public void initBoardQueenTest() throws Exception {
		boolean white = false;
		System.out.println("Queen Test init called:");
		for (int i = 0; i < 8; i++) {
			for (int j = 1; j < 9; j++) {
				BoardButton butn = new BoardButton(i, j);
				butn.setColor(white);
				// ABBREVIATION IS BEING PASSED ASCII VALUES!! NEEDS FIX!
				butn.setAbbreviation(i, j);
				GameBoard[i][j - 1] = butn;
				white = !white; // Flip color

			}
			white = !white;
		}
		Piece Queen = new Queen("Queen", true, 0, 0);
		whitePlayer.addPiece(Queen);
		BoardButton a = GameBoard[3][3];
		Queen.setLocation("D4");
		a.setPiece(Queen);
		System.out.println(a.getPiece().getAbbrev());
		System.out.println(a.getPiece().getLocation());

	}

	public void initBoardKnightTest() throws Exception {
		boolean white = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 1; j < 9; j++) {
				BoardButton butn = new BoardButton(i, j);
				butn.setColor(white);
				// ABBREVIATION IS BEING PASSED ASCII VALUES!! NEEDS FIX!
				butn.setAbbreviation(i, j);
				GameBoard[i][j - 1] = butn;
				white = !white; // Flip color

			}
			white = !white;
		}
		Piece Knight = new Knight("Knight", true, 0, 0);
		whitePlayer.addPiece(Knight);
		BoardButton a = GameBoard[3][3];
		a.setPiece(Knight);
		Knight.setLocation("D4");
	}

	public void initBoardKingTest() throws Exception {
		boolean white = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 1; j < 9; j++) {
				BoardButton butn = new BoardButton(i, j);
				butn.setColor(white);
				// ABBREVIATION IS BEING PASSED ASCII VALUES!! NEEDS FIX!
				butn.setAbbreviation(i, j);
				GameBoard[i][j - 1] = butn;
				white = !white; // Flip color

			}
			white = !white;
		}
		Piece King = new King("King", true, 0, 0);
		whitePlayer.addPiece(King);
		BoardButton a = GameBoard[3][3];
		a.setPiece(King);
		King.setLocation("D4");
	}

	public void initBoardRookTest() throws Exception {
		boolean white = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 1; j < 9; j++) {
				BoardButton butn = new BoardButton(i, j);
				butn.setColor(white);
				// ABBREVIATION IS BEING PASSED ASCII VALUES!! NEEDS FIX!
				butn.setAbbreviation(i, j);
				GameBoard[i][j - 1] = butn;
				white = !white; // Flip color

			}
			white = !white;
		}
		Piece Rook = new Rook("Rook", true, 3, 3);
		whitePlayer.addPiece(Rook);
		BoardButton a = GameBoard[3][3];
		a.setPiece(Rook);
		Rook.setLocation("D4");
	}

	public void initBoardBishopTest() throws Exception {
		boolean white = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 1; j < 9; j++) {
				BoardButton butn = new BoardButton(i, j);
				butn.setColor(white);
				// ABBREVIATION IS BEING PASSED ASCII VALUES!! NEEDS FIX!
				butn.setAbbreviation(i, j);
				GameBoard[i][j - 1] = butn;
				white = !white; // Flip color

			}
			white = !white;
		}
		Piece Bishop = new Bishop("Bishop", true, 3, 3);
		whitePlayer.addPiece(Bishop);
		BoardButton a = GameBoard[3][3];
		a.setPiece(Bishop);
		Bishop.setLocation("D4");
	}

	public Player getWhitePlayer() {
		return whitePlayer;
	}

	public Player getBlackPlayer() {
		return blackPlayer;
	}

}