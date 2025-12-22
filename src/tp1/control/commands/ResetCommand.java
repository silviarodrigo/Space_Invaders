package tp1.control.commands;

import tp1.view.*;
import tp1.logic.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import tp1.control.*;
import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.InitializationException;

public class ResetCommand extends Command {
	private InitialConfiguration conf;

	public ResetCommand() {
	}

	protected ResetCommand(InitialConfiguration conf) {
		this.conf = conf;
	}

	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	protected String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	protected String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	public boolean execute(GameModel game) throws CommandExecuteException {
		boolean repintar;
		try {
			if (this.conf != null) {
				game.reset(this.conf);
				repintar = true;// hay que repintar
			} else {
				repintar = false;
			}
			return repintar;
		} catch (InitializationException e) {
			throw new CommandExecuteException(Messages.INITIAL_CONFIGURATION_ERROR, e);
		}
	}

	public Command parse(String[] conf) throws CommandParseException {
		Command c = null;
		if (matchCommandName(conf[0])) {
			if (conf.length == 1) {
				// Si ponen solo "reset"
				c = new ResetCommand(InitialConfiguration.NONE);
			} else if (conf.length == 2) {
				try {
					// Si la configuración no existe, devuelve null.
					c = new ResetCommand(InitialConfiguration.readFromFile(conf[1]));
				} catch (FileNotFoundException e) {
					throw new CommandParseException(Messages.FILE_NOT_FOUND.formatted(conf[1]));
				} catch (IOException e) {
					throw new CommandParseException(Messages.READ_ERROR.formatted(conf[1], e));
				}
			} else {
				// si aparte del reset y la conf hay otro parametro ya hay un numero incorrecto
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			}
		}
		return c;
	}
}
