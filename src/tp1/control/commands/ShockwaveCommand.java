package tp1.control.commands;

import tp1.view.*;
import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.NoShockWaveException;
import tp1.logic.*;

public class ShockwaveCommand extends NoParamsCommand {

	protected String getName() {
		return Messages.COMMAND_SHOCKWAVE_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_SHOCKWAVE_SHORTCUT;
	}

	protected String getDetails() {
		return Messages.COMMAND_SHOCKWAVE_DETAILS;
	}

	protected String getHelp() {
		return Messages.COMMAND_SHOCKWAVE_HELP;
	}

	public boolean execute(GameModel game) throws CommandExecuteException {
		try {
			game.shockWave(); // solo si tiene un shockwave y lo ejecuta actualizamos el juego
			game.update();
			return true; // hay que repintar
		} catch (NoShockWaveException e) {
			throw new CommandExecuteException(Messages.SHOCKWAVE_ERROR);
		}

	}
}
