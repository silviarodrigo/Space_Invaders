package tp1.control.commands;

import tp1.view.*;
import tp1.exceptions.CommandExecuteException;
import tp1.logic.*;

public class ExitCommand extends NoParamsCommand {
	protected String getName() {
		return Messages.COMMAND_EXIT_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_EXIT_SHORTCUT;
	}

	protected String getDetails() {
		return Messages.COMMAND_EXIT_DETAILS;
	}

	protected String getHelp() {
		return Messages.COMMAND_EXIT_HELP;
	}

	public boolean execute(GameModel game) throws CommandExecuteException {
		game.exit();
		return false; // no hay que repintar
	}

}