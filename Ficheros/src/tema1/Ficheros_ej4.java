package tema1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ficheros_ej4 {
	String ruta = "C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\bin\\tema1\\ficherito.txt";
	
	public static void main(String[] args) {
		Ficheros_ej4 ej4 = new Ficheros_ej4();
		try {
			ej4.leerFichero();
		} catch (FileNotFoundException e) {
			System.out.println("ARCHIVO NO ENCONTRADO");
		} catch (IOException e) {
			System.out.println("ERROR DE ENTRADA/SALIDA");
		}
	}

	public void leerFichero() throws FileNotFoundException, IOException {
		ArrayList<Character> letras = new ArrayList<Character>();
		
		try (FileReader reader = new FileReader(ruta)) {
			int i;
			
			while ((i = reader.read()) != -1) {
				System.out.print((char) i);
				letras.add((char) i);
			}
			
			for (int j = 0; j < letras.size(); j++) {
				System.out.println(letras.get(j));
			}
		}
	}
}