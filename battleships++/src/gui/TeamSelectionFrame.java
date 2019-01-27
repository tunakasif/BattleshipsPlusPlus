package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Team selection to set color of the ships
 * @author Tuna Alikasifoglu
 * @version 9 May 2018
 *
 */
public class TeamSelectionFrame extends JFrame
{
	// constants
	private static final int HEAD_FONT_SIZE = 40;
	private static final int BUTTON_FONT_SIZE = 30;
	private static final String FONT = "Courier";
	private static final Font HEAD_FONT = new Font( FONT , Font.BOLD , HEAD_FONT_SIZE);
	private static final Font BUTTON_FONT = new Font( FONT , Font.PLAIN , BUTTON_FONT_SIZE);
	
	private static final String TITLE = "BATTLESHIPS++";
	private static final String TEAM1 = "PIRATES";
	private static final String TEAM2 = "NAVY";
	
	private static final int WIDTH = 700;
	private static final int HEIGTH = 500;	

	// properties

	// components
	// labels
	JLabel label;
	// buttons
	JButton teamButton1;
	JButton teamButton2;
	JButton backButton;
	// panels
	private JPanel buttonPanel;
	// Event Handling
	PirateButtonListener teamListener1;
	NavyButtonListener navyListener;
	BackButtonListener backListener;
	

	// inner classes
	/**
	 * action listener for button
	 * @author Tuna Alikasifoglu
	 * @version 9 May 2018
	 *
	 */
	private class PirateButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed( ActionEvent e ) 
		{
			(new ShipDeploymentFrame( Color.BLACK )).setVisible( true );
			dispose();
		}

	}
	
	/**
	 * action listener for button
	 * @author Tuna Alikasifoglu
	 * @version 9 May 2018
	 *
	 */
	private class NavyButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed( ActionEvent e ) 
		{
			(new ShipDeploymentFrame( Color.GRAY )).setVisible( true );
			dispose();
		}

	}
	
	/**
	 * action listener for button
	 * @author Tuna Alikasifoglu
	 * @version 9 May 2018
	 *
	 */
	private class BackButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed( ActionEvent e ) 
		{
			(new HomePageFrame()).setVisible( true );
			dispose();
		}

	}

	// constructors
	public TeamSelectionFrame()
	{
		this.setTitle( TITLE );
		this.setSize( WIDTH , HEIGTH );
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLayout( new GridLayout( 2, 1 ) );
		addComponents();
	}

	// methods
	/**
	 * adds components to frame
	 */
	private void addComponents() 
	{
		// event handling
		teamListener1 = new PirateButtonListener();
		navyListener = new NavyButtonListener();
		backListener = new BackButtonListener();
		
		// create labels
		label = new JLabel( "TEAM SELECTION" );
		label.setHorizontalAlignment( (int) CENTER_ALIGNMENT );
		label.setFont( HEAD_FONT );

		// create buttons
		teamButton1 = new JButton( TEAM1 );
		teamButton1.setFont( BUTTON_FONT );
		
		teamButton2 = new JButton( TEAM2 );
		teamButton2.setFont( BUTTON_FONT );
		
		backButton = new JButton( "BACK" );
		backButton.setFont( BUTTON_FONT );

		teamButton1.setPreferredSize( new Dimension( 60 , 40 ) );
		teamButton2.setPreferredSize( new Dimension( 60 , 40 ) ); 
		backButton.setPreferredSize( new Dimension( 60 , 40 ) );
		
		teamButton1.addActionListener( teamListener1 );
		teamButton2.addActionListener( navyListener );
		backButton.addActionListener( backListener );

		// create panel
		buttonPanel = new JPanel();
		buttonPanel.setLayout(  new GridLayout( 3 , 1 ) );

		buttonPanel.add( teamButton1 );
		buttonPanel.add( teamButton2 );
		buttonPanel.add( backButton );

		// create frame
		this.add( label );
		this.add( buttonPanel );
	}
}
