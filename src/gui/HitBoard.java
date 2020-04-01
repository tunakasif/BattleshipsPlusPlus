package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.BattleBoard;

public class HitBoard extends JPanel
{
	// constants
	private static final int PREFERRED_DIMENSION = 400; 
	private static final Color BORDER_COLOR = Color.BLACK;
	private static final char HIT = 'x';
	private static final char MISS = 'o';

	// properties
	private BattleBoard board;
	private JLabel[][] labels;

	// constructor
	public HitBoard( BattleBoard board )
	{
		this.board = board;

		// setting layout
		this.setLayout( new GridLayout(board.getDimension() , board.getDimension()) );
		this.setBorder( BorderFactory.createLineBorder(BORDER_COLOR) );
		this.setPreferredSize( new Dimension( PREFERRED_DIMENSION , PREFERRED_DIMENSION ) );
		labels = new JLabel[board.getDimension()][board.getDimension()];

		// creating and adding game table
		for ( int i = 0; i < board.getDimension(); i++ ) 
		{
			for (int j = 0; j < board.getDimension(); j++ ) 
			{
				labels[j][i] = new JLabel( Character.toString( board.getHitBoard()[i][j]) );
				labels[j][i].setHorizontalAlignment( SwingConstants.CENTER);
				this.add( labels[j][i] );
			}
		}
		setBoard();
	}

	/**
	 * sets play board according to condition of the hitboard
	 */
	public void setBoard()
	{
		// method code
		for ( int i = 0; i < board.getDimension(); i++ ) 
		{
			for (int j = 0; j < board.getDimension(); j++ ) 
				labels[j][i].setText( Character.toString( board.getHitBoard()[j][i]) );
		}
		repaint();
	}

	/**
	 * paints board
	 */
	@Override
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );

		int increaseX = (int)(getSize().getWidth() / board.getDimension());
		int increaseY = (int)(getSize().getHeight()/ board.getDimension());

		for ( int i = 0; i < getSize().getWidth() - increaseX; i = i + increaseX ) 
		{
			for ( int j = 0; j < getSize().getHeight() - increaseY; j = j + increaseY ) 
			{
				g.drawRect(i, j, increaseX , increaseY );
				
				if ( board.getHitBoard()[(i / increaseX)][(j / increaseY)] == HIT )
				{
					g.setColor( Color.RED );
					g.fillRect(i, j, increaseX , increaseY );
				}
				
				else if ( board.getHitBoard()[(i / increaseX)][(j / increaseY)] == MISS )
				{
					g.setColor( Color.WHITE );
					g.fillRect(i, j, increaseX , increaseY );
				}
				g.setColor( Color.BLACK );
			}
		}
	}
}

