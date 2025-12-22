package tp1.control.commands;

import tp1.view.*;
import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.LaserInFlightException;
import tp1.logic.*;

public class ShootCommand extends NoParamsCommand {

	protected String getName() {
		return Messages.COMMAND_SHOOT_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_SHOOT_SHORTCUT;
	}

	protected String getDetails() {
		return Messages.COMMAND_SHOOT_DETAILS;
	}

	protected String getHelp() {
		return Messages.COMMAND_SHOOT_HELP;
	}

	public boolean execute(GameModel game) throws CommandExecuteException {
		try {
			game.shootLaser();
			game.update();// solo si se puede lanzar un laser y lo lanzamos actualizamos el tablero
			return true;// hay que repintar
		} catch (LaserInFlightException e) {
			throw new CommandExecuteException(Messages.LASER_ERROR, e);
		}
	}
}
