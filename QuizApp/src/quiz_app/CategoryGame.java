package quiz_app;

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
	 * @param category
	 * @param difficulty
	 */
	public CategoryGame(String category, int difficulty){
		this.category = category;
		this.difficulty = difficulty;
		scanner = new Scanner(System.in);
		qm = QuestionManager.getInstance();
		randomGenerator = new Random();
		ArrayList<Question> questions = buildQuestions();
		
	}
	/**
	 * Build an ArrayList of Questions to be used in this Game
	 * @return
	 */
	public ArrayList<Question> buildQuestions(){
		ArrayList<Question> qs = qm.getQuestions(category);
		for (Question q : qs){
			if (q.getDifficulty() != this.difficulty){
				qs.remove(q);
			}
		}
		ArrayList<Question> questions = new ArrayList<Question>();
		if (qs.size() <= 10){
			return qs;
		}
		for (int i = 0; i < 10; i++){
			int index = randomGenerator.nextInt(qs.size());
			questions.add(qs.get(index));
			qs.remove(index);
		}
		return questions;
		
	}
	class askQuestion extends TimerTask{

		@Override
		public void run() {
			Question q = questions.get(0);
			System.out.println(q.getQuestion());
//			boolean correct = false;
			String answer = scanner.next();
			
		}
		
	}

}
