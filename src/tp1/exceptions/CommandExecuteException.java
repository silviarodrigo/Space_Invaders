package tp1.exceptions;

public class CommandExecuteException extends Exception{

	private static final long serialVersionUID = -6021285827608218348L;
	
	public CommandExecuteException(String error) {
		super(error);
	}

	public CommandExecuteException(String error, Throwable cause) {
		super(error, cause);
	}

}
