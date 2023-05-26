package the_lucky_gauntlet.Exceptions;
/**
 * This Exception runs when a value goes beyond its intended range.
 * @author Athena Kimwell
 */
public class BeyondRangeException extends Exception{
	public BeyondRangeException() {
	}
	public BeyondRangeException(String msg) {
		super(msg);
	}
}