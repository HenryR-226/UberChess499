package gradleproject1;

/**
 *
 * @author Henry Rheault
 * 
 *         Move started as a specific type of string, eg, nE4, KG7. It has
 *         changed to store the old and new board squares it was involving, it's
 *         notation, and the piece that moved.
 */
public class Move {

	private BoardButton old;
	private int oldrow, oldcol;
	private BoardButton n3w;
	private int newrow, newcol;
	
	private String abbreviation;
	private Piece piece;
	private Piece captured = null;
	private boolean isWhite;
	private boolean capture = false;

	private static Player whitePlayer;
	private static Player blackPlayer;

	private static Board b;
	
	private static int movesWithoutCapture = 0;
	
	/**
	 * @author Henry Rheault
	 *
	 *         Commits a move to a gamer's move list. Assumes move is valid when
	 *         being called. Try-Catch for good measure and debugging ease to see if
	 *         the error is within Move or elsewhere.
	 * 
	 *         Adds a move to a particular gamer's move list for demo purposes.
	 *         
	 *         @deprecated
	 */
	public Move(Piece p, char row, char column) throws Exception {
		try {

			BoardButton[][] GameBoard = b.getGameBoard(); // Fetch gameboard object

			String s = p.getLocation();
			char loc[] = s.toCharArray();
			int i = (int) loc[0]; // Number column
			int j = (int) loc[1];

			// 10/27/19: Update and make sure with new grid layout this reports/converts the
			// correct grid notations!
			// A1 - 0,0; H8 - 7,7, etc.
			int m = (int) row;
			int n = (int) column;
			this.old = GameBoard[i][j];
			this.n3w = GameBoard[m][n];
			this.piece = p;

			System.out.println(p.getAbbrev() + " moved from " + loc[0] + loc[1] + " to " + row + column + ".");
			setAbbreviation(String.valueOf(p.getAbbrev()) + String.valueOf(row) + String.valueOf(column));

			String move = String.valueOf(p.getAbbrev());
			if (n3w.getPiece() != null) {
				move = move + "x"; // x means a piece captured the piece on it's destination square
				this.capture = true;
				this.captured = n3w.getPiece();
			}
			move = move + row + column;
			if (p.isWhite())
				whitePlayer.addMove(this);
			else
				blackPlayer.addMove(this);

			String location = this.n3w.getAbbreviation();
			p.setLocation(location); 
			this.getPiece().incRank();
			this.getPiece().madeFirstMove();
			
		} catch (Exception e) {
			System.out.println("Invalid move constructor. Try again.");
		}
	}
	
	public Move(Piece p, BoardButton button, boolean thisIsAMoveGeneratorOnlyWillNotPushAMove) throws Exception { // Overloaded																											// boardbutton
		// to attempt a move to
		try {
			BoardButton[][] GameBoard = b.getGameBoard(); // Fetch gameboard object
			String s = p.getLocation();
			char loc[] = s.toCharArray();
			int i = (int) loc[0] - 'A'; // Number column
			int j = (int) loc[1] - '0' - 1;

			this.old = GameBoard[i][j];
			//System.out.println("Calling remove on boardbutton" + i + " " + j);
			this.n3w = button;
			this.piece = p;
			String abbrev = n3w.getAbbreviation();
			//System.out.println("Line 92 in Move, " + p.getAbbrev() + " moved from " + loc[0] + loc[1] + " to " + abbrev + ".");

			String move = String.valueOf(p.getAbbrev());
			if (n3w.getPiece() != null) {
				move = move + "x"; // x means a piece captured the piece on it's destination square
				capture = true;
				this.captured = n3w.getPiece();
			} 
			move = move + abbrev;
			setAbbreviation(move);

		} catch (Exception e) {
			System.out.println("Invalid move constructor taking board square. Try again.");
			e.printStackTrace();
		}
	}

	
	public Move(Piece p, BoardButton button) throws Exception { // Overloaded constructor, simply declare a boardbutton
																// to attempt a move to
		try {
			BoardButton[][] GameBoard = b.getGameBoard(); // Fetch gameboard object

			String s = p.getLocation();
			char loc[] = s.toCharArray();
			int i = (int) loc[0] - 'A'; // Number column
			int j = (int) loc[1] - '0' - 1;

			this.old = GameBoard[i][j];
			//System.out.println("Calling remove on boardbutton" + i + " " + j);
			this.n3w = button;
			this.piece = p;
			String abbrev = n3w.getAbbreviation();
			System.out.println(p.getAbbrev() + " moved from " + loc[0] + loc[1] + " to " + abbrev + ".");

			String move = String.valueOf(p.getAbbrev());
			if (n3w.getPiece()!=null && n3w.getPiece().isWhite()!=piece.isWhite()) {
				move = move + "x"; // x means a piece captured the piece on it's destination square
				capture = true;
				this.captured = n3w.getPiece();
				movesWithoutCapture = 0;
				n3w.removePiece(piece);
			}
			else old.removePiece();
			move = move + abbrev;
			setAbbreviation(move);

			if (p.isWhite()) {				
				//Check move implementations, needs to be tested. 11/29/2019				
				if (whitePlayer.inCheck()) 		//If we're in check, make sure this takes us out of check
					if (!whitePlayer.getKing().isInCheck(GameBoard, whitePlayer.getPieceList(), this.piece, this)) whitePlayer.removeCheck();
					//else return null because you can't push this move
				whitePlayer.addMove(this);
			} else {
				if (blackPlayer.inCheck()) 
					if (!blackPlayer.getKing().isInCheck(GameBoard, whitePlayer.getPieceList(), this.piece, this)) blackPlayer.removeCheck();
					//else return null because you can't push this move
				blackPlayer.addMove(this);
			}
			String location = this.n3w.getAbbreviation();
			//Update pawn first move field
			if (this.piece.getAbbrev() == 'p' || this.piece.getAbbrev()=='P') {
				if (this.piece.firstMove()) {
					piece.madeFirstMove();
					
					if (Math.abs(this.n3w.getRow() - this.old.getRow()) == 2) this.getPiece().incRank();
				}
				if (this.piece.getRank()==5 && ((Pawn) this.piece).canPromote()) { 
					try {
						((Pawn)this.piece).promote();
					} catch (Exception e) {
						
					}
				
				}
				this.getPiece().incRank();
				this.getPiece().madeFirstMove();
			}
			
			n3w.setPiece(p);
			p.setLocation(location);
			if (p.getAbbrev()=='p' || p.getAbbrev()=='P') movesWithoutCapture = 0;
			
			//Stalemate!!
			if (movesWithoutCapture == 50 || (b.getBlackPlayer().getPieceList().size()==1 && b.getWhitePlayer().getPieceList().size()==1)) {
				System.out.println("50 move or King-only stalemate! Game's over. Line 171 Move");
				Player.getGameState().setWinrar(null);
				Player.getGameState().setStalemate();
			}

		} catch (Exception e) {
			System.out.println("Invalid move constructor taking board square. Try again.");
			e.printStackTrace();
		}
	}
	/**
	 * Move constructor for pawn promotion only, straight up column
	 */
	public Move (Pawn p) {
		assert (p.getRow() == 6 || p.getRow() == 1);
		BoardButton[][] GameBoard = b.getGameBoard(); // Fetch gameboard object

		String s = p.getLocation();
		char loc[] = s.toCharArray();
		int i = (int) loc[0] - 'A'; // Number column
		int j = (int) loc[1] - '0' - 1;

		this.old = GameBoard[i][j];
		//System.out.println("Calling remove on boardbutton" + i + " " + j);
		if (p.isWhite) this.n3w = GameBoard[p.getCol()][7];
		if (!p.isWhite) this.n3w = GameBoard[p.getCol()][0];
		
		//this.piece = p;
		String abbrev = n3w.getAbbreviation();
		String move = String.valueOf(p.getAbbrev());
		movesWithoutCapture = 0;
		move = move + abbrev;
		setAbbreviation(move);
		
		Piece promoted = p.promote(); 
		
		old.removePiece();
		n3w.setPiece(promoted);
		
		this.piece = promoted;
		promoted.setLocation(abbrev);
	}
	
	//Overloaded, boolean true = steps RIGHT, false = steps LEFT
	public Move (Pawn p, boolean side) {
		assert (p.getRow() == 6 || p.getRow() == 1);
		BoardButton[][] GameBoard = b.getGameBoard(); // Fetch gameboard object

		String s = p.getLocation();
		char loc[] = s.toCharArray();
		int i = (int) loc[0] - 'A'; // Number column
		int j = (int) loc[1] - '0' - 1;

		this.old = GameBoard[i][j];
		//System.out.println("Calling remove on boardbutton" + i + " " + j);
		if (p.isWhite) {
			if (side) this.n3w = GameBoard[p.getCol()+1][7];
			else this.n3w = GameBoard[p.getCol()-1][7];
		}
		if (!p.isWhite) {
			if (side) this.n3w = GameBoard[p.getCol()+1][0];
			else this.n3w = GameBoard[p.getCol()-1][0];
		}
		
		//this.piece = p;
		String abbrev = n3w.getAbbreviation();
		String move = String.valueOf(p.getAbbrev());
		String x = "x";
		movesWithoutCapture = 0;
		move = move + x + abbrev;
		if (n3w.isFull()) captured = n3w.getPiece();
		setAbbreviation(move);
		
		Piece promoted = p.promote(); 
		
		old.removePiece();
		n3w.removePiece(promoted);
		n3w.setPiece(promoted);
		
		this.piece = promoted;
		promoted.setLocation(abbrev);
	}
	
	//Creates a castle move
	public Move(King k, Rook r) {
		
	}
	
	/** @author Henry Rheault
	 * Generates and returns a move based on a input string. 
	 * Passed in 
	 * @throws Exception 
	 */
	public Move newMove(Piece p, BoardButton button) throws Exception {
		Move m = new Move(p, button);
		return m;
	}

	// Get the boardbutton the move was FROM
	public BoardButton getOld() {
		return this.old;
	}

	// Get the boardbutton the move was TO
	public BoardButton getNew() {
		return this.n3w;
	}

	// Return string/algebraic notation
	public String getAbbreviation() {
		return this.abbreviation;
	}

	// To be updated: Make it take in args of char and do the operations/checks as
	// done in the Move constructors above
	// To be equivalent to proper Object Oriented form. IE: "no funky data"
	private void setAbbreviation(String s) {
		this.abbreviation = s;
	}

	public static void setGameBoard(Board board) {
		b = board;
	}

	public static void setWhitePlayer(Player whitey) {
		whitePlayer = whitey;
	}

	public static void setBlackPlayer(Player black) {
		blackPlayer = black;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public Piece getCaptured() {
		return this.captured;
	}
	
	public boolean wasCaptured() {
		return capture;
	}

}
