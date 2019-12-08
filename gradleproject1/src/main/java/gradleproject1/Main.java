package gradleproject1;

import java.math.BigInteger;
/*
 Uberchess current build : 0.00.01
*/
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
	private static GameState g;
	
	public static Scanner scan;

	// Creates test board with single piece in the center of the board for
	// testability of generating moves. Space or other character for default
	public static Board testBoard(char c) throws Exception {

		Player whitePlayer = new Player(true, false, 3);
		Player blackPlayer = new Player(false, false, 3);
		Move.setWhitePlayer(whitePlayer);
		Move.setBlackPlayer(blackPlayer);

		Board b = new Board(whitePlayer, blackPlayer);
		BoardButton[][] GameBoard = b.getGameBoard();
		c = Character.toUpperCase(c);
		switch (c) {
		case 'Q':
			b.initBoardQueenTest();
			break;
		case 'K':
			b.initBoardKingTest();
			break;
		case 'B':
			b.initBoardBishopTest();
			break;
		case 'N':
			b.initBoardKnightTest();
			break;
		case 'R':
			b.initBoardRookTest();
			break;
		case 'P':	
			b.initPromotionTest();
			break;
		case 'C':
			b.initCastlingTest();
			break;
		case 'M':	
			b.initCheckmateTest();
			break;
		default:
			b = initDefault();
			break;
		}
		return b;
	}

	public static Board initDefault() throws Exception {

		Player whitePlayer = new Player(true, false, 3);
		Player blackPlayer = new Player(false, false, 3);
		Move.setWhitePlayer(whitePlayer);
		Move.setBlackPlayer(blackPlayer);

		Board b = new Board(whitePlayer, blackPlayer);

		BoardButton[][] GameBoard = b.getGameBoard();
		b.initBoard();
		return b;
	}

	/**
	 * @param args the command line arguments
	 * @throws Exception, because some things it calls throw exception, and I'm
	 *                    tired of big scary red error bars
	 */
	public static void main(String[] args) throws Exception {

		GameState g = new GameState();
		Main.g = g;

		Scanner s = new Scanner(System.in);
		scan = s;
		String in = null;
		char c = ' ';
		System.out.println("Enter character of Piece to construct test board, C for Castle test,\n"
				+ "P for promotion/pawn test, M for Checkmate Test,\ninvalid char/piece for default game:");
		if (s.hasNextLine())
			in = s.nextLine();
		if (in != null)
			c = in.charAt(0);

		Board b = testBoard(c);
		BoardButton[][] GameBoard = b.getGameBoard();
		boolean run = true;
		boolean turn = g.whoseTurn();

		g.setWhite(b.getWhitePlayer());
		g.setBlack(b.getBlackPlayer());
		if (c == 'm')	{
			g.turn();
		}
		b.draw(b);

		Move.setGameBoard(b);
		System.out.println("White: Upper Case, first move:");
		/*
		 * System.out.println(" ");
		 * System.out.println("White: Upper Case, first move:"); System.out.
		 * println("Hint: Type grid square for first pawn move (no need to specify pawn):"
		 * ); /
		 */// System.out.println("Enter move for pawn");

		while (run) {
			ArrayList<BoardButton> moves = new ArrayList<BoardButton>();
			try {

				System.out.println("Move or Info? M for manual move, A for AI move (black only), I for info, H for history, 'Quit' to quit:");
				String control = s.nextLine();
				if (control.compareToIgnoreCase("quit") == 0) {
					break;
				}
				//AI mode
				if (control.compareToIgnoreCase("a") == 0) {
					System.out.println("AI move selected.");

					// Copy - pasted from below 'move mode', altered to only support white player's
					// moves

					Piece test = null;
					BoardButton a = null;
					String oldX1 = null;
					char[] oldXchar = { ' ' };

					if (g.whoseTurn()) {

						do { // Do-while for the player to select a piece of the proper team

							System.out.println("\nEnter Col "); // While kicks the player back up here if they select a
																// wrong team's piece or a piece with 0 moves
							oldX1 = s.nextLine();
							if (oldX1.compareToIgnoreCase("quit") == 0) {
								break;
							}
							oldXchar = oldX1.toCharArray();
							oldXchar[0] = Character.toUpperCase(oldXchar[0]);
							int x = (int) oldXchar[0];
							System.out.println("Enter Row ");
							String oldY1 = s.nextLine();
							if (oldY1.compareToIgnoreCase("quit") == 0) {
								break;
							}
							// Changed 11/2/2019 - Test again. Attempted to make the test values be Letter &
							// Row Number
							// System.out.println("Asserts being called");
							assert (x - 'A') >= 0;
							assert (x - 'A') <= 7;
							assert (Integer.valueOf(oldY1) - 1) >= 0;
							assert (Integer.valueOf(oldY1) - 1) <= 7;
							// System.out.println("Asserts true");
							a = GameBoard[x - 'A'][Integer.valueOf(oldY1) - 1];

							// System.out.println("Calling button " + (x - 'A') + " " +
							// (Integer.valueOf(oldY1) - 1));

							try {
								test = a.getPiece();
								if (test.isWhite() != g.whoseTurn())
									System.out.println("Wrong team. Try again:");
								assert (test.isWhite() == g.whoseTurn()); // Crash the Try-Catch if a piece is selected
																			// of
																			// wrong color

							} catch (Exception e) {
								System.out.println("Generalized error. Try again.");
							}
							ArrayList<Move> allMoves = new ArrayList<Move>();
							if (test.isWhite() && b.getWhitePlayer().inCheck()) {
								//if 0 length it's a checkmate
								allMoves = b.getAllMoves(b.getWhitePlayer().getPieceList(), b.getGameBoard());
								if (allMoves.size() == 0) {
									System.out.println("White checkmated! Line 167 Main");
									g.setWinrar(b.getBlackPlayer());
									b.getWhitePlayer().setGame(false);
									b.getBlackPlayer().setGame(true);
								}
								System.out.println("You're in check!!");
								if (b.getBlackPlayer().inCheck())
									System.out.println("Black player in check as well.");
							} else if (!test.isWhite() && b.getBlackPlayer().inCheck()) {
								//if 0 length it's a checkmate
								allMoves = b.getAllMoves(b.getBlackPlayer().getPieceList(), b.getGameBoard());
								if (allMoves.size() == 0) {
									System.out.println("Black checkmated! Line 180 Main");
									g.setWinrar(b.getWhitePlayer());
									b.getWhitePlayer().setGame(true);
									b.getBlackPlayer().setGame(false);
								}
							
								System.out.println("You're in check!!");
								if (b.getWhitePlayer().inCheck()) System.out.println("White player in check as well.");
							}
							
							 else if (b.getBlackPlayer().inCheck()) {
								allMoves = b.getAllMoves(b.getBlackPlayer().getPieceList(), b.getGameBoard());
								if (allMoves.size() == 0) {
								System.out.println("Black checkmated! Line 191 Main");
								g.setWinrar(b.getWhitePlayer());
								b.getWhitePlayer().setGame(true);
								b.getBlackPlayer().setGame(false);
								}
							
								System.out.println("Black player in check");
							}
								else if (b.getWhitePlayer().inCheck()) {

									if (b.getWhitePlayer().inCheck()) {
										allMoves = b.getAllMoves(b.getWhitePlayer().getPieceList(), b.getGameBoard());
										if (allMoves.size() == 0) {
											System.out.println("White checkmated! Line 167 Main");
											g.setWinrar(b.getBlackPlayer());
											b.getWhitePlayer().setGame(false);
											b.getBlackPlayer().setGame(true);
										}
									}
								
								System.out.println("White player in check");
								}

							moves = test.getMoves(test, GameBoard); // Make sure the moves list isnt' null, would
																	// previously
																	// 'pass' your turn
							System.out.println("Possible Moves:");
							for (int ctr = 0; ctr < moves.size(); ctr++)
								System.out.print(moves.get(ctr).getAbbreviation() + ", ");
							System.out.println(".");
							System.out.println("Current offset: " + test.getOffset());
							if (moves.size() == 0) {
								System.out.println("No valid moves. Select another piece.");
							}

						} while (test.isWhite() != g.whoseTurn() || moves.size() == 0); // End do-while. Piece obtained,
																						// getmoves called

						// System.out.println("Piece obtained: " + a.getPiece().getName() + " , calling
						// getmoves");

						boolean moveFlag = true;
						Move moveIteration = null;
						while (moveFlag) {
							/*
							 * System.out.println("Enter col to move:"); String move = s.nextLine(); if
							 * (move.compareToIgnoreCase("quit")==0) break;
							 */
							System.out.println("\nEnter Col to Move to: ");
							String oldX2 = s.nextLine();
							if (oldX2.compareToIgnoreCase("quit") == 0) {
								break;
							}
							char[] oldXchar2 = oldX1.toCharArray();
							int x2 = (int) Character.toUpperCase(oldXchar[0]);
							System.out.println("Enter Row to Move to: ");
							String oldY2 = s.nextLine();
							if (oldY2.compareToIgnoreCase("quit") == 0) {
								break;
							}
							// System.out.println("Move attempted: " + );
							boolean madeMove = false;
							for (BoardButton butn : moves) {
								if ((Character.toString(test.getAbbrev()) + oldX2.toUpperCase() + oldY2)
										.compareTo(test.getAbbrev() + butn.getAbbreviation()) == 0) {
									Piece enemy = butn.getPiece();
									moveIteration = new Move(test, butn);
									madeMove = true;

									if (moveIteration.getAbbreviation().contains("x")) {
										System.out.println("Piece captured!");
										if (enemy.isWhite())
											System.out.println("White " + enemy.getName() + " captured on square "
													+ butn.getAbbreviation() + "!");
										else
											System.out.println("Black " + enemy.getName() + " captured on square "
													+ butn.getAbbreviation() + "!");
									}

								}

							}
							if (!madeMove)
								System.out.println("Invalid move. Try again.");
							moveFlag = false;
							if (g.whoseTurn())
								System.out.println("Black's Turn:");
							else
								System.out.println("White's Turn:");
							g.turn();

						}
					} else {
						// FIXME - In progress AI agent code
						// Ryan Brodsky's 3 move search depth move gen, only does one team

						ArrayList<Piece> AiPieceList = new ArrayList<Piece>();
						AiPieceList = b.getBlackPlayer().getPieceList();
						ArrayList<Move> AiCurrentPossibleMoves = new ArrayList<Move>();
						AiCurrentPossibleMoves = b.getAllMoves(AiPieceList, b.getGameBoard());

						Move bestMove = b.getBlackPlayer().bestMoveForLoop(AiCurrentPossibleMoves);
						System.out.println("Best move for black player: " + bestMove.getAbbreviation());
						Move moveIteration = new Move(bestMove.getPiece(), bestMove.getNew());
						// Already done so not needed: b.getBlackPlayer().addMove(moveIteration);
					}
					// End in progress
					g.turn();
				}

				// MOVE MODE -FIXME
				if (control.compareToIgnoreCase("M") == 0) { // Call moves, select a grid location of a piece
					Piece test = null;
					BoardButton a = null;
					String oldX1 = null;
					char[] oldXchar = { ' ' };
					
					
                      
					do { // Do-while for the player to select a piece of the proper team

//					
//						ArrayList<Piece> AiTestList = new ArrayList<Piece>();
//						AiTestList = b.getWhitePlayer().getPieceList();
//						System.out.println("Eval Points: " + b.getWhitePlayer().evalPoints(AiTestList));

						System.out.println("\nEnter Col "); // While kicks the player back up here if they select a
															// wrong team's piece or a piece with 0 moves
						oldX1 = s.nextLine();
						if (oldX1.compareToIgnoreCase("quit") == 0) {
							break;
						}
						oldXchar = oldX1.toCharArray();
						oldXchar[0] = Character.toUpperCase(oldXchar[0]);
						int x = (int) oldXchar[0];
						System.out.println("Enter Row ");
						String oldY1 = s.nextLine();
						if (oldY1.compareToIgnoreCase("quit") == 0) {
							break;
						}
						// Changed 11/2/2019 - Test again. Attempted to make the test values be Letter &
						// Row Number
						// System.out.println("Asserts being called");
						assert (x - 'A') >= 0;
						assert (x - 'A') <= 7;
						assert (Integer.valueOf(oldY1) - 1) >= 0;
						assert (Integer.valueOf(oldY1) - 1) <= 7;
						// System.out.println("Asserts true");
						a = GameBoard[x - 'A'][Integer.valueOf(oldY1) - 1];

						// System.out.println("Calling button " + (x - 'A') + " " +
						// (Integer.valueOf(oldY1) - 1));

						try {
							test = a.getPiece();
							if (test.isWhite() != g.whoseTurn())
								System.out.println("Wrong team. Try again:");
							assert (test.isWhite() == g.whoseTurn()); // Crash the Try-Catch if a piece is selected of
																		// wrong color

						} catch (Exception e) {
							System.out.println("Generalized error. Try again.");
						}
						if (test.isWhite() && b.getWhitePlayer().inCheck()) {
							System.out.println("You're in check!!");
							if (b.getBlackPlayer().inCheck()) System.out.println("Black player in check as well.");
						}
						else if (!test.isWhite() && b.getBlackPlayer().inCheck()) {
							System.out.println("You're in check!!");
							if (b.getWhitePlayer().inCheck()) System.out.println("White player in check as well.");
						}
						else if (b.getBlackPlayer().inCheck()) System.out.println("Black player in check");
						else if (b.getWhitePlayer().inCheck()) System.out.println("White player in check");
						
						moves = test.getMoves(test, GameBoard); // Make sure the moves list isnt' null, would previously
																// 'pass' your turn
						System.out.println("Possible Moves:");
						for (int ctr = 0; ctr < moves.size(); ctr++)
							System.out.print(moves.get(ctr).getAbbreviation() + ", ");
						System.out.println(".");
						System.out.println("Current offset: " + test.getOffset());
						if (moves.size() == 0) {
							System.out.println("No valid moves. Select another piece.");
						}

					} while (test.isWhite() != g.whoseTurn() || moves.size() == 0); // End do-while. Piece obtained,
																					// getmoves called

					// System.out.println("Piece obtained: " + a.getPiece().getName() + " , calling
					// getmoves");

					boolean moveFlag = true;
					Move moveIteration = null;
					while (moveFlag) {
						/*
						 * System.out.println("Enter col to move:"); String move = s.nextLine(); if
						 * (move.compareToIgnoreCase("quit")==0) break;
						 */
						System.out.println("\nEnter Col to Move to: ");
						String oldX2 = s.nextLine();
						if (oldX2.compareToIgnoreCase("quit") == 0) {
							break;
						}
						char[] oldXchar2 = oldX1.toCharArray();
						int x2 = (int) Character.toUpperCase(oldXchar[0]);
						System.out.println("Enter Row to Move to: ");
						String oldY2 = s.nextLine();
						if (oldY2.compareToIgnoreCase("quit") == 0) {
							break;
						}
						// System.out.println("Move attempted: " + );
						boolean madeMove = false;
						for (BoardButton butn : moves) {
							if ((Character.toString(test.getAbbrev()) + oldX2.toUpperCase() + oldY2)
									.compareTo(test.getAbbrev() + butn.getAbbreviation()) == 0) {
								Piece enemy = butn.getPiece();
								
								if (test.getAbbrev() == 'P' && oldY2.compareTo(Integer.toString(8)) == 0) {
									if (oldX2.compareTo(oldX1)!=0) {
										if (oldX2.compareTo(oldX1) > 0) moveIteration = new Move((Pawn)test, true);	//Step right
										if (oldX2.compareTo(oldX1) < 0) moveIteration = new Move((Pawn) test, false);	//Step left
									}
									else moveIteration = new Move ((Pawn) test);		
										
									}
								else if (test.getAbbrev() == 'p' && oldY2.compareTo(Integer.toString(0)) == '0') {
									if (oldX2.compareTo(oldX1)!=0) {
										if (oldX2.compareTo(oldX1) > 0) moveIteration = new Move((Pawn)test, true);	//Step right
										if (oldX2.compareTo(oldX1) < 0) moveIteration = new Move((Pawn) test, false);	//Step left
									}
									else moveIteration = new Move ((Pawn) test);
								}
								else {
									moveIteration = new Move(test, butn);
									madeMove = true;
								}
								if (moveIteration.getAbbreviation().contains("x")) {
									System.out.println("Piece captured!");
									if (enemy.isWhite())
										System.out.println("White " + enemy.getName() + " captured on square "
												+ butn.getAbbreviation() + "!");
									else
										System.out.println("Black " + enemy.getName() + " captured on square "
												+ butn.getAbbreviation() + "!");
								}

							}

						}
						if (!madeMove)
							System.out.println("Invalid move. Try again.");
						moveFlag = false;
						if (g.whoseTurn())
							System.out.println("Black's Turn:");
						else
							System.out.println("White's Turn:");
						g.turn();

					}
				}
				// INFO MODE- -FIXME
				else if (control.compareToIgnoreCase("I") == 0) {
					System.out.println("\nEnter Col ");
					String oldX = s.nextLine();
					if (oldX.compareToIgnoreCase("quit") == 0) {
						break;
					}
					int x2, y2;
					char[] oldXchar2 = oldX.toCharArray();
					x2 = (int) Character.toUpperCase(oldXchar2[0]);
					System.out.println("Enter Row ");
					String oldY = s.nextLine();
					if (oldY.compareToIgnoreCase("quit") == 0) {
						break;
					}
					// Changed 11/2/2019 - Test again. Attempted to make the test values be Letter &
					// Row Number
					// System.out.println("Asserts being called");
					assert (x2 - 'A') >= 0;
					assert (x2 - 'A') <= 7;
					assert (Integer.valueOf(oldY) - 1) >= 0;
					assert (Integer.valueOf(oldY) - 1) <= 7;
					// System.out.println("Asserts true");
					BoardButton a1 = GameBoard[x2 - 'A'][Integer.valueOf(oldY) - 1];
					// System.out.println("Calling button " + (x2 - 'A') + " " +
					// (Integer.valueOf(oldY) - 1));
					Piece test2 = a1.getPiece();
					// System.out.println("Piece obtained," + a1.getPiece().getAbbrev() + " ,
					// calling getmoves");
					moves = test2.getMoves(test2, GameBoard);

					System.out.println("\nPiece: " + test2.getName());
					if (test2.isWhite() == true) {
						System.out.println("Piece Team: White \n");
					} else {
						System.out.println("Piece Team: Black \n");
					}
					System.out.println("Current offset: " + test2.getOffset());
					System.out.print("Selected team points: ");
					if (test2.isWhite()) {
						System.out.println(g.getWhite().evalPoints());
					} else {
						System.out.println(g.getBlack().evalPoints());
					}

					System.out.println("Generating all moves: ");
					ArrayList<Move> allPlayersMovesList = new ArrayList<Move>();
					double time = (double) System.nanoTime();
					if (test2.isWhite())
						allPlayersMovesList = b.getAllMoves(g.getWhite().getPieceList(), b.getGameBoard());
					else
						allPlayersMovesList = b.getAllMoves(g.getBlack().getPieceList(), b.getGameBoard());
					double time2 = (double) System.nanoTime();
					System.out.println("All moves for team selected: ");
					for (Move m : allPlayersMovesList) {
						System.out.print(m.getAbbreviation() + ", ");
					}
					double elapsed = (double) time2 - (double) time;
					elapsed = elapsed / 1000000;
					//long result = TimeUnit.SECONDS.convert(elapsed, TimeUnit.NANOSECONDS);

					System.out.println("Time taken to generate moves: " + elapsed + " Milliseconds.");

				}

				// HISTORY MODE
				else if (control.compareToIgnoreCase("H") == 0) {
					// System.out.println("Calling History mode:");
					boolean historyOfWhomstdve = g.whoseTurn();
					// System.out.println("Team = " + historyOfWhomstdve);
					ArrayList<Move> gamerMoves;
					// System.out.println("Calling getMoves:");
					if (historyOfWhomstdve)
						gamerMoves = g.getWhite().getMoves();
					else
						gamerMoves = g.getBlack().getMoves();
					System.out.println("Your team's moves:");
					for (Move m : gamerMoves) {
						System.out.println(m.getAbbreviation());
					}
					System.out.println("");

				}

				else if (control.compareToIgnoreCase("quit") == 0) {
					System.out.println("Loop broken. Have a nice day, party hard!");
					break;
				}
			} catch (Exception e) {
				System.out.println("No Moves at Entered Location");
				e.printStackTrace();
			}

//			for (BoardButton foff : moves) {
//				System.out.println("Possible Moves " + foff.getAbbreviation());
//			}

			System.out.println("");

			b.draw(b);

		} // End While
	}

	/**
	 * Call when user starts a new AI game
	 */

	public static void ownedByFactsAndLogic() {
	
	}

	public static void poolsClosed() {

	}

}