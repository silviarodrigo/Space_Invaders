package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExplosiveAlien extends RegularAlien {
	private static final int ARMOUR = 2;
	private static final int DAMAGE = 1;
	private static final int POINTS = 12;

	public ExplosiveAlien(GameWorld game, Position pos, AlienManager alienManager) {
		super(game, pos, alienManager);
	}

	public ExplosiveAlien() {
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
		return Messages.EXPLOSIVE_ALIEN_SYMBOL;
	}

	public String getDescription() {
		return Messages.EXPLOSIVE_ALIEN_DESCRIPTION;
	}

//METODOS SOBRE SU VIDA
	public void receiveDamage(int damage) {
		super.receiveDamage(damage);
		if (!isAlive()) {// si muere atacamos a todos los AlienShip que nos rodean
			performAttack();
		}

	}

//METODOS DE ATAQUE
	private void performAttack() {
		game.performExplosiveAlien(this);
	}

//METODOS DE CREACION
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new ExplosiveAlien(game, pos, am);
	}

}
