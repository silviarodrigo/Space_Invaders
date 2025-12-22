package tp1.logic;

import tp1.exceptions.NotAllowedMoveException;
import tp1.view.Messages;

/**
 * Represents the allowed movements in the game
 *
 */
public enum Move {
	LEFT(-1, 0), LLEFT(-2, 0), RIGHT(1, 0), RRIGHT(2, 0), DOWN(0, 1), UP(0, -1), NONE(0, 0);

	private final int x;
	private final int y;

	private Move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// Dada la dirección que quiere el jugador devuelve el Move correspondiente
	public static Move parse(String param) throws NotAllowedMoveException, IllegalArgumentException {
		if (param.equalsIgnoreCase("left")) {
			return LEFT;
		} else if (param.equalsIgnoreCase("lleft")) {
			return LLEFT;
		} else if (param.equalsIgnoreCase("right")) {
			return RIGHT;
		} else if (param.equalsIgnoreCase("rright")) {
			return RRIGHT;
		} else if (param.equalsIgnoreCase("none")) {
			return NONE;
		} else if (param.equalsIgnoreCase("up") || param.equalsIgnoreCase("down")) {
			// si introducimos un Move real pero que la nave no puede realizar
			throw new NotAllowedMoveException(Messages.ALLOWED_MOVES_MESSAGE);
		} else {
			// el parametro introducido no corresponde con un movimiento valido
			throw new IllegalArgumentException(Messages.ALLOWED_MOVES_MESSAGE);
		}

	}

	// Cambia la dirección de los aliens
	public Move flip() {
		if (this.equals(RIGHT)) {
			return LEFT;
		} else
			return RIGHT;
	}

}
