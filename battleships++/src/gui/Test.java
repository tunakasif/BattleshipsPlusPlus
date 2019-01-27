package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import game.*;

/**
 * beforehand test for appearance of gui
 * @author Tuna Alikasifoglu
 * @version 9 May 2018
 *
 */
public class Test 
{
	// constants
	private static final int DIMENSION = 7;
	
	// methods
	public static void main( String[] args ) 
	{
		// constants
		final int FRAME_WIDTH = 700;
		final int FRAME_HEIGHT = 400;

		// variables
		JFrame frame;
		JPanel gamePanel;
		HitBoard hitPanel1;
		HitBoard hitPanel2;
		BattleBoard board1;
		AI computer;
		
		// program code
		frame = new JFrame();
		gamePanel = new JPanel();
		board1 = new BattleBoard( DIMENSION );
		
		generateBattleBoard( board1 );
		board1.takeHit(0, 0);
		board1.takeHit(6, 6);
		
		computer = new AI( board1 );
		hitPanel1 = new HitBoard( board1 );
		hitPanel2 = new HitBoard( computer.getBattleBoard() );
		
		gamePanel.add(hitPanel1);
		gamePanel.add(hitPanel2);
		frame.add( gamePanel );
		
		frame.setSize( FRAME_WIDTH, FRAME_HEIGHT );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible( true );
	}
	
	private static void generateBattleBoard( BattleBoard board ) 
	{
		// easy mode
		board.addShip(0, 0, 0);
		board.setOrientation(1, Ship.Orientation.VERTICAL);
		board.setOrientation(2, Ship.Orientation.VERTICAL);
		board.setOrientation(3, Ship.Orientation.VERTICAL);
		board.setOrientation(4, Ship.Orientation.VERTICAL);
		board.addShip(1, 0, 1);
		board.addShip(2, 1, 1);
		board.addShip(3, 2, 1);
		board.addShip(4, 3, 1);
	}

}
