package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.exceptions.InitializationException;
import tp1.logic.*;
import tp1.view.Messages;

public class ShipFactory {
	private static final List<AlienShip> AVAILABLE_ALIEN_SHIPS = Arrays.asList(new RegularAlien(), new DestroyerAlien(),
			new ExplosiveAlien());

	private ShipFactory() {
		;
	}

	public static AlienShip spawnAlienShip(String input, GameWorld game, Position pos, AlienManager am)
			throws InitializationException {
		AlienShip alien = null;
		for (AlienShip c : AVAILABLE_ALIEN_SHIPS) {
			if (input.equalsIgnoreCase(c.getSymbol())) {
				if (pos.isOnBoard()) {
					alien = c.copy(game, pos, am);
				} else {// si la posicion no está en el tablero lanzamos la excepcion correspondiente
					throw new InitializationException(Messages.OFF_WORLD_POSITION.formatted(pos.printPosition()));
				}
			}
		}
		if (alien == null) {// si el alien es null es porque el simbolo no coincide con el de una navef
			throw new InitializationException(Messages.UNKNOWN_SHIP.formatted(input.toUpperCase()));
		}
		return alien;
	}

}
