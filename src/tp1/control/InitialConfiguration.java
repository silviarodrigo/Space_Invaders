package tp1.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InitialConfiguration {

	public static final InitialConfiguration NONE = new InitialConfiguration();
	private ArrayList<String> descriptions;

	private InitialConfiguration() {
	}

	private InitialConfiguration(ArrayList<String> descriptions) {
		this.descriptions = descriptions;
	}

	public static InitialConfiguration readFromFile(String filename) throws FileNotFoundException, IOException {
		BufferedReader entrada = null;
		ArrayList<String> descriptions = new ArrayList<String>();
		try {
			entrada = new BufferedReader(new FileReader(filename));
			String line = entrada.readLine();
			while (line != null) {
				descriptions.add(line);
				line = entrada.readLine(); // leer la siguiente linea
			}
		} finally {
			if (entrada != null) {// cerramos el fichero siempre
				entrada.close();
			}
		}
		return new InitialConfiguration(descriptions);
	}

	public List<String> getShipDescription() {
		return Collections.unmodifiableList(descriptions);
	}

}
