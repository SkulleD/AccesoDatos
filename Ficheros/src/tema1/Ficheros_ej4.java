package tema1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
		HashMap<Character, Integer> hash = new HashMap<>();
		
		try (FileReader reader = new FileReader(ruta)) {
			int i;
			int cont = 0;
			
			while ((i = reader.read()) != -1) {
				if (hash.containsKey((char)i)) {
					hash.put((char)i, hash.get((char)i + 1));
					cont++;
				}
				else {
					hash.put((char)i, 1);
				}
			}
			
			System.out.printf("Tamaño: %d", hash.size());
			System.out.println(hash.get("a"));
			System.out.println(hash.entrySet());
			System.out.println(cont);
		}
	}
}