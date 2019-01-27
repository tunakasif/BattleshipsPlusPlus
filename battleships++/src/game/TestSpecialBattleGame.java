package game;

import java.util.Scanner;

import game.SpecialBattle.SpecialCard;

/**
 * Tests Special Game
 * @author Tuna Alikasifoglu
 * @version 5 May 2018
 *
 */
public class TestSpecialBattleGame
{
	// global constants
	private static final int DIMENSION = 8;

	// global variables
	private static BattleBoard board;
	private static SpecialBattle game;
	private static AI computer;

	public static void main( String[] args ) 
	{
		Scanner scan = new Scanner( System.in );
		// variables
		int index;
		SpecialCard sc;

		// method code

		setBattleBoard();
		startGame();
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
		int index;
		int[] target;
		boolean condition;

		// method code

		target = new int[2];
		computer = new AI( board );
		game = new SpecialBattle( board , computer.getBattleBoard() );
		System.out.println( "Let's Battle! \n\n" );
		System.out.println( game.toString() );

		game.setShield( game.getTurn() + 1, 0, 0);
		do
		{	
			if ( board.isBoardReady() )
			{
				if ( game.getTurn() == 1 )
				{
					System.out.print( "Do you want to use a Special Card? " );
					condition = scan.nextBoolean();

					if ( condition )
					{
						System.out.println( "Special Cards" );
						for ( int i = 0; i < game.getDeck().size(); i++ ) 
						{
							if ( !game.getDeck().get(i).getUsed( game.getTurn() ) )
								System.out.println( i + "-) " + game.getDeck().get(i) );
						}
						System.out.println();

						System.out.print( "\nSelect Special Card: " );
						index = scan.nextInt();
						scan.nextLine();

						if ( !game.getDeck().get( index ).getUsed( game.getTurn() ) )
						{
							game.getDeck().get( index ).setActive( game.getTurn() , true );
							System.out.println( game.getDeck().get( index ) + " is used." );
							if ( game.getDeck().get( index ).name() == SpecialCard.SHIELD.name() )
							{
								System.out.println( "Choose Your Shield" );
								System.out.print( "Enter X-Coordinate: " );
								x = scan.nextInt();
								scan.nextLine();
								System.out.print( "Enter Y-Coordinate: " );
								y = scan.nextInt();
								scan.nextLine();
								game.setShield( game.getTurn(), x, y);
								System.out.println( String.format( "Shield is added to (%d , %d)" , x , y ) );
							}
						}

						else
							System.out.println( game.getDeck().get( index ) + " is already used." );
					}

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
					target = computer.getTarget();
					game.play(target[0], target[1]);
				}
				System.out.println( game.toString() );
			}
			
			else
				setBattleBoard();

		} while ( game.doesGameContinue() );

		System.out.println( game.getGameCondition() );
	}
}

