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

	private JTextField newCatInput;

	private JTextField infoQuestion;

	private JTextField infoAns;

	private JTextField infoDiff;

	private JTextField infoIncAnsInput;

	private JTextArea infoIncAns;

	private String selectedCategory;

	private Question selectedQuestion;

	private QuestionUI() {
		qm = QuestionManager.getInstance();
		selectedCategory = "";
		selectedQuestion = null;
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

		infoQuestion = new JTextField();
		infoQuestion.setBounds(6, 62, 446, 116);
		infoPanel.add(infoQuestion);
		infoQuestion.setColumns(10);

		JLabel labelCorrectAnswer = new JLabel();
		labelCorrectAnswer.setText("Correct Answer");
		labelCorrectAnswer.setBounds(16, 190, 100, 29);
		infoPanel.add(labelCorrectAnswer);

		JLabel lblIncorrectAnswer = new JLabel();
		lblIncorrectAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		lblIncorrectAnswer.setText("Incorrect Answers:");
		lblIncorrectAnswer.setBounds(6, 278, 446, 29);
		infoPanel.add(lblIncorrectAnswer);

		infoAns = new JTextField();
		infoAns.setBounds(128, 191, 324, 26);
		infoPanel.add(infoAns);
		infoAns.setColumns(10);

		JLabel lblDifficulty = new JLabel();
		lblDifficulty.setText("Difficulty");
		lblDifficulty.setBounds(16, 228, 100, 29);
		infoPanel.add(lblDifficulty);

		infoDiff = new JTextField();
		infoDiff.setColumns(10);
		infoDiff.setBounds(128, 229, 324, 26);
		infoPanel.add(infoDiff);

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
		
		infoIncAns = new JTextArea();
		infoIncAns.setEditable(false);
		
		infoIncAnsInput = new JTextField();
		infoIncAnsInput.setBounds(16, 559, 237, 26);
		infoPanel.add(infoIncAnsInput);
		infoIncAnsInput.setColumns(10);

		JButton btnAddIncorrect = new JButton("Add Incorrect Answer");
		btnAddIncorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((selectedQuestion != null) && (!infoIncAnsInput.equals(""))){
					selectedQuestion.addIncorrect(infoIncAnsInput.getText());
					infoIncAns.append(infoIncAnsInput.getText() + "\n");
					try {
						qm.saveToFile();
					} catch (IOException e1) {
					}
				}
			}
		});
		btnAddIncorrect.setBounds(265, 559, 175, 29);
		
		infoPanel.add(btnAddIncorrect);

		JButton btnDeleteQuestion = new JButton("Delete this Question");
		btnDeleteQuestion.setBounds(128, 663, 217, 62);
		infoPanel.add(btnDeleteQuestion);

		JScrollPane scroll_IncAnswer = new JScrollPane(infoIncAns);
		scroll_IncAnswer.setBounds(6, 319, 424, 216);
		scroll_IncAnswer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll_IncAnswer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		infoPanel.add(scroll_IncAnswer);

		// ========== questionPanel ==========
		TextFieldMouseListener m1 = new TextFieldMouseListener();
		JTextField qCatInput = new JTextField();
		qCatInput.setBounds(110, 542, 175, 29);
		qCatInput.addMouseListener(m1);

		// Labels for indicating different inputs.
		JLabel catLabel = new JLabel();
		catLabel.setBounds(10, 542, 100, 29);
		catLabel.setText("Category");

		JTextField qQuestInput = new JTextField();
		qQuestInput.setBounds(110, 583, 175, 29);
		qQuestInput.addMouseListener(m1);

		// Need btnlistener to get num_questions in the category
		JLabel qLabel = new JLabel();
		qLabel.setBounds(10, 583, 100, 29);
		qLabel.setText("Question");

		JTextField qCAnsInput = new JTextField();
		qCAnsInput.setBounds(110, 624, 175, 29);
		qCAnsInput.addMouseListener(m1);

		JLabel cLabel = new JLabel();
		cLabel.setBounds(10, 624, 100, 29);
		cLabel.setText("Correct Answer");

		JTextField qDiffInput = new JTextField();
		qDiffInput.setBounds(110, 665, 175, 29);
		qDiffInput.addMouseListener(m1);

		JLabel diffLabel = new JLabel();
		diffLabel.setBounds(10, 665, 100, 29);
		diffLabel.setText("Difficulty (1-3)");

		questionPanel = new JPanel();
		questionPanel.setBounds(374, 20, 300, 750);
		questionPanel.setLayout(null);
		questionPanel.add(qCatInput);
		questionPanel.add(qQuestInput);
		questionPanel.add(qCAnsInput);
		questionPanel.add(qDiffInput);
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
				String catString = qCatInput.getText();
				String qString = qQuestInput.getText();
				String cString = qCAnsInput.getText();
				String diffString = qDiffInput.getText();
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
				// add null check for deleting a question.
				if (listquestion.getSelectedValue() != null) {
					String buff_str = listquestion.getSelectedValue();
					if (qm.listCategory(selectedCategory).size() > 0) {
						for (Question q : qm.listCategory(selectedCategory)) {
							if (buff_str.equals(q.getQuestion())) {
								selectedQuestion = q;
								infoQuestion.setText(q.getQuestion());
								infoAns.setText(q.getCorrectAnswer());
								String diff = Integer.toString(q.getDifficulty());
								infoDiff.setText(diff);
								infoIncAns.setText("");
								for (String incAnswer : q.getIncorrects()) {
									infoIncAns.append(incAnswer + "\n");
								}
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
				if (listcategory.getSelectedValue() != null) {
					selectedCategory = listcategory.getSelectedValue();
					if (!selectedCategory.equals("")) {
						selectedQuestion = null;
						model2.removeAllElements();
						emptyInfoPanel();

						if (qm.listCategory(selectedCategory).size() > 0) {
							for (Question q : qm.listCategory(selectedCategory)) {
								model2.addElement(q.getQuestion());
							}
						}
						qCatInput.setText(selectedCategory);
						int num_que = qm.listCategory(selectedCategory).size();
						lblQuestions.setText("Questions (" + num_que + ")");
					}
				}

			}
		});

		// Textfield for inputing category
		newCatInput = new JTextField();
		newCatInput.addMouseListener(m1);
		newCatInput.setBounds(60, 589, 130, 26);
		categoryPanel.add(newCatInput);
		newCatInput.setColumns(10);

		// Button for adding a new category
		JLabel newCatLabel = new JLabel("New Category:");
		newCatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newCatLabel.setBounds(6, 561, 240, 16);
		categoryPanel.add(newCatLabel);

		JButton addCategorybtn = new JButton("Add Category");
		addCategorybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newCat = newCatInput.getText();
				if (!newCat.equals("")) {
					qm.addCategory(newCat);
					try {
						qm.saveToFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					model.addElement(newCat);
				}
			}
		});
		addCategorybtn.setBounds(60, 627, 130, 29);
		categoryPanel.add(addCategorybtn);

		JButton btnDeleteTheCategory = new JButton("Delete the Category");
		btnDeleteTheCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeElement(selectedCategory);
				qm.removeCategory(selectedCategory);
				selectedCategory = "";
				model2.removeAllElements();
				emptyInfoPanel();
				selectedQuestion = null;
				try {
					qm.saveToFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDeleteTheCategory.setBounds(40, 706, 175, 29);
		categoryPanel.add(btnDeleteTheCategory);

		container.add(categoryPanel);
	}

	private void createAndShowGui() {
		jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jframe.setVisible(true);
	}

	private void emptyInfoPanel() {
		infoQuestion.setText("");
		infoAns.setText("");
		infoDiff.setText("");
		infoIncAns.setText("");
		infoIncAnsInput.setText("");
	}

	public static void main(String[] args) {
		QuestionUI q = new QuestionUI();
		q.createAndShowGui();

	}
}
