package Battleshipgame;

/**
 * @author nikol
 *
 */
public class InvalidException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public InvalidException () {
		super("InvalidException");
	}
	
	public InvalidException(String message) {
		super(message);
	}
}