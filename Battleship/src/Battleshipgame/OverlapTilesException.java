package Battleshipgame;

/**
 * @author nikol
 *
 */
public class OverlapTilesException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public OverlapTilesException () {
		super("OverlapTilesException");
	}
	
	public OverlapTilesException(String message) {
		super(message);
	}
}
