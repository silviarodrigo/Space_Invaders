package tp1.logic.gameobjects;

import tp1.exceptions.LaserInFlightException;
import tp1.exceptions.NoShockWaveException;
import tp1.exceptions.NotEnoughPointsException;
import tp1.exceptions.OffWorldException;
import tp1.logic.*;
import tp1.view.*;

public class UCMShip extends Ship {
	private static final int ARMOUR = 3;
	private static final int DAMAGE = 1;
	private int points = 0;
	private boolean canShoot;
	private boolean hasShockWave;
	private ShockWave shockWave;

	public UCMShip(GameWorld game, Position pos) {
		super(game, pos, ARMOUR);
		this.canShoot = true;
		this.hasShockWave = false;
	}

	public UCMShip() {
		;
	}

//METODOS GETTER
	public int getDamage() {
		return DAMAGE;
	}

	public int getArmour() {
		return ARMOUR;
	}

	public String getInfo() {
		return Messages.ucmShipDescription(Messages.UCMSHIP_DESCRIPTION, DAMAGE, ARMOUR);
	}

// METODOS PARA IMPRIMIR TABLERO E INFORMACIÓN
	protected String getSymbol() {
		if (isAlive()) {
			return Messages.UCMSHIP_SYMBOL;
		} else {
			return Messages.UCMSHIP_DEAD_SYMBOL;

		}
	}

	public String toString() {
		return getSymbol();
	}

	public String toString(Position pos) {
		if (this.pos.equals(pos)) {
			return getSymbol();
		} else
			return "";

	}

	public String stateToString() {
		if (this.hasShockWave) {
			return "Life: " + life + "\nPoints: " + points + "\nShockWave:" + " ON\n";
		} else {
			return "Life: " + life + "\nPoints: " + points + "\nShockWave:" + " OFF\n";
		}

	}

//METODOS SOBRE SU VIDA
	public void onDelete() {
		;
	}

//METODOS DE MOVIMIENTO Y POSICION
	public void move(Move move) throws OffWorldException {
		Position pos1 = pos.move(move);
		// Si la pos nueva está en el tablero movemos y decimos que vuelvas a pintar
		if (pos1.isOnBoard()) {
			this.pos = pos1;
		} else {// si la posicion se saldría del tablero lanzamos la excepcion correspondiente
			throw new OffWorldException(Messages.OFF_WORLD_MESSAGE.formatted(move, this.pos.printPosition()));
		}
	}

	public void automaticMove() {
		;
	}

//METODOS SOBRE EL LASER
	// LASER NORMAL
	// Si quiere y puede disparar creamos un nuevo laser
	public void shootLaser() throws LaserInFlightException {
		if (this.canShoot) {
			game.addObject(new UCMLaser(this.game, this.pos));
			this.canShoot = false; // acutalizamos que ya no pueda disparar
		} else {// si el laser no puede disparar es porque ya hay otro en el tablero
			throw new LaserInFlightException(Messages.LASER_ALREADY_SHOT);
		}
	}

	// Permitimos que pueda volver a disparar
	public void enableLaser() {
		this.canShoot = true;
	}

	// SUPERLASER
	public void shootSuperLaser() throws LaserInFlightException, NotEnoughPointsException {
		if (this.points < 5) {// no tiene suficientes puntos para lanzar un super laser
			throw new NotEnoughPointsException(Messages.NOT_ENOUGH_POINTS_ERROR.formatted(this.points, 5));
		} else if (!this.canShoot) {// ya hay un laser en el tablero
			throw new LaserInFlightException(Messages.LASER_ALREADY_SHOT);
		} else {
			game.addObject(new SuperLaser(this.game, this.pos));
			this.canShoot = false; // acutalizamos que ya no pueda disparar
			this.points -= 5;// le quitamos 5 puntos por lanzar
		}
	}

//METODOS SOBRE EL SHOCKWAVE
	// Crea un nuevo ShockWave
	public void shockWave(boolean bool) {
		if (!bool && !this.hasShockWave) {
			this.hasShockWave = true;
		}
	}

	// Lanza el shockWave
	public void executeShockWave() throws NoShockWaveException {
		if (this.hasShockWave) {
			this.shockWave=new ShockWave(this.game);
			game.addObject(this.shockWave);
			game.performAttack(shockWave); // llamamos a todos los ataques del shockWave
			this.hasShockWave = false;
			this.shockWave.onDelete();// ponemos la vida a 0 para poder eliminarla del container
		} else
			throw new NoShockWaveException();
	}

//METODOS DE ATAQUE Y PUNTOS
	// El player solo es atacado por las EnemyWeapon
	public boolean receiveAttack(EnemyWeapon weapon) {
		receiveDamage(weapon.getDamage());
		return true;
	}

	// Recibe los puntos conseguidos por matar a un alien/ufo y los suma
	public void receivePoints(int points) {
		this.points += points;
	}

//OTROS METODOS
	public void computerAction() {
		;
	}

}
