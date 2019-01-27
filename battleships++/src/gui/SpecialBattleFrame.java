package gui;

import java.awt.Color;

import javax.swing.JFrame;

import game.AI;
import game.BattleBoard;

/**
 * frame version of special battle panel
 * for easier navigation
 * @author Tuna Alikasifoglu
 * @version 9 May 2018
 *
 */
public class SpecialBattleFrame extends JFrame
{
	// constants
	private static final String TITLE = "BATTLESHIPS++";
	private static final int WIDTH = 1000;
	private static final int HEIGTH = 1200;	
	
	// panels
	private SpecialBattleGUIPanel panel;

	// constructors
	public SpecialBattleFrame( BattleBoard board , Color color )
	{
		this.setTitle( TITLE );
		this.setSize( WIDTH , HEIGTH );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLocationRelativeTo( null );
		addComponents( board , color );
	}
	
	public SpecialBattleFrame( BattleBoard board , AI computer , Color color )
	{
		this.setTitle( TITLE );
		this.setSize( WIDTH , HEIGTH );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLocationRelativeTo( null );
		addComponents( board , computer , color );
	}
	
	public SpecialBattleFrame( BattleBoard board , AI computer , Color color, 
			boolean isShieldUsed , boolean isDoubleUsed , boolean isMineUsed , int elapsedTime  )
	{
		this.setTitle( TITLE );
		this.setSize( WIDTH , HEIGTH );
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLocationRelativeTo( null );
		addComponents( board , computer , color, isShieldUsed , isDoubleUsed , isMineUsed , elapsedTime );
	}

	// methods
	/**
	 * adds panel to frame
	 * @param board
	 * @param color
	 */
	private void addComponents( BattleBoard board , Color color ) 
	{
		panel = new SpecialBattleGUIPanel( board , color );

		// create frame
		this.add( panel );
	}
	
	/**
	 * adds panel to frame
	 * @param board
	 * @param computer
	 * @param color
	 */
	private void addComponents( BattleBoard board , AI computer , Color color ) 
	{
		panel = new SpecialBattleGUIPanel( board , computer , color );

		// create frame
		this.add( panel );
	}
	
	/**
	 * adds panel to frame
	 * @param board
	 * @param computer
	 * @param color
	 * @param isShieldUsed
	 * @param isDoubleUsed
	 * @param isMineUsed
	 * @param elapsedTime
	 */
	private void addComponents( BattleBoard board , AI computer , Color color , 
			boolean isShieldUsed , boolean isDoubleUsed , boolean isMineUsed , int elapsedTime ) 
	{
		panel = new SpecialBattleGUIPanel( board , computer , color , 
				isShieldUsed , isDoubleUsed , isMineUsed , elapsedTime  );

		// create frame
		this.add( panel );
	}
}
