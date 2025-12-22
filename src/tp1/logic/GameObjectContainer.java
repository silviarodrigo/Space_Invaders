package tp1.logic;

import java.util.ArrayList;
import tp1.logic.gameobjects.*;
import java.util.List;

public class GameObjectContainer {

	private List<GameObject> objects;

	public GameObjectContainer() {
		objects = new ArrayList<GameObject>();
	}

	public void add(GameObject object) {
		objects.add(object);
	}

	public void remove(GameObject object) {
		objects.remove(object);
	}

	public String positionToString(int col, int row) {
		StringBuilder str = new StringBuilder();// creamos una cadena con todos los simbolos de los objetos
		for (GameObject object : objects) {
			if (object != null && object.isOnPosition(new Position(col, row))) {
				str.append(object.toString());
			}
		}
		return str.toString();
	}

//METODOS DE ATAQUE
	// Las armas atacan
	public void performAttack(Weapon weapon) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject object = objects.get(i);
			if (object != weapon) {
				weapon.performAttack(object);
			}
		}
	}

	// El ExplosiveAlien ataca
	public void performExplosiveAlien(ExplosiveAlien alien) {
		for (GameObject object : objects) {
			object.receiveAttack(alien);
		}
	}

//	// Los objetos comprueban que no esten siendo atacados
	public void checkAttacksTo(GameItem item) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject object = objects.get(i);
			if (object != item) {
				object.performAttack(item);
			}
		}
	}

//UPDATE DEL JUEGO
	public void update() {
		computerActions();
		automaticMoves();
		removeDead();
	}

	public void computerActions() {
		for (GameObject object : objects) {
			object.computerAction();
		}
	}

	public void automaticMoves() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject object = objects.get(i);
			object.automaticMove();
		}
	}

	public void removeDead() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject object = objects.get(i);
			if (!object.isAlive()) {
				object.die();
				remove(object);
				// Para que vuelva a revisar el último elemento.
				i--;
			}
		}
	}

}