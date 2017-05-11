package quiz_app;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CategoryGameUI {
	
	private final JFrame jframe;
	
	private Container container;

	public CategoryGameUI(CategoryGame cm) {
		
		jframe = new JFrame();
		jframe.setTitle(cm.category);
		jframe.setSize(new Dimension(1200, 800));
		
		JLabel imageLabel = new JLabel();
		imageLabel.setSize(new Dimension(1200, 400));
		imageLabel.setLocation(0, 0);
		
		BufferedImage img = null;
		try {
			File file = new File(cm.category + ".png");
			img = ImageIO.read(file);
		} catch (IOException e) {
		}		
		ImageIcon nextIcon = new ImageIcon(img.getScaledInstance(1200, 400, img.SCALE_AREA_AVERAGING));
		imageLabel.setIcon(nextIcon);
		
		JPanel questionPanel = new JPanel();
		questionPanel.setSize(new Dimension(1200, 400));
		questionPanel.setLocation(0, 400);
		questionPanel.setLayout(null);
		
		JButton button1 = new JButton("Answer 1");
		button1.setSize(200, 35);
		button1.setLocation(50, 250);
		JButton button2 = new JButton("Answer 2");
		button2.setSize(200, 35);
		button2.setLocation(350, 250);
		JButton button3 = new JButton("Answer 3");
		button3.setSize(200, 35);
		button3.setLocation(650, 250);
		JButton button4 = new JButton("Answer 4");
		button4.setSize(200, 35);
		button4.setLocation(950, 250);
		
		questionPanel.add(button1);
		questionPanel.add(button2);
		questionPanel.add(button3);
		questionPanel.add(button4);
		

		
		container = jframe.getContentPane();
		container.setLayout(null);
		container.add(imageLabel);
		container.add(questionPanel);
		
		jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jframe.setVisible(true);
	}
	
	public static void main(String[] args) {
		Player p1 = new Player("Timmay", "login@domain.com", "gobbles", "/avatar");
		String category = "Star Wars";
		String q1 = "What is Luke's last name?";
		int d1 = 1;
		String a1 = "Skywalker";
		Question question1 = new Question(category, q1, a1, d1);
		question1.addIncorrect("Starkiller");
		question1.addIncorrect("Solo");
		question1.addIncorrect("McLucas");
		CategoryGame cm = new CategoryGame(p1, category, 1);
		CategoryGameUI cmui = new CategoryGameUI(cm);
	}

}
