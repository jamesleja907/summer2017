package quiz_app;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
	
	/**
	 * A question object.
	 */
	private static final long serialVersionUID = 1482532405230307666L;
	
	/** The unique id of the Question. */
	private String id;
	
	/** The category of the Question. */
	private String category;
	
	/** The difficulty of the Question. */
	private int difficulty;
	
	/** The question of the Question. */
	private String question;
	
	/** The correct answer to the Question. */
	private String correctAnswer;
	
	/** A list of incorrect answers to the Question. */
	private ArrayList<String> incorrects;
	
	/**
	 * A new Question object.
	 */
	public Question(String category, String question, 
				String correctAnswer, int difficulty) {
		this.category = category;
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.difficulty = difficulty;
		// Check the QuestionLog to get the next id?
		this.incorrects = new ArrayList<String>();
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public ArrayList<String> getIncorrects() {
		return incorrects;
	}

	public void setIncorrects(ArrayList<String> incorrects) {
		this.incorrects = incorrects;
	}
	
	public void addIncorrect(String incorrect) {
		this.incorrects.add(incorrect);
	}

}
