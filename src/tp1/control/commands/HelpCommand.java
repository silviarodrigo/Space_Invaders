package tp1.control.commands;

import tp1.view.*;
import tp1.logic.*;

public class HelpCommand extends NoParamsCommand {

	protected String getName() {
		return Messages.COMMAND_HELP_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_HELP_SHORTCUT;
	}

	protected String getDetails() {
		return Messages.COMMAND_HELP_DETAILS;
	}

	protected String getHelp() {
		return Messages.COMMAND_HELP_HELP;
	}

	public boolean execute(GameModel game) {
		System.out.println(Messages.HELP_AVAILABLE_COMMANDS);
		System.out.println(CommandGenerator.commandHelp());
		return false; // no hay que repintar
	}
}