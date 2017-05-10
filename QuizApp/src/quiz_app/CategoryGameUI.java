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

	private CategoryGameUI(CategoryGame cm) {
		
		jframe = new JFrame();
		jframe.setTitle(cm.category);
		jframe.setSize(new Dimension(1200, 800));
		
		JLabel imageLabel = new JLabel();
		imageLabel.setSize(new Dimension(1188, 402));
		imageLabel.setLocation(6, 6);
		
		BufferedImage img = null;
		try {
			File file = new File(cm.category + ".png");
			img = ImageIO.read(file);
		} catch (IOException e) {
		}		
		ImageIcon nextIcon = new ImageIcon(img.getScaledInstance(1200, 400, img.SCALE_AREA_AVERAGING));
		imageLabel.setIcon(nextIcon);

		
		container = jframe.getContentPane();
		container.setLayout(null);
		container.add(imageLabel);
		
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
