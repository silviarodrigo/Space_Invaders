package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class UCMLaser extends UCMWeapon {
	private static final int DAMAGE = 1;
	private static final int ARMOUR = 1;

	public UCMLaser(GameWorld game, Position pos) {
		super(game, pos, ARMOUR, Move.UP);
	}

//METODOS GETTER
	public int getDamage() {
		return DAMAGE;
	}

	public int getArmour() {
		return ARMOUR;
	}

// METODOS PARA IMPRIMIR TABLERO E INFORMACIÓN	
	protected String getSymbol() {
		return Messages.LASER_SYMBOL;
	}

//METODOS SOBRE SU VIDA
	// Dice al player que puede lanzar otro laser
	public void onDelete() {
		this.life = 0;
		game.enableLaser();
	}

//METODOS DE ATAQUE
	// El laser solo es atacado por las EnemyWeapon
	public boolean receiveAttack(EnemyWeapon weapon) {
		receiveDamage(weapon.getDamage());
		return true;
	}

	// METODOS DE MOVIMIENTO Y POSICION
	public void automaticMove() {
		performMovement(dir);
		if (isOut())
			die();
		else
			game.performAttack(this);
	}

}
