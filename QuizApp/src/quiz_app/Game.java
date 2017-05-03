package quiz_app;

/**
 * 
 * An abstract Game class
 *
 */
public abstract class Game {

	/** Check if player is ready */
	protected boolean ready;
	/** The time allotted for the game */
	protected int time;
	/** The score for the game */
	protected int score;

	public Game() {

	}

	// public Question askQuestion(){
	//
	// }
	/**
	 * Compares the selected/inputed answer to the correct answer.
	 * 
	 */
	public boolean judgeAnswer() {
		return true;
	}

	/**
	 * reward points based on a correct answer
	 * 
	 * @return score
	 */
	public int updateScore() {
		if (judgeAnswer()) {
			score += 10;
		}
		return score;
	}

}
