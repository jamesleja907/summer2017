package quiz_app;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * An abstract Game class
 *
 */
public abstract class Game {

	/** Check if player is ready. */
	protected boolean ready;
	/** The time allotted for the game. */
	protected int time;
	/** The score for the game. */
	protected int score;
	/** The player of the game. */
	private Player player;
	/** The number of correct answers in the game. */
	private int correctAnswers;

	public Game(Player player) {
		this.setPlayer(player);
		this.score = 0;
		this.setCorrectAnswers(0);

	}

	/** Abstract methods to be inherited by the children of Game. */
	public abstract void askQuestion(Question q) throws IOException;

	public abstract void playGame();

	public abstract ArrayList<Question> buildQuestions();

	/**
	 * Compares the selected/inputed answer to the correct answer.
	 * 
	 * @param q
	 *            The question object.
	 * @param answer
	 *            The answer supplied by the player.
	 * @return if the player's answer matches the correct answer.
	 */
	public boolean judgeAnswer(Question q, String answer) {
		return q.getCorrectAnswer().equals(answer);
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * reward points based on a correct answer
	 * 
	 * @return score
	 */
	public void updatePlayer() {
		int playerScore = this.player.getTotal_score();
		playerScore += this.score;
		this.player.setTotal_score(playerScore);
		int playerCorrect = this.player.getTotal_correct();
		playerCorrect += this.correctAnswers;
		this.player.setTotal_correct(playerCorrect);
	}

}
