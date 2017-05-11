package quiz_app;

import java.util.HashMap;

/**
 * The Class for player in the app.
 */
public class Player extends Account {

	/** The serializing id */
	private static final long serialVersionUID = 2671901295270695198L;

	/** The Player's name. */
	private String name;

	/** The unique login ID. */
	private String login;

	/** The password for login. */
	private String password;

	/** The total score of all questions this player had answered. */
	private Integer total_score;

	/** The total correct score of all questions this player has answered. */
	private Integer total_correct;

	/** Scores the player get for different categories. */
	private HashMap<String, Integer> category_scores;

	/** Player's avatar. This should be an address to a local file. */
	private String avator;

	/** Security question for changing password. */
	private String question;

	/** The answer to the security question. */
	private String answer;

	/**
	 * Create a player with name, password, total score, total correct scores,
	 * and scores in each category.
	 * 
	 * @param name
	 *            the name of this Player
	 * @param password
	 *            the password for login
	 * @param login
	 *            the user id
	 * @param avator
	 *            player's avatar
	 */
	public Player(String name, String login, String password, String avator) {
		super(login, password);
		this.name = name;
		this.total_score = 0;
		this.total_correct = 0;
		this.category_scores = new HashMap<String, Integer>();
		this.avator = avator;
		this.question = null;
		this.answer = null;
	}

	/**
	 * @return the Player's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Update the player's name.
	 * 
	 * @param name
	 *            the Player's new name
	 */
	public void setName(String name) {
		this.name = name;
	}

//	/**
//	 * @return the player's string login id.
//	 */
//	public String getLogin() {
//		return this.login;
//	}

	/**
	 * Set or reset the player's password if the player pass the "old password"
	 * check.
	 * 
	 * @param old_password
	 *            the player's current password
	 * @param new_password
	 *            the new password
	 */
	public void setPasswordP(String old_password, String new_password) {
		// A user can only change the password if he/she can correctly provide
		// the old password.
		if (this.password.equals(old_password)) {
			this.password = new_password;
		}
	}

	/**
	 * Set or reset the player's password if the player can answer the security
	 * question.
	 * 
	 * @param input_answer
	 *            the player's input
	 * @param new_password
	 *            the new password
	 */
	public void setPasswordQ(String input_answer, String new_password) {
		if (this.answer.equals(input_answer)) {
			this.password = new_password;
		}
	}

	/**
	 * @return the total scores of all questions the player answered.
	 */
	public int getTotal_score() {
		return total_score;
	}

	/**
	 * Update the total score.
	 * 
	 * @param total_score
	 *            the new total score
	 */
	public void setTotal_score(Integer total_score) {
		this.total_score = total_score;
	}

	/**
	 * @return the total correct score the player get.
	 */
	public Integer getTotal_correct() {
		return total_correct;
	}

	/**
	 * Update the total correct score.
	 * 
	 * @param total_correct
	 *            the new total correct score
	 */
	public void setTotal_correct(Integer total_correct) {
		this.total_correct = total_correct;
	}

	/**
	 * @return the correctness rate of this user.
	 */
	public Double getCorrect_Average() {
		double result = this.total_correct / this.total_score;
		return result;
	}

	/**
	 * @return the collection of all scores the player get in different
	 *         category.
	 */
	public HashMap<String, Integer> getCatgory_scores() {
		return category_scores;
	}

	/**
	 * Update the score in the category.
	 * 
	 * @param category
	 *            the category that its score need to be updated
	 * 
	 * @param addedScore
	 *            added scores
	 */
	public void updateCatgory_scores(String category, Integer addedScore) {
		if (this.category_scores.containsKey(category)) {
			this.category_scores.put(category, this.category_scores.get(category) + addedScore);
		}
	}

	/**
	 * @return the Player's avatar.
	 */
	public String getAvator() {
		return avator;
	}

	/**
	 * Update the Player's avatar
	 * 
	 * @param avator
	 *            the local address of file avatar
	 */
	public void setAvator(String avator) {
		this.avator = avator;
	}

	/**
	 * @return the player's security question.
	 */

	public String getQuestion() {
		return question;
	}

	/**
	 * Update the player's security question.
	 * 
	 * @param new_question
	 *            the new security question to be set
	 * 
	 * @param old_answer
	 *            the answer to the current security question
	 */
	public void setQuestion(String new_question) {
		this.question = new_question;
	}

	/**
	 * @return the answer to the player's security question.
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * Update the answer to player's security question.
	 * 
	 * @param answer
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * Print the related information of a player.
	 */
	@Override
	public String toString() {
		return "Info of the player\n" + "Avator: " + avator + "\nName: " + name + " \nLogin ID: " + login
				+ "\nTotal Correct Score: " + total_correct + "\nCategory_scores: " + category_scores;
	}

}
