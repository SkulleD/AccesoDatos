package tema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Ficheros_ej7 {
	int numLineas = 0;
	int numPalabras = 0;
	boolean sensibleCaso = false;
	ArrayList<String> lista = new ArrayList<>();

	public void menu() {
		int menu = 0;

		switch (menu) {

		}
	}
	
	public void contador(File file) throws FileNotFoundException {
		File file2 = new File("C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio7\\Nuevotxt.txt");
		String palabra = "";
		
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				sc.nextLine();
				numLineas++;
			}
		} catch (FileNotFoundException e) {	
		}

		try (Scanner sc2 = new Scanner(file)) {
			while (sc2.hasNext()) {
				palabra = sc2.next();
				addToArray(palabra);
				numPalabras++;
			}
		} catch (FileNotFoundException e) {
			
		}

		System.out.println("Líneas: " + numLineas + "\nPalabras: " + numPalabras);
	}
	
	public void ordenAscendente() {
		Collections.sort(lista);
		
		if (!sensibleCaso()) {
			for (String palabra : lista) {
				System.out.println(palabra);
			}
		} else {
			
		}

	}
	
	public void ordenDescendente() {
		Collections.sort(lista, Collections.reverseOrder());
		
		if (!sensibleCaso()) {
			for (String palabra : lista) {
				System.out.println(palabra);
			}
		} else {
			
		}
	}
	
	public boolean sensibleCaso() {
		return sensibleCaso;
	}
	
	public void addToArray(String palabra) {
		lista.add(palabra);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Ficheros_ej7 ej7 = new Ficheros_ej7();
		File file = new File("C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio7\\Texto_ej7.txt");

		ej7.contador(file);
		ej7.ordenDescendente();
	}
}
