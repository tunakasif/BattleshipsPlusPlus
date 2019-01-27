package game;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Extends Battle Class to have special cards in the game play
 * depends on the final version of the Battle Class so not ready yet
 * @author Omer Faruk Oflaz
 * @version 5 May 2018
 *
 */
public class SpecialBattle extends Battle
{
	// constants
	private static final int PLAYER_NUMBER = 2;

	// Enumerations
	public enum SpecialCard
	{
		DOUBLE_SHOT,
		REPAIR_RELOCATE,
		SHIELD,
		MINE_BALL;

		// properties
		private boolean active[];
		private boolean used[];

		// constructors
		private SpecialCard()
		{
			active = new boolean[PLAYER_NUMBER];
			used = new boolean[PLAYER_NUMBER];

			for ( boolean b : active ) 
				b = false;
			for ( boolean b : used ) 
				b = false;
		}

		// methods
		/**
		 * 
		 * @return used
		 */
		public boolean getUsed( int turn )
		{
			return used[turn - 1];
		}

		/**
		 * 
		 * @return active
		 */
		public boolean getActive( int turn )
		{
			return active[turn - 1];
		}

		/**
		 * sets active to condition
		 * @param condition
		 */
		public void setActive( int turn , boolean condition )
		{
			active[turn - 1] = condition;
		}

		/**
		 * sets used to condition
		 * @param condition
		 */
		public void setUsed( int turn , boolean condition )
		{
			used[turn - 1] = condition;
		}

		/**
		 * use a special card
		 * @param turn
		 */
		public void use( int turn )
		{
			this.setActive( turn , false );
			this.setUsed( turn , true );
		}
	}

	// inner classes
	public class Location
	{
		// properties
		int CoordinateX;
		int CoordinateY;

		// constructors
		public Location( int x , int y ) 
		{
			CoordinateX = x;
			CoordinateY = y;
		}

		// methods
		public int getX()
		{
			return CoordinateX;
		}

		public int getY()
		{
			return CoordinateY;
		}
	}

	// properties
	private ArrayList<SpecialCard> deck;
	private Location[] shieldLocation;

	// constructors
	public SpecialBattle( BattleBoard board1, BattleBoard board2 ) 
	{
		super( board1, board2 );
		deck = new ArrayList<SpecialCard>();
		shieldLocation = new Location[ PLAYER_NUMBER ]; 

		for ( SpecialCard card : SpecialCard.values() ) 
			deck.add( card );
	}

	// methods

	@Override
	public boolean play( int x , int y )
	{
		// variables
		int tempTurn;
		boolean playCondition;
		SpecialCard sc;

		// method code
		sc = getSpecial();

		// play with Double Shot
		if ( sc == SpecialCard.DOUBLE_SHOT )
		{
			sc.use( super.getTurn() );

			if ( shieldLocation[2 - super.getTurn()] == null )
			{
				tempTurn = super.getTurn();
				playCondition = super.play( x , y );
				super.setTurn( tempTurn );
				return playCondition;
			}

			else
			{
				if ( shieldLocation[2 - super.getTurn()].getX() == x
						&& shieldLocation[2 - super.getTurn()].getY() == y)
				{
					shieldLocation[2 - super.getTurn()] = null;
					super.setTurn( super.getTurn() );
					return false;
				}

				else
				{
					tempTurn = super.getTurn();
					playCondition = super.play( x , y );
					super.setTurn( tempTurn );
					return playCondition;	
				}
			}
		}

		// play with MineBall 
		else if ( sc == SpecialCard.MINE_BALL )
		{
			sc.use( super.getTurn() );

			if ( shieldLocation[2 - super.getTurn()] == null )
			{
				return super.play( x , y );
			}

			else
			{
				if ( shieldLocation[2 - super.getTurn()].getX() == x
						&& shieldLocation[2 - super.getTurn()].getY() == y)
				{
					shieldLocation[2 - super.getTurn()] = null;
					super.setTurn(3 - super.getTurn() );
					return false;
				}

				else
					return super.play( x , y );
			}
		}

		// play with Repair & Relocate
		else if ( sc == SpecialCard.REPAIR_RELOCATE )
		{
			sc.use( super.getTurn() );

			if ( shieldLocation[2 - super.getTurn()] == null )
			{
				return super.play( x , y );
			}

			else
			{
				if ( shieldLocation[2 - super.getTurn()].getX() == x
						&& shieldLocation[2 - super.getTurn()].getY() == y)
				{
					shieldLocation[2 - super.getTurn()] = null;
					super.setTurn(3 - super.getTurn() );
					return false;
				}

				else
					return super.play( x , y );
			}
		}

		// play with shield
		else if ( sc == SpecialCard.SHIELD )
		{
			sc.use( super.getTurn() );

			if ( shieldLocation[2 - super.getTurn()] == null )
			{
				return super.play( x , y );
			}

			else
			{
				if ( shieldLocation[2 - super.getTurn()].getX() == x
						&& shieldLocation[2 - super.getTurn()].getY() == y)
				{
					shieldLocation[2 - super.getTurn()] = null;
					super.setTurn(3 - super.getTurn() );
					return false;
				}

				else
					return super.play( x , y );
			}
		}

		// normal play
		else
		{
			if ( shieldLocation[2 - super.getTurn()] == null )
			{
				return super.play( x , y );
			}

			else
			{
				if ( shieldLocation[2 - super.getTurn()].getX() == x
						&& shieldLocation[2 - super.getTurn()].getY() == y)
				{
					shieldLocation[2 - super.getTurn()] = null;
					super.setTurn(3 - super.getTurn() );
					return false;
				}

				else
					return super.play( x , y );
			}
		}

	}

	/**
	 * 
	 * @return active special card, returns null if there is no active card
	 */
	private SpecialCard getSpecial()
	{
		// variables
		SpecialCard sc;

		sc = null;
		for ( SpecialCard card : deck )
		{
			if ( card.getActive( super.getTurn() ) )
				sc = card;
		}
		return sc;
	}

	/**
	 * 
	 * @return deck of special cards
	 */
	public ArrayList<SpecialCard> getDeck()
	{
		return this.deck;
	}
	
	/**
	 * sets the location of the player of turn
	 * @param turn
	 * @param x
	 * @param y
	 */
	public void setShield( int turn , int x , int y )
	{
		shieldLocation[turn - 1] = new Location( x , y );
	}
	
	/**
	 * 
	 * @return Location of the shield
	 */
	public Location[] getShield()
	{
		return shieldLocation;
	}
}


