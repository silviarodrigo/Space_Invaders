package tp1.logic.gameobjects;

import tp1.logic.*;

public abstract class GameObject implements GameItem {

	protected Position pos;
	protected int life;
	protected GameWorld game;

	public GameObject(GameWorld game, Position pos, int life) {
		this.pos = pos;
		this.game = game;
		this.life = life;
	}

	public GameObject() {
		;
	}
	
/*ESQUEMA HERENCIAS:
										GameObject
				  Ship										     	   Weapon
		UCMSHip			EnemyShip						    UCMWeapon           EnemyWeapon
					Ufo		     AlienShip		    	Laser      Shockwave        Bomb
						    RegAlien    DestryA       SuperLsr
						   Explosive
*/
//METODOS GETTERS
	protected abstract String getSymbol();

	protected abstract int getDamage();

	protected abstract int getArmour();

	protected int getLife() {
		return this.life;
	}

	protected Position getPosition() {
		return this.pos;
	}

//METODOS SOBRE SU VIDA
	public abstract void onDelete();

	public boolean isAlive() {
		return this.life > 0;
	}

	public void die() {
		onDelete();
	}

	public void receiveDamage(int damage) {
		this.life = this.life - damage;
	}

//METODOS SOBRE LA POSICION Y EL MOVIMIENTO
	public abstract void automaticMove();

	protected void performMovement(Move dir) {
		this.pos = pos.move(dir);
	}

	public boolean isOnPosition(Position pos) {
		return (this.pos.equals(pos));
	}

	protected boolean isOut() {
		return !this.pos.isOnBoard();
	}

//METODOS DE ATAQUE
	// metodos de ataque por defecto para los objetos que no se atacan entre ellos
	public boolean performAttack(GameItem other) {
		return false;
	}

	public boolean receiveAttack(EnemyWeapon weapon) {
		return false;
	}

	public boolean receiveAttack(UCMWeapon weapon) {
		return false;
	}

	public boolean receiveAttack(ShockWave wave) {
		return false;
	}
	public void receiveAttack(ExplosiveAlien alien) {}

//OTROS METODOS
	public void computerAction() {}
}
