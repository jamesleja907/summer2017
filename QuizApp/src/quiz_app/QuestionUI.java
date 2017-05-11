package quiz_app;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class QuestionUI {

	QuestionManager qm;

	private final JFrame jframe;

	private Container container;

	private JPanel categoryPanel;

	private JPanel questionPanel;

	private JPanel infoPanel;

	private JTextField categoryInput;

	private JTextField textField_Question;

	private JTextField textField_CorrectAnswer;

	private JTextField textField_Difficulty;

	private JTextField textField_1;

	private String selectedCategory;

	private QuestionUI() {
		qm = QuestionManager.getInstance();

		jframe = new JFrame();
		jframe.setTitle("Question Manager");
		jframe.setSize(new Dimension(1280, 800));

		container = jframe.getContentPane();
		container.setLayout(null);

		// Window listeners for closing?? or save on button mashing
		// ========== infoPanel ==========
		infoPanel = new JPanel();
		infoPanel.setBounds(748, 20, 458, 750);
		jframe.getContentPane().add(infoPanel);
		infoPanel.setLayout(null);

		textField_Question = new JTextField();
		textField_Question.setBounds(6, 62, 446, 116);
		infoPanel.add(textField_Question);
		textField_Question.setColumns(10);

		JLabel labelCorrectAnswer = new JLabel();
		labelCorrectAnswer.setText("Correct Answer");
		labelCorrectAnswer.setBounds(16, 190, 100, 29);
		infoPanel.add(labelCorrectAnswer);

		JLabel lblIncorrectAnswer = new JLabel();
		lblIncorrectAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		lblIncorrectAnswer.setText("Incorrect Answers:");
		lblIncorrectAnswer.setBounds(6, 278, 446, 29);
		infoPanel.add(lblIncorrectAnswer);

		textField_CorrectAnswer = new JTextField();
		textField_CorrectAnswer.setBounds(128, 191, 324, 26);
		infoPanel.add(textField_CorrectAnswer);
		textField_CorrectAnswer.setColumns(10);

		JLabel lblDifficulty = new JLabel();
		lblDifficulty.setText("Difficulty");
		lblDifficulty.setBounds(16, 228, 100, 29);
		infoPanel.add(lblDifficulty);

		textField_Difficulty = new JTextField();
		textField_Difficulty.setColumns(10);
		textField_Difficulty.setBounds(128, 229, 324, 26);
		infoPanel.add(textField_Difficulty);

		JLabel lblDetails = new JLabel("Details");
		lblDetails.setBorder(BorderFactory.createLineBorder(Color.black));
		lblDetails.setBackground(Color.white);
		lblDetails.setOpaque(true);
		lblDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetails.setBounds(6, 6, 446, 16);
		infoPanel.add(lblDetails);

		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion.setBounds(6, 44, 446, 16);
		infoPanel.add(lblQuestion);

		textField_1 = new JTextField();
		textField_1.setBounds(16, 559, 237, 26);
		infoPanel.add(textField_1);
		textField_1.setColumns(10);

		JButton btnAddIncorrect = new JButton("Add Incorrect Answer");
		btnAddIncorrect.setBounds(265, 559, 175, 29);
		infoPanel.add(btnAddIncorrect);

		JButton btnDeleteQuestion = new JButton("Delete this Question");
		btnDeleteQuestion.setBounds(128, 663, 217, 62);
		infoPanel.add(btnDeleteQuestion);

		JTextArea textArea_IncAnswer = new JTextArea();
		textArea_IncAnswer.setEditable(false);

		JScrollPane scroll_IncAnswer = new JScrollPane(textArea_IncAnswer);
		scroll_IncAnswer.setBounds(6, 319, 424, 216);
		scroll_IncAnswer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll_IncAnswer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		infoPanel.add(scroll_IncAnswer);

		// ========== questionPanel ==========
		TextFieldMouseListener m1 = new TextFieldMouseListener();
		JTextField cat_text = new JTextField();
		cat_text.setBounds(110, 542, 175, 29);
		cat_text.addMouseListener(m1);

		// Labels for indicating different inputs.
		JLabel catLabel = new JLabel();
		catLabel.setBounds(10, 542, 100, 29);
		catLabel.setText("Category");

		JTextField qText = new JTextField();
		qText.setBounds(110, 583, 175, 29);
		qText.addMouseListener(m1);

		// Need btnlistener to get num_questions in the category
		JLabel qLabel = new JLabel();
		qLabel.setBounds(10, 583, 100, 29);
		qLabel.setText("Question");

		JTextField cAnswer = new JTextField();
		cAnswer.setBounds(110, 624, 175, 29);
		cAnswer.addMouseListener(m1);

		JLabel cLabel = new JLabel();
		cLabel.setBounds(10, 624, 100, 29);
		cLabel.setText("Correct Answer");

		JTextField diffText = new JTextField();
		diffText.setBounds(110, 665, 175, 29);
		diffText.addMouseListener(m1);

		JLabel diffLabel = new JLabel();
		diffLabel.setBounds(10, 665, 100, 29);
		diffLabel.setText("Difficulty (1-3)");

		questionPanel = new JPanel();
		questionPanel.setBounds(374, 20, 300, 750);
		questionPanel.setLayout(null);
		questionPanel.add(cat_text);
		questionPanel.add(qText);
		questionPanel.add(cAnswer);
		questionPanel.add(diffText);
		questionPanel.add(catLabel);
		questionPanel.add(qLabel);
		questionPanel.add(diffLabel);
		questionPanel.add(cLabel);

		// Button for adding a new question.
		JButton addQuestion = new JButton("Add Question");
		addQuestion.setBounds(110, 706, 175, 29);
		addQuestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String catString = cat_text.getText();
				String qString = qText.getText();
				String cString = cAnswer.getText();
				String diffString = diffText.getText();
				if ((catString != "") && (qString != "") && (cString != "") && (diffString != "")) {
					Question q = new Question(catString, qString, cString, Integer.valueOf(diffString));
					qm.addQuestion(q);
					try {
						qm.saveToFile();
						System.out.println("got here");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		questionPanel.add(addQuestion);

		// Create the viewer for list of questions.
		DefaultListModel<String> model2 = new DefaultListModel<String>();
		model2.removeAllElements();

		JScrollPane scrollPaneQuestion = new JScrollPane();
		scrollPaneQuestion.setBounds(6, 6, 290, 530);
		questionPanel.add(scrollPaneQuestion);

		JLabel lblQuestions = new JLabel("Questions");
		scrollPaneQuestion.setColumnHeaderView(lblQuestions);
		lblQuestions.setHorizontalAlignment(SwingConstants.CENTER);
		JList<String> listquestion = new JList<String>(model2);
		scrollPaneQuestion.setViewportView(listquestion);
		listquestion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listquestion.setBorder(new LineBorder(Color.GRAY));

		listquestion.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				String buff_str = new String();
				buff_str = listquestion.getSelectedValue();
				if (qm.listCategory(selectedCategory).size() > 0) {
					for (Question q : qm.listCategory(selectedCategory)) {
						if (buff_str.equals(q.getQuestion())) {
							textField_Question.setText(q.getQuestion());
							textField_CorrectAnswer.setText(q.getCorrectAnswer());
							String diff = Integer.toString(q.getDifficulty());
							textField_Difficulty.setText(diff);
							textArea_IncAnswer.setText("");
							for (String incAnswer : q.getIncorrects()) {
								textArea_IncAnswer.append(incAnswer + "\n");
							}
						}
					}
				}
			}

		});

		container.add(questionPanel);

		// ========== CategoryPanel ==========
		// Create JPanel for category and display all imported categories.
		categoryPanel = new JPanel();
		categoryPanel.setBounds(50, 20, 250, 750);
		categoryPanel.setBorder(null);
		categoryPanel.setLayout(null);

		// Create the category counter.
		// Create the title for list of categories.
		int num_cat = qm.getQuestions().size();
		JLabel listimageslbl = new JLabel("List of Categories " + "(" + num_cat + ")");

		// Collection of all categories.
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.removeAllElements();
		for (String category : qm.getQuestions().keySet()) {
			model.addElement(category);
		}

		// Create the viewer for list of categories.
		JScrollPane scrollPaneCategory = new JScrollPane();
		scrollPaneCategory.setBounds(6, 6, 240, 530);
		categoryPanel.add(scrollPaneCategory);
		scrollPaneCategory.setColumnHeaderView(listimageslbl);
		listimageslbl.setHorizontalAlignment(SwingConstants.CENTER);
		JList<String> listcategory = new JList<String>(model);
		scrollPaneCategory.setViewportView(listcategory);
		listcategory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listcategory.setBorder(new LineBorder(Color.GRAY));

		// btnlistener for displaying all questions in a category
		listcategory.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String buff_str = new String();
				buff_str = listcategory.getSelectedValue();

				if (!buff_str.equals("")) {
					selectedCategory = buff_str;
					model2.removeAllElements();
					textField_Question.setText("");
					textField_CorrectAnswer.setText("");
					textField_Difficulty.setText("");

					if (qm.listCategory(buff_str).size() > 0) {
						for (Question q : qm.listCategory(buff_str)) {
							model2.addElement(q.getQuestion());
						}
					}
					int num_que = qm.listCategory(buff_str).size();
					lblQuestions.setText("Questions (" + num_que + ")");
				}

			}
		});

		// Textfield for inputing category
		categoryInput = new JTextField();
		categoryInput.setBounds(60, 589, 130, 26);
		categoryPanel.add(categoryInput);
		categoryInput.setColumns(10);

		// Button for adding a new category
		JLabel lblNewCategory = new JLabel("New Category:");
		lblNewCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewCategory.setBounds(6, 561, 240, 16);
		categoryPanel.add(lblNewCategory);

		JButton addCategorybtn = new JButton("Add Category");
		addCategorybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addCategorybtn.setBounds(60, 627, 130, 29);
		categoryPanel.add(addCategorybtn);

		// Button for deleting a category
		JButton btnDeleteTheCategory = new JButton("Delete the Category");
		btnDeleteTheCategory.setBounds(40, 706, 175, 29);
		categoryPanel.add(btnDeleteTheCategory);

		container.add(categoryPanel);
	}

	private void createAndShowGui() {
		jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jframe.setVisible(true);
	}

	public static void main(String[] args) {
		QuestionUI q = new QuestionUI();
		q.createAndShowGui();

	}
}
