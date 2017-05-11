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
import java.util.Collections;

public class PlayerUI {
	 
	private final JFrame jframe;
	
	private Container container;
	
	public PlayerUI(Player player) {
		jframe = new JFrame();
		jframe.setTitle(player.getName());
		jframe.setSize(new Dimension(1200, 800));
		
		JLabel imageLabel = new JLabel();
		imageLabel.setSize(new Dimension(200, 200));
		imageLabel.setLocation(53, 6);
		
		JScrollPane scrollPaneCatScores = new JScrollPane();
		scrollPaneCatScores.setBounds(6, 281, 300, 419);
		
		BufferedImage img = null;
		try {
			File file = new File(player.getAvator());
			img = ImageIO.read(file);
		} catch (IOException e) {
		}		
		ImageIcon nextIcon = new ImageIcon(img.getScaledInstance(200, 200, img.SCALE_AREA_AVERAGING));
		imageLabel.setIcon(nextIcon);
		
		
		
		container = jframe.getContentPane();
		container.setLayout(null);
		container.add(imageLabel);
		container.add(scrollPaneCatScores);
		
		JLabel lblYourCategoryScores = new JLabel("Your Category Scores");
		lblYourCategoryScores.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourCategoryScores.setBounds(6, 264, 300, 16);
		jframe.getContentPane().add(lblYourCategoryScores);
		
		JButton btnNewButton = new JButton("Change your avatar");
		btnNewButton.setBounds(71, 218, 164, 29);
		jframe.getContentPane().add(btnNewButton);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(332, 6, 360, 694);
		jframe.getContentPane().add(infoPanel);
		infoPanel.setLayout(null);
		
		JLabel lblPlayerInfo = new JLabel("Your information");
		lblPlayerInfo.setBounds(6, 5, 348, 16);
		lblPlayerInfo.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(lblPlayerInfo);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(6, 49, 61, 16);
		infoPanel.add(lblLogin);
		
		JLabel lblPlayerLogin = new JLabel(player.getLogin());
		lblPlayerLogin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPlayerLogin.setBounds(214, 49, 146, 16);
		infoPanel.add(lblPlayerLogin);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(6, 90, 74, 16);
		infoPanel.add(lblPassword);
		
		int passwordLength = player.getPassword().length();
		
		JLabel lblNewLabel = new JLabel(String.join("", Collections.nCopies(passwordLength, "*")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(167, 90, 187, 16);
		infoPanel.add(lblNewLabel);
		
		JButton btnChangeYourLogin = new JButton("Change login");
		btnChangeYourLogin.setBounds(6, 139, 155, 29);
		infoPanel.add(btnChangeYourLogin);
		
		JButton btnNewButton_1 = new JButton("Change password");
		btnNewButton_1.setBounds(199, 139, 155, 29);
		infoPanel.add(btnNewButton_1);
		jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jframe.setVisible(true);
		
	}
	public static void main(String[] args) {
		Player p1 = new Player("Timmay", "login@domain.com", "gobbles", "Star Wars.png");
		PlayerUI pUI = new PlayerUI(p1);
	}
}
