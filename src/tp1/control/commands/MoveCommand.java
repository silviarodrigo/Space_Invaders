package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.NotAllowedMoveException;
import tp1.exceptions.OffWorldException;
import tp1.logic.*;
import tp1.view.Messages;

public class MoveCommand extends Command {
	private Move move;

	public MoveCommand() {
	}

	protected MoveCommand(Move move) {
		this.move = move;
	}

	protected String getName() {
		return Messages.COMMAND_MOVE_NAME;
	}

	protected String getShortcut() {
		return Messages.COMMAND_MOVE_SHORTCUT;
	}

	protected String getDetails() {
		return Messages.COMMAND_MOVE_DETAILS;
	}

	protected String getHelp() {
		return Messages.COMMAND_MOVE_HELP;
	}

	public Command parse(String[] commandWords) throws CommandParseException {
		Command c = null;
		try {
			if (matchCommandName(commandWords[0])) {
				if (commandWords.length != 2) {// si hay mas/menos de dos parametros ya hay un numero incorrecto
					throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
				}
				c = new MoveCommand(Move.parse(commandWords[1]));
			}

		} catch (NotAllowedMoveException e) {
			throw new CommandParseException(Messages.DIRECTION_ERROR + commandWords[1].toUpperCase(), e);
		} catch (IllegalArgumentException i) {
			throw new CommandParseException(Messages.DIRECTION_ERROR + commandWords[1], i);
		}
		return c;
	}

	public boolean execute(GameModel game) throws CommandExecuteException {
		try {
			game.move(move);
			game.update();// solo si el movimiento es correcto y se puede ejecutar actualizamos el juego
			return true;// hay que repintar
		} catch (OffWorldException e) {
			throw new CommandExecuteException(Messages.MOVEMENT_ERROR, e);
		}
	}

}
