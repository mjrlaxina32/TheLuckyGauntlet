package the_lucky_gauntlet.Exceptions;

public class InvalidInputException extends Exception{
	public InvalidInputException() {
	}
	public InvalidInputException(String msg) {
		super(msg);
	}
}