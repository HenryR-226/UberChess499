package gradleproject1;

/*
 Uberchess current build : 0.00.01
*/
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	
	// Creates test board with single piece in the center of the board for testability of generating moves. Space or other character for default
	public static Board testBoard(char c) throws Exception {

		Player whitePlayer = new Player(true, false);
		Player blackPlayer = new Player(false, false);
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
		default:
			b = initDefault();
		}
		return b;
	}

	public static Board initDefault() throws Exception {

		Player whitePlayer = new Player(true, false);
		Player blackPlayer = new Player(false, false);
		Move.setWhitePlayer(whitePlayer);
		Move.setBlackPlayer(blackPlayer);

		Board b = new Board(whitePlayer, blackPlayer);
		BoardButton[][] GameBoard = b.getGameBoard();
		b.initBoard();
		return b;
	}

	/**
	 * @param args the command line arguments
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		GameState g = new GameState();
		Scanner s = new Scanner(System.in);
		String in = null;
		char c = ' ';
		System.out.println("Enter character of Piece to construct test board, invalid char/piece for default game:");
		if (s.hasNextLine())
			in = s.nextLine();
		if (in != null)
			c = in.charAt(0);

		Board b = testBoard(c);
		BoardButton[][] GameBoard = b.getGameBoard();
		boolean run = true;

		b.draw(b);

		Move.setGameBoard(b);
		/*
		System.out.println(" ");
		System.out.println("White: Upper Case, first move:");
		System.out.println("Hint: Type grid square for first pawn move (no need to specify pawn):");
		/
		*/// System.out.println("Enter move for pawn");

		while (run) {
			ArrayList<BoardButton> moves = new ArrayList<BoardButton>();
			try {

				System.out.println("Move or Info? M for move, I for info, 'Quit' to quit:");
				String control = s.nextLine();
				if (control.compareToIgnoreCase("quit") == 0) {
					break;
				}
				if (control.compareToIgnoreCase("M") == 0) { // Call moves, select a grid location of a piece
					Piece test = null;
					BoardButton a = null;
					String oldX1 = null;
					char[] oldXchar = { ' ' };
					do {
						System.out.println("\nEnter Col ");
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
							assert (test.isWhite() == g.whoseTurn()); // Just crash the Try-Catch if a piece is selected
																		// of wrong color
						} catch (Exception e) {
							System.out.println("Wrong team. Try again.");
						}
					} while (test.isWhite() != g.whoseTurn());
					System.out.println("Piece obtained," + a.getPiece().getAbbrev() + " , calling getmoves");
					moves = test.getMoves(test, GameBoard);
					System.out.println("Possible Moves:");
					for (int ctr = 0; ctr < moves.size(); ctr++) {
						System.out.print(moves.get(ctr).getAbbreviation() + ", ");
						System.out.println(".");
					}

					boolean moveFlag = true;
					Move moveIteration = null;
					while (moveFlag) {
						/*
						 * System.out.println("Enter col to move:"); String move = s.nextLine(); if
						 * (move.compareToIgnoreCase("quit")==0) break;
						 */
						System.out.println("\nEnter Col ");
						String oldX2 = s.nextLine();
						if (oldX2.compareToIgnoreCase("quit") == 0) {
							break;
						}
						char[] oldXchar2 = oldX1.toCharArray();
						int x2 = (int) Character.toUpperCase(oldXchar[0]);
						System.out.println("Enter Row ");
						String oldY2 = s.nextLine();
						if (oldY2.compareToIgnoreCase("quit") == 0) {
							break;
						}
						// System.out.println("Move attempted: " + );
						for (BoardButton butn : moves) {
							if ((Character.toString(test.getAbbrev()) + oldX2.toUpperCase()+ oldY2)
									.compareTo(test.getAbbrev() + butn.getAbbreviation()) == 0)
								moveIteration = new Move(test, butn);
							
							/*
							System.out
									.println("Move attempted: " + (Character.toString(test.getAbbrev()) + oldX2 + oldY2)
											+ " should be equal to " + test.getAbbrev() + butn.getAbbreviation()); */
							
							// b.getWhitePlayer().addMove(moveIteration);

						}
						moveFlag = false;
						g.turn();
					}
				}

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
					System.out.println("Asserts being called");
					assert (x2 - 'A') >= 0;
					assert (x2 - 'A') <= 7;
					assert (Integer.valueOf(oldY) - 1) >= 0;
					assert (Integer.valueOf(oldY) - 1) <= 7;
					System.out.println("Asserts true");
					BoardButton a1 = GameBoard[x2 - 'A'][Integer.valueOf(oldY) - 1];
					System.out.println("Calling button " + (x2 - 'A') + " " + (Integer.valueOf(oldY) - 1));
					Piece test2 = a1.getPiece();
					System.out.println("Piece obtained," + a1.getPiece().getAbbrev() + " , calling getmoves");
					moves = test2.getMoves(test2, GameBoard);
					System.out.println("Possible Moves:");

					System.out.println("\nPiece: " + test2.getName());
					if (test2.isWhite() == true) {
						System.out.println("Piece Team: White \n");
					} else {
						System.out.println("Piece Team: Black \n");
					}

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
		System.out.println(
				"                                                               -+ydNNNNMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNNNho/.                                                                     ");
		System.out.println(
				"                                                            .+hmNNNNMNNMMMMMMMMMMMMMMMMMMNNNNNNNNMMMMMMMMMMMMMMMMMMMMMMMMMNNNNNNNNNds:                                                                 ");
		System.out.println(
				"                                                         -+hmNNNNNNMMMMMNNNNNNNNNNNNNNNNNNNNNMNMMMMNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNds-                                                              ");
		System.out.println(
				"                                                       -smmNNNNNMMMNNMNmmNNNNNNNNNNNMMNMMNMMMMMMMMMMMMMMMNNNNNNNNNNMMMMNNNNNNNNNNNNNNNNNNy-                                                            ");
		System.out.println(
				"                                                     -sdmmNNNNmmNNNNNNNMMMNNNNNNNNMMMMMMMMMMMMMMMMMMNNNNNMMMNMMMMMNNNNMMMNNNNMMMMMMNNNNNNNdo`                                                          ");
		System.out.println(
				"                                                   `+dmNNmmmmdmNNNNMNNNNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNMMMMMMMMNNNNmh-                                                        ");
		System.out.println(
				"                                                  .smmmmdmNNNNNNNNNMMMMNNNNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNNm+'                                                       ");
		System.out.println(
				"                                                 /hmddNmNNNNNNMMNNNNNMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMNNNNd/.                                                      ");
		System.out.println(
				"                                                /hddmNNNMNNMMNNMNNMMMMMMMMMMMMMMNNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNNNNNNmd+`                                                    ");
		System.out.println(
				"                                              .+ddmmNNNMMNMMMNNNNNNMMMNNNMMMMMMMMNNNNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNMMMMMMMMMNNNMNmmy:                                                   ");
		System.out.println(
				"                                            .+hhmmNNNNNMMNMNmNNNNNNMMMMNMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNNmdo.                                                 ");
		System.out.println(
				"                                          `/ydmmNNNNNNMMMMMNNNMMMMNMMMNMMMMMMMMMMMMMNNNMMMMMMMMMMMMMNMMMMMMMMMMMMNNNMMMMMMMMMMMMMNMMMMMMMMMMMMMMNNNNNmd/                                                ");
		System.out.println(
				"                                         :yhmmNNNNNNNMMMMMMNNNNNNNMNNNNMMMMMMMMMMMMMNNNNMMMMMNNNMMMNNNNMMMMMMMMMMMMMMMNNMMMMMMMMMMNNNMMMMMMMMMMMNMNNNNNm+                                               ");
		System.out.println(
				"                                       `/hdmmmNNNNNNNNMMMNMNNNmNNNNNNNNMMNNMMMMMMMMMMMMNNNMMMMMMMMMMMNNNNNNNNNNMMNMNMMMMMMMMMMMMMNNNNNMMMMMMMMMMNMMNNNNNmo`                                             ");
		System.out.println(
				"                                       ./dmmmNNNNNNNNNNNMMMNNNmmNNNNMMMMMMMMMMMMMNMMMNNMMMMMMNNMMMMNMNMMMMNNNMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMNMMMMMNMMNNNNNd+                                             ");
		System.out.println(
				"                                       -hmmmNNNmmNNNNNNMNNNNNNNmNNNNMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMNNNNNMMMNNNNNNNd:`                                            ");
		System.out.println(
				"                                      `+ddmmmNmNNNNNNNNNNNNNNNmmNNNNNNMMMNMMMMMMNMMMMMMMMMMMMMMMMMMMMMNNMMMMMMMNNNMMMMMMMMMMMMMMMMMMMMMMMMMNNNNNNNNNNNNNNN+                                             ");
		System.out.println(
				"                                      `omdmmNNNNNNNNNNNmmNNmmmNNNNNNNNNMNMNNNNNNNNMNNNMNMMMMMMMMMNNMMMMMNMMNNMNMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNNMMNNNNNNNNNmd`                                            ");
		System.out.println(
				"                                      `ohmNNNNNNNNNNNNNNmmmmmmmmmmmmNNNNNNNNmmmmmmmmmNmNNNNNNNNNNNMNNMNNNNNNMMMMMMMMMMMMMMMMMNNNNNNNNNNNNNNMMMMMMNNNNMNNNNmo                                            ");
		System.out.println(
				"                                       /dmNNNNNNNNNNNNNmmmmmmdmmmmdddmmNmmmmmmhhhhdhhhdmmmNmNNNNNNmmNmmmNNNNNMMMMMMMMMMMMMMNNNNNNNNNNNNNMMNNNNMMMMMMNNMMMNNd:                                           ");
		System.out.println(
				"                                      `sdNNNNNNNMMMNNmNmmmmdddhhyyyyyyyhddddhhysooossyyyhhdhhddddddddddmmdmNmmNNNNNMMMMMMMMMMMMMMNNNNNNNNMMMNMMMMMMMNMNMMMNNh`                                          ");
		System.out.println(
				"                                      `+dNNNNNMNNNNNNmmmmddhyso+++++++osyyyyhyso+///:+/+ossssyyhhhhhhdhyyyhyhhdmmNNNNNNNNNNNNNNNNNNNNNNNNNNMNMNMMMMMMMMMMMMNm/                                          ");
		System.out.println(
				"                                       /hmmNNNNNNNNmmmdhyyso+/::::::::::/+++o+//:::-----://++oosssssoo+++++osyyhhhdddddddddddmddmmmmmmmmmmmNNNNMMMMMMMMMMMNMNs`                                         ");
		System.out.println(
				"                                       -hddmNNNNNmmddhyso++//:::--------::::::----..........--::-:::::::---:://++++++++++oooosssyyyhhddddddddmNNMMMMMMMMMMNNms.                                         ");
		System.out.println(
				"                                       :syhmmNmmmdhyso++///:::----------.....................................------::::::::////+++oosssyyyyyyhhmNNMMMMMMMMMNm+                                          ");
		System.out.println(
				"                                       -soydmmmmdhsoo+////:::--------.....................``````.................----------::::////++ssossssssyhmNMMMMMMMMNNm+                                          ");
		System.out.println(
				"                                       `ossddmmdhyso++///::::--------.....................`````````..............-.---------:::::////++++ooooosyhmNNMMMMNNNmm:                                          ");
		System.out.println(
				"                                       `/+syhddhhyo++//:::::---------....................````..```...................-------::::::::////+++oooosydmNMMMMNNmmm:                                          ");
		System.out.println(
				"                                        -/ssdhhyyss++/:::::--------.-.....................```..````.................--------::::::::::///++++oosydmmNMNNNNmmh-                                          ");
		System.out.println(
				"                                         :+syhsysoo+//:::::-------..-...................`````.......................--------::::::::::://///++osyhmmNNNNNmmms`                                          ");
		System.out.println(
				"                                         ./+sosso++///::::------.----............................................----------:::-:::::::://///++osyhddNmNmmmmdy`                                          ");
		System.out.println(
				"                                         `//ooo+++////:::---------..............................................---.------::::::::::::://////++oyhhdmmmNmhmmd-                                          ");
		System.out.println(
				"                                         -::+/++/////::::----------............................................---------------::::::::////////++syhdmmmmhydmmy`                                         ");
		System.out.println(
				"                                         :///-/:/://::::----------------.......................................------------::::::::::://///////+/syhddmdhhmmNm+                                         ");
		System.out.println(
				"                                         /+oo.:-://:--------------------.....................................-------------::::::::::/://////////:oshydhhddmNmd:                                         ");
		System.out.println(
				"                                         :yso:-``-:-`--------------------...................................----------::-:::::::::://////////::--:+oohyhdmmNNdy/-`                                      ");
		System.out.println(
				"                                    ``..`:yyso:  ``.`.--------------------..................................------------:--::::::::////////:::-.---./yhhdmmmNmy+:`                                      ");
		System.out.println(
				"                                    `---:ooyso/   ```..----------------------...............................--------------::::::::::::////::--.-..`.:yhddmdNmhs:./`                                     ");
		System.out.println(
				"                                     `--:+ssoo/    ```..--------------------................................-..---------::::::::::::////:/::-.....-.:ydhdydmdy/:yh.                                     ");
		System.out.println(
				"                                    -..:::ososo`    ``...----------.-----....................................------------::::::::::::////:::-...-.---ydhyydsso+hhs`                                     ");
		System.out.println(
				"                                   `:/:-::/ooo+`    ```..-------..--.............................................--------:::::::::::::////::--..---:-sdhhho/oo+hy+                                      ");
		System.out.println(
				"                                   `-:--:--+o+/.    ```...----------.-.....--................................-----------:---::::::///:////:::-----:::shhy+:ohs/ss:                                      ");
		System.out.println(
				"                                    .-.-----+o+`     ``..------:::------------......................--.....--------------:::::://+ooso+++++//:---:--:ohh+:+yso//+.                                      ");
		System.out.println(
				"                                    ``.-----:+/`    `.--:////+osso++/::::--------.............-........---------------:::://+oosyhhhdddhyyyso+/---.--/yy/sdyso+::                                       ");
		System.out.println(
				"                                     `.-:://:/:     ``:/syyhhhddmddhyysoo+/:::::::-----------------.-------:---:-:--:///+osyhdmmmmNmmmmNmmdhyo/::-.--:s+sddyso+/.                                       ");
		System.out.println(
				"                                     `--://++:-  `` ```:+oyyyyhdmmmmmmmmdhhhhhyyyso+oo+/::::-----------:::::/:::////+oyhhdmNNNNNMNNNNNNmmdhys+:::-...-/ohhyyso//-                                       ");
		System.out.println(
				"                                     .--::::::.  `````.-:+osyyyyhhdmmNNNNNNNNNNNmmmmddhyso+/:::::::::::::/o+oosyhhdmmmNNNNNMMMMNNNmmddhhyssso+/:--....:+ssoo+//:.                                       ");
		System.out.println(
				"                                     ..---...-`   ``..--:/+++oooossyyhdmNNNNNNNNNNNNmmmdhhy++/:::::::///+oshdmmNNNNNNMNMMMMMNNNmmdddddhysssss+/:--....-o++o+++/:.                                       ");
		System.out.println(
				"                                     `.---...-`   ``---://++osyhhhhhhdmmNNNNMMMNNNNmmmmmdhyso+//:::://+osyhdmmNNNNMMMMMMMMMMMMMNNNNmddmmdhysso+/--...`-so/+oo+/-                                        ");
		System.out.println(
				"                                      ..---..-`    `.-::/++osyhhddmmNmmNNNMNMMMNNNNNmmddhhyso+///:://++oshdmmNNNNMMMMNNMMMMMMNNmNNNNNNmdhsso++/:--..``.oo/oo/::`                                        ");
		System.out.println(
				"                                       `..-..-`    `.--::::///oyhddhhysdNNNNNNdsshmmddhyyyyso+/:::--://oshdddddNNNNmmdhhyyyhhddddddhyhhso+++////:-..``./s+o+/-.                                         ");
		System.out.println(
				"                                        `.-.--     `----:/::::/+oooossoosssoyoo+osyyysyo//++/::---.---:+osyo++sssossssoossssssssssssyyso+++/////:-.````:s+s+/.                                          ");
		System.out.println(
				"                                        `..-.-     `.----:::::::///////++ooooooo+ooo//::::://:--....--:/+oo+/+////++o+oooooooossossoooo+++//////:-.````:o++/:`                                          ");
		System.out.println(
				"                                         `...-     `----------:::::::::///////////////::::::::--....---:/oo+/////////////++++++++++////::/:////::.`````.+o//-                                           ");
		System.out.println(
				"                                          ..-.     `.------------------:::::::::::::::::::::::-......--:/+++////::::::::::::::::::::::::::::///:-.`````.///:`                                           ");
		System.out.println(
				"                                          `...     `.------------------------------------:::---......--://++////:::-----::::----:--::::::://///:-.``` `.+//-`                                           ");
		System.out.println(
				"                                          `...      `.-----------......-------.----------:::--.......--:/+++////::-----------------:::::::::::/-.```` `.o+:`                                            ");
		System.out.println(
				"                                           ...      `.-----------.......-.......--------::----......---://+++///:::----------------:::::::::/::-.````  :o:`                                             ");
		System.out.println(
				"                                           ``.`     ``.------------.............----------:---......---://///////:::--------------:-::::::::::-..````  /+`                                              ");
		System.out.println(
				"                                             ``     ``.---------.---.......-....-------------........--:://///////::::-------------:::::::::::-...```  /.                                               ");
		System.out.println(
				"                                              `      ``.---------------..---.----------------........--::////++++++//::::--------:::::::///:-.....```  `                                                ");
		System.out.println(
				"                                                    ```..---------------------------::::------.......--::://///++o+++///::::::::::::://///::-....````                                                   ");
		System.out.println(
				"                                                    ```..-------------------------:::::---::---.....---:://:////+oo++oo+////::////////////::-..-.`.``                                                   ");
		System.out.println(
				"                                                    ````.-:-::-----------------:::::::---------......---:::://///oy++ooooo++/////////++++/:::-:-...``                                                   ");
		System.out.println(
				"                                                    ```.--:-:::::---:----:::::::///:::--------........--:///++//+sy+////+oooo++++++++++o+//:///:-..``                                                   ");
		System.out.println(
				"                                                    ```.---:::::::::::::::::////::::-::-::::::--..-----:/oooooooshy+/::///+oooooooooooooo+++//+/--..`                                                   ");
		System.out.println(
				"                                                    ``..--::::::::::::::::////::-----:////++oo+///////+oyhhddddddhs+///////++osoooooooso++oo+/+/:-..`                                                   ");
		System.out.println(
				"                                                     `.---:////////////////:::--------/oshhdddhysssssyhdmNNmmdhhys++///////+++ooooooosso+os+o++/:-..`                                                   ");
		System.out.println(
				"                                                     `..--///////////////::::::-------:/+oooosyhdddmmmNNNdhysooo++++///+++++++osoooooosoossoo++/:--.                                                    ");
		System.out.println(
				"                                                      ..-::///::://////::///:::::----::::/:///+osydmmmddhsooo++++++++++++oooossso+++ooo+ssosoo+/::-`                                                    ");
		System.out.println(
				"                                                      `.--::///:::///:://+//:///:::::::::::////+oosyhyyysoo++++oooooososyhhhyyso+//+o+++yssooo+/::`                                                     ");
		System.out.println(
				"                                                      `..-::://--://///////+++o++++//://///////++ssyssssooooooosssssyyhdmmmhyoo+//+++++osssso++//.                                                      ");
		System.out.println(
				"                                                       `.-::://:-:////////+osyhhyoo+++++///////+++++++++//+++oosyyhdmmmmhs+///////++++osoooooo+/-                                                       ");
		System.out.println(
				"                                                       ``.::://:-:://////++ososssyhhyyyssssosoo++//+/+++oosyhddmmddhhhso//::::////+oo+soooooo++-                                                        ");
		System.out.println(
				"                                                         `-:/:::--:////////////::/+osssyyyhhhhhhhdhhhhhhdddhhyyssosooss/:::::////+oooosoosooo+o:.                                                       ");
		System.out.println(
				"                                                         `.::::/:://///////:::::::://////++++/+++++oosssoo++++++oooosy+/::::///oosssssssssooosdMNms:                                                    ");
		System.out.println(
				"                                                          `.::///://///////::::::::::////:///////////++++++////++ooyy+//////++osssssssssssoo+dddMMMNd:                                                  ");
		System.out.println(
				"                                                           ..:/+/////++/////:::::::://////////////+++ooooososssyyhhy+//////+osssyssssyssssso+NNdNMMMNNy`                                                ");
		System.out.println(
				"                                                        `:s/.-://+++o+o+++//::::::::://+oooooooosssssysssssosyyyys+///////ooossssssssssyyys/dMNmNMMMMMNh.                                               ");
		System.out.println(
				"                                                       /dmsy:.-/++++++o+oo+++///:::::::///+ooooooosooo++++o+ooo+////:///+ooossoossyyyyyyys++NMNNNMMMMMNNy.                                              ");
		System.out.println(
				"                                                     .ymNdhdh.-/++o++++++o++o+////::::::::/://::/:////////////////::://+oooosssssyyyyyyhyy/hMMMMNMMMMMMNms.                                             ");
		System.out.println(
				"                                                    -dmNNddmm:-/ooooooo++ooo++++//::::::::---------------:::/::::::::/++oossyysyyhyyyhhhyo+NMMMMMMMMMMMMMhh:                                            ");
		System.out.println(
				"                                                   .dmNMmmdmmh.:+ossosss++oooo++//:::::----------------:::::::::::://+ooosyyyyhhhhhhhhdhsodMMMMMMMMMMMMMNNdh+`                                          ");
		System.out.println(
				"                                                  .dmNMNmmmmmN--/+osyssysoooooo++///:::--:------------::::/::::///+++osoosyyyhdhhhddddhyyhMMMMMMMMMMMMMMNNddso-                                         ");
		System.out.println(
				"                                                 `ymNMMNmmdmmNs.:/+oyyyyyysoooo+++///::::::--::--:::::::::/:::/+++ooosssyyyhdddddmmmmmddmNMMMMMMMMMMMMMMMMdmyo+/`                                       ");
		System.out.println(
				"                                                 omNMMNmmmdmmNm:-:/+ssyhyhyyysoooo+//////:::::::::/:::://///+/+oossyyhyyddddmmmmNNNNNmmmNMMMMNNMMMMMMMMMMNmdy/:oo`                                      ");
		System.out.println(
				"                                               `+mNMMMNNmmmmmNNs///++osyhdhhhhyssoooo+++/+///////:////+/++++o+ossyhhhdddmmmmmNNNNNNmmmmmMMMMNNMMMMMMMMMMMNNdy/+hmo.`                                    ");
		System.out.println(
				"                                               odNMMMMNNmmmmmNMy+/+/++osyhddddhddhyyyyosooo++o++++++++oooososyyhddmmmmmmmmNNNNNNNNmmmdmNMMMNNNMMMMMMMMMMMMMddoymmmyss+:.                                ");
		System.out.println(
				"                                             .smNMMMMMNNNmmmNNMd++/////++oyhdmmddmddddhhhyyssyysssssyyyyyhhhdmmNNNNNNNNNNMNNNNNmmdddddNMMMMNNNMMMMMMMMMMMMNmhyhmNmmhdddhy+:.`                           ");
		System.out.println(
				"                                       `.-.``ymNMMMMMNNNNmmmNNNms+///////++oyhdmmmmmmmmmmmmmmdmdddmmmmmmNNNNNMMMMMNNNNMMMMNNNNmmdhhhdmMMMMNNNMMMMMMMMMMMMMNNddhdNNNddNNmmmmhy+:.`                       ");
		System.out.println(
				"                                    .:oyyo:--mmMMMMMMNNNNNNmmNNNyo/////////++oyhddmNNNNNNNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNmmmdhhhhdmNMMMMNNMMMMMMMMMMMMMMMdmmhdNNNNmNNMNmmmNmmhyo/-.`                  ");
		System.out.println(
				"                                 ./ydmdyys::.yNNNMMMMNNNNNNNmNNNhs++/////////++oyhhdmmNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNmmddhhhhhddNNMMMNNNMMMMMMMMMMMMMmNmNmohNNNNNMNMMMNmmNNNmmdhyo+/:.``            ");
		System.out.println(
				"                            `.-/sdNNmdhmds+o`dNNNmNNMMNNNNNNNNNNdyo+///////////++osyhhdmmNNNMMMMMMMMMMMMMMMMMMMMMMMMNNNmmddhhhhhhhdmNMMMNNNMMMMMMMMMMMMMMNMmNy:smNNNNMMMMMMMNmNNNNNNNmdhyssso/-.`       ");
		System.out.println(
				"                       ``-:/+sdNNNNddmmmmdhs-hmNNNNMMMNNNNNNNNNNmyso++////////////+oosyhhhddmNNNNNMMMMMMMMMMMNNNNNNNmmddhhhyyhyhhdmmNMNMNNMMMMMMMMMMNMMMMMMmNh/shNNMMMMMMMMMMMNNNNMMNNNNNmhhhmmdyo/.`   ");
		System.out.println(
				"                  ``.--/ooshmNNNNmmmNmdhhddmNNMMMMMMMMMNNNNNNNNNmmyoo+///://://////++ooosyyhhhddddmmmmmmmmmmmmmmmmmddhhhhhhyyhyhddmNNNMNNNMMMMMMMMMMNMMMMNNyNm/smNNNMMMMMMMMMMMNNNNMMMMNNNNmmddmNNNmds+-");
		System.out.println(
				"              ``.:/+osyyhmNNNNNmmmNNdyhmNMMMMMMMMMMMMMMNNNNNNNNNNNmyoo+////::::::://++++o+osssyyhhhhhddhddddddddhhhhhhhhyyhyyyhhddmNNMNNNMMMMMMMMMMMMMMMMNNhMNsdMMMMMMMMMMMMMMMMMNNNNMMMMMMNNNmmmNNNNNNN");
		System.out.println(
				"         `.--::/oyhhsydNNNNNNNmmNNNNddNmNNMMMMMMMMMMMMMNNNNNNNNNNNNmho++///:::::::////+++++++oooosssyyyyyyyyhyyyyyyyyyyyyysyyyhhdNNNMMNMMMMMMMMMMMMMMMMMNNmmMMNNMMMMMMMMMMMMMMMMMMMNNNNMMMMMMMNNmmmNNNMM");
		System.out.println(
				"     `.:/++/+shdmhshmNNNNNNNmmNNNNNNNmmNNNMMMMMMMMMMMMMMMNNNNNNNNNNNNms++///::::/://////+++++++++oooooooossssyyysyssssssssssyyhdNNMNMNNMMMMMMMMMMMMMMMMMNMmNMMMMMMMMMMMMNMMMMMMMMMMMNNNNNMMMMMMMNNNNmNNN");
		System.out.println(
				" `.:+oooosydmmdyydNNNNNNNNNmNNNNNNNNNNdNNNMMMMMMMMMMMMMMMNNNNNNNNNNNNNmy++//:/:::://///////+/+//++++oo+ooooooooooooooooosossyhmNNMNMNNMMMMMMMMMMMMMMMMMNmNmMMMMMMMMMMMMMMNMMMMMMMMMMMNNNNNMMMMMMMMNNNNNN");
		System.out.println(
				"+ysoooshmmmmhyhmNNNNNNNNNmmNNNNNNNNNNNNmNmMMMMMMMMMMMMMMMNNNNNNNNNNNNNNNho/+//:::::::/:/://////////++/++/++++oo++oooooosoosydNMNMMMMNMMMMMMMMMMMMMMMMMNNNNdMMMMMMMMMMMMMMMNMMMMMMMMMMMMNNNNNMMMMMMMMNNNN");
		System.out.println(
				"soshdmmmmdyymNNNNNNNNNNNmNNNNNNNNNNNNNNNmNmMMMMMMMMMMMMMMNMMMMNNNNNNNNNNNds+///::::::::::/:///////////////+++++++++++++oosymNNNMNMNMMMMMMMMMMMMMMMMMMMMNNmdMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNMNMMMMMMMMMNN");
		System.out.println(
				"dmmmmmmdyhmNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNmNNMMMMMMMMMMMNMMMMMMNNNNNNNNNNNNNy+/////::::::::::::::////////////+//+++//++oooyNNNNMNMNMMMMMMMMMMMMMMMMMMMMMNNNdMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMNNMMMMMMMMMMMM ");

		System.out.println(
				"          _______                       _________ _        _            ______   _______      _______           _        _______  ______       ______           ");
		System.out.println(
				"|\\     /|(  ___  )|\\     /|    |\\     /|\\__   __/( \\      ( \\          (  ___ \\ (  ____ \\    (  ___  )|\\     /|( (    /|(  ____ \\(  __  \\     (  ___ \\ |\\     /|  ");
		System.out.println(
				"( \\   / )| (   ) || )   ( |    | )   ( |   ) (   | (      | (          | (   ) )| (    \\/    | (   ) || )   ( ||  \\  ( || (    \\/| (  \\  )    | (   ) )( \\   / ) ");
		System.out.println(
				" \\ (_) / | |   | || |   | |    | | _ | |   | |   | |      | |          | (__/ / | (__        | |   | || | _ | ||   \\ | || (__    | |   ) |    | (__/ /  \\ (_) /  ");
		System.out.println(
				"  \\   /  | |   | || |   | |    | |( )| |   | |   | |      | |          |  __ (  |  __)       | |   | || |( )| || (\\ \\) ||  __)   | |   | |    |  __ (    \\   /   ");
		System.out.println(
				"   ) (   | |   | || |   | |    | || || |   | |   | |      | |          | (  \\ \\ | (          | |   | || || || || | \\   || (      | |   ) |    | (  \\ \\    ) (    ");
		System.out.println(
				"   | |   | (___) || (___) |    | () () |___) (___| (____/\\| (____/\\    | )___) )| (____/\\    | (___) || () () || )  \\  || (____/\\| (__/  )    | )___) )   | |    ");
		System.out.println(
				"   \\_/   (_______)(_______)    (_______)\\_______/(_______/(_______/    |/ \\___/ (_______/    (_______)(_______)|/    )_)(_______/(______/     |/ \\___/    \\_/    ");
		System.out.println(
				"                                                                                                                                                                ");
		System.out.println(
				"                   _______  _______  _______ _________ _______          _______  _        ______         _        _______  _______ _________ _______            ");
		System.out.println(
				"                  (  ____ \\(  ___  )(  ____ \\\\__   __/(  ____ \\        (  ___  )( (    /|(  __  \\       ( \\      (  ___  )(  ____ \\\\__   __/(  ____ \\            ");
		System.out.println(
				"                  | (    \\/| (   ) || (    \\/   ) (   | (    \\/        | (   ) ||  \\  ( || (  \\  )      | (      | (   ) || (    \\/   ) (   | (    \\/            ");
		System.out.println(
				"                  | (__    | (___) || |         | |   | (_____         | (___) ||   \\ | || |   ) |      | |      | |   | || |         | |   | |                 ");
		System.out.println(
				"                  |  __)   |  ___  || |         | |   (_____  )        |  ___  || (\\ \\) || |   | |      | |      | |   | || | ____    | |   | |                 ");
		System.out.println(
				"                  | (      | (   ) || |         | |         ) |        | (   ) || | \\   || |   ) |      | |      | |   | || | \\_  )   | |   | |                 ");
		System.out.println(
				"                  | )      | )   ( || (____/\\   | |   /\\____) |        | )   ( || )  \\  || (__/  )      | (____/\\| (___) || (___) |___) (___| (____/\\           ");
		System.out.println(
				"                  |/       |/     \\|(_______/   )_(   \\_______)        |/     \\||/    )_)(______/       (_______/(_______)(_______)\\_______/(_______/           ");
	}

}