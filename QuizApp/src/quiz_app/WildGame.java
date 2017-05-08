package quiz_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A game featuring questions from any category and any difficulty level.
 */
public class WildGame extends Game {
	
	/** The Question Manager for WildGame. */
	private QuestionManager qm;
	
	/** The questions of the WildGame. */
	private ArrayList<Question> questions;	
	
	/** Generator to pick random categories and questions. */
	private Random randomGenerator;
	
	public WildGame(Player player) {
		super(player);
		qm = QuestionManager.getInstance();
		randomGenerator = new Random();
		buildQuestions();
		
	}

	@Override
	public void askQuestion(Question q) throws IOException {
		// TODO Auto-generated method stub

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
		} else {
			System.out.println("Sorry that's wrong");
		}
		timer.cancel();
	}
		

	@Override
	public void playGame() {
		int numberQuestions = questions.size();
		for (int i = 0; i < numberQuestions; i++) {
			try {
				askQuestion(questions.get(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		updatePlayer();
	}

	@Override
	public void buildQuestions() {
		questions = new ArrayList<Question>();
		ArrayList<String> categories = new ArrayList(qm.getQuestions().keySet());
		int numQuestions;
		if (qm.getTotalQuestions() < 10) {
			numQuestions = qm.getTotalQuestions();
		} else {
			numQuestions = 10;
		}
		for (int i = 0; i < numQuestions; i ++) {
			String category = categories.get(randomGenerator.nextInt
											(categories.size()));
			ArrayList<Question> catQuestions = qm.listCategory(category);
			questions.add(catQuestions.get(randomGenerator.nextInt
											(catQuestions.size())));
		}
	}

}
