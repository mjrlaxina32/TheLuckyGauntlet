package the_lucky_gauntlet.Exceptions;
/**
 * This exception runs when an order that does not exist is being accessed.
 * @author Athena Kimwell
 */
public class InvalidOrderException extends Exception{
	public InvalidOrderException() {
	}
	public InvalidOrderException(String msg) {
		super(msg);
	}
}