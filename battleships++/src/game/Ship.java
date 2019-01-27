package game;

/**
 * Holds the properties of name, length, orientation 
 * of the Battleships
 * @author Tuna Alikasifoglu
 * @version 5 May 2018
 *
 */
public class Ship 
{
	// enumerations
	/**
	 * A ship can be horizontal or vertical
	 *
	 */
	public enum Orientation
	{
		HORIZONTAL,
		VERTICAL
	}
	
	/**
	 * Ships can have various lengths and various functions
	 * these types can be specified as enumerations
	 *
	 */
	public enum Type
	{
		AIRCRAFT_CARRIER( 5 ), 
		BATTLESHIP( 4 ), 
		SUBMARINE( 3 ),
		CRUISER( 3 ), 
		DESTROYER( 2 );
		
		// properties
		private final int length;
		
		// constructor
		private Type( int length )
		{
			this.length = length;
		}
		
		/**
		 * 
		 * @return length of the ship
		 */
		public int getLength()
		{
			return length;
		}
	}
	
	// properties
	private Orientation orientation;
	private Type type;
	private boolean located;
	private int initialX;
	private int initialY;
	
	// constructors
	public Ship( Type type )
	{
		this.type = type;
		orientation = Orientation.HORIZONTAL; 
		located = false;
	}
	
	public Ship( Orientation orientation , Type type )
	{
		this.orientation = orientation; 
		this.type = type;
		located = false;
	}
	
	// methods 
	/**
	 * sets the orientation of the ship
	 * @param orientation
	 */
	public void setOrientation( Orientation orientation )
	{
		this.orientation = orientation;
	}
	
	/**
	 * sets located to condition
	 * @param condition
	 */
	public void setLocated( boolean condition )
	{
		located = condition;
	}
	
	/**
	 * set initialX to x
	 * @param x
	 */
	public void setX( int x )
	{
		initialX = x;
	}
	
	/**
	 * set initialX to x
	 * @param x
	 */
	public void setY( int y )
	{
		initialY = y;
	}
	
	/**
	 * 
	 * @return initialY
	 */
	public int getX()
	{
		return initialX;
	}
	
	/**
	 * 
	 * @return initialY
	 */
	public int getY()
	{
		return initialY;
	}
	
	/**
	 * @return orientation
	 */
	public Orientation getOrientation()
	{
		return orientation;
	}
	
	/**
	 * 
	 * @return type of the ship
	 */
	public Type getType()
	{
		return type;
	}
	
	/**
	 * 
	 * @return located
	 */
	public boolean getLocated()
	{
		return located;
	}
	
	@Override
	public String toString()
	{
		return String.format( "%s, %d" , this.type , this.type.length );
	}
}
