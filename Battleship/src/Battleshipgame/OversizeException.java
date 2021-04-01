/**
 * 
 */
package Battleshipgame;

/**
 * @author nikol
 *
 */
public class OversizeException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public OversizeException () {
		super("OversizeException");
	}
	
	public OversizeException(String message) {
		super(message);
	}
}
