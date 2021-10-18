package tema1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ficheros_ej8 {
	public void escribeSinBuffer(File fileLeer, File fileEscribir) throws FileNotFoundException, IOException {
		try (FileInputStream input = new FileInputStream(fileLeer);
				FileOutputStream output = new FileOutputStream(fileEscribir)) {
			int c = 0;
			while ((c = input.read()) != -1) {
				output.write(c);
			}

		} catch (FileNotFoundException e) {

		}

		System.out.println("Tarea finalizada");
	}

	public void escribeConBuffer(File fileLeer, File fileEscribir, byte[] buffer)
			throws FileNotFoundException, IOException {
		try (FileInputStream input = new FileInputStream(fileLeer);
				FileOutputStream output = new FileOutputStream(fileEscribir)) {
			int c = 0;
			
			while ((c = input.read(buffer)) != -1) {
				output.write(buffer, 0 , c);
			}

		} catch (FileNotFoundException e) {

		}
		
		System.out.println("Tarea finalizada");
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Ficheros_ej8 ej8 = new Ficheros_ej8();
		File fileLeer = new File("C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio8\\Palabraza.txt");
		File fileEscribir = new File("C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio8\\FicheroRecibe.txt");
		byte[] buffer = new byte[5000];

		//ej8.escribeSinBuffer(fileLeer, fileEscribir);
		ej8.escribeConBuffer(fileLeer, fileEscribir, buffer);
	}
}