package the_lucky_gauntlet.Exceptions;
/**
 * This exception runs when a room is currently inaccessible.
 * @author Athena Kimwell
 */
public class InaccessibleRoomException extends Exception{
	public InaccessibleRoomException() {
	}
	public InaccessibleRoomException(String msg) {
		super(msg);
	}
}