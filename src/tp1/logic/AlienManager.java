package tp1.logic;

import tp1.logic.gameobjects.RegularAlien;
import tp1.view.Messages;
import tp1.control.InitialConfiguration;
import tp1.exceptions.InitializationException;
import tp1.logic.gameobjects.*;

public class AlienManager {

	private Level level;
	private GameWorld game;
	private int remainingAliens;
	private boolean squadInFinalRow;
	private int shipsOnBorder;
	private boolean onBorder;

	public AlienManager(GameWorld game, Level level) {
		this.level = level;
		this.game = game;
		this.remainingAliens = 0;
		this.squadInFinalRow = false;
		this.shipsOnBorder = 0;
		this.onBorder = false;
	}

//METODOS GETTERS
	public int getSpeed() {
		return this.level.getNumCyclesToMoveOneCell();
	}

//METODOS INICIALIZADORES
	// Inicializa los AlienShip y el Ufo
	public GameObjectContainer initialize(InitialConfiguration conf) throws InitializationException {
		GameObjectContainer container = new GameObjectContainer();
		if (conf.equals(InitialConfiguration.NONE)) {
			initializeRegularAliens(container);
			initializeDestroyerAliens(container);
		} else {
			costumedInitialization(container, conf);
		}
		initializeOvni(container);
		return container;
	}

	// CONFIGURACION NONE
	// Inicializa los RegularAlien
	protected void initializeRegularAliens(GameObjectContainer container) {
		int numAliens = this.level.getNumRegularAliens();
		int TotalRow = this.level.getNumRowsRegularAliens();
		for (int i = 0; i < TotalRow; i++) {// inicializa los aliens en su posición inicial
			for (int t = 0; t < (numAliens / TotalRow); t++) {
				container.add(new RegularAlien(this.game, new Position(2 + t, 1 + i), this));
				this.remainingAliens++;
			}
		}
	}

	// Inicializa los DestroyerAlien
	protected void initializeDestroyerAliens(GameObjectContainer container) {
		int numAliens = this.level.getNumDestroyerAliens();
		int row = this.level.getNumRowsRegularAliens();
		int startCol = (Game.DIM_X / 2) - (numAliens / 2);
		for (int i = 0; i < numAliens; i++) {// inicializa los aliens en su posición inicial
			container.add(new DestroyerAlien(this.game, new Position(startCol + i, 1 + row), this));
			this.remainingAliens++;
		}
	}

	// Inicializa el Ufo
	private void initializeOvni(GameObjectContainer container) {
		container.add(new Ufo(game));
	}

	// CONFIGURACION SACADA DE FICHERO
	// Inicializa siguiendo una configuracion inicial
	private void costumedInitialization(GameObjectContainer container, InitialConfiguration conf)
			throws InitializationException {
		for (String shipDescription : conf.getShipDescription()) {
			String[] words = shipDescription.toLowerCase().trim().split("\\s+");
			if (words.length != 3) {// si hay mas/menos parametros ya es una entrada incorrecta
				throw new InitializationException(Messages.INCORRECT_ENTRY.formatted(shipDescription));
			}
			try {
				AlienShip ship = ShipFactory.spawnAlienShip(words[0], game,
						new Position(Integer.valueOf(words[1]), Integer.valueOf(words[2])), this);
				container.add(ship);
				this.remainingAliens++;
			} catch (NumberFormatException e) {
				// hacemos Integer.valueOf y la configuracion no envia una posicion valida
				throw new InitializationException(
						Messages.INVALID_POSITION.formatted(words[1].toUpperCase(), words[2].toUpperCase()));
			}
		}

	}

//METODOS DE CONTROL
	// SOBRE EL BORDE Y BAJADA
	public void shipOnBorder() {
		if (!onBorder) {
			this.onBorder = true;
			this.shipsOnBorder = this.remainingAliens;
		}
	}

	public boolean onBorder() {
		return this.onBorder;
	}

	// Funcion que indica que ha bajado un alien
	public void alienDescended() {
		this.shipsOnBorder--;
		if (this.shipsOnBorder == 0) {
			this.onBorder = false;
		}
	}

	// SOBRE ULTIMA FILA
	public boolean haveLanded() {
		return this.squadInFinalRow;
	}

	public void finalRowReached() {
		this.squadInFinalRow = true;
	}

	// SOBRE LOS ALIENS
	public int getRemainingAliens() {
		return this.remainingAliens;
	}

	public void alienDead() {
		this.remainingAliens--;
		if (this.onBorder) {
			this.shipsOnBorder--;
		}
	}

}
