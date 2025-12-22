package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.view.Messages;

public class DestroyerAlien extends AlienShip {
	private static final int ARMOUR = 1;
	private static final int DAMAGE = 1;
	private static final int POINTS = 10;
	private boolean canShoot;
	private boolean bombReadyToFire;

	public DestroyerAlien(GameWorld game, Position pos, AlienManager alienManager) {
		super(game, pos, ARMOUR, alienManager);
		this.canShoot = true;
		this.bombReadyToFire = false;

	}

	public DestroyerAlien() {
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
		return Messages.DESTROYER_ALIEN_SYMBOL;
	}

	public String getDescription() {
		return Messages.DESTROYER_ALIEN_DESCRIPTION;
	}

//METODOS DE MOVIMIENTO Y POSICION
	public void automaticMove() {
		super.automaticMove();// llamamos al automaticMove() del alienShip
		if (this.bombReadyToFire) {
			game.addObject(new Bomb(this.game, this.pos, this));
			this.canShoot = false;
		}
	}

//METODOS SOBRE LAS BOMBAS
	// Devuelve si podría lanzar una bomba
	public void computerAction() {
		if (this.cyclesToMove != 0 && this.canShoot) {
			this.bombReadyToFire = canGenerateRandomBomb();
		} else
			this.bombReadyToFire = false;
	}

	public void enableBomb() {
		this.canShoot = true;
	}

	private boolean canGenerateRandomBomb() {
		return game.getRandom().nextDouble() < game.getLevel().getShootFrecuency();
	}

//METODOS DE CREACION
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new DestroyerAlien(game, pos, am);
	}
}
