package the_lucky_gauntlet.Exceptions;

public class InaccessibleRoomException extends Exception{
	public InaccessibleRoomException() {
	}
	public InaccessibleRoomException(String msg) {
		super(msg);
	}
}