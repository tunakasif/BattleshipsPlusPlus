package game;

import java.util.Scanner;

/**
 * Test battleship game as console application 
 * @author Tuna Alikasifoglu 
 * @version 5 May 2018
 *
 */
public class TestBattleGame 
{
	// global constants
	private static final int DIMENSION = 5;

	// global variables
	private static BattleBoard board;
	private static Battle game;
	private static AI computer;

	// methods
	public static void main( String[] args ) 
	{
		// program code
		setBattleBoard();
		startGame();
	}

	/**
	 * starts the battleship game and
	 * continues while taking input from user
	 * for coordinates 
	 */
	private static void startGame() 
	{
		Scanner scan = new Scanner( System.in );
		// variables
		int x;
		int y;
		int target[];

		// method code
		
		target = new int[2];
		computer = new AI( board );
		game = new Battle( board , computer.getBattleBoard() );
		System.out.println( "Let's Battle! \n\n" );
		System.out.println( game.toString() );

		do
		{
			if ( game.getTurn() == 1 )
			{
				System.out.println( "Choose Your Target" );
				System.out.print( "Enter X-Coordinate: " );
				x = scan.nextInt();
				scan.nextLine();
				System.out.print( "Enter Y-Coordinate: " );
				y = scan.nextInt();
				scan.nextLine();

				game.play(x, y);
			}
			
			else
			{
				target  = computer.getTarget();
				game.play(target[0], target[1]);
			}
			System.out.println( game.toString() );


		} while ( game.doesGameContinue() );

		System.out.println( game.getGameCondition() );
	}

	/**
	 * sets the battle board by taking input 
	 * from the user
	 */
	private static void setBattleBoard()
	{
		Scanner scan = new Scanner( System.in );

		// variables
		boolean orientation;
		int index;
		int x;
		int y;

		// method code
		board = new BattleBoard( DIMENSION );
		board.printBattleBoard();
		do
		{
			System.out.println( "Ship Deployment" );
			for ( int i = 0; i < board.getCrew().size(); i++ ) 
			{
				if ( !board.getCrew().get(i).getLocated() )
					System.out.println( i + "-) " + board.getCrew().get(i) );
			}
			System.out.println();
			
			System.out.print( "Select ship: " );
			index = scan.nextInt();
			scan.nextLine();

			System.out.println( board.getCrew().get(index) + " is selected." );

			System.out.print( "Is Horizontal? " );
			orientation = scan.nextBoolean();
			scan.nextLine();

			if ( orientation )
				board.getCrew().get(index).setOrientation(Ship.Orientation.HORIZONTAL);
			else
				board.getCrew().get(index).setOrientation(Ship.Orientation.VERTICAL);

			System.out.print( "Enter X-Coordinate: " );
			x = scan.nextInt();
			scan.nextLine();
			System.out.print( "Enter Y-Coordinate: " );
			y = scan.nextInt();
			scan.nextLine();

			if ( board.addShip(index, x, y) )
				System.out.println( board.getCrew().get(index) + " is added." );
			else
				System.out.println( "Location is not avaliable." );
			board.printBattleBoard();
		} while ( !board.isBoardReady() );
	}

}
