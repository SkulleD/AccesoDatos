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
	File file = new File("C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio7\\Texto_ej7.txt");
	File fileNew = new File("C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio7\\Nuevotxt.txt");
	int numLineas = 0;
	int numPalabras = 0;
	public boolean sensibleCaso = false;
	ArrayList<String> lista = new ArrayList<>();
	
	public void contador(File file) throws FileNotFoundException {
		String linea = "";
		lista.clear();
		numLineas = 0;
		numPalabras = 0;

		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				linea = sc.nextLine();
				addToArray(linea);
				numLineas++;
			}
		} catch (FileNotFoundException e) {
		}

		try (Scanner sc2 = new Scanner(file)) {
			while (sc2.hasNext()) {
				sc2.next();
				numPalabras++;
			}
		} catch (FileNotFoundException e) {

		}

		System.out.println("Líneas: " + numLineas + "\nPalabras: " + numPalabras);
	}

	public void ordenAscendente() {
		if (sensibleCaso) {
			Collections.sort(lista);
		} else {
			Collections.sort(lista, String.CASE_INSENSITIVE_ORDER);
		}

		for (String linea : lista) {
			escribir(linea);
			System.out.println(linea);
		}
	}

	public void ordenDescendente() {
		if (sensibleCaso) {
			Collections.sort(lista, Collections.reverseOrder());
		} else {
			Collections.sort(lista, String.CASE_INSENSITIVE_ORDER);
			Collections.reverse(lista);
		}

		for (String linea : lista) {
			escribir(linea);
			System.out.println(linea);
		}
	}

	public void addToArray(String linea) {
		lista.add(linea);
	}
	
	public void escribir(String linea) {	
		try (PrintWriter writer = new PrintWriter(new FileWriter(fileNew, true))) {
			writer.println(linea);
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e1) {

		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Ficheros_ej7 ej7 = new Ficheros_ej7();

		ej7.contador(ej7.file);
		ej7.contador(ej7.file);
		ej7.sensibleCaso = false;
		ej7.ordenAscendente();
		System.out.printf("Nuevo archivo txt creado en %s", ej7.fileNew.toString());
	}
}