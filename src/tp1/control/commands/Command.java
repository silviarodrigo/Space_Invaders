package tp1.control.commands;

import tp1.view.*;
import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.logic.*;

public abstract class Command {
	protected abstract String getName();

	protected abstract String getShortcut();

	protected abstract String getDetails();

	protected abstract String getHelp();

	public abstract boolean execute(GameModel game) throws CommandExecuteException;

	public abstract Command parse(String[] parameter) throws CommandParseException;

	protected boolean matchCommandName(String name) {
		return getShortcut().equalsIgnoreCase(name) || getName().equalsIgnoreCase(name);
	}

	public String helpText() {
		return getDetails() + Messages.HELP_DETAILS_COMMAND_HELP_SEPARATOR + getHelp() + "\n";
	}
}