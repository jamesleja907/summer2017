package quiz_app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ConfirmWindow {
	
	private final JFrame jFrame;
	
	private JPanel buttonPanel;
	
	private Container contents; 
	
	private JLabel jLabel;
	
	public ConfirmWindow(ActionListener action, String message) {
		
		jFrame = new JFrame();
		jFrame.setTitle("Confirm");
		jFrame.setSize(new Dimension(600, 300));
		
		contents = jFrame.getContentPane();
		contents.setLayout(null);
		
		jLabel = new JLabel(message);
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel.setSize(new Dimension(593, 50));
		jLabel.setLocation(6, 99);
		
		
		buttonPanel = new JPanel();
		buttonPanel.setSize(new Dimension(599, 299));
		buttonPanel.setLayout(null);
		
		JButton yesButton = new JButton("Yes");	
		yesButton.setSize(new Dimension(100, 50));
		yesButton.setLocation(167, 211);
		// add to yesButton the action to be confirmed.
		yesButton.addActionListener(action);
			
	
		JButton noButton = new JButton("No");
		noButton.setSize(new Dimension(100, 50));
		noButton.setLocation(332, 211);
		noButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				jFrame.dispose();
			}
			
		});
		
		buttonPanel.add(jLabel);
		buttonPanel.add(noButton);
		buttonPanel.add(yesButton);
		contents.add(buttonPanel);
		
		jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jFrame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Yay");
			}
		};
		ConfirmWindow q = new ConfirmWindow(action, "Are you sure?");
	}
		
		

}
