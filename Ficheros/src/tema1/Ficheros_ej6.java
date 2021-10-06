package tema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Ficheros_ej6 {
	public void uneFicheros(String rutaFinal, String archivoFinal) throws IOException, FileNotFoundException {
		File file = new File(rutaFinal);
		File fileFinal = new File(archivoFinal);
		char charUnir = ' ';
		
		if (file.exists()) {
			File[] archivos = file.listFiles();
			for (File texto : archivos) {
				if (texto.isFile()) {
					try (FileReader reader = new FileReader(texto)) {
						int i;
						while ((i = reader.read()) != -1) {
							charUnir = (char) i;
							
							try (PrintWriter writer = new PrintWriter(new FileWriter(fileFinal, true))) {
								writer.print(charUnir);
								
							} catch (FileNotFoundException e) {
								
							} catch (IOException e) {
								
							}
						}
					} catch (FileNotFoundException e) {
						
					} catch (IOException e) {
						
					}
				}
			}
			System.out.println("\nUnido todo en -----> " + fileFinal.getAbsolutePath());
		}
	}
	
	public void leerFichero(File fichero, int n, String rutaEscribir) throws IOException, FileNotFoundException {
		Ficheros_ej6 ej6 = new Ficheros_ej6();
		File file;
		try (FileReader filereader = new FileReader(fichero)) {
			char buffer[] = new char[n];
			int i;
			String aEscribir = "";
			int numArchivo = 0;
			
			while ((i = filereader.read(buffer)) != -1) {
				aEscribir = String.valueOf(buffer);
				file = new File(rutaEscribir + numArchivo +".txt");
				ej6.escribeTrozos(file, aEscribir);
				numArchivo++;
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
	}
	
	public void escribeTrozos(File archivo, String cadena) throws IOException {
		System.out.println("Escrito en -----> " + archivo.getAbsolutePath());
		try (FileWriter filewriter = new FileWriter(archivo)) {
			for (int i = 0; i < cadena.length(); i++) {
				filewriter.write(cadena.charAt(i));
				//filewriter.write(System.getProperty("line.separator"));
			}	
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String rutabase = "C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio6\\TEXTOBASE.txt";
		String rutaEscribir = "C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio6\\Archivos\\archivo";
		String rutaUnirFicheros = "C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio6\\Archivos";
		String archivoFinal = "C:\\Users\\AlvaroVila\\eclipse-workspace\\Ficheros\\Ejercicio6\\ArchivoFinal.txt";
		File file = new File(rutabase);
		Ficheros_ej6 ej6 = new Ficheros_ej6();
		
		ej6.leerFichero(file, 5, rutaEscribir);
		ej6.uneFicheros(rutaUnirFicheros, archivoFinal);

	}
}