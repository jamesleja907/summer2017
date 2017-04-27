package quiz_app;

import java.util.ArrayList;
/**
 * 
 * A Game based on category
 *
 */
public class CategoryGame extends Game {
	
	protected String category;
	
	private int score;
//	
	private int time;
	
	public CategoryGame(String category){
		this.category = category;
		
	}	
	/**
	 * Groups questions based on category
	 * @return ArrayList
	 */
	public ArrayList getQuestions(){
		ArrayList questions = new ArrayList(); 
		
		return questions;
		
	}
	

}
