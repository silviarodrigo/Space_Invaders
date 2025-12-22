package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class Bomb extends EnemyWeapon {
	private static final int DAMAGE = 1;
	private DestroyerAlien destroyerAlien;

	public Bomb(GameWorld game, Position pos, DestroyerAlien destroyerAlien) {
		super(game, pos, 1, Move.DOWN);
		this.destroyerAlien = destroyerAlien;
	}

//METODOS GETTER
	protected String getSymbol() {
		return Messages.BOMB_SYMBOL;
	}

	public int getDamage() {
		return DAMAGE;
	}

	public int getArmour() {
		return 1;
	}

//METODOS SOBRE SU VIDA
	// Mata a la bomba
	public void die() {
		onDelete();
		this.life = 0;// le quita la vida para que en removeDead la eliminen
	}

	// Avisa al alien de que puede lanzar otra bomba
	public void onDelete() {
		this.destroyerAlien.enableBomb();
	}

//METODOS DE MOVIMIENTO Y POSICION
	public void automaticMove() {
		if (!this.destroyerAlien.isAlive() && this.pos.equals(this.destroyerAlien.getPosition())) {
			// comprobamos que el laser no acaba de matar al alien q iba a lanzar la bomba
			die();
		} else {
			performMovement(dir);
			if (isOut())
				die();
			else
				game.performAttack(this);
		}

	}
	

//METODOS DE ATAQUE
	// El laser se mueve despues de la bomba entonces debe poder atacarla
	public boolean receiveAttack(UCMWeapon weapon) {
		receiveDamage(weapon.getDamage());
		return true;
	}
}
