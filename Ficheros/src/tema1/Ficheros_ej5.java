package tema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ficheros_ej5 {
	String ruta = "C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Texto5.txt";
	File archivo = new File(ruta);
	String cadena = "velit esse cillum";

	public static void main(String[] args) {
		Ficheros_ej5 ej5 = new Ficheros_ej5();
		try {
			ej5.leerFichero(ej5.cadena);
		} catch (FileNotFoundException e) {
			System.out.println("ARCHIVO NO ENCONTRADO");
		} catch (IOException e) {
			System.out.println("ERROR DE ENTRADA/SALIDA");
		}
	}

	public void leerFichero(String cadena) throws FileNotFoundException, IOException {
		File[] lista = archivo.listFiles();

		int i = 0;
		int lineaCadena = 0;
		boolean encontrar = false;

		try (FileReader reader = new FileReader(ruta)) {
			while ((i = reader.read()) != -1) {
				System.out.print((char) i);
				
				//if () {
					//lineaCadena++;
				//}
			}
		}
		
		if (ruta.contains(cadena)) {
			encontrar = true;
		} else {
			encontrar = false;
		}
		
		if (encontrar) {
			System.out.printf("\n\nLa cadena \"%s\" aparece en la línea %d.", cadena, lineaCadena);
		} else {
			System.out.println("\n\nEl archivo no contiene esa cadena.");
		}
	}
}