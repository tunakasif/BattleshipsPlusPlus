package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * home page of the project, a warm welcome
 * 
 * @author Tuna Alikasifoglu
 * @version 9 May 2018
 *
 */
public class HomePageFrame extends JFrame {
	// constants
	private static final int HEAD_FONT_SIZE = 40;
	private static final int BODY_FONT_SIZE = 20;
	private static final int BUTTON_FONT_SIZE = 30;
	private static final String FONT = "Courier";
	private static final Font HEAD_FONT = new Font(FONT, Font.BOLD, HEAD_FONT_SIZE);
	private static final Font BODY_FONT = new Font(FONT, Font.PLAIN, BODY_FONT_SIZE);
	private static final Font BUTTON_FONT = new Font(FONT, Font.PLAIN, BUTTON_FONT_SIZE);

	private static final String TITLE = "BATTLESHIPS++";
	private static final String INSTRUCTIONS = "<html>To understand the application "
			+ "\"Battleships++\", user must be familiar with<br>the traditional "
			+ "game of Battleships. This project is an extended version<br>of "
			+ "the traditional game Battleships.According to Wikipedia definition;"
			+ "<br>\"Battleships is a guessing game for two players. It is played on ruled"
			+ "<br>grids (paper or board) on which the players' fleets of ships are marked."
			+ " <br>The locations of the fleet are concealed from the other player. "
			+ "Players <br>alternate turns calling \"shots\" at the other player's ships,"
			+ " i.e. calling <br>a specific spot as A3, and the objective of the game is "
			+ "to destroy the <br>opposing player's fleet.\" Battleships++, as it can be"
			+ " understood from its <br>name, extends the gameplay of traditional game and "
			+ "coverts it to a desktop <br>application.</html>";
	private static final String ABOUT_US = "<html>We Are The Captains!<br>"
			+ "<br>Tuna Alikaşifoğlu <br>Gökay Tamzaralıoğlu "
			+ "<br>Alperen Balcı <br>Ömer Faruk Oflaz <br><br>Thank you for" + " choosing us</html>";

	private static final int WIDTH = 700;
	private static final int HEIGTH = 500;

	// components
	// images
	private ImageIcon logo;
	// labels
	private JLabel welcome;
	private JLabel message;
	private JLabel icon;
	// buttons
	private JButton playButton;
	private JButton infoButton;
	private JButton aboutButton;
	// panels
	private JPanel buttonPanel;
	// Event Handling
	private PlayButtonListener playListener;
	private InfoButtonListener infoListener;
	private AboutButtonListener aboutListener;

	// inner classes
	/**
	 * action listener for button
	 * 
	 * @author Tuna Alikasifoglu
	 * @version 9 May 2018
	 *
	 */
	private class PlayButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			(new TeamSelectionFrame()).setVisible(true);
			dispose();
		}

	}

	/**
	 * action listener for button
	 * 
	 * @author Tuna Alikasifoglu
	 * @version 9 May 2018
	 *
	 */
	private class InfoButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			message = new JLabel(INSTRUCTIONS);
			message.setFont(BODY_FONT);
			JOptionPane.showMessageDialog(infoButton, message, "INSTRUCTIONS", 1);
		}

	}

	/**
	 * action listener for button
	 * 
	 * @author Tuna Alikasifoglu
	 * @version 9 May 2018
	 *
	 */
	private class AboutButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			message = new JLabel(ABOUT_US);
			message.setFont(BODY_FONT);
			JOptionPane.showMessageDialog(aboutButton, message, "INSTRUCTIONS", 1);
		}

	}

	// constructors
	public HomePageFrame() {
		this.setTitle(TITLE);
		this.setSize(WIDTH, HEIGTH);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(3, 1));
		addComponents();
	}

	// methods
	public static void main(String[] args) {
		// variables
		HomePageFrame frame;

		// program code
		frame = new HomePageFrame();
		frame.setVisible(true);
	}

	/**
	 * add components to the frame
	 */
	private void addComponents() {
		logo = new ImageIcon("src/gui/images/logo.jpeg");
		icon = new JLabel();
		icon.setIcon(logo);
		icon.setHorizontalAlignment(SwingConstants.CENTER);

		// event handling
		playListener = new PlayButtonListener();
		infoListener = new InfoButtonListener();
		aboutListener = new AboutButtonListener();

		// create labels
		welcome = new JLabel("WELCOME TO BATTLESHIPS++");
		welcome.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		welcome.setFont(HEAD_FONT);

		// create buttons
		playButton = new JButton("PLAY");
		playButton.setFont(BUTTON_FONT);

		infoButton = new JButton("INSTRUCTIONS");
		infoButton.setFont(BUTTON_FONT);

		aboutButton = new JButton("ABOUT US");
		aboutButton.setFont(BUTTON_FONT);

		playButton.setPreferredSize(new Dimension(60, 40));
		infoButton.setPreferredSize(new Dimension(60, 40));
		aboutButton.setPreferredSize(new Dimension(60, 40));

		// listeners
		playButton.addActionListener(playListener);
		infoButton.addActionListener(infoListener);
		aboutButton.addActionListener(aboutListener);

		// create panel
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 1));

		buttonPanel.add(playButton);
		buttonPanel.add(infoButton);
		buttonPanel.add(aboutButton);

		// create frame
		this.add(icon);
		this.add(welcome);
		this.add(buttonPanel);
	}

}
