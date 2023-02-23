package the_lucky_gauntlet.Exceptions;

public class InvalidOrderException extends Exception{
	public InvalidOrderException() {
	}
	public InvalidOrderException(String msg) {
		super(msg);
	}
}