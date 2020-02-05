package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import gradleproject1.Board;
import gradleproject1.BoardButton;
import gradleproject1.GameState;
import gradleproject1.King;
import gradleproject1.Move;
import gradleproject1.Piece;
import gradleproject1.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class ChessGameAIController implements Initializable {

	private boolean firstClick = false;
	private ImageView firstClickSpot;
	private boolean secondClick = false;
	private Node secondClickSpot;
	private BoardButton[][] GameBoard;
	private GameState g;
	private static Board b;
	private ArrayList<BoardButton> moves;
	private Piece test = null;
	private boolean madeMove = false;
	private boolean currentPlayer;
	private boolean capturePiece;
	private ArrayList<ImageView> blackPieces;
	private ArrayList<ImageView> whitePieces;
	private boolean capture = false;
	BoardButton a = null;

	@FXML
	ImageView pawn_black1, pawn_black2, pawn_black3, pawn_black4, pawn_black5, pawn_black6, pawn_black7, pawn_black8,
			rook_black1, rook_black2, knight_black1, knight_black2, bishop_black1, bishop_black2, queen_black,
			king_black;

	@FXML
	ImageView pawn_white1, pawn_white2, pawn_white3, pawn_white4, pawn_white5, pawn_white6, pawn_white7, pawn_white8,
			rook_white1, rook_white2, knight_white1, knight_white2, bishop_white1, bishop_white2, queen_white,
			king_white;

	@FXML
	Rectangle a8, b8, c8, d8, e8, f8, g8, h8, a7, b7, c7, d7, e7, f7, g7, h7;

	@FXML
	Label playerTurnLabel;

	@FXML
	StackPane stackPane;

	@FXML
	GridPane gridPane;

	@FXML
	ListView<ImageView> black_list, white_list;

	@FXML
	public void returnToMainMenu(ActionEvent event) {
		/*
		 * try { Parent MainMenuViewParent =
		 * FXMLLoader.load(getClass().getResource("/application/MainMenu.fxml"));
		 * //retrieves the location of the fxml file Scene menuScene = new
		 * Scene(MainMenuViewParent); //initialize the new scene as the fxml we loaded
		 * //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		 * //this line gets to the stage information Stage window = (Stage)
		 * myMenuBar.getScene().getWindow(); //currently bugs out here
		 * window.setScene(menuScene); //changes the stage scene to be our new scene
		 * window.show(); }catch(Exception e) { e.printStackTrace();
		 * System.out.println("Can't return to Main Menu"); }
		 */
		System.out.println("it doesnt work yet");
	}

	@SuppressWarnings("static-access")
	public void makeMove(MouseEvent event) {
		System.out.println("Click");
		int x, y, x2, y2;
		madeMove = false;
		if (g.whoseTurn() == true) {
			try {
				// This if statements handles the first time something is clicked.
				if (!firstClick) {
					moves = null;
					test = null;
					firstClick = true;
					firstClickSpot = (ImageView) event.getSource();
					y = gridPane.getColumnIndex(firstClickSpot);
					x = gridPane.getRowIndex(firstClickSpot);
					a = GameBoard[x][y];
					System.out.println("Entered: " + x + " " + y);
					test = a.getPiece();

					// Determines if the piece does not match whose turn it is.
					if (test.isWhite() != g.whoseTurn()) {

						firstClick = false;
					}

					moves = test.getMoves(test, GameBoard); // Make sure the moves list isnt' null, would previously
															// 'pass'
															// your turn
					for (int ctr = 0; ctr < moves.size(); ctr++)
						System.out.print(moves.get(ctr).getAbbreviation() + ", ");

					if (moves.size() == 0) {
						firstClick = false;

					}

					if (test.isWhite() && b.getWhitePlayer().inCheck()) {
						System.out.println("You're in check!!");
						if (b.getBlackPlayer().inCheck())
							System.out.println("Black player in check as well.");
					} else if (!test.isWhite() && b.getBlackPlayer().inCheck()) {
						System.out.println("You're in check!!");
						if (b.getWhitePlayer().inCheck())
							System.out.println("White player in check as well.");
					}

					System.out.println(); // just making it easier to read console.

					// handles the 2nd item being clicked.
				} else if (firstClick && !secondClick) {
					System.out.println("Now Click Second Move");

					try {

						secondClickSpot = (ImageView) event.getSource();
						System.out.println("this is secondClickSpot: " + secondClickSpot);
						capturePiece = true;
					} catch (Exception e) {
						capturePiece = false;
						secondClickSpot = (Node) event.getSource();
					}

					y = gridPane.getColumnIndex(firstClickSpot);
					x = gridPane.getRowIndex(firstClickSpot);
					y2 = gridPane.getColumnIndex(secondClickSpot);
					x2 = gridPane.getRowIndex(secondClickSpot);

					System.out.println("Entered: " + x2 + " " + (y2));
					doMove(x, x2, y, y2);

				}
				// b.draw(b);

			} catch (Exception e) {
				firstClick = false;
				System.out.println("Fail");
			}
			System.out.println(g.whoseTurn());
			doMoveMagic();

			if (madeMove) {
				doAIMove();

			}
			// b.draw(b);
		}
	}

	public void doMoveMagic() {
		System.out.print(g.whoseTurn());
		if (g.whoseTurn()) {
			playerTurnLabel.setText("White Player's Turn");
			currentPlayer = true;
		} else {
			playerTurnLabel.setText("Black Player's Turn");
			currentPlayer = false;
		}
	}

	@SuppressWarnings("static-access")
	public void doAIMove() {
		int x, y, x2, y2;
		try {
			ArrayList<Piece> AiPieceList = new ArrayList<Piece>();
			AiPieceList = b.getBlackPlayer().getPieceList();
			ArrayList<Move> AiCurrentPossibleMoves = new ArrayList<Move>();
			AiCurrentPossibleMoves = b.getAllMoves(AiPieceList, b.getGameBoard());

			Move bestMove = b.getBlackPlayer().bestMoveForLoop(AiCurrentPossibleMoves);
			int i = bestMove.getPiece().getRow();
			int j = bestMove.getPiece().getCol();
			System.out.println("ROW:" + i + " Col:" + j);
			GetAIpiece(i, j);

			System.out.println("Best move for black player: " + bestMove.getAbbreviation());

			i = bestMove.getNew().getRow();
			j = bestMove.getNew().getColumn() - 1;
			GetAISecondspot(i, j);

			y = gridPane.getColumnIndex(firstClickSpot);
			x = gridPane.getRowIndex(firstClickSpot);
			y2 = gridPane.getColumnIndex(secondClickSpot);
			x2 = gridPane.getRowIndex(secondClickSpot);

			a = GameBoard[y][x];
			test = a.getPiece();
			System.out.println((x) + " " + (y) + " " + i + " " + j);
			moves = test.getMoves(test, GameBoard);
			System.out.println("Doing Move");
			System.out.println(x + " " + y + " " + i + " " + j);
			doMove(x, x2, y, y2);

			b.draw(b);

			doMoveMagic();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void GetAIpiece(int i, int j) {
		System.out.print(i + " " + j);
		firstClickSpot = null;
		System.out.print("Checking");
		for (Node child : gridPane.getChildren()) {
			int column = GridPane.getColumnIndex(child);
			int row = GridPane.getRowIndex(child);

			if (j == column && i == row) {
				if (child instanceof ImageView) {
					System.out.println(GridPane.getColumnIndex(child) + "Got IT");
					System.out.println(GridPane.getRowIndex(child));
					firstClickSpot = (ImageView) child;
				}
			}
		}
		if (firstClickSpot == null) {
		}
	}

	private void GetAISecondspot(int i, int j) {
		capture = false;
		secondClickSpot = null;
		System.out.print("Checking for" + i + " " + j);
		for (Node child : gridPane.getChildren()) {
			int column = GridPane.getColumnIndex(child);
			int row = GridPane.getRowIndex(child);
			if (i == row && j == column) {
				if (child instanceof ImageView) {
					System.out.println(
							"Row " + GridPane.getRowIndex(child) + "Col " + GridPane.getColumnIndex(child) + "IMG ");
					secondClickSpot = (ImageView) child;
					capturePiece = true;
					capture = true;
				} else if (capture == false) {
					System.out.println(
							"Row" + GridPane.getRowIndex(child) + "Col" + GridPane.getColumnIndex(child) + "Rect ");
					capturePiece = false;
					secondClickSpot = (Rectangle) child;

				}
			}
		}

	}

	@SuppressWarnings("static-access")
	private void doMove(int x, int x2, int y, int y2) {
		Move moveIteration = null;
		for (BoardButton butn : moves) {
			System.out.println(butn.getRow() + " " + (butn.getColumn()));
			if (butn.getRow() == x2 && butn.getColumn() == y2 + 1) {
				System.out.println("Success");
				Piece enemy = butn.getPiece();

				try {
					moveIteration = new Move(test, butn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				butn.getPiece().madeFirstMove();
				System.out.println("Moveing" + gridPane.getRowIndex(secondClickSpot) + " "
						+ gridPane.getColumnIndex(secondClickSpot));

				gridPane.setColumnIndex(firstClickSpot, gridPane.getColumnIndex(secondClickSpot));
				gridPane.setRowIndex(firstClickSpot, gridPane.getRowIndex(secondClickSpot));
				madeMove = true;
				if (capturePiece == true) {
					gridPane.getChildren().remove(secondClickSpot);
					if (currentPlayer) {
						black_list.getItems().add((ImageView) secondClickSpot);
					} else if (!currentPlayer) {
						white_list.getItems().add((ImageView) secondClickSpot);
					}
				}

				firstClick = false;
				g.turn();

				if (moveIteration.getAbbreviation().contains("x")) {
					System.out.println("Piece captured!");
					if (enemy.isWhite())
						System.out.println(
								"White " + enemy.getName() + " captured on square " + butn.getAbbreviation() + "!");
					else
						System.out.println(
								"Black " + enemy.getName() + " captured on square " + butn.getAbbreviation() + "!");
				}
				moves = test.getMoves(test, GameBoard); // Make sure the moves list isnt' null, would
														// previously
														// 'pass'

				for (int ctr = 0; ctr < moves.size(); ctr++) {
					if (moves.get(ctr).getPiece() instanceof King) { // checks to see if one of the possible
																		// moves is going to land on a king.
						if (currentPlayer) {
							System.out.println("black is in check"); // it will print that its in check, but
																		// currently doesn't do anything
																		// with
																		// it.
							// I think it has to do with the setCheck and stuff in piece.
						} else
							System.out.println("white is in check");
					}
				}
			} else {
				firstClick = false;
			}

		}

	}

	public void saveGame() {

//		
//		// Open a database connection
//         //(create a new database if it doesn't exist yet):
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
//        EntityManager em = emf.createEntityManager();
//        System.out.println("1");
//        em.getTransaction().begin();
//        GameDataBase game = new GameDataBase(white_list, black_list);
//        System.out.println("2");
//        em.persist(game);
//        System.out.println("3");
//        em.getTransaction().commit();
//		em.close();
//
//		System.out.println("4");
//		
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		g = new GameState();

		black_list.setCellFactory(listView -> new ListCell<ImageView>() {
			private ImageView imageView = new ImageView();

			@Override
			public void updateItem(ImageView view, boolean empty) {
				super.updateItem(view, empty);
				if (empty) {
					setText(null);
					setGraphic(null);
				} else {
					Image image = view.getImage();
					imageView.setImage(image);
					setGraphic(imageView);
				}
			}
		});
		white_list.setCellFactory(listView -> new ListCell<ImageView>() {
			private ImageView imageView = new ImageView();

			@Override
			public void updateItem(ImageView view, boolean empty) {
				super.updateItem(view, empty);
				if (empty) {
					setText(null);
					setGraphic(null);
				} else {
					Image image = view.getImage();
					imageView.setImage(image);
					setGraphic(imageView);
				}
			}
		});

		try {
			b = initDefault();
		} catch (Exception e) {

			e.printStackTrace();
		}
		GameBoard = b.getGameBoard();

		g.setWhite(b.getWhitePlayer());
		g.setBlack(b.getBlackPlayer());
		Move.setGameBoard(b);

	}

	public static Board initDefault() throws Exception {

		Player whitePlayer = new Player(true, false, 3);
		Player blackPlayer = new Player(false, false, 3);
		Move.setWhitePlayer(whitePlayer);
		Move.setBlackPlayer(blackPlayer);

		b = new Board(whitePlayer, blackPlayer);

		b.initBoard();
		return b;
	}

}
