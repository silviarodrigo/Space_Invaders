package tp1.control.commands;

import tp1.view.*;
import tp1.logic.*;

public class ListCommand extends NoParamsCommand {

	protected String getName() {
		return Messages.COMMAND_LIST_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_LIST_SHORTCUT;
	}

	protected String getDetails() {
		return Messages.COMMAND_LIST_DETAILS;
	}

	protected String getHelp() {
		return Messages.COMMAND_LIST_HELP;
	}

	public boolean execute(GameModel game) {
		System.out.println(game.listofShips());
		return false; // no hay que repintar
	}

}
