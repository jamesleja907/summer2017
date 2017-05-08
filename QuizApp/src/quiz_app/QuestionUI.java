package quiz_app;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class QuestionUI {

	QuestionManager qm;

	private final JFrame jframe;

	private JPanel questionPanel;

	private JTextArea textarea;

	private Container container;

	private JPanel categoryPanel;
	private JTextField categoryInput;

	private QuestionUI() {
		qm = QuestionManager.getInstance();

		jframe = new JFrame();
		jframe.setTitle("Question Manager");
		jframe.setSize(new Dimension(1000, 1000));

		// Window listeners for closing?? or save on button mashing

		// ========== buttonPanel ==========
		TextFieldMouseListener m1 = new TextFieldMouseListener();
		JTextField cat_text = new JTextField();
		cat_text.setBounds(110, 670, 175, 29);
		cat_text.addMouseListener(m1);

		JLabel catLabel = new JLabel();
		catLabel.setBounds(10, 670, 100, 29);
		catLabel.setText("Category");

		JTextField qText = new JTextField();
		qText.setBounds(110, 711, 175, 29);
		qText.addMouseListener(m1);

		JLabel qLabel = new JLabel();
		qLabel.setBounds(10, 711, 100, 29);
		qLabel.setText("Question");

		JTextField cAnswer = new JTextField();
		cAnswer.setBounds(110, 752, 175, 29);
		cAnswer.addMouseListener(m1);

		JLabel cLabel = new JLabel();
		cLabel.setBounds(10, 752, 100, 29);
		cLabel.setText("Correct Answer");

		JTextField diffText = new JTextField();
		diffText.setBounds(110, 793, 175, 29);
		diffText.addMouseListener(m1);

		JLabel diffLabel = new JLabel();
		diffLabel.setBounds(10, 793, 100, 29);
		diffLabel.setText("Difficulty (1-3)");

		questionPanel = new JPanel();
		questionPanel.setBounds(374, 45, 300, 902);
		questionPanel.setLayout(null);
		questionPanel.add(cat_text);
		questionPanel.add(qText);
		questionPanel.add(cAnswer);
		questionPanel.add(diffText);
		questionPanel.add(catLabel);
		questionPanel.add(qLabel);
		questionPanel.add(diffLabel);
		questionPanel.add(cLabel);

		container = jframe.getContentPane();
		container.setLayout(null);
		container.add(questionPanel);

		JButton addQuestion = new JButton("Add Question");
		addQuestion.setBounds(110, 834, 175, 29);
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
		
		DefaultListModel<String> model2 = new DefaultListModel<String>();
		
		model2.removeAllElements();
		// Try display questions in the STAR WARS
		for(Question q : qm.listCategory("Star Wars")) {
			System.out.println(q.getQuestion());
			model2.addElement(q.getQuestion());
		}
		container.add(questionPanel);
		
		JScrollPane scrollPaneQuestion = new JScrollPane();
		scrollPaneQuestion.setBounds(6, 6, 290, 641);
		questionPanel.add(scrollPaneQuestion);
		
		JLabel lblQuestions = new JLabel("Questions");
		scrollPaneQuestion.setColumnHeaderView(lblQuestions);
		lblQuestions.setHorizontalAlignment(SwingConstants.CENTER);
		JList<String> listquestion = new JList<String>(model2);
		scrollPaneQuestion.setViewportView(listquestion);
		listquestion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listquestion.setBorder(new LineBorder(Color.GRAY));
		
		// ========== CategoryPanel ==========
		// Create JPanel for category and display all imported categories.
		categoryPanel = new JPanel();
		categoryPanel.setBounds(50, 45, 250, 902);
		categoryPanel.setBorder(null);
		categoryPanel.setLayout(null);

		// Create the title for list of categories.
		// Create the category counter.
		int num_cat = qm.getQuestions().size();

		DefaultListModel<String> model = new DefaultListModel<String>();
		
		model.removeAllElements();
		for (String category : qm.getQuestions().keySet()) {
			model.addElement(category);
			//System.out.println(category);
		}
		container.add(categoryPanel);
		
		JButton addCategorybtn = new JButton("Add Category");
		addCategorybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
				// Create the viewer for list of categories.
				JScrollPane scrollPaneCategory = new JScrollPane();
				scrollPaneCategory.setBounds(6, 6, 240, 713);
				categoryPanel.add(scrollPaneCategory);
				JLabel listimageslbl = new JLabel("List of Categories " + "(" + num_cat + ")");
				scrollPaneCategory.setColumnHeaderView(listimageslbl);
				listimageslbl.setHorizontalAlignment(SwingConstants.CENTER);
		JList<String> listcategory = new JList<String>(model);
		scrollPaneCategory.setViewportView(listcategory);
		listcategory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listcategory.setBorder(new LineBorder(Color.GRAY));
		addCategorybtn.setBounds(60, 834, 130, 29);
		categoryPanel.add(addCategorybtn);
		
		categoryInput = new JTextField();
		categoryInput.setBounds(60, 787, 130, 26);
		categoryPanel.add(categoryInput);
		categoryInput.setColumns(10);
		
		JLabel lblNewCategory = new JLabel("New Category:");
		lblNewCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewCategory.setBounds(60, 768, 130, 16);
		categoryPanel.add(lblNewCategory);

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
