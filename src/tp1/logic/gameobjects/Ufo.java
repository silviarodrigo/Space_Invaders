package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class Ufo extends EnemyShip {
	private static final int ARMOUR = 1;
	private static final int DAMAGE = 0;
	private static final int POINTS = 25;
	private boolean enabled;

	public Ufo(GameWorld game) {
		super(game, new Position(8, 0), ARMOUR, Move.LEFT);
		this.enabled = false;
	}

	public Ufo() {
		;
	}

//METODOS GETTER
	public int getDamage() {
		return DAMAGE;
	}

	public int getPoints() {
		return POINTS;
	}

	public int getArmour() {
		return ARMOUR;
	}

// METODOS PARA IMPRIMIR TABLERO E INFORMACIÓN
	protected String getSymbol() {
		return Messages.UFO_SYMBOL;
	}

	public String getDescription() {
		return Messages.UFO_DESCRIPTION;
	}

	public String toString() {
		if (this.enabled) {
			return super.toString();
		} else
			return "";
	}

//METODOS SOBRE SU VIDA	
	// Le revivimos la vida después de que lo mate el laser
	public void BringToLife() {
		this.life = 1;
	}

	// Le desactivamos
	public void onDelete() {
		super.onDelete();
		this.enabled = false;
	}

	// Lo activamos y reseteamos
	private void enable() {
		this.enabled = true;
		this.pos = new Position(9, 0);
		// Le revivimos (usamos la vida para ver si tenemos shockWave)
		BringToLife();
	}

//METODOS DE MOVIMIENTO Y POSICION
	public void automaticMove() {
		if (this.enabled) {
			performMovement(this.dir);
			if (isOut()) {
				this.enabled = false;
			}
		}
	}

//METODOS DE ATAQUE
	// Al ufo solo lo atacan las UCMWeapon
	public boolean receiveAttack(UCMWeapon laser) {
		receiveDamage(laser.getDamage());
		if (!isAlive()) {
			game.enableShockWave();
			onDelete();
			BringToLife();
		}
		return true;
	}

//METODOS DE APARICIÓN
	public void computerAction() {
		if (!enabled && canGenerateRandomUfo()) {
			enable();
		}
	}

	private boolean canGenerateRandomUfo() {
		return game.getRandom().nextDouble() < game.getLevel().getUfoFrequency();
	}

}
