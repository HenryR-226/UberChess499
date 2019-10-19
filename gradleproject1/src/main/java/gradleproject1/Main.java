/*
 Uberchess current build : 0.00.01
*/

package gradleproject1;

/**
 *
 * @author Tyrone Lamar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("UberChess Chess Game Initialization");
        
       Board b=new Board();
           b.initBoard();
           b.initBlack();
           b.initWhite();
        
           
           b.draw();
           System.out.println(" ");
           System.out.println("White: Upper Case, first move:");
           System.out.println("Hint: Type grid square for first pawn move (no need to specify pawn):");
           
    }
    
    /**
     * Call when user starts a new AI game
     */
    
    public static void ownedByFactsAndLogic(){
         System.out.println("                                                               -+ydNNNNMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNNNho/.                                                                     "); System.out.println(
"                                                            .+hmNNNNMNNMMMMMMMMMMMMMMMMMMNNNNNNNNMMMMMMMMMMMMMMMMMMMMMMMMMNNNNNNNNNds:                                                                 "); System.out.println(
"                                                         -+hmNNNNNNMMMMMNNNNNNNNNNNNNNNNNNNNNMNMMMMNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNds-                                                              "); System.out.println(
"                                                       -smmNNNNNMMMNNMNmmNNNNNNNNNNNMMNMMNMMMMMMMMMMMMMMMNNNNNNNNNNMMMMNNNNNNNNNNNNNNNNNNy-                                                            "); System.out.println(
"                                                     -sdmmNNNNmmNNNNNNNMMMNNNNNNNNMMMMMMMMMMMMMMMMMMNNNNNMMMNMMMMMNNNNMMMNNNNMMMMMMNNNNNNNdo`                                                          "); System.out.println(
"                                                   `+dmNNmmmmdmNNNNMNNNNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNMMMMMMMMNNNNmh-                                                        "); System.out.println(
"                                                  .smmmmdmNNNNNNNNNMMMMNNNNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNNm+'                                                       "); System.out.println(
"                                                 /hmddNmNNNNNNMMNNNNNMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMNNNNd/.                                                      "); System.out.println(
"                                                /hddmNNNMNNMMNNMNNMMMMMMMMMMMMMMNNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNNNNNNmd+`                                                    "); System.out.println(
"                                              .+ddmmNNNMMNMMMNNNNNNMMMNNNMMMMMMMMNNNNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNMMMMMMMMMNNNMNmmy:                                                   "); System.out.println(
"                                            .+hhmmNNNNNMMNMNmNNNNNNMMMMNMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNNmdo.                                                 "); System.out.println(
"                                          `/ydmmNNNNNNMMMMMNNNMMMMNMMMNMMMMMMMMMMMMMNNNMMMMMMMMMMMMMNMMMMMMMMMMMMNNNMMMMMMMMMMMMMNMMMMMMMMMMMMMMNNNNNmd/                                                "); System.out.println(
"                                         :yhmmNNNNNNNMMMMMMNNNNNNNMNNNNMMMMMMMMMMMMMNNNNMMMMMNNNMMMNNNNMMMMMMMMMMMMMMMNNMMMMMMMMMMNNNMMMMMMMMMMMNMNNNNNm+                                               "); System.out.println(
"                                       `/hdmmmNNNNNNNNMMMNMNNNmNNNNNNNNMMNNMMMMMMMMMMMMNNNMMMMMMMMMMMNNNNNNNNNNMMNMNMMMMMMMMMMMMMNNNNNMMMMMMMMMMNMMNNNNNmo`                                             "); System.out.println(
"                                       ./dmmmNNNNNNNNNNNMMMNNNmmNNNNMMMMMMMMMMMMMNMMMNNMMMMMMNNMMMMNMNMMMMNNNMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMNMMMMMNMMNNNNNd+                                             "); System.out.println(
"                                       -hmmmNNNmmNNNNNNMNNNNNNNmNNNNMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMNNNNNMMMNNNNNNNd:`                                            "); System.out.println(
"                                      `+ddmmmNmNNNNNNNNNNNNNNNmmNNNNNNMMMNMMMMMMNMMMMMMMMMMMMMMMMMMMMMNNMMMMMMMNNNMMMMMMMMMMMMMMMMMMMMMMMMMNNNNNNNNNNNNNNN+                                             "); System.out.println(
"                                      `omdmmNNNNNNNNNNNmmNNmmmNNNNNNNNNMNMNNNNNNNNMNNNMNMMMMMMMMMNNMMMMMNMMNNMNMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNNMMNNNNNNNNNmd`                                            "); System.out.println(
"                                      `ohmNNNNNNNNNNNNNNmmmmmmmmmmmmNNNNNNNNmmmmmmmmmNmNNNNNNNNNNNMNNMNNNNNNMMMMMMMMMMMMMMMMMNNNNNNNNNNNNNNMMMMMMNNNNMNNNNmo                                            "); System.out.println(
"                                       /dmNNNNNNNNNNNNNmmmmmmdmmmmdddmmNmmmmmmhhhhdhhhdmmmNmNNNNNNmmNmmmNNNNNMMMMMMMMMMMMMMNNNNNNNNNNNNNMMNNNNMMMMMMNNMMMNNd:                                           "); System.out.println(
"                                      `sdNNNNNNNMMMNNmNmmmmdddhhyyyyyyyhddddhhysooossyyyhhdhhddddddddddmmdmNmmNNNNNMMMMMMMMMMMMMMNNNNNNNNMMMNMMMMMMMNMNMMMNNh`                                          "); System.out.println(
"                                      `+dNNNNNMNNNNNNmmmmddhyso+++++++osyyyyhyso+///:+/+ossssyyhhhhhhdhyyyhyhhdmmNNNNNNNNNNNNNNNNNNNNNNNNNNMNMNMMMMMMMMMMMMNm/                                          "); System.out.println(
"                                       /hmmNNNNNNNNmmmdhyyso+/::::::::::/+++o+//:::-----://++oosssssoo+++++osyyhhhdddddddddddmddmmmmmmmmmmmNNNNMMMMMMMMMMMNMNs`                                         "); System.out.println(
"                                       -hddmNNNNNmmddhyso++//:::--------::::::----..........--::-:::::::---:://++++++++++oooosssyyyhhddddddddmNNMMMMMMMMMMNNms.                                         "); System.out.println(
"                                       :syhmmNmmmdhyso++///:::----------.....................................------::::::::////+++oosssyyyyyyhhmNNMMMMMMMMMNm+                                          "); System.out.println(
"                                       -soydmmmmdhsoo+////:::--------.....................``````.................----------::::////++ssossssssyhmNMMMMMMMMNNm+                                          "); System.out.println(
"                                       `ossddmmdhyso++///::::--------.....................`````````..............-.---------:::::////++++ooooosyhmNNMMMMNNNmm:                                          "); System.out.println(
"                                       `/+syhddhhyo++//:::::---------....................````..```...................-------::::::::////+++oooosydmNMMMMNNmmm:                                          "); System.out.println(
"                                        -/ssdhhyyss++/:::::--------.-.....................```..````.................--------::::::::::///++++oosydmmNMNNNNmmh-                                          "); System.out.println(
"                                         :+syhsysoo+//:::::-------..-...................`````.......................--------::::::::::://///++osyhmmNNNNNmmms`                                          "); System.out.println(
"                                         ./+sosso++///::::------.----............................................----------:::-:::::::://///++osyhddNmNmmmmdy`                                          "); System.out.println(
"                                         `//ooo+++////:::---------..............................................---.------::::::::::::://////++oyhhdmmmNmhmmd-                                          "); System.out.println(
"                                         -::+/++/////::::----------............................................---------------::::::::////////++syhdmmmmhydmmy`                                         "); System.out.println(
"                                         :///-/:/://::::----------------.......................................------------::::::::::://///////+/syhddmdhhmmNm+                                         "); System.out.println(
"                                         /+oo.:-://:--------------------.....................................-------------::::::::::/://////////:oshydhhddmNmd:                                         "); System.out.println(
"                                         :yso:-``-:-`--------------------...................................----------::-:::::::::://////////::--:+oohyhdmmNNdy/-`                                      "); System.out.println(
"                                    ``..`:yyso:  ``.`.--------------------..................................------------:--::::::::////////:::-.---./yhhdmmmNmy+:`                                      "); System.out.println(
"                                    `---:ooyso/   ```..----------------------...............................--------------::::::::::::////::--.-..`.:yhddmdNmhs:./`                                     "); System.out.println(
"                                     `--:+ssoo/    ```..--------------------................................-..---------::::::::::::////:/::-.....-.:ydhdydmdy/:yh.                                     "); System.out.println(
"                                    -..:::ososo`    ``...----------.-----....................................------------::::::::::::////:::-...-.---ydhyydsso+hhs`                                     "); System.out.println(
"                                   `:/:-::/ooo+`    ```..-------..--.............................................--------:::::::::::::////::--..---:-sdhhho/oo+hy+                                      "); System.out.println(
"                                   `-:--:--+o+/.    ```...----------.-.....--................................-----------:---::::::///:////:::-----:::shhy+:ohs/ss:                                      "); System.out.println(
"                                    .-.-----+o+`     ``..------:::------------......................--.....--------------:::::://+ooso+++++//:---:--:ohh+:+yso//+.                                      "); System.out.println(
"                                    ``.-----:+/`    `.--:////+osso++/::::--------.............-........---------------:::://+oosyhhhdddhyyyso+/---.--/yy/sdyso+::                                       "); System.out.println(
"                                     `.-:://:/:     ``:/syyhhhddmddhyysoo+/:::::::-----------------.-------:---:-:--:///+osyhdmmmmNmmmmNmmdhyo/::-.--:s+sddyso+/.                                       "); System.out.println(
"                                     `--://++:-  `` ```:+oyyyyhdmmmmmmmmdhhhhhyyyso+oo+/::::-----------:::::/:::////+oyhhdmNNNNNMNNNNNNmmdhys+:::-...-/ohhyyso//-                                       "); System.out.println(
"                                     .--::::::.  `````.-:+osyyyyhhdmmNNNNNNNNNNNmmmmddhyso+/:::::::::::::/o+oosyhhdmmmNNNNNMMMMNNNmmddhhyssso+/:--....:+ssoo+//:.                                       "); System.out.println(
"                                     ..---...-`   ``..--:/+++oooossyyhdmNNNNNNNNNNNNmmmdhhy++/:::::::///+oshdmmNNNNNNMNMMMMMNNNmmdddddhysssss+/:--....-o++o+++/:.                                       "); System.out.println(
"                                     `.---...-`   ``---://++osyhhhhhhdmmNNNNMMMNNNNmmmmmdhyso+//:::://+osyhdmmNNNNMMMMMMMMMMMMMNNNNmddmmdhysso+/--...`-so/+oo+/-                                        "); System.out.println(
"                                      ..---..-`    `.-::/++osyhhddmmNmmNNNMNMMMNNNNNmmddhhyso+///:://++oshdmmNNNNMMMMNNMMMMMMNNmNNNNNNmdhsso++/:--..``.oo/oo/::`                                        "); System.out.println(
"                                       `..-..-`    `.--::::///oyhddhhysdNNNNNNdsshmmddhyyyyso+/:::--://oshdddddNNNNmmdhhyyyhhddddddhyhhso+++////:-..``./s+o+/-.                                         "); System.out.println(
"                                        `.-.--     `----:/::::/+oooossoosssoyoo+osyyysyo//++/::---.---:+osyo++sssossssoossssssssssssyyso+++/////:-.````:s+s+/.                                          "); System.out.println(
"                                        `..-.-     `.----:::::::///////++ooooooo+ooo//::::://:--....--:/+oo+/+////++o+oooooooossossoooo+++//////:-.````:o++/:`                                          "); System.out.println(
"                                         `...-     `----------:::::::::///////////////::::::::--....---:/oo+/////////////++++++++++////::/:////::.`````.+o//-                                           "); System.out.println(
"                                          ..-.     `.------------------:::::::::::::::::::::::-......--:/+++////::::::::::::::::::::::::::::///:-.`````.///:`                                           "); System.out.println(
"                                          `...     `.------------------------------------:::---......--://++////:::-----::::----:--::::::://///:-.``` `.+//-`                                           "); System.out.println(
"                                          `...      `.-----------......-------.----------:::--.......--:/+++////::-----------------:::::::::::/-.```` `.o+:`                                            "); System.out.println(
"                                           ...      `.-----------.......-.......--------::----......---://+++///:::----------------:::::::::/::-.````  :o:`                                             "); System.out.println(
"                                           ``.`     ``.------------.............----------:---......---://///////:::--------------:-::::::::::-..````  /+`                                              "); System.out.println(
"                                             ``     ``.---------.---.......-....-------------........--:://///////::::-------------:::::::::::-...```  /.                                               "); System.out.println(
"                                              `      ``.---------------..---.----------------........--::////++++++//::::--------:::::::///:-.....```  `                                                "); System.out.println(
"                                                    ```..---------------------------::::------.......--::://///++o+++///::::::::::::://///::-....````                                                   "); System.out.println(
"                                                    ```..-------------------------:::::---::---.....---:://:////+oo++oo+////::////////////::-..-.`.``                                                   "); System.out.println(
"                                                    ````.-:-::-----------------:::::::---------......---:::://///oy++ooooo++/////////++++/:::-:-...``                                                   "); System.out.println(
"                                                    ```.--:-:::::---:----:::::::///:::--------........--:///++//+sy+////+oooo++++++++++o+//:///:-..``                                                   "); System.out.println(
"                                                    ```.---:::::::::::::::::////::::-::-::::::--..-----:/oooooooshy+/::///+oooooooooooooo+++//+/--..`                                                   "); System.out.println(
"                                                    ``..--::::::::::::::::////::-----:////++oo+///////+oyhhddddddhs+///////++osoooooooso++oo+/+/:-..`                                                   "); System.out.println(
"                                                     `.---:////////////////:::--------/oshhdddhysssssyhdmNNmmdhhys++///////+++ooooooosso+os+o++/:-..`                                                   "); System.out.println(
"                                                     `..--///////////////::::::-------:/+oooosyhdddmmmNNNdhysooo++++///+++++++osoooooosoossoo++/:--.                                                    "); System.out.println(
"                                                      ..-::///::://////::///:::::----::::/:///+osydmmmddhsooo++++++++++++oooossso+++ooo+ssosoo+/::-`                                                    "); System.out.println(
"                                                      `.--::///:::///:://+//:///:::::::::::////+oosyhyyysoo++++oooooososyhhhyyso+//+o+++yssooo+/::`                                                     "); System.out.println(
"                                                      `..-::://--://///////+++o++++//://///////++ssyssssooooooosssssyyhdmmmhyoo+//+++++osssso++//.                                                      "); System.out.println(
"                                                       `.-::://:-:////////+osyhhyoo+++++///////+++++++++//+++oosyyhdmmmmhs+///////++++osoooooo+/-                                                       "); System.out.println(
"                                                       ``.::://:-:://////++ososssyhhyyyssssosoo++//+/+++oosyhddmmddhhhso//::::////+oo+soooooo++-                                                        "); System.out.println(
"                                                         `-:/:::--:////////////::/+osssyyyhhhhhhhdhhhhhhdddhhyyssosooss/:::::////+oooosoosooo+o:.                                                       "); System.out.println(
"                                                         `.::::/:://///////:::::::://////++++/+++++oosssoo++++++oooosy+/::::///oosssssssssooosdMNms:                                                    "); System.out.println(
"                                                          `.::///://///////::::::::::////:///////////++++++////++ooyy+//////++osssssssssssoo+dddMMMNd:                                                  "); System.out.println(
"                                                           ..:/+/////++/////:::::::://////////////+++ooooososssyyhhy+//////+osssyssssyssssso+NNdNMMMNNy`                                                "); System.out.println(
"                                                        `:s/.-://+++o+o+++//::::::::://+oooooooosssssysssssosyyyys+///////ooossssssssssyyys/dMNmNMMMMMNh.                                               "); System.out.println(
"                                                       /dmsy:.-/++++++o+oo+++///:::::::///+ooooooosooo++++o+ooo+////:///+ooossoossyyyyyyys++NMNNNMMMMMNNy.                                              "); System.out.println(
"                                                     .ymNdhdh.-/++o++++++o++o+////::::::::/://::/:////////////////::://+oooosssssyyyyyyhyy/hMMMMNMMMMMMNms.                                             "); System.out.println(
"                                                    -dmNNddmm:-/ooooooo++ooo++++//::::::::---------------:::/::::::::/++oossyysyyhyyyhhhyo+NMMMMMMMMMMMMMhh:                                            "); System.out.println(
"                                                   .dmNMmmdmmh.:+ossosss++oooo++//:::::----------------:::::::::::://+ooosyyyyhhhhhhhhdhsodMMMMMMMMMMMMMNNdh+`                                          "); System.out.println(
"                                                  .dmNMNmmmmmN--/+osyssysoooooo++///:::--:------------::::/::::///+++osoosyyyhdhhhddddhyyhMMMMMMMMMMMMMMNNddso-                                         "); System.out.println(
"                                                 `ymNMMNmmdmmNs.:/+oyyyyyysoooo+++///::::::--::--:::::::::/:::/+++ooosssyyyhdddddmmmmmddmNMMMMMMMMMMMMMMMMdmyo+/`                                       "); System.out.println(
"                                                 omNMMNmmmdmmNm:-:/+ssyhyhyyysoooo+//////:::::::::/:::://///+/+oossyyhyyddddmmmmNNNNNmmmNMMMMNNMMMMMMMMMMNmdy/:oo`                                      "); System.out.println(
"                                               `+mNMMMNNmmmmmNNs///++osyhdhhhhyssoooo+++/+///////:////+/++++o+ossyhhhdddmmmmmNNNNNNmmmmmMMMMNNMMMMMMMMMMMNNdy/+hmo.`                                    "); System.out.println(
"                                               odNMMMMNNmmmmmNMy+/+/++osyhddddhddhyyyyosooo++o++++++++oooososyyhddmmmmmmmmNNNNNNNNmmmdmNMMMNNNMMMMMMMMMMMMMddoymmmyss+:.                                "); System.out.println(
"                                             .smNMMMMMNNNmmmNNMd++/////++oyhdmmddmddddhhhyyssyysssssyyyyyhhhdmmNNNNNNNNNNMNNNNNmmdddddNMMMMNNNMMMMMMMMMMMMNmhyhmNmmhdddhy+:.`                           "); System.out.println(
"                                       `.-.``ymNMMMMMNNNNmmmNNNms+///////++oyhdmmmmmmmmmmmmmmdmdddmmmmmmNNNNNMMMMMNNNNMMMMNNNNmmdhhhdmMMMMNNNMMMMMMMMMMMMMNNddhdNNNddNNmmmmhy+:.`                       "); System.out.println(
"                                    .:oyyo:--mmMMMMMMNNNNNNmmNNNyo/////////++oyhddmNNNNNNNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNmmmdhhhhdmNMMMMNNMMMMMMMMMMMMMMMdmmhdNNNNmNNMNmmmNmmhyo/-.`                  "); System.out.println(
"                                 ./ydmdyys::.yNNNMMMMNNNNNNNmNNNhs++/////////++oyhhdmmNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNmmddhhhhhddNNMMMNNNMMMMMMMMMMMMMmNmNmohNNNNNMNMMMNmmNNNmmdhyo+/:.``            "); System.out.println(
"                            `.-/sdNNmdhmds+o`dNNNmNNMMNNNNNNNNNNdyo+///////////++osyhhdmmNNNMMMMMMMMMMMMMMMMMMMMMMMMNNNmmddhhhhhhhdmNMMMNNNMMMMMMMMMMMMMMNMmNy:smNNNNMMMMMMMNmNNNNNNNmdhyssso/-.`       "); System.out.println(
"                       ``-:/+sdNNNNddmmmmdhs-hmNNNNMMMNNNNNNNNNNmyso++////////////+oosyhhhddmNNNNNMMMMMMMMMMMNNNNNNNmmddhhhyyhyhhdmmNMNMNNMMMMMMMMMMNMMMMMMmNh/shNNMMMMMMMMMMMNNNNMMNNNNNmhhhmmdyo/.`   "); System.out.println(
"                  ``.--/ooshmNNNNmmmNmdhhddmNNMMMMMMMMMNNNNNNNNNmmyoo+///://://////++ooosyyhhhddddmmmmmmmmmmmmmmmmmddhhhhhhyyhyhddmNNNMNNNMMMMMMMMMMNMMMMNNyNm/smNNNMMMMMMMMMMMNNNNMMMMNNNNmmddmNNNmds+-"); System.out.println(
"              ``.:/+osyyhmNNNNNmmmNNdyhmNMMMMMMMMMMMMMMNNNNNNNNNNNmyoo+////::::::://++++o+osssyyhhhhhddhddddddddhhhhhhhhyyhyyyhhddmNNMNNNMMMMMMMMMMMMMMMMNNhMNsdMMMMMMMMMMMMMMMMMNNNNMMMMMMNNNmmmNNNNNNN"); System.out.println(
"         `.--::/oyhhsydNNNNNNNmmNNNNddNmNNMMMMMMMMMMMMMNNNNNNNNNNNNmho++///:::::::////+++++++oooosssyyyyyyyyhyyyyyyyyyyyyysyyyhhdNNNMMNMMMMMMMMMMMMMMMMMNNmmMMNNMMMMMMMMMMMMMMMMMMMNNNNMMMMMMMNNmmmNNNMM"); System.out.println(
"     `.:/++/+shdmhshmNNNNNNNmmNNNNNNNmmNNNMMMMMMMMMMMMMMMNNNNNNNNNNNNms++///::::/://////+++++++++oooooooossssyyysyssssssssssyyhdNNMNMNNMMMMMMMMMMMMMMMMMNMmNMMMMMMMMMMMMNMMMMMMMMMMMNNNNNMMMMMMMNNNNmNNN"); System.out.println(
" `.:+oooosydmmdyydNNNNNNNNNmNNNNNNNNNNdNNNMMMMMMMMMMMMMMMNNNNNNNNNNNNNmy++//:/:::://///////+/+//++++oo+ooooooooooooooooosossyhmNNMNMNNMMMMMMMMMMMMMMMMMNmNmMMMMMMMMMMMMMMNMMMMMMMMMMMNNNNNMMMMMMMMNNNNNN"); System.out.println(
"+ysoooshmmmmhyhmNNNNNNNNNmmNNNNNNNNNNNNmNmMMMMMMMMMMMMMMMNNNNNNNNNNNNNNNho/+//:::::::/:/://////////++/++/++++oo++oooooosoosydNMNMMMMNMMMMMMMMMMMMMMMMMNNNNdMMMMMMMMMMMMMMMNMMMMMMMMMMMMNNNNNMMMMMMMMNNNN"); System.out.println(
"soshdmmmmdyymNNNNNNNNNNNmNNNNNNNNNNNNNNNmNmMMMMMMMMMMMMMMNMMMMNNNNNNNNNNNds+///::::::::::/:///////////////+++++++++++++oosymNNNMNMNMMMMMMMMMMMMMMMMMMMMNNmdMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNMNMMMMMMMMMNN"); System.out.println(
"dmmmmmmdyhmNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNmNNMMMMMMMMMMMNMMMMMMNNNNNNNNNNNNNy+/////::::::::::::::////////////+//+++//++oooyNNNNMNMNMMMMMMMMMMMMMMMMMMMMMNNNdMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMNNMMMMMMMMMMMM ");

 
System.out.println("          _______                       _________ _        _            ______   _______      _______           _        _______  ______       ______           ");
System.out.println("|\\     /|(  ___  )|\\     /|    |\\     /|\\__   __/( \\      ( \\          (  ___ \\ (  ____ \\    (  ___  )|\\     /|( (    /|(  ____ \\(  __  \\     (  ___ \\ |\\     /|  ");
System.out.println("( \\   / )| (   ) || )   ( |    | )   ( |   ) (   | (      | (          | (   ) )| (    \\/    | (   ) || )   ( ||  \\  ( || (    \\/| (  \\  )    | (   ) )( \\   / ) ");
System.out.println(" \\ (_) / | |   | || |   | |    | | _ | |   | |   | |      | |          | (__/ / | (__        | |   | || | _ | ||   \\ | || (__    | |   ) |    | (__/ /  \\ (_) /  ");
System.out.println("  \\   /  | |   | || |   | |    | |( )| |   | |   | |      | |          |  __ (  |  __)       | |   | || |( )| || (\\ \\) ||  __)   | |   | |    |  __ (    \\   /   ");
System.out.println("   ) (   | |   | || |   | |    | || || |   | |   | |      | |          | (  \\ \\ | (          | |   | || || || || | \\   || (      | |   ) |    | (  \\ \\    ) (    ");
System.out.println("   | |   | (___) || (___) |    | () () |___) (___| (____/\\| (____/\\    | )___) )| (____/\\    | (___) || () () || )  \\  || (____/\\| (__/  )    | )___) )   | |    ");
System.out.println("   \\_/   (_______)(_______)    (_______)\\_______/(_______/(_______/    |/ \\___/ (_______/    (_______)(_______)|/    )_)(_______/(______/     |/ \\___/    \\_/    ");
System.out.println("                                                                                                                                                                ");
System.out.println("                   _______  _______  _______ _________ _______          _______  _        ______         _        _______  _______ _________ _______            ");
System.out.println("                  (  ____ \\(  ___  )(  ____ \\\\__   __/(  ____ \\        (  ___  )( (    /|(  __  \\       ( \\      (  ___  )(  ____ \\\\__   __/(  ____ \\            ");
System.out.println("                  | (    \\/| (   ) || (    \\/   ) (   | (    \\/        | (   ) ||  \\  ( || (  \\  )      | (      | (   ) || (    \\/   ) (   | (    \\/            ");
System.out.println("                  | (__    | (___) || |         | |   | (_____         | (___) ||   \\ | || |   ) |      | |      | |   | || |         | |   | |                 ");
System.out.println("                  |  __)   |  ___  || |         | |   (_____  )        |  ___  || (\\ \\) || |   | |      | |      | |   | || | ____    | |   | |                 ");
System.out.println("                  | (      | (   ) || |         | |         ) |        | (   ) || | \\   || |   ) |      | |      | |   | || | \\_  )   | |   | |                 ");
System.out.println("                  | )      | )   ( || (____/\\   | |   /\\____) |        | )   ( || )  \\  || (__/  )      | (____/\\| (___) || (___) |___) (___| (____/\\           ");
System.out.println("                  |/       |/     \\|(_______/   )_(   \\_______)        |/     \\||/    )_)(______/       (_______/(_______)(_______)\\_______/(_______/           "); 
    }
    
}

