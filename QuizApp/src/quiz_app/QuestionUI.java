package quiz_app;

import javax.swing.*;
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
	
	
	private QuestionUI(){
		qm = QuestionManager.getInstance();
		
		
		jframe = new JFrame();
		jframe.setTitle("Question Manager");
		jframe.setSize(new Dimension(1000, 1000));
		
		// Window listeners for closing?? or save on button mashing
		
		TextFieldMouseListener m1 = new TextFieldMouseListener();
		JTextField cat_text = new JTextField();
		cat_text.setSize(175, 29);
		cat_text.setLocation(100,6);
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
		buttonPanel.setSize(900, 900);
		buttonPanel.setLocation(100, 100);
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
		addQuestion.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String catString = cat_text.getText();
				String qString = qText.getText();
				String cString = cAnswer.getText();
				String diffString = diffText.getText();
				if ((catString != "") && (qString != "") 
					&& (cString != "") && (diffString != "")) {
					Question q = new Question(catString, qString, cString,
											Integer.valueOf(diffString));
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
		
		
		
		
	}
	private void createAndShowGui(){
		jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jframe.setVisible(true);
	}
	
	
	
	

	public static void main(String[] args) {
		QuestionUI q = new QuestionUI();
		q.createAndShowGui();
		

	}

}
