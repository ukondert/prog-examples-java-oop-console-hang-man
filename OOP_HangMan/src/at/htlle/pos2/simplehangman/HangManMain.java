package at.htlle.pos2.simplehangman;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class HangManMain {

	public static void main(String[] args) {
		
		HangMan hangMan = new HangMan(10);
		
		URL imageUrl = HangManMain.class.getResource("Hangman_100x133.jpg");
		
		createMessageDialogWithImage("<html><body><h1>Simple Hangman Game</h1></body></html>", "",JLabel.CENTER
				, imageUrl);
		
		
		String question = JOptionPane.showInputDialog("Bitte gegebe die Kategorie ein zu welcher das Wort gehört!");
		String answer = JOptionPane.showInputDialog("Bitte gebe das zu erratende Wort ein:");
		
		Question newQuestion = new Question(question, answer.toUpperCase(), question);
		
		hangMan.setQuestions(new Question[] {newQuestion});
		hangMan.selectQuestion(0);
		JOptionPane.showMessageDialog(null
				, "<html><body>Das gesuchte Wort kommt aus der Kategorie <br> <center><div style='font-size:16px;'>" + hangMan.getCurrentQuestion().getCategory() + "</div></center></body></html>"
				, "HangMan", JOptionPane.PLAIN_MESSAGE);
		
		
		do {
			String encodedAnswer = formatEncodedAnswer(hangMan.getEncodedAnswerChars());
			char inputChar = inputAlphanumericChar(
					"<html><body> " 
					+ " <h2 style='font-size:24px;text-align:center;'>" + encodedAnswer + "</h2>"
					+ "<p style='font-size:14px;text-align:center;'>Bereits verwendet Buchstaben: </p>" 
					+ "<p style='font-size:14px;text-align:center;color:blue;'>" + new String(hangMan.getUsedChars()) + "</p>" 
					+ "<h3 align='center'>Fehlversuche: " + hangMan.getFaildTrial() + " von erlaubten " + hangMan.getMaxFaildTrials() + "</h3>"
					+ "Welcher Buchstabe glaubst Du kommt in dem Wort vor?");
			
			boolean existChar = hangMan.setChar(inputChar);
			if (existChar) {
				JOptionPane.showMessageDialog(null, "Gratuliere der Buchstabe ist im Wort enthalten"
						, "HangMan", JOptionPane.PLAIN_MESSAGE);
			} else JOptionPane.showMessageDialog(null, "Buchstabe " + inputChar + " ist NICHT im Wort enthalten!", "HangMan",
					JOptionPane.PLAIN_MESSAGE);
		} while (!hangMan.isAnswerFound() && !hangMan.isGameOver());
		
		if (hangMan.isAnswerFound()) {
			JOptionPane.showMessageDialog(null, "<html><body><h1 style='font-size:32px;text-align:center'> Gratuliere!</h1>"
					+ "<p style='font-size:20px;text-align:center;'>Du hast das gesuchte Wort <br><strong style='color:green;'>" 
					+ hangMan.getAnswer() + "</strong><br>mit <strong style='color:green;'>" + hangMan.getFaildTrial() + "</strong> Fehlversuchen erraten</p>"
					+ "</body></html>", "HangMan", JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "<html><body><h1 style='font-size:32px' Leider verloren!</h1>"
					+ "<p style='font-size:20px;text-align:center;'>Das gesuchte Wort war <strong style='color:red;'>" + hangMan.getAnswer() + "</strong></p>"
					+ "</body></html>", "HangMan", JOptionPane.PLAIN_MESSAGE);
		}
		
		
	}
	
	/**
	 * Eingabe eines Buchstaben in einem Eingabedialog
	 * 
	 * @param message angezeigte Nachricht
	 * @return
	 */
	public static char inputAlphanumericChar(String message) {
		boolean validInput = false;
		char inputChar = 0;
		do {
			String input = JOptionPane.showInputDialog(message);
			input = input.toUpperCase();
			inputChar = input.charAt(0);
			if (!Character.isAlphabetic(inputChar)) {
				JOptionPane.showMessageDialog(null, "Es sind nur Buchstaben erlaubt!");
			} else validInput = true;

		} while (!validInput);
		
		return inputChar;
		
	}
	
	/**
	 * formatierte Darstellung des codierten Antwort
	 * 
	 * @param answerChars - codierte Antwortbuchstaben
	 * @return
	 */
	public static String formatEncodedAnswer(char[] answerChars) {
		String formatedString = "";
		for (int i = 0; i < answerChars.length - 1; i++) {
			formatedString += answerChars[i] + " ";
		}
		formatedString += answerChars[answerChars.length - 1];
		
		return formatedString;
	}
	
	/**
	 * Erzeugen des Nachrichtendialogs mit einem Bild
	 * 
	 * @param message - angezeigte Nachricht
	 * @param messageTitle - Titel des Nachrichtenfensters
	 * @param labelPosition - Position des Labels (mit Bild)
	 * @param imageUrl - URL des anzuzeigenden Bildes
	 */
	public static void createMessageDialogWithImage(String message, String messageTitle, int labelPosition, URL imageUrl) {
		ImageIcon icon = new ImageIcon(imageUrl);
		
		JOptionPane.showMessageDialog(null, new JLabel(message, icon, labelPosition)
				, messageTitle, JOptionPane.PLAIN_MESSAGE);
		
	}
	
}
