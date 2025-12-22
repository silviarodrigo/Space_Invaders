package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.LaserInFlightException;
import tp1.exceptions.NotEnoughPointsException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class SuperLaserCommand extends NoParamsCommand {
	protected String getName() {
		return Messages.COMMAND_SUPERLASER_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_SUPERLASER_SHORTCUT;
	}

	protected String getDetails() {
		return Messages.COMMAND_SUPERLASER_DETAILS;
	}

	protected String getHelp() {
		return Messages.COMMAND_SUPERLASER_HELP;
	}

	public boolean execute(GameModel game) throws CommandExecuteException {
		try {
			game.shootSuperLaser();
			game.update();// solo si se puede lanzar un laser y lo lanzamos actualizamos el tablero
			return true;// hay que repintar
		} catch (NotEnoughPointsException | LaserInFlightException e) {
			throw new CommandExecuteException(Messages.SUPERLASER_ERROR, e);
		}
	}
}
