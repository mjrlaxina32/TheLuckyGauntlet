package the_lucky_gauntlet.Exceptions;

public class BeyondRangeException extends Exception{
	public BeyondRangeException() {
	}
	public BeyondRangeException(String msg) {
		super(msg);
	}
}