package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.*;
import tp1.logic.Position;
import tp1.view.Messages;

public class RegularAlien extends AlienShip {
	private static final int ARMOUR = 2;
	private static final int DAMAGE = 0;
	private static final int POINTS = 5;

	public RegularAlien(GameWorld game, Position pos, AlienManager alienManager) {
		super(game, pos, ARMOUR, alienManager);
	}

	public RegularAlien() {
		;
	}

//METODOS GETTER
	public int getPoints() {
		return POINTS;
	}

	public int getDamage() {
		return DAMAGE;
	}

	public int getArmour() {
		return ARMOUR;
	}

// METODOS PARA IMPRIMIR TABLERO E INFORMACIÓN
	protected String getSymbol() {
		return Messages.REGULAR_ALIEN_SYMBOL;
	}

	public String getDescription() {
		return Messages.REGULAR_ALIEN_DESCRIPTION;
	}

//METODOS DE CREACION
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new RegularAlien(game, pos, am);
	}
}