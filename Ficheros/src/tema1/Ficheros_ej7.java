package tema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;

public class Ficheros_ej7 {
	int numLineas = 0;
	int numPalabras = 0;

	public void menu() {
		int menu = 0;

		switch (menu) {

		}
	}

	public <T> void contador(File file) throws FileNotFoundException {
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				sc.nextLine();
				numLineas++;
			}
		}

		try (Scanner sc2 = new Scanner(file)) {
			while (sc2.hasNext()) {
				sc2.next();
				numPalabras++;
			}
		}

		System.out.println("Líneas: " + numLineas + "\nPalabras: " + numPalabras);
	}
	
	public void opcion_A() {
		
	}

	public static void main(String[] args) throws FileNotFoundException {
		Ficheros_ej7 ej7 = new Ficheros_ej7();
		File file = new File("C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio7\\Texto_ej7.txt");

		ej7.contador(file);
	}

}
