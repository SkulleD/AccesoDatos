package tema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Ficheros_ej4 {
	public static void main(String[] args) {
		Ficheros_ej4 ej4 = new Ficheros_ej4();
		String ruta = "C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\bin\\tema1\\ficherito.txt";
		File file = new File(ruta);
		
		try {
			ej4.leerFichero(file, ruta);
		} catch (FileNotFoundException e) {
			System.out.println("ARCHIVO NO ENCONTRADO");
		} catch (IOException e) {
			System.out.println("ERROR DE ENTRADA/SALIDA");
		}
	}

	public void leerFichero(File file, String ruta) throws FileNotFoundException, IOException {
		HashMap<Character, Integer> hash = new HashMap<>();
		
		try (FileReader reader = new FileReader(ruta)) {
			int i;
			int comp = 0;
			char letra = ' ';
			
			while ((i = reader.read()) != -1) {
				if (hash.containsKey((char)i)) {
					hash.put((char)i, hash.get((char)i + 1));

				}
				else {
					hash.put((char)i, 1);
				}
				
				if (comp < hash.get((char)i)) {
					comp = hash.get((char)i);
					letra = (char)i;
				}
			}
			
			System.out.printf("Tamaño: %d", hash.size());
			System.out.println(hash.get("a"));
			System.out.println(hash.entrySet());
			System.out.printf("\nEl caracter más común es %c", letra);
		}
	}
}