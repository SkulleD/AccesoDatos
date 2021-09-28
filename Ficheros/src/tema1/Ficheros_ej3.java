package tema1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ficheros_ej3 {
	String ruta = "C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\bin\\tema1\\ficherito.txt";

	public static void main (String[] args) {
		Ficheros_ej3 ficheros = new Ficheros_ej3();
		try {
			ficheros.leerFichero();
		} catch (FileNotFoundException e) {
			System.out.println("ARCHIVO NO ENCONTRADO");
		} catch (IOException e) {
			System.out.println("ERROR DE ENTRADA/SALIDA");
		}
	}
	
	public void leerFichero() throws FileNotFoundException, IOException {
		try (FileReader fileIn = new FileReader(ruta)) {
			int i ;
			int cont = 0;
			char letra = 'e';
			while ((i = fileIn.read()) != -1) {
				System.out.print((char) i);
				
				if (i == letra) {
					cont++;
				}
			}
			System.out.printf("\n\nLa letra '%c' aparece %d veces", letra, cont );
		}
	}
}