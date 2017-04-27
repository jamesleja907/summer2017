package quiz_app;

import java.util.HashMap;

/* 
 * The Class for player in the app.
 */
public class Player {
	// The Player's name.
	private String name;
	// The unique login ID.
	private Integer login;
	// The password for login.
	private String password;
	// The total score of all questions this player had answered.
	private Integer total_score;
	// The total correct socre of all questions this player has answered.
	private Integer total_correct;
	// Scores the player get for different categories.
	private HashMap<String, Integer> category_scores;
	// Palyer's avator. This should be an address to a local file.
	private String avator;

	/*
	 * Create a player with name, password, total score, total correct scores,
	 * and socres in each catgory.
	 */
	public Player(String name, String password, Integer total_score, Integer total_correct,
			HashMap<String, Integer> catgory_scores, String avator) {
		this.name = name;
		this.password = password;
		this.total_score = total_score;
		this.total_correct = total_correct;
		this.category_scores = catgory_scores;
		this.avator = avator;
	}

	/*
	 * Return the player's name.
	 */
	public String getName() {
		return name;
	}

	/*
	 * Set the player's name with String name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Return the player's interger login id.
	 */
	public Integer getLogin() {
		return login;
	}

	/*
	 * Set the player's unique login id.
	 */
	public void setLogin(Integer login) {
		if (this.login == null) {
			this.login = login;
		}
	}

	/*
	 * Set or reset the player's password.
	 */
	public void setPassword(String old_password, String new_password) {
		// A user can only change the password if he/she can correctly provide
		// the old password.
		if (this.password == old_password) {
			this.password = new_password;
		}
	}

	// Return the total scores of all questions the player answered.
	public Integer getTotal_score() {
		return total_score;
	}

	// Update the total score with Integer total_score.
	public void setTotal_score(Integer total_score) {
		this.total_score = total_score;
	}

	// Get the total correct score the player get.
	public Integer getTotal_correct() {
		return total_correct;
	}

	// Update the total correct score with Integer total_correct.
	public void setTotal_correct(Integer total_correct) {
		this.total_correct = total_correct;
	}

	/*
	 * Return the correctness rate of this user.
	 */
	public Double getCorrect_Average() {
		double result = this.total_correct / this.total_score;
		return result;
	}
	
	/*
	 * Return the collection of all scores the player get in different category.
	 */
	public HashMap<String, Integer> getCatgory_scores() {
		return category_scores;
	}
	
	//
	public void setCatgory_scores(HashMap<String, Integer> catgory_scores) {
		this.category_scores = catgory_scores;
	}

	public String getAvator() {
		return avator;
	}

	public void setAvator(String avator) {
		this.avator = avator;
	}

}
