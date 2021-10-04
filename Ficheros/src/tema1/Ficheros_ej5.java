package tema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Ficheros_ej5 {
	public static void main(String[] args) {
		Ficheros_ej5 ej5 = new Ficheros_ej5();
		String ruta = "C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Texto5.txt";
		File archivo = new File(ruta);
		String cadena = "pesado";
		
		try {	
			ej5.leerFichero(archivo, cadena);
		} catch (FileNotFoundException e) {
			System.out.println("ARCHIVO NO ENCONTRADO");
		} catch (IOException e) {
			System.out.println("ERROR DE ENTRADA/SALIDA");
		}
	}

	public void leerFichero(File fichero, String cadena) throws FileNotFoundException, IOException {
		int lineaCadena = 0;
		String linea = "";
		boolean encontrado = false;

		try (Scanner sc = new Scanner(fichero)) {
			while (sc.hasNextLine()) {
				linea = sc.nextLine();
				lineaCadena++;
				
				if (linea.contains(cadena)) {
					encontrado = true;
					System.out.printf("La cadena \"%s\" aparece en la línea %d.\n", cadena, lineaCadena);
				}
			}
		}
		
		if (!encontrado) {
			System.out.println("El archivo no contiene esa cadena.");
		}
	}
}