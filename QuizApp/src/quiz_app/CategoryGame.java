package quiz_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * A Game based on category
 *
 */
public class CategoryGame extends Game {

	private QuestionManager qm;
	protected String category;
	private int difficulty;
	private int score;
	private int time;
	private Random randomGenerator;
	private Timer timer;
	private ArrayList<Question> questions;
	private Scanner scanner;

	/**
	 * Create a CategoryGame based on the desired category and difficulty
	 * 
	 * @param category
	 * @param difficulty
	 */
	public CategoryGame(Player player, String category, int difficulty) {
		super(player);
		this.category = category;
		this.difficulty = difficulty;
		scanner = new Scanner(System.in);
		qm = QuestionManager.getInstance();
		randomGenerator = new Random();
		ArrayList<Question> questions = buildQuestions();

	}

	/**
	 * Build an ArrayList of Questions to be used in this Game
	 * 
	 * @return
	 */
	@Override
	public ArrayList<Question> buildQuestions() {
		ArrayList<Question> qs = qm.getQuestions(category);
		for (Question q : qs) {
			if (q.getDifficulty() != this.difficulty) {
				qs.remove(q);
			}
		}
		ArrayList<Question> questions = new ArrayList<Question>();
		if (qs.size() <= 10) {
			return qs;
		}
		for (int i = 0; i < 10; i++) {
			int index = randomGenerator.nextInt(qs.size());
			questions.add(qs.get(index));
			qs.remove(index);
		}
		return questions;

	}

	/**
	 * Asks a single question, with a time limit for the player to answer.
	 * 
	 * @param q
	 *            The Question being asked.
	 * @throws IOException
	 */
	@Override
	public void askQuestion(Question q) throws IOException {

		System.out.println(q.getQuestion());
		String str = "";
		TimerTask task = new TimerTask() {
			public void run() {
				if (str.equals("")) {
					System.out.println("You ran out of time!! The correct answer was " + q.getCorrectAnswer() + "\n");
				}
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 10 * 1000);

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String answer = in.readLine();
		if (judgeAnswer(q, answer)) {
			score += 10;
			setCorrectAnswers(getCorrectAnswers() + 1);
			System.out.println("Correct \n");
			timer.cancel();
		}
		timer.cancel();
	}

	@Override
	public void playGame() {
		for (int i = 0; i < 10; i++) {
			try {
				askQuestion(questions.get(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		updatePlayer();
	}

	public static void main(String[] args) throws IOException {
		String category = "Star Wars";
		String q1 = "What is Luke's last name?";
		int d1 = 1;
		String a1 = "Skywalker";
		Question question1 = new Question(category, q1, a1, d1);
		question1.addIncorrect("Starkiller");
		question1.addIncorrect("Solo");
		// CategoryGame cm = new CategoryGame(category, 1);
		// cm.askQuestion(question1);
		// System.out.println(cm.score);
	}
}
