package quiz_app;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the saving and loading of Questions.
 */
public class QuestionManager {
	
	/** The instance of QuestionManager */
	private static final QuestionManager instance = new QuestionManager();
	
	/** A map from Category to an array of Questions. */
	private Map<String, ArrayList<Question>> questions;
	
	private QuestionManager() {
		questions = new HashMap<String, ArrayList<Question>>();
		File file = new File("questions.ser");
		if (file.exists()) {
			try {
				readFromFile(file.getPath());
			} catch (FileNotFoundException e) {
			} catch (ClassNotFoundException e) {
			}
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * @return the instance of ImageManager.
	 */
	public static QuestionManager getInstance() {
		return instance;
	}

	/**
	 * Populates the question map from the file at path filePath.
	 * 
	 * @param filePath
	 *            the path of the data file
	 * @throws FileNotFoundException
	 *             if filePath is not a valid path
	 */
	public void readFromFile(String filePath) throws FileNotFoundException, ClassNotFoundException {
		try {
			InputStream file = new FileInputStream(filePath);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);

			// deserialize the Map
			questions = (Map<String, ArrayList<Question>>) input.readObject();
			input.close();
		} catch (ClassNotFoundException ex) {
		} catch (IOException ex) {
		}
	}
	
	/**
	 * Writes the questions to file at filePath.
	 * 
	 * @param filePath
	 *            the file to write the records to
	 * @throws IOException
	 */
	public void saveToFile() throws IOException {
		File file = new File("questions.ser");

		OutputStream filePath = new FileOutputStream(file.getPath());
		OutputStream buffer = new BufferedOutputStream(filePath);
		ObjectOutput output = new ObjectOutputStream(buffer);

		// serialize the Map
		output.writeObject(questions);
		output.close();
	}
	/**
	 * Add a question to the map.
	 * 
	 * @param question
	 * 		The question to be added.
	 */
	public void addQuestion(Question question) {
		// Get the category from the question, which is the key.
		String category = question.getCategory();
		if (questions.containsKey(category)) {
			// If the question is not in the array, add it.
			if (!questions.get(category).contains(question)){
				questions.get(category).add(question);
			}
		}
	}
	/**
	 * Remove a question from the map.
	 * 
	 * @param question
	 * 		The question to be removed.
	 */
	public void removeQuestion(Question question) {
		String category = question.getCategory();
		if (questions.containsKey(category)) {
			if (questions.get(category).contains(question)){
				questions.get(category).remove(question);
			}
		}
	}
}
