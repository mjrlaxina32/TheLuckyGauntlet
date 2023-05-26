package the_lucky_gauntlet.Exceptions;
/**
 * This exception runs when an invalid value was inputted into the program.
 * @author Athena Kimwell
 */
public class InvalidInputException extends Exception{
	public InvalidInputException() {
	}
	public InvalidInputException(String msg) {
		super(msg);
	}
}