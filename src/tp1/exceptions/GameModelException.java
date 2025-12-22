package tp1.exceptions;

public class GameModelException extends Exception {

	private static final long serialVersionUID = -6021285827608218348L;

	public GameModelException(String message) {
		super(message);
	}

	public GameModelException() {
		super();
	}
}
