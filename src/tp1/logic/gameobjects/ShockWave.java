package tp1.logic.gameobjects;

import tp1.view.Messages;
import tp1.logic.*;

public class ShockWave extends UCMWeapon {
	private static final int DAMAGE = 1;

	public ShockWave(GameWorld game) {
		super(game, new Position(0,0), 1, Move.NONE); // Realmente el ShockWave no tiene ninguna direccion
	}

//METODOS GETTER
	public int getDamage() {
		return DAMAGE;
	}

	public int getArmour() {
		return 0;
	}

// METODOS PARA IMPRIMIR TABLERO E INFORMACIÓN
	protected String getSymbol() {
		return Messages.SHOCKWAVE_SYMBOL;
	}

	public String toString() {
		return "";// devolvemos una cadena vacia porque el simbolo no lo usamos nunca
	}

//METODOS SOBRE SU VIDA
	public void onDelete() {
		this.life = 0; // Ponemos su vida a 0 para poder eliminarlo del container.
	}

//METODOS SOBRE SU POSICION Y MOVIMIENTO
	public void automaticMove() {
		;
	}

//METODOS DE ATAQUE
	// sobrescribimos el metodo porque el shockWave y las naves no tienen que
	// compartir posicion
	public boolean performAttack(GameItem other) {
		if (other.isAlive()) {
			weaponAttack(other);
			return true;
		} else {
			return false;
		}
	}

	// sobrescribimos para que por defecto vaya a receiveAttack(ShockWave wave)
	// menos en los casos de los AlienShip
	protected boolean weaponAttack(GameItem object) {
		return object.receiveAttack(this);
	}

}
