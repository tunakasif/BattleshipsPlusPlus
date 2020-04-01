package game;

/**
 * By using BattleBoards crates and initiates a BattleShip Game
 * @author Tuna Alikasifoglu
 * @version 5 May 2018
 *
 */
public class Battle 
{
	// constants
	private static final int TURN_OF_PLAYER_1 = 1;
	private static final int TURN_OF_PLAYER_2 = 2;

	// properties
	private BattleBoard board1;
	private BattleBoard board2;
	private String gameCondition;
	private int turn;

	// constructors
	public Battle( BattleBoard board1 , BattleBoard board2 )
	{
		this.board1 = board1;
		this.board2 = board2;
		this.turn = 1;
	}

	// methods
	/**
	 * checks if the game is still going on
	 */
	public boolean doesGameContinue()
	{
		setGameCondition();
		return ( (board1.getLives() > 0) && (board2.getLives() > 0) );
	}

	/**
	 * sets the game condition according to game data 
	 */
	private void setGameCondition()
	{
		if ( board1.getLives() == 0 )
			gameCondition = "Game is over!\nOpponent is the winner.";
		else if ( board2.getLives() == 0 )
			gameCondition = "Game is over!\nYou are the winner.";
		else
			gameCondition = "Game is still in progress.";
	}

	/**
	 * @return gameCondition
	 */
	public String getGameCondition()
	{
		return gameCondition;
	}

	/**
	 * shoots to the given point according to the turn of the shooter
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean play( int x , int y )
	{
		if ( doesGameContinue() )
		{
			if ( turn == TURN_OF_PLAYER_1 )
			{
				if ( board2.takeHit(x , y) )
				{
					turn = TURN_OF_PLAYER_1;
					System.out.printf( "Player1 shot (%d , %d) and hit! \n" , x , y );
					return true;
				}

				turn = TURN_OF_PLAYER_2;
				System.out.printf( "Player1 shot (%d , %d) and missed! \n" , x , y );
				return false;
			}

			else
			{
				if ( board1.takeHit(x , y) )
				{
					turn = TURN_OF_PLAYER_2;
					System.out.printf( "Player2 shot (%d , %d) and hit! \n" , x , y );
					return true;
				}

				turn = TURN_OF_PLAYER_1;
				System.out.printf( "Player2 shot (%d , %d) and missed! \n" , x , y );
				return false;
			}
		}

		else
			System.out.println( gameCondition );

		return false;
	}
	
	/**
	 * 
	 * @return dimension of the game
	 */
	public int getDimension()
	{
		return board1.getDimension();
	}
	
	public void setBattleBoard( BattleBoard board ) 
	{
		this.board1 = board;
	}
	
	/**
	 * 
	 * @return board of player1
	 */
	public BattleBoard getBattleBoard()
	{
		return board1;
	}
	
	/**
	 * 
	 * @return whose turn it is 1 for player1 2 for player2 
	 */
	public int getTurn()
	{
		return this.turn;
	}
	
	/**
	 * sets game turn to parameter
	 * @param turn
	 */
	public void setTurn( int turn )
	{
		this.turn = turn;
	}

	@Override
	public String toString()
	{
		System.out.println( "Your BattleBoard:" );
		board1.printBattleBoard();
		System.out.println( "Your HitBoard:" );
		board2.printHitBoard();
		System.out.println( "Opponent's HitBoard:" );
		board1.printHitBoard();
		return "Turn: " + turn + "\n";
	}
}
