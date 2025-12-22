package tp1.exceptions;

public class NotAllowedMoveException extends GameModelException {

	private static final long serialVersionUID = -6021285827608218348L;

	public NotAllowedMoveException(String message) {
		super(message);
	}
}
