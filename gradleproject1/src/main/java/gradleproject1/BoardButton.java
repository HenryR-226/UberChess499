package gradleproject1;

import java.util.ArrayList;

public class BoardButton {
	GameState g;
	private int row;
	private int column;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	private Piece piece;
	private String abbreviation;
	private boolean isWhite; // Is the square dark or light
	private boolean isFull; // Is something there
	private boolean highlighted = false; // Should I be highlighted right now?

	public void setGameState(GameState game) {
		this.g = game;
	}

	public BoardButton() {
	}

	private boolean isClicked; // is specific square clicked
	static private boolean somethingClicked; // is anything on the board clicked, can't be static

	// Gameboard

	static private char c;
	static private short rowOffset = 1; // idk why I put these here as globals since I only need it in draw()

	public Piece getPiece() throws NullPointerException {
		try {
			return piece;
		} catch (NullPointerException npe) {
			return null;
		}
	}

	// Tested and works A: 11/1/2019
	// Converts STRING notation to ARRAY notation (col/row).
	public static ArrayList<Integer> toArray(String s) {
		ArrayList<Integer> reeturn = new ArrayList<Integer>();
		char[] c = s.toCharArray();
		int x = ((int) c[0] - 65);
		reeturn.add(x);
		int y = (int) c[1] - 48;
		reeturn.add(y);
		return reeturn;
	}

	// Tested and works A : 11/1/2019
	// Converts ARRAY notation to STRING notation
	public static String toChess(ArrayList<Integer> xy) {
		try {
			assert (xy.size() == 2);
			String s = new String();
			int x = xy.get(0);
			int y = xy.get(1);
			x = (char) x + 65;
			s = s + String.valueOf(x);
			y = (char) y + 49;
			s = s + String.valueOf(y);
			return s;
		} catch (Exception e) {
			System.out.println("Input argument not correct length, or you did something more catastrophic.");
			e.printStackTrace();
			return null;
		}
	}

	// Assumes check has been run for empty square
	public void setPiece(Piece p) {
		try {
			if (this.isFull) {
				assert (this.piece.isWhite() != p.isWhite());
			}
		} catch (Exception e) {
			System.out.println("Illegal move created, friendly fire!");
			e.printStackTrace();
		}

		this.isFull = true;
		this.piece = p;
	}

	// Piece moves off voluntarily
	public void removePiece() {
		this.isFull = false;
		this.piece = null;
		System.out.println("Piece removed and set to null, line 106 BB");
		System.out.println(abbreviation + this.isFull);
	}

	/**
	 * @author Henry Rheault
	 * 
	 *         Method for a piece being captured, takes argument of a piece doing
	 *         the capturing. Adds the piece to a player's lost pieces list and
	 *         removes from their active piece list. Then rewrites the piece on the
	 *         given BB as the argument piece.
	 */
	public void removePiece(Piece p) {
		Player player;
		boolean team = p.isWhite();
		if (team)
			player = GameState.getBlack(); // If p is white -> black lost piece
		else
			player = GameState.getWhite(); // If p is black -> white lost piece
		player.pieceCaptured(this.piece);
		this.piece = p;
		this.isFull = true;
	}

	public boolean isFull() {
		return this.isFull;
	}

	/**
	 * @author Henry Rheault
	 * 
	 *         Method for assigning the BoardButton a chessboard abbreviation that
	 *         it can simply report back. Verifies format with code cloned from
	 *         GridOffset. If not upper case when passed in it is converted to be
	 *         such. MUST BE UPPERCASE as arithmetic checks are being run on it
	 *         compared to 'A' and 'H' both upper case.
	 */
	public void setAbbreviation(String s) {
		this.abbreviation = s;
	}

	// FIX ME
	// https://pastebin.com/N71TiVif
	public void setAbbreviation(int col, int row) throws Exception {
		boolean colFlag = false;
		boolean rowFlag = false;
		try {
			String stringAbrv = "Z";

			switch (col) {
			case (0):
				stringAbrv = "A" + (row);
				break;
			case (1):
				stringAbrv = "B" + (row);
				break;
			case (2):
				stringAbrv = "C" + (row);
				break;
			case (3):
				stringAbrv = "D" + (row);
				break;
			case (4):
				stringAbrv = "E" + (row);
				break;
			case (5):
				stringAbrv = "F" + (row);
				break;
			case (6):
				stringAbrv = "G" + (row);
				break;
			case (7):
				stringAbrv = "H" + (row);
				break;
			default:
				System.out.println("Yo dingus invalid column, 0 - 7 only");
				System.out.println("You passed: " + col);
				colFlag = true;
			}

			if (rowFlag || colFlag)
				throw new Exception();

			setAbbreviation(stringAbrv);

		} catch (Exception e) {
			System.out.println("Invalid col & row passed.");
			System.out.println("Input : " + col + " " + row);
			e.printStackTrace();
		}
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public void clicked() {

	}

	public BoardButton(int column, int row) {
		this.row = row;
		this.column = column;
	}

	public void setColor(boolean c) {
		this.isWhite = c;
	}

	public boolean isWhite() {
		return this.isWhite;
	}

	// Testable Main, mostly to check println offsets.
	public static void Main(String[] args) {
	}

}