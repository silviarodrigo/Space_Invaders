package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.*;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class AlienShip extends EnemyShip {
	protected int cyclesToMove;
	protected int speed;
	protected AlienManager alienManager;

	public AlienShip(GameWorld game, Position pos, int armour, AlienManager alienManager) {
		super(game, pos, armour, Move.LEFT);
		this.alienManager = alienManager;
		this.speed = this.alienManager.getSpeed();
		this.cyclesToMove = this.speed;

	}

	public AlienShip() {
		;
	}

//METODOS RELACIONADOS CON EL TABLERO
	public boolean isInFinalRow() {
		return this.pos.isInFinalRow();
	}

	protected boolean isInBorder() {
		return this.pos.isInBorder();
	}

//METODOS SOBRE SU VIDA
	// Mata al alien y otorga los puntos al jugador
	public void onDelete() {
		super.onDelete();
		this.alienManager.alienDead();// avisa al alienmanager de que quite uno a los aliens restantes
	}

//METODOS RELACIONADOS CON EL MOVIMIENTO
	public void automaticMove() {
		if (this.cyclesToMove == 0) {
			performMovement(this.dir);
			this.cyclesToMove = this.speed;
			if (isInBorder()) {
				this.alienManager.shipOnBorder();
			}
		} else if (alienManager.onBorder()) {// solo puede bajar si no es su turno de moverse
			descend();
			if (isInFinalRow()) {
				this.alienManager.finalRowReached();
			}
		} else {
			this.cyclesToMove--;
		}
	}

	protected void descent() {
		this.pos = pos.move(Move.DOWN);

	}

	public void descend() {
		descent();
		alienManager.alienDescended();
		if (!isInFinalRow()) {
			this.dir = this.dir.flip();// cambiamos la dirección de movimiento
		}
		this.cyclesToMove = this.speed;// reinicia el contador de ciclos para moverse
		game.checkAttacksTo(this);
	}

//METODOS DE ATAQUE
	public boolean receiveAttack(ShockWave wave) {
		receiveDamage(wave.getDamage());
		return true;
	}

	// Método que resta una vida a todos los AlienShip que rodean al ExplosiveAlien
	public void receiveAttack(ExplosiveAlien alien) {
		// Lo recorremos de la misma forma que haríamos con una matriz
		int incF[] = { 1, 1, 0, -1, -1, -1, 0, 1 };
		int incC[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
		Position alienPos = alien.getPosition();
		for (int i = 0; i < 8; i++) {
			Position pos = alienPos.sumPosition(incF[i], incC[i]);
			if (this.isOnPosition(pos)) {
				receiveDamage(alien.getDamage());
			}
		}
	}

//OTROS METODOS
	protected abstract AlienShip copy(GameWorld game, Position pos, AlienManager am);

}
