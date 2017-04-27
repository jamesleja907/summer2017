package quiz_app;

import java.util.HashMap;

/* 
 * The Class for player in the game.
 */
public class Player {

	private String name;
	private Integer login;
	private String password;
	private Integer total_score;
	private Integer total_correct;
	private HashMap<String, Integer> catgory_scores;
	private String avator;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLogin() {
		return login;
	}

	public void setLogin(Integer login) {
		if (this.login == null) {
			this.login = login;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String old_password, String new_password) {
		// A user can only change the password if he/she can correctly provide
		// the old password.
		if (this.password == old_password) {
			this.password = new_password;
		}
	}

	public Integer getTotal_score() {
		return total_score;
	}

	public void setTotal_score(Integer total_score) {
		this.total_score = total_score;
	}

	public Integer getTotal_correct() {
		return total_correct;
	}

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
	
	public HashMap<String, Integer> getCatgory_scores() {
		return catgory_scores;
	}

	public void setCatgory_scores(HashMap<String, Integer> catgory_scores) {
		this.catgory_scores = catgory_scores;
	}

	public String getAvator() {
		return avator;
	}

	public void setAvator(String avator) {
		this.avator = avator;
	}

}
