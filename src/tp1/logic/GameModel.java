package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.exceptions.InitializationException;
import tp1.exceptions.LaserInFlightException;
import tp1.exceptions.NoShockWaveException;
import tp1.exceptions.NotEnoughPointsException;
import tp1.exceptions.OffWorldException;

public interface GameModel {

//METODOS DE COMANDOS
	public void move(Move move) throws OffWorldException;

	public void shootLaser() throws LaserInFlightException;

	public void shockWave() throws NoShockWaveException;

	public String listofShips();

	public void exit();

	public void reset(InitialConfiguration conf) throws InitializationException;

	public void shootSuperLaser() throws LaserInFlightException, NotEnoughPointsException;

//OTROS METODOS
	public boolean isFinished();

	public void update();

}
