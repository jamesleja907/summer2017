package quiz_app;

import java.util.HashMap;

/**
 * The Class for player in the app.
 */
public class Player {
	/** The Player's name. */
	private String name;
	/** The unique login ID. */
	private String login;
	/** The password for login. */
	private String password;
	/** The total score of all questions this player had answered. */
	private Integer total_score;
	/** The total correct socre of all questions this player has answered. */
	private Integer total_correct;
	/** Scores the player get for different categories. */
	private HashMap<String, Integer> category_scores;
	/** Palyer's avator. This should be an address to a local file. */
	private String avator;

	/**
	 * Create a player with name, password, total score, total correct scores,
	 * and socres in each catgory.
	 * 
	 * @param name
	 *            the name of this Player
	 * @param password
	 *            the password for login
	 * @param total_score
	 *            the total score of all questions this player had answered.
	 * @param total_correct
	 *            the total correct socre of all questions this player has
	 *            answered.
	 * @param catgory_scores
	 *            ccores the player get for different categories
	 * @param avator
	 *            palyer's avator
	 */
	public Player(String name, String login, String password, String avator) {
		this.name = name;
		this.login = login;
		this.password = password;
		this.total_score = 0;
		this.total_correct = 0;
		this.category_scores = new HashMap<String, Integer>();
		this.avator = avator;
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

	/**
	 * @return the player's interger login id.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Set the player's unique login id.
	 * 
	 * @param login
	 *            the unique id of the player
	 */
	public void setLogin(String new_login) {
		// Check all other login to make sure the String new_login is unique.
		if (this.login == null) {
			this.login = new_login;
		}
	}

	/**
	 * Set or reset the player's password.
	 * 
	 * @param old_password
	 *            Player's current password
	 * @param new_password
	 *            the new password
	 */
	public void setPassword(String old_password, String new_password) {
		// A user can only change the password if he/she can correctly provide
		// the old password.
		if (this.password == old_password) {
			this.password = new_password;
		}
	}

	/**
	 * @return the total scores of all questions the player answered.
	 */
	public Integer getTotal_score() {
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
	 * @return the Player's avator.
	 */
	public String getAvator() {
		return avator;
	}

	/**
	 * Update the Player's avator
	 * 
	 * @param avator
	 *            the local address of file avator
	 */
	public void setAvator(String avator) {
		this.avator = avator;
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
