package quiz_app;
//HAHA
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Question implements Serializable {

	/**
	 * A question object.
	 */
	private static final long serialVersionUID = 1482532405230307666L;

	/** The unique id of the Question. */
	// Never being used.
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
	 * 
	 * @param category
	 *            The category of the Question.
	 * @param question
	 *            The question.
	 * @param correctAnswer
	 *            The answer to the question.
	 * @param difficulty
	 *            The difficulty of the question.
	 */
	public Question(String category, String question, String correctAnswer, int difficulty) {
		this.category = category;
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.difficulty = difficulty;
		// Check the QuestionLog to get the next id?
		this.incorrects = new ArrayList<String>();
	}
	
	/** 
	 * @return the question string.
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * Set a new question.
	 * 
	 * @param question
	 * 			The new question. 
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	
	/**
	 * @return the Id of the question/
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Set a new id.
	 * 
	 * @param id
	 * 			The new id.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the category of the question.
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Set a new category
	 * 
	 * @param category
	 * 			The new category.
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * @return the difficulty of the question.
	 */
	public int getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Set a new difficulty.
	 * 
	 * @param difficulty
	 * 			The new difficulty of the question.
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	/**
	 * @return the correct answer to the question.
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	
	/**
	 * Set a new correct answer
	 * 
	 * @param correctAnswer
	 * 			The new correct answer.
	 */
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	/**
	 * @return the incorrect answers for the question.
	 */
	public ArrayList<String> getIncorrects() {
		return incorrects;
	}
	
	/**
	 * Set a new list of incorrect answers.
	 * 
	 * @param incorrects
	 * 			The ArrayList of new incorrect answers.
	 */
	public void setIncorrects(ArrayList<String> incorrects) {
		this.incorrects = incorrects;
	}
	
	/**
	 * Add an incorrect answer to the question
	 * 
	 * @param incorrect
	 * 			The incorrect answer to be added to the ArrayList.
	 */
	public void addIncorrect(String incorrect) {
		this.incorrects.add(incorrect);
	}
	
	/**
	 * Return if this question is equal to another.
	 * 
	 * @param o
	 * 			The object to compare this question to.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}
		Question q = (Question) o;
		return Objects.equals(question, q.question) && Objects.equals(correctAnswer, q.correctAnswer);
	}
}
