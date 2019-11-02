package at.htlle.pos2.simplehangman;

/**
 * Startklasse für das Spiel Hangman. Es kann über einen Programmparameter, die maximale Anzahl an
 * Fehlversuchen für die Angabe eines Buchstabens der im Wort vorkommen muss, angegeben werden.
 * 
 * Defaultwert hierfür ist mit 10 festgelegt.
 * @author uweko
 *
 */
public class HangManMain {
	
	public static void main(String[] args) {
		// Defaultwert für die max. Anzahl an falschen Antworten
		int maxNumOfInvalidAnswers = 10;
		
		if (args.length >= 1) {
			try {
				maxNumOfInvalidAnswers = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.println("Parameter ist keine Zahl! - es wird der Defaultwert gesetzt: " + maxNumOfInvalidAnswers);
			}
		}
			
		HangManWithOptionPane hangManGame = new HangManWithOptionPane(maxNumOfInvalidAnswers);
		
		hangManGame.start();
	}

}
