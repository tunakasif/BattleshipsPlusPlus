package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import game.*;
import game.Ship.Orientation;

/**
 * take cares of setting ships into the board
 * @author Tuna Alikasifoglu
 * @version 9 May 2018
 *
 */
public class ShipDeploymentPanel extends JPanel
{
	// constants
	private static final String VERTICAL_BUTTON = "VERTICAL";
	private static final String HORIZONTAL_BUTTON = "HORIZONTAL";
	private static final int DIMENSION = 7;
	
	private static final int HEAD_FONT_SIZE = 30;
	private static final int BODY_FONT_SIZE = 20;
	private static final int RADIOBUTTON_FONT_SIZE = 20;
	private static final int BUTTON_FONT_SIZE = 20;
	private static final String FONT = "Courier";
	private static final Font HEAD_FONT = new Font( FONT , Font.BOLD , HEAD_FONT_SIZE);
	private static final Font BODY_FONT = new Font( FONT , Font.PLAIN , BODY_FONT_SIZE);
	private static final Font RADIO_FONT = new Font( FONT , Font.PLAIN , RADIOBUTTON_FONT_SIZE);
	private static final Font BUTTON_FONT = new Font( FONT , Font.PLAIN , BUTTON_FONT_SIZE);	

	// properties
	private BattleBoard board;
	private AI computer;
	private Color shipColor;
	private boolean isShieldUsed;
	private boolean isDoubleUsed;
	private boolean isMineUsed; 
	private int elapsedTime;

	// components
	// labels
	private JLabel command0;
	private JLabel command1;
	private JLabel command2;
	private JLabel command3;
	private JLabel command4;
	private JLabel condition;
	private JLabel shipTitle;
	private JLabel orientationTitle;

	// Buttons
	private JButton startButton;
	private JButton challengeButton;
	private JButton restartButton;
	private JRadioButton verticalButton;
	private JRadioButton horizontalButton;
	private ButtonGroup shipGroup;
	private ButtonGroup orientationGroup;
	// Panels
	private JPanel radioPanel;
	private JPanel buttonPanel;
	private WarBoard boardPanel;
	private JPanel addWarPanel;
	private JPanel labelPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	// Event Handling
	private MyButtonListener buttonListener;
	private MyMouseListener mouseListener;
	private TrialButtonListener trialListener;
	private RestartButtonListener restartListener;


	// inner classes
	/**
	 * action listener for button
	 * @author Tuna Alikasifoglu
	 * @version 9 May 2018
	 *
	 */
	private class RestartButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed( ActionEvent e ) 
		{
			( new TeamSelectionFrame() ).setVisible( true );
			dispose();
		}
	}

	/**
	 * action listener for button
	 * @author Tuna Alikasifoglu
	 * @version 9 May 2018
	 *
	 */
	private class MyButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed( ActionEvent e ) 
		{
			removeAll();	
			repaint();
			dispose();

			if ( computer == null )
				(new SpecialBattleFrame( board , shipColor )).setVisible( true );

			else
				(new SpecialBattleFrame( board , computer , shipColor , isShieldUsed , isDoubleUsed , isMineUsed , elapsedTime )).setVisible( true );
		}

	}

	/**
	 * action listener for button
	 * @author Tuna Alikasifoglu
	 * @version 9 May 2018
	 *
	 */
	private class TrialButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed( ActionEvent e ) 
		{
			startButton.setEnabled( true );
			repaint();
		}

	}

	/**
	 * mouse listener for hitboard
	 * @author Tuna Alikasifoglu
	 * @version 9 May 2018
	 *
	 */
	private class MyMouseListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed( MouseEvent e )  
		{
			if ( shipGroup.getSelection() == null )
				condition.setText( "Condition: Please select a ship." );

			else
			{
				if ( orientationGroup.getSelection().getActionCommand().equals(HORIZONTAL_BUTTON) )
					board.getCrew().get( getIndex(shipGroup.getSelection().getActionCommand()))
					.setOrientation(Orientation.HORIZONTAL);
				else
					board.getCrew().get( getIndex(shipGroup.getSelection().getActionCommand()))
					.setOrientation(Orientation.VERTICAL);

				if ( board.addShip( getIndex( shipGroup.getSelection().getActionCommand() ) , 
						e.getX() / ( (boardPanel.getWidth() / board.getDimension()) ), 
						e.getY() / (boardPanel.getHeight() / board.getDimension()) ) )
				{
					condition.setText( "Condition: " + shipGroup.getSelection().getActionCommand()
							+ " added." );
					shipGroup.getSelection().setEnabled( false );
					shipGroup.clearSelection();
				}

				else
					condition.setText( "Condition: Not Avaliable." );

				if ( board.isBoardReady() )
					startButton.setEnabled( true );
			}
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		private int getIndex( String name )
		{	
			for ( int i = 0; i < board.getCrew().size(); i++ ) 
			{
				if ( board.getCrew().get(i).getType().name().equals( name ) )
					return i;
			}

			return -1;
		}
	}

	// constructors
	public ShipDeploymentPanel( Color color )
	{
		shipColor = color;
		board = new BattleBoard( DIMENSION );
		createComponents();
	}

	public ShipDeploymentPanel( Color color , BattleBoard board )
	{
		shipColor = color;
		this.board = board;
		createComponents();
	}

	public ShipDeploymentPanel( Color color , BattleBoard board , AI computer )
	{
		shipColor = color;
		this.board = board;
		this.computer = computer;
		createComponents();
	}

	public ShipDeploymentPanel( Color color , BattleBoard board , AI computer , 
			boolean isShieldUsed , boolean isDoubleUsed, boolean isMineUsed , int elapsedTime )
	{
		shipColor = color;
		this.board = board;
		this.computer = computer;
		this.isShieldUsed = isShieldUsed;
		this.isDoubleUsed = isDoubleUsed;
		this.isMineUsed = isMineUsed;
		this.elapsedTime = elapsedTime;
		createComponents();
	}

	// methods
	/**
	 * to dispose frame from a panel
	 */
	private void dispose() 
	{
		SwingUtilities.getWindowAncestor( this ).dispose();
	}

	/**
	 * creates all components
	 */
	private void createComponents() 
	{
		createEventHandling();
		createLabels();
		createButtons();
		createPanels();
	}

	/**
	 * creates and sets panels
	 */
	private void createPanels() 
	{
		// variables
		JRadioButton button;

		// method code
		// board panel
		boardPanel = new WarBoard( board , shipColor );
		boardPanel.setBackground( Color.BLUE );
		boardPanel.addMouseListener( mouseListener );

		addWarPanel = new JPanel();
		addWarPanel.add( boardPanel );

		// panel for radio buttons
		radioPanel = new JPanel();
		radioPanel.setLayout( new BoxLayout( radioPanel , BoxLayout.Y_AXIS ) );
		radioPanel.add( orientationTitle );
		radioPanel.add( horizontalButton );
		radioPanel.add( verticalButton );

		// ship buttons
		buttonPanel = new JPanel();
		buttonPanel.setLayout( new BoxLayout( buttonPanel , BoxLayout.Y_AXIS) );
		buttonPanel.add( shipTitle );
		for ( int i = 0; i <  board.getCrew().size(); i++ ) 
		{
			button = new JRadioButton( board.getCrew().get(i).getType().name() + ", " 
					+ board.getCrew().get(i).getType().getLength() );
			button.setFont( RADIO_FONT );
			button.setActionCommand(board.getCrew().get(i).getType().name() );

			for ( Ship ship : board.getCrew() ) 
			{
				if ( ship.getLocated() && button.getActionCommand().equals(ship.getType().name()) )
					button.setEnabled( false );
			}

			shipGroup.add( button );
			buttonPanel.add( button );
			button.setSelected( true );
		}

		// labels
		labelPanel = new JPanel();
		labelPanel.setLayout( new BoxLayout( labelPanel , BoxLayout.Y_AXIS) );
		labelPanel.add( command0 );
		labelPanel.add( command1 );
		labelPanel.add( command2 );
		labelPanel.add( command3 );
		labelPanel.add( command4 );
		labelPanel.add( new JLabel( "--------" ) );
		labelPanel.add( condition );
		labelPanel.add( new JLabel( "--------" ) );


		// left panel
		leftPanel = new JPanel();
		leftPanel.setLayout( new GridLayout( 3 , 1) );

		leftPanel.add( labelPanel );
		leftPanel.add( buttonPanel );
		leftPanel.add( radioPanel );

		// right panel
		rightPanel = new JPanel();
		rightPanel.setLayout( new BoxLayout( rightPanel , BoxLayout.Y_AXIS ) );

		rightPanel.add( addWarPanel );
		rightPanel.add( startButton );
		rightPanel.add( challengeButton );
		rightPanel.add( restartButton );


		// setting layout of the ShipDeployment Panel
		this.setLayout( new GridLayout( 1 , 2 , 30 , 30 ) );
		this.add( leftPanel );
		this.add( rightPanel );
		this.setPreferredSize( new Dimension( 900 , 500 ) );


	}

	/**
	 * creates and sets buttons
	 */
	private void createButtons() 
	{
		// initializing
		startButton = new JButton( "Start Game" );
		startButton.setFont( BUTTON_FONT );
		
		challengeButton = new JButton( "Challenge?" );
		challengeButton.setFont( BUTTON_FONT );
		
		restartButton = new JButton( "Restart" );
		restartButton.setFont( BUTTON_FONT );
		
		horizontalButton = new JRadioButton( HORIZONTAL_BUTTON );
		horizontalButton.setFont( RADIO_FONT );
		
		verticalButton = new JRadioButton( VERTICAL_BUTTON );
		verticalButton.setFont( RADIO_FONT );
		
		orientationGroup = new ButtonGroup();
		shipGroup = new ButtonGroup();

		// setting action commands
		horizontalButton.setActionCommand( HORIZONTAL_BUTTON );
		verticalButton.setActionCommand( VERTICAL_BUTTON );

		// setting group
		orientationGroup.add( horizontalButton );
		orientationGroup.add( verticalButton );
		horizontalButton.setSelected( true );

		// setting layout
		challengeButton.setHorizontalAlignment( SwingConstants.CENTER );
		startButton.setHorizontalAlignment( SwingConstants.CENTER );
		restartButton.setHorizontalAlignment( SwingConstants.CENTER );

		// setting listeners
		challengeButton.addActionListener( trialListener );
		startButton.addActionListener( buttonListener );
		restartButton.addActionListener( restartListener );
		startButton.setEnabled( false );
	}

	/**
	 * creates and sets labels
	 */
	private void createLabels() 
	{
		// setting labels
		command0 = new JLabel( "Commands" );
		command0.setFont( new Font("Courier", Font.BOLD , HEAD_FONT_SIZE) );
		command0.setHorizontalAlignment( (int)CENTER_ALIGNMENT );
		
		command1 = new JLabel( "1-) Choose a ship" );
		command1.setFont( BODY_FONT );
		
		command2 = new JLabel( "2-) Select Orientation" );
		command2.setFont( BODY_FONT );
		
		command3 = new JLabel( "3-) Click for initial position" );
		command3.setFont( BODY_FONT );
		
		command4 = new JLabel( "4-) Start Game" );
		command4.setFont( BODY_FONT );
		
		condition = new JLabel( "Condition: Start Deployment!" );
		condition.setFont( BODY_FONT );
		condition.setHorizontalAlignment( (int)CENTER_ALIGNMENT );
		
		// button headers
		shipTitle = new JLabel( "Select Orientation: " );
		shipTitle.setFont( HEAD_FONT );
		
		orientationTitle = new JLabel( "Select Orientation: " );
		orientationTitle.setFont( HEAD_FONT ); 
	}

	/**
	 * creates and sets buttons
	 */
	private void createEventHandling() 
	{
		// setting listeners
		trialListener = new TrialButtonListener();
		buttonListener = new MyButtonListener();
		mouseListener = new MyMouseListener();
		restartListener = new RestartButtonListener();
	}
}
