package tp1.logic;

import java.util.Random;
import tp1.logic.gameobjects.ExplosiveAlien;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Weapon;

public interface GameWorld {

	public Level getLevel();

	public Random getRandom();

	public void receivePoints(int points);

//AÑADE OBJETOS
	public void addObject(GameObject obj);

	public void enableShockWave();

	public void enableLaser();

//METODOS DE ATAQUE
	public void checkAttacksTo(GameItem item);

	public void performAttack(Weapon weapon);

	public void performExplosiveAlien(ExplosiveAlien alien);

}
