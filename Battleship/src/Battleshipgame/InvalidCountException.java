package Battleshipgame;

/**
 * @author nikol
 *
 */
public class InvalidCountException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public InvalidCountException () {
		super("InvalidCountException");
	}
	
	public InvalidCountException(String message) {
		super(message);
	}
}
