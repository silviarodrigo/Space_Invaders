package tp1.logic.gameobjects;

import tp1.logic.Position;

public interface GameItem {

//METODOS DE ATAQUE
	public boolean performAttack(GameItem other);

	public boolean receiveAttack(EnemyWeapon weapon);

	public boolean receiveAttack(UCMWeapon weapon);

	public boolean receiveAttack(ShockWave wave);

	public void receiveAttack(ExplosiveAlien alien);

//OTROS METODOS
	public boolean isAlive();

	public boolean isOnPosition(Position pos);

}