package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class SuperLaser extends UCMLaser {
	private static final int DAMAGE = 2;

	public SuperLaser(GameWorld game, Position pos) {
		super(game, pos);
	}

// METODOS GETTER
	public int getDamage() {
		return DAMAGE;
	}

// METODOS PARA IMPRIMIR TABLERO E INFORMACIÓN
	protected String getSymbol() {
		return Messages.SUPERLASER_SYMBOL;
	}

}
