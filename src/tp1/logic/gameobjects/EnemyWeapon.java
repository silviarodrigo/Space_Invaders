package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon {

	public EnemyWeapon(GameWorld game, Position pos, int life, Move dir) {
		super(game, pos, life, dir);
	}

	public EnemyWeapon() {
		;
	}

	protected boolean weaponAttack(GameItem object) {
		return object.receiveAttack(this);
	}

}
