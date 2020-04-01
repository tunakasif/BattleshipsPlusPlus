package game;

import java.util.ArrayList;

import game.Ship.Orientation;

/**
 * By using Ship class creates a board for ships and 
 * take cares of the hit actions
 * @author Tuna Alikasifoglu
 * @version 5 May 2018
 *
 */
public class BattleBoard 
{
	// constants
	private static final int BOTH_DIRECTIONS = 0;
	private static final int HORIZONTAL = 1;
	private static final int VERTICAL = 2;
	private static final int UNAVAILABLE = -1;
	private static final int EMPTY = 0;
	private static final int OCCUPIED = 1;
	private static final char HIT = 'x';
	private static final char MISS = 'o';
	private static final char NOT_BEEN_HIT = '.';

	// properties
	private int[][] warBoard;
	private char[][] hitBoard;
	private int dimension;
	private int totalLives;
	private ArrayList<Ship> crew;

	// constructors
	public BattleBoard( int dimension )
	{
		if ( dimension < 5 )
			dimension = 5;
		this.dimension = dimension;
		warBoard = new int[dimension][dimension];
		hitBoard = new char[dimension][dimension];

		for ( int i = 0; i < dimension; i++ ) 
		{
			for ( int j = 0; j < dimension; j++ ) 
			{
				warBoard[j][i] = EMPTY;
				hitBoard[j][i] = NOT_BEEN_HIT;
			}
		}
		totalLives = 0;
		createCrew();
	}

	// methods
	/**
	 * 
	 * @return true if all the ships in the crew are located
	 */
	public boolean isBoardReady()
	{
		for ( Ship ship : crew ) 
		{
			if ( !ship.getLocated() )
				return false;
		}
		return true;
	}

	/**
	 * sets ships in the crew
	 */
	private void createCrew()
	{
		crew = new ArrayList<Ship>();

		for ( Ship.Type type : Ship.Type.values() ) 
		{
			crew.add( new Ship(type) );
		}
	}

	/**
	 * sets orientation of a ship in the crew
	 * @param index
	 * @param orientation
	 */
	public void setOrientation( int index , Ship.Orientation orientation )
	{
		crew.get( index ).setOrientation(orientation);
	}

	/**
	 * 
	 * @param initialPositionX
	 * @param initialPositionY
	 * @return index of the ship starts from that location
	 * if there are no ships return -1 
	 */
	private int getIndexByPosition( int initialPositionX , int initialPositionY )
	{
		for ( int i = 0; i < crew.size(); i++ ) 
		{
			if ( (crew.get( i ).getX() == initialPositionX) && (crew.get( i ).getY() == initialPositionY) )
				return i;
		}
		
		return UNAVAILABLE;
	}
	
	/**
	 * removes the ship with the given index from warBoard
	 * @param index
	 * @param initialPositionX
	 * @param initialPositionY
	 * @return 0 if both directions are available
	 * 1 if only horizontal is available
	 * 2 if only vertical is available
	 * -1 if no slot is available
	 */
	public int removeShip( int initialPositionX , int initialPositionY )
	{
		// variables
		int index;
		boolean horizontal;
		boolean vertical;
		Orientation orientation;

		// method code
		index = getIndexByPosition( initialPositionX , initialPositionY );
		
		if ( index == UNAVAILABLE )
			return UNAVAILABLE;
		
		horizontal = false;
		vertical = false;
		orientation = crew.get(index).getOrientation();

		// checks whether if there is another spot for the ship
		crew.get(index).setOrientation( Ship.Orientation.HORIZONTAL);
		for ( int i = 0; i <= (dimension - crew.get(index).getType().getLength()); i++ ) 
		{
			for ( int j = 0; j < dimension; j++ ) 
			{
				if ( checkSpaceForShip( index , i , j ) )
					horizontal = true;
			}
		}

		crew.get(index).setOrientation( Ship.Orientation.VERTICAL);
		for ( int i = 0; i < dimension; i++ ) 
		{
			for ( int j = 0; j <= (dimension - crew.get(index).getType().getLength()); j++ ) 
			{
				if ( checkSpaceForShip( index , i , j ) )
					vertical = true;
			}
		}
		crew.get(index).setOrientation( orientation );
		
		// removing ship and returning result
		if ( horizontal || vertical )
		{
			// removing ship
			crew.get(index).setLocated( false );
			addLives( (-1) * crew.get(index).getType().getLength() );
			
			if ( crew.get(index).getOrientation() == Ship.Orientation.HORIZONTAL )
			{
				for ( int i = initialPositionX; i < initialPositionX + crew.get(index).getType().getLength(); i++ )
				{
					if ( hitBoard[i][initialPositionY] == HIT )
					{
						addLives( 1 );
						hitBoard[i][initialPositionY] = MISS;
					}
					warBoard[i][initialPositionY] = EMPTY;
				}
			}
			
			else
			{
				for ( int i = initialPositionY; i < initialPositionY + crew.get(index).getType().getLength(); i++ )
				{
					if ( hitBoard[initialPositionX][i] == HIT )
					{
						addLives( 1 );
						hitBoard[i][initialPositionY] = MISS;
					}
					warBoard[initialPositionX][i] = EMPTY;
				}
			}
			
			// returning available spots
			if ( horizontal && vertical )
			{
				return BOTH_DIRECTIONS;
			}

			else if ( horizontal )
			{
				crew.get(index).setOrientation( Ship.Orientation.HORIZONTAL);
				return HORIZONTAL;
			}

			else if ( vertical )
			{
				crew.get(index).setOrientation( Ship.Orientation.VERTICAL);
				return VERTICAL;
			}
		}
		return UNAVAILABLE;
	}
	
	/**
	 * if the specified location is available changes the
	 * 0s to 1s at that location and increases the total length
	 * @param index
	 * @param initialPositionX
	 * @param initialPositionY
	 * @return true if that ship can be located
	 */
	public boolean addShip( int index , int initialPositionX , int initialPositionY )
	{
		if ( crew.get(index).getLocated() )
			return false;
		
		if ( checkSpaceForShip( index , initialPositionX , initialPositionY ) )
		{
			crew.get(index).setX( initialPositionX );
			crew.get(index).setY( initialPositionY );
			
			if ( crew.get(index).getOrientation() == Ship.Orientation.HORIZONTAL )
			{
				for ( int i = initialPositionX; i < initialPositionX + crew.get(index).getType().getLength(); i++ )
					warBoard[i][initialPositionY] = OCCUPIED;
			}

			else
			{
				for ( int i = initialPositionY; i < initialPositionY + crew.get(index).getType().getLength(); i++ )
					warBoard[initialPositionX][i] = OCCUPIED;
			}
			crew.get(index).setLocated( true );
			totalLives = totalLives + crew.get(index).getType().getLength();
			return true;
		}

		return false;
	}

	/**
	 * checks whether if the location specified is available 
	 * @param index
	 * @param initialPositionX
	 * @param initialPositionY
	 * @return true if space is empty for that ship
	 */
	private boolean checkSpaceForShip( int index , int initialPositionX , int initialPositionY )
	{
		
		if ( ( ((initialPositionX + crew.get(index).getType().getLength()) > this.dimension) && 
				(crew.get(index).getOrientation() == Ship.Orientation.HORIZONTAL) ) ||
				(((initialPositionY + crew.get(index).getType().getLength()) > this.dimension) && 
						(crew.get(index).getOrientation() == Ship.Orientation.VERTICAL) )
				|| ((crew.get(index).getType().getLength() <= 0) && (crew.get(index).getType().getLength() > dimension))
				|| (initialPositionX >= dimension) || (initialPositionY >= dimension))
		{
			return false;
		}


		if ( crew.get(index).getOrientation() == Ship.Orientation.HORIZONTAL )
		{
			for ( int i = initialPositionX; i < initialPositionX + crew.get(index).getType().getLength(); i++ )
			{
				if( (warBoard[i][initialPositionY] != EMPTY) || (hitBoard[i][initialPositionY] != NOT_BEEN_HIT) )
					return false;
			}
		}

		else
		{
			for ( int i = initialPositionY; i < initialPositionY + crew.get(index).getType().getLength(); i++ )
			{
				if( (warBoard[initialPositionX][i] != EMPTY) || (hitBoard[initialPositionX][i] != NOT_BEEN_HIT) )
					return false;
			}
		}

		return true;
	}

	/**
	 * 
	 * @return remaining lives 
	 */
	public int getLives()
	{
		return totalLives;
	}

	/**
	 * 
	 * @return dimension of the board
	 */
	public int getDimension()
	{
		return dimension;
	}
	
	/**
	 * 
	 * @return warBoard
	 */
	public int[][] getWarBoard()
	{
		return this.warBoard;
	}
	
	/**
	 * 
	 * @return hitBoard
	 */
	public char[][] getHitBoard()
	{
		return this.hitBoard;
	}

	/**
	 * 
	 * @return crew 
	 */
	public ArrayList<Ship> getCrew()
	{
		return crew;
	}

	/**
	 * increase the totalLives with given parameter
	 * @param lifePoint
	 */
	private void addLives( int lifePoint )
	{
		totalLives = totalLives + lifePoint;
	}

	/**
	 * shoots the given coordinates
	 * @param x
	 * @param y
	 * @return true if cannon ball hits the target
	 */
	public boolean takeHit( int x , int y )
	{
		if ( ((0 <= x) && (x < dimension)) && ((0 <= y) && (y < dimension)) )
		{	
			if ( hitBoard[x][y] == NOT_BEEN_HIT )
			{
				if ( warBoard[x][y] == OCCUPIED )
				{
					hitBoard[x][y] = HIT;
					this.addLives( -1 );
					return true;
				}
				hitBoard[x][y] = MISS;
				return false;
			}

			System.out.println( "You have already shot here" );
			return false;
		}
		return false;
	}

	/**
	 * prints the battle board to the console
	 */
	public void printBattleBoard()
	{
		System.out.print("  ");
		for ( int j = 0; j < dimension; j++ ) 
			System.out.print( j + " " );
		System.out.println();

		for ( int i = 0; i < dimension; i++ ) 
		{
			System.out.print( i + " " );
			for ( int j = 0; j < dimension; j++ ) 
				System.out.print( warBoard[j][i] + " " );
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * prints the hit board to the console
	 */
	public void printHitBoard()
	{
		System.out.print("  ");
		for ( int j = 0; j < dimension; j++ ) 
			System.out.print( j + " " );
		System.out.println();

		for ( int i = 0; i < dimension; i++ ) 
		{
			System.out.print( i + " " );
			for ( int j = 0; j < dimension; j++ ) 
				System.out.print( hitBoard[j][i] + " " );
			System.out.println();
		}
		System.out.println();
	}
}
