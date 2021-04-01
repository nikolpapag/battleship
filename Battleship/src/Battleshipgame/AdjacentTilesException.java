/**
 * 
 */
package Battleshipgame;

/**
 * @author nikol
 *
 */
public class AdjacentTilesException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public AdjacentTilesException () {
		super("AdjacentTilesException");
	}
	
	public AdjacentTilesException(String message) {
		super(message);
	}
}
