package game;

import game.Ship.Orientation;

/**
 * Enemy of the SinglePlayer game, computer itself.
 * Creates its own random BattleBoard and fights against you
 * @author Tuna Alikasifoglu
 * @version 5 May 2018
 *
 */
public class AI 
{	
	// constants
	private static final int NUMBER_OF_COORDINATES = 2;
	
	// properties
	private BattleBoard myBoard;
	private BattleBoard enemyBoard;
	private int dimension;
	private int[] target;
	
	// constructors
	public AI( BattleBoard enemyBoard )
	{
		this.enemyBoard = enemyBoard;
		this.dimension = enemyBoard.getDimension();
		target = new int[ NUMBER_OF_COORDINATES ];
		myBoard = new BattleBoard( dimension );
		generateBattleBoard();
	}
	
	// methods
	/**
	 * 
	 * @return BattleBoard of the AI
	 */
	public BattleBoard getBattleBoard()
	{
		return myBoard;
	}
	
	/**
	 * generates random BattleBoard
	 * now it only generates particular BattleBoard
	 * it is in beta stage 
	 */
	private void generateBattleBoard() 
	{
		// variables
		int[] target;
		
		// method code
		
		// setting orientations
		for ( int i = 0; i < myBoard.getCrew().size(); i++ ) 
			myBoard.getCrew().get( i ).setOrientation( generateOrientation() );
		
		// setting board
		target = new int[ 2 ];
		
		for ( int i = 0; i < myBoard.getCrew().size(); i++ ) 
		{
			do
			{
				target = generateRandomLocation( myBoard.getCrew().get( i ) );
			} while ( !myBoard.addShip( i, target[0], target[1]) );
		}
	}
	
	/** 
	 * generates suitable random location 
	 * @return generated location
	 */
	private int[] generateRandomLocation( Ship ship )
	{
		// variables
		int[] target;
		
		// method code
		target = new int[ 2 ];
		target[0] = (int)( Math.random() * (myBoard.getDimension() 
				- ship.getType().getLength() + 1) );
		target[1] = (int)( Math.random() * (myBoard.getDimension() 
				- ship.getType().getLength() + 1) );;
		
		return target;
	}
	
	/**
	 * generates 0 or 1
	 * @return HORIZONTAL if number is 0 return VERTICAL if number is 1
	 */
	private Orientation generateOrientation()
	{
		if ( (int)(Math.random() * 2) == 0 )
			return Orientation.HORIZONTAL;
		return Orientation.VERTICAL;
	}
	
	/**
	 * generates the target 
	 * @return the target 
	 */
	public int[] getTarget()
	{
		generateTarget();
		return target;
	}
	
	/**
	 * generates coordinates of target 
	 * now it only generates a random target
	 * it is in beta stage
	 */
	private void generateTarget() 
	{
		// easy mode
		target[0] = (int)(Math.random() * dimension);
		target[1] = (int)(Math.random() * dimension);
	}
}
