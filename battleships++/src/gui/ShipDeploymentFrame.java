package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.AI;
import game.BattleBoard;

/**
 * frame version of ship deployment panel
 * for easier navigation
 * @author Tuna Alikasifoglu
 * @version 9 May 2018
 *
 */
public class ShipDeploymentFrame extends JFrame
{
	// constants
	private static final String TITLE = "BATTLESHIPS++";
	private static final int WIDTH = 900;
	private static final int HEIGTH = 700;	

	// properties
	private Color shipColor;
	
	// panels
	private ShipDeploymentPanel panel;

	// constructors
	public ShipDeploymentFrame( Color color )
	{
		shipColor = color;
		this.setTitle( TITLE );
		this.setSize( WIDTH , HEIGTH );
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLayout( new BorderLayout() );
		addComponents();
	}
	
	public ShipDeploymentFrame( Color color , BattleBoard board )
	{
		shipColor = color;
		this.setTitle( TITLE );
		this.setSize( WIDTH , HEIGTH );
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLayout( new BorderLayout() );
		addComponents( board );
	}
	
	public ShipDeploymentFrame( Color color , BattleBoard board , AI computer )
	{
		shipColor = color;
		this.setTitle( TITLE );
		this.setSize( WIDTH , HEIGTH );
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLayout( new BorderLayout() );
		addComponents( board , computer );
	}
	
	public ShipDeploymentFrame( Color color , BattleBoard board , AI computer , 
			boolean isShieldUsed , boolean isDoubleUsed, boolean isMineUsed , int elapsedTime )
	{
		shipColor = color;
		this.setTitle( TITLE );
		this.setSize( WIDTH , HEIGTH );
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLayout( new BorderLayout() );
		addComponents( board , computer , isShieldUsed , isDoubleUsed, isMineUsed , elapsedTime );
	}

	// methods
	/**
	 * add panel to the frame
	 */
	private void addComponents() 
	{
		panel = new ShipDeploymentPanel( shipColor );
		
		// create frame
		this.add( panel , BorderLayout.CENTER );
	}
	
	/**
	 * add panel to the frame
	 */
	private void addComponents( BattleBoard board ) 
	{
		panel = new ShipDeploymentPanel( shipColor , board );
		
		// create frame
		this.add( panel , BorderLayout.CENTER );
	}
	
	/**
	 * add panel to the frame
	 */
	private void addComponents( BattleBoard board , AI computer ) 
	{
		panel = new ShipDeploymentPanel( shipColor , board , computer );
		
		// create frame
		this.add( panel , BorderLayout.CENTER );
	}
	
	/**
	 * add panel to frame
	 * @param board
	 * @param computer
	 * @param isShieldUsed
	 * @param isDoubleUsed
	 * @param isMineUsed
	 * @param elapsedTime
	 */
	private void addComponents( BattleBoard board , AI computer , 
			boolean isShieldUsed , boolean isDoubleUsed, boolean isMineUsed , int elapsedTime ) 
	{
		// method code
		panel = new ShipDeploymentPanel( shipColor ,  board , computer , isShieldUsed , isDoubleUsed, isMineUsed , elapsedTime );
		
		// create frame
		this.add( panel , BorderLayout.CENTER );
	}
}
