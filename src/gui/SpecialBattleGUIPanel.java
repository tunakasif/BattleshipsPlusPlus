package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import game.*;
import game.SpecialBattle.SpecialCard;

/**
 * main game takes place in this panel 
 * @author Tuna Alikasifoglu
 * @version 9 May 2018
 *
 */
public class SpecialBattleGUIPanel extends JPanel
{
	// constants
	private static final int HEAD_FONT_SIZE = 30;
	private static final int BODY_FONT_SIZE = 20;
	private static final int RADIOBUTTON_FONT_SIZE = 20;
	private static final int BUTTON_FONT_SIZE = 20;
	private static final String FONT = "Courier";
	private static final Font HEAD_FONT = new Font( FONT , Font.BOLD , HEAD_FONT_SIZE);
	private static final Font BODY_FONT = new Font( FONT , Font.PLAIN , BODY_FONT_SIZE);
	private static final Font RADIO_FONT = new Font( FONT , Font.PLAIN , RADIOBUTTON_FONT_SIZE);
	private static final Font BUTTON_FONT = new Font( FONT , Font.PLAIN , BUTTON_FONT_SIZE);	
	private static final String INFO_TEXT = "<html> Cards: <br> <br>2X Shot: Gives two shots in one turn.<br>"
			+ "<br><br>Repair & Relocate: Repair a ship totally and<br>and let you set it to a new position "
			+ "<br>that didn't get hit You have to choose initial box of a ship."
			+ "<br><br>Shield: Gives protection to a single point<br>"
			+ "<br>MineBall: Transforms normal cannonball to mine.<br>If you hit a ship it functions"
			+ "as a normal it.<br>If you miss it becomes a mine to hunt<br> relocated"
			+ "ships of your opponent to sink entire ship.</html>";
	
	private static final String DOUBLE = "2X SHOT";
	private static final String RR = "REPAIR & RELOCATE";
	private static final String MINE = "MINEBALL";
	private static final String SHIELD = "SHIELD";

	private static final String ACTION_DOUBLE = "DOUBLE_SHOT";
	private static final String ACTION_RR = "REPAIR_RELOCATE";
	private static final String ACTION_MINE = "MINE_BALL";
	private static final String ACTION_SHIELD = "SHIELD";

	private static final int DELAY_MILISECONDS = 4000;
	private static final int TURN_OF_PLAYER1 = 1;

	// properties
	private BattleBoard myBoard;
	private SpecialBattle game;
	private AI computer;
	private Color shipColor;
	private boolean timerStarted;
	private int elapsedTime;
	boolean isShieldUsed; 
	boolean isDoubleUsed; 
	boolean isMineUsed; 

	// components
	// labels
	private JLabel infoTitle;
	private JLabel time;
	private JLabel turnLabel;
	private JLabel actionLabel;
	private JLabel specialActionLabel;
	private JLabel specialTitle;
	private JLabel reminder;
	private JLabel instructionsForCards;
	// Buttons
	private JButton useButton;
	private JButton forMoreInfo;
	private JButton restartButton;
	private JRadioButton doubleShotButton;
	private JRadioButton repairRelocateButton;
	private JRadioButton mineBallButton;
	private JRadioButton shieldButton;
	private ButtonGroup group;
	// Panels
	private HitBoard hitPanel;
	private WarBoard warPanel;
	private JPanel addHitPanel;
	private JPanel addWarPanel;
	private JPanel labelPanel;
	private JPanel buttonPanel;
	private JPanel radioPanel;
	// Event Handling
	private MyButtonListener buttonListener;
	private MyMouseListener mouseListener;
	private MyTimerListener timerListener;
	private NormalTimerListener normalTimer;
	private InfoButtonListener infoListener;
	private RestartButtonListener restartListener;
	private ShieldMouseListener shieldListener;
	private RRMouseListener rrListener;
	private Timer timer;
	private Timer totalTimer;

	// inner classes
	/**
	 * mouse listener for R&R
	 * @author Tuna Alikasifoglu
	 * @version 9 May 2018
	 *
	 */
	private class RRMouseListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed( MouseEvent e ) 
		{
			if ( !repairRelocateButton.isEnabled() )
			{
				if ( game.getBattleBoard().removeShip( e.getX() / ((warPanel.getWidth() / game.getDimension())) , 
						e.getY() / (warPanel.getHeight() / game.getDimension()) ) == -1 )
				{
					actionLabel.setText( "Select initial box of the ship!" );
				}
				
				else
				{
					warPanel.removeMouseListener( shieldListener );
					( new ShipDeploymentFrame( shipColor , game.getBattleBoard() , computer ,
							shieldButton.isEnabled() , doubleShotButton.isEnabled()
							, mineBallButton.isEnabled() , elapsedTime) ).setVisible( true );
					dispose();
				}
			}
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
		
	}
	
	/**
	 * mouse listener for shield
	 * @author Tuna Alikasifoglu
	 * @version 9 May 2018
	 *
	 */
	private class ShieldMouseListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed( MouseEvent e ) 
		{
			if ( !shieldButton.isEnabled() )
			{
				game.setShield( game.getTurn() , 
						e.getX() / ((warPanel.getWidth() / game.getDimension())) , 
						e.getY() / (warPanel.getHeight() / game.getDimension()) );
				warPanel.removeMouseListener( shieldListener );
				specialActionLabel.setText( String.format( "Special Condition: Shield added (%d , %d)" ,
						(e.getX() / ((warPanel.getWidth() / game.getDimension())) + 1) , 
						(e.getY() / (warPanel.getHeight() / game.getDimension())) + 1) );
			}
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
		
	}
	
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
			if ( !timerStarted )
			{
				timer.start();
				totalTimer.start();
			}

			if ( game.doesGameContinue() )
			{
				if ( group.getSelection() == null )
					specialActionLabel.setText( "Special Condition: Select A Special Card" );

				else
				{
					game.getDeck().get( getIndex( group.getSelection().getActionCommand() ) )
					.setActive(game.getTurn() , true);
					specialActionLabel.setText( "Special Condition: " + group.getSelection().getActionCommand()
							+ " used" );
					
					if ( group.getSelection().getActionCommand().equals( SpecialCard.SHIELD.name() ) )
						actionLabel.setText( "Select a spot on your WarBoard." );
					
					else if ( group.getSelection().getActionCommand().equals( SpecialCard.REPAIR_RELOCATE.name() ) )
						actionLabel.setText( "Select initial box of a ship." );
					
					group.getSelection().setEnabled( false );
					group.clearSelection();

				}
			}

			else
			{
				timer.stop();
				totalTimer.stop();
				useButton.setEnabled( false );
			}
			repaint();
		}

		private int getIndex( String name )
		{
			for ( int i = 0; i < game.getDeck().size(); i++ ) 
			{
				if ( game.getDeck().get(i).name().equals( name ) )
					return i;
			}
			return -1;
		}

	}
	
	/**
	 * action listener for button
	 * @author Tuna Alikasifoglu
	 * @version 9 May 2018
	 *
	 */
	private class InfoButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed( ActionEvent e ) 
		{
			instructionsForCards = new JLabel( INFO_TEXT );
			instructionsForCards.setFont( BODY_FONT );
			
			JOptionPane.showMessageDialog( forMoreInfo, instructionsForCards , "INSTRUCTIONS" , 1 , null );
		}
	}

	/**
	 * mouse listener for hit board
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
			if ( !timerStarted )
			{
				totalTimer.start();
				timer.start();
			}
			
			if ( game.doesGameContinue() )
			{
				if ( game.getTurn() == TURN_OF_PLAYER1 )
				{
					if ( game.play(e.getX() / ((hitPanel.getWidth() / game.getDimension())),
							e.getY() / (hitPanel.getHeight() / game.getDimension()) ) )
					{
						turnLabel.setText( "Turn: Your Turn" );
						turnLabel.setBackground( Color.GREEN );
						actionLabel.setText( String.format("You shot (%d , %d) and hit." , 
								(e.getX() / ((hitPanel.getWidth() / game.getDimension())) + 1),
								(e.getY() / (hitPanel.getHeight() / game.getDimension())) + 1) );
					}

					else
					{
						if ( game.getTurn() == TURN_OF_PLAYER1 )
						{
							turnLabel.setText( "Turn: Your Turn" );
							turnLabel.setBackground( Color.GREEN );
						}
						
						else
						{
							turnLabel.setText( "Turn: Opponent's Turn" );
							turnLabel.setBackground( Color.RED );
						}

						actionLabel.setText( String.format("You shot (%d , %d) and missed." , 
								(e.getX() / ((hitPanel.getWidth() / game.getDimension())) + 1),
								(e.getY() / (hitPanel.getHeight() / game.getDimension())) + 1) );
					}
					specialActionLabel.setText( "Special Condition: Special Card?" );
					hitPanel.setBoard();
					warPanel.setBoard();
				}
			}

			if ( !game.doesGameContinue() )
			{
				totalTimer.stop();
				timer.stop();
				turnLabel.setText( "Turn: Game Over" );
				actionLabel.setText( game.getGameCondition() );
				specialActionLabel.setText( "" );
				useButton.setEnabled( false );
				removeMouseListener( mouseListener );
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

	}

	/**
	 * timer listener for AI hits
	 * @author Tuna Alikasifoglu
	 * @version 9 May 2018
	 *
	 */
	private class MyTimerListener implements ActionListener
	{
		@Override
		public void actionPerformed( ActionEvent e ) 
		{
			// variables
			int[] target;

			// method code
			target = new int[ 2 ];

			if ( game.doesGameContinue() )
			{
				if ( game.getTurn() != TURN_OF_PLAYER1 )
				{
					target = computer.getTarget();
					if ( game.play( target[0] , target[1] ) )
					{
						turnLabel.setText( "Turn: Opponent's Turn" );
						turnLabel.setBackground( Color.RED );
						actionLabel.setText( String.format("Opponent shot (%d , %d) and hit" ,
								(target[0] + 1) , (target[1] + 1) ));
					}

					else
					{
						turnLabel.setText( "Turn: Your Turn" );
						turnLabel.setBackground( Color.GREEN );
						actionLabel.setText( String.format("Opponent shot (%d , %d) and missed" ,
								(target[0] + 1) , (target[1] + 1) ));	
					}
					specialActionLabel.setText( "Special Condition: Special Card?" );
					
					if ( game.getShield()[TURN_OF_PLAYER1 - 1] != null)
					{
						reminder.setText( String.format( "Shield at (%d , %d)" ,
								(game.getShield()[TURN_OF_PLAYER1 - 1].getX() + 1) , 
								(game.getShield()[TURN_OF_PLAYER1 - 1].getY() + 1) ));
					}
					
					else
						reminder.setText( "" );
					warPanel.setBoard();
				}
			}
			
			if ( !game.doesGameContinue() )
			{
				timer.stop();
				turnLabel.setText( "Turn: Game Over" );
				actionLabel.setText( game.getGameCondition() );
				specialActionLabel.setText( "" );
				useButton.setEnabled( false );
				removeMouseListener( mouseListener );
			}
			repaint();
		}

	}

	/**
	 * timer listener for game duration
	 * @author Tuna Alikasifoglu
	 * @version 9 May 2018
	 *
	 */
	private class NormalTimerListener implements ActionListener
	{
		@Override
		public void actionPerformed( ActionEvent e ) 
		{
			elapsedTime = elapsedTime + 1000;
			time.setText( "Game Duration: "+ (elapsedTime / 1000) + "s" );
			
			if ( game.getTurn() == TURN_OF_PLAYER1 )
				useButton.setEnabled( true );
			else
				useButton.setEnabled( false );
		}
		
	}
	
	// constructors
	public SpecialBattleGUIPanel( BattleBoard board , Color color )
	{
		shipColor = color;
		myBoard = board;
		computer = new AI( myBoard );
		game = new SpecialBattle( myBoard , computer.getBattleBoard() );
		createComponents();
	}
	
	public SpecialBattleGUIPanel( BattleBoard board , AI computer , Color color )
	{
		shipColor = color;
		myBoard = board;
		this.computer = computer;
		game = new SpecialBattle( myBoard , computer.getBattleBoard() );
		createComponents();
		warPanel.removeMouseListener( rrListener );
		repairRelocateButton.setEnabled( false );
		repaint();
	}
	
	public SpecialBattleGUIPanel( BattleBoard board , AI computer , Color color , 
			boolean isShieldUsed , boolean isDoubleUsed , boolean isMineUsed , int elapsedTime)
	{
		shipColor = color;
		myBoard = board;
		this.computer = computer;
		game = new SpecialBattle( myBoard , computer.getBattleBoard() );
		createComponents();
		warPanel.removeMouseListener( rrListener );
		repairRelocateButton.setEnabled( false );
		
		this.elapsedTime = elapsedTime;
		time.setText( "Game Duration: "+ (elapsedTime / 1000) + "s" );
		doubleShotButton.setEnabled( isDoubleUsed );
		mineBallButton.setEnabled( isMineUsed );
		shieldButton.setEnabled( isShieldUsed );
		group.clearSelection();
		
		repaint();
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
		hitPanel = new HitBoard( computer.getBattleBoard() );
		warPanel = new WarBoard( myBoard , shipColor );

		hitPanel.setBackground( Color.BLUE );
		warPanel.setBackground( Color.BLUE );

		hitPanel.addMouseListener( mouseListener );
		warPanel.addMouseListener( shieldListener );
		warPanel.addMouseListener( rrListener );
		
		addHitPanel = new JPanel();
		addWarPanel = new JPanel();
		
		addHitPanel.add( hitPanel );
		addWarPanel.add( warPanel );
		
		// label panel
		labelPanel = new JPanel();
		labelPanel.setLayout( new GridLayout( 7 , 1 ) );
		labelPanel.add( restartButton );
		labelPanel.add( infoTitle );
		labelPanel.add( time );
		labelPanel.add( turnLabel );
		labelPanel.add( reminder );
		labelPanel.add( actionLabel );
		labelPanel.add( specialActionLabel );

		// button panel
		radioPanel = new JPanel();
		buttonPanel = new JPanel();

		radioPanel.setLayout( new GridLayout( 2 , 2 ) );
		radioPanel.add( doubleShotButton );
		radioPanel.add( repairRelocateButton );
		radioPanel.add( mineBallButton );
		radioPanel.add( shieldButton );

		buttonPanel.setLayout( new GridLayout( 4 , 1 ) );
		buttonPanel.add( specialTitle );
		buttonPanel.add( forMoreInfo );
		buttonPanel.add( radioPanel );
		buttonPanel.add( useButton );

		// setting layout of the panel
		this.setLayout( new GridLayout( 2 , 2 , 20 , 20 ) );
		this.add( addHitPanel );
		this.add( labelPanel );
		this.add( addWarPanel );
		this.add( buttonPanel );
	}

	/**
	 * creates and sets buttons
	 */
	private void createButtons() 
	{
		// initializing
		useButton = new JButton( "Use Special Card" );
		useButton.setFont( BUTTON_FONT );
		
		forMoreInfo = new JButton( "Info About Special Cards" );
		forMoreInfo.setFont( BUTTON_FONT );
		
		restartButton = new JButton( "Restart" );
		restartButton.setFont( BUTTON_FONT );
		
		doubleShotButton = new JRadioButton( DOUBLE );
		doubleShotButton.setFont( RADIO_FONT );
		
		repairRelocateButton = new JRadioButton( RR );
		repairRelocateButton.setFont( RADIO_FONT );
		
		mineBallButton = new JRadioButton( MINE );
		mineBallButton.setFont( RADIO_FONT );
		
		shieldButton = new JRadioButton( SHIELD );
		shieldButton.setFont( RADIO_FONT );
		
		group = new ButtonGroup();

		// action commands
		doubleShotButton.setActionCommand( ACTION_DOUBLE );
		repairRelocateButton.setActionCommand( ACTION_RR );
		mineBallButton.setActionCommand( ACTION_MINE );
		shieldButton.setActionCommand( ACTION_SHIELD );

		// setting groups
		group.add( doubleShotButton );
		group.add( repairRelocateButton );
		group.add( mineBallButton );
		group.add( shieldButton );
		doubleShotButton.setSelected( true );
		
		// setting layout
		useButton.setHorizontalAlignment( (int)CENTER_ALIGNMENT );
		forMoreInfo.setHorizontalAlignment( (int)CENTER_ALIGNMENT );
		restartButton.setHorizontalAlignment( (int)CENTER_ALIGNMENT );

		// setting listeners
		useButton.addActionListener( buttonListener );
		forMoreInfo.addActionListener( infoListener );
		restartButton.addActionListener( restartListener );
	}

	/**
	 * creates and sets labels
	 */
	private void createLabels() 
	{
		// setting labels
		infoTitle = new JLabel( "GAME INFO" );
		infoTitle.setHorizontalAlignment( SwingConstants.CENTER );
		infoTitle.setFont( HEAD_FONT );
		
		time = new JLabel( "Game Duration: "+ elapsedTime + "s" );
		time.setHorizontalAlignment( SwingConstants.CENTER );
		time.setFont( BODY_FONT );
		
		turnLabel = new JLabel( "Turn: Your Turn");
		turnLabel.setHorizontalAlignment( SwingConstants.CENTER );
		turnLabel.setFont( BODY_FONT );
		turnLabel.setOpaque( true );
		turnLabel.setBackground( Color.GREEN );
		
		reminder = new JLabel();
		reminder.setHorizontalAlignment( SwingConstants.CENTER );
		reminder.setFont( BODY_FONT );
		
		actionLabel = new JLabel( "Condition: Let's Battle!" );
		actionLabel.setHorizontalAlignment( SwingConstants.CENTER );
		actionLabel.setFont( BODY_FONT );
		
		specialActionLabel = new JLabel( "Special Condition: Special Card?" );
		specialActionLabel.setHorizontalAlignment( SwingConstants.CENTER );
		specialActionLabel.setFont( BODY_FONT );
		
		specialTitle = new JLabel( "Special Cards" );
		specialTitle.setHorizontalAlignment( SwingConstants.CENTER );
		specialTitle.setFont( BODY_FONT );
	}

	/**
	 * creates and sets event handling components
	 */
	private void createEventHandling() 
	{
		// initializing
		elapsedTime = 0;
		timerStarted = false;

		// setting listeners
		infoListener = new InfoButtonListener();
		buttonListener = new MyButtonListener();
		mouseListener = new MyMouseListener();
		shieldListener = new ShieldMouseListener();
		rrListener = new RRMouseListener();
		restartListener = new RestartButtonListener();
		timerListener = new MyTimerListener();
		normalTimer = new NormalTimerListener();
		timer = new Timer( DELAY_MILISECONDS , timerListener );
		totalTimer = new Timer( 1000 , normalTimer);
	}
}
