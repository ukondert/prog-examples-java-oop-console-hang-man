package at.htlle.pos2.simplehangman;

public class Question {
	private String question;
	private String answer;
	private String category;
	
	public Question() {
		
	}
	
	public Question(String question, String answer, String category) {
		super();
		this.question = question;
		this.answer = answer;
		this.category = category;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
