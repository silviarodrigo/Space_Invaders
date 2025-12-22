package tp1.logic.gameobjects;

import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.*;

public abstract class EnemyShip extends Ship {

	protected Move dir;

	public EnemyShip(GameWorld game, Position pos, int armour, Move dir) {
		super(game, pos, armour);
		this.dir = dir;
	}

	public EnemyShip() {
		;
	}

	public abstract int getPoints();

	public abstract int getDamage();

	public abstract int getArmour();

	public abstract String getDescription();

	// Imprime la información de la nave para la lista
	protected String getInfo() {
		return Messages.alienDescription(getDescription(), getPoints(), getDamage(), getArmour());
	}

	// Imprime el simbolo de la nave con su vida
	public String toString() {
		return Messages.status(getSymbol(), getLife());
	}

	// Mata al alien y otorga los puntos al jugador
	public void onDelete() {
		this.game.receivePoints(getPoints());
	}

	// lo unico que ataca a los EnemyShip son las UCMWeapon
	public boolean receiveAttack(UCMWeapon weapon) {
		receiveDamage(weapon.getDamage());
		return true;
	}

}
