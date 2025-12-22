package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;
import tp1.logic.*;
import tp1.logic.Position;

public abstract class Ship extends GameObject {
	private static final List<Ship> availableShips = Arrays.asList(new RegularAlien(), new DestroyerAlien(),
			new ExplosiveAlien(), new UCMShip(), new Ufo());

	public Ship(GameWorld game, Position pos, int armour) {
		super(game, pos, armour);

	}

	public Ship() {
		;
	}

	public static String listOfShips() {
		StringBuilder str = new StringBuilder();// construimos una cadena con toda la informacion de las naves
		for (Ship o : availableShips) {
			str.append(o.getInfo() + "\n");
		}
		return str.toString();
	}

	protected abstract String getInfo();

}
