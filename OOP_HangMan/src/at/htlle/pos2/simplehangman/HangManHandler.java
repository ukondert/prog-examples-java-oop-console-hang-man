package at.htlle.pos2.simplehangman;

import at.htlle.pos2.inpututils.InputValidationUtil;

/**
 * Klasse HangManHandler dient zur Steuerung/Durchführung des Spiels. 
 * 
 * Die direkte Interaktion mit dem Benutzer/Spieler erfolgt NICHT mit dieser Klasse -> Keine
 * Implementierung des User Interfaces (= UI) in dieser Klasse
 * 
 * @author uweko
 *
 */
public class HangManHandler {
	
	// Fragen
	private Question[] questions;
	
	// gesuchte Antwort mit "codierten" Characters
	private char[] encodedAnswerChars;
	
	// max. Anzahl an Fehlversuchen
	private int maxfailedTrials;
	
	// aktuelle Anzahl an Fehlversuchen
	private int faildTrial;
	
	// Antwort gefunden?
	private boolean answerFound;
	
	// Spiel beendet?
	private boolean gameOver;
	
	// gesuchte Antwort
	private String answer;
	
	// gesuchte Buchstaben der Antwort
	private char[] answerChars;
	
	// aktuelle Frage
	private Question currentQuestion;
	
	// bereits verwendete Buchstaben
	private char[] usedChars;
	
	// Anzahl an bereits verwendeten Buchstaben
	private int usedCharCounter;
	
	// Alle Buchstaben des Alphabets (inkl. Umlaute)
	private char[] alpahbeticChars;
	
	/**
	 * Erstellen des HangMan-Game Objekts mit der Angabe der max. Anzahl an verlaubten Fehlversuchen
	 * (= Eingabe eines nicht im gesuchten Wort vorkommenden Buchstabens)
	 * 
	 * @param maxFaildTials - max. Anzahl an Fehlversuchen 
	 */
	public HangManHandler(int maxFaildTials) {
		
		this.maxfailedTrials = maxFaildTials;
		String abcString = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÜÖß";
		alpahbeticChars = abcString.toCharArray();
		usedChars = new char[alpahbeticChars.length];
		
	}
	
	/**
	 * Auswahl der Frage, die der Spieler beantworten muss
	 * 
	 * @param id
	 * @return 0 - Fragenauswahl erfolgreich
	 *         1 - id zu groß
	 */
	public int selectQuestion(int id) {
		int errCode = InputValidationUtil.valueBetween(id, 0, questions.length);
		if (errCode == 0) {
			answerFound = false;
			currentQuestion = questions[id];
			answer = currentQuestion.getAnswer();
			answerChars = answer.toCharArray();
			faildTrial=0;
			usedCharCounter = 0;
			prepareEncodedAnswerChars();
		}
		return errCode;
	}
	
	/**
	 * Setzen des Buchstabens, der in der gesuchten Antwort vorkommen soll. Ist dieser
	 * Buchstabe enthalten, so wird er an der/den Stelle/n in der codierten Antwort
	 * eingefügt (= encodedAnswerChars)
	 * 
	 * @param selectedChar - gesetzter Buchstabe
	 * @return false - Buchstabe kommt NICHT in der Antwort vor
	 *         true  - Buchstabe ist in Antwort enthalten
	 */
	public boolean setChar(char selectedChar) {
		this.usedChars[this.usedCharCounter] = selectedChar;
		this.usedCharCounter++;
		
		boolean charExists = false;
		for (int i = 0; i < answerChars.length; i++) {
			if (selectedChar == answerChars[i]) {
				encodedAnswerChars[i] = selectedChar;
				charExists = true;
			}
		}
		if (!charExists) faildTrial++;
		
		String encodedAnswer = new String(encodedAnswerChars);
		if (encodedAnswer.equalsIgnoreCase(answer)) answerFound = true;
		else if (faildTrial >= maxfailedTrials) gameOver = true;
		
		return charExists;
	}
	
	/**
	 * Erstellen des kodierten char-Arrays bestehend aus Bindestrichen.
	 * Die Länge des Arrays entspricht der Länge der gesuchten Antwort 
	 */
	private void prepareEncodedAnswerChars() {
		encodedAnswerChars = new char[answer.length()];
		
		for (int i = 0; i < answer.length(); i++) {
			encodedAnswerChars[i] = '_';
		}
	}


	
	/* -----------------------------
	 * Getter- und Setter - Methoden
	 * -----------------------------
	 */
	
	/**
	 * Rückgabe aller Fragen des Spiels
	 * @return
	 */
	public Question[] getQuestions() {
		return questions;
	}


	/**
	 * Setzen der Fragen für das Spiel
	 * @param questions
	 */
	public void setQuestions(Question[] questions) {
		this.questions = questions;
	}


	/**
	 * Rückgabe der codierten Buchstaben der gesuchten Antwort
	 * @return
	 */
	public char[] getEncodedAnswerChars() {
		return encodedAnswerChars;
	}

	
	/**
	 * Rückgabe der Anzahl an Fehlversuchen  (= Eingabe eines nicht im gesuchten Wort vorkommenden Buchstabens)
	 * @return
	 */
	public int getFaildTrial() {
		return faildTrial;
	}


	/**
	 * Rückgabe der gesuchten Antwort
	 * @return
	 */
	public String getAnswer() {
		return answer;
	}


	/**
	 * Rückgabe der aktuellen Frage (= hier Kategorie)
	 * @return
	 */
	public Question getCurrentQuestion() {
		return currentQuestion;
	}


	/**
	 * Setzen der Anzahl der maximalen Anzahl an Fehlversuchen
	 * 
	 * @param maxTrials
	 */
	public void setMaxFaildTrials(int maxTrials) {
		this.maxfailedTrials = maxTrials;
	}
	
	/**
	 * Rückgabe der Anzahl der maximalen Anzahl an Fehlversuchen
	 * @return
	 */
	public int getMaxFaildTrials() {
		return this.maxfailedTrials;
	}

	/**
	 * Wurde die gesuchte Antwort gefunden
	 * 
	 * @return
	 */
	public boolean isAnswerFound() {
		return answerFound;
	}

	/**
	 * Ist das Spiel beendet (Anwort gefunden oder max. Anzahl an Fehlversuchen erreicht)
	 * @return
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * Rückgabe aller bereits (vom Spieler) verwendeten Buchstaben
	 * @return
	 */
	public char[] getUsedChars() {
		return usedChars;
	}
	
	
}
