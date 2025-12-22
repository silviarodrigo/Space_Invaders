package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Weapon extends GameObject {

	protected Move dir;

	public Weapon(GameWorld game, Position pos, int life, Move dir) {
		super(game, pos, life);
		this.dir = dir;
	}

	public Weapon() {
		;
	}

	public void computerAction() {
		;
	}

	public String toString() {
		return getSymbol();
	}

//METODOS DE ATAQUE
	protected abstract boolean weaponAttack(GameItem other);

	public boolean performAttack(GameItem other) {
		if (this.isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			if (weaponAttack(other)) {
				die();
				return true;
			}
		}
		return false;
	}

}
