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

	private JPanel buttonPanel;

	private JTextArea textarea;

	private Container container;
	
	private JPanel categoryPanel;

	private QuestionUI() {
		qm = QuestionManager.getInstance();

		jframe = new JFrame();
		jframe.setTitle("Question Manager");
		jframe.setSize(new Dimension(1000, 1000));
		
		// Window listeners for closing?? or save on button mashing
		
		// ========== buttonPanel ==========
		TextFieldMouseListener m1 = new TextFieldMouseListener();
		JTextField cat_text = new JTextField();
		cat_text.setSize(175, 29);
		cat_text.setLocation(100, 6);
		cat_text.addMouseListener(m1);

		JLabel catLabel = new JLabel();
		catLabel.setSize(100, 29);
		catLabel.setLocation(0, 6);
		catLabel.setText("Category");

		JTextField qText = new JTextField();
		qText.setSize(175, 29);
		qText.setLocation(100, 35);
		qText.addMouseListener(m1);

		JLabel qLabel = new JLabel();
		qLabel.setSize(100, 29);
		qLabel.setLocation(0, 35);
		qLabel.setText("Question");

		JTextField cAnswer = new JTextField();
		cAnswer.setSize(175, 29);
		cAnswer.setLocation(100, 64);
		cAnswer.addMouseListener(m1);

		JLabel cLabel = new JLabel();
		cLabel.setSize(100, 29);
		cLabel.setLocation(0, 64);
		cLabel.setText("Correct Answer");

		JTextField diffText = new JTextField();
		diffText.setSize(175, 29);
		diffText.setLocation(100, 93);
		diffText.addMouseListener(m1);

		JLabel diffLabel = new JLabel();
		diffLabel.setSize(100, 29);
		diffLabel.setLocation(0, 93);
		diffLabel.setText("Difficulty (1-3)");

		buttonPanel = new JPanel();
		buttonPanel.setSize(300, 343);
		buttonPanel.setLocation(400, 557);
		buttonPanel.setLayout(null);
		buttonPanel.add(cat_text);
		buttonPanel.add(qText);
		buttonPanel.add(cAnswer);
		buttonPanel.add(diffText);
		buttonPanel.add(catLabel);
		buttonPanel.add(qLabel);
		buttonPanel.add(diffLabel);
		buttonPanel.add(cLabel);

		container = jframe.getContentPane();
		container.setLayout(null);
		container.add(buttonPanel);

		JButton addQuestion = new JButton("Add Question");
		addQuestion.setSize(175, 29);
		addQuestion.setLocation(100, 122);
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

		buttonPanel.add(addQuestion);
		
		// ========== CategoryPanel ==========
		// Create JPanel for category and display all imported categories.
		categoryPanel = new JPanel();
		categoryPanel.setBounds(50, 45, 250, 740);
		categoryPanel.setBorder(new LineBorder(Color.GRAY));
		categoryPanel.setLayout(null);
		
		// Create the title for list of categories.
		JLabel listimageslbl = new JLabel("List of Categories");
		listimageslbl.setHorizontalAlignment(SwingConstants.CENTER);
		listimageslbl.setBounds(0, 6, 250, 16);
		categoryPanel.add(listimageslbl);
		
		// Create the viewer for list of categories.
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 36, 240, 698);
		categoryPanel.add(scrollPane);
		
		// Create the category counter.
		JLabel categorycount = new JLabel("(0)");
		categorycount.setBounds(185, 6, 59, 19);
		categoryPanel.add(categorycount);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String> listcategory = new JList<String>(model);
		listcategory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listcategory.setBorder(new LineBorder(Color.GRAY));
		listcategory.setBounds(5, 110, 240, 581);
		
		container.add(categoryPanel);
		
		for(String : )

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
