package tp1.logic;

import tp1.view.Messages;

public class Position {

	private final int col;
	private final int row;

	public Position(int col, int row) {
		this.col = col;
		this.row = row;
	}

	// Comprueba si dos posiciones son iguales
	public boolean equals(Position pos) {
		if (this.col == pos.col && this.row == pos.row) {
			return true;
		}
		return false;
	}

	// Dado una posición y un movimiento devuelve la posición final
	public Position move(Move move) {
		return new Position(this.col + move.getX(), this.row + move.getY());
	}

//COMPROBACIONES DE POSICIONES CON RESPECTO AL TABLERO
	// si está dentro
	public boolean isOnBoard() {
		if (this.col < Game.DIM_X && this.col >= 0 && this.row < Game.DIM_Y && this.row >= 0) {
			return true;
		} else
			return false;
	}

	// si está en el borde
	public boolean isInBorder() {
		if (this.col == 0 || this.col == (Game.DIM_X - 1)) {
			return true;
		} else
			return false;
	}

	// si está en la última fila
	public boolean isInFinalRow() {
		if (this.row == (Game.DIM_Y - 1)) {
			return true;
		} else
			return false;
	}

	// Suma a una posicion otra posicion
	public Position sumPosition(int col, int row) {
		return new Position(this.col + col, this.row + row);
	}

//OTROS METODOS
	// metodo usado para los formatted de Messages
	public String printPosition() {
		return Messages.POSITION.formatted(this.col, this.row);
	}
}
