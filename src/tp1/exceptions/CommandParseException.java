package tp1.exceptions;

public class CommandParseException extends Exception {

	private static final long serialVersionUID = -6021285827608218348L;

	public CommandParseException(String unknownCommand) {
		super(unknownCommand);
	}

	public CommandParseException(String unknownCommand, Throwable cause) {
		super(unknownCommand, cause);
	}

}
