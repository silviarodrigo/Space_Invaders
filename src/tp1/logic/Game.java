package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.exceptions.InitializationException;
import tp1.exceptions.LaserInFlightException;
import tp1.exceptions.NoShockWaveException;
import tp1.exceptions.NotEnoughPointsException;
import tp1.exceptions.OffWorldException;
import tp1.logic.gameobjects.*;
import java.util.Random;

public class Game implements GameStatus, GameWorld, GameModel {

	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;

	private Level level;
	private Random random;
	private long seed;
	private int cycle;
	private boolean endGame;
	private UCMShip player;
	private AlienManager alienManager;

	private GameObjectContainer container;

//METODOS INICIALIZADORES
	public Game(Level level, long seed) throws InitializationException {
		this.level = level;
		this.seed = seed;
		// al iniciar el juego se usa una configuracion NONE por defecto
		initGame(InitialConfiguration.NONE);
	}

	private void initGame(InitialConfiguration conf) throws InitializationException {
		// Usamos atributos auxiliares para la inicializacion, hasta saber si esta es correcta
		AlienManager auxAlienManager = new AlienManager(this, this.level);
		GameObjectContainer container = auxAlienManager.initialize(conf);
		// Si no ha lanzado ninguna excepción
		alienManager = auxAlienManager;
		this.container = container;
		this.player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		this.container.add(player);
		this.cycle = 0;
		this.random = new Random(seed);
		this.endGame = false;
	}

//METODOS DEL JUEGO
	// CONSEGUIR DATOS
	public int getCycle() {
		return this.cycle;
	}

	public Random getRandom() {
		return this.random;
	}

	public Level getLevel() {
		return this.level;
	}

	public int getRemainingAliens() {
		return alienManager.getRemainingAliens();
	}

	// IMPRIMIR TABLERO
	public String positionToString(int col, int row) {
		if (player.isAlive()) {
			return container.positionToString(col, row);
		} else {
			return container.positionToString(col, row) + player.toString(new Position(col, row));
		}
	}

	// Información player
	public String stateToString() {
		return player.stateToString();
	}

	// QUIEN GANA
	public boolean isFinished() {
		if (aliensWin() || playerWin() || this.endGame) {
			return true;
		} else
			return false;
	}

	public boolean playerWin() {
		if (this.alienManager.getRemainingAliens() == 0) {
			return true;
		} else
			return false;
	}

	public boolean aliensWin() {
		if (!this.player.isAlive() || this.alienManager.haveLanded()) {
			return true;
		} else
			return false;
	}

// METODOS QUE USA COMMAND
	public void exit() {
		this.endGame = true;
	}

	public void reset(InitialConfiguration conf) throws InitializationException {
		initGame(conf);
	}

	public String listofShips() {
		return Ship.listOfShips();
	}

//METODOS ACTUALIZACIÓN DEL JUEGO
	public void update() {
		this.cycle++;
		container.update();

	}

//METODOS MOVIMIENTO		
	// Comprueba si el movimiento pedido se puede realizar
	// Devuelve si es necesario actulizar e imprimir el tablero
	public void move(Move direction) throws OffWorldException {
		if (direction != null) {
			player.move(direction);
		}
	}

//METODOS BORRAR Y AÑADIR OBJETOS
	// BORRAR
	// Borra el laser actual e informa al player de que puede volver a disparar
	public void enableLaser() {
		this.player.enableLaser();
	}

	// AÑADIR OBJETOS
	// Si el ovni está muerto y el player no tiene ya un shockWave crea uno
	public void enableShockWave() {
		player.shockWave(false);
	}

	public void addObject(GameObject obj) {
		container.add(obj);
	}

//METODOS ATAQUES
	public void performAttack(Weapon weapon) {
		container.performAttack(weapon);
	}

	// Dispara laser
	public void shootLaser() throws LaserInFlightException {
		player.shootLaser();
	}

	// Dispara super laser
	public void shootSuperLaser() throws LaserInFlightException, NotEnoughPointsException {
		player.shootSuperLaser();
	}

	// ATAQUES DEL LASER
	public void checkAttacksTo(GameItem item) {
		this.container.checkAttacksTo(item);

	}

	// ATAQUES DEL SHOCKWAVE
	// Lanza el shockWave
	public void shockWave() throws NoShockWaveException {
		player.executeShockWave();
		
	}

	// ATAQUES DEL EXPLOSIVE ALIEN
	public void performExplosiveAlien(ExplosiveAlien alien) {
		container.performExplosiveAlien(alien);
	}

//METODOS DEL PLAYER
	// Actualiza los puntos ganados por el player
	public void receivePoints(int points) {
		this.player.receivePoints(points);
	}
	

}
