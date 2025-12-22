package tp1.exceptions;

public class NotEnoughPointsException extends GameModelException {

	private static final long serialVersionUID = -6021285827608218348L;

	public NotEnoughPointsException(String message) {
		super(message);
	}
}
