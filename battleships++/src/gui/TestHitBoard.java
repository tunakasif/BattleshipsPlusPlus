package gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.BattleBoard;

public class TestHitBoard 
{
//	public static void main( String[] args ) 
//	{
//		// variables
//		JFrame frame;
//		BattleBoard board;
//		HitBoard hitPanel;
//		
//		// program code
//		frame = new JFrame();
//		frame.setLayout( new BorderLayout() );
//		frame.setSize( 500 , 500 );
//		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//		
//		board = new BattleBoard( 7 );
//		hitPanel = new HitBoard( board );
//		
//		frame.add( hitPanel );
//		frame.setSize(700, 700);;
//		frame.setVisible( true );
//		
//	}
	
	public static void main( String[] args ) 
	{
		// variables
		
		// method code
		JPanel total;
		BattleBoard board;
		JFrame frame = new JFrame();
		
		JLabel label1 = new JLabel( "Heading" );
		JLabel label2 = new JLabel( "Bottom" );
		
		label1.setHorizontalAlignment( SwingConstants.CENTER );
		label2.setHorizontalAlignment( SwingConstants.CENTER );
		
		board = new BattleBoard( 7 );
		
		total = new JPanel();
		
		HitBoard panel1 = new HitBoard( board );
		WarBoard panel2 = new WarBoard( board );
		
		frame.setLayout( new GridLayout( 1 , 2 ) );
		
		total.add(panel1);
		total.add( panel2 );
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add( new JLabel( "a" ) );
		frame.add( total );
		frame.setVisible( true );
	}
}
