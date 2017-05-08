package quiz_app;

public class Admin extends Account {

	private static final long serialVersionUID = 5958318758075997048L;

	/** QuestionManager for adding and removing questions. */
	private QuestionManager qm;

	public Admin(String login, String password) {
		super(login, password);
		qm = QuestionManager.getInstance();

	}

	public void addQuestion(String category, String question, String correctAnswer, int difficulty) {
		Question q = new Question(category, question, correctAnswer, difficulty);
		qm.addQuestion(q);
	}

	public void removeQuestion(Question question) {
		qm.removeQuestion(question);
	}

	public void addIncorrect(Question question, String incorrect) {
		question.addIncorrect(incorrect);
	}

}
