package tp1.control.commands;

import tp1.view.*;
import tp1.exceptions.CommandExecuteException;
import tp1.logic.*;

public class NoneCommand extends NoParamsCommand {

	protected String getName() {
		return Messages.COMMAND_NONE_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_NONE_SHORTCUT;
	}

	protected String getDetails() {
		return Messages.COMMAND_NONE_DETAILS;
	}

	protected String getHelp() {
		return Messages.COMMAND_NONE_HELP;
	}

	protected boolean matchCommandName(String name) {
		if (name.equals("")) {// la entrada "" tambien deberia ejecutar none
			name = "n";
		}
		return super.matchCommandName(name);
	}

	public boolean execute(GameModel game) throws CommandExecuteException {
		game.update();
		return true; // hay que repintar
	}
}
