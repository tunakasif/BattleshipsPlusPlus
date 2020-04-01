package gui;

import java.awt.Color;
import java.awt.Graphics;

import game.BattleBoard;

/**
 * interface of player's warBoard
 * extends hit board because it is simply same 
 * thing with visible ships 
 * @author Tuna Alikasifoglu
 * @version 9 May 2018
 *
 */
public class WarBoard extends HitBoard
{
	// constants
	private static final int EMPTY = 0;
	private static final int OCCUPIED = 1;
	private static final char HIT = 'x';
	private static final char MISS = 'o';
	private static final char NOT_BEEN_HIT = '.';

	// properties
	private BattleBoard board;
	private Color shipColor;

	// constructors
	public WarBoard( BattleBoard board )
	{
		super( board );
		this.board = board;
		shipColor = Color.BLACK;
	}
	
	public WarBoard( BattleBoard board , Color color )
	{
		super( board );
		this.board = board;
		shipColor = color;
	}

	// methods
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
				if ( (board.getWarBoard()[(i / increaseX)][(j / increaseY)] == OCCUPIED) 
						&& (board.getHitBoard()[(i / increaseX)][(j / increaseY)] == NOT_BEEN_HIT) )
				{
					g.setColor( shipColor );
					g.fillRect(i, j, increaseX , increaseY );
				}
				
				else if ( (board.getWarBoard()[(i / increaseX)][(j / increaseY)] == OCCUPIED) 
						&& (board.getHitBoard()[(i / increaseX)][(j / increaseY)] == HIT) )
				{
					g.setColor( Color.RED );
					g.fillRect(i, j, increaseX , increaseY );
				}
				
				else if ( (board.getWarBoard()[(i / increaseX)][(j / increaseY)] == EMPTY) 
						&& (board.getHitBoard()[(i / increaseX)][(j / increaseY)] == MISS) )
				{
					g.setColor( Color.WHITE );
					g.fillRect(i, j, increaseX , increaseY );
				}
				g.setColor( shipColor );
			}
		}


	}
}
